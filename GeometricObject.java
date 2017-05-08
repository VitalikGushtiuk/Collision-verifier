package test;
/**
 * abstract class Geometric object implemented to generalize geometric
 * figures and use them in a quad tree data structure.
 * @see
 * For adding more geometric objects in this package, just write your own 
 * class which extend Geometric Object and define methods of framing in 
 * rectangle and intersection with point for new figure. 
 */
public class GeometricObject {
	
	public GeometricObject() {};
	
	/**
	 * Verifies intersection between geometric object framed in a rectangle
	 * with rectangle given by points(x1,y1) (x2,y2)
	 * @return True if figures intersect, false otherwise. 
	 */
	public boolean intersects(double x1, double y1, double x2, double y2){
		Rectangle r = this.getRectangle();
		return r.getX1() <= x2 &&
			   r.getX2() >= x1 &&
			   r.getY1() <= y2 &&
			   r.getY2() >= y1 ;
	}
	/**
	 * Verifies intersection between geometric object framed in a rectangle
	 * with rectangle given as a parameter.
	 * @return True if figures intersect, false otherwise. 
	 */
	public boolean intersects(Rectangle r2){
		Rectangle r = this.getRectangle();
		if(r.equals(null) || r2.equals(null) )
			return false;
		if(r.getX1() <= r2.getX2() &&
		   r.getX2() >= r2.getX1() &&
		   r.getY1() <= r2.getY2() &&
		   r.getY2() >= r2.getY1() )
			return true;
		
		return false;
	}
	/**
	 * Verifies if point with coordinates (x,y) is
	 * inside of a geometric object.
	 */
	public boolean verifPoint(double x,double y){
		return false;
	}
	/**
	 * Returns rectangle in which geometric object is framed.
	 */
	public Rectangle getRectangle() {
		return null;
	}
}