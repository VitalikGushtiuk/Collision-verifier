package test;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.InputStreamReader;
import java.io.IOException;
import java.io.FileNotFoundException;

/**
 *Class starts to read commands from text file, tests quad tree,
 *and writes output in other text file.
 *@throws InputNotFound exception if file can not be found
 *@throws IOException if file can not be open 
 */
public class Main {
	public static void main(String [] args){
		BufferedWriter writer = null;
		BufferedReader reader = null;
		FileInputStream is = null;
		String []buffer = null;
		String Line = null;
		try{
			is = new FileInputStream("quadtree.in");
			writer = new BufferedWriter(new FileWriter("quadtree.out"));
			reader = new BufferedReader(new InputStreamReader(is));
			Line = reader.readLine();
		}catch(FileNotFoundException e){
			System.out.println("File not found");
		}catch(IOException e){
			System.out.println("Invalid File");
		}
		buffer = Line.split("\\s+");
		QTree q = new QTree(new Rectangle(Integer.parseInt(buffer[0]),Integer.parseInt(buffer[1]),
										  Integer.parseInt(buffer[2]),Integer.parseInt(buffer[3])));
		try{
			while((Line = reader.readLine()) != null){
				buffer = Line.split("\\s+");
				
				if (buffer[0].equals("11")){
					if(buffer[1].equals("1")){
						q.Insert(new Rectangle(Double.parseDouble(buffer[3]), Double.parseDouble(buffer[4]),
										   Double.parseDouble(buffer[5]), Double.parseDouble(buffer[6])),
										   Integer.parseInt(buffer[2]));
					}
					else if( buffer[1].equals("2")){
						q.Insert(new Triangle(Double.parseDouble(buffer[3]), Double.parseDouble(buffer[4]),
											Double.parseDouble(buffer[5]), Double.parseDouble(buffer[6]),
											Double.parseDouble(buffer[7]), Double.parseDouble(buffer[8])),
											Integer.parseInt(buffer[2]));
					}
					else if( buffer[1].equals("3")){
						q.Insert(new Circle(Double.parseDouble(buffer[3]), Double.parseDouble(buffer[4]),
											Double.parseDouble(buffer[5])), Integer.parseInt(buffer[2]));
					}
					else if( buffer[1].equals("4")){
						q.Insert(new Diamond(Double.parseDouble(buffer[3]), Double.parseDouble(buffer[4]),
										Double.parseDouble(buffer[5]), Double.parseDouble(buffer[6]),
										Double.parseDouble(buffer[7]), Double.parseDouble(buffer[8]),
										Double.parseDouble(buffer[9]), Double.parseDouble(buffer[10])),
										Integer.parseInt(buffer[2]));
					}
				}
				else if(buffer[0].equals("22"))
					q.Delete(Integer.parseInt(buffer[1]));
				else if(buffer[0].equals("33"))
					q.Detect_P(Double.parseDouble(buffer[1]), Double.parseDouble(buffer[2]), writer);
				else if(buffer[0].equals("44")){
					if(buffer[1].equals("1"))
						q.Detect_F(new Rectangle(Double.parseDouble(buffer[2]), Double.parseDouble(buffer[3]),
										   Double.parseDouble(buffer[4]), Double.parseDouble(buffer[5])),
										   writer);
					else if(buffer[1].equals("2"))
						q.Detect_F(new Triangle(Double.parseDouble(buffer[2]), Double.parseDouble(buffer[3]),
											Double.parseDouble(buffer[4]), Double.parseDouble(buffer[5]),
											Double.parseDouble(buffer[6]), Double.parseDouble(buffer[7])),
											writer);
					else if(buffer[1].equals("3"))						
						q.Detect_F(new Circle(Double.parseDouble(buffer[2]), Double.parseDouble(buffer[3]),
											Double.parseDouble(buffer[4])), writer);
					
					else if(buffer[1].equals("4"))
						q.Detect_F(new Diamond(Double.parseDouble(buffer[2]), Double.parseDouble(buffer[3]),
										Double.parseDouble(buffer[4]), Double.parseDouble(buffer[5]),
										Double.parseDouble(buffer[6]), Double.parseDouble(buffer[7]),
										Double.parseDouble(buffer[8]), Double.parseDouble(buffer[9])),
										writer);
				}
			}
		}catch(IOException e){
			System.out.println("Unable to read from file");
		}finally{
			try{
				writer.close();
			}catch(IOException e){
				System.out.println("Unable to close file"); 
			}
		}
	}
}