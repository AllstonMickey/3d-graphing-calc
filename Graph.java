import java.util.*;
import java.io.*;
import java.lang.Math;

/*
 * Written by Kai Ergin
 */

public class Graph{
	public static double placePara(Line check){
		double i = check.dVector[0]*check.dVector[0] + check.dVector[1]*check.dVector[1];
		double j = 2*check.dVector[0]*check.pVector[0] + 2*check.dVector[1]*check.pVector[1] - check.dVector[2];
		double k = check.pVector[0]*check.pVector[0] + check.pVector[1]*check.pVector[1] - check.pVector[2];
		if(j*j - 4*i*k<0){
			return -1;
		}
		double a = (-1*j+Math.sqrt(j*j - 4*i*k))/(2*i);
		double b = (-1*j-Math.sqrt(j*j - 4*i*k))/(2*i);
		//System.out.printf("%f %f\n",a,b);
		boolean aTrue = true;
		boolean bTrue = true;
		double t = .5;
		if(Math.abs(a*check.dVector[0] + check.pVector[0]) >= t || Math.abs(a*check.dVector[1] + check.pVector[1]) >= t || Math.abs(a*check.dVector[2] + check.pVector[2]) >= t){
			aTrue = false;
		}
		if(Math.abs(b*check.dVector[0] + check.pVector[0]) >= t || Math.abs(b*check.dVector[1] + check.pVector[1]) >= t || Math.abs(b*check.dVector[2] + check.pVector[2]) >= t){
			bTrue = false;
		}
		if(a<b){
			if(aTrue){
				return a;
			}
		}
		if(bTrue){
			return b;
		}
		if(aTrue){
			return a;
		}
		return -1;
	}
	// Uses cylinders instead of lines to graph axes
	public static double placeAxis(Line check, int r, int s){
		double a = check.dVector[r]*check.dVector[r] + check.dVector[s]*check.dVector[s];
		double b = 2*check.dVector[r]*check.pVector[r] + 2*check.dVector[s]*check.pVector[s];
		double c = check.pVector[r]*check.pVector[r] + check.pVector[s]*check.pVector[s] - .00001;
		if(b*b - 4*a*c<0){
			return -2;
		}
		double e = (-1*b-Math.sqrt(b*b - 4*a*c))/(2*a);
		double f = (-1*b+Math.sqrt(b*b - 4*a*c))/(2*a);
		boolean aTrue = true;
		boolean bTrue = true;
		double t = .5;
		if(Math.abs(e*check.dVector[0] + check.pVector[0]) >= t || Math.abs(e*check.dVector[1] + check.pVector[1]) >= t || Math.abs(e*check.dVector[2] + check.pVector[2]) >= t){
			aTrue = false;
		}
		if(Math.abs(f*check.dVector[0] + check.pVector[0]) >= t || Math.abs(f*check.dVector[1] + check.pVector[1]) >= t || Math.abs(f*check.dVector[2] + check.pVector[2]) >= t){
			bTrue = false;
		}
		if(a<b){
			if(aTrue){
				return e;
			}
		}
		if(bTrue){
			return f;
		}
		if(aTrue){
			return e;
		}
		return -2;
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
		if(!zside){
			normVector[0] = fx;
			normVector[1] = fy;
			normVector[2] = -1;
		}
		Line[][] vectors = new Line[500][500];
		for(int a=0;a<500;a++){
			for(int b=0;b<500;b++){
				double i = ((double)(a-250)/(double)(250 * Math.sqrt(1+fx*fx)))+x;
				double j = ((double)(b-250)/(double)(250 * Math.sqrt(1+fy*fy)))+y;
				double k = d + fx*i + fy*j;
				double[] arg = {i,j,k};
				vectors[a][b] = new Line(normVector,arg);
			}
		}
		return vectors;
	}
}