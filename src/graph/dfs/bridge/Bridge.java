package graph.dfs.bridge;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

import graph.Graph;

public class Bridge {

    private int bridges;      
    private int cnt;          
    private int[] pre;        
    private int[] low; 
	
    public Bridge(Graph G) {
        low = new int[G.getV()];
        pre = new int[G.getV()];
        for (int v = 0; v < G.getV(); v++)
            low[v] = -1;
        for (int v = 0; v < G.getV(); v++)
            pre[v] = -1;
        
        for (int v = 0; v < G.getV(); v++)
            if (pre[v] == -1)
                dfs(G, v, v);
    }

	
    public int components() { 
    	return bridges + 1; 
    }

    private void dfs(Graph G, int u, int v) {
        pre[v] = cnt++;
        low[v] = pre[v];
        for (int w : G.getAdj(v)) {
            if (pre[w] == -1) {
                dfs(G, v, w);
                low[v] = Math.min(low[v], low[w]);
                if (low[w] == pre[w]) {
                    System.out.println(v + "-" + w + " is a bridge");
                    bridges++;
                }
            }
            else if (w != u)
                low[v] = Math.min(low[v], pre[w]);
        }
    }
    
    public static void main(String[] args) throws FileNotFoundException {
    	Graph g = new Graph(new Scanner(new File("src/graph/tiny.txt")));
        Bridge bridge = new Bridge(g);
        System.out.println("Edge connected components = " + bridge.components());
    }

}
