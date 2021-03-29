package com.company.dataStructure.stack;

class Stack {
    private int capacity;
    private int top;
    private int[] array;

    public Stack(int capacity) {
        this.capacity = capacity;
        this.top = 0;
        this.array = new int[capacity];
    }

    public boolean push(int value) {
        if (top == capacity) {
//            capacity *= 2;
//            int[] newArray = new int[capacity];
//            for (int i = 0; i < array.length; i++) {
//                newArray[i] = array[i];
//            }
//            array = newArray;
            System.out.println("오버플로우 발생");
            return false;
        }

        array[top] = value;
        top++;
        return true;
    }

    public int pop() {
        if (top == 0) {
            return -1;
        }
        int result = array[--top];
        return result;
    }

    public int peek() {
        if (top == 0) {
            return -1;
        }
        return array[top];
    }

    public boolean isEmpty() {
        if (top == 0) {
            return true;
        } else {
            return false;
        }
    }
}

class StackTest {
    public static void main(String[] args) {
        Stack stack = new Stack(4);
        stack.push(1);
        stack.push(2);
        stack.push(3);
        stack.push(4);
        stack.push(5);

        System.out.println((stack.pop()));
        System.out.println((stack.pop()));

        System.out.println((stack.peek()));

        System.out.println((stack.pop()));
        System.out.println((stack.pop()));
        System.out.println((stack.pop()));
    }
}
