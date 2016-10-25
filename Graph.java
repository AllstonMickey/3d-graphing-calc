import java.util.*;
import java.io.*;
import java.lang.Math;

/*
 * Written by Kai Ergin
 */

public class Graph{
	public static boolean placePara(Line check){
		double i = check.dVector[0]*check.dVector[0] + check.dVector[1]*check.dVector[1];
		double j = 2*check.dVector[0]*check.pVector[0] + 2*check.dVector[1]*check.pVector[1] - check.dVector[2];
		double k = check.pVector[0]*check.pVector[0] + check.pVector[1]*check.pVector[1] - check.pVector[2];
		if(j*j - 4*i*k<0){
			return false;
		} else if(Math.abs((-1*j+Math.sqrt(j*j - 4*i*k))/(2*i))<=1 || Math.abs((-1*j-Math.sqrt(j*j - 4*i*k))/(2*i))<=1){
			return true;
		}
		return false;
	}
	// Uses cylinders instead of lines to graph axes
	public static boolean placeX(Line check){
		double a = check.dVector[1]*check.dVector[1] + check.dVector[2]*check.dVector[2];
		double b = 2*check.dVector[1]*check.pVector[1] + 2*check.dVector[2]*check.pVector[2];
		double c = check.pVector[1]*check.pVector[1] + check.pVector[2]*check.pVector[2] - .00001;
		if(b*b - 4*a*c > 0){
			return true;
		}
		return false;
	}
	public static boolean placeY(Line check){
		double a = check.dVector[0]*check.dVector[0] + check.dVector[2]*check.dVector[2];
		double b = 2*check.dVector[0]*check.pVector[0] + 2*check.dVector[2]*check.pVector[2];
		double c = check.pVector[0]*check.pVector[0] + check.pVector[2]*check.pVector[2] - .00001;
		if(b*b - 4*a*c > 0){
			return true;
		}
		return false;
	}
	public static boolean placeZ(Line check){
		double a = check.dVector[0]*check.dVector[0] + check.dVector[1]*check.dVector[1];
		double b = 2*check.dVector[0]*check.pVector[0] + 2*check.dVector[1]*check.pVector[1];
		double c = check.pVector[0]*check.pVector[0] + check.pVector[1]*check.pVector[1] - .00001;
		if(b*b - 4*a*c > 0){
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
		for(int a=0;a<500;a++){
			for(int b=0;b<500;b++){
				double i = ((double)(a-250)/(double)(500 * Math.sqrt(1+fx*fx)))+x;
				double j = ((double)(b-250)/(double)(500 * Math.sqrt(1+fy*fy)))+y;
				double k = d + fx*i + fy*j;
				double[] arg = {i,j,k};
				vectors[a][b] = new Line(normVector,arg);
			}
		}
		return vectors;
	}
}