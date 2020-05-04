package com.mj;

import java.util.List;
import java.util.Map;
import java.util.Set;
import com.mj.graph.Graph;
import com.mj.graph.Graph.EdgeInfo;
import com.mj.graph.Graph.PathInfo;
import com.mj.graph.Graph.WeightManager;
import com.mj.graph.ListGraph;

public class Main {
	static WeightManager<Double> weightManager = new WeightManager<Double>() {
		public int compare(Double w1, Double w2) {
			return w1.compareTo(w2);
		}

		public Double add(Double w1, Double w2) {
			return w1 + w2;
		}

		@Override
		public Double zero() {
			return 0.0;
		}
	};
	
	public static void main(String[] args) {
//		testMultiSp();
		test();
	}
	
	static void testMultiSp() {
		Graph<Object, Double> graph = directedGraph(Data.NEGATIVE_WEIGHT1);
		Map<Object, Map<Object, PathInfo<Object, Double>>> sp = graph.shortestPath();
		sp.forEach((Object from, Map<Object, PathInfo<Object, Double>> paths) -> {
			System.out.println(from + "---------------------");
			paths.forEach((Object to, PathInfo<Object, Double> path) -> {
				System.out.println(to + " - " + path);
			});
		});
	}
	
	static void testSp() {
		Graph<Object, Double> graph = directedGraph(Data.NEGATIVE_WEIGHT1);
		Map<Object, PathInfo<Object, Double>> sp = graph.shortestPath("A");
		if (sp == null) return;
		sp.forEach((Object v, PathInfo<Object, Double> path) -> {
			System.out.println(v + " - " + path);
		});
	}
	
	static void testMst() {
		Graph<Object, Double> graph = undirectedGraph(Data.MST_01);
		Set<EdgeInfo<Object, Double>> infos = graph.mst();
		for (EdgeInfo<Object, Double> info : infos) {
			System.out.println(info);
		}
	}
	
	static void testTopo() {
		Graph<Object, Double> graph = directedGraph(Data.TOPO);
		List<Object> list = graph.topologicalSort();
		System.out.println(list);
	}
	
	static void testBfs() {
		Graph<Object, Double> graph = directedGraph(Data.BFS_02);
		graph.bfs(0, (Object v) -> {
			System.out.println(v);
			return false;
		});
	}
	
	static void testDfs() {
		Graph<Object, Double> graph = directedGraph(Data.DFS_02);
		graph.dfs("a", (Object v) -> {
			System.out.println(v);
			return false;
		});
	}
	
	static void test() {
		ListGraph<String, Integer> graph = new ListGraph<>();
//		graph.addEdge("V0", "V1");
//		graph.addEdge("V1", "V0");
//		
//		graph.addEdge("V0", "V2");
//		graph.addEdge("V2", "V0");
//
//		graph.addEdge("V0", "V3");
//		graph.addEdge("V3", "V0");
//
//		graph.addEdge("V1", "V2");
//		graph.addEdge("V2", "V1");
//		
//		graph.addEdge("V2", "V3");
//		graph.addEdge("V3", "V2");
//		
//		graph.print();
		
		graph.addEdge("V1", "V0", 9);
		graph.addEdge("V1", "V2", 3);
		graph.addEdge("V2", "V0", 2);
		graph.addEdge("V2", "V3", 5);
		graph.addEdge("V3", "V4", 1);
		graph.addEdge("V0", "V4", 6);
		
		graph.removeEdge("V0", "V4");
		graph.removeVertex("V0");
		
		graph.print();
	}
	
	/**
	 * 有向图
	 */
	private static Graph<Object, Double> directedGraph(Object[][] data) {
		Graph<Object, Double> graph = new ListGraph<>(weightManager);
		for (Object[] edge : data) {
			if (edge.length == 1) {
				graph.addVertex(edge[0]);
			} else if (edge.length == 2) {
				graph.addEdge(edge[0], edge[1]);
			} else if (edge.length == 3) {
				double weight = Double.parseDouble(edge[2].toString());
				graph.addEdge(edge[0], edge[1], weight);
			}
		}
		return graph;
	}
	
	/**
	 * 无向图
	 * @param data
	 * @return
	 */
	private static Graph<Object, Double> undirectedGraph(Object[][] data) {
		Graph<Object, Double> graph = new ListGraph<>(weightManager);
		for (Object[] edge : data) {
			if (edge.length == 1) {
				graph.addVertex(edge[0]);
			} else if (edge.length == 2) {
				graph.addEdge(edge[0], edge[1]);
				graph.addEdge(edge[1], edge[0]);
			} else if (edge.length == 3) {
				double weight = Double.parseDouble(edge[2].toString());
				graph.addEdge(edge[0], edge[1], weight);
				graph.addEdge(edge[1], edge[0], weight);
			}
		}
		return graph;
	}
}
