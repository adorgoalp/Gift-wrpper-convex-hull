package com.adorgolap.helper;

public class Point {
	public double x;
	public double y;

	public Point(double x, double y) {
		this.x = x;
		this.y = y;
	}
	public double getDistance(Point p)
	{
		return Math.sqrt((p.x-x)*(p.x-x) + (p.y-y)*(p.y-y));
	}
	@Override
	public String toString() {
		return "(" + x + ", " + y + ")";
	}
	public double getAngle(Point A, Point B) {
		Point C = this;
		double a = B.getDistance(C);
		double b = A.getDistance(C);
		double c = A.getDistance(B);
		double cosC = (a*a+b*b-c*c)/(2*a*b);
		return Math.acos(cosC);
	}
	@Override
	public boolean equals(Object obj) {
		
		if (obj == null) {
	        return false;
	    }
	    if (getClass() != obj.getClass()) {
	        return false;
	    }
	    final Point o = (Point) obj;
	    if(this.x == o.x && this.y == o.y)
	    {
	    	return true;
	    }
	    return false;
	}
}
