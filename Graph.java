import java.util.*;
import java.io.*;
import java.lang.Math;

/*
 * Written by Kai Ergin
 * Last updated: 10/21/2016
 */

class Graph{
	public static void main(String[] args){
		double x = Double.parseDouble(args[0]);
		double y = Double.parseDouble(args[1]);
		boolean zside = Boolean.parseBoolean(args[2]);
		Line[][] myLines = genScreen(x,y,zside);
	}
	public static boolean placeAxis(Line check){
		double a = -1*check.pVector[0]/check.dVector[0];
		double b = -1*check.pVector[1]/check.dVector[1];
		double c = -1*check.pVector[2]/check.dVector[2];
		if(Math.abs(a-b)<=.002 && Math.abs(a)<=2 && Math.abs(b)<=2){
			return true;
		} else if(Math.abs(b-c)<=.002 && Math.abs(b)<=2 && Math.abs(c)<=2){
			return true;
		} else if(Math.abs(c-a)<=.002 && Math.abs(a)<=2 && Math.abs(c)<=2){
			return true;
		}
		return false;
	}
	public static Line[][] genScreen(double x, double y, boolean zside){
		if(Math.sqrt(x*x+y*y)>=1){
			System.out.println("Error in your numbers");
			System.exit(0);
		}
		double z = Math.sqrt(1-x*x-y*y);
		double fx = -x/Math.sqrt(1-x*x-y*y);
		double fy = -y/Math.sqrt(1-x*x-y*y);
		if(zside){
			z*=-1;
			fx*=-1;
			fy*=-1;
			System.out.println("You chose the lower half of the sphere");
		}
		double d = (-1)*fx*x + (-1)*fy*y + z;
		double[] normVector = {-fx,-fy,1};
		Line[][] vectors = new Line[500][500];
		int count = 0;
		for(int a=0;a<500;a++){
			for(int b=0;b<500;b++){
				double i = ((double)(a-250)/(double)(250 * Math.sqrt(1+fx*fx)))+x;
				double j = ((double)(b-250)/(double)(250 * Math.sqrt(1+fy*fy)))+y;
				double k = d + fx*i + fy*j;
				double[] arg = {i,j,k};
				vectors[a][b] = new Line(normVector,arg);
				//System.out.println(vectors[a][b].pVector[0] + " " + vectors[a][b].pVector[1] + " " + vectors[a][b].pVector[2]);
				if(placeAxis(vectors[a][b])){
					count++;
					System.out.println(vectors[a][b].pVector[0] + " " + vectors[a][b].pVector[1] + " " + vectors[a][b].pVector[2]);
					System.out.println(a + " " + b);
				}
			}
		}
		System.out.println(vectors[0][0].dVector[0] + " " + vectors[0][0].dVector[1] + " " + vectors[0][0].dVector[2]);
		System.out.println(count);
		return vectors;
	}
}