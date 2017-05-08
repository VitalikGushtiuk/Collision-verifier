package test;
/**
 *Geometric object Circle. 
 */
public class Circle extends GeometricObject{
	private double x1,y1;
	private double radius;
	/**
	 * Constructor with fields radius, x, y to create new circle
	 */
	public Circle( double radius, double x, double y) {
		this.x1 = x;
		this.y1 = y;
		this.radius = radius;
	}
	/**
	 * Verifies if point with coordinates (x,y) is
	 * inside of a circle.
	 */
	@Override
	public boolean verifPoint(double x, double y){
		return (Math.pow(x - x1, 2) + Math.pow(y - y1, 2) <= Math.pow(this.radius, 2));
	}
	/**
	 * Returns rectangle in which circle is framed.
	 */
	@Override
	public Rectangle getRectangle(){
		return new Rectangle(this.x1 - this.radius, this.y1 - this.radius,
									this.x1 + this.radius, this.y1 + this.radius);
	}
}
