import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import static java.time.temporal.ChronoUnit.SECONDS;

public class DP181IAverageSpeed {
    private static final Pattern speedP = Pattern.compile("Speed limit is ([0-9]+\\.[0-9]+) (mph|km/h).");
    private static final Pattern cameraP = Pattern.compile("Speed camera number ([0-9]+) is ([0-9]+) metres down the motorway.");
    private static final Pattern vehicalP = Pattern.compile("Vehicle ([0-9 A-Z]+) passed camera ([0-9]+) at ([0-9]{2}:[0-9]{2}:[0-9]{2}).");


    public static void main(String[] args) throws IOException {
       final List<Integer> cameraDistances = new ArrayList<>();
       final Map<String, List<LocalTime>> vehicalLog = new HashMap<>();

       final List<String> inputLines = Files.readAllLines(Paths.get("181I_input1.txt"));

       final float speedLimit = Float.parseFloat(inputLines.get(0)
            .replaceAll(DP181IAverageSpeed.speedP.pattern(), "$1"));


       inputLines.stream().skip(1).forEach(line ->{
           final Matcher cameraM = DP181IAverageSpeed.cameraP.matcher(line);
           if (cameraM.matches()){
               cameraDistances.add(Integer.parseInt(cameraM.group(2)));
           } else {
               final Matcher logM = DP181IAverageSpeed.vehicalP.matcher(line);
               if (logM.matches()){
                   String vehicle = logM.group(1);
                   LocalTime time = LocalTime.parse(logM.group(3));
                   vehicalLog.computeIfAbsent(vehicle, t -> new ArrayList<LocalTime>()).add(time);
               }
           }
       });


       for(Map.Entry<String, List<LocalTime>> entry: vehicalLog.entrySet()){
           List<LocalTime> logs = entry.getValue();
           System.out.println(entry.getKey());
           for (LocalTime time: logs){
               System.out.println("\t" + time);
           }
       }

       for (int i=1; i < cameraDistances.size(); i++){
           int distance = cameraDistances.get(i) - cameraDistances.get(i-1);
           for (Map.Entry<String, List<LocalTime>> entry: vehicalLog.entrySet()){
                List<LocalTime> logs = entry.getValue();
                LocalTime first = logs.get(i-1);
                LocalTime second = logs.get(i);

                float interval = first.until(second, SECONDS);
                double speed = 2.237 * distance/interval;

                if (speed > speedLimit){
                    System.out.printf("Vehicle %s broke the speed limit by %.1f mph.\n", entry.getKey(), speed - speedLimit);
                }
           }
       }
    }
}
