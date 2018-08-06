package chess;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;

public class miniMaxTree 

{
	int max;
	int min;
	Stack <Integer> moveList= new Stack<Integer>();
	//used to hold the best current moves
	Stack <Integer> bestMove= new Stack<Integer>();
	Stack <Integer> scndBestMove= new Stack<Integer>();
	//used to hold the best and second best move master positions.
	Piece[][] lmaster= new Piece[8][8];
	Piece[][] master=new Piece[8][8];
	Piece[][] rmaster=new Piece[8][8];
	Piece[][] masterLeft= new Piece[8][8];
	Piece[][] masterRight= new Piece[8][8];
	int currentBest;
	int currentScndBest;
	int value;
	int lValue;
	int rValue;
	int yPos;
	int xPos;
	int movePosY;
	int movePosX;
	int yPos2;
	int xPos2;
	int movePosY2;
	int movePosX2;
	int availMoveCount;
	node root;
	node left;
	node right;
	//Piece[][] savePiece= new Piece[8][8];
	boolean pieceWasInSpot=false;
	board gb2;
	board gb3;
	
	
	
	public miniMaxTree(Piece[][] board, int depth, Player play, Player enemy)
	{
		
		for(int w=0; w<8; w++)
		{
			for(int j=0; j<8; j++)
			{
				master[w][j]=board[w][j];
				
			}
		}
		
		//getting the moveList for enemy player, left side 
		fillMoveList( board, play,  enemy  );
		//get the current master value
		 int rootValue=getmasterEvaluation( board, play,  enemy);
		//finding best and second best moves, filling all values for upcoming nodes
		getBestMoves( board, play,  enemy);
		//fill the root node
		
	
		Piece[][] tempL = new Piece[8][8];
		Piece[][] tempR= new Piece[8][8];
		for(int i=0; i<8; i++)
		{
			for(int j=0;j<8; j++)
			{
				tempL[i][j]=lmaster[i][j];
			}
		}
		for(int i=0; i<8; i++)
		{
			for(int j=0;j<8; j++)
			{
				tempR[i][j]=rmaster[i][j];
			}
		}
		int tlValue=getmasterEvaluation( tempL, play,  enemy);
    	int trValue=getmasterEvaluation(   tempR, play,  enemy);

		node left = new node(play, xPos, yPos, movePosX, movePosY, tempL, tlValue);
		node right = new node(play, xPos2, yPos2, movePosX2, movePosY2, tempR, trValue);
		root = new node( play, master, rootValue, left, right);
		
		
		//getting the moveList for enemy player, left side 
		fillMoveList( root.left.getBoard(), enemy,  play);	
		
		//finding best and second best moves, filling all values for upcoming nodes
		getBestMoves( root.left.getBoard(), enemy,  play);	
		//System.out.println("aaaaaa"+root.left.getMovePosX());
		
		Piece[][] tempL2 = new Piece[8][8];
		Piece[][] tempR2= new Piece[8][8];
		for(int i=0; i<8; i++)
		{
			for(int j=0;j<8; j++)
			{
				tempL2[i][j]=lmaster[i][j];
			}
		}
		for(int i=0; i<8; i++)
		{
			for(int j=0;j<8; j++)
			{
				tempR2[i][j]=rmaster[i][j];
			}
		}
		int tlValue2=getmasterEvaluation( tempL2, enemy,  play);
		int trValue2=getmasterEvaluation(   tempR2, enemy,  play);

		//System.out.println("aaaaaa"+root.left.getMovePosX());
		//filling respective nodes
		node eLeft = new node(enemy, xPos, yPos, movePosX, movePosY, tempL2, tlValue2*-1);
		node eRight = new node(enemy, xPos2, yPos2, movePosX2, movePosY2, tempR2, trValue2*-1);
		//System.out.println("aaaaaa"+root.left.getMovePosX());
		
		//placing nodes in the tree
		left.setLeft(eLeft);
		left.setRight(eRight);
		
		//getting the moveList for enemy player, left side 
		fillMoveList( root.right.getBoard(), enemy,  play);	
				
		//finding best and second best moves, filling all values for upcoming nodes
		getBestMoves( root.right.getBoard(), enemy,  play);	
				//System.out.println("aaaaaa"+root.left.getMovePosX());
				
				Piece[][] tempL3 = new Piece[8][8];
				Piece[][] tempR3= new Piece[8][8];
				for(int i=0; i<8; i++)
				{
					for(int j=0;j<8; j++)
					{
						tempL3[i][j]=lmaster[i][j];
					}
				}
				for(int i=0; i<8; i++)
				{
					for(int j=0;j<8; j++)
					{
						tempR3[i][j]=rmaster[i][j];
					}
				}
				int tlValue3=getmasterEvaluation( tempL3, enemy,  play);
				int trValue3=getmasterEvaluation(   tempR3, enemy,  play);

				//System.out.println("aaaaaa"+root.left.getMovePosX());
				//filling respective nodes
				node eLeft2 = new node(enemy, xPos, yPos, movePosX, movePosY, tempL3, tlValue3*-1);
				node eRight2 = new node(enemy, xPos2, yPos2, movePosX2, movePosY2, tempR3, trValue3*-1);
				
				//System.out.println("aaaaaa"+root.left.getMovePosX());
				
				//placing nodes in the tree
				right.setLeft(eLeft2);
				right.setRight(eRight2);
				
				//getting the moveList for enemy player, left side 
				fillMoveList( root.left.left.getBoard(), play,  enemy);	
						
				//finding best and second best moves, filling all values for upcoming nodes
				getBestMoves( root.left.left.getBoard(), play,  enemy);	
						//System.out.println("aaaaaa"+root.left.getMovePosX());
						
						Piece[][] tempL4 = new Piece[8][8];
						Piece[][] tempR4= new Piece[8][8];
						for(int i=0; i<8; i++)
						{
							for(int j=0;j<8; j++)
							{
								tempL4[i][j]=lmaster[i][j];
							}
						}
						for(int i=0; i<8; i++)
						{
							for(int j=0;j<8; j++)
							{
								tempR4[i][j]=rmaster[i][j];
							}
						}
						int tlValue4=getmasterEvaluation( tempL4, play,  enemy);
						int trValue4=getmasterEvaluation(   tempR4, play,  enemy);

						//System.out.println("aaaaaa"+root.left.getMovePosX());
						//filling respective nodes
						node fLeft= new node(play, xPos, yPos, movePosX, movePosY, tempL4, tlValue4);
						node fRight = new node(play, xPos2, yPos2, movePosX2, movePosY2, tempR4, trValue4);
						
						//System.out.println("aaaaaa"+root.left.getMovePosX());
						
						//placing nodes in the tree
						root.left.left.setLeft(fLeft);
						root.left.left.setRight(fRight);
						
			
						
						//getting the moveList for enemy player, left side 
						fillMoveList( root.left.right.getBoard(), play,  enemy);	
								
						//finding best and second best moves, filling all values for upcoming nodes
						getBestMoves( root.left.right.getBoard(), play,  enemy);	
								//System.out.println("aaaaaa"+root.left.getMovePosX());
								
								Piece[][] tempL5 = new Piece[8][8];
								Piece[][] tempR5= new Piece[8][8];
								for(int i=0; i<8; i++)
								{
									for(int j=0;j<8; j++)
									{
										tempL5[i][j]=lmaster[i][j];
									}
								}
								for(int i=0; i<8; i++)
								{
									for(int j=0;j<8; j++)
									{
										tempR5[i][j]=rmaster[i][j];
									}
								}
								int tlValue5=getmasterEvaluation( tempL5, play,  enemy);
								int trValue5=getmasterEvaluation(   tempR5, play,  enemy);

								//System.out.println("aaaaaa"+root.left.getMovePosX());
								//filling respective nodes
								node fLeft2= new node(play, xPos, yPos, movePosX, movePosY, tempL5, tlValue5);
								node fRight2 = new node(play, xPos2, yPos2, movePosX2, movePosY2, tempR5, trValue5);
								
								//System.out.println("aaaaaa"+root.left.getMovePosX());
								
								//placing nodes in the tree
								root.left.right.setLeft(fLeft2);
								root.left.right.setRight(fRight2);
								//getting the moveList for enemy player, left side 
								fillMoveList( root.right.left.getBoard(), play,  enemy);	
										
								//finding best and second best moves, filling all values for upcoming nodes
								getBestMoves( root.right.left.getBoard(),play,  enemy);	
										//System.out.println("aaaaaa"+root.left.getMovePosX());
										
										Piece[][] tempL6 = new Piece[8][8];
										Piece[][] tempR6= new Piece[8][8];
										for(int i=0; i<8; i++)
										{
											for(int j=0;j<8; j++)
											{
												tempL6[i][j]=lmaster[i][j];
											}
										}
										for(int i=0; i<8; i++)
										{
											for(int j=0;j<8; j++)
											{
												tempR6[i][j]=rmaster[i][j];
											}
										}
										int tlValue6=getmasterEvaluation( tempL6, play,  enemy);
										int trValue6=getmasterEvaluation(   tempR6, play,  enemy);

										//System.out.println("aaaaaa"+root.left.getMovePosX());
										//filling respective nodes
										node fLeft3= new node(play, xPos, yPos, movePosX, movePosY, tempL6, tlValue6);
										node fRight3 = new node(play, xPos2, yPos2, movePosX2, movePosY2, tempR6, trValue6);
										
										//System.out.println("aaaaaa"+root.left.getMovePosX());
										
										//placing nodes in the tree
										root.right.left.setLeft(fLeft3);
										root.right.left.setRight(fRight3);
										
										fillMoveList( root.right.right.getBoard(), play,  enemy);	
										
										//finding best and second best moves, filling all values for upcoming nodes
										getBestMoves( root.right.right.getBoard(), play,  enemy);	
												//System.out.println("aaaaaa"+root.left.getMovePosX());
												
												Piece[][] tempL7 = new Piece[8][8];
												Piece[][] tempR7= new Piece[8][8];
												for(int i=0; i<8; i++)
												{
													for(int j=0;j<8; j++)
													{
														tempL7[i][j]=lmaster[i][j];
													}
												}
												for(int i=0; i<8; i++)
												{
													for(int j=0;j<8; j++)
													{
														tempR7[i][j]=rmaster[i][j];
													}
												}
												int tlValue7=getmasterEvaluation( tempL7, play,  enemy);
												int trValue7=getmasterEvaluation(   tempR7, play,  enemy);

												//System.out.println("aaaaaa"+root.left.getMovePosX());
												//filling respective nodes
												node fLeft4= new node(play, xPos, yPos, movePosX, movePosY, tempL7, tlValue7);
												node fRight4 = new node(play, xPos2, yPos2, movePosX2, movePosY2, tempR7, trValue7);
												
												//System.out.println("aaaaaa"+root.left.getMovePosX());
												
												//placing nodes in the tree
												root.right.right.setLeft(fLeft4);
												root.right.right.setRight(fRight4);
				
				
				
				
				
				
				
			/*	
		this.printmaster(root.getBoard());
		this.printmaster(root.left.getBoard());
		this.printmaster(root.left.left.getBoard());
		this.printmaster(root.left.left.left.getBoard());
		this.printmaster(root.left.left.right.getBoard());
		System.out.println("------------------------");
		this.printmaster(root.left.right.getBoard());
		this.printmaster(root.left.right.left.getBoard());
		this.printmaster(root.left.right.right.getBoard());
		
		
		this.printmaster(root.right.getBoard());
		this.printmaster(root.right.left.getBoard());
		this.printmaster(root.right.left.left.getBoard());
		this.printmaster(root.right.left.right.getBoard());
		this.printmaster(root.right.right.getBoard());
		this.printmaster(root.right.right.left.getBoard());
		this.printmaster(root.right.right.right.getBoard());
		*/
		//this.calculateMove();
		
		
		
		
		//System.out.println("aaaaaa"+root.left.getMovePosX());
	
		
		/*
		//this.printmaster(root.left.left.getBoard());
		//this.printmaster(lmaster);
		
		fillMoveList( root.right.getBoard(), enemy,  play);		
		getBestMoves(root.right.getBoard(), enemy,  play);	
		node eLeft2 = new node(play, xPos, yPos, movePosX, movePosY, lmaster, lValue);
		node eRight2 = new node(play, xPos2, yPos2, movePosX2, movePosY2, rmaster, rValue);
		root.right.setLeft(eLeft2);
		root.right.setRight(eRight2);
		//this.printmaster(board);
		//this.printmaster(root.left.getBoard());

		/*
		fillMoveList( root.left.left.getBoard(), play,  enemy);		
		getBestMoves( play,  enemy);	
		node fLeft = new node(play, xPos, yPos, movePosX, movePosY, lmaster, lValue);
		node fRight = new node(play, xPos2, yPos2, movePosX2, movePosY2, rmaster, rValue);
		root.left.left.setLeft(eLeft);
		root.left.left.setRight(eRight);
		
		fillMoveList( root.left.right.getBoard(), play,  enemy);		
		getBestMoves( play,  enemy);	
		node fLeft2 = new node(play, xPos, yPos, movePosX, movePosY, lmaster, lValue);
		node fRight2 = new node(play, xPos2, yPos2, movePosX2, movePosY2, rmaster, rValue);
		root.left.right.setLeft(eLeft);
		root.left.left.setRight(eRight);
		*/
	//this.printLevelOrder();
	

		//lmaster[yPos][xPos]=null;


		/*
		//fill the moveList
		for(int i=1; i<depth; i++)
		{
			//if the depth is even, then current player movelist is being evaluated
			if(i%2==0)
			{
				System.out.println("I"+i);
				//while()
				System.out.println("depth "+i);
				
				//fill the moveList starting at the leftMost node 
				fillMoveList( root.getLeftMost(root).getBoard(), play,  enemy);		
				// find and fill best and second best move
				getBestMoves( play,  enemy);
				
			
			
				//initialize child nodes
				node leftChild = new node(play, xPos, yPos, movePosX, movePosY, lmaster, currentBest);
				
				//this.printmaster(lmaster);
				node rightChild = new node(play, xPos2, yPos2, movePosX2, movePosY2, rmaster, currentScndBest);
				//find the lowest leaf node and set the child nodes
				//this.printmaster(lmaster);
				//this.printmaster(rmaster);
				root.getLeftMost(root).setLeft(leftChild);
				root.getLeftMost(root).setRight(rightChild);
				//leftMost child nodes are filled
				
				
				//fill the moveList starting at the rightMost node 
				fillMoveList( root.getRightMost(root).getBoard(), play,  enemy);
				
				// find and fill best and second best move
				getBestMoves( play,  enemy);
				
			
		
				//initialize child nodes
				node leftChild2 = new node(play, xPos, yPos, movePosX, movePosY, lmaster, currentBest);
				//this.printmaster(master);
				node rightChild2 = new node(play, xPos2, yPos2, movePosX2, movePosY2, rmaster, currentScndBest);
				//find the lowest leaf node and set the child nodes
				root.getRightMost(right).setLeft(leftChild2);
				root.getRightMost(right).setRight(rightChild2);
				//root.inOrderTraverseTree(root);
				//rightMost child nodes are filled
				
			}
			//if the depth is odd, then the enemy player's moves are being evaluated, just reverse the players in fillMoveList and getBestMoves
			else
			{
				System.out.println("HEEEERRRRRRRRRREEEEe");
				//fill the moveList starting at the leftMost node 
				fillMoveList( root.getLeftMost(root).getBoard(), enemy,  play);		
				// find and fill best and second best move
				getBestMoves( enemy,  play);
				
				
			
				//initialize child nodes									//multiplying by neg 1 for summation purposes later
				node leftChild = new node(enemy, xPos, yPos, movePosX, movePosY, lmaster, currentBest);
				node rightChild = new node(enemy, xPos2, yPos2, movePosX2, movePosY2, rmaster, currentScndBest);
				//find the lowest leaf node and set the child nodes
				left.getLeftMost(left).setLeft(leftChild);
				left.getLeftMost(left).setRight(rightChild);
				//leftMost child nodes are filled
				
				
				//fill the moveList starting at the rightMost node 
				fillMoveList( root.getRightMost(root).getBoard(), enemy,  play);
				
				// find and fill best and second best move
				getBestMoves( enemy,  play);
				
				
		
				//initialize child nodes									//multiplying by neg 1 for summation purposes later		
				node leftChild2 = new node(enemy,xPos,
						yPos, movePosX, movePosY, lmaster, currentBest*-1);
				node rightChild2 = new node(enemy,xPos2, yPos2, movePosX2, movePosY2, rmaster, currentScndBest*-1);
				//find the lowest leaf node and set the child nodes
				root.getRightMost(right).setLeft(leftChild2);
				root.getRightMost(right).setRight(rightChild2);
			}
			
		}
		this.printLevelOrder();
		*/
	}
	/*
	public Stack<Integer> getMoveList(Player play, Player enemy, Piece[][] master)
	{
	
		for(int i=0; i<play.getPieceList().size(); i++)
		{
			for(int j=0; j<enemy.getPieceList().size(); j++)
			{
				
				//fill non attack moves first
				if(j==0)
				{
					//a loop for each possible move direction starting at 12 o'clock position and moving clockwise
					int iteratorX=play.getPiece(i).getX();
					int iteratorY=0;
					iteratorX=play.getPiece(i).getX();
					iteratorY= play.getPiece(i).getY()-1;
					while(play.getPiece(i).move(master, iteratorX, iteratorY)==true)
					{
						moveList.push(iteratorY);
						moveList.push(iteratorX);
						moveList.push(play.getPiece(i).getY());
						moveList.push(play.getPiece(i).getX());
						
						
						iteratorY--;
						if((iteratorX>8)||(iteratorX<0)||(iteratorY>8)|(iteratorY<0))
						{
							break;
						}
					
					}
					iteratorX=play.getPiece(i).getX()-1;
					iteratorY= play.getPiece(i).getY()-1;
					while(play.getPiece(i).move(master, iteratorX, iteratorY)==true)
					{
						moveList.push(iteratorY);
						moveList.push(iteratorX);
						moveList.push(play.getPiece(i).getY());
						moveList.push(play.getPiece(i).getX());
						
						iteratorX--;
						iteratorY--;
						if((iteratorX>8)||(iteratorX<0)||(iteratorY>8)|(iteratorY<0))
						{
							break;
						}
					}
					iteratorX=play.getPiece(i).getX()-1;
					iteratorY= play.getPiece(i).getY();
					while(play.getPiece(i).move(master, iteratorX, iteratorY)==true)
					{
						moveList.push(iteratorY);
						moveList.push(iteratorX);
						moveList.push(play.getPiece(i).getY());
						moveList.push(play.getPiece(i).getX());
					
						iteratorX++;
						if((iteratorX>8)||(iteratorX<0)||(iteratorY>8)|(iteratorY<0))
						{
							break;
						}
					
					}
					iteratorX=play.getPiece(i).getX()+1;
					iteratorY= play.getPiece(i).getY()+1;
					while(play.getPiece(i).move(master, iteratorX, iteratorY)==true)
					{
						moveList.push(iteratorY);
						moveList.push(iteratorX);
						moveList.push(play.getPiece(i).getY());
						moveList.push(play.getPiece(i).getX());
						
						iteratorX++;
						iteratorY++;
						if((iteratorX>8)||(iteratorX<0)||(iteratorY>8)|(iteratorY<0))
						{
							break;
						}
					}
					iteratorX=play.getPiece(i).getX();
					iteratorY= play.getPiece(i).getY()+1;
					while(play.getPiece(i).move(master, iteratorX, iteratorY)==true)
					{
						moveList.push(iteratorY);
						moveList.push(iteratorX);
						moveList.push(play.getPiece(i).getY());
						moveList.push(play.getPiece(i).getX());
					
				
						iteratorY++;
						if((iteratorX>8)||(iteratorX<0)||(iteratorY>8)|(iteratorY<0))
						{
							break;
						}
					}
					iteratorX=play.getPiece(i).getX()-1;
					iteratorY= play.getPiece(i).getY()+1;
					while(play.getPiece(i).move(master, iteratorX, iteratorY)==true)
					{
						moveList.push(iteratorY);
						moveList.push(iteratorX);
						moveList.push(play.getPiece(i).getY());
						moveList.push(play.getPiece(i).getX());
						
						iteratorX--;
						iteratorY++;
						if((iteratorX>8)||(iteratorX<0)||(iteratorY>8)|(iteratorY<0))
						{
							break;
						}
					}
					iteratorX=play.getPiece(i).getX()-1;
					iteratorY= play.getPiece(i).getY();
					while(play.getPiece(i).move(master, iteratorX, iteratorY)==true)
					{
						moveList.push(iteratorY);
						moveList.push(iteratorX);
						moveList.push(play.getPiece(i).getY());
						moveList.push(play.getPiece(i).getX());
						
						iteratorX--;
						if((iteratorX>8)||(iteratorX<0)||(iteratorY>8)|(iteratorY<0))
						{
							break;
						}
					
					}
					iteratorX=play.getPiece(i).getX()-1;
					iteratorY= play.getPiece(i).getY()-1;
					while(play.getPiece(i).move(master, iteratorX, iteratorY)==true)
					{
						moveList.push(iteratorY);
						moveList.push(iteratorX);
						moveList.push(play.getPiece(i).getY());
						moveList.push(play.getPiece(i).getX());
						
						
						
						iteratorX--;
						iteratorY--;
						if((iteratorX>8)||(iteratorX<0)||(iteratorY>8)|(iteratorY<0))
						{
							break;
						}
					}
					
				
					
				}
				//fix the list of pieces
					if(play.inOrder.get(i).move(master, enemy.postOrder.get(j).getX(), enemy.postOrder.get(j).getY())==true)
					{
						//finding the best move
						if(play.inOrder.get(i).getValue()>max)
						{
							this.setMax(play.getPiece(i).getValue());
						}
						
							moveList.push(enemy.postOrder.get(i).getY());
							moveList.push(enemy.postOrder.get(i).getX());
							moveList.push(play.inOrder.get(i).getY());
							moveList.push(play.inOrder.get(i).getX());	
							
					}
					
			}
		}
		
		return moveList;
	}
	
	/*
	 * This method does the work to evaluate a current master setup, used to calculate
	 * the value of mock moves
	 */
  	public int getmasterEvaluation( Piece[][] board, Player play, Player enemy) {
		Piece[][] temp = new Piece[8][8];
		Queue<Piece> friendly= new LinkedList<Piece>();
		Queue<Piece> enemys= new LinkedList<Piece>();
		ArrayList<Integer> eCoordinates = new ArrayList<Integer>();
		ArrayList<Integer> fCoordinates = new ArrayList<Integer>();
		//temp = master;
		int piecesThreatened = 0;
		int piecesGuarded = 0;
		int piecesVulnerable = 0;
		int friendlyTotalValue=0;
		int enemyTotalValue=0;
		//this.printmaster(master);

		for (int i = 0; i < 8; i++) {
			for (int j = 0; j < 8; j++) {
				
			//System.out.println(master[i][j].getPlayer().toString());
					if(board[i][j]!=null)
					{
						if(board[i][j].getPlayer().toString().equals(play.toString()))
						{
							friendly.add(board[i][j]);
							fCoordinates.add(i);
							fCoordinates.add(j);
						    friendlyTotalValue+=board[i][j].getValue();
						
						}
						else if(board[i][j].getPlayer().toString().equals(enemy.toString()))
						{
							//System.out.println(master[i][j].getValue());
							enemys.add(board[i][j]);
							eCoordinates.add(i);
							eCoordinates.add(j);
							enemyTotalValue+=board[i][j].getValue();
						}
						
						
					}
			}
		}
		//good
			while(friendly.peek()!=null)
			{
				int count =eCoordinates.size();
				while(count!=0)
				{
					count--;
					int xPos=eCoordinates.get(count);
					count--;
					int yPos= eCoordinates.get(count);
					//count--;
					if(friendly.peek().move(board, xPos, yPos)==true)
					{
						piecesThreatened+=board[yPos][xPos].getValue();
					}
				}
				friendly.remove();
			
			}
			while(enemys.peek()!=null)
			{
				//System.out.println("HHERRREE");
				int count =fCoordinates.size();
				while(count!=0)
				{
					count--;
					int xPos=fCoordinates.get(count);
					count--;
					int yPos= fCoordinates.get(count);
					//count--;
					if(enemys.peek().move(board, xPos, yPos)==true)
					{
						piecesVulnerable+=board[yPos][xPos].getValue();
						
					}
				
				}
				enemys.remove();
			}
		
				/*
				//System.out.println(play.getPiece(i).toString()+""+play.getPiece(i).getX());
	
				if (play.getPiece(i).move(master, enemy.getPiece(j).getX(), enemy.getPiece(j).getY()) == true) {
				System.out.println("here");
					piecesThreatened += enemy.getPiece(j).getValue();
					//System.out.println(piecesThreatened);
				}
				if(i+1>=play.getPieceList().size())
				{
					break;
				}
				int guardedPieceY = play.getPiece(i+1).getY();
				int guardedPieceX = play.getPiece(i+1).getX();
				master[guardedPieceY][guardedPieceX]=null;
				if (play.getPiece(i).move(master, guardedPieceX, guardedPieceY) == true)
						 {
					piecesGuarded += play.getPiece(i+1).getValue();
				}
				master[guardedPieceY][guardedPieceX]=play.getPiece(i+1);
				
*/
		/*
		for (int y = 0; y < enemy.getPieceList().size(); y++) {
			for (int c = 0; c < play.getPieceList().size(); c++) {
				if (enemy.getPiece(y).move(master, play.getPiece(c).getX(), play.getPiece(c).getY()) == true) {
					piecesVulnerable += play.getPiece(c).getValue();
				}
			}
		}
		//System.out.println("piecesThreatened " + piecesThreatened);
		//System.out.println("piecesGuarded " + piecesGuarded);
		//System.out.println("piecesVulnerable " + piecesVulnerable);
*/
			//System.out.println();
			//System.out.print("piecesThreatened "+piecesThreatened+"piecesGuarded "+piecesGuarded+"friendlyTotalValue "+friendlyTotalValue+"piecesVulnerable "+piecesVulnerable+"enemyTotalValue "+enemyTotalValue);
	
			//System.out.print("EVAL:");
			//System.out.println((piecesThreatened + piecesGuarded + friendlyTotalValue) - (piecesVulnerable + enemyTotalValue));
			
			return (piecesThreatened + piecesGuarded + friendlyTotalValue) - (piecesVulnerable + enemyTotalValue);
			
		
		//return 0;
	}

