package algorithms;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.PriorityQueue;

public class DijkstrasShortestPath {
  private Integer[] previous;
  
  private static class Edge {
    private int from, to, cost;    

    public Edge(int from, int to, int cost) {
      this.from = from;
      this.to = to;      
      this.cost = cost;
    }
  }

  private static class Node {
    private int id, value;

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
  // in a directed graph with non-negative integer weights.
  // This could be optimised by using an indexed priority queue, 
  // to avoid polling stale nodes from the priority queue.
  public double[] dijkstra(List<List<Edge>> graph, int start) {
    if (start < 0 || start >= graph.size()) {
      throw new IllegalArgumentException("Start node index is invalid; got: " + start);
    }

    int v = graph.size();
    previous = new Integer[v];
    boolean[] visited = new boolean[v];    
    double[] distance = new double[v];            
    for (int i = 0; i < v; i++) {
      distance[i] = (i == start ? 0 : Double.POSITIVE_INFINITY);            
    }
    PriorityQueue<Node> queue = new PriorityQueue<>(nodeComparator);
    queue.offer(new Node(start, 0));
    Node node;

    while (!queue.isEmpty()) {
      node = queue.poll();
      if (node.value > distance[node.id]) continue;
      for (Edge edge : graph.get(node.id)) {
        if (!visited[edge.to]) {
          int newDistance = node.value + edge.cost;
          if (newDistance < distance[edge.to]) {
            previous[edge.to] = edge.from;
            distance[edge.to] = newDistance;
            queue.offer(new Node(edge.to, newDistance));
          }
        }
      }
      visited[node.id] = true;
    }
    return distance;
  }

  public List<List<Integer>> constructShortestPaths(List<List<Edge>> graph, int start) {
    if (start < 0 || start >= graph.size()) {
      throw new IllegalArgumentException("Start node index is invalid; got: " + start);
    }

    double[] distance = dijkstra(graph, start);    
    List<List<Integer>> paths = new ArrayList<>(graph.size());
    for (int i = 0; i < graph.size(); i++) {
      if (i == start || distance[i] == Double.POSITIVE_INFINITY) {
        paths.set(i, new ArrayList<>());
        continue;
      }
      List<Integer> path = new ArrayList<>();
      path.add(i);
      int node = i;
      while (previous[node] != null) {        
        path.add(previous[node]);
        node = previous[node];
      }
      Collections.reverse(path);
      paths.set(i, path);
    }
    return paths;
  }
}
