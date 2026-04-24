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

}
