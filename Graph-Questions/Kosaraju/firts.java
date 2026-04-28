package Kosaraju;

import java.util.ArrayList;
import java.util.Stack;

public class firts {

    class Edges {
        int dest;
        int dist;

        public Edges(int dest, int dist) {
            this.dest = dest;
            this.dist = dist;
        }
    }

    public void topSort(ArrayList<Edges> graph[], int curr, boolean visited[], Stack<Integer> s) {

        visited[curr] = true;
        for (int i = 0; i < graph[curr].size(); i++) {
            Edges e = graph[curr].get(i);
            if (!visited[e.dest]) {
                topSort(graph, e.dest, visited, s);
            }
        }
        s.push(curr);

    }

    public void dfs(ArrayList<Edges> graph[], int curr, boolean vis[]) {
        vis[curr] = true;
        System.err.println(curr + " ");
        for (int i = 0; i < graph[curr].size(); i++) {
            Edges e = graph[curr].get(i);
            if (!vis[e.dest]) {
                dfs(graph, e.dest, vis);
            }
        }
    }

    public void kosarajuAlgo(int n, ArrayList<Edges> graph[]) {
        boolean vis[] = new boolean[n];
        Stack<Integer> s = new Stack<>();

        for (int i = 0; i < n; i++) {
            if (!vis[i]) {
                topSort(graph, i, vis, s);
            }

        }

        ArrayList<Edges> traspo[] = new ArrayList[n];

        for (int i = 0; i < n; i++) {
            traspo[i] = new ArrayList<>();
        }
        for (int i = 0; i < n; i++) {
            for (int j = 0; i < graph[i].size(); j++) {
                Edges e = graph[i].get(j);
                traspo[e.dest].add(new Edges(j, 0));
            }
        }

        while (s.isEmpty()) {
            int curr = s.pop();
            if (!vis[curr]) {
                dfs(tranpose, curr, vis);
            }

        }

    }

}
