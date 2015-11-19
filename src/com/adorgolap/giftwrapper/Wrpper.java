package com.adorgolap.giftwrapper;

import java.util.ArrayList;

import com.adorgolap.helper.Helper;
import com.adorgolap.helper.Point;

public class Wrpper {
	ArrayList<Point> points = new ArrayList<Point>();
	ArrayList<Point> hull = new ArrayList<>();
	Point rightMaxPoint = null, leftMaxPoint = null, distantPoint;

	public Wrpper() {
		// Helper.createInput(Main.INPUT_SIZE);
		wrapTheGift();
	}

	public void wrapTheGift() {
		points.clear();
		hull.clear();
		points = Helper.takeInput();
		System.out.println(points);
		Point lowestExtremePoint = findLowestRightmostExtremePoint(points);
		Point anotherPointOnHorizontalLine = new Point(lowestExtremePoint.x + 1, lowestExtremePoint.y);
		double minAngle = Double.MAX_VALUE;

		hull.add(lowestExtremePoint);
		Point nextPoint = null;
		for (Point p : points) {
			if (!p.equals(lowestExtremePoint)) {
				// C.getAngle(A,B) returns <C
				double angle = Helper.getAngle(p, anotherPointOnHorizontalLine, lowestExtremePoint);
				if (angle < minAngle) {
					minAngle = angle;
					nextPoint = p;
				}
			}
		}
		hull.add(nextPoint);
		Point prevPoint = lowestExtremePoint;
		while (!nextPoint.equals(lowestExtremePoint)) {
			points.removeAll(hull);
			points.add(lowestExtremePoint);
			double maxAngle = Double.MIN_VALUE;
			Point minPoint = null;
			if (points.isEmpty())
				break;
			for (Point p : points) {
				// C.getAngle(A,B) returns <C
				double angle = Helper.getAngle(prevPoint, p, nextPoint);
				// System.out.println("A="+prevPoint+" B="+p+" C=" + nextPoint+"
				// angle = "+angle);
				if (angle > maxAngle) {
					maxAngle = angle;
					minPoint = p;
				}
			}
			hull.add(minPoint);
			prevPoint = nextPoint;
			nextPoint = minPoint;
			// System.out.println(nextPoint);
		}
		System.out.println(hull);
		findRect(hull.get(hull.size() - 2), hull.get(hull.size() - 1));
	}

	private void findRect(Point p1, Point p2) {
		System.out.println(p1 + " " + p2);
		double rightMaxProjection = Double.MIN_VALUE;
		double leftMaxProjection = Double.MIN_VALUE;
		double maxAreaOfTriangle = Double.MIN_VALUE;
		for (Point p : hull) {
			// findRight Max
			double rightAngle = Helper.getAngle(p2, p, p1);
			double rightDistance = p1.getDistance(p);
			double rightProjection = rightDistance * Math.cos(rightAngle);

			if (rightProjection > rightMaxProjection) {
				rightMaxPoint = p;
				rightMaxProjection = rightProjection;
			}
			// findLeftMax
			double leftAngle = Helper.getAngle(p1, p, p2);
			double leftDistance = p2.getDistance(p);
			double leftProjection = leftDistance * Math.cos(leftAngle);

			if (leftProjection > leftMaxProjection) {
				leftMaxPoint = p;
				leftMaxProjection = leftProjection;
			}
			// find distant point
			if (!p1.equals(p) && !p2.equals(p)) {
				double areaOfTriangle = 0.5 * Math.sin(rightAngle) * p1.getDistance(p2) * rightDistance;
				System.out.println(areaOfTriangle);
				if (areaOfTriangle > maxAreaOfTriangle) {
					System.out.println(p);
					distantPoint = p;
					maxAreaOfTriangle = areaOfTriangle;
				}
			}
		}
		// System.out.println(maxPoint);
	}

	private Point findLowestRightmostExtremePoint(ArrayList<Point> pointsArray) {
		ArrayList<Point> lowestPoints = new ArrayList<>();
		double minY = Double.MAX_VALUE;
		for (Point p : pointsArray) {
			if (p.y < minY) {
				minY = p.y;
				lowestPoints.clear();
				lowestPoints.add(p);
			} else if (p.y == minY) {
				lowestPoints.add(p);
			}
		}
		int maxXindex = 0;
		double maxX = Double.MIN_VALUE;
		for (Point p : lowestPoints) {
			if (p.x > maxX) {
				maxX = p.x;
				maxXindex = lowestPoints.indexOf(p);
			}
		}
		return lowestPoints.get(maxXindex);
	}

	public ArrayList<Point> getAllPoints() {
		ArrayList<Point> all = new ArrayList<Point>(points);
		all.addAll(hull);
		return all;
	}

	public ArrayList<Point> getConvexHull() {
		// TODO Auto-generated method stub
		return hull;
	}

	public Point getRightMaxPoint() {
		// TODO Auto-generated method stub
		return rightMaxPoint;
	}

	public Point getLeftMaxPoint() {
		return leftMaxPoint;
	}

	public Point getDistantPoint() {
		return distantPoint;
	}
}