  	public node calculateMove()
  	{
  		int left=this.root.left.getValue();
  		int right=this.root.right.getValue();
  		
  		left+=this.root.left.left.getValue();
  		left+=this.root.left.right.getValue();
  		left+=this.root.left.left.left.getValue();
  		left+=this.root.left.left.right.getValue();
  		left+=this.root.left.right.left.getValue();
  		left+=this.root.left.right.right.getValue();
  		
  		right+=this.root.right.left.getValue();
  		right+=this.root.right.right.getValue();
  		right+=this.root.right.left.left.getValue();
  		right+=this.root.right.left.right.getValue();
  		right+=this.root.right.right.right.getValue();
  		right+=this.root.right.right.left.getValue();
  		
  		System.out.println("Left: "+left);
  		System.out.println("Right: "+right);
  		System.out.println();

  			if(left>right)
  			{
  				System.out.println("Left: "+left);
  				return this.root.left;
  			}
  			else
  			{
  				System.out.println("Right: "+right);
  				return this.root.right;
  			}
  			
  		
  		
  		
  	}
	/*
	 * fill the moveList, which is an integer stack that contains current and move
	 * position coordinates.
	 */
	public void fillMoveList(Piece[][] board, Player play, Player enemy)
	{
	
		availMoveCount=0;
		moveList.clear();
		for(int i=0; i<8; i++)
		{
			for(int j=0; j<8; j++)
			{
				//System.out.println("HHHHHHHHHHHHHHHHH");
				//fill non attack moves first
				
					//a loop for each possible move direction starting at 12 o'clock position and moving clockwise
				
				
					
					if(board[i][j]!=null)
					{
						
					if(board[i][j].getPlayer().toString().equals(play.toString()))
					{
						int iteratorX=j;
						int iteratorY=0;
						iteratorX=j;
						iteratorY=i-1;
						if((board[i][j].toString().equals("[*k*]"))||((board[i][j].toString().equals("[!k!]"))))
						{
							
							int x=j;
							int y =i;
							//System.out.println();
							
							if(board[i][j].move(board, x-1, y-2)==true)
							{
								if((x>7)||(x<0)||(y>7)|(y<0))
								{
									break;
								}
								moveList.push(x-1);
								moveList.push(y-2);
								moveList.push(j);
								moveList.push(i);
								availMoveCount++;
								
							}
							
							if(board[i][j].move(board, x-1, y+2)==true)
							{
								if((x>7)||(x<0)||(y>7)|(y<0))
								{
									break;
								}
								
					
								moveList.push(x-1);
								moveList.push(y+2);
								moveList.push(j);
								moveList.push(i);
								availMoveCount++;
						
								
							}
							
							if(board[i][j].move(board, x+1, y-2)==true)
							{
								if((x>7)||(x<0)||(y>7)|(y<0))
								{
									break;
								}
								moveList.push(x+1);
								moveList.push(y-2);
								moveList.push(j);
								moveList.push(i);
								availMoveCount++;
							
								
							}
							
							if(board[i][j].move(board, x+1, y+2)==true)
							{
								if((x>7)||(x<0)||(y>7)|(y<0))
								{
									break;
								}
								moveList.push(x+1);
								moveList.push(y+2);
								moveList.push(j);
								moveList.push(i);
								availMoveCount++;
							
							
							}
							
							if(board[i][j].move(board, x-2, y-1)==true)
							{
								if((x>7)||(x<0)||(y>7)|(y<0))
								{
									break;
								}
								moveList.push(x-2);
								moveList.push(y-1);
								moveList.push(j);
								moveList.push(i);
								availMoveCount++;
							
							
							}
							
							if(board[i][j].move(board, x-2, y+1)==true)
							{
								if((x>7)||(x<0)||(y>7)|(y<0))
								{
									break;
								}
								moveList.push(x-2);
								moveList.push(y+1);
								moveList.push(j);
								moveList.push(i);
								availMoveCount++;
							
							
							}
							
							if(board[i][j].move(board, x+2, y-1)==true)
							{
								if((x>7)||(x<0)||(y>7)||(y<0))
								{
									break;
								}
								moveList.push(x+2);
								moveList.push(y-1);
								moveList.push(j);
								moveList.push(i);
								availMoveCount++;
							
								
							}
							
							if(board[i][j].move(board, x+2, y+1)==true)
							{
								if((x>7)||(x<0)||(y>7)||(y<0))
								{
									break;
								}
								moveList.push(x+2);
								moveList.push(y+1);
								moveList.push(j);
								moveList.push(i);
								availMoveCount++;
								
								//System.out.println("HHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHasldflasdjflasjf");
								
								
							}
						}
					while(board[i][j].move(board, iteratorX, iteratorY)==true)
					{
						//y then x
						moveList.push(iteratorX);
						moveList.push(iteratorY);
						moveList.push(j);
						moveList.push(i);
						availMoveCount++;
						
						
						iteratorY--;
						if((iteratorX>7)||(iteratorX<0)||(iteratorY>7)||(iteratorY<0))
						{
							break;
						}
					
					
					}
					iteratorX=j-1;
					iteratorY= i-1;
					while(board[i][j].move(board, iteratorX, iteratorY)==true)
					{
						moveList.push(iteratorX);
						moveList.push(iteratorY);
						moveList.push(j);
						moveList.push(i);
						availMoveCount++;
						
						iteratorX--;
						iteratorY--;
						if((iteratorX>7)||(iteratorX<0)||(iteratorY>7)|(iteratorY<0))
						{
							break;
						}
					}
					iteratorX=j-1;
					iteratorY=i;
					while(board[i][j].move(board, iteratorX, iteratorY)==true)
					{
						moveList.push(iteratorX);
						moveList.push(iteratorY);
						moveList.push(j);
						moveList.push(i);
						availMoveCount++;
					
						iteratorX++;
						if((iteratorX>7)||(iteratorX<0)||(iteratorY>7)|(iteratorY<0))
						{
							break;
						}
					
					}
					iteratorX=j+1;
					iteratorY= i+1;
					while(board[i][j].move(board, iteratorX, iteratorY)==true)
					{
						moveList.push(iteratorX);
						moveList.push(iteratorY);
						moveList.push(j);
						moveList.push(i);
						availMoveCount++;
						
						iteratorX++;
						iteratorY++;
						if((iteratorX>7)||(iteratorX<0)||(iteratorY>7)|(iteratorY<0))
						{
							break;
						}
					}
					iteratorX=j;
					iteratorY= i+1;
					while(board[i][j].move(board, iteratorX, iteratorY)==true)
					{
						moveList.push(iteratorX);
						moveList.push(iteratorY);
						moveList.push(j);
						moveList.push(i);
						availMoveCount++;
					
				
						iteratorY++;
						if((iteratorX>7)||(iteratorX<0)||(iteratorY>7)|(iteratorY<0))
						{
							break;
						}
					}
					iteratorX=j-1;
					iteratorY= i+1;
					while(board[i][j].move(board, iteratorX, iteratorY)==true)
					{
						moveList.push(iteratorX);
						moveList.push(iteratorY);
						moveList.push(j);
						moveList.push(i);
						availMoveCount++;
						iteratorX--;
						iteratorY++;
						if((iteratorX>7)||(iteratorX<0)||(iteratorY>7)|(iteratorY<0))
						{
							break;
						}
					}
					iteratorX=j-1;
					iteratorY= i;
					while(board[i][j].move(board, iteratorX, iteratorY)==true)
					{
						moveList.push(iteratorX);
						moveList.push(iteratorY);
						moveList.push(j);
						moveList.push(i);
						availMoveCount++;
						
						iteratorX--;
						if((iteratorX>7)||(iteratorX<0)||(iteratorY>7)|(iteratorY<0))
						{
							break;
						}
					
					}
					iteratorX=j-1;
					iteratorY= i-1;
					while(board[i][j].move(board, iteratorX, iteratorY)==true)
					{
						moveList.push(iteratorX);
						moveList.push(iteratorY);
						moveList.push(j);
						moveList.push(i);
						availMoveCount++;
						
						
						
						iteratorX--;
						iteratorY--;
						if((iteratorX>7)||(iteratorX<0)||(iteratorY>7)|(iteratorY<0))
						{
							break;
						}
					}
					
				
					
				}
					/*
				//fix the list of pieces
					if(board[i][j].move(board, enemy.getPiece(j).getX(), enemy.getPiece(j).getY())==true)
					{
						//finding the best move
						if(board[i][j].getValue()>max)
						{
							this.setMax(board[i][j].getValue());
						}
						
							moveList.push(enemy.getPiece(j).getX());
							moveList.push(enemy.getPiece(j).getY());
							moveList.push(board[i][j].getX());
							moveList.push(board[i][j].getY());	
							availMoveCount++;
					}
					*/
			}
		}
		}
		
	

	}
	
