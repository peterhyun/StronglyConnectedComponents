import java.util.*;

public class Control {
	public static void main(String[] args) { // Read the files and get ready to form the 3 graph expressions.
		Scanner scan = new Scanner(System.in);
		int V = scan.nextInt();
		Graph graph = new Graph(V);
		int neighborsNum, neighbor;
		int whichVertex = 1;
		for (int j=0;j<V;j++) {
			neighborsNum = scan.nextInt();
			graph.insertArrayNeighborSize(whichVertex, neighborsNum); // This is for forming adjacency array.
			for (int i = 0; i < neighborsNum; i++) {
				neighbor = scan.nextInt();
				graph.setNeighborConnection(whichVertex, neighbor);
			}
		whichVertex++;
		}

		// Strongly Connected Components with matrix
		System.out.println("Matrix method's SCC output");
		long startTime = System.nanoTime();
		graph.DFSM();
		graph.transposeM();
		LinkedList<LinkedList<Integer>> strongly = graph.DFSGR(1);
		long endTime = System.nanoTime();
		long elapsedTime = endTime - startTime;
		printListofListsAlphabetically(strongly);
		System.out.println("Matrix method time : " + elapsedTime);

		System.out.println("\nList method's SCC output");
		startTime = System.nanoTime();
		graph.DFSL();
		graph.reverseL();
		strongly = graph.DFSGR(2);
		endTime = System.nanoTime();
		elapsedTime = endTime - startTime;
		printListofListsAlphabetically(strongly);
		System.out.println("List method time : " + elapsedTime);

		System.out.println("\nArray method's SCC output");
		startTime = System.nanoTime();
		graph.DFSA();
		graph.reverseA();
		strongly = graph.DFSGR(3);
		endTime = System.nanoTime();
		elapsedTime = endTime - startTime;
		printListofListsAlphabetically(strongly);
		System.out.println("Array method time : " + elapsedTime);
	}

	public static void printListofListsAlphabetically(LinkedList<LinkedList<Integer>> strongly) {
		// I'm gonna first sort the individual arrays
		for (int i = 0; i < strongly.size(); i++) {
			Collections.sort(strongly.get(i));
		}
		int n = strongly.size();
		LinkedList<Integer> temp;

		// Use insertion sort. I thought it's already gonna be kinda sorted so won't be
		// a big problem.
		for (int i = 1; i < n; i++) {
			int key = strongly.get(i).get(0);
			temp = strongly.get(i);
			int j = i - 1;
			while (j >= 0 && strongly.get(j).get(0) > key) {
				strongly.remove(j + 1);
				strongly.add(j + 1, strongly.get(j));
				j = j - 1;
			}
			strongly.remove(j + 1);
			strongly.add(j + 1, temp);
		}

		for (int i = 0; i < strongly.size(); i++) {
			int temp2 = strongly.get(i).size();
			for (int j = 0; j < temp2; j++) {
				System.out.print((strongly.get(i).get(j) + 1) + " ");
			}
			System.out.println();
		}
	}
}