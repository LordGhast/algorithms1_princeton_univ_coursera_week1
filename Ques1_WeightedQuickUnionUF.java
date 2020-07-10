/*
 Social network connectivity. 
 Given a social network containing nn members and
  a log file containing mm timestamps at which times 
  pairs of members formed friendships, design an algorithm
  to determine the earliest time at which all members are
  connected (i.e., every member is a friend of a friend
  of a friend ... of a friend).
  Assume that the log file is sorted by timestamp and 
  that friendship is an equivalence relation. The 
  running time of your algorithm should be m \log nmlogn
  or better and use extra space proportional to nn. 
 */

public class Ques1_WeightedQuickUnionUF {
	private int id[]; // id[i] = parents of node i
	private int sz[]; // sz[i] = size of node i
	private int count; // number of connected components

	public Ques1_WeightedQuickUnionUF(int N) {
		count = N;
		id = new int[N];
		sz = new int[N];
		for (int i = 0; i < N; ++i) {
			id[i] = i;
			sz[i] = 1;
		}
	}

	public int count() {
		return count;
	}

	private int root(int p) {
		while (id[p] != p) {
			id[p] = id[id[p]]; // path compression
			p = id[p];
		}
		return p;
	}

	public void union(int p, int q) {
		int rootp = root(p);
		int rootq = root(q);
		if (rootp == rootq) return;
		if (sz[rootp] < sz[rootq]) {
			id[rootp] = rootq;
			sz[rootq] += sz[rootp];
		} else {
			id[rootq] = rootp;
			sz[rootp] += sz[rootq];
		}
		count--;
	}

	public boolean connected(int p, int q) {
		return root(p) == root(q);
	}

	public static void main(String[] args) {
		Ques1_WeightedQuickUnionUF uf = new Ques1_WeightedQuickUnionUF(10);
		System.out.println(uf.count() == 10);
		uf.union(0, 1);
		System.out.println(uf.count() == 9);
		uf.union(2, 3);
		System.out.println(uf.count() == 8);
		uf.union(0, 2);
		System.out.println(uf.count() == 7);
		uf.union(1, 3);
		System.out.println(uf.count() == 7);
	}
}
