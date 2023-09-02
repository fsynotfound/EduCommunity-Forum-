package com.nowcoder.community;

import java.util.*;

class Solution {
    int m, n;
    int[][] g;

    public int[][] colorBorder(int[][] grid, int row, int col, int color) {
        m = grid.length;
        n = grid[0].length;
        g = grid;
        int[][] ans = new int[m][n];
        Deque<int[]> d = new ArrayDeque<>();
        boolean[][] res = new boolean[m][n];
        d.addLast(new int[]{row, col});
        res[row][col] = true;
        bfs(d, res);
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (res[i][j]) {
                    ans[i][j] = color;
                } else {
                    ans[i][j] = grid[i][j];
                }
            }
        }
        return ans;
    }

    int[][] dirs = new int[][]{{1, 0}, {-1, 0}, {0, 1}, {0, -1}};

    void bfs(Deque<int[]> d, boolean[][] res) {
        while (!d.isEmpty()) {
            int[] poll = d.pollFirst();
            int x = poll[0], y = poll[1];
            for (int[] dir : dirs) {
                int nx = x + dir[0], ny = y + dir[1];
                if (nx < 0 || nx >= m || ny < 0 || ny >= n) {
                    continue;
                }
                if (g[nx][ny] != g[x][y]) {
                    continue;
                }
                res[nx][ny] = true;
                d.addLast(new int[]{nx, ny});
            }
        }
    }

    public static void main(String[] args) {
        Solution solution = new Solution();
        int[][] gird = new int[][]{{1, 1}, {1, 2}};
        gird = solution.colorBorder(gird, 0, 0, 3);
        for(int i = 0; i < gird.length; i++){
            for(int j = 0; j < gird[0].length; j++){
                System.out.println(gird[i][j]);
            }
        }
    }
}

class Main {
    public static void main(String[] args) {
        String[] array = new String[]{"Apple", "Orange", "Banana", "Apple"};
        Arrays.sort(array, (s1, s2) -> {
            return s1.compareTo(s2);
        });
        System.out.println(String.join(", ", array));
    }
}