

package Lines;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.io.*;

public class LineFrame extends JFrame{
	
		public Lineball a =new Lineball();
		public TopScores topScores = new TopScores();
		public Player player = new Player();
		public JFrame GameOver = new JFrame(" GameOver !");
		
		public Icon icon[] = new Icon[22];
		public JButton button[][] = new JButton[9][9];  // Display status
		public JMenuItem nextball [] = new JMenuItem[3];// Display balls that will appear
		public JMenuItem score=new JMenuItem("0  ");   	// Display score
		public  int x=-1,y=-1; 					
	
	// Create Frame	
  	public LineFrame(){
   
    	// Load 7 big balls
    	icon[1] = new ImageIcon("Images/big1.png");   
    	icon[2] = new ImageIcon("Images/big2.png");
    	icon[3] = new ImageIcon("Images/big3.png");
    	icon[4] = new ImageIcon("Images/big4.png");
    	icon[5] = new ImageIcon("Images/big5.png");
    	icon[6] = new ImageIcon("Images/big6.png");
    	icon[7] = new ImageIcon("Images/big7.png");
    
    	// Load 7 small balls
    	icon[8] = new ImageIcon("Images/small1.png");
    	icon[9] = new ImageIcon("Images/small2.png");
    	icon[10] = new ImageIcon("Images/small3.png");
    	icon[11] = new ImageIcon("Images/small4.png");
    	icon[12] = new ImageIcon("Images/small5.png");
    	icon[13] = new ImageIcon("Images/small6.png");
    	icon[14] = new ImageIcon("Images/small7.png");
    
    	// Load 7 moving balls
    	icon[15] = new ImageIcon("Images/d1.gif");
		icon[16] = new ImageIcon("Images/d2.gif");
		icon[17] = new ImageIcon("Images/d3.gif");
		icon[18] = new ImageIcon("Images/d4.gif");
		icon[19] = new ImageIcon("Images/d5.gif");
		icon[20] = new ImageIcon("Images/d6.gif");
		icon[21] = new ImageIcon("Images/d7.gif");
	
		for (int i=0;i<9;i++)
    	   for(int j=0;j<9;j++){
       		   button[i][j]=new JButton(icon[0]);
      	  	}
              	
		for (int i=0;i<9;i++)
       		for(int j=0;j<9;j++)	 
        		add(button[i][j]);	

		x=y=-1;
				
		setMenu();
		setButton();
		setTitle(" Lines ");
		setLayout(new GridLayout(9,9));
		setSize(520,600);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setResizable(false);
        setVisible(true);				
	 }
 //----------------------------------------------------------------------------------------
	// Create menu
	public void setMenu(){
		
		JMenuBar menu=new JMenuBar();
		menu.setLayout(new GridLayout(1,7));
		setJMenuBar(menu);
		JMenu gameMenu=new JMenu("Game");// Add game tab to Menu
		gameMenu.setMnemonic('g');
		menu.add(gameMenu);
		
		JMenuItem newItem=new JMenuItem("NewGame"); // Add to Menu
		newItem.setToolTipText(" NewGame selected ");
		newItem.setMnemonic('n');
		newItem.addActionListener(new ActionListener(){		
			public void actionPerformed(ActionEvent ae){
				startGame();
		}				
		});
	    gameMenu.add(newItem);	    
	    
	    JMenuItem exitItem=new JMenuItem("Exit");// Add Exit
		exitItem.setToolTipText(" Exit selected ");
		exitItem.setMnemonic('e');
		exitItem.addActionListener(new ActionListener(){		
			public void actionPerformed(ActionEvent ae){
				System.exit(0);
		}		
		});
		gameMenu.add(exitItem);
		
		
		JMenu gameUtilities=new JMenu("Utilities");// Add Utilities
		gameUtilities.setMnemonic('U');
		menu.add(gameUtilities);
		
	    JMenuItem topScoresItem=new JMenuItem("TopScores");//add TopScores
		topScoresItem.setToolTipText(" TopScores selected");
		topScoresItem.setMnemonic('t');
		topScoresItem.addActionListener(new ActionListener(){		
			public void actionPerformed(ActionEvent ae){
				try{
					topScores.showTopScores();						
				}catch(IOException e){}
		}		
		});				
	    gameUtilities.add(topScoresItem);		
		
		JMenuItem undoItem=new JMenuItem("StepBack"); //add StepBack
		undoItem.setToolTipText("  StepBack selected ");
		undoItem.setMnemonic('b');
		undoItem.addActionListener(new ActionListener(){		
			public void actionPerformed(ActionEvent ae){
			     StepBack();
			     			     
		}				
		});
	    gameUtilities.add(undoItem);
		
	    for(int i=0;i<3;i++){
	    	nextball[i]= new JMenuItem();
	    	menu.add(nextball[i]);
	    }
	    			
	    Icon scoreIcon = new ImageIcon("Images/score.png");
	    score.setIcon(scoreIcon);
		menu.add(score);

	}
//----------------------------------------------------------------------------------------
    // Stepback Function
    public void StepBack(){
    	a.Undo();
    	displayNextBall();
		score.setText((int)a.MarkResult+" ");    	    	
    	drawBall();	   
    		
    }	
//----------------------------------------------------------------------------------------
	// Redraw cells
	public void drawBall(){
        for (int i=0;i<9;i++) 	
           	for (int j=0;j<9;j++){     
           		 button[i][j].setIcon(icon[a.ball[i][j]]);

       }
	}
 //----------------------------------------------------------------------------------------	
	// Display 3 upcoming balls
	public void displayNextBall(){
		for(int i=0;i<3;i++){
	    	nextball[i].setIcon(icon[a.nextcolor[i]]);
	    }		
	}
 //----------------------------------------------------------------------------------------	
	// Make ball move after selected
	public void bounceBall(){
		for(int i=0;i<9;i++)
		  for(int j=0;j<9;j++)
				if(a.ball[i][j]>14)a.ball[i][j]-=14;				

		if(x>=0&&y>=0)a.ball[x][y]+=14;
		
		drawBall();
	}
 //----------------------------------------------------------------------------------------	
	//Interaction with board
	public void setButton(){
	   for (int i=0;i<9;i++)
        for (int j=0;j<9;j++)	 
        	button[i][j].addActionListener( new ActionListener(){
			   public void actionPerformed(ActionEvent ae)
			   {		    				
		      		for (int i=0;i<9;i++)
                 	   for(int j=0;j<9;j++)
           	       		if(ae.getSource()==button[i][j]){
           	       			Icon n = button[i][j].getIcon();
	
						 if(x!=i&&y!=j&&(n==icon[1]||n==icon[2]||n==icon[3]
							 ||n==icon[4]||n==icon[5]||n==icon[6]||n==icon[7]))						
							{
								x=i;
								y=j;
									
							}
							else if(x==i&&y==j){
										x=y=-1;
										
									}
							   else if(x>-1&&y>-1&&(n==icon[0]||n==icon[8]||n==icon[9]||n==icon[10]
								        ||n==icon[11]||n==icon[12]||n==icon[13]||n==icon[14])){

								        	if(a.Load(x,y,i,j)==true){
								        		a.saveUndo();// Save previos action
								        		try{moveBall(x,y,i,j);}catch(Exception e){}
								        		drawBall();
								        		if(a.cutBall()==false)a.new3Balls();
								        		a.cutBall();
								        		displayNextBall();// Display 3 upcoming colors
								        		drawBall();
												x=y=-1;	
								        	}
								        } 

							bounceBall();
							player.scores=(int)a.MarkResult;
							score.setText((int)a.MarkResult+" ");
							try{
								stopGame();// Stop the game if board is full
							}catch(IOException e){}
												           	            
           	            }

			     }

		  });

	}
 //----------------------------------------------------------------------------------------	
	// Move balls
	public void moveBall(int i,int j,int ii,int jj)throws Exception{		

		   a.ball[ii][jj] = a.ball[i][j]-14;
		   a.ball[i][j] = 0;
		   for(int k=0;k<22;k++)
		   	  if(button[i][j].getIcon()==icon[k])
		   	  	button[ii][jj].setIcon(icon[k-14]);
		   	  button[i][j].setIcon(icon[0]);	  					    					    	
	}

