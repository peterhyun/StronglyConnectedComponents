# StronglyConnectedComponents
This program is for finding strongly connected components given a graph by three matrix representation methods which are the adjacency matrix method, the adjacency list method, and the adjacency array method.
The graph input should be given as file, and the file format should be as follows. The first line contains the number of vertices. The (i+1)st line contains edges going out of vertex i (the first number of the (i+1)th line is the number of edges). For example, the input
3
2 2 3
0
1 1
would mean that the graph has 3 vertices, vertex 1 is connected to vertex 2 and 3, vertex 2 is connected is not connected to any vertex, and vertex 3 is connected to vertex 1.

To run this program on the command line, the following inputs should be typed.
```
javac Control.java
java Control (filename)
```
