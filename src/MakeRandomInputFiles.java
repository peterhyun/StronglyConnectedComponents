import java.io.*;
import java.util.*;

public class MakeRandomInputFiles {
	public static void main(String[] args) throws FileNotFoundException {
		for (int i = 0; i <= 30; i++) { // GONNA test with 100 different files varying from 3000~8000
			makefileSparse(i);
			//makefileDense(i);
			System.out.println("made 1 sparse file when i is " + i);
		}
	}

	// change this if you want to.
	public static int VnumOrigin = 1000;
	public static int addUnit = 100;

	public static void makefileSparse(int size) throws FileNotFoundException {
		Random random = new Random();

		// Fix this for modification!
		int Vnum = VnumOrigin + addUnit * size;

		PrintStream out = new PrintStream(new File("madeinputSparse" + Vnum + ".txt"));
		// print the first line
		out.println(Vnum + " ");
		//System.out.println("Vnum is "+Vnum);
		for (int i = 0; i < Vnum; i++) { // For each line!!
			int numOfNeighbor = Vnum;
			numOfNeighbor = random.nextInt((Vnum/4) + 1);	//To make it sparse
			out.print(numOfNeighbor + " ");
			LinkedList<Integer> list = new LinkedList<Integer>();
			for (int k = 1; k <= Vnum; k++) {
				list.add(k);
			}
			int timesPrinted = 0;
			while (timesPrinted < numOfNeighbor) {
				int index = random.nextInt(list.size());
				out.print(list.remove(index) + " ");
				timesPrinted++;
			}
			out.println();
		}
	}

	public static void makefileDense(int size) throws FileNotFoundException {

		Random random = new Random();

		// Fix this for modification!
		int Vnum = VnumOrigin + addUnit * size;

		PrintStream out = new PrintStream(new File("madeinputDense" + Vnum + ".txt"));
		// print the first line
		out.println(Vnum + " ");
		for (int i = 0; i < Vnum; i++) { // For each line!!
			int numOfNeighbor = 0;
			while (!(numOfNeighbor > (Vnum / 2))) {
				numOfNeighbor = random.nextInt(Vnum + 1); // number of neighbors are available from 0 to Vnum
			}
			out.print(numOfNeighbor + " ");
			LinkedList<Integer> list = new LinkedList<Integer>();
			for (int k = 1; k <= Vnum; k++) {
				list.add(k);
			}
			int timesPrinted = 0;
			while (timesPrinted < numOfNeighbor) {
				int index = random.nextInt(list.size());
				out.print(list.remove(index) + " ");
				timesPrinted++;
			}
			out.println();
		}
	}
}