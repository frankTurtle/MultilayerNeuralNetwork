/**
 * Created by Barret J. Nobel on 4/26/2016.
 * Requires 3 int arguments representing the 2 inputs and 1 desired output
 */

import java.util.HashMap;
import java.util.Map;

public class App {
    private final static String FORWARD = "forward"; //....................... final value for forward key in map
    private final static String BACKWARD = "backward"; //..................... final value for backward key in map
    private final static double ALPHA = 0.1; //.............................. final value for the learning
    private final static double ERROR_LOW = -0.01;
    private final static double ERROR_HIGH = 0.01;
    private final static String X1 = "IDX1"; //............................... final value for the first input
    private final static String X2 = "IDX2"; //............................... final value for the second input
    private final static String OUTPUT = "ID9"; //............................ final value for the output node
    private final static String[] NEURON_NAMES = { "ID3","ID4", "ID5",
                                                   "ID6","ID7", "ID8",
                                                   OUTPUT,X1,X2 }; //......... final array full of neuron names

    public static void main( String[] args ){
        HashMap< String, Neuron > neuronHashMap = new HashMap<>(); //.................................. variables to hold all neurons and deltas
        HashMap< String, Double > deltaHashMap = new HashMap<>();
        double error; //............................................................................... holds the error

        setupNeuronConnections( neuronHashMap,
                Double.parseDouble(args[0]), Double.parseDouble(args[1]) ); //......................... setup all connections between neurons

        int count = 0; //.............................................................................. a counter to see how many iterations
        do{
            computeSigmoid( neuronHashMap, FORWARD ); //............................................... compute all sigmoids
            error = Double.parseDouble(args[2]) - neuronHashMap.get( OUTPUT ).getSigmoid(); //......... compute error of output

            if(count % 10000 == 0 ){
                System.out.println( String.format( "Input: %s %s %s", args[0], args[1], args[2]) );
                System.out.println( "Alpha: " + ALPHA );
                printOutput( count, neuronHashMap );
            }

            deltaHashMap.put( OUTPUT, computeDeltaForOutput(neuronHashMap.get(OUTPUT), error) ); //.... put the delta of the output
            computeDelta( deltaHashMap, neuronHashMap ); //............................................ compute deltas for the rest of the neurons
            computeAndUpdateWeightCorrection( neuronHashMap, deltaHashMap ); //........................ calc change in weights and update current values
            count++; //................................................................................ increment the counter
        }while( Math.abs(error) > ERROR_HIGH || Math.abs(error) < ERROR_LOW ); //...................... repeat all the above while error is out of range

        System.out.println( "Final iteration: " + count + "\nError: " + error +
                "\nActual Output: " + neuronHashMap.get(OUTPUT).getSigmoid() ); //..................... prints out the error value at the end and actual output

    }

