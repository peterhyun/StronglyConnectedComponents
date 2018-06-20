# StronglyConnectedComponents
This program is for finding strongly connected components and comparing the execute time based on three different graph representation methods which are the adjacency matrix method, the adjacency list method, and the adjacency array method.
The graph input should be given as file, and the file format should be as follows. The first line contains the number of vertices. The (i+1)st line contains edges going out of vertex i (the first number of the (i+1)th line is the number of edges). For example, the input
3
2 2 3
0
1 1
would mean that the graph has 3 vertices, vertex 1 is connected to vertex 2 and 3, vertex 2 is connected is not connected to any vertex, and vertex 3 is connected to vertex 1.
Then the program converts this graph data to the 3 different graph representations(which is not included in execute time), and then computes the time to get the strongly connected components of the graph by the 3 different representations repectively.
The output of the program lists all strongly connected components in each line, in lexicographical order.

To run this program on the command line, the following inputs should be typed.
```
$javac Control.java
$java Control (filename)
```
