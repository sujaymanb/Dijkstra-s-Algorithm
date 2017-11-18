import java.util.Scanner;
import java.util.Arrays;
import java.util.NoSuchElementException;

// TODO: implement decreasekey
// TODO: make it non generic (vertex)

public class MinHeap
{
	private static final int d = 2;
	private int heapSize;
	private Vertex[] heap;

	public MinHeap(int cap) {
		heapSize = 0;
		heap = new Vertex[cap + 1];
	}

	public boolean isEmpty() {
		return heapSize == 0;
	}

	public boolean isFull() {
		return heapSize == heap.length;
	}

	private int parent(int i) {
		return(i - 1)/d;
	}

	private int kthChild(int i, int k) {
		return d * i + k;
	}

	public void insert(Vertex x) {
		if (isFull())
			throw new NoSuchElementException("Heap Full");

		heap[heapSize++] = x;
		heapifyUp(heapSize - 1);
	}

	public Vertex findMin() {
		if (isEmpty())
			throw new NoSuchElementException("Heap Empty");

		return heap[0];
	}

	public Vertex get(int i) {
		if (isEmpty())
			throw new NoSuchElementException("Heap Empty");

		return heap[i];
	}

	public Vertex deleteMin() {
		return delete(0);
	}

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

	public void decreaseKey(int i, int newval) {
		heap[i].cost = newval;
		while(i != 0 && heap[parent(i)].compareTo(heap[i]) > 0) {
			Vertex tmp = heap[i];
			heap[i] = heap[parent(i)];
			heap[parent(i)] = tmp;
			i = parent(i);
		}
	}

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

	public int findKeyByName(int name) {
		for (int i = 0; i < heapSize; i++) {
			if (heap[i].name == name) {
				return i;
			}	
		}

		return -1;
	}


}