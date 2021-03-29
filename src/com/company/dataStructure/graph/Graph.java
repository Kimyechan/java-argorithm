package com.company.dataStructure.graph;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

class Vertex {
    public int value;
    List<Integer> adjList;

    public Vertex(int value, List<Integer> adjList) {
        this.value = value;
        this.adjList = adjList;
    }
}

class Graph {
    List<Vertex> vertexList = new ArrayList<>();
    int[] visited;
    Queue<Integer> queue = new LinkedList<>();

    public void insert(int value, int [] adjVertices) {
        int vInd = vertexList.size();
        List<Integer> adjList = new ArrayList<>();

        for (int idx: adjVertices) {
            adjList.add(idx);
        }
        Vertex v = new Vertex(value, adjList);

        for (int adjV: v.adjList) {
            vertexList.get(adjV).adjList.add(vInd);
        }

        vertexList.add(v);
    }

//    public boolean bfs(int ind, int value) {
//        visited[ind] = 1;
//        if (ind == value) {
//            return true;
//        }
//
//        queue.offer(ind);
//
//        while (queue.size() != 0) {
//            int element = queue.remove();
//            for (Integer vertex : vertexList.get(element).adjList) {
//                if (visited[vertex] == 1) {
//                    continue;
//                }
//                if (vertex == value) {
//                    return true;
//                }
//                visited[vertex] = 1;
//                queue.offer(vertex);
//            }
//        }
//
//        return false;
//    }
    public boolean bfs(int ind, int value) {
        queue.offer(ind);

        while (queue.size() != 0) {
            int element = queue.remove();
            visited[element] = 1;
            if (element == value) {
                return true;
            }
            for (Integer vertex : vertexList.get(element).adjList) {
                if (visited[vertex] == 1) {
                    continue;
                }
                queue.offer(vertex);
            }
        }

        return false;
    }

//    public boolean dfs(int ind, int value) {
//        if (ind == value) {
//            return true;
//        }
//        visited[ind] = 1;
//
//        for (Integer vertex : vertexList.get(ind).adjList) {
//            if (visited[vertex] == 1) {
//                continue;
//            }
//            if (vertex == value) {
//                return true;
//            }
//            return dfs(vertex, value);
//        }
//        return false;
//    }
    public boolean dfs(int ind, int value) {
        visited[ind] = 1;
        if (ind == value) {
            return true;
        }

        for (Integer vertex : vertexList.get(ind).adjList) {
            if (visited[vertex] == 1) {
                continue;
            }
            return dfs(vertex, value);
        }
        return false;
    }


    public void initializeVisited() {
        int size = vertexList.size();
        visited = new int[size];

        for (int i = 0; i < size; i++) {
            visited[i] = 0;
        }
    }
}

class GraphTest {
    public static void main(String[] args) {
        Graph g = new Graph();

        int [] array1 = {};
        g.insert(0, array1);

        int [] array2 = {0};
        g.insert(1, array2);

        int [] array3 = {1};
        g.insert(2, array3);

        int [] array4 = {0, 2};
        g.insert(3, array4);

        int [] array5 = {3};
        g.insert(4, array5);

        g.initializeVisited();
        System.out.println(g.bfs(0, 2));
        g.initializeVisited();
//        g.visited = new int[]{0, 0, 0, 0, 0};
        System.out.println(g.bfs(0, 6));
        g.initializeVisited();
//        g.visited = new int[]{0, 0, 0, 0, 0};
        System.out.println(g.dfs(0, 2));
        g.initializeVisited();
//        g.visited = new int[]{0, 0, 0, 0, 0};
        System.out.println(g.dfs(0, 6));
    }
}
