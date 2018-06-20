import java.util.*;

import org.apache.commons.io.FileUtils;

import java.io.*;
import java.nio.file.Files;

class DeleteThisProgram {

	public static PrintStream print1;
	public static PrintStream print2;
	public static PrintStream print3;
	
	//Change the names whenever necessary
	public static String matrixoutput = "blogsoutMatrix.txt";
	public static String listoutput = "blogsoutList.txt";
	public static String arrayoutput = "blogsoutArray.txt";
	
	//Change this(1/2)!
	public static String answercompared = "openflights.out";
	
	public static void main(String[] args) throws IOException{	//Read the files and get ready to form the 3 graph expressions.
		print1 = new PrintStream(matrixoutput);
		print2 = new PrintStream(listoutput);
		print3 = new PrintStream(arrayoutput);
		//Change this(2/2)!
		Scanner scan = new Scanner(new File("openflights.in"));
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
		//System.out.println("!!!!!"+graph.array[0].neighbors[0]);
		//graph.showAllDataStructures();
		
		//System.out.println("Matrix output");
		//Strongly Connected Components with matrix
		graph.DFSM();
		//System.out.println(graph.stack.lastElement()); //This is the biggest 
		graph.transposeM();
		//graph.showAllDataStructures(); //right
		//System.out.println("Highest Fv is "+graph.getHighestFv());
		LinkedList<LinkedList<Integer>> strongly = graph.DFSGR(1);
		//System.out.println("strongly.size is "+strongly.size());	//shows how many strongly connected sets are there
		printListofListsAlphabetically(strongly,1);
		
		//System.out.println("\nList output");
		graph.DFSL();
		graph.reverseL();
		//graph.showAllDataStructures();	//Correct!
		strongly = graph.DFSGR(2);	
		printListofListsAlphabetically(strongly,2);
		
		//System.out.println("\nArray output");
		graph.DFSA();
		graph.reverseA();
		strongly = graph.DFSGR(3);
		printListofListsAlphabetically(strongly,3);
		File answerFile = new File(answercompared);
		Scanner Answer = new Scanner(answerFile);
		Scanner myMatrixAnswer = new Scanner(new File(matrixoutput));
		Scanner myListAnswer = new Scanner(new File(listoutput));
		Scanner myArrayAnswer = new Scanner(new File(arrayoutput));
		
		String readAnswer="";
		String readmyAnswer="";
		int a,b;
		while(myMatrixAnswer.hasNextInt() || Answer.hasNextInt()) {
			a = myMatrixAnswer.nextInt();
			b = Answer.nextInt();
			if(a!=b) {
				System.out.println(a+" != "+b);
				System.out.println("Wrong");
				return;
			}
		}
		System.out.println("Matrix implementation Correct!");
		
		Answer = new Scanner(answerFile);
		while(myListAnswer.hasNextInt() || Answer.hasNextInt()) {
			a = myListAnswer.nextInt();
			b = Answer.nextInt();
			if(a!=b) {
				System.out.println(a+" != "+b);
				System.out.println("Wrong");
				return;
			}
		}
		System.out.println("List implementation Correct!");
		
		Answer = new Scanner(answerFile);
		while(myArrayAnswer.hasNextInt() || Answer.hasNextInt()) {
			a = myArrayAnswer.nextInt();
			b = Answer.nextInt();
			if(a!=b) {
				System.out.println(a+" != "+b);
				System.out.println("Wrong");
				return;
			}
		}
		System.out.println("Array implementation Correct!");
		
		
/*
		if(FileUtils.contentEquals(myMatrixAnswer, Answer)) {
			System.out.println("Matrix implementation is right! Congrats");
		}
		else {
			System.out.println("Matrix implementation is wrong you loser");
		}
		if(FileUtils.contentEquals(myListAnswer, Answer)) {
			System.out.println("List implementaion is right! Congrats");
		}
		else {
			System.out.println("List implementation is wrong you loser");
		}
		if(FileUtils.contentEquals(myArrayAnswer, Answer)) {
			System.out.println("Array implementation is right! Congrats");
		}
		else {
			System.out.println("Array implementation is wrong you loser");
		}*/
	}
	
	public static void printListofListsAlphabetically(LinkedList<LinkedList<Integer>> strongly, int mode) {
		//I'm gonna first sort the individual arrays
		for(int i=0;i<strongly.size();i++) {
			Collections.sort(strongly.get(i));
		}
		int n = strongly.size();
		LinkedList<Integer> temp;
		//Use insertion sort. I think it's already gonna be kinda sorted so won't be a big problem.
		for(int i=1;i<n;i++) {
			int key = strongly.get(i).get(0);
			temp = strongly.get(i);
			int j = i-1;
			while(j>=0 && strongly.get(j).get(0) > key) {
				strongly.remove(j+1);
				strongly.add(j+1,strongly.get(j));
				j = j-1;
			}
			strongly.remove(j+1);
			strongly.add(j+1,temp);
		}
		
		//System.out.println("strongly.size is "+strongly.size());
		for(int i=0;i<strongly.size();i++) {
			int temp2 = strongly.get(i).size();
			for(int j=0;j<temp2;j++) {
			//	System.out.print((strongly.get(i).get(j)+1)+" ");
				if(mode==1)
					print1.print((strongly.get(i).get(j)+1)+" ");
				else if(mode==2)
					print2.print((strongly.get(i).get(j)+1)+" ");
				else
					print3.print((strongly.get(i).get(j)+1)+" ");
			}
			//System.out.println();
			if(mode==1)
				print1.println();
			else if(mode==2)
				print2.println();
			else
				print3.println();
		}
	}
}
