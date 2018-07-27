package chess;

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
	Piece[][] tempBrd= new Piece[8][8];
	int currentBest;
	int currentScndBest;
	int value;
	int lValue;
	int rValue;
	node root;
	node left;
	node right;
	Piece[][] savePiece= new Piece[8][8];
	boolean pieceWasInSpot=false;
	board gb2;
	
	
	public miniMaxTree(board gb, int depth, Player play, Player enemy)
	{
		gb2=gb;
		gb.copyBoard(master);
		//this.printmaster(master);
		for(int j=0; j<8; j++)
		{
			for(int y=0; y<8; y++)
			{
				master[j][y]=master[j][y];
			}
		}
		this.printmaster(master);
		//this.printmaster(master);
		//master=master;
		//fill the moveList
		fillMoveList( play,  enemy  );
		//get the current master value
		 int rootValue=getmasterEvaluation(   play,  enemy);
		// find best and second best move
		getBestMoves( play,  enemy);
		//fill the root node
	
		//this.printmaster(master);
		//fill left, right,  and root node
		int yPos=bestMove.pop();
		int xPos=bestMove.pop();
		int movePosY=bestMove.pop();
		int movePosX=bestMove.pop();
		
		lValue=getmasterEvaluation(  play,  enemy);
		rValue=getmasterEvaluation(   play,  enemy);
		
		//this.printmaster(lmaster);
		int yPos2=scndBestMove.pop();
		int xPos2=scndBestMove.pop();
		int movePosY2=scndBestMove.pop();
		int movePosX2=scndBestMove.pop();
		
		node left = new node(xPos, yPos, movePosX, movePosY, lmaster, lValue);
		node right = new node(xPos2, yPos2, movePosX2, movePosY2, rmaster, rValue);
		root = new node( master, rootValue, left, right);
	//	this.printmaster(master);
		lmaster[yPos][xPos]=null;
	//	this.printmaster(lmaster);
	//	this.printmaster(rmaster);

		//fill the moveList
		for(int i=0; i<depth; i++)
		{
			//if the depth is even, then current player movelist is being evaluated
			if(i%2==0)
			{
				
				//fill the moveList starting at the leftMost node 
				fillMoveList( play,  enemy);		
				// find and fill best and second best move
				getBestMoves( play,  enemy);
				
				 yPos=bestMove.pop();
				 xPos=bestMove.pop();
				 movePosY=bestMove.pop();
				 movePosX=bestMove.pop();
				 //set the respective values
				
				 yPos2=scndBestMove.pop();
				 xPos2=scndBestMove.pop();
				 movePosY2=scndBestMove.pop();
				 movePosX2=scndBestMove.pop();
			
				//initialize child nodes
				node leftChild = new node(xPos, yPos, movePosX, movePosY, lmaster, currentBest);
				this.printmaster(lmaster);
				node rightChild = new node(xPos2, yPos2, movePosX2, movePosY2, rmaster, currentScndBest);
				//find the lowest leaf node and set the child nodes
				left.getLeftMost().setLeft(leftChild);
				left.getLeftMost().setRight(rightChild);
				//leftMost child nodes are filled
				
				
				//fill the moveList starting at the rightMost node 
				fillMoveList( play,  enemy);
				
				// find and fill best and second best move
				getBestMoves( play,  enemy);
				
				 yPos=bestMove.pop();
				 xPos=bestMove.pop();
				 movePosY=bestMove.pop();
				 movePosX=bestMove.pop();
				 
				 yPos2=scndBestMove.pop();
				 xPos2=scndBestMove.pop();
				 movePosY2=scndBestMove.pop();
				 movePosX2=scndBestMove.pop();
		
				//initialize child nodes
				node leftChild2 = new node(xPos, yPos, movePosX, movePosY, lmaster, currentBest);
				this.printmaster(master);
				node rightChild2 = new node(xPos2, yPos2, movePosX2, movePosY2, rmaster, currentScndBest);
				//find the lowest leaf node and set the child nodes
				right.getRightMost().setLeft(leftChild2);
				right.getRightMost().setRight(rightChild2);
				
				//rightMost child nodes are filled
				
			}
			//if the depth is odd, then the enemy player's moves are being evaluated, just reverse the players in fillMoveList and getBestMoves
			else
			{
				
				//fill the moveList starting at the leftMost node 
				fillMoveList( enemy,  play);		
				// find and fill best and second best move
				getBestMoves( enemy,  play);
				
				 yPos=bestMove.pop();
				 xPos=bestMove.pop();
				 movePosY=bestMove.pop();
				 movePosX=bestMove.pop();
				 //set the respective values
			
				 yPos2=scndBestMove.pop();
				 xPos2=scndBestMove.pop();
				 movePosY2=scndBestMove.pop();
				 movePosX2=scndBestMove.pop();
			
				//initialize child nodes									//multiplying by neg 1 for summation purposes later
				node leftChild = new node(xPos, yPos, movePosX, movePosY, lmaster, currentBest*-1);
				node rightChild = new node(xPos2, yPos2, movePosX2, movePosY2, rmaster, currentScndBest*-1);
				//find the lowest leaf node and set the child nodes
				left.getLeftMost().setLeft(leftChild);
				left.getLeftMost().setRight(rightChild);
				//leftMost child nodes are filled
				
				
				//fill the moveList starting at the rightMost node 
				fillMoveList( enemy,  play);
				
				// find and fill best and second best move
				getBestMoves( enemy,  play);
				
				 yPos=bestMove.pop();
				 xPos=bestMove.pop();
				 movePosY=bestMove.pop();
				 movePosX=bestMove.pop();
				 
				 yPos2=scndBestMove.pop();
				 xPos2=scndBestMove.pop();
				 movePosY2=scndBestMove.pop();
				 movePosX2=scndBestMove.pop();
		
				//initialize child nodes									//multiplying by neg 1 for summation purposes later		
				node leftChild2 = new node(xPos, yPos, movePosX, movePosY, lmaster, currentBest*-1);
				node rightChild2 = new node(xPos2, yPos2, movePosX2, movePosY2, rmaster, currentScndBest*-1);
				//find the lowest leaf node and set the child nodes
				right.getRightMost().setLeft(leftChild2);
				right.getRightMost().setRight(rightChild2);
			}
			
		}
	}
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
	public int getmasterEvaluation( Player play, Player enemy) {
		Piece[][] temp = new Piece[8][8];
		//temp = master;
		int piecesThreatened = 0;
		int piecesGuarded = 0;
		int piecesVulnerable = 0;

		for (int i = 0; i < play.getPieceList().size(); i++) {
			for (int j = 0; j < enemy.getPieceList().size(); j++) {
				
	
				if (play.getPiece(i).move(master, enemy.getPiece(j).getX(), enemy.getPiece(j).getY()) == true) {
				
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
				

			}
		}
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

		return (piecesThreatened + piecesGuarded) - piecesVulnerable;
			
		
		//return 0;
	}

	/*
	 * fill the moveList, which is an integer stack that contains current and move
	 * position coordinates.
	 */
	public void fillMoveList(Player play, Player enemy)
	{
	
		for(int i=0; i<play.getPieceList().size(); i++)
		{
			for(int j=0; j<enemy.getPieceList().size(); j++)
			{
				//System.out.println("HHHHHHHHHHHHHHHHH");
				//fill non attack moves first
				
					//a loop for each possible move direction starting at 12 o'clock position and moving clockwise
					int iteratorX=play.getPiece(i).getX();
					int iteratorY=0;
					iteratorX=play.getPiece(i).getX();
					iteratorY= play.getPiece(i).getY()-1;
					
					if(j==0)
					{
						if((play.getPiece(i).toString().equals("[*k*]"))||((play.getPiece(i).toString().equals("[!k!]"))))
						{
							
							int x= play.getPiece(i).getX();
							int y =play.getPiece(i).getY();
							
							if(play.getPiece(i).move(master, x-1, y-2)==true)
							{
								if((x>7)||(x<0)||(y>7)|(y<0))
								{
									break;
								}
								moveList.push(x-1);
								moveList.push(y-2);
								moveList.push(play.getPiece(i).getX());
								moveList.push(play.getPiece(i).getY());
								
							}
							
							if(play.getPiece(i).move(master, x-1, y+2)==true)
							{
								if((x>7)||(x<0)||(y>7)|(y<0))
								{
									break;
								}
								moveList.push(x-1);
								moveList.push(y+2);
								moveList.push(play.getPiece(i).getX());
								moveList.push(play.getPiece(i).getY());
								
							}
							
							if(play.getPiece(i).move(master, x+1, y-2)==true)
							{
								if((x>7)||(x<0)||(y>7)|(y<0))
								{
									break;
								}
								moveList.push(x+1);
								moveList.push(y-2);
								moveList.push(play.getPiece(i).getX());
								moveList.push(play.getPiece(i).getY());
								
							}
							
							if(play.getPiece(i).move(master, x+1, y+2)==true)
							{
								if((x>7)||(x<0)||(y>7)|(y<0))
								{
									break;
								}
								moveList.push(x+1);
								moveList.push(y+2);
								moveList.push(play.getPiece(i).getX());
								moveList.push(play.getPiece(i).getY());
							
							}
							
							if(play.getPiece(i).move(master, x-2, y-1)==true)
							{
								if((x>7)||(x<0)||(y>7)|(y<0))
								{
									break;
								}
								moveList.push(x-2);
								moveList.push(y-1);
								moveList.push(play.getPiece(i).getX());
								moveList.push(play.getPiece(i).getY());
							
							}
							
							if(play.getPiece(i).move(master, x-2, y+1)==true)
							{
								if((x>7)||(x<0)||(y>7)|(y<0))
								{
									break;
								}
								moveList.push(x-2);
								moveList.push(y+1);
								moveList.push(play.getPiece(i).getX());
								moveList.push(play.getPiece(i).getY());
							
							}
							
							if(play.getPiece(i).move(master, x+2, y-1)==true)
							{
								if((x>7)||(x<0)||(y>7)|(y<0))
								{
									break;
								}
								moveList.push(x+2);
								moveList.push(y-1);
								moveList.push(play.getPiece(i).getX());
								moveList.push(play.getPiece(i).getY());
								
							}
							
							if(play.getPiece(i).move(master, x+2, y+1)==true)
							{
								if((x>7)||(x<0)||(y>7)|(y<0))
								{
									break;
								}
								moveList.push(x+2);
								moveList.push(y+1);
								moveList.push(play.getPiece(i).getX());
								moveList.push(play.getPiece(i).getY());
								
								
							}
						}
					while(play.getPiece(i).move(master, iteratorX, iteratorY)==true)
					{
						//y then x
						moveList.push(iteratorX);
						moveList.push(iteratorY);
						moveList.push(play.getPiece(i).getX());
						moveList.push(play.getPiece(i).getY());
						
						
						iteratorY--;
						if((iteratorX>7)||(iteratorX<0)||(iteratorY>7)|(iteratorY<0))
						{
							break;
						}
					
					}
					iteratorX=play.getPiece(i).getX()-1;
					iteratorY= play.getPiece(i).getY()-1;
					while(play.getPiece(i).move(master, iteratorX, iteratorY)==true)
					{
						moveList.push(iteratorX);
						moveList.push(iteratorY);
						moveList.push(play.getPiece(i).getX());
						moveList.push(play.getPiece(i).getY());
						
						iteratorX--;
						iteratorY--;
						if((iteratorX>7)||(iteratorX<0)||(iteratorY>7)|(iteratorY<0))
						{
							break;
						}
					}
					iteratorX=play.getPiece(i).getX()-1;
					iteratorY= play.getPiece(i).getY();
					while(play.getPiece(i).move(master, iteratorX, iteratorY)==true)
					{
						moveList.push(iteratorX);
						moveList.push(iteratorY);
						moveList.push(play.getPiece(i).getX());
						moveList.push(play.getPiece(i).getY());
					
						iteratorX++;
						if((iteratorX>7)||(iteratorX<0)||(iteratorY>7)|(iteratorY<0))
						{
							break;
						}
					
					}
					iteratorX=play.getPiece(i).getX()+1;
					iteratorY= play.getPiece(i).getY()+1;
					while(play.getPiece(i).move(master, iteratorX, iteratorY)==true)
					{
						moveList.push(iteratorX);
						moveList.push(iteratorY);
						moveList.push(play.getPiece(i).getX());
						moveList.push(play.getPiece(i).getY());
						
						iteratorX++;
						iteratorY++;
						if((iteratorX>7)||(iteratorX<0)||(iteratorY>7)|(iteratorY<0))
						{
							break;
						}
					}
					iteratorX=play.getPiece(i).getX();
					iteratorY= play.getPiece(i).getY()+1;
					while(play.getPiece(i).move(master, iteratorX, iteratorY)==true)
					{
						moveList.push(iteratorX);
						moveList.push(iteratorY);
						moveList.push(play.getPiece(i).getX());
						moveList.push(play.getPiece(i).getY());
					
				
						iteratorY++;
						if((iteratorX>7)||(iteratorX<0)||(iteratorY>7)|(iteratorY<0))
						{
							break;
						}
					}
					iteratorX=play.getPiece(i).getX()-1;
					iteratorY= play.getPiece(i).getY()+1;
					while(play.getPiece(i).move(master, iteratorX, iteratorY)==true)
					{
						moveList.push(iteratorX);
						moveList.push(iteratorY);
						moveList.push(play.getPiece(i).getX());
						moveList.push(play.getPiece(i).getY());
						
						iteratorX--;
						iteratorY++;
						if((iteratorX>7)||(iteratorX<0)||(iteratorY>7)|(iteratorY<0))
						{
							break;
						}
					}
					iteratorX=play.getPiece(i).getX()-1;
					iteratorY= play.getPiece(i).getY();
					while(play.getPiece(i).move(master, iteratorX, iteratorY)==true)
					{
						moveList.push(iteratorX);
						moveList.push(iteratorY);
						moveList.push(play.getPiece(i).getX());
						moveList.push(play.getPiece(i).getY());
						
						iteratorX--;
						if((iteratorX>7)||(iteratorX<0)||(iteratorY>7)|(iteratorY<0))
						{
							break;
						}
					
					}
					iteratorX=play.getPiece(i).getX()-1;
					iteratorY= play.getPiece(i).getY()-1;
					while(play.getPiece(i).move(master, iteratorX, iteratorY)==true)
					{
						moveList.push(iteratorX);
						moveList.push(iteratorY);
						moveList.push(play.getPiece(i).getX());
						moveList.push(play.getPiece(i).getY());
						
						
						
						iteratorX--;
						iteratorY--;
						if((iteratorX>7)||(iteratorX<0)||(iteratorY>7)|(iteratorY<0))
						{
							break;
						}
					}
					
				
					
				}
				//fix the list of pieces
					if(play.getPiece(i).move(master, enemy.getPiece(j).getX(), enemy.getPiece(j).getY())==true)
					{
						//finding the best move
						if(play.getPiece(i).getValue()>max)
						{
							this.setMax(play.getPiece(i).getValue());
						}
						
							moveList.push(enemy.getPiece(j).getX());
							moveList.push(enemy.getPiece(j).getY());
							moveList.push(play.getPiece(i).getX());
							moveList.push(play.getPiece(i).getY());	
							
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
	public void getBestMoves(Player play, Player enemy)
	{
		//
		int best=-100;
		int scndBest=-100;
	
		
		
		for(int i=0; i<moveList.size(); i+=4)
		{
		gb2.copyBoard(master);
		
			//create mock move here
			int yPos=moveList.pop();
			int xPos=moveList.pop();
			int movePosY=moveList.pop();
			int movePosX=moveList.pop();
			//System.out.println(xPos+""+yPos+""+movePosY+""+movePosX);
			if(master[movePosY][movePosX]!=null)
			{
				savePiece[movePosY][movePosX]=master[movePosY][movePosX];	
			
				//set the boolean
				pieceWasInSpot=true;
			}
	
			master[movePosY][movePosX]=master[yPos][xPos];
			master[yPos][xPos]=null;
			this.printmaster(master);
			

			int temp= getmasterEvaluation(   play,  enemy);

			if (temp>best)
			{
				//remove any move that is worse than the current move
				
				//fill best 
				bestMove.push(movePosX);
				bestMove.push(movePosY);
				bestMove.push(xPos);
				bestMove.push(yPos);
				//System.out.println("LLLLLLLLLLLLLLLL");
				//fill best master
				for(int w=0; w<8; w++)
				{
					for(int j=0; j<8; j++)
					{
						lmaster[w][j]=master[w][j];
						
					}
				}
				//master[yPos][xPos]=null;
			//	this.printmaster(master);
		
				
				//set best value
				best=temp;
				currentBest=temp;
				
			}
			else
			{
				//remove any move that is worse than the current move
				
				scndBestMove.push(movePosX);
				scndBestMove.push(movePosY);
				scndBestMove.push(xPos);
				scndBestMove.push(yPos);
				//fill second best master
				for(int w=0; w<8; w++)
				{
					for(int j=0; j<8; j++)
					{
						rmaster[w][j]=master[w][j];
					}
				}
			
				//fill scndbest
				scndBest=temp;
				currentScndBest=temp;
			}
			/*
			if(pieceWasInSpot)
			{
				master[yPos][xPos]=master[movePosY][movePosX];
				master[movePosY][movePosX]=savePiece[movePosY][movePosX];
			}
			else
			{
			//this.printmaster(tempBrd);
			master[yPos][xPos]=master[movePosY][movePosX];
			master[movePosY][movePosX]=null;
			}
			*/
		}
	}

	/*
	 * Creates a mock move in order to calculate the value
	 */
												//where the piece is	//where the piece is going
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
													//where the piece is 	//where the piece is going
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
  }
	
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
	public void setMaster(Piece[][] master)
	{
		
	}
}
