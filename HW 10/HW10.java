import java.util.Iterator;
import java.util.Set;


import java.util.ArrayList;
import java.util.Collection;
/**
 * 
 * Name: Michael Lee
 * SBUID: 112424954
 * Dont' import anything other than what's allowed.
 */
public class HW10 implements Connectable<Integer> {
	Vertex[] vertexSet = new Vertex[10000];
	int vsSize = 0;
	
	public HW10() {
	}
	
	/*
	 * If you successfully completed HW9, you can just copy your legacy code into the following!
	 * How easy is that!
	 */
	public Set<Integer> nodeSet(){
		Set<Integer> s = new NodeSet<Integer>();
		for(int i = 0; i < vsSize; i++) {
			s.add(vertexSet[i].data);
		}
		return s;
		
	}
	public Set<Integer> getNeighbors(Integer node){
		for(int i = 0; i < vsSize; i++) {
			if(vertexSet[i].data.equals(node)) {
				Set<Integer> s = new NodeSet<Integer>();
				for(int j = 0; j < vertexSet[i].neighborIndex; j++) {
					s.add(vertexSet[i].neighbors[j].data);
				}
				return s;
			}
		}
		return null;
	} // return the set of neighbors connected to 'node'
	
	public Iterator<Integer> breadthFirstIterator() {
		return null;// return the iterator on nodes in a breadth-first manner
	}
	
	public Iterator<Integer> depthFirstIterator() {
		return null;// same as above in a depth-first manner
	}
	
	public Connectable<Integer> getMST() {
		return null;
	}
	
	public void addNode(Integer node) {
		for(int i = 0; i < vsSize; i ++) {
			if(vertexSet[i].data.equals(node)) {
				return;
			}
			
		}
		vertexSet[vsSize++] = new Vertex(node);
	}// add a new vertex into the current graph
	public boolean addEdge(Integer source, Integer target, double w) {
		if(!contains(source)) {
			addNode(source);
		}
		if(!contains(target)) {
			addNode(target);
		}
		Vertex v1 = null, v2 = null;
		for(int i = 0; i < vsSize; i++) {
			if(vertexSet[i].data.equals(source)) {
				v1 = vertexSet[i];
			}else if(vertexSet[i].data.equals(target)) {
				v2 = vertexSet[i];
			}
		}
		for(int i = 0; i < v1.neighborIndex; i++) {
			if(v1.neighbors[i].data.equals(v2.data)) {
				return false;
			}
		}
		v1.neighbors[v1.neighborIndex] = v2;
		v2.neighbors[v2.neighborIndex] = v1;
		v1.weights[v1.neighborIndex++] = w;
		v2.weights[v2.neighborIndex++] = w;
		
		return true;
	} // add a new edge. also add non-existing nodes. return false if edge already exists. 
	
	public boolean contains(Integer n) {
		for(int i = 0; i < vsSize; i++) {
			if(vertexSet[i].data.equals(n)) {
				return true;
			}
		}
		return false;
	}
	
