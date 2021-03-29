package com.company.dataStructure.queue;

public class LinearQueue {
    private int capacity;
    private int front;
    private int rear;
    private int[] array;

    public LinearQueue(int capacity) {
        this.capacity = capacity;
        this.front = 0;
        this.rear = 0;
        this.array = new int[capacity];
    }

    public boolean put(int value) {
        if (rear == capacity) {
//            throw new RuntimeException("overflow 발생");
            System.out.println("overflow 발생");
            return false;
        }
        array[rear++] = value;
        return true;
    }

    public int get() {
        if (front == rear) {
//            throw new RuntimeException("underflow 발생");
            System.out.println("underflow 발생");
            return -1;
        }
        return array[front++];
    }

    public int peek() {
        if (front == rear) {
//            throw new RuntimeException("underflow 발생");
            System.out.println("underflow 발생");
            return -1;
        }
        return array[front];
    }

    public String toString() {
        String ret = "";
        for (int i = front; i < rear; i++) {
            ret += array[i] + " ";
        }
        return ret;
    }
}

class LinearQueueTest {
    public static void main(String[] args) {
        LinearQueue queue = new LinearQueue(5);
        System.out.println(queue);

        queue.put(1);
        queue.put(2);
        queue.put(3);
        System.out.println(queue);

        System.out.println((queue.get()));
        System.out.println((queue.get()));
        System.out.println((queue.get()));
        System.out.println((queue.get()));
        System.out.println(queue);

        queue.put(4);
        queue.put(5);
        queue.put(6);
        System.out.println(queue);

        System.out.println((queue.get()));
        System.out.println((queue.get()));
        System.out.println((queue.peek()));
        System.out.println((queue.peek()));
        System.out.println((queue.get()));
        System.out.println(queue);
    }
}