    // Helper method to setup all the connections between nodes
    private static void setupNeuronConnections( HashMap< String, Neuron > neuronHashMap,
                                                double xInput1, double xInput2 ){
        for( String name : NEURON_NAMES ){ //................................................ loop through each Neuron
            Neuron addMe = new Neuron( name ); //............................................ create a new one with the name

            if( name.equals(X1) ) addMe.setSigmoid( xInput1 ); //............................ if its the X inputs, set sigmoids to passed in value
            if( name.equals(X2) ) addMe.setSigmoid( xInput2 );
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

        for( int index = 0; index < NEURON_NAMES.length; index++ ){ //................................... loop through each Neuron
            switch( index ){
                case 0: //............................................................................... Neuron 3
                    neuron3.setAConnectedNodeDirection( FORWARD, neuron5, neuron6 ); //.................. setup connecting nodes
                    neuron3.setAConnectedNodeDirection( BACKWARD, neuronX1, neuronX2 );

                    neuron3.setAConnectedWeightNamed( neuron5.getName(), Neuron.randomNumber() ); //..... setup the connecting weights
                    neuron3.setAConnectedWeightNamed( neuron6.getName(), Neuron.randomNumber() );

                    neuron5.setAConnectedNodeDirection( BACKWARD, neuron3 ); //.......................... nodes its connected too also point to this one
                    neuron6.setAConnectedNodeDirection( BACKWARD, neuron3 );
                    break;

                case 1: //............................................................................... Neuron 4
                    neuron4.setAConnectedNodeDirection( FORWARD, neuron5, neuron6 );
                    neuron4.setAConnectedNodeDirection( BACKWARD, neuronX1, neuronX2 );

                    neuron4.setAConnectedWeightNamed( neuron5.getName(), Neuron.randomNumber() );
                    neuron4.setAConnectedWeightNamed( neuron6.getName(), Neuron.randomNumber() );

                    neuron5.setAConnectedNodeDirection( BACKWARD, neuron4 );
                    neuron6.setAConnectedNodeDirection( BACKWARD, neuron4 );
                    break;

                case 2: //............................................................................... Neuron 5
                    neuron5.setAConnectedNodeDirection( FORWARD, neuron7, neuron8 );

                    neuron5.setAConnectedWeightNamed( neuron7.getName(), Neuron.randomNumber() );
                    neuron5.setAConnectedWeightNamed( neuron8.getName(), Neuron.randomNumber() );

                    neuron7.setAConnectedNodeDirection( BACKWARD, neuron5 );
                    neuron8.setAConnectedNodeDirection( BACKWARD, neuron5 );
                    break;

                case 3: //............................................................................... Neuron 6
                    neuron6.setAConnectedNodeDirection( FORWARD, neuron7, neuron8 );

                    neuron6.setAConnectedWeightNamed( neuron7.getName(), Neuron.randomNumber() );
                    neuron6.setAConnectedWeightNamed( neuron8.getName(), Neuron.randomNumber() );

                    neuron7.setAConnectedNodeDirection( BACKWARD, neuron6 );
                    neuron8.setAConnectedNodeDirection( BACKWARD, neuron6 );
                    break;

                case 4: //.............................................................................. Neuron 7
                    neuron7.setAConnectedNodeDirection( FORWARD, neuron9 );

                    neuron7.setAConnectedWeightNamed( neuron9.getName(), Neuron.randomNumber() );

                    neuron9.setAConnectedNodeDirection( BACKWARD, neuron7 );
                    break;

                case 5: //.............................................................................. Neuron 8
                    neuron8.setAConnectedNodeDirection( FORWARD, neuron9 );

                    neuron8.setAConnectedWeightNamed( neuron9.getName(), Neuron.randomNumber() );

                    neuron9.setAConnectedNodeDirection( BACKWARD, neuron8 );
                    break;

                case 7: //.............................................................................. X1
                    neuronX1.setAConnectedNodeDirection( FORWARD, neuron3, neuron4 );

                    neuronX1.setAConnectedWeightNamed( neuron3.getName(), Neuron.randomNumber() );
                    neuronX1.setAConnectedWeightNamed( neuron4.getName(), Neuron.randomNumber() );
                    break;

                case 8: //.............................................................................. X2
                    neuronX2.setAConnectedNodeDirection( FORWARD, neuron3, neuron4 );

                    neuronX2.setAConnectedWeightNamed( neuron3.getName(), Neuron.randomNumber() );
                    neuronX2.setAConnectedWeightNamed( neuron4.getName(), Neuron.randomNumber() );
                    break;

            }
        }
    }

    // Helper method to compute all sigmoid functions
    private static void computeSigmoid( HashMap< String, Neuron > neuronHashMap, String direction ){
        for( String key : NEURON_NAMES ){ //........................................................... loop through each neuron name
            if( key.equals(X1) || key.equals(X2)){ continue; } //...................................... if its the input nodes ignore
            neuronHashMap.get( key ).calculateSigmoid( direction ); //................................. calculate sigmoid
        }
    }

    // Helper method to compute all delta values
    // doesnt compute the output delta, thats handled below as a special case
    private static void computeDelta( HashMap< String, Double > deltaHashMap,
                                      HashMap< String, Neuron > neuronHashMap){
        double weight = 0.0; //..................................................... instance variables to hold all values used in calculation
        double sum = 0.0;
        double delta;
        double value;

        for( int key = 5; key > -1; key-- ){ //..................................... loop through each value in names array
            Neuron neuron = neuronHashMap.get( NEURON_NAMES[key] ); //.............. get each neuron and values with it
            double sigmoid = neuron.getSigmoid();

            for( String nameKey : neuron.getWeightForNeuronNamed().keySet() ){ //... loop through each neuron with a weight associated
                delta = deltaHashMap.get( nameKey );
                weight += neuron.getWeightForNeuronNamed( nameKey );
                sum += delta * weight; //........................................... sum up all delta * weights
            }

            value = sigmoid * ( 1 - sigmoid ) * sum; //............................. calc with sigmoid values
            deltaHashMap.put( neuron.getName(), value ); //......................... put it into the hash
        }
    }

    // Helper method to compute the delta value for the output
    private static double computeDeltaForOutput( Neuron output, double error ){
        double sigmoid = output.getSigmoid();
        return sigmoid * ( 1 - sigmoid ) * error;
    }

    // Helper method to computer and update all the weights in the network!
    private static void computeAndUpdateWeightCorrection( HashMap< String, Neuron > neuronHashMap,
                                                            HashMap< String, Double > deltaHashMap ){
        for( Map.Entry<String, Neuron> neuron : neuronHashMap.entrySet() ){ //.......................... loop through each neuron
            for( String name : neuron.getValue().getWeightForNeuronNamed().keySet() ){ //............... loop through the neurons they've a weight associated with
                double yValue = neuron.getValue().getSigmoid(); //...................................... get all values needed for calculation
                double delta = deltaHashMap.get( name );
                double currentWeight = neuron.getValue().getWeightForNeuronNamed( name );
                double change = ALPHA * yValue * delta;

                neuron.getValue().setAConnectedWeightNamed( name, (currentWeight + change) ); //........ overwrite value in the hash with updated one
            }

            try { //.................................................................................... update the threshold value too
                double delta = deltaHashMap.get(neuron.getValue().getName()); //........................ get delta and threshold based on current neuron
                double currentThreshold =  neuron.getValue().getThreshold();
                double change = ALPHA * -1 * delta; //.................................................. calculate the change
                neuron.getValue().setThreshold( currentThreshold + change ); //......................... update value
            }
            catch (NullPointerException e){ continue; } //.............................................. here for the inputs with no threshold
        }
    }

    private static void printOutput( int count, HashMap< String, Neuron > neuronHashMap ){
        for( Map.Entry<String, Neuron> entry : neuronHashMap.entrySet() ){ //.......................... loop through each neuron and print out teh name and weights
            System.out.print( "Weights at " + count + " ");
            System.out.println( entry.getValue().getWeightForNeuronNamed() );
        }
        System.out.println( "\n________________\n" );
    }
}
