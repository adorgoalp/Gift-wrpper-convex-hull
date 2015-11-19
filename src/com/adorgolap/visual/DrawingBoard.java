package com.adorgolap.visual;

import java.awt.Color;
import java.awt.Graphics;
import java.util.ArrayList;
import javax.swing.JPanel;

import com.adorgolap.giftwrapper.Main;
import com.adorgolap.helper.Point;

@SuppressWarnings("serial")
public class DrawingBoard extends JPanel{
	ArrayList<Point> allPoints = new ArrayList<Point>();
	ArrayList<Point> hull = new ArrayList<Point>();
	Point p1,p2,maxPoint,minPoint,distantPoint;
	public DrawingBoard(ArrayList<Point> allPoints , ArrayList<Point> convexHull) {
		this.allPoints.addAll(allPoints);
		this.hull.addAll(convexHull);
	}
	
	public DrawingBoard(ArrayList<Point> allPoints2, ArrayList<Point> convexHull, Point maxPoint2,Point minPoint2, Point distantPoint) {
		this.allPoints.addAll(allPoints2);
		this.hull.addAll(convexHull);
		this.maxPoint = maxPoint2;
		this.minPoint = minPoint2;
		this.distantPoint = distantPoint;
	}

	@Override
	protected void paintComponent(Graphics g) {
		drawGrid(g);
		g.setColor(Color.ORANGE);

		for(Point p : allPoints)
		{
			g.fillOval((int)p.x-3, Main.HEIGHT- ((int)p.y+3), 6, 6);
//			g.drawString(p.toString(), p.x-2, p.y);
		}
		g.setColor(Color.RED);
		for(int i = 0 ; i < hull.size() - 1 ;i++)
		{
			p1 = hull.get(i);
			p2 = hull.get(i+1);
			g.drawLine((int)p1.x,Main.HEIGHT- (int)p1.y,(int)p2.x,Main.HEIGHT- (int)p2.y);
		}
		if(maxPoint != null)
		{
			g.setColor(Color.MAGENTA);
			g.drawOval((int)maxPoint.x-4, Main.HEIGHT - (int)maxPoint.y-4, 8, 8);
		}
		if(minPoint != null)
		{
			g.setColor(Color.MAGENTA);
			g.drawOval((int)minPoint.x-4, Main.HEIGHT - (int)minPoint.y-4, 8, 8);
		}
		if(distantPoint != null)
		{
			g.setColor(Color.MAGENTA);
			g.drawOval((int)distantPoint.x-4, Main.HEIGHT - (int)distantPoint.y-4, 8, 8);
		}else
		{
			System.out.println("ops");
		}
	}

	private void drawGrid(Graphics g) {
		
		for(int i = 0 ; i < Main.HEIGHT;i = i + 50)
		{
			g.setColor(Color.DARK_GRAY);
			g.drawLine(i, 0, i, Main.HEIGHT-50);
			g.drawLine(50, i, Main.WIDTH, i);
			g.setColor(Color.WHITE);
			g.drawString(i+"", i-10, Main.HEIGHT-30);
			g.drawString(i+"", 20, Main.HEIGHT-i);
		}
	}
}
