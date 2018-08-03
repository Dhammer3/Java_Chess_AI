package chess;

import java.util.ArrayList;
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
	Piece[][] savePiece= new Piece[8][8];
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
	
		
		//fill the moveList
		fillMoveList( board, play,  enemy  );
		System.out.println(availMoveCount);
		//get the current master value
		 int rootValue=getmasterEvaluation( board, play,  enemy);
		// find best and second best move
		getBestMoves( board, play,  enemy);
		//fill the root node
	
	
		this.printmaster(board);

		
		lValue=getmasterEvaluation( master, play,  enemy);
		rValue=getmasterEvaluation(   master, play,  enemy);
		
	
		
		node left = new node(play, xPos, yPos, movePosX, movePosY, lmaster, lValue);
		node right = new node(play, xPos2, yPos2, movePosX2, movePosY2, rmaster, rValue);
		root = new node( play, master, rootValue, left, right);
		System.out.println("HEERERERERe");
		

		

		fillMoveList( root.left.getBoard(), enemy,  play);		
		getBestMoves( root.left.getBoard(), enemy,  play);	
		node eLeft = new node(play, xPos, yPos, movePosX, movePosY, lmaster, lValue);
		node eRight = new node(play, xPos2, yPos2, movePosX2, movePosY2, rmaster, rValue);
		left.setLeft(eLeft);
		left.setRight(eRight);

		
		fillMoveList( root.right.getBoard(), enemy,  play);		
		getBestMoves(root.right.getBoard(), enemy,  play);	
		node eLeft2 = new node(play, xPos, yPos, movePosX, movePosY, lmaster, lValue);
		node eRight2 = new node(play, xPos2, yPos2, movePosX2, movePosY2, rmaster, rValue);
		root.right.setLeft(eLeft2);
		root.right.setRight(eRight2);
		this.printmaster(board);
		this.printmaster(root.left.getBoard());

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
		ArrayList<Piece> plays= new ArrayList<Piece>();
		ArrayList<Piece> enemys= new ArrayList<Piece>();
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
							//piecesThreatened++;	
						//System.out.println(master[i][j].getValue());
							plays.add(board[i][j]);
						friendlyTotalValue+=board[i][j].getValue();
						}
						else if(board[i][j].getPlayer().toString().equals(enemy.toString()))
						{
							//System.out.println(master[i][j].getValue());
							enemys.add(board[i][j]);
							enemyTotalValue+=board[i][j].getValue();
						}
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
			}
		}
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
		return (piecesThreatened + piecesGuarded + friendlyTotalValue) - (piecesVulnerable + enemyTotalValue);
			
		
		//return 0;
	}

	/*
	 * fill the moveList, which is an integer stack that contains current and move
	 * position coordinates.
	 */
	public void fillMoveList(Piece[][] board, Player play, Player enemy)
	{
		availMoveCount=0;
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
								if((x>7)||(x<0)||(y>7)|(y<0))
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
								if((x>7)||(x<0)||(y>7)|(y<0))
								{
									break;
								}
								moveList.push(x+2);
								moveList.push(y+1);
								moveList.push(j);
								moveList.push(i);
								availMoveCount++;
								
								
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
						moveList.push(i);
						moveList.push(j);
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
	
		bestMove.empty();
		scndBestMove.empty();
		
		for(int i=0; i<moveList.size(); i+=4)
		{
		//gb2.copyBoard(master);
		
		//this.printmaster(master);
		
			//create mock move here
			int yPos=moveList.pop();
			int xPos=moveList.pop();
			int movePosY=moveList.pop();
			int movePosX=moveList.pop();
			//this.printmaster(board);
		
			//System.out.println(xPos+""+yPos+""+movePosY+""+movePosX);
			/*
			if(master[movePosY][movePosX]!=null)
			{
				savePiece[movePosY][movePosX]=master[movePosY][movePosX];	
			
				//set the boolean
				pieceWasInSpot=true;
			}
			*/
			board[movePosY][movePosX]=board[yPos][xPos];
			board[yPos][xPos]=null;
		
			//this.printmaster(board);
			//this.printmaster(master);
			//this.printmaster(board);
			//this.printmaster(master);
			//gb2.updateBoard(master);
			//this.printmaster(master);
			//gb3=new board();
			//gb3.updateBoard(master);
			
		//	this.printmaster(master);

			int temp= getmasterEvaluation( board,  play,  enemy);
			//System.out.println("temp"+temp);
			
			if (temp>best)
			{
				
				//this.printmaster(master);
				System.out.println("temp"+temp);
				//remove any move that is worse than the current move
				
				this.yPos=yPos;
				this.xPos=xPos;
				this.movePosY=movePosY;
				this.movePosX=movePosX;
			
			
				//System.out.println("LLLLLLLLLLLLLLLL");
				//fill best master
				for(int w=0; w<8; w++)
				{
					for(int j=0; j<8; j++)
					{
						lmaster[w][j]=board[w][j];
						
					}
				}
				
				//check
				//lmaster[this.movePosY][this.movePosX]=lmaster[this.yPos][this.xPos];
				//lmaster[this.yPos][this.xPos]=null;
				//master[yPos][xPos]=null;
				//System.out.println("HEEEERRE");
			//this.printmaster(lmaster);
		
				
				//set best value
				best=temp;
				currentBest=temp;
				
			}
			else
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
					for(int j=0; j<8; j++)
					{
						rmaster[w][j]=board[w][j];
					}
				}
			
				//good
				rmaster[movePosY2][movePosX2]=rmaster[yPos2][xPos2];
				//master[yPos2][xPos2]=null;
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
			board[yPos][xPos]=board[movePosY][movePosX];
			board[movePosY][movePosX]=null;
			//this.printmaster(board);
		}
		//System.out.println("KKKKKKKKKKKKKKKKKKKKKK"+calc);
		//gb2.copyBoard(master);
		//this.printmaster(rmaster);
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
	void printLevelOrder()
    {
        int h = height(root);
        int i;
        for (i=1; i<=h; i++)
            printGivenLevel(root, i);
    }
 
    /* Compute the "height" of a tree -- the number of
    nodes along the longest path from the root node
    down to the farthest leaf node.*/
    int height(node root)
    {
        if (root == null)
           return 0;
        else
        {
            /* compute  height of each subtree */
            int lheight = height(root.left);
            int rheight = height(root.right);
             
            /* use the larger one */
            if (lheight > rheight)
                return(lheight+1);
            else return(rheight+1); 
        }
    }
 
    /* Print nodes at the given level */
    void printGivenLevel (node root ,int level)
    {
        if (root == null)
            return;
        if (level == 1)
            System.out.print(root.getPlayer().toString()+" "+root.getSide() + " ");
       
        else if (level > 1)
        {
            printGivenLevel(root.left, level-1);
            printGivenLevel(root.right, level-1);
        }
        System.out.println();
    }
}