 //----------------------------------------------------------------------------------------	
	// Start the game
	public void startGame(){
		a.StartGame();
		score.setText("0 ");		
		displayNextBall();
		drawBall();
		x=y=-1;
	
	}
 //----------------------------------------------------------------------------------------	
	// Function to stop the game if the board is full
	public void stopGame()    throws IOException{
		if(a.gameover==true){
			topScores.readFile();
			boolean k2 =false;// check scores
			for(int i=0;i<10;i++)
				if(topScores.player[i].scores<player.scores){
					k2 = true;
					break;
				}
			if(k2==true){
				player.setName();
				topScores.add(player);
				topScores.showTopScores();
				startGame();
			}else {
				GameOver = new JFrame(" GameOver !");
				JButton msg1 = new JButton(" TRO CHOI KET THUC !");
				JButton msg2 = new JButton(" Ban Ghi Duoc "+player.scores+" Diem");
				GameOver.add(msg1);
				GameOver.add(msg2);
				GameOver.setLayout(new GridLayout(2,1));
				GameOver.setSize(290,150);
				GameOver.setResizable(false);
				GameOver.show();
				GameOver.addWindowListener( new WindowAdapter() {// Start new game upon closing windows
					public void windowClosing(WindowEvent e){ 
							startGame();
					}
 				});
			}		
		}
	}
 //----------------------------------------------------------------------------------------

}
