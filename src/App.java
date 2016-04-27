/**
 * Created by Barret J. Nobel on 4/26/2016.
 */
public class App {
    public static void main( String[] args ){
        Neuron test1 = new Neuron("test1", 3.3858 );
//        System.out.println( test1.getThreshold() );

        Neuron test2 = new Neuron( "test2", 1.0 );
        Neuron test3 = new Neuron( "test3", 1.8 );


        test1.setAConnectedNodeDirection( "forward", test2 );
        test1.setAConnectedNodeDirection( "forward", test3 );
        System.out.println( test1.getConnectedNodesDirection("forward") );
        Neuron text4 = new Neuron( test1 );
        System.out.println( text4.getConnectedNodesDirection("forward") );
//        test1.setAConnectedWeightDirection( "FORWARD", 2.0 );
//        System.out.println( test1.getWeightDirection( "FORWARD" ) );
    }
}
