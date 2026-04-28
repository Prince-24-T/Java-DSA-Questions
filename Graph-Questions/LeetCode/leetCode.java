import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.PriorityQueue;

public class leetCode {

    // Questions number 1631;
    class Info implements Comparable<Info> {
        int row;
        int col;
        int height;

        public Info(int row, int col, int height) {
            this.row = row;
            this.col = col;
            this.height = height;
        }

        @Override
        public int compareTo(Info e2) {
            return this.height - e2.height;
        }
    }

    public int minimumEffortPath(int[][] heights) {
        int min = Integer.MAX_VALUE;
        PriorityQueue<Info> pq = new PriorityQueue<>();
        boolean visited[][] = new boolean[heights.length][heights[0].length];
        int n = heights.length;
        int m = heights[0].length;
        pq.add(new Info(0, 0, 0));
        int validPath[][] = { { 0, 1 }, { 1, 0 }, { 0, -1 }, { -1, 0 } };
        while (!pq.isEmpty()) {
            Info curr = pq.remove();
            int currHeight = heights[curr.row][curr.col];
            if (min == Integer.MAX_VALUE) {
                min = curr.height;
            } else {
                if (curr.height > min) {
                    min = curr.height;
                }
            }
            if (curr.row == n - 1 && curr.col == m - 1) {
                break;
            }
            visited[curr.row][curr.col] = true;
            for (int i = 0; i < validPath.length; i++) {
                int newRow = curr.row + validPath[i][0];
                int newCol = curr.col + validPath[i][1];
                if (newRow >= 0 && newCol >= 0 && newRow < heights.length && newCol < heights[0].length) {
                    if (!visited[newRow][newCol]) {
                        int height = Math.abs(heights[newRow][newCol] - currHeight);
                        pq.add(new Info(newRow, newCol, height));
                    }
                }
            }
        }
        return min;
    }

    // 1514. Path with Maximum Probability

    class Solution {
        class Edges {
            int src;
            int dest;
            double prob;

            public Edges(int src, int dest, double prob) {
                this.src = src;
                this.dest = dest;
                this.prob = prob;
            }
        }

        class Info implements Comparable<Info> {
            int src;
            double prob;

            public Info(int src, double prob) {
                this.prob = prob;
                this.src = src;
            }

            @Override
            public int compareTo(Info e2) {
                return Double.compare(e2.prob, this.prob);
            }
        }

        public double maxProbability(int n, int[][] edges, double[] succProb, int start_node, int end_node) {
            double ans = 1;
            ArrayList<Edges> graph[] = new ArrayList[n];
            for (int i = 0; i < n; i++) {
                graph[i] = new ArrayList<>();
            }
            for (int i = 0; i < edges.length; i++) {
                int src = edges[i][0];
                int dest = edges[i][1];
                double prob = succProb[i];
                graph[src].add(new Edges(src, dest, prob));
                graph[dest].add(new Edges(dest, src, prob));
            }
            PriorityQueue<Info> pq = new PriorityQueue<>();
            boolean visited[] = new boolean[n];
            pq.add(new Info(start_node, 1.0));
            while (!pq.isEmpty()) {
                Info curr = pq.remove();
                visited[curr.src] = true;
                if (curr.src == end_node) {
                    return curr.prob;
                }
                for (int i = 0; i < graph[curr.src].size(); i++) {
                    Edges e = graph[curr.src].get(i);
                    if (!visited[e.dest]) {
                        int newVal = e.dest;
                        double newProb = curr.prob * e.prob;
                        pq.add(new Info(newVal, newProb));
                    }
                }
            }
            return 0;
        }
    }

    // 721. Accounts Merge classic question

    public String find(String str, HashMap<String, String> par) {
        if (!str.equals(par.get(str))) {
            par.put(str, find(par.get(str), par));
        }
        return par.get(str);
    }

    public void union(String x, String y, HashMap<String, String> par) {
        String parX = find(x, par);
        String parY = find(y, par);
        if (parX.equals(parY)) {
            return;
        } else {
            par.put(parY, parX);
        }
    }

