/**
 * Created by Barret J. Nobel on 4/26/2016.
 */

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class Neuron implements Cloneable {
    private double sigmoid;
    private double threshold;
    private ArrayList< Neuron > forwardNodes;
    private ArrayList< Neuron > backwardNodes;
    private HashMap< String, Double > weightDirection = new HashMap<>();
    private String name;

    private static final String FORWARD = "forward";
    private static final String BACKWARD = "backward";

//    public Neuron(){
//        this.setSigmoid( 0.0 );
//        this.setThreshold( 0.0 );
//        forwardNodes = new ArrayList<>();
//        backwardNodes = new ArrayList<>();
//        this.name = "";
//    }

    public Neuron( String name, double threshold ){
        this.setSigmoid( 0.0 );
        this.setThreshold( threshold );
        forwardNodes = new ArrayList<>();
        backwardNodes = new ArrayList<>();
        this.name = name;
    }

    public Neuron( Neuron copyMe ){
        this.setSigmoid( copyMe.getSigmoid() );
        this.setThreshold( copyMe.getThreshold() );
        this.arrayListHelper( copyMe.getConnectedNodesDirection(FORWARD), forwardNodes);
        this.arrayListHelper( copyMe.getConnectedNodesDirection(BACKWARD), backwardNodes);
    }

    private void arrayListHelper( ArrayList<Neuron> copyFrom, ArrayList<Neuron> copyTo ){
        for( Neuron addMe : copyFrom ){ copyTo.add(addMe.clone()); }
    }

    public void setSigmoid(double sigmoid) {
        this.sigmoid = sigmoid;
    }

    public void setThreshold(double threshold) {
        this.threshold = threshold;
    }

//    private HashMap< String, Neuron[] > setupConnectedNodes(){
//        HashMap< String, Neuron[] > returnMap = new HashMap<>();
//        returnMap.put( FORWARD, new Neuron[2] );
//        returnMap.put( BACKWARD, new Neuron[2] );
//
//        return returnMap;
//    }
//
//    private HashMap< String, Neuron[] > setupConnectedNodes( Map< String, Neuron[] > copyThisMap ){
//        HashMap< String, Neuron[] > returnMap = new HashMap<>();
//        returnMap.put( FORWARD, copyThisMap.get(FORWARD).clone() );
//        returnMap.put( BACKWARD, copyThisMap.get(BACKWARD).clone() );
//
//        return returnMap;
//    }

    public void setAConnectedWeightDirection( String direction, double value ){ weightDirection.put( direction, value ); }

    public void setAConnectedNodeDirection( String direction, Neuron node ){
        if( direction.toUpperCase().equals(FORWARD) ){ forwardNodes.add(node); }
        else{ backwardNodes.add(node); }
    }

    public double getSigmoid() {
        return sigmoid;
    }

    public double getThreshold() {
        return threshold;
    }

    public ArrayList< Neuron > getConnectedNodesDirection( String direction ) {
        return ( direction.toUpperCase().equals(FORWARD) ) ? forwardNodes : backwardNodes;
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

    public Neuron clone(){
        try{
            return (Neuron)super.clone();
        }
        catch( CloneNotSupportedException e ){
            System.out.println( "Cloning not supported" );
            return this;
        }
    }

    public String toString(){
        return String.format( "Neuron %s", name );
    }
}
