import java.io.File;
import java.io.FileNotFoundException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.HashMap;
import java.util.Date;
import java.util.Iterator;
import java.math.BigDecimal;
import java.math.RoundingMode;

public class AverageSpeed {

    public AverageSpeed() { 
    }
    
    public static void average_speed(Scanner input) throws Exception{
        BigDecimal speed_limit = new BigDecimal(0);
        String unit = "km/h";
        float _RATIO =  1.609344f;

        //reading speed limit
        Pattern pat = Pattern.compile("Speed limit is ([0-9.]+) (mph|km/h).");
        String line = input.nextLine();
        Matcher mat = pat.matcher(line);
        if (mat.find()){
            speed_limit = new BigDecimal(Float.parseFloat(mat.group(1)));
            unit = mat.group(2);
        }
        if ("mph".equals(unit)) speed_limit = speed_limit.multiply(new BigDecimal(_RATIO));

        //reading camera
        HashMap<Integer, Integer> cameras = new HashMap<Integer, Integer>();
        pat = Pattern.compile("Speed camera number ([0-9]+) is ([0-9]+) metres down the motorway.");
        while (true) {
            mat = pat.matcher(input.nextLine());
            if (mat.find()){
                cameras.put(Integer.parseInt(mat.group(1)), Integer.parseInt(mat.group(2)));
            }
            else break;
        }

        //reading log
        HashMap<String, HashMap<Integer, Date>> cars = new HashMap<String, HashMap<Integer, Date>>();
        pat = Pattern.compile("Vehicle ([0-9A-Z ]+) passed camera ([0-9]+) at ([0-9:]+).");
        SimpleDateFormat format = new SimpleDateFormat("HH:mm:ss");
        while (input.hasNextLine()){
            mat = pat.matcher(input.nextLine());
            if (mat.find()){
                String reg_num = mat.group(1);
                Integer cam = Integer.parseInt(mat.group(2));
                Date date = format.parse(mat.group(3));
                if (cars.containsKey(reg_num)){
                    HashMap<Integer, Date> log = cars.get(reg_num);
                    log.put(cam, date);
                } else {
                    HashMap<Integer, Date> log = new HashMap<Integer, Date>();
                    log.put(cam, date);
                    cars.put(reg_num, log);
                }
            } 
        }

        //for (String car: cars.keySet()){
            //System.out.print(car + " ");
            //System.out.println(cars.get(car));
        //}

        for (String car: cars.keySet()){
            HashMap<Integer, Date> log = cars.get(car);
            Iterator<Map.Entry<Integer, Date>>  it = log.entrySet().iterator();
            Map.Entry<Integer, Date> prev = it.next();
            while (it.hasNext()){
                Map.Entry<Integer, Date> next = it.next();
                BigDecimal distance = new BigDecimal(cameras.get(next.getKey())).subtract(new BigDecimal(cameras.get(prev.getKey())));
                //float distance = (cameras.get(next.getKey()) - cameras.get(prev.getKey()));
                BigDecimal time = new BigDecimal(next.getValue().getTime()).subtract(new BigDecimal(prev.getValue().getTime())).divide(new BigDecimal(3600), 6, RoundingMode.HALF_UP);
                BigDecimal diff = distance.divide(time, 6, RoundingMode.HALF_UP).subtract(speed_limit);
                //System.out.println("distance " + distance + " time " +  time + " diff " + diff);
                if (diff.compareTo(BigDecimal.ZERO) > 0){
                    if ("mph".equals(unit))
                        System.out.println("Vehicle " + car + " broke the speed limit by " + diff.divide(new BigDecimal(_RATIO), 6, RoundingMode.HALF_UP) + " mph.");
                    else 
                        System.out.println("Vehicle " + car + " broke the speed limit by " + diff + " km/h.");
                }
                prev = next;
            }
        }
    }



    public static void main(String[] args) throws Exception{
       File file = new File(args[0]);
       try {
           Scanner scanner = new Scanner(file);
            average_speed(scanner);

       } catch(FileNotFoundException e){
           e.printStackTrace();
       }  
    }
}
