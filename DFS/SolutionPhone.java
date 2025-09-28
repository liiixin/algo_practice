package DFS;

// https://www.nowcoder.com/questionTerminal/c552248efdbd41a18d35b7a2329f7ad8
// 手机屏幕解锁模式
/*
        图形密码即使图案相同，但是顺序不同的话也算作一种
        dfs，以每个位置作为起点，然后走 [n, m] 个点
        当走到无路可走的时候，如果走的点小于 m，那么该路径不满足要求
        当已经走的点 > n 的时候，那么表示接下来的路径也不满足要求，直接返回

        当我们发现周围 几个点都已经走过了，如果是普通的 dfs，这时候已经返回了
        但是，我们可以越过已经访问过的点，继续往下一个点走，因此我们需要判断
        8 个方向第 2 个点是否已经访问过了，如果没有，那么可以继续访问

        注意：只有我们发现某个方向上的点访问过了才可以越过该点
        如果该方向上的第一个点还没有访问，那么我们不能直接越过
        */
public class SolutionPhone {
    /**
     * 实现方案
     * @param m int整型 最少m个键
     * @param n int整型 最多n个键
     * @return int整型
     */
    public int solution (int m, int n) {
        //范围处理
        if(m > n){
            return 0;
        }
        m = Math.max(0, m);
        n = Math.min(9, n);
        if(n == 0){
            return 0;
        }
        // write code here
        res = 0;
        visited = new boolean[3][3];
        // 遍历9个点，p表示访问个数
        for(int i = 0; i < 3; i++){
            for(int j = 0; j < 3; j++){
                dfs(i, j, m, n, 1);
            }
        }
        return res;
    }
    static int res;
    static boolean[][] visited;
    int[][] pos = { // 16条路
            {1, 0},{1, 1},{1, -1}, {-1, 0}, {-1, -1}, {-1, 1}, {0, 1}, {0, -1},
            {1, 2}, {2, 1}, {-1, 2}, {-1, -2}, {-2, -1}, {-2, 1}, {1, -2}, {2, -1}
    };
    private void dfs(int i, int j, int m, int n, int p){
        if(p >= m){
            res++;
        }
        //当访问个数大于等于 n 个，那么已经足够了，无需继续访问
        if(p >= n){
            return;
        }

        visited[i][j] = true;
        //16条路都走一遍
        for(int[] po : pos){
            int x = i + po[0];
            int y = j + po[1];
            if(!isEvil(x, y)){ // 如果该点 走得通
                if(!visited[x][y]){ // 如果该点 未访问过
                    dfs(x, y, m, n, p + 1); // 继续dfs
                }else{              // 如果该点 访问过
                                    // 越过同方向上的点
                    int xx = x + po[0];
                    int yy = y + po[1];
                    if(!isEvil(xx, yy) && !visited[xx][yy]){ // 如果该点 走得通且未访问过
                        //越过 (x, y) 点，访问下一个点
                        dfs(xx, yy, m, n, p + 1);
                    }
                }
            }
        }
        visited[i][j] = false;
    }
    private boolean isEvil(int i, int j){ // 判断是否越界
        return i < 0 || i >= 3 || j < 0 || j >= 3;
    }

    public static void main(String[] args) {
        int m = 1, n = 2;
        SolutionPhone solutionPhone = new SolutionPhone();
        int ans = solutionPhone.solution(m, n);
        System.out.println(ans);
    }
}