	public Vertex findVertex(Integer n) {
		for(int i = 0; i < vsSize; i++) {
			if(vertexSet[i].data.equals(n)) {
				return vertexSet[i];
			}

		}
		return null;
	}
	public boolean removeNode(Integer node){
		Vertex v1 = null;
		for(int i = 0; i < vsSize; i++) {
			if(vertexSet[i].data.equals(node)) {
				v1 = vertexSet[i];
				for(int j = i; j < vsSize; j++) {
					vertexSet[j] = vertexSet[j + 1];
				}
				vsSize--;
				for(int j = 0; j < v1.neighborIndex; j++) {
					Vertex neighbor = v1.neighbors[j];
					for(int k = 0; k < vsSize; k++) {
						if(vertexSet[k].equals(neighbor)) {
							for(int l = 0; l < neighbor.neighborIndex; l++) {
								if(neighbor.neighbors[l].equals(v1)) {
									for(int m = l; m < neighbor.neighborIndex; m++) {
										neighbor.neighbors[m] = neighbor.neighbors[m + 1];
										neighbor.weights[m] = neighbor.weights[m + 1];
									}
									neighbor.neighborIndex--;
									break;
								}
							}
							break;
						}
					}
				}
			}
		}
		
		
		
		
		
		
		return v1 == null ? false : true;
		
	
	} // remove node. return false if node doesn't exist.
	public boolean removeEdge(Integer source, Integer target){
		if(!contains(source) || !contains(target)) {
			return false;
		}
		Vertex v1 = findVertex(source);
		Vertex v2 = findVertex(target);
		int checker = v1.neighborIndex;
		for(int i = 0; i < v1.neighborIndex; i ++) {
			Vertex neighbor = v1.neighbors[i];
			if(neighbor.equals(v2)) {
				while(i < v1.neighborIndex) {
					v1.weights[i] = v1.weights[i+1];
					v1.neighbors[i] = v1.neighbors[i + 1];
					i++;
				}
				v1.neighborIndex--;
			}
			
			
		}
		if(checker == v1.neighborIndex) return false;
		int checker2 = v2.neighborIndex;
		for(int i = 0; i < v2.neighborIndex; i ++) {
			Vertex neighbor = v2.neighbors[i];
			if(neighbor.equals(v1)) {
				while(i < v2.neighborIndex) {
					v2.weights[i] = v2.weights[i+1];
					v2.neighbors[i] = v2.neighbors[i + 1];
					i++;
				}
			}
			v2.neighborIndex--;
			
			
		}
		if(checker2 == v2.neighborIndex) return false;
		
		
		
		return true;
		
		
		
	} // remove edge. return false if edge doesn't exist.
	
	public double getWeight(Integer source, Integer target){
		if(source == null || target == null) {
			return -1;
		}
		Vertex v1 = findVertex(source);
		Vertex v2 = findVertex(target);
		if(v1 == null || v2 == null) return -1;
		for(int i = 0; i < v1.neighborIndex; i++) {
			if(v2.equals(v1.neighbors[i])) {
				return v1.weights[i];
			}
		}
		return -1;
	} // return weight of edge (what if edge doesn't exist?)
	public boolean setWeight(Integer source, Integer target, double w){return true;} // set the weight. existing weights are overwritten. if edge doesn't exist, return false;
	public int numNodes(){return 1;} // return the total number of nodes
	public int numEdges(){return 1;} // return the total number of edges
	
	/*
	 * TODO: Single-source shortest path. Start at the given 'source'.
	 * 'source' is guaranteed to be in the node set when we test it.
	 * Return a list of arrays, where the first element of the ArrayList 
	 * contains an ordered sequence (array) representing the shortest path.
	 * See the code in main() to get an idea of what I mean. 
	 */
	
	
	
	/*
	 * This algorithm is a greedy algorithm. It finds the best path by using two reasonings:
	 * 1. The shortest path contains all the shortest subpaths
	 * 2. If the detour is faster, take it. The dijkstra function takes a source Integer node to start from
	 * and returns an arraylist with all the optimal path route to each node. The path routes in the arraylist
	 * is ordered based on the edge input order. I have a previous array and an distance array. The previous array
	 * will record each nodes previous node in their optimal path and the distance array will record all the distances
	 * from 0 to n. The distance is set to all infinity values except the direct paths (direct edges from the source) 
	 * and the node itself (set to 0). Then through a loop, it finds the minimal path and goes through each of 
	 * the detour options available to another node. If that detour is shorter, take that route and update the distance array.
	 * distance[v] = distance[u] + getWeight[u, v] < distance [v] ? distance[u] + getWeight[u, v] : distance[v];
	 * Since any undirect paths are set to infinity all variations of detours that are not direct paths will be updated. 
	 * Also, every time the detour is taken it updates the previous array.  The data structure to help with this 
	 * algorithm is using a array of Vertex where each vertex has an neighbor and a weight array. Therefore, looking up any 
	 * neighbor, weight, or vertex takes worst case O(n). The space complexity for neighbor, weight, and vertex is a O(c) (constant of size 100000).
	 * The overall time complexity of dijkstra is O(n * m * m) where n is the number of nodes in the set and m is the number of neighbors
	 * in a given node. If all nodes are connected, time complexity becomes O(n^3). (Although dijkstra should be O(n^2), due to the fact that 
	 * my neighbors were connected through an array (look up time of O(n)) and not adjacency matrix (look up time of O(1)), it became O( n * m * M)).
	 * Finally, the array list is returned after the paths is figured out using back tracing from the previous array. 
	 * 
	 * 
	 */
	
