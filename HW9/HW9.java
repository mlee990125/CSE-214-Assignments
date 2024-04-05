import java.util.Collection;
import java.util.Iterator;
import java.util.Set;
/**
 * 
 * Name:Michael Lee
 * SBUID: 11242454
 *
 */
public class HW9<E> implements Connectable<E>{
	Set<E> nodeSet;
	Object[] vertexSet;
	int vsSize = 0;
	int edgeNum = 0;
	
	public HW9() {
		this.nodeSet = new NodeSet<E>();
		this.vertexSet = new Vertex[10000];
	}
	
	public static void main(String[] args) {
		HW9 hw = new HW9();
		Character c = 'A';
		hw.addNode(c);
		Character c2 = 'B';
		hw.addNode(c2);
		Character c3 = 'C';
		hw.addNode(c3);
		Character c4 = 'D';
		Character c5 = 'E';
		Character c6 = 'F';
		Character c7 = 'G';
		hw.addNode(c4);
		hw.addNode(c5);
		hw.addNode(c6);
		hw.addNode(c7);
		hw.addEdge(c, c7, 1.0);
		hw.addEdge(c, c2, 2.0);
		hw.addEdge(c, c4, 3.0);
		hw.addEdge(c2, c3, 4.0);
		hw.addEdge(c3, c4, 5.0);
		hw.addEdge(c2, c5, 6.0);
		hw.addEdge(c5, c6, 7.0);
		
		System.out.print("Neighbors of 'A': ");
		for(Object e : hw.getNeighbors(c)) {
			System.out.print(e + " ");
		}
		System.out.println();
		System.out.print("Neighbors of 'B': ");
		for(Object e : hw.getNeighbors(c2)) {
			System.out.print(e + " ");
		}
		System.out.println();
		System.out.print("Neighbors of 'C': ");
		for(Object e : hw.getNeighbors(c3)) {
			System.out.print(e + " ");
		}
		System.out.println();
		System.out.print("Neighbors of 'D': ");
		for(Object e : hw.getNeighbors(c4)) {
			System.out.print(e + " ");
		}
		System.out.println();

		System.out.print("Neighbors of 'E': ");
		for(Object e : hw.getNeighbors(c5)) {
			System.out.print(e + " ");
		}
		System.out.println();

		System.out.print("Neighbors of 'F': ");
		for(Object e : hw.getNeighbors(c6)) {
			System.out.print(e + " ");
		}
		System.out.println();

		System.out.print("Neighbors of 'G': ");
		for(Object e : hw.getNeighbors(c7)) {
			System.out.print(e + " ");
		}
		System.out.println();
		

		Iterator dfsIt = hw.depthFirstIterator();
		while(dfsIt.hasNext()) {
			System.out.println(dfsIt.next());
		}
		System.out.println();
		Iterator bfsIt = hw.breadthFirstIterator();
		while(bfsIt.hasNext()) {
			System.out.println(bfsIt.next());
		}
		System.out.println();
		
		while(dfsIt.hasNext()) {
			System.out.println(dfsIt.next());
		}
		System.out.println();
		while(bfsIt.hasNext()) {
			System.out.println(bfsIt.next());
		}
		
	
	
		

		
		



		

		

	

		
	
	
		
		
		
	}
	
	public Vertex<E> findVertex(E node) {
		for(int i = 0; i < vsSize; i++) {
			if(node.equals(((Vertex)vertexSet[i]).data)) {
				return (Vertex)vertexSet[i];
			}
		}
		return null;
	}
	/*
	 * nodeSet() function returns a set of datas E. It is a custom set implemented by me
	 * called NodeSet has few of the Set functions fully implemented. The time complexity is the
	 * size of the object array created in the creation of set which is 10000. Insertion is of
	 * elements is O(1).
	 */
	@Override
	public Set<E> nodeSet() {
		// TODO Auto-generated method stub
		return nodeSet;
	}
	/*
	 * getNeighbors takes a node Data and returns a NodeSet of its neighbors.
	 * It uses my custom class Vertex, that has a neighbors arr and copies each data of 
	 * the neighbors arr of Vertex into a new neighbor set. Insertion is O(n) for the size of
	 * the neighbors and time complexity is creation of neighbor set which is 10000.
	 */
	@Override
	public Set<E> getNeighbors(E node) {
		// TODO Auto-generated method stub
		Vertex v = findVertex(node);
		Set<E> neighborSet = new NodeSet<E>();
		for(int i = 0; i < v.neighborIndex; i++) {
			neighborSet.add((E)v.neighbors[i].data);
		}
		return neighborSet;
	}
	/*
	 * breadthFirstIterator returns a custom class bfsIterator that implements 
	 * iterator interface. The main method of this class, .next() takes the time complexity 
	 * of O(n * m) where n = number of nodes in node set, and m = number of neighbor nodes
	 * each n has. m varies in size has each node doesn't have the same number of neighbors
	 * but in worst case (where every node is connected to each other) its O(n^2). .next() uses
	 * a queue data structure to work its algorithm. 
	 */
	@Override
	public Iterator<E> breadthFirstIterator() {
		// TODO Auto-generated method stub
		return new bfsIterator(this);
	}
	/*
	 * depthFirstIterator returns a custom class dfsIterator that implements 
	 * iterator interface. The main method of this class, .next() takes the time complexity 
	 * of O(n * m) where n = number of nodes in node set, and m = number of neighbor nodes
	 * each n has. m varies in size has each node doesn't have the same number of neighbors
	 * but in worst case (where every node is connected to each other) its O(n^2). .next() uses
	 * a stack data structure to work its algorithm. 
	 */
	

