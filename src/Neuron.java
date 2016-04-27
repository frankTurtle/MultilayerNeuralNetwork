/**
 * Created by Barret J. Nobel on 4/26/2016.
 */

import java.util.ArrayList;
import java.util.HashMap;

public class Neuron implements Cloneable {
    private double sigmoid; //................................ instance variable to hold sigmoid value
    private double threshold; //.............................. instance variable to hold the threshold value
    private ArrayList< Neuron > forwardNodes; //.............. list of all nodes connected forward
    private ArrayList< Neuron > backwardNodes; //............. list of all nodes connected backwards
    private HashMap< String, Double > weightDirection; //..... map to give the weight in forward / backward direction
    private String name; //................................... Neuron name

    private static final String FORWARD = "forward"; //....... final value for key in map
    private static final String BACKWARD = "backward"; //..... final value for key in map

    // Constructor with parameters
    // requires all new Neurons to have a name and threshold value
    public Neuron( String name, double threshold ){
        this.setSigmoid( 0.0 ); //................... initialize all instance variables
        this.setThreshold( threshold );
        forwardNodes = new ArrayList<>();
        backwardNodes = new ArrayList<>();
        weightDirection = new HashMap<>();
        this.name = name;
    }

    // Copy constructor
    // makes a copy of the Neuron passed in
    public Neuron( Neuron copyMe ){
        this.setSigmoid( copyMe.getSigmoid() );
        this.setThreshold( copyMe.getThreshold() );
        forwardNodes = new ArrayList<>();
        backwardNodes = new ArrayList<>();
        weightDirection = new HashMap<>();
        this.arrayListHelper( copyMe.getConnectedNodesDirection(FORWARD), forwardNodes);
        this.arrayListHelper( copyMe.getConnectedNodesDirection(BACKWARD), backwardNodes);
        this.hashMapHelper( copyMe.getWeightDirection(), weightDirection );
        this.name = copyMe.getName();
    }

    // Helper method to populate the list of connected Neurons
    private void arrayListHelper( ArrayList<Neuron> copyFrom, ArrayList<Neuron> copyTo ){
        for( Neuron addMe : copyFrom ){ copyTo.add(addMe.clone()); }
    }

    // Helper method to populate the list of weights forwards and backwards
    private void hashMapHelper( HashMap< String, Double > copyFrom, HashMap< String, Double > copyTo ){
        for( String key : copyFrom.keySet() ){ copyTo.put( key, copyFrom.get(key) ); }
    }

    //...................................... SETTERS
    public void setSigmoid(double sigmoid) {
        this.sigmoid = sigmoid;
    }

    public void setThreshold(double threshold) {
        this.threshold = threshold;
    }

    public void setAConnectedWeightDirection( String direction, double value ){ weightDirection.put(direction, value); }

    public void setAConnectedNodeDirection( String direction, Neuron node ){
        if( direction.toUpperCase().equals(FORWARD) ){ forwardNodes.add(node); }
        else{ backwardNodes.add(node); }
    }

    //...................................... GETTERS
    public double getSigmoid() {
        return sigmoid;
    }

    public double getThreshold() {
        return threshold;
    }

    public ArrayList< Neuron > getConnectedNodesDirection( String direction ) {
        return ( direction.toUpperCase().equals(FORWARD) ) ? forwardNodes : backwardNodes;
    }

    public HashMap<String, Double> getWeightDirection() {
        return weightDirection;
    }

    public double getWeightDirection( String direction ) {
        return weightDirection.get( direction.toUpperCase() );
    }

    public String getName() {
        return name;
    }

    // Overridden method used to implement cloning objects
    public Neuron clone(){
        try{ return (Neuron) super.clone(); }
        catch( CloneNotSupportedException e ){
            System.out.println( "Cloning not supported" );
            return this;
        }
    }

    // Overridden toString
    // prints out the neuron name
    public String toString(){
        return String.format( "Neuron %s", name );
    }
}
