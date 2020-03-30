package com.mj.union;
/**
 * Quick Union - 基于rank的优化 - 路径减半(Path Halving)
 * @author yusael
 */
public class UnionFind_QU_R_PH extends UnionFind_QU_R {

	public UnionFind_QU_R_PH(int capacity) {
		super(capacity);
	}
	
	public int find(int v){
		rangeCheck(v);
		while(v != parents[v]){
			parents[v] = parents[parents[v]];
			v = parents[v];
		}
		return v;
	}
	
}