	@Override
	public Iterator<E> depthFirstIterator() {
		// TODO Auto-generated method stub
		return new dfsIterator(this);
	}

	@Override
	public Connectable<E> getMST() {
		// TODO Auto-generated method stub
		return null;
	}
	
	/*
	 * This method adds the data E to the nodeSet and also adds a 
	 * Vertex into vertexSet. to check for duplicates, it runs O(n). insertion
	 * of both nodeSet and vertexSet are O(1).
	 */

	@Override
	public void addNode(E node) {
		// TODO Auto-generated method stub
		for(int i = 0; i < vsSize; i++) {
			if((((Vertex)vertexSet[i]).data).equals(node)) {
				return;
			}
		}
		nodeSet.add(node);
		vertexSet[vsSize++] = new Vertex(node);
		
		
	}
	/*
	 * This method returns a boolean if an edge is available to be added.
	 * When adding the edge, the vertex values of the source and target are
	 * used to access the weights array of each vertex values. They are then
	 * updated. Checking for duplicates takes O(n) and updating values take O(1);
	 */

	@Override
	public boolean addEdge(E source, E target, double w) {
		// TODO Auto-generated method stub
		Vertex v1 = findVertex(source);
		Vertex v2 = findVertex(target);
		if(v1 == null || v2 == null || source == target) return false;
		for(int i = 0; i < v1.neighborIndex; i++) {
			if(v1.neighbors[i].equals(v2)) return false;
		}
		v1.neighbors[v1.neighborIndex] = v2;
		v2.neighbors[v2.neighborIndex] = v1;
		v1.weights[v1.neighborIndex++] = w;
		v2.weights[v2.neighborIndex++] = w;
		edgeNum++;
		return true;
		
	}
	
	/*
	 * This method removes the E data from both the nodeSet and the vertexSet.
	 * Then, all neighbors of the vertex removes the removed data from its list of 
	 * neighbors. Total number of neighbors is n and the number of the neighbor's neighbors
	 * is m then time complexity is O(n * m). If the neighbor isn't found, it returns false; 
	 */

	@Override
	public boolean removeNode(E node) {
		// TODO Auto-generated method stub
		Vertex v = findVertex(node);
		if(v == null) return false;
		for(int i = 0; i < v.neighborIndex; i ++) {
			Vertex neighbor = v.neighbors[i];
			for(int j = 0; j < neighbor.neighborIndex; j++) {
				if(neighbor.neighbors[j].equals(v)) {
					while(j < neighbor.neighborIndex) {
						neighbor.weights[j] = neighbor.weights[j+1];
						neighbor.neighbors[j] = neighbor.neighbors[j + 1];
						j++;
					}
					neighbor.neighborIndex--;
					
				}
			}
			
			
		}
		for(int i = 0; i < vsSize; i++) {
			if(((Vertex)vertexSet[i]).equals(v)) {
				while(i < vsSize) {
					vertexSet[i] = vertexSet[i + 1];
					i++;
				}
				vsSize--;
				
			}
		}
		nodeSet.remove(node);
		
	
		return true;
	}
	
	