	/*
	 * loop through and find the best two moves the player can currently make, 
	 * using getmasterEvaluation, doMockMove, revertMockMove
	 * fills bestMove and scndBestMove Integer stacks, and lmaster and rmaster which hold the current mock move.
	 * fills currentBest and currentScndBest ints which hold the values for those respective moves.
	*/
	public void getBestMoves(Piece[][] board, Player play, Player enemy)
	{
		//Piece[][] lmaster= new Piece[8][8];
		//
		int best=-100;
		int scndBest=-100;
		int calc=0;
		int count=moveList.size();
		boolean pieceWasInSpot=false;
		Stack<Piece> savePiece= new Stack<Piece>();
	
		bestMove.empty();
		scndBestMove.empty();
		savePiece.empty();
		
		while(count!=0)
		{
		
		
			int yPos=moveList.pop();
			int xPos=moveList.pop();
			int movePosY=moveList.pop();
			int movePosX=moveList.pop();
			

			
			
			//create mock move here
			 

		
		if(board[movePosY][movePosX]!=null)
		{
			pieceWasInSpot=true;
			savePiece.push(board[movePosY][movePosX]);
		}
			
			board[movePosY][movePosX]=board[yPos][xPos];
			board[yPos][xPos]=null;
		
			
			
		

			int temp= getmasterEvaluation( board,  play,  enemy);
		
			if(play.toString().equals("Black"))
			{
			//	System.out.println("JJJJJJ");
				//this.printmaster(board);
				//System.out.println(temp);
			}
			if (temp>best)
			{
	
				this.yPos=yPos;
				this.xPos=xPos;
				this.movePosY=movePosY;
				this.movePosX=movePosX;
	
				for(int w=0; w<8; w++)
				{
					for(int j=0; j<8; j++)
					{
						lmaster[w][j]=board[w][j];
						
					}
				}
				//System.out.println("HHHHHERERERWFSDGDSAG");
		//	this.printmaster(lmaster);
		setMasterLeft(lmaster);
		//printmaster(getMasterLeft());
		//this.set
				
				//set best value
				best=temp;
				temp=best;
				currentBest=temp;
				lValue=temp;
				
			}
			else if(temp>scndBest)
			{
				//remove any move that is worse than the current move
				
				this.yPos2=yPos;
				this.xPos2=xPos;
				this.movePosY2=movePosY;
				this.movePosX2=movePosX;
				calc+=4;
				//fill second best master
				for(int w=0; w<8; w++)
				{
					for(int l=0; l<8; l++)
					{
						rmaster[w][l]=board[w][l];
					}
				}
				this.setMasterRight(rmaster);
				//System.out.println("JJJJJJJJakjsdflkajsdlfjas");
		//	this.printmaster(this.getMasterRight());
			
			
				//fill scndbest
				scndBest=temp;
				currentScndBest=temp;
				rValue=temp;
			}

			
			
			board[yPos][xPos]=board[movePosY][movePosX];
			if(pieceWasInSpot)
			{
				board[movePosY][movePosX]=savePiece.pop();
				pieceWasInSpot=false;
			}
			else
			{
			board[movePosY][movePosX]=null;
			}
			
			//this.printmaster(lmaster);
		
	
			 count=count-4;
		}
		moveList.empty();

		//printmaster(getMasterLeft());
		
	}


