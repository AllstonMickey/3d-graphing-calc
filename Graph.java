import java.util.*;
import java.io.*;
import java.lang.Math;

/*
 * Written by Kai Ergin
 */

public class Graph{
	public static boolean placeParabaloid(Line check){
		/*
		double i = check.dVector[0]*check.dVector[0] + check.dVector[1]*check.dVector[1];
		double j = 2*check.dVector[0]*check.pVector[0] + 2*check.dVector[1]*check.dVector[1] - check.dVector[2];
		double k = check.pVector[0]*check.pVector[0] + check.pVector[1]*check.pVector[1] - check.pVector[2];
		if(j*j - 4*i*k<0){
			return false;
		} else if(Math.abs((-1*j+Math.sqrt(j*j - 4*i*k))/(2*i))<=10 || Math.abs((-1*j-Math.sqrt(j*j - 4*i*k))/(2*i))<=10){
			return true;
		}*/
		return false;
	}
	public static boolean placeX(Line check){
		double a = -1*check.pVector[0]/check.dVector[0];
		double b = -1*check.pVector[1]/check.dVector[1];
		double c = -1*check.pVector[2]/check.dVector[2];
		if(Math.abs(b-c)<=.002/Math.sqrt(1+check.dVector[0]*check.dVector[0])/* && Math.abs(b)<=1.5 && Math.abs(c)<=1.5 && Math.abs(b)>=.5 && Math.abs(c)>=.5*/){
			return true;
		}
		return false;
	}
	public static boolean placeY(Line check){
		double a = -1*check.pVector[0]/check.dVector[0];
		double b = -1*check.pVector[1]/check.dVector[1];
		double c = -1*check.pVector[2]/check.dVector[2];
		if(Math.abs(c-a)<=.002/Math.sqrt(1+check.dVector[1]*check.dVector[1])/* && Math.abs(a)<=1.5 && Math.abs(c)<=1.5 && Math.abs(a)>=.5 && Math.abs(c)>=.5*/){
			return true;
		}
		return false;
	}
	public static boolean placeZ(Line check){
		double a = -1*check.pVector[0]/check.dVector[0];
		double b = -1*check.pVector[1]/check.dVector[1];
		double c = -1*check.pVector[2]/check.dVector[2];
		if(Math.abs(a-b)<=.002/Math.sqrt(1+check.dVector[2]*check.dVector[2])/* && Math.abs(a)<=1.5 && Math.abs(b)<=1.5 && Math.abs(a)>=.5 && Math.abs(c)>=.5*/){
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