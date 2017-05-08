package test;

/**
 *Geometric object triangle.  
 */
public class Triangle extends GeometricObject{
	private double x1,y1,x2,y2,x3,y3;

	public Triangle(double x1, double y1, double x2, double y2, double x3, double y3) {
		super();
		this.x1 = x1;this.y1 = y1;
		this.x2 = x2;this.y2 = y2;
		this.x3 = x3;this.y3 = y3;
	}
	@Override
	public boolean verifPoint(double x, double y){
		double ABC = Math.abs (x1 * (y2 - y3) + x2 * (y3 - y1) + x3 * (y1 - y2));
		double ABP = Math.abs (x1 * (y2 - y) + x2 * (y - y1) + x * (y1 - y2));
		double APC = Math.abs (x1 * (y - y3) + x * (y3 - y1) + x3 * (y1 - y));
		double PBC = Math.abs (x * (y2 - y3) + x2 * (y3 - y) + x3 * (y - y2));
		return (ABP + APC + PBC == ABC);
	}
	@Override
	public Rectangle getRectangle(){
		if(x1 < x2)
			return new Rectangle(this.x1, Math.min(this.y2, this.y3), 
								this.x3, this.y1);
		else if(x1 > x3)
			return new Rectangle(this.x2, Math.min(this.y2, this.y3),
								this.x1, this.y1);
		else 
			return new Rectangle(this.x2, this.y2,
								this.x3, this.y1);
	}
}