	/*
	 * Creates a mock move in order to calculate the value
	 */
	
	/*//where the piece is	//where the piece is going
	public void doMockMove( int xPos,int  yPos, int movePosY, int movePosX)
	{
		
	
		
		//2: check if the move spot is null, if not save the piece.
		if(master[movePosY][movePosX]!=null) 
		{
			savePiece[movePosY][movePosX]=master[movePosY][movePosX];
			
			//set the boolean
			pieceWasInSpot=true;
		}
		
		//3:do the mock move and set the mock master
			master[movePosY][movePosX]=master[yPos][xPos];
			master[yPos][xPos]=null;
			//this.printmaster(tempBrd);
	}
		
	/*
	 *Undo the previous mock move 
	 */
		
	/*//where the piece is 	//where the piece is going
	public void revertMockMove( int xPos,int  yPos, int movePosY, int movePosX)
	{
		
		//1: fill a temp master
		for(int i=0; i<8; i++)
		{
			for(int j=0; j<8; j++)
			{
				master[i][j]=master[i][j];
			}
		}
		//2: undo the previous move
		master[movePosY][movePosX]=master[yPos][xPos];
		master[yPos][xPos]=null;
		
		//3: check if there was a piece removed from the previous mock move 
		if(pieceWasInSpot)
		{
			
			master[yPos][xPos]=savePiece[yPos][xPos];
			
			//reset the boolean
			pieceWasInSpot=false;
		}
		
	
	}
	
	public node getRoot()
	{
		return root;
	}
	
	
	  /* Given a binary tree, print its nodes according to the
    "bottom-up" postorder traversal. */
	/*
	 public void printPostorder(node node)
  {
      if (node == null)
    	 // System.out.println();
          return;

      // first recur on left subtree
      printPostorder(node.left);

      // then recur on right subtree
      printPostorder(node.right);

      // now deal with the node
      System.out.print(node.value + " "); 
  }*/
	
