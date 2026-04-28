package kruskalAlgo;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.PriorityQueue;

public class first {
    public int find(int x, int par[]) {
        if (par[x] != x) {
            par[x] = find(par[x], par);
        }
        return par[x];
    }

    public void union(int x, int y, int rank[], int par[]) {
        int parX = find(x, par);
        int parY = find(y, par);
        if (parX != parY) {
            if (rank[parX] > rank[parY]) {
                par[parY] = parX;
            } else if (rank[parX] < rank[parY]) {
                par[parX] = parY;
            } else {
                par[parY] = parX;
                rank[parX]++;
            }
        }
    }

    class Edges implements Comparable<Edges> {
        int src;
        int dest;
        int weight;

        public Edges(int src, int dest, int weight) {
            this.src = src;
            this.dest = dest;
            this.weight = weight;
        }

        @Override
        public int compareTo(Edges o) {
            return this.weight - o.weight;
        }

    }

    public void creteGraph(ArrayList<Edges> edges) {
        edges.add(new Edges(0, 1, 10));
        edges.add(new Edges(0, 2, 15));
        edges.add(new Edges(0, 3, 30));
        edges.add(new Edges(1, 3, 40));
        edges.add(new Edges(2, 3, 50));
    }

    public void kruskalAlso(ArrayList<Edges> edges, int v) {
        int par[] = new int[v];
        for (int i = 0; i < v; i++) {
            par[i] = i;
        }
        int rank[] = new int[v];
        creteGraph(edges);
        Collections.sort(edges);
        int cost = 0;

        for (int i = 0; i < v - 1; i++) {
            Edges e = edges.get(i);
            int src = e.src;
            int dest = e.dest;
            int ParA = find(src, par);
            int ParB = find(dest, par);
            if (ParA != ParB) {
                cost += e.weight;
                union(src, dest, rank, par);

            }
        }
        System.out.println(cost);

    }

}
