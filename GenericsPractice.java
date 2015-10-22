import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.function.Consumer;

class NaturalNumber {

	private int i;

    public NaturalNumber(int i) { 
		this.i = i;
    }
}

class EvenNumber extends NaturalNumber{

	private int i;

    public EvenNumber(int i) { 
        super(i);
    }

    public void set(){
        System.out.println("AHA");
    }
}

//class ENConsumer <T extends EvenNumber> {
class ENConsumer implements Consumer<NaturalNumber> {

    @Override
    public void accept(NaturalNumber n){
        System.out.println("ACCEPT");
    }
}

public class GenericsPractice {
    public static void main(String[] args) {
        Optional<EvenNumber> en = Optional.ofNullable(new EvenNumber(1));
        en.ifPresent(new ENConsumer());
    }
    
}