	public void setMax(int a)
	{
		max=a;
		
	}
	public int getMax()
	{
		return max;
	}
	public  void printmaster(Piece[][] master)
	{
		int pos=1;
		System.out.println("\t  \t !BLACK!");
		
		System.out.print(" [ A ][ B ][ C ][ D ][ E ][ F ][ G ][ H ]");
		System.out.println();
		System.out.println();
		
		for(int i=0; i<8; i++)
		{
			System.out.print(i+1+"");
			for(int j=0; j<8; j++)
			{
				if(master[i][j]==null)
				{
					System.out.print("[   ]");
				}
				else
				{
					System.out.print(master[i][j].toString());
				}

			}
			System.out.print(i+1+"");
			System.out.println();
		}
		
		System.out.println();
		System.out.print(" [ A ][ B ][ C ][ D ][ E ][ F ][ G ][ H ]");
		System.out.println();
		System.out.println("\t \t *WHITE*");
	
		
	}

	public void resetMaster(Piece[][] master)
	{
		
	}
	
	public void setMasterLeft(Piece[][] master)
	{ 
		Piece[][] temp= new Piece[8][8];
		for(int u=0;u<8; u++)
		{
			for(int h=0; h<8; h++)
			{
				masterLeft[u][h]=master[u][h];
			}
		}
		//masterLeft=master;
		//this.tempBrd=master;
	}
	public Piece[][] getMasterLeft()
	{
		return masterLeft;
	}
	public void setMasterRight(Piece[][] master)
	{ 
		Piece[][] temp= new Piece[8][8];
		for(int u=0;u<8; u++)
		{
			for(int h=0; h<8; h++)
			{
				masterRight[u][h]=master[u][h];
			}
		}
		//this.tempBrd=master;
	}
	public Piece[][] getMasterRight()
	{
		return rmaster;
	}
}