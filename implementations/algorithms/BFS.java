package algorithms;

import java.util.Queue;
import java.util.LinkedList;
import java.util.List;

public class BFS {
  private static class Edge {
    private int from, to;    

    public Edge(int from, int to) {
      this.from = from;
      this.to = to;      
    }
  }
  
  public void search(List<List<Edge>> graph, int start, int v) {
    Queue<Integer> queue = new LinkedList<>();
    queue.offer(start);
    boolean[] visited = new boolean[v];
    visited[start] = true;
    while (!queue.isEmpty()) {
      int node = queue.poll();
      for (Edge edge : graph.get(node)) {
        if (!visited[edge.to]) {
          queue.offer(edge.to);
          visited[edge.to] = true;
        }
      }
    }
  }
}
