package test;
import java.io.BufferedWriter;
/**
 * Data structure for storing geometric objects and realizing operations on them. 
 * @author Vitalii Gushtiuk.
 */
public interface QuadTree {
	public void Insert(GeometricObject f,int ID1);
	public void Delete(int ID);
	public void Detect_P(double x, double y, BufferedWriter writer);
	public void Detect_F(GeometricObject f, BufferedWriter writer);
}