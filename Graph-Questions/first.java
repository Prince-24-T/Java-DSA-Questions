import java.util.LinkedList;
import java.util.Queue;

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

    // public static void main(String[] args) {

    // }

}