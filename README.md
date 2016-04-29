# MultilayerNeuralNetwork
Consider the 4-layer back-propogation network. Suppose the network performs logical XOR operation. Neurons 1 and 2 in the input layer accept inputs x1 and x2 respectively and redistribute these inputs to neurons 3 and 4 in the hidden-layer1 without any processing. Neurons 5 and 6 in the hidden-layer2. Neurons 5 and 6 process the inputs from Neurons 3 and 4 and redistrubute to neuron 7 in the output latyer. Now train this ANN network using the larning algorithm, discussed in thie class, with various input epochs 'til the mean square error is less than 0.01. Once the network has solved the problem, test your network, test your network presenting all training sets and calculating the network output. Show your output at various stages.


# Output: Input 1 1 0
Iteration : Error Value

0: 0.280793335286586

100000: 0.023325361046071302

200000: 0.016303996916480767

300000: 0.013240780849021139

400000: 0.011429400165159032

500000: 0.010199698937089496

519786: 0.009999998133466526

Actual Output: 0.9900000018665335
_________________________________

NeuronID : Weights at output

IDX1
{ID4=0.7789321419122459, ID3=0.8512828860691146}

IDX2
{ID4=4.425301206981084, ID3=5.028459705091663}

ID4
{ID6=2.990681480941819, ID5=4.792554262667083}

ID3
{ID6=1.6102302405916409, ID5=4.378354804549863}

ID6
{ID8=0.7255728077615196, ID7=0.21824501867757443}

ID5
{ID8=0.8244371149774972, ID7=1.9273808864725117}

ID8
{ID9=2.201873977807648}

ID7
{ID9=2.8066642876525716}

ID9
{}


Process finished with exit code 0
