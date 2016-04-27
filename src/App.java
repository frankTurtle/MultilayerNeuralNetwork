/**
 * Created by Barret J. Nobel on 4/26/2016.
 */

import java.util.HashMap;

public class App {
    private final static String FORWARD = "forward"; //....................... final value for forward key in map
    private final static String BACKWARD = "backward"; //..................... final value for backward key in map
    private final static String[] NEURON_NAMES = { "ID3","ID4", "ID5",
                                                   "ID6","ID7", "ID8",
                                                   "ID9","IDX1", "IDX2" }; //. final array full of neuron names

    public static void main( String[] args ){
        double x1 = 0;
        double x2 = 0;
        HashMap< String, Neuron > neuronHashMap = new HashMap<>();

        setupNeuronConnections( neuronHashMap );
//        System.out.println( neuronHashMap.get(NEURON_NAMES[0]).getConnectedNodesDirection(BACKWARD) );

        for( String key : NEURON_NAMES ){ System.out.println( neuronHashMap.get(key).getWeightDirection(FORWARD) ); }
    }

    // Helper method to setup all the connections between nodes
    private static void setupNeuronConnections( HashMap< String, Neuron > neuronHashMap ){
        for( String name : NEURON_NAMES ){ //................................................ loop through each Neuron
            Neuron addMe = new Neuron( name ); //............................................ create a new one with the name
            neuronHashMap.put( name, addMe ); //............................................. add it into HashMap
        }

        Neuron neuron3  = neuronHashMap.get( NEURON_NAMES[0] ).clone(); //................... get a clone of each Neuron
        Neuron neuron4  = neuronHashMap.get( NEURON_NAMES[1] ).clone();
        Neuron neuron5  = neuronHashMap.get( NEURON_NAMES[2] ).clone();
        Neuron neuron6  = neuronHashMap.get( NEURON_NAMES[3] ).clone();
        Neuron neuron7  = neuronHashMap.get( NEURON_NAMES[4] ).clone();
        Neuron neuron8  = neuronHashMap.get( NEURON_NAMES[5] ).clone();
        Neuron neuron9  = neuronHashMap.get( NEURON_NAMES[6] ).clone();
        Neuron neuronX1 = neuronHashMap.get( NEURON_NAMES[7] ).clone();
        Neuron neuronX2 = neuronHashMap.get( NEURON_NAMES[8] ).clone();

        for( int index = 0; index < NEURON_NAMES.length; index++ ){ //....................... loop through each Neuron
            switch( index ){
                case 0: //................................................................... Neuron 3
                    neuron3.setAConnectedNodeDirection( FORWARD, neuron5, neuron6 ); //...... setup connecting nodes
                    neuron3.setAConnectedNodeDirection( BACKWARD, neuronX1 );
                    neuron3.setAConnectedNodeDirection( BACKWARD, neuronX2 );

                    neuron5.setAConnectedNodeDirection( BACKWARD, neuron3 ); //.............. nodes its connected too also point to this one
                    neuron6.setAConnectedNodeDirection( BACKWARD, neuron3 );
                    break;

                case 1: //................................................................... Neuron 4
                    neuron4.setAConnectedNodeDirection( FORWARD, neuron5, neuron6 );
                    neuron4.setAConnectedNodeDirection( BACKWARD, neuronX1 );
                    neuron4.setAConnectedNodeDirection( BACKWARD, neuronX2 );

                    neuron5.setAConnectedNodeDirection( BACKWARD, neuron4 );
                    neuron6.setAConnectedNodeDirection( BACKWARD, neuron4 );
                    break;

                case 2: //................................................................... Neuron 5
                    neuron5.setAConnectedNodeDirection( FORWARD, neuron7, neuron8 );

                    neuron7.setAConnectedNodeDirection( BACKWARD, neuron5 );
                    neuron8.setAConnectedNodeDirection( BACKWARD, neuron5 );
                    break;

                case 3: //................................................................... Neuron 6
                    neuron6.setAConnectedNodeDirection( FORWARD, neuron7, neuron8 );

                    neuron7.setAConnectedNodeDirection( BACKWARD, neuron6 );
                    neuron8.setAConnectedNodeDirection( BACKWARD, neuron6 );
                    break;

                case 4: //................................................................... Neuron 7
                    neuron7.setAConnectedNodeDirection( FORWARD, neuron9 );

                    neuron9.setAConnectedNodeDirection( BACKWARD, neuron7 );
                    break;

                case 5: //................................................................... Neuron 8
                    neuron8.setAConnectedNodeDirection( FORWARD, neuron9 );

                    neuron9.setAConnectedNodeDirection( BACKWARD, neuron8 );
                    break;

                case 7: //................................................................... X1
                    neuronX1.setAConnectedNodeDirection( FORWARD, neuron3, neuron4 );
                    break;

                case 8: //................................................................... X2
                    neuronX2.setAConnectedNodeDirection( FORWARD, neuron3, neuron4 );
                    break;

            }
        }
    }
}
