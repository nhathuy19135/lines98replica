package Lines;

import java.awt.*;
import javax.swing.*;
import java.io.*;

public class Player{
	
	public String name;
	public int  scores;
	
	public Player(){
		this.name = "";
		this.scores=0;
	}
	public Player(String name,int scores){
		this.name = name;
		this.scores=scores;
	}
	public void setName(){
		 String a;
 		 a = JOptionPane.showInputDialog(" Your Name: ")+"";
 		 this.name=a;
	}	
	//Export player name
	public void writeData(DataOutput out)throws IOException{
		writeFixedString(this.name, 20 , out);
        out.writeInt(this.scores);

	}
	
	//Read player name
	public void readData( DataInput in)throws IOException{
		this.name = readFixedString(20, in);
        this.scores 	    = in.readInt();
      	
	}	
		//Import player name
	public  String readFixedString(int size, DataInput in)
	  	throws IOException{
    	
    	StringBuilder b = new StringBuilder(size);
      	int i = 0;
      	boolean more = true;
      	while (more && i < size){
        	char ch = in.readChar();
         	i++;
         	if (ch == 0)
         		more = false;
         	else b.append(ch);
      	}
      	
      	in.skipBytes(2 * (size - i));
      	return b.toString();
 	}
	
   	public  void writeFixedString(String s, int size, DataOutput out)
      throws IOException{
      	int i;
      	for (i = 0; i < size; i++){
        	char ch = 0;
         	if (i < s.length()) ch = s.charAt(i);
         	out.writeChar(ch);
      	}
   	}
}