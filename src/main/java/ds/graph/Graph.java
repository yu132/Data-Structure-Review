package ds.graph;

public interface Graph<L> {

	Iterable<Edge<L>> getEdge(Point point);

	Iterable<Edge<L>> allEdge();

	Iterable<Point> allPoint();

}
