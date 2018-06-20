
public class AdjArray {
	private int neighborsNum;
	private int[] neighbors;
	private int i; //index location to insert neighbor info.
	public AdjArray() {
		neighborsNum = 0;
		i=0;
	}
	
	public int getNeighborsSize() {
		return neighborsNum;
	}
	
	public int getNeighbor(int I) {
		return neighbors[I];
	}
	
	public void setNeighborsSize(int neighborsNum) {
		this.neighborsNum = neighborsNum;
		neighbors = new int[neighborsNum];
	}
	
	public void insertNeighbor(int neighbor) {
		neighbors[i] = neighbor;
		i++;
		if(i>neighborsNum) {
			System.out.println("Size exceed error!!!. Should not happen");	//Should not happen
		}
	}
}
