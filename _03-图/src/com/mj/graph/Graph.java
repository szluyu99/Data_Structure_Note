package com.mj.graph;

import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Set;

public abstract class Graph<V, E> {
	protected WeightManager<E> weightManager; // 权重管理
	
	public Graph() {}
	
	public Graph(WeightManager<E> weightManager) {
		this.weightManager = weightManager;
	}
	
	public abstract int edgesSize(); // 边的数量
	public abstract int verticesSize();	// 顶点数量
	
	public abstract void addVertex(V v); // 添加顶点
	public abstract void addEdge(V from, V to); // 添加边
	public abstract void addEdge(V from, V to, E weight);// 添加边
	
	public abstract void removeVertex(V v); // 删除顶点
	public abstract void removeEdge(V from, V to); // 删除边
	
	public abstract void bfs(V begin, VertexVisitor<V> visitor); // 广度优先搜索
	public abstract void dfs(V begin, VertexVisitor<V> visitor); // 深度优先搜索
	
	public abstract List<V> topologicalSort(); // 拓扑排序
	
	public abstract Set<EdgeInfo<V, E>> mst(); // 最小生成树
	
//	public abstract Map<V, E> shortestPath(V begin); // 单源最短路径
	public abstract Map<V, PathInfo<V, E>> shortestPath(V begin); // 返回路径信息的单源最短路径
	
	public abstract Map<V, Map<V, PathInfo<V, E>>> shortestPath(); // 多源最短路径
	
	public interface WeightManager<E> { // 管理权重
		int compare(E w1, E w2); // 比较权重
		E add(E w1, E w2); // 权重相加
		E zero(); 
	}
	
	public interface VertexVisitor<V>{
		boolean visit(V v);
	}
	
	/**
	 * 最短路径返回的路径信息, 包含到某个顶点的路径信息和总权值
	 */
	public static class PathInfo<V, E> {
		protected E weight; // 权值
		protected List<EdgeInfo<V, E>> edgeInfos = new LinkedList<>(); 
		public PathInfo() {}
		public PathInfo(E weight) {
			this.weight = weight;
		}
		public E getWeight() {
			return weight;
		}
		public void setWeight(E weight) {
			this.weight = weight;
		}
		public List<EdgeInfo<V, E>> getEdgeInfos() {
			return edgeInfos;
		}
		public void setEdgeInfos(List<EdgeInfo<V, E>> edgeInfos) {
			this.edgeInfos = edgeInfos;
		}
		@Override
		public String toString() {
			return "PathInfo [weight=" + weight + ", edgeInfos=" + edgeInfos + "]";
		}
		
	}
	
	public static class EdgeInfo<V, E>{
		private V from;
		private V to;
		private E weight;
		public EdgeInfo(V from, V to, E weight) { // 边信息
			super();
			this.from = from;
			this.to = to;
			this.weight = weight;
		}
		public V getFrom() {
			return from;
		}
		public void setFrom(V from) {
			this.from = from;
		}
		public V getTo() {
			return to;
		}
		public void setTo(V to) {
			this.to = to;
		}
		public E getWeight() {
			return weight;
		}
		public void setWeight(E weight) {
			this.weight = weight;
		}
		@Override
		public String toString() {
			return "EdgeInfo [from=" + from + ", to=" + to + ", weight=" + weight + "]";
		}
	}
	
 }
