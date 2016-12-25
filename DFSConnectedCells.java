import java.io.*;
import java.util.*;
import java.text.*;
import java.math.*;
import java.util.regex.*;

public class DFSConnectedCells {
    
    public static int getBiggestRegion(int[][] matrix) {
        int w = matrix.length;
        int h = matrix[0].length;
        int[][] visited = new int[w][h];
        int maxSize = 0;
        for(int i=0; i<w; i++) {
            for(int j=0; j<h; j++) {
                if(matrix[i][j]==1) {
                    int size = dfs(i,j,matrix,visited,w,h);
                    if(size > maxSize)
                        maxSize = size;
                }
            }
        }
        return maxSize;
    }
    
    static int dfs(int i, int j, int[][] matrix, int[][] visited, int w, int h) {
        visited[i][j]=1;
        int size = 1;
        if(i>0 && matrix[i-1][j]==1 && visited[i-1][j]==0) {
            size += dfs(i-1,j,matrix,visited,w,h);
        }
        if(j>0 && matrix[i][j-1]==1 && visited[i][j-1]==0) {
            size += dfs(i,j-1,matrix,visited,w,h);
        }
        if(i<w-1 && matrix[i+1][j]==1 && visited[i+1][j]==0) {
            size += dfs(i+1,j,matrix,visited,w,h);
        }
        if(j<h-1 && matrix[i][j+1]==1 && visited[i][j+1]==0) {
            size += dfs(i,j+1,matrix,visited,w,h);
        }
        if(j<h-1 && i<w-1 && matrix[i+1][j+1]==1 && visited[i+1][j+1]==0) {
            size += dfs(i+1,j+1,matrix,visited,w,h);
        }
        if(j<h-1 && i>0 && matrix[i-1][j+1]==1 && visited[i-1][j+1]==0) {
            size += dfs(i-1,j+1,matrix,visited,w,h);
        }
        if(j>0 && i>0 && matrix[i-1][j-1]==1 && visited[i-1][j-1]==0) {
            size += dfs(i-1,j-1,matrix,visited,w,h);
        }
        if(j>0 && i<w-1 && matrix[i+1][j-1]==1 && visited[i+1][j-1]==0) {
            size += dfs(i+1,j-1,matrix,visited,w,h);
        }
        return size;
    }
    
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int n = in.nextInt();
        int m = in.nextInt();
        int grid[][] = new int[n][m];
        for(int grid_i=0; grid_i < n; grid_i++){
            for(int grid_j=0; grid_j < m; grid_j++){
                grid[grid_i][grid_j] = in.nextInt();
            }
        }
        System.out.println(getBiggestRegion(grid));
    }
}
