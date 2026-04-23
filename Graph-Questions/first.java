import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;

public class first {

    static class Info {
        int row, col, val;

        public Info(int row, int col, int val) {
            this.row = row;
            this.col = col;
            this.val = val;

        }

    }
    /// BFS traverse

    public static void BFS(int graph[][]) {

        int validPath[][] = { { 0, 1 }, { 1, 0 }, { 0, -1 }, { -1, 0 } };
        Queue<Info> q = new LinkedList<>();
        q.add(new Info(0, 0, 0));
        while (!q.isEmpty()) {
            int size = q.size();
            for (int i = 0; i < size; i++) {
                Info curr = q.remove();
                for (int k = 0; k < validPath.length; k++) {
                    int newRow = curr.row + validPath[k][0];
                    int newCol = curr.col + validPath[k][1];
                    if (newCol >= 0 && newCol < graph[0].length && newRow > 0 && newRow <= graph.length) {
                        System.out.println(curr.val);
                        q.add(new Info(newRow, newCol, curr.val));
                    }
                }
            }
        }

    }

    // DFS traversing
    public static void DFS(int graph[][], boolean visisted[][], int row, int col) {
        if (col < 0 || row < 0 || row >= graph.length || col >= graph[0].length || visisted[row][col]) {
            return;
        }
        visisted[row][col] = true;

        int validPath[][] = { { 0, 1 }, { 1, 0 }, { -1, 0 }, { 0, -1 } };

        for (int i = 0; i < validPath.length; i++) {
            int newRow = row + validPath[i][0];
            int newCol = col + validPath[i][1];
            DFS(graph, visisted, newRow, newCol);
        }

    }

    /// cycclic detection in undirected graph;
    public static boolean isCyclic(int graph[][], boolean visited[][], int row, int col, int parRow, int parCol) {
        if (row < 0 || col < 0 || row >= graph.length || col >= graph[0].length) {
            return false;
        }
        visited[row][col] = true;
        int validPath[][] = { { 0, 1 }, { 1, 0 }, { -1, 0 }, { 0, -1 } };
        for (int i = 0; i < validPath.length; i++) {

            int newRow = row + validPath[i][0];
            int newCol = col + validPath[i][1];
            int newpar = graph[row][col];
            if (newCol >= 0 && newRow >= 0 && newCol < graph[0].length && newRow < graph.length) {

                if (!visited[newRow][newCol]) {
                    if (isCyclic(graph, visited, newRow, newCol, row, col)) {
                        return true;

                    }
                } else if (visited[newRow][newRow] && parCol != newCol || parRow != newRow) {
                    return true;
                }
            }

        }
        return false;

    }

    // delect bipartite graph;
    public static boolean isBipartite(int graph[][]) {

        int validPath[][] = { { 0, 1 }, { 1, 0 }, { 0, -1 }, { -1, 0 } };
        int col[][] = new int[graph.length][graph[0].length];

        for (int i = 0; i < col.length; i++) {
            Arrays.fill(col[i], -1);
        }
        col[0][0] = 0;

        for (int g = 0; g < graph.length; g++) {
            for (int j = 0; j < graph[0].length; j++) {

                if (col[g][j] == -1) {
                    Queue<Info> q = new LinkedList<>();
                    q.add(new Info(g, j, 0));

                    while (!q.isEmpty()) {
                        int size = q.size();
                        for (int i = 0; i < size; i++) {
                            Info curr = q.remove();
                            int newRow = curr.row + validPath[i][0];
                            int newCol = curr.col + validPath[i][1];
                            if (newCol >= 0 && newRow >= 0 && newCol < graph[0].length && newRow < graph.length) {
                                if (col[newCol][newCol] == -1) {
                                    int newC = col[curr.row][curr.col] == 0 ? 1 : 0;
                                    q.add(new Info(newRow, newCol, newC));
                                } else if (col[newRow][newCol] == col[curr.row][curr.col]) {
                                    return false;
                                }
                            }

                        }

                    }

                }
            }
        }

        return true;

    }

    // cyclic detction in the directed graph

    public static boolean isCycle2(int graph[][], boolean visited[][], boolean path[][], int row, int col) {

        visited[row][col] = true;
        path[row][col] = true;

        int validPath[][] = { { 0, 1 }, { 1, 0 }, { 0, -1 }, { -1, 0 } };
        for (int i = 0; i < validPath.length; i++) {
            int newRow = row + validPath[i][0];
            int newCol = col + validPath[i][1];
            if (newRow >= 0 && newCol >= 0 && newCol < graph[0].length && newRow < graph.length) {
                if (!visited[newRow][newCol]) {
                    if (isCycle2(graph, visited, path, newRow, newCol)) {
                        return true;
                    }
                } else if (path[newRow][newCol]) {
                    return true;
                }

            }

        }

        path[row][col] = false;

        return false;

    }

    static class Info3 {
        int src;
        int dest;

        public Info3(int src, int dest) {
            this.dest = dest;
            this.src = src;
        }
    }

    // topological sort
    public static void dfs2(ArrayList<Info3> graph[], boolean vivsted[], Stack<Integer> s, int src) {
        if (src > graph.length || vivsted[src]) {
            return;
        }

        vivsted[src] = true;

        for (int i = 0; i < graph[src].size(); i++) {
            Info3 e = graph[src].get(i);
            if (!vivsted[e.dest]) {
                dfs2(graph, vivsted, s, e.dest);

            }

        }
        s.push(src);

    }

    /// graph[i][0] // src , graph[i][1] desti
    public static void Topologicalsort(int graph[][]) {
        int n = graph.length;

        @SuppressWarnings("unchecked")
        ArrayList<Info3>[] graphs = new ArrayList[n];

        for (int i = 0; i < n; i++) {
            graphs[i] = new ArrayList<>();
        }

        for (int i = 0; i < n; i++) {
            int src = graph[i][0];
            int dest = graph[i][1];

            graphs[src].add(new Info3(src, dest));
        }

        Stack<Integer> s = new Stack<>();
        boolean visited[] = new boolean[graph.length];

        for (int i = 0; i < graph.length; i++) {
            int src = graph[i][0];
            if (!visited[src]) {
                dfs2(graphs, visited, s, src);

            }
        }

        while (!s.empty()) {
            System.out.print(s.pop() + " ");

        }
        System.out.println();

    }

    public static void main(String[] args) {

    }

}