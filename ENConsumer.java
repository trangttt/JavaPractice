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

    public static void set(){
        System.out.println("AHA");
    }
}

public class ENConsumer implements Consumer<EvenNumber> {

    @Override
    public void accept(EvenNumber n){
        System.out.println("ACCEPT");
    }
}

