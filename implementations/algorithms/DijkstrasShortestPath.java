package algorithms;

import java.util.Comparator;
import java.util.List;
import java.util.PriorityQueue;

public class DijkstrasShortestPath {
  private static class Edge {
    private int from, to, cost;    

    public Edge(int from, int to, int cost) {
      this.from = from;
      this.to = to;      
      this.cost = cost;
    }
  }

  private static class Node {
    int id, value;

    public Node(int id, int value) {
      this.id = id;
      this.value = value;
    }
  }

  private Comparator<Node> nodeComparator = new Comparator<>() {
    @Override
    public int compare(Node node1, Node node2) {
      if (node1.value == node2.value) return 0;
      return node1.value < node2.value ? -1 : 1;      
    }
  };
  
  // Returns optimal distance from start node to each other node 
  // in a directed graph with non-negative integer weights
  public int[] lazyDijkstra(List<List<Edge>> graph, int start, int v) {
    int[] distance = new int[v];
    boolean[] visited = new boolean[v];
    distance[start] = 0;
    PriorityQueue<Node> queue = new PriorityQueue<>(nodeComparator);
    queue.offer(new Node(start, 0));
    Node node;
    while (!queue.isEmpty()) {
      node = queue.poll();
      for (Edge edge : graph.get(node.id)) {
        if (!visited[edge.to]) {
          int newDistance = node.value + edge.cost;
          if (newDistance < distance[edge.to]) {
            distance[edge.to] = newDistance;
            queue.offer(new Node(edge.to, newDistance));
          }
        }
      }
      visited[node.id] = true;
    }
    return distance;
  }
}
