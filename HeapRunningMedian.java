import java.io.*;
import java.util.*;
import java.text.*;
import java.math.*;
import java.util.regex.*;

public class HeapRunningMedian {

    static int compareSize(Heap left, Heap right) {
        if(left.getSize() < right.getSize()) return -1;
        else if(left.getSize() > right.getSize()) return 1;
        return 0;
    }
    static double getMedian(int elem, double med, MaxHeap left, MinHeap right) {
        double m = med;
        switch(compareSize(left,right)) {
            case 0: // Equal number of elements
                if(elem < med) {
                    left.add(elem);
                    m = left.getTop();
                }
                else {
                    right.add(elem);
                    m = right.getTop();
                }
                break;
            case 1: // More elements in left
                if(elem < med) {
                    right.add(left.extractTop());
                    left.add(elem);
                }
                else {
                    right.add(elem);
                }
                m = ((double)(left.getTop() + right.getTop())) / 2;
                break;
            case -1: // More elements in right
                if(elem > med) {
                    left.add(right.extractTop());
                    right.add(elem);
                }
                else {
                    left.add(elem);
                }
                m = ((double)(left.getTop() + right.getTop())) / 2;
                break;
        }
        return m;
    }
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int n = in.nextInt();
        int[] a = new int[n];
        double m = 0;
        MaxHeap left = new MaxHeap();
        MinHeap right = new MinHeap();
        for(int a_i=0; a_i < n; a_i++){
            a[a_i] = in.nextInt();
            m = getMedian(a[a_i],m,left,right);
            System.out.println(m);
        }
    }
}



abstract class Heap {
    int size;
    int capacity;
    public int[] heap;
    
    public Heap() {
        size = 0;
        capacity = 10;
        heap = new int[capacity];
    }
    
    public int getParentIndex(int index) { return (index-1)/2 ;}
    public int getLeftChildIndex(int index) { return 2*index + 1;}
    public int getRightChildIndex(int index) { return 2*index + 2;}
    public boolean hasLeftChild(int index) { return getLeftChildIndex(index) < size ;} 
    public boolean hasRightChild(int index) { return getRightChildIndex(index) < size ;} 
    public boolean hasParent(int index) { return getParentIndex(index) >= 0;}
    public int getLeftChild(int index) { return heap[getLeftChildIndex(index)];}
    public int getRightChild(int index) { return heap[getRightChildIndex(index)];}
    public int getParent(int index) { return heap[getParentIndex(index)];}
    public int getSize() { return size; }
    
    public void ensureCapacity() {
        if(size==capacity) {
            heap = Arrays.copyOf(heap, capacity*2);
            capacity = 2*capacity;
        }
        
    }
    public void add(int element) {
        ensureCapacity();
        heap[size] = element;
        size++;
        heapifyUp();
    }
    public int extractTop() {
        int e = getTop();
        heap[0] =  heap[size-1];
        size--;
        heapifyDown();
        return e;
    }
    
    
    public int getTop() {
        return heap[0];
    }
    
    abstract void heapifyUp();
    abstract void heapifyDown();
}

class MaxHeap extends Heap {
    public MaxHeap() {
        super();
    }
    void heapifyDown() {
        int index = 0;
        while(hasLeftChild(index)) {
            int largerChildIndex = getLeftChildIndex(index);
            if(hasRightChild(index)) {
                if(getRightChild(index) > getLeftChild(index)) {
                    largerChildIndex = getRightChildIndex(index);
                }
            } else {}
            if(heap[largerChildIndex] > heap[index]) {
                int temp = heap[index];
                heap[index] =  heap[largerChildIndex];
                heap[largerChildIndex] = temp;
                index = largerChildIndex;
            }
            else {
                break;
            }
        }
    }
    
    void heapifyUp() {
        int index = size-1;
        while(hasParent(index) && getParent(index) < heap[index]) {
            int temp = heap[index];
            heap[index] = getParent(index);
            heap[getParentIndex(index)] = temp;
            index = getParentIndex(index);
        }
    }
}

class MinHeap extends Heap {
    public MinHeap() {
        super();
    }
    void heapifyDown() {
        int index = 0;
        while(hasLeftChild(index)) {
            int smallerChildIndex = getLeftChildIndex(index);
            if(hasRightChild(index)) {
                if(getRightChild(index) < getLeftChild(index)) {
                    smallerChildIndex = getRightChildIndex(index);
                }
            } else {}
            if(heap[smallerChildIndex] < heap[index]) {
                int temp = heap[index];
                heap[index] =  heap[smallerChildIndex];
                heap[smallerChildIndex] = temp;
                index = smallerChildIndex;
            }
            else {
                break;
            }
        }
        
    }
    
    void heapifyUp() {
        int index = size-1;
        while(hasParent(index) && getParent(index) > heap[index]) {
            int temp = heap[index];
            heap[index] = getParent(index);
            heap[getParentIndex(index)] = temp;
            index = getParentIndex(index);
        }
    }
}
