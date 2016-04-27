/**
 * Created by Barret J. Nobel on 4/26/2016.
 */

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class Neuron {
    private double sigmoid;
    private double threshold;
    private HashMap< String, Neuron[] > connectedNodesDirection;
    private HashMap< String, Double > weightDirection = new HashMap<>();
    private String name;

    private static final String FORWARD = "forward";
    private static final String BACKWARD = "backward";

    public Neuron(){
        this.setSigmoid( 0.0 );
        this.setThreshold( 0.0 );
        this.name = "";
        this.connectedNodesDirection = this.setupConnectedNodes();
    }

    public Neuron( String name, double threshold ){
        this.setSigmoid( 0.0 );
        this.setThreshold( threshold );
        this.name = name;
        this.connectedNodesDirection = this.setupConnectedNodes();
    }

    public Neuron( Neuron copyMe ){
        this.setSigmoid( copyMe.getSigmoid() );
        this.setThreshold( copyMe.getThreshold() );
        this.connectedNodesDirection = this.setupConnectedNodes( copyMe.getConnectedNodesDirection() );
    }

    public void setSigmoid(double sigmoid) {
        this.sigmoid = sigmoid;
    }

    public void setThreshold(double threshold) {
        this.threshold = threshold;
    }

    private HashMap< String, Neuron[] > setupConnectedNodes(){
        HashMap< String, Neuron[] > returnMap = new HashMap<>();
        returnMap.put( FORWARD, new Neuron[2] );
        returnMap.put( BACKWARD, new Neuron[2] );

//        System.out.println( Arrays.toString(test));

//        System.out.println( Arrays.toString( returnMap.get(FORWARD)));

        return returnMap;
    }

    private HashMap< String, Neuron[] > setupConnectedNodes( Map< String, Neuron[] > copyThisMap ){
        HashMap< String, Neuron[] > returnMap = new HashMap<>();
        returnMap.put( FORWARD, copyThisMap.get(FORWARD).clone() );
        returnMap.put( BACKWARD, copyThisMap.get(BACKWARD).clone() );

//        System.out.println( Arrays.toString(copyThisMap.get(FORWARD)) );
        return returnMap;
    }

    public void setAWeightDirection( String direction, double value ){ weightDirection.put( direction, value ); }

    public void setAConnectedNodeDirection( String direction, Neuron node ){
        Neuron[] temp = connectedNodesDirection.get( direction );

        System.out.println( Arrays.toString(temp) + "PICK UP HERE ERRORRRRRRRRR FIX THE NULL");
        connectedNodesDirection.get( direction )[ (temp[0] == null) ? 0 : 1 ] = new Neuron( node );
    }

    public double getSigmoid() {
        return sigmoid;
    }

    public double getThreshold() {
        return threshold;
    }

    public Neuron[] getConnectedNodesDirection( String direction ) {
        return connectedNodesDirection.get( direction );
    }

    public Map< String, Neuron[] > getConnectedNodesDirection() {
        return connectedNodesDirection;
    }

    public Map<String, Double> getWeightDirection() {
        return weightDirection;
    }

    public double getWeightDirection( String direction ) {
        return weightDirection.get( direction );
    }

    public String getName() {
        return name;
    }

    public String toString(){
        return String.format( "Neuron %s", name );
    }
}
