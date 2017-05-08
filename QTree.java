package test;

import java.util.LinkedList;
import java.util.Collections;
import java.io.IOException;
import java.io.BufferedWriter;

/**
 *Quad tree data structure.
 *@see
 *Basic operations:
 *Insert(Geometric Object, ID number);
 *Delete(ID Number);
 *Detect_P(x,y);
 *Detect_F(Geometric Object);
 */
public class QTree implements QuadTree {
	private Node root;
	private Rectangle screen;
	private int E;
	
	public QTree() {
		super();
		this.root = new Node();
		this.screen = new Rectangle();
		this.E = 10;
	}
	
	public QTree(Rectangle screen) {
		super();
		this.root = new Node();
		this.screen = screen;
		this.E = 10;
	}
	 class Node{
			private LinkedList< GeometricObject > figures;
			private LinkedList< Integer > storeID;
			private Node NE, NW, SE, SW;
			
			public Node(){
				this.figures = new LinkedList<GeometricObject>();
				this.storeID = new LinkedList<Integer>();
			}
			
			public Node(GeometricObject figure, int ID) {
				this.figures = new LinkedList<GeometricObject>();
				this.storeID = new LinkedList<Integer>();
				this.figures.add(figure);
				this.storeID.add(ID);
			}
			
			public void addNode(GeometricObject figure, int ID){
				this.figures.add(figure);
				this.storeID.add(ID);
			}
			
	}
	/**
	 * Method inserts geometric object in the quad tree.
	 * @param f Figure to be inserted.
	 * @param ID ID number of a figure.
	 */
	public void Insert(GeometricObject f,int ID){
		root = Insert(root, f, ID, screen.getX1(), screen.getY1(), screen.getX2(), screen.getY2(), 0);
	}
	private Node Insert(Node n, GeometricObject f, int ID,double x1,double y1,double x2,double y2, int lvl){
		if( isLeaf(n) && n.figures.isEmpty() ) {
			return new Node(f,ID);
		}
		if( lvl > this.E ){
			n.addNode(f,ID);
			return n;
		}
		if( isLeaf(n) && !n.figures.isEmpty()){
			for(int i = 0;i < n.figures.size(); i++){
				if( !f.getRectangle().intersects(n.figures.get(i).getRectangle()) )
					break;
				if(i == n.figures.size() - 1 ){
					n.addNode(f, ID);
					return n;
				}
			}
			n.NE = new Node();
			n.NW = new Node();
			n.SE = new Node();
			n.SW = new Node();
			GeometricObject buf;
			int buf_id;
			while( !n.figures.isEmpty() ){
				buf = n.figures.removeFirst();
				buf_id = n.storeID.removeFirst();
				Insert(n, buf, buf_id, x1, y1, x2, y2,lvl );
			}
		}
		lvl ++;
		
		if( f.getRectangle().intersects((x1+x2)/2, (y1+y2)/2, x2, y2))
			n.NE = Insert( n.NE, f, ID, (x1+x2)/2, (y1+y2)/2, x2, y2, lvl);
		if( f.getRectangle().intersects( x1, (y1+y2)/2, (x1+x2)/2, y2))
			n.NW = Insert( n.NW, f, ID, x1, (y1+y2)/2, (x1+x2)/2, y2,lvl);
		if( f.getRectangle().intersects( x1, y1, (x1+x2)/2, (y1+y2)/2))
			n.SW = Insert( n.SW, f, ID, x1, y1, (x1+x2)/2, (y1+y2)/2,lvl);
		if( f.getRectangle().intersects((x1+x2)/2 , y1, x2, (y1+y2)/2))
			n.SE = Insert( n.SE, f, ID, (x1+x2)/2 , y1, x2, (y1+y2)/2,lvl);
		
		return n;
	}
	/**
	 * Verifies if node of quad tree is a leaf.
	 * @param n Node to be verified.
	 * @return true if node is leaf, null otherwise.
	 */
	public boolean isLeaf(Node n){
		return n.NE == null && n.NW == null && n.SE == null && n.SW == null;
	}
	/**
	 * Method deletes geometric object with ID number from the quad tree.
	 * @param ID ID number of a figure to be deleted.
	 */
	public void Delete(int ID){
		GeometricObject figure = this.Search(ID);
		Delete(this.root,figure, this.screen.getX1(), this.screen.getY1(), 
			   this.screen.getX2(), this.screen.getY2());
	}
	private void Delete(Node n, GeometricObject f, double x1, double y1, double x2, double y2){
		if( !n.equals(null) )
			for(int i = 0; i < n.storeID.size(); i++){
				if(n.figures.get(i).equals(f)){
					n.storeID.remove(i);
					n.figures.remove(i);
				}
			}
		
		if( isLeaf(n) )
			return;
		
		if( f.getRectangle().intersects((x1+x2)/2, (y1+y2)/2, x2, y2))
			Delete( n.NE, f, (x1+x2)/2, (y1+y2)/2, x2, y2);
		if( f.getRectangle().intersects( x1, (y1+y2)/2, (x1+x2)/2, y2))
			Delete( n.NW, f, x1, (y1+y2)/2, (x1+x2)/2, y2);
		if( f.getRectangle().intersects( x1, y1, (x1+x2)/2, (y1+y2)/2))
			Delete( n.SW, f, x1, y1, (x1+x2)/2, (y1+y2)/2);
		if( f.getRectangle().intersects((x1+x2)/2 , y1, x2, (y1+y2)/2))
			Delete( n.SE, f, (x1+x2)/2 , y1, x2, (y1+y2)/2);
	}
	/**
	 * Method verifies if point with coordinates (x,y) has collision with
	 * figures in a quad tree.
	 * @param x X coordinate.
	 * @param y Y coordinate.
	 * @param writer BufferedWriter to output information in a file.
	 * @return LinkedList with stacked ID numbers of figures which have 
	 * collision with point (x,y) .
	 */
	public void Detect_P(double x, double y, BufferedWriter writer){
		LinkedList<Integer> l = new LinkedList<Integer>();
		
		Detect_P(this.root,l,this.screen.getX1(), this.screen.getY1(), 
					this.screen.getX2(),this.screen.getY2(), x, y);
		
		this.displayDetected(l,writer);
	}
	private void Detect_P(Node n, LinkedList<Integer> l, double x1, double y1, double x2, double y2, double x, double y){
		if( !n.figures.isEmpty() ){
			for(int i = 0;i < n.figures.size(); i++){
				if(n.figures.get(i).verifPoint(x, y) == true && !l.contains(n.storeID.get(i))){
					l.add(n.storeID.get(i));
				}
			}
		}
		else if (  isLeaf(n) )
			return;
		else{
			if( (x1+x2)/2 <= x && x2 >= x && (y1+y2)/2 <= y && y2 >= y)
				Detect_P(n.NE, l, (x1+x2)/2, (y1+y2)/2, x2, y2, x, y);
			if( x1 <= x && (x1+x2)/2 >= x && (y1+y2)/2 <= y && y2 >= y)
				Detect_P(n.NW, l, x1, (y1+y2)/2, (x1+x2)/2, y2, x, y);
			if(x1 <= x && (x1+x2)/2 >= x&& y1 <= y && (y1+y2)/2 >= y)
				Detect_P(n.SW, l, x1, y1, (x1+x2)/2, (y1+y2)/2, x, y);
			if((x1+x2)/2 <= x && x2 >= x && y1 <= y && (y1+y2)/2 >= y)
				Detect_P(n.SE, l, (x1+x2)/2, y1, x2, (y1+y2)/2, x, y);
		}
	}
	/**
	 * Method solves detection of geometric object f given as a parameter,
	 * and objects in a quad tree.
	 * @param f Geometric Object for which collision shell be checked.
	 * @param writer BufferedWriter to output information in a file.
	 * @return LinkedList with stacked ID numbers of figures which intersect 
	 * figure f. 
	 */
	public void Detect_F(GeometricObject f, BufferedWriter writer){
		LinkedList<Integer> l = new LinkedList<Integer>();
		Detect_F(this.root, f, l,this.screen.getX1(), this.screen.getY1(), 
					this.screen.getX2(),this.screen.getY2());
		this.displayDetected(l,writer);
	}
	private void Detect_F(Node n, GeometricObject f, LinkedList<Integer> l, double x1, double y1, double x2, double y2){
		if( isLeaf(n) && !n.equals(null) ){
			for(int i = 0; i < n.figures.size(); i++){
				if( f.intersects(n.figures.get(i).getRectangle()) && !l.contains(n.storeID.get(i))){
					l.add( n.storeID.get(i) );
				}
			}
			return;
		}
		if( f.getRectangle().intersects((x1+x2)/2, (y1+y2)/2, x2, y2))
			Detect_F( n.NE, f, l, (x1+x2)/2, (y1+y2)/2, x2, y2);
		if( f.getRectangle().intersects( x1, (y1+y2)/2, (x1+x2)/2, y2))
			Detect_F( n.NW, f, l, x1, (y1+y2)/2, (x1+x2)/2, y2);
		if( f.getRectangle().intersects( x1, y1, (x1+x2)/2, (y1+y2)/2))
			Detect_F( n.SW, f, l, x1, y1, (x1+x2)/2, (y1+y2)/2);
		if( f.getRectangle().intersects((x1+x2)/2 , y1, x2, (y1+y2)/2))
			Detect_F( n.SE, f, l, (x1+x2)/2 , y1, x2, (y1+y2)/2);
	}
	/**
	 * Displays ID numbers of figures in which collision was detected by 
	 * methods Detect_F and Detect_P sorted in ascending order. 
	 * @param list Linked List in which id was stacked.
	 * @param writer BufferedWriter to output information in a file.
	 */
	public void displayDetected(LinkedList<Integer> list, BufferedWriter writer){
		try{
			if( list.isEmpty() ){
				writer.write("NIL");
			}
			else{
				Collections.sort(list);
				while(!list.isEmpty()){
					writer.write(String.valueOf(list.pollFirst()));
					if( list.size() != 0)
						writer.write(" ");
				}
			}
			writer.write('\n');
		}catch(IOException e){
			System.out.println("Unable to write to file.");
		}
	}
	/**
	 * Searches for Geometric Object with ID given like parameter in a quad tree. 
	 * @param ID ID number of a figure to be found.
	 * @return Figure with given ID or null.
	 */
	public GeometricObject Search(int ID){
		GeometricObject f = null;
		f = Search(this.root, f, ID);
		return f;
	}
	private GeometricObject Search(Node node, GeometricObject f, int ID){
		if( !node.equals(null) )
			for(int i = 0; i < node.storeID.size(); i++){
				if(node.storeID.get(i) == ID){
					return node.figures.get(i);
				}
			}
		
		if( isLeaf(node) )
			return null;
		
		f = Search( node.NE, f, ID );
		if(f != null)return f;
		f = Search( node.NW, f, ID );
		if(f != null)return f;
		f = Search( node.SW, f, ID );
		if(f != null)return f;
		f = Search( node.SE, f, ID );
		if(f != null)return f;
		return f;
	}
}