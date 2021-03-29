package com.company.dataStructure.queue.tree;

class BinaryTree {
    private int [] array;

    public BinaryTree(int [] array) {
        this.array = array;
    }

    public void preorder() {
        System.out.println("preoder = ");
        preorderRecursion(0);
        System.out.println("");
    }

    public void preorderRecursion(int index) {
        if (index >= array.length) {
            return;
        }
        System.out.print(array[index]);

        preorderRecursion(index * 2 + 1);
        preorderRecursion(index * 2 + 2);
    }

    public void inorder() {
        System.out.println("inorder = ");
        inorderRecursion(0);
        System.out.println("");
    }

    public void inorderRecursion(int index) {
        if (index >= array.length) {
            return;
        }

        inorderRecursion(index * 2 + 1);
        System.out.print(array[index]);
        inorderRecursion(index * 2 + 2);
    }

    public void postorder() {
        System.out.println("postorder = ");
        postorderRecursion(0);
        System.out.println("");
    }

    public void postorderRecursion(int index) {
        if (index >= array.length) {
            return;
        }

        postorderRecursion(index * 2 + 1);
        postorderRecursion(index * 2 + 2);
        System.out.print(array[index]);
    }

    public boolean bfs(int value) {
        for (int search : array) {
            if (search == value) {
                return true;
            }
        }
        return false;
    }

    public boolean dfs(int value) {
        int ret = dfsRecursion(0, value);
        if (ret == -1) {
            return false;
        } else {
            return true;
        }
    }

    public int dfsRecursion(int index, int value) {
        if (index >= array.length) {
            return -1;
        }

        if (array[index] == value) {
            return index;
        }

        int left = dfsRecursion(index * 2 + 1, value);
        int right = dfsRecursion(index * 2 + 2, value);

        if (left != -1) {
            return left;
        }

        if (right != -1) {
            return right;
        }

        return -1;
    }
}


class BinaryTreeTest {
    public static void main(String[] args) {
        int [] array = {0, 1, 2, 3, 4, 5, 6, 7, 8, 9};
        BinaryTree tree = new BinaryTree(array);

        tree.preorder();
        tree.inorder();
        tree.postorder();

        System.out.println(tree.bfs(2));
        System.out.println(tree.bfs(10));

        System.out.println(tree.dfs(5));
        System.out.println(tree.dfs(14));
    }
}