	public boolean containsNode(E node) {
		Iterator it = nodeSet.iterator();
		while(it.hasNext()) {
			E element = (E) it.next();
			if(element.equals(node)) return true;
		}
		return false;
	}
	/*
	 * This method finds the edge between the source and the target and removes it.
	 * If the source and target don't exist, it returns false. After finding its source,
	 * it removes the target from the source's neighbor list. Same goes for the target and 
	 * removing the source from the target's neighbor list. Since it iterates through two different
	 * sets of neighbors, it is time complexity of O(n + m). 
	 */
	@Override
	public boolean removeEdge(E source, E target) {
	
		Vertex v1 = findVertex(source);
		Vertex v2 = findVertex(target);
		if(v1 == null || v2 == null) return false;
		int checker = v1.neighborIndex;
		for(int i = 0; i < v1.neighborIndex; i ++) {
			Vertex neighbor = v1.neighbors[i];
			if(neighbor.equals(v2)) {
				while(i < v1.neighborIndex) {
					v1.weights[i] = v2.weights[i+1];
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
		edgeNum--;
		
		return true;
	}
	
	/*
	 * This method goes through the sources neighbor list 
	 * until it finds the target. Since the neighbor list and the
	 * weight list index are in 1 to 1 sync when the neighbor is found,
	 * the weight on that index is updated to the correct weight. If the nodes are not found,
	 * then it returns -1. Time complexity is O(n) for n = the neighbor list of source.
	 */

	@Override
	public double getWeight(E source, E target) {
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
	}
	
	/*
	 * This method iterates through the source and target node
	 * until it finds each other on the neighbor list. Then, since the index
	 * of neighbor list is same as weight list, the weight is correctly
	 * updated. n = number of neighbors of source and m = number of neighbors of
	 * target, time complexity is O(n + m). 
	 */

	@Override
	public boolean setWeight(E source, E target, double w) {
		if(source == null || target == null) return false;
		Vertex v1 = findVertex(source);
		Vertex v2 = findVertex(target);
		if(v1 == null || v2 == null) return false;
		for(int i = 0; i < v1.neighborIndex; i++) {
			if(v2.equals(v1.neighbors[i])) {
				v1.weights[i] = w;
	
			}
		}
		for(int i = 0; i < v2.neighborIndex; i++) {
			if(v1.equals(v2.neighbors[i])) {
				v2.weights[i] = w;
				return true;
			}
		}
		
		return false;
	}
	
	/*
	 * this returns the nodeSet size and is O(1).
	 */

	@Override
	public int numNodes() {
		// TODO Auto-generated method stub
		return nodeSet.size();
	}
	
	/*
	 * this returns the num of edges and is O(1).
	 */
	@Override
	public int numEdges() {
		// TODO Auto-generated method stub
		return edgeNum;
	}
	
	class NodeSet<E> implements Set<E>{
		private int capacity;
		private int size;
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
	
	class Vertex<E>{
		private boolean visited;
		private E data;
		private Vertex<E>[] neighbors;
		private double[] weights;
		private int neighborIndex = 0;
		public Vertex(E data) {
			this.data = data;
			this.neighbors = new Vertex[10000];
			this.weights = new double[10000];
			visited = false;

		
		}
		public String toString() {
			return data.toString();
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
	
	class bfsIterator<E> implements Iterator<E>{
		private HW9 hw;
		private int c = 0;
		private int index = 0;
		Vertex<E>[] queue;
		Vertex currNode;
		
		public bfsIterator(HW9 hw) {
			this.hw = hw;
			queue = new Vertex[10000];
			currNode = (Vertex) hw.vertexSet[0];
			currNode.visited = true;
		}

		@Override
		public boolean hasNext() {
			// TODO Auto-generated method stub
			if(c == hw.vsSize) {
				for(int i = 0; i < hw.vsSize; i++) {
					((Vertex)hw.vertexSet[i]).visited = false;
				}
				c = 0;
				index = 0;
				currNode = (Vertex) hw.vertexSet[0];
				currNode.visited = true;
				return false;
			}
			if(((Vertex)hw.vertexSet[0]).visited == false) {
				((Vertex)hw.vertexSet[0]).visited = true;
			}
			return c < hw.vsSize;
		}

		@Override
		public E next() {
			E returnNode = (E) currNode.data;
			for(int i = 0; i < currNode.neighborIndex; i++) {
				if(!currNode.neighbors[i].visited) {
					currNode.neighbors[i].visited = true;
					queue[index++] = currNode.neighbors[i];
					
				}
				
			}
			currNode = queue[c];
			c++;
			return returnNode;
			
		}
		
	}
	
	class dfsIterator<E> implements Iterator<E>{
		int c = 0;
		private HW9 hw;
		Vertex currNode;
		int index = -1;
		Vertex[] stack = new Vertex[10000];
		
		public dfsIterator(HW9 hw) {
			this.hw = hw;
			currNode = (Vertex) hw.vertexSet[0];
			stack[++index] = currNode;
	
		}

		@Override
		public boolean hasNext() {
			if(c == hw.vsSize) {
				for(int i = 0; i < hw.vsSize; i++) {
					((Vertex)hw.vertexSet[i]).visited = false;
				}
				c = 0;
				index = -1;
				stack[++index] = currNode;
				return false;

			}
			
			return c < hw.vsSize;
			
		}

		@Override
		public E next() {
			Vertex v = stack[index--];
			v.visited = true;
			c++;
			E returnNode = (E) v.data;
			for(int i = 0; i < v.neighborIndex; i++) {
				if(!v.neighbors[i].visited) {
					v.neighbors[i].visited = true;
					stack[++index] = v.neighbors[i];
				}
			}
			return returnNode;
			
		
			
			
		}
		
	}
	
	
	
}