	public ArrayList<int[]> dijkstra(Integer source) {
		ArrayList<int[]> al = new ArrayList<>();
		int[] path;
		int[] previous = new int[vsSize];
		for(int i = 0; i < previous.length; i++) {
			previous[i] = -1;
		}

		double[] distance = new double[vsSize];
		Vertex v = findVertex(source);
		for(int i = 0; i < vsSize; i++) {
			distance[i] = Double.POSITIVE_INFINITY;
			Vertex v2 = vertexSet[i];
			for(int j = 0; j < v2.neighborIndex; j++) {
				if(v2.neighbors[j].equals(v)) {
					distance[i] = getWeight(v2.data, v.data);
					previous[i] = v.data;
					//System.out.println(previous[i]);
				}
			}
			if(v == v2) {
				distance[i] = 0;
				
			}
			
		}
		
		for(int j = 0; j < vsSize; j++) {
			double min = Double.POSITIVE_INFINITY;
			int index = -1;
			for(int i = 0; i < distance.length; i++) {
				if(!vertexSet[i].visited && distance[i] < min) {
					index = i;
					min = distance[i];

				}
			}
			if(index == -1) {
				
				
			}else {
				vertexSet[index].visited = true;

			}
			
			//System.out.println(min);
			//System.out.println(vertexSet[index].data);
			detour(distance, index, previous);
			
			
			
			
		}
		for(int i = 0; i < previous.length; i++) {
			vertexSet[i].visited = false;
			ArrayList<Integer> al2 = new ArrayList<>();
			int index = i;
			al2.add(vertexSet[i].data);
			while(previous[index] != -1) {
				al2.add(previous[index]);
				for(int j = 0; j < vsSize; j++) {
					if(previous[index] == ((vertexSet[j]).data)) {
						index = j;
						break;
					
					}
				}
			}
			
			//System.out.println();
			
			path = new int[al2.size()];
			int n = al2.size();
			while(n-- > 0) {
				path[al2.size() - n - 1] = al2.get(n);
			}
			al.add(path);
			
			
		}
	
		
		
		
		
		
		return al;
	}
	
	public void detour(double[] distance, int index, int[] previous) {
		int detourIndex = -1;
		
		for(int i = 0; i < vertexSet[index].neighborIndex; i++) {
			for(int j = 0; j < vsSize; j++) {
				if(vertexSet[j].equals(vertexSet[index].neighbors[i])) detourIndex = j;
			}
			if(detourIndex == -1) {
				
			}else if(getWeight(vertexSet[index].data, vertexSet[detourIndex].data) != -1 && getWeight(vertexSet[index].data, vertexSet[detourIndex].data) + distance[index] < distance[detourIndex]) {
				distance[detourIndex] = getWeight(vertexSet[index].data, vertexSet[detourIndex].data) + distance[index];
				previous[detourIndex] = vertexSet[index].data;
				
			}
		}
	
	}
	
