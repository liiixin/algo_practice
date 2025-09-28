package BFS;

// https://www.nowcoder.com/questionTerminal/c356fb7d1678419a9e9aa3c6f5a05765
// 迷宫游戏
// ACM模式
import java.util.*;
public class SolutionMaze {
    public static void main(String[] args){

        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();
        char[][] grid = new char[n][n];
        int xStart = -1,yStart = -1;
        int xEnd = -1, yEnd = -1;
        //注意要换行
        scanner.nextLine();
        //每一行读取，一共n行
        for(int i = 0; i < n; i++){
            String str = scanner.nextLine();
            char[] charArray = str.toCharArray();
            //对一行的字符进行遍历，存入的二维数组中
            for(int j = 0;j < n; j++){
                grid[i][j] = charArray[j];
                if(grid[i][j] == 'S'){
                    xStart = i;
                    yStart = j;
                }
                if(grid[i][j] == 'E'){
                    xEnd = i;
                    yEnd = j;
                }
            }
        }
        //如果不存在起始点，直接返回
        if(xStart == -1 || yStart == -1 || xEnd == -1 || yEnd == -1){
            System.out.println(-1);
            return;
        }
        // 定义一个队列存储每一层的节点
        Queue<int[]> queue = new LinkedList<>();
        // 将起点放入队列中
        queue.add(new int[]{xStart,yStart});
        // 定义一个辅助数组，用来标记访问过的节点
        boolean[][] isVisited = new boolean[n][n];
        isVisited[xStart][yStart] = true;

        //用来记录步数
        int res = 0;

        while(!queue.isEmpty()){
            //记录当前层有多少节点
            int currentSize = queue.size();
            //依次遍历每一层
            for(int i = 0; i < currentSize; i++){
                // 取出队列元素
                int[] xy = queue.remove();
                //如果是终点，返回。
                if(xy[0] == xEnd && xy[1] == yEnd){
                    System.out.print(res);
                    return;
                }
                int x = -1,y = -1;
                // x, y颠倒了
                //向右移动，越界处理
                if(xy[0] + 1 == n){
                    x = 0;
                    y = xy[1];
                }else{
                    x = xy[0]+1;
                    y = xy[1];
                }
                if(grid[x][y] != '#' && !isVisited[x][y]){
                    queue.add(new int[]{x,y});
                    isVisited[x][y] = true;
                }
                //向左移动，越界处理
                if(xy[0] - 1 == -1){
                    x = n-1;
                    y = xy[1];
                }else{
                    x = xy[0]-1;
                    y = xy[1];
                }
                if(grid[x][y] != '#' && !isVisited[x][y]){
                    queue.add(new int[]{x,y});
                    isVisited[x][y] = true;
                }
                //向下移动，越界处理
                if(xy[1]+1 == n){
                    x = xy[0];
                    y = 0;
                }else{
                    x = xy[0];
                    y = xy[1]+1;
                }
                if(grid[x][y] != '#' && !isVisited[x][y]){
                    queue.add(new int[]{x,y});
                    isVisited[x][y] = true;
                }
                //向上移动，越界处理
                if(xy[1]-1 == -1){
                    x = xy[0];
                    y = n-1;
                }else{
                    x = xy[0];
                    y = xy[1]-1;
                }
                if(grid[x][y] != '#' && !isVisited[x][y]){
                    queue.add(new int[]{x,y});
                    isVisited[x][y] = true;
                }
            }
            //如果遍历完一层,没有到达终点，步数加1
            res++;
        }
        //最终都没到达终点，返回
        System.out.print(-1);
        return;
    }
}

