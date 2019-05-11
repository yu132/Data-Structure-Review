package ag.dm;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map.Entry;
import java.util.Random;

public class KMeans {

	public static class Point2D {
		double	x;
		double	y;

		public Point2D(double x, double y) {
			super();
			this.x = x;
			this.y = y;
		}

		public double distance(Point2D p) {
			return Math.sqrt(Math.pow(x - p.x, 2) + Math.pow(y - p.y, 2));
		}

		@Override
		public String toString() {
			return "Point2D [x=" + x + ", y=" + y + "]";
		}

		@Override
		public int hashCode() {
			final int prime = 31;
			int result = 1;
			long temp;
			temp = Double.doubleToLongBits(x);
			result = prime * result + (int) (temp ^ (temp >>> 32));
			temp = Double.doubleToLongBits(y);
			result = prime * result + (int) (temp ^ (temp >>> 32));
			return result;
		}

		@Override
		public boolean equals(Object obj) {
			if (this == obj)
				return true;
			if (obj == null)
				return false;
			if (getClass() != obj.getClass())
				return false;
			Point2D other = (Point2D) obj;
			if (Double.compare(x, other.x) != 0)
				return false;
			if (Double.compare(y, other.y) != 0)
				return false;
			return true;
		}

	}

	private static int[] getRandomKPoint(int nums, int k) {//随机选取n个不重复的点

		Random r = new Random();

		int[] array = new int[nums];

		Arrays.setAll(array, i -> i);

		int[] res = new int[k];

		for (int i = 0; i < k; ++i) {
			res[i] = array[r.nextInt(nums - i)];
			array[res[i]] = array[nums - 1 - i];
		}

		return res;
	}

	/**
	 * 
	 * 聚类的核心算法
	 * 
	 * @param points		点集
	 * @param tempClusters	点和其对应的类别
	 * @param centerPoint	每个类别的中心点
	 * @return				中心点是否发生改变
	 */
	private static boolean cluster(List<Point2D> points, HashMap<Point2D, Integer> tempClusters,
			List<Point2D> centerPoint) {

		boolean flag = true;

		for (Point2D point : points) {//对每个点计算类别

			double minDis = Double.MAX_VALUE;
			int clusterIndex = -1;

			for (int i = 0; i < centerPoint.size(); ++i) {
				double temp = point.distance(centerPoint.get(i));

				if (Double.compare(temp, minDis) < 0) {//如果这个类的中心距离比较近
					minDis = temp;
					clusterIndex = i;//将最近的类别标记成这个类别
				}
			}

			tempClusters.put(point, clusterIndex);//修改类别种类
		}

		int[] xs = new int[centerPoint.size()];
		int[] ys = new int[centerPoint.size()];

		for (Entry<Point2D, Integer> entry : tempClusters.entrySet()) {//将类别中心的坐标相加
			xs[entry.getValue()] += entry.getKey().x;
			ys[entry.getValue()] += entry.getKey().y;
		}

		for (int i = 0; i < xs.length; ++i) {

			Point2D newCenter = new Point2D(xs[i] * 1.0 / xs.length, ys[i] * 1.0 / ys.length);//重新计算中心

			if (!newCenter.equals(centerPoint.get(i))) {//如果中心改变
				centerPoint.set(i, newCenter);
				flag = false;//标记中心已改变
			}

		}

		return flag;
	}

	/**
	 * kmeans实现
	 * 
	 * @param points	点集
	 * @param k			类别个数
	 * @param deep		迭代深度
	 * @return			包含多个簇的集合
	 */
	public static List<List<Point2D>> kMeans(List<Point2D> points, int k, int deep) {

		int[] indexes = getRandomKPoint(points.size(), k);//随机选取k个类别中心

		HashMap<Point2D, Integer> tempClusters = new HashMap<>();

		for (int i = 0; i < points.size(); ++i) {
			tempClusters.put(points.get(i), -1);//将点放进临时的类别记录的数据结构中
		}

		List<Point2D> centerPoint = new ArrayList<>();

		List<List<Point2D>> clusters = new ArrayList<>();

		for (int i = 0; i < indexes.length; ++i) {
			clusters.add(new ArrayList<>());
			centerPoint.add(points.get(indexes[i]));//将类别中心初始化
		}

		while (deep-- != 0) {//聚类，直到中心不改变或到达迭代的深度上限为止
			if (cluster(points, tempClusters, centerPoint))
				break;
		}

		for (Point2D point : points) {//将点从临时的数据结构中收集出来
			clusters.get(tempClusters.get(point)).add(point);
		}

		return clusters;
	}
}