	public static void main(String[] args) {
		HW10 hw = new HW10();
		/*
		hw.addEdge(0, 1, 4); // Weights are guaranteed to be non-negative
		hw.addEdge(0, 7, 8);
		hw.addEdge(1, 7, 11);
		hw.addEdge(1, 2, 8);
		hw.addEdge(2, 8, 2);
		hw.addEdge(2, 5, 4);
		hw.addEdge(2, 3, 7);
		hw.addEdge(7, 8, 7);
		hw.addEdge(7, 6, 1);
		hw.addEdge(6, 5, 2);
		hw.addEdge(8, 6, 6);
		hw.addEdge(5, 3, 14);
		hw.addEdge(4, 3, 9);
		hw.addEdge(5, 4, 10);
		*/ 
		hw.addEdge(0, 1, 1); // Weights are guaranteed to be non-negative
		hw.addEdge(0, 2, 2);
		hw.addEdge(2, 3, 3);
		hw.addEdge(4, 5, 4);
		hw.addEdge(2, 4, 5);
		hw.addEdge(1, 5, 1);
		hw.addEdge(3, 5, 1);
		


		

		
		
		
		int source = 0;
		ArrayList<int[]> paths = hw.dijkstra(source);
		for(int i = 0; i < paths.size(); i++) {
			int[] arr = paths.get(i);  
			System.out.print("Shortest path from " + source + " to " + i + ": ");
			for(int j = 0; j < arr.length; j++) 
				System.out.print(arr[j] + " ");
			System.out.println();
		}
		
		
		
		/* Sample answer (must include starting node):
		 * 0
		 * 0 1
		 * 0 2
		 * 0 1 5 3
		 * 0 1 5 4
		 * 0 1 5
		 */
	}
}
class NodeSet<E> implements Set<E>{
	private int capacity;
	int size;
	public E[] arr;


	public NodeSet() {
		// TODO Auto-generated constructor stub
		capacity = 10000;
		size = 0;
		arr = (E[]) new Object[capacity];
	}

	@Override
	public int size() {
		// TODO Auto-generated method stub
		return size;
	}

	@Override
	public boolean isEmpty() {
		// TODO Auto-generated method stub
		return size == 0;
	}

	@Override
	public boolean contains(Object o) {
		for(int i = 0; i < size; i ++) {
			if(arr[0].equals(o)) {
				return true;
			}
		}
		return false;
	}

	@Override
	public Iterator<E> iterator() {
		// TODO Auto-generated method stub
		return new NodeSetIt<E>(this);
	}

	@Override
	public Object[] toArray() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public <T> T[] toArray(T[] a) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean add(E e) {
		
		if(size >= capacity - 1) {
			capacity = 2 * capacity;
			E[] temp = (E[]) new Object[capacity];
			arr = temp;
		}
		
		arr[size++] = e;
		return true;
	}

	@Override
	public boolean remove(Object o) {
		for(int i = 0; i < size; i++) {
			if(arr[i].equals(o)) {
				while(i < size - 1) {
					arr[i] = arr[i + 1];
					i++;
				}
				size--;
				return true;

			}
		}
		return false;
	}

	@Override
	public boolean containsAll(Collection<?> c) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean addAll(Collection<? extends E> c) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean retainAll(Collection<?> c) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean removeAll(Collection<?> c) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public void clear() {
		// TODO Auto-generated method stub
		
	}
	
}
class NodeSetIt<E> implements Iterator<E>{
	private E[] arr;
	private int size;
	private int index;
	
	public NodeSetIt (NodeSet<E> nodeSet){
		arr = nodeSet.arr;
		//System.out.println(arr[0]);
		index = 0;
		size = nodeSet.size;
	}

	@Override
	public boolean hasNext() {
		return index < size;
	}

	@Override
	public E next() {
		// TODO Auto-generated method stub
		if(index >= arr.length) {
			return null;
		}
		return arr[index++];
	}
	
}

class Vertex{
	boolean visited;
	Integer data;
	Vertex[] neighbors;
	double[] weights;
	int neighborIndex;
	public Vertex(Integer data) {
		this.data = data;
		this.neighbors = new Vertex[10000];
		this.weights = new double[10000];
		this.visited = false;
		this.neighborIndex = 0;

	
	}


	public String toString() {
		return data.toString();
	}
	
}