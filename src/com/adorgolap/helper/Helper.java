package com.adorgolap.helper;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.UnsupportedEncodingException;
import java.io.Writer;
import java.util.ArrayList;
import java.util.Random;


public class Helper {
	public static ArrayList<Point> takeInput() {
		ArrayList<Point> points = new ArrayList<Point>();
		BufferedReader br = null;
		try {
			String line;
			br = new BufferedReader(new FileReader("input.txt"));
			while ((line = br.readLine()) != null) {
				String[] t = line.split(",");
				double x = Double.parseDouble(t[0]);
				double y = Double.parseDouble(t[1]);
				Point v = new Point(x, y);
				points.add(v);
			}
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				if (br != null)
					br.close();
			} catch (IOException ex) {
				ex.printStackTrace();
			}
		}
		return points;
	}

	public static void createInput(int n) {
		Random random = new Random();
		try (Writer writer = new BufferedWriter(
			new OutputStreamWriter(new FileOutputStream("input.txt"), "utf-8"))) {
			for(int i = 0 ; i < n;i++)
			{
				double x = 50+random.nextDouble()*500;
				double y = 50+random.nextDouble()*500;
				writer.write(x+","+y+"\n");
			}
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	public static double getAngle(Point A, Point B,Point C) {
		double a = B.getDistance(C);
		double b = A.getDistance(C);
		double c = A.getDistance(B);
		double cosC = (a*a+b*b-c*c)/(2*a*b);
		return Math.acos(cosC);
	}
}
