import java.util.Scanner;
import java.util.Arrays;
import java.util.NoSuchElementException;


/* this is the heap implementation for vertices, 
it uses an array to implement a binary heap */
public class MinHeap
{
	private static final int d = 2;
	private int heapSize;
	private Vertex[] heap;

	// constructor
	public MinHeap(int cap) {
		heapSize = 0;
		heap = new Vertex[cap + 1];
	}

	// check if empty
	public boolean isEmpty() {
		return heapSize == 0;
	}

	// check if full
	public boolean isFull() {
		return heapSize == heap.length;
	}

	// get the key of parent of a heap item
	private int parent(int i) {
		return(i - 1)/d;
	}

	// get the kth child of the heap item
	private int kthChild(int i, int k) {
		return d * i + k;
	}

	// insert an item into the heap
	public void insert(Vertex x) {
		if (isFull())
			throw new NoSuchElementException("Heap Full");

		heap[heapSize++] = x;
		heapifyUp(heapSize - 1);
	}

	// return the minimum item (top of heap)
	public Vertex findMin() {
		if (isEmpty())
			throw new NoSuchElementException("Heap Empty");

		return heap[0];
	}

	// return the item with given key in heap
	public Vertex get(int i) {
		if (isEmpty())
			throw new NoSuchElementException("Heap Empty");

		return heap[i];
	}

	// return min and delete it
	public Vertex deleteMin() {
		return delete(0);
	}

	// delete an item of given key from the heap
	public Vertex delete(int i)
	{
		if (isEmpty())
			throw new NoSuchElementException("Heap Empty");
		Vertex item = heap[i];
		heap[i] = heap[heapSize - 1];
		heapSize--;
		heapifyDown(i);
		return item;
	}

	// reform the heap
	private void heapifyUp(int child) {
		Vertex tmp = heap[child];
		while(child > 0 && tmp.compareTo(heap[parent(child)]) < 0) {
			heap[child] = heap[parent(child)];
			child = parent(child);
		}

		heap[child] = tmp;
	}

	private void heapifyDown(int i) {
		int child;
		Vertex tmp = heap[i];
		while(kthChild(i, 1) < heapSize) {
			child = minChild(i);
			if(heap[child].compareTo(tmp) < 0)
				heap[i] = heap[child];
			else
				break;
			i = child;
		}
		heap[i] = tmp;
	}

	// update the value of an item with given key then remake heap
	public void decreaseKey(int i, int newval) {
		heap[i].cost = newval;
		while(i != 0 && heap[parent(i)].compareTo(heap[i]) > 0) {
			Vertex tmp = heap[i];
			heap[i] = heap[parent(i)];
			heap[parent(i)] = tmp;
			i = parent(i);
		}
	}

	// reutnr the min child key
	private int minChild(int i) {
		int best = kthChild(i, 1);
		int k = 2;
		int pos = kthChild(i, k);
		while((k <= d) && (pos < heapSize)) {
			if(heap[pos].compareTo(heap[best]) < 0 )
				best = pos;
			pos = kthChild(i, k++);
		}
		return best;
	}

	// get the key of a vertex given its name
	public int findKeyByName(int name) {
		for (int i = 0; i < heapSize; i++) {
			if (heap[i].name == name) {
				return i;
			}	
		}

		return -1;
	}

	// update the parent of the item
	public void vertexUpdateParent(int i, Vertex parent) {
		heap[i].parent = parent;	
	}
}
