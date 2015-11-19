package com.adorgolap.giftwrapper;

import javax.swing.JFrame;

import java.awt.Color;
import java.util.*;
import com.adorgolap.helper.Point;
import com.adorgolap.visual.DrawingBoard;

public class Main {
	public static final int INPUT_SIZE = 20;
	public static final int HEIGHT = 600;
	public static final int WIDTH = 600;
	public static Wrpper wrapper = new Wrpper();

	public static void main(String[] args) {
		ArrayList<Point> allPoints = wrapper.getAllPoints();
		ArrayList<Point> convexHull = wrapper.getConvexHull();
		Point rightPoint = wrapper.getRightMaxPoint();
		Point leftPoint = wrapper.getLeftMaxPoint();
		Point distantPoint = wrapper.getDistantPoint();
		System.out.println(distantPoint);
		DrawingBoard drawingBoard = new DrawingBoard(allPoints, convexHull,rightPoint,leftPoint,distantPoint);

		JFrame frame = new JFrame("Convex Hull by Gift Wrapping Algorithm");
		frame.setBackground(Color.BLACK);
		frame.add(drawingBoard);
		frame.setSize(HEIGHT, WIDTH);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setLocationRelativeTo(null);
		frame.setVisible(true);

	}

}
