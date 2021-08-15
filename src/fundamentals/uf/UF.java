package fundamentals.uf;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class UF {

	private int[] parent;
	private byte[] rank;
	private int count;
	
	public UF(int n) {
		if(n < 0)
			throw new IllegalArgumentException();
		count = n;
		parent = new int[n];
		rank = new byte[n];
		for (int i = 0; i < n; i++) {
			parent[i] = i;
			rank[i] = 0;
		}
	}
	
	public int find(int p) {
		validate(p);
		while(p != parent[p]) {
			parent[p] = parent[parent[p]];
			p = parent[p];
		}
		return p;
	}
	
	public int count() {
		return count;
	}
	
	public void union(int p, int q) {
		int rootP = find(p);
		int rootQ = find(q);
		
		if(rootP == rootQ)
			return;
		
		if(rank[rootP] < rank[rootQ])
			parent[rootP] = rootQ;
		else if(rank[rootP] > rank[rootQ])
			parent[rootQ] = rootP;
		else {
			parent[rootQ] = rootP;
			rank[rootP]++;
		}
		count--;
	}
	
	private void validate(int p) {
		if(p < 0 || p >= parent.length) 
			throw new IllegalArgumentException("Index " + p + " is not between 0 and " + (parent.length+1));
	}
	
    public static void main(String[] args) throws FileNotFoundException{
        Scanner sc = new Scanner(new File("src/fundamentals/uf/tinyUF.txt"));
    	int n = sc.nextInt();
        UF uf = new UF(n);
        while (sc.hasNext()) {
            int p = sc.nextInt();
            int q = sc.nextInt();
            if (uf.find(p) == uf.find(q)) continue;
            uf.union(p, q);
            System.out.println(p + " " + q);
        }
        System.out.println(uf.count() + " components");
    }
	
}
