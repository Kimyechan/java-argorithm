package com.company.dataStructure.queue;

class CircularQueue {
    private int capacity;
    private int front;
    private int rear;
    private int[] array;
    private boolean isFull;

    public CircularQueue(int capacity) {
        this.capacity = capacity;
        this.front = 0;
        this.rear = 0;
        this.array = new int[capacity];
        this.isFull = false;
    }

    public boolean put(int value) {
        if (isFull) {
            System.out.println("overflow 발생");
            return false;
        }
        array[rear++] = value;
        rear = rear % capacity;
        if (rear == front) {
            isFull = true;
        }
        return true;
    }

    public int get() {
        if (!isFull && front == rear) {
            System.out.println("underflow 발생");
            return -1;
        }
        int ret = array[front++];
        front = front % capacity;
        isFull = false;
        return ret;
    }

    public int peek() {
        if (!isFull && front == rear) {
            System.out.println("underflow 발생");
            return -1;
        }
        return array[front];
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        int end = rear;
        if (front > rear) {
            end = rear + capacity;
        }
        for (int i = front; i < end; i++) {
            sb.append(array[i % capacity] + " ");
        }
        return sb.toString();
    }
}

class CircularQueueTest {
    public static void main(String[] args) {
        CircularQueue queue = new CircularQueue(5);
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

