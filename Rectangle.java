package test;

/**
 *Geometric object Rectangle.  
 */
public class Rectangle extends GeometricObject{
	private double x1,x2,y1,y2;
	
	public Rectangle(){};
	/**
	 * Constructor with fields x1, y1, x2, y2 to create new Rectangle
	 */
	public Rectangle(double x1, double y1, double x2, double y2) {
		this.x1 = x1;
		this.x2 = x2;
		this.y1 = y1;
		this.y2 = y2;
	}
	/**
	 * Returns rectangle.
	 */
	@Override
	public Rectangle getRectangle(){
		return this;
	}
	/**
	 * Verifies if point with coordinates (x,y) is
	 * inside of a Rectangle.
	 */
	@Override
	public boolean verifPoint(double x,double y){
		return (this.x1 <= x && this.x2 >= x && this.y1 <= y && this.y2 >= y);
	}
	public double getX1() {
		return x1;
	}
	public double getX2() {
		return x2;
	}
	public double getY1() {
		return y1;
	}
	public double getY2() {
		return y2;
	}
}