    public List<List<String>> accountsMerge(List<List<String>> accounts) {

        HashMap<String, String> parent = new HashMap<>();
        HashMap<String, String> emailToName = new HashMap<>();

        for (List<String> acc : accounts) {
            String name = acc.get(0);
            for (int i = 1; i < acc.size(); i++) {
                String email = acc.get(i);
                parent.put(email, email);
                emailToName.put(email, name);
            }
        }

        for (List<String> acc : accounts) {
            String x = acc.get(1);
            for (int i = 2; i < acc.size(); i++) {
                String y = acc.get(i);
                union(x, y, parent);
            }
        }
        HashMap<String, List<String>> map = new HashMap<>();
        for (String key : parent.keySet()) {
            String root = find(key, parent);
            map.computeIfAbsent(root, k -> new ArrayList<>()).add(key);
        }

        List<List<String>> res = new ArrayList<>();

        for (String root : map.keySet()) {
            List<String> emails = map.get(root);
            Collections.sort(emails);
            List<String> temp = new ArrayList<>();
            temp.add(emailToName.get(root));
            temp.addAll(emails);
            res.add(temp);

        }
        return res;
    }

    /// 1489 question number
    /// class Solution {

    public int find(int x, int par[]) {
        if (par[x] != x) {
            par[x] = find(par[x], par);
        }
        return par[x];

    }

    public void union(int x, int y, int par[], int rank[]) {
        int parA = find(x, par);
        int parB = find(y, par);
        if (parA != parB) {
            if (rank[parA] > rank[parB]) {
                par[parB] = parA;
            } else if (rank[parA] < rank[parB]) {
                par[parA] = parB;

            } else {
                par[parB] = parA;
                rank[parA]++;
            }
        }
    }

    class Edges implements Comparable<Edges> {
        int src, dest, weight, idx;

        public Edges(int src, int dest, int weight, int idx) {
            this.src = src;
            this.dest = dest;
            this.weight = weight;
            this.idx = idx;
        }

        public int compareTo(Edges e) {
            return this.weight - e.weight;
        }
    }

    public int kruskalAlgo(int n, ArrayList<Edges> graph, int skipEdge, int forceEdge) {

        int par[] = new int[n];
        int rank[] = new int[n];

        for (int i = 0; i < n; i++) {
            par[i] = i;
        }
        int mstCost = 0;
        int edgesCount = 0;

        if (forceEdge != -1) {
            Edges e = graph.get(forceEdge);
            int parA = find(e.src, par);
            int parB = find(e.dest, par);
            if (parA != parB) {
                union(e.src, e.dest, par, rank);
                mstCost += e.weight;
                edgesCount++;
            }
        }

        for (int i = 0; i < graph.size(); i++) {
            if (skipEdge == i) {
                continue;
            }
            Edges e = graph.get(i);
            int parA = find(e.src, par);
            int parB = find(e.dest, par);
            if (parA != parB) {
                union(e.src, e.dest, par, rank);
                edgesCount++;
                mstCost += e.weight;
            }
        }
        if (edgesCount != n - 1) {
            return Integer.MAX_VALUE;
        }
        return mstCost;

    }

    public List<List<Integer>> findCriticalAndPseudoCriticalEdges(int n, int[][] edges) {

        ArrayList<Edges> graph = new ArrayList<>();
        for (int i = 0; i < edges.length; i++) {
            int src = edges[i][0];
            int dest = edges[i][1];
            int weight = edges[i][2];
            graph.add(new Edges(src, dest, weight, i));

        }
        Collections.sort(graph);
        List<Integer> crit = new ArrayList<>();
        List<Integer> pseudo = new ArrayList<>();

        int originalMst = kruskalAlgo(n, graph, -1, -1);

        for (int i = 0; i < graph.size(); i++) {

            int skipWeight = kruskalAlgo(n, graph, i, -1);
            if (skipWeight > originalMst) {
                crit.add(graph.get(i).idx);
            } else {
                int forceEdge = kruskalAlgo(n, graph, -1, i);
                if (forceEdge == originalMst) {
                    pseudo.add(graph.get(i).idx);

                }
            }

        }
        List<List<Integer>> result = new ArrayList<>();
        result.add(crit);
        result.add(pseudo);

        return result;

    }
}
