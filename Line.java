public class Line{
	public double[] dVector = new double[3];
	public double[] pVector = new double[3];
	public Line(double[] direction, double[] point){
		System.arraycopy(direction,0,this.dVector,0,direction.length);
		System.arraycopy(point,0,this.pVector,0,point.length);
	}
}