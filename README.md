# MultilayerNeuralNetwork
Consider the 4-layer back-propogation network. Suppose the network performs logical XOR operation. Neurons 1 and 2 in the input layer accept inputs x1 and x2 respectively and redistribute these inputs to neurons 3 and 4 in the hidden-layer1 without any processing. Neurons 5 and 6 in the hidden-layer2. Neurons 5 and 6 process the inputs from Neurons 3 and 4 and redistrubute to neuron 7 in the output latyer. Now train this ANN network using the larning algorithm, discussed in thie class, with various input epochs 'til the mean square error is less than 0.01. Once the network has solved the problem, test your network, test your network presenting all training sets and calculating the network output. Show your output at various stages.


# Output: Using Input 1 1 0
Iteration : Error Value

0: -0.6563638630332373

100000: -0.02351568214838211

200000: -0.016369533315113354

300000: -0.013276032048792553

400000: -0.011452133561758658

500000: -0.010215886344671854

521340: -0.009999992621741616

Actual Output: 0.009999992621741616
_________

Weights:

IDX1
{ID4=2.178582466733447, ID3=1.938778200208061}

IDX2
{ID4=3.089914576085478, ID3=2.7914674186317376}

ID4
{ID6=1.92438594452644, ID5=0.22032556044987534}

ID3
{ID6=1.3249391792029437, ID5=0.9758084208014188}

ID6
{ID8=1.6711051991358414, ID7=1.8466839245767044}

ID5
{ID8=-0.1666575966464748, ID7=1.1466561682685688}

ID8
{ID9=-2.0774888773073523}

ID7
{ID9=-2.003249626309154}

ID9
{}


Process finished with exit code 0
