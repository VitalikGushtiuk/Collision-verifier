package test;
/**
 *Geometric object diamond.  
 */
public class Diamond extends GeometricObject {
	private double x1,x2,x3,x4,y1,y2,y3,y4;
	/**
	 * Constructor with fields x1,y1,x2,y2,x3,y3,x4,y4 representing coordinates 
	 * of new diamond.
	 */
	public Diamond( double x1, double y1, double x2, double y2, double x3, double y3, double x4, double y4) {
		this.x1 = x1;this.y1 = y1;
		this.x2 = x2;this.y2 = y2;
		this.x3 = x3;this.y3 = y3;
		this.x4 = x4;this.y4 = y4;
	}
	/**
	 * Verifies if point with coordinates (x,y) is
	 * inside of a diamond by checking two triangles.
	 */
	@Override
	public boolean verifPoint(double x, double y){
		return verifyTriangle( this.x1, this.y1, this.x2, this.y2, this.x3, this.y3, x, y) ||
				verifyTriangle( this.x1, this.y1, this.x3, this.y3, this.x4, this.y4, x, y);
	}
	/**
	 * Returns rectangle in which diamond is framed.
	 */
	@Override
	public Rectangle getRectangle(){
		return new Rectangle( Math.min(Math.min(x1, x2),Math.min(x3, x4)),
				Math.min(Math.min(y1, y2),Math.min(y3, y4)),
				Math.max(Math.max(x1, x2),Math.max(x3, x4)),
				Math.max(Math.max(y1, y2),Math.max(y3, y4)));
	}
	/**
	 * Verifies if point (x,y) is inside of triangle
	 * @return
	 */
	boolean verifyTriangle(double x1,double y1,double x2,double y2,double x3,double y3,double x, double y){
		double ABC = Math.abs (x1 * (y2 - y3) + x2 * (y3 - y1) + x3 * (y1 - y2));
		double ABP = Math.abs (x1 * (y2 - y) + x2 * (y - y1) + x * (y1 - y2));
		double APC = Math.abs (x1 * (y - y3) + x * (y3 - y1) + x3 * (y1 - y));
		double PBC = Math.abs (x * (y2 - y3) + x2 * (y3 - y) + x3 * (y - y2));
		return (ABP + APC + PBC == ABC);
	}
}
