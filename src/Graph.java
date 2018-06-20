import java.util.*;

public class Graph {
	private int[] visited;
	private int[][] matrix;
	private LinkedList<Integer>[] list;
	private AdjArray[] array;
	public Stack<Integer> endTime;
	Stack<Integer> stack;
	private int Vnum;
	@SuppressWarnings("unchecked")
	public Graph(int Vnum) {	//constructor
		this.Vnum = Vnum;
		visited = new int[Vnum];
		Arrays.fill(visited, 0);
		
		matrix = new int[Vnum][Vnum];
		for(int i=0;i<Vnum;i++) {
			Arrays.fill(matrix[i], 0);
		}
		
		list = new LinkedList[Vnum];
		array = new AdjArray[Vnum];
		
		for(int i=0;i<Vnum;i++) {
			list[i] = new LinkedList<Integer>();
			array[i] = new AdjArray();
		}
	}
	
	public void insertArrayNeighborSize(int whichVertex, int neighborsNum) {	//for adjacency array
		array[whichVertex-1].setNeighborsSize(neighborsNum);
	}
	
	public void setNeighborConnection(int whichVertex, int whichNeighbor) {		//for matrix and list
		matrix[whichVertex-1][whichNeighbor-1] = 1;
		list[whichVertex-1].add(whichNeighbor);
		array[whichVertex-1].insertNeighbor(whichNeighbor);
	}
	
	public void DFSM() {
		Arrays.fill(visited, 0);
		//Just gonna start the search from node 1.
		endTime = new Stack<Integer>();
		for(int i=0;i<Vnum;i++) {
			if(visited[i]==0) {
				aDFSM(i);
			}
		}
		
	}
	
	private void aDFSM(int i) {	// i is the node number you're at right now.
		visited[i] = 1;
		for(int j=0;j<Vnum;j++) {
			if(matrix[i][j]==1 && visited[j]==0) {
				aDFSM(j);
			}
		}
		endTime.push(i);
	}
	
	private void aDFSMGR(int i) {	// i is the node number you're at right now.
		visited[i] = 1;
		for(int j=0;j<Vnum;j++) {
			if(matrix[i][j]==1 && visited[j]==0) {
				aDFSMGR(j);
			}
		}
		stack.push(i);
	}
	
	public void DFSL() {
		Arrays.fill(visited, 0);
		endTime = new Stack<Integer>();
		for(int i=0;i<Vnum;i++) {
			if(visited[i]==0) {
				aDFSL(i);
			}
		}
	}
	
	private void aDFSL(int i) {
		visited[i] = 1;
		Iterator<Integer> j = list[i].iterator();
		int n;
		while(j.hasNext()) {
			n = j.next();
			if(visited[n-1]==0) {
				aDFSL(n-1);
			}
		}
		endTime.push(i);
	}
	
	private void aDFSLGR(int i) {
		visited[i] = 1;
		Iterator<Integer> j = list[i].iterator();
		int n;
		while(j.hasNext()) {
			n = j.next();
			if(visited[n-1]==0) {
				aDFSLGR(n-1);
			}
		}
		stack.push(i);
	}
	
	public void DFSA() {
		Arrays.fill(visited, 0);
		//Just gonna start the search from node 1.
		endTime = new Stack<Integer>();
		for(int i=0;i<Vnum;i++) {
			if(visited[i]==0) {
				aDFSA(i);
			}
		}
	}

	private void aDFSA(int i) {
		visited[i] = 1;
		int n;
		for(int j=0;j<array[i].getNeighborsSize();j++) {
			n = array[i].getNeighbor(j);
			if(visited[n-1]==0) {
				aDFSA(n-1);
			}
		}
		endTime.push(i);
	}
	
	private void aDFSAGR(int i) {
		visited[i] = 1;
		int n;
		for(int j=0;j<array[i].getNeighborsSize();j++) {
			n = array[i].getNeighbor(j);
			if(visited[n-1]==0) {
				aDFSAGR(n-1);
			}
		}
		stack.push(i);
	}
	
	public LinkedList<LinkedList<Integer>> DFSGR(int type) { //i should be the one with the largest f[v];
		Arrays.fill(visited, 0);
		//Just gonna start the search from node 1.
		
		stack = new Stack<Integer>();
		
		LinkedList<LinkedList<Integer>> strongly = new LinkedList<LinkedList<Integer>>();
		
		LinkedList<Integer> temp;
		while(!endTime.isEmpty()) {	//Still has to be checked Vnum times
			int mostRecent = endTime.pop();
			if(visited[mostRecent]==0) {
				if(type==1)
					aDFSMGR(mostRecent);
				else if(type==2)
					aDFSLGR(mostRecent);
				else
					aDFSAGR(mostRecent);
			}
			temp = new LinkedList<Integer>();
			while(!stack.isEmpty()) {
				temp.add(stack.pop());
			}
			if(!temp.isEmpty()) {
				strongly.add(temp);
			}
		}
		return strongly;
	}
	
	public void transposeM() {
		int temp=0;
		for(int i=0;i<Vnum;i++) {
			for(int j=i+1;j<Vnum;j++) {
				temp = matrix[i][j];
				matrix[i][j] = matrix[j][i];
				matrix[j][i] = temp;
			}
		}
	}
	
	public void reverseL() {
		Iterator<Integer> j;
		@SuppressWarnings("unchecked")
		LinkedList<Integer>[] reversedList = new LinkedList[Vnum];
		for(int i=0;i<Vnum;i++) {
			reversedList[i] = new LinkedList<Integer>();
		}
		
		for(int i=0;i<Vnum;i++) {
			j = list[i].iterator();
			while(j.hasNext()) {
				int temp = j.next();
				reversedList[temp-1].add(i+1);
			}
		}
		list = reversedList;
	}
	
	public void reverseA() {
		AdjArray[] reversedArray = new AdjArray[Vnum];
		int[] countNeighbor = new int[Vnum];
		Arrays.fill(countNeighbor, 0);
		for(int i=0;i<Vnum;i++) {
			for(int j=0;j<array[i].getNeighborsSize();j++) {
				countNeighbor[array[i].getNeighbor(j)-1]++;
			}
		}
		for(int i=0;i<Vnum;i++) {
			reversedArray[i] = new AdjArray();
			reversedArray[i].setNeighborsSize(countNeighbor[i]);
		}
		for(int i=0;i<Vnum;i++) {
			for(int j=0;j<array[i].getNeighborsSize();j++) {
				reversedArray[array[i].getNeighbor(j)-1].insertNeighbor(i+1);;
			}
		}
		array = reversedArray;
	}
}
