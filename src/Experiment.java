import java.util.*;
import java.io.*;

public class Experiment {
	static PrintStream sparseMatrixResult;
	static PrintStream sparseListResult;
	static PrintStream sparseArrayResult;
	static PrintStream denseMatrixResult;
	static PrintStream denseListResult;
	static PrintStream denseArrayResult;
	
	public static void main(String[] args) throws FileNotFoundException{
		sparseMatrixResult = new PrintStream(new File("sparseMatrixResult.txt"));
		sparseListResult = new PrintStream(new File("sparseListResult.txt"));
		sparseArrayResult = new PrintStream(new File("sparseArrayResult.txt"));
		denseMatrixResult = new PrintStream(new File("denseMatrixResult.txt"));
		denseListResult = new PrintStream(new File("denseListResult.txt"));
		denseArrayResult = new PrintStream(new File("denseArrayResult.txt")); 
		for(int i=0;i<=30;i++) {
			String[] input = new String[2];
			
			input[0] = "madeinputSparse"+(1000+100*i)+".txt";
			input[1] = Integer.toString(0);	//sparse mode indicator
			temp(input);
			input[0] = "madeinputDense"+(1000+100*i)+".txt";
			input[1] = Integer.toString(1);	//dense mode indicator
			temp(input);
			System.out.println("time measured when i is "+i);
		}
	};
	
	public static void temp(String[] args) throws FileNotFoundException{	//Read the files and get ready to form the 3 graph expressions.
		Scanner scan = new Scanner(new File(args[0]));
		int V = scan.nextInt();
		Graph graph = new Graph(V);
		int neighborsNum, neighbor;
		int whichVertex = 1;
		while(scan.hasNextInt()) {
			neighborsNum = scan.nextInt();
			graph.insertArrayNeighborSize(whichVertex, neighborsNum);	//This is for forming adjacency array.
			for(int i=0;i<neighborsNum;i++) {
				neighbor = scan.nextInt();
				graph.setNeighborConnection(whichVertex, neighbor);
			}
			whichVertex++;
		}
		
		System.out.println("Matrix output");
		if(args[1]=="0") {
			sparseMatrixResult.println("Matrix output");
		}
		else {
			denseMatrixResult.println("Matrix output");
		}
		//Strongly Connected Components with matrix
		
		long startTime = System.nanoTime();
		
		graph.DFSM();
		graph.transposeM();
		LinkedList<LinkedList<Integer>> strongly = graph.DFSGR(1);
		long endTime = System.nanoTime();
		long elapsedTime = endTime - startTime;
		
		if(args[1].equals("0")) {
			sparseMatrixResult.println(elapsedTime);
		}
		else {
			denseMatrixResult.println(elapsedTime);
		}
		
		startTime = System.nanoTime();
		graph.DFSL();
		graph.reverseL();
		strongly = graph.DFSGR(2);	
		endTime = System.nanoTime();
		elapsedTime = endTime-startTime;
		if(args[1].equals("0")) {
			sparseListResult.println(elapsedTime);
		}
		else {
			denseListResult.println(elapsedTime);
		}
		startTime = System.nanoTime();
		graph.DFSA();
		graph.reverseA();
		strongly = graph.DFSGR(3);
		endTime = System.nanoTime();
		elapsedTime = endTime-startTime;
		if(args[1].equals("0")) {
			sparseArrayResult.println(elapsedTime);
		}
		else {
			denseArrayResult.println(elapsedTime);
		}
	}
}

