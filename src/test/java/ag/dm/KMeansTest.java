package ag.dm;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.junit.Test;

import ag.dm.KMeans.Point2D;

public class KMeansTest {

	@Test
	public void test() {

		Point2D p0 = new Point2D(1, 1);
		Point2D p1 = new Point2D(2, 1);
		Point2D p2 = new Point2D(3, 1);
		Point2D p3 = new Point2D(1, 2);
		Point2D p4 = new Point2D(1, 3);
		Point2D p5 = new Point2D(-1, 1);
		Point2D p6 = new Point2D(1, -1);
		Point2D p7 = new Point2D(1.5, 1.5);

		Point2D p10 = new Point2D(100, 1);
		Point2D p11 = new Point2D(101, 1);
		Point2D p12 = new Point2D(103, 1);
		Point2D p13 = new Point2D(101, 2);
		Point2D p14 = new Point2D(101, 3);
		Point2D p15 = new Point2D(99, 1);
		Point2D p16 = new Point2D(101, -1);
		Point2D p17 = new Point2D(101.5, 1.5);

		ArrayList<Point2D> points = new ArrayList<>(Arrays.asList(p0, p1, p2, p3, p4, p5, p6, p7,
				p10, p11, p12, p13, p14, p15, p16, p17));

		List<List<Point2D>> result = KMeans.kMeans(points, 2, 1000000);

		System.out.println(result.get(0));
		System.out.println(result.get(1));
	}

}
