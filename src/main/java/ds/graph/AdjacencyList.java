package ds.graph;

import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class AdjacencyList<L> implements Graph<L> {

	private Map<Point, Set<Edge<L>>>	map;

	private Set<Edge<L>>				edges;

	public AdjacencyList(Map<Point, Set<Edge<L>>> map) {
		super();
		this.map = map;
	}

	public AdjacencyList(List<Point> points, Set<Edge<L>> edges) {
		super();
		this.map = new HashMap<>();
		this.edges = edges;
		for (Point point : points) {
			map.put(point, new HashSet<Edge<L>>());
		}
		for (Edge<L> edge : edges) {
			map.get(edge.getPoint1()).add(edge);
			map.get(edge.getPoint2()).add(edge);
		}
	}

	@Override
	public Iterable<Edge<L>> getEdge(Point point) {
		return map.get(point);
	}

	@Override
	public Iterable<Edge<L>> allEdge() {
		if (edges == null)
			culEdges();
		return edges;
	}

	private void culEdges() {
		edges = new HashSet<>();
		for (Set<Edge<L>> set : map.values()) {
			edges.addAll(set);
		}
	}

	@Override
	public Iterable<Point> allPoint() {
		return map.keySet();
	}

}
