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
	//used to hold the best and second best move board positions.
	Piece[][] lBoard= new Piece[8][8];
	Piece[][] rBoard=new Piece[8][8];
	Piece[][] tempBrd= new Piece[8][8];
	int value;
	node head;
	node left;
	node right;
	Piece[][] savePiece= new Piece[8][8];
	boolean pieceWasInSpot=false;
	
	public miniMaxTree(Piece[][] board, int depth, Player play, Player enemy)
	{
		//fill the moveList
		fillMoveList( play,  enemy,  board);
		//get the current board value
		 int rootValue=getBoardEvaluation( board,  play,  enemy);
		// find best and second best move
		getBestMoves( play,  enemy, board);
		//fill the root node
	
		//fill left, right,  and root node
		int yPos=bestMove.pop();
		int xPos=bestMove.pop();
		int movePosY=bestMove.pop();
		int movePosX=bestMove.pop();
		
		int yPos2=bestMove.pop();
		int xPos2=bestMove.pop();
		int movePosY2=bestMove.pop();
		int movePosX2=bestMove.pop();
		
		node left = new node(xPos, yPos, movePosX, movePosY, board, value);
		node right = new node(xPos2, yPos2, movePosX2, movePosY2, board, value);
		node root = new node( board, rootValue, left, right);

		//fill the moveList
		for(int i=0; i<depth; i++)
		{
			//if the depth is even, then current player movelist is being evaluated
			if(i%2==0)
			{
				//starting at the leftMost node, fill the child nodes. 
				
				//fill the moveList starting at the left node 
				fillMoveList( play,  enemy, lBoard);
				//get the current board value
				int leftValue=left.getValue();
				// find best and second best move
				getBestMoves( play,  enemy, lBoard);
				
				//
				fillMoveList( play,  enemy, rBoard);
				//get the current board value
				int rightValue=right.getValue();
				// find best and second best move
				getBestMoves( play,  enemy, rBoard);
				
				
			}
			//if the depth is odd, then the enemy player's moves are being evaluated
			else
			{
				
			}
			
		}
	}
	public Stack<Integer> getMoveList(Player play, Player enemy, Piece[][] board)
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
					while(play.getPiece(i).move(board, iteratorX, iteratorY)==true)
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
					while(play.getPiece(i).move(board, iteratorX, iteratorY)==true)
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
					while(play.getPiece(i).move(board, iteratorX, iteratorY)==true)
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
					while(play.getPiece(i).move(board, iteratorX, iteratorY)==true)
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
					while(play.getPiece(i).move(board, iteratorX, iteratorY)==true)
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
					while(play.getPiece(i).move(board, iteratorX, iteratorY)==true)
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
					while(play.getPiece(i).move(board, iteratorX, iteratorY)==true)
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
					while(play.getPiece(i).move(board, iteratorX, iteratorY)==true)
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
					if(play.inOrder.get(i).move(board, enemy.postOrder.get(j).getX(), enemy.postOrder.get(j).getY())==true)
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
	 * This method does the work to evaluate a current board setup, used to calculate
	 * the value of mock moves
	 */
	public int getBoardEvaluation(Piece[][] board, Player play, Player enemy) {
		Piece[][] temp = new Piece[8][8];
		temp = board;
		int piecesThreatened = 0;
		int piecesGuarded = 0;
		int piecesVulnerable = 0;

		for (int i = 0; i < play.getPieceList().size(); i++) {
			for (int j = 0; j < enemy.getPieceList().size(); j++) {
				
	
				if (play.getPiece(i).move(board, enemy.getPiece(j).getX(), enemy.getPiece(j).getY()) == true) {
				
					piecesThreatened += enemy.getPiece(j).getValue();
					System.out.println(piecesThreatened);
				}
				if(i+1>=play.getPieceList().size())
				{
					break;
				}
				int guardedPieceY = play.getPiece(i+1).getY();
				int guardedPieceX = play.getPiece(i+1).getX();
				board[guardedPieceY][guardedPieceX]=null;

				if (play.getPiece(i).move(board, guardedPieceX, guardedPieceY) == true)
						 {
					piecesGuarded += play.getPiece(i+1).getValue();
				}
				board[guardedPieceY][guardedPieceX]=play.getPiece(i+1);
				

			}
		}
		for (int y = 0; y < enemy.getPieceList().size(); y++) {
			for (int c = 0; c < play.getPieceList().size(); c++) {
				if (enemy.getPiece(y).move(board, play.getPiece(c).getX(), play.getPiece(c).getY()) == true) {
					piecesVulnerable += play.getPiece(c).getValue();

				}
			}
		}
		System.out.println("piecesThreatened " + piecesThreatened);
		System.out.println("piecesGuarded " + piecesGuarded);
		System.out.println("piecesVulnerable " + piecesVulnerable);

		return (piecesThreatened + piecesGuarded) - piecesVulnerable;
			
		
		//return 0;
	}

	/*
	 * fill the moveList, which is an integer stack that contains current and move
	 * position coordinates.
	 */
	public void fillMoveList(Player play, Player enemy, Piece[][] board)
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
					while(play.getPiece(i).move(board, iteratorX, iteratorY)==true)
					{
						//y then x
						moveList.push(iteratorX);
						moveList.push(iteratorY);
						moveList.push(play.getPiece(i).getX());
						moveList.push(play.getPiece(i).getY());
						
						
						iteratorY--;
						if((iteratorX>8)||(iteratorX<0)||(iteratorY>8)|(iteratorY<0))
						{
							break;
						}
					
					}
					iteratorX=play.getPiece(i).getX()-1;
					iteratorY= play.getPiece(i).getY()-1;
					while(play.getPiece(i).move(board, iteratorX, iteratorY)==true)
					{
						moveList.push(iteratorX);
						moveList.push(iteratorY);
						moveList.push(play.getPiece(i).getX());
						moveList.push(play.getPiece(i).getY());
						
						iteratorX--;
						iteratorY--;
						if((iteratorX>8)||(iteratorX<0)||(iteratorY>8)|(iteratorY<0))
						{
							break;
						}
					}
					iteratorX=play.getPiece(i).getX()-1;
					iteratorY= play.getPiece(i).getY();
					while(play.getPiece(i).move(board, iteratorX, iteratorY)==true)
					{
						moveList.push(iteratorX);
						moveList.push(iteratorY);
						moveList.push(play.getPiece(i).getX());
						moveList.push(play.getPiece(i).getY());
					
						iteratorX++;
						if((iteratorX>8)||(iteratorX<0)||(iteratorY>8)|(iteratorY<0))
						{
							break;
						}
					
					}
					iteratorX=play.getPiece(i).getX()+1;
					iteratorY= play.getPiece(i).getY()+1;
					while(play.getPiece(i).move(board, iteratorX, iteratorY)==true)
					{
						moveList.push(iteratorX);
						moveList.push(iteratorY);
						moveList.push(play.getPiece(i).getX());
						moveList.push(play.getPiece(i).getY());
						
						iteratorX++;
						iteratorY++;
						if((iteratorX>8)||(iteratorX<0)||(iteratorY>8)|(iteratorY<0))
						{
							break;
						}
					}
					iteratorX=play.getPiece(i).getX();
					iteratorY= play.getPiece(i).getY()+1;
					while(play.getPiece(i).move(board, iteratorX, iteratorY)==true)
					{
						moveList.push(iteratorX);
						moveList.push(iteratorY);
						moveList.push(play.getPiece(i).getX());
						moveList.push(play.getPiece(i).getY());
					
				
						iteratorY++;
						if((iteratorX>8)||(iteratorX<0)||(iteratorY>8)|(iteratorY<0))
						{
							break;
						}
					}
					iteratorX=play.getPiece(i).getX()-1;
					iteratorY= play.getPiece(i).getY()+1;
					while(play.getPiece(i).move(board, iteratorX, iteratorY)==true)
					{
						moveList.push(iteratorX);
						moveList.push(iteratorY);
						moveList.push(play.getPiece(i).getX());
						moveList.push(play.getPiece(i).getY());
						
						iteratorX--;
						iteratorY++;
						if((iteratorX>8)||(iteratorX<0)||(iteratorY>8)|(iteratorY<0))
						{
							break;
						}
					}
					iteratorX=play.getPiece(i).getX()-1;
					iteratorY= play.getPiece(i).getY();
					while(play.getPiece(i).move(board, iteratorX, iteratorY)==true)
					{
						moveList.push(iteratorX);
						moveList.push(iteratorY);
						moveList.push(play.getPiece(i).getX());
						moveList.push(play.getPiece(i).getY());
						
						iteratorX--;
						if((iteratorX>8)||(iteratorX<0)||(iteratorY>8)|(iteratorY<0))
						{
							break;
						}
					
					}
					iteratorX=play.getPiece(i).getX()-1;
					iteratorY= play.getPiece(i).getY()-1;
					while(play.getPiece(i).move(board, iteratorX, iteratorY)==true)
					{
						moveList.push(iteratorX);
						moveList.push(iteratorY);
						moveList.push(play.getPiece(i).getX());
						moveList.push(play.getPiece(i).getY());
						
						
						
						iteratorX--;
						iteratorY--;
						if((iteratorX>8)||(iteratorX<0)||(iteratorY>8)|(iteratorY<0))
						{
							break;
						}
					}
					
				
					
				}
				//fix the list of pieces
					if(play.inOrder.get(i).move(board, enemy.postOrder.get(j).getX(), enemy.postOrder.get(j).getY())==true)
					{
						//finding the best move
						if(play.inOrder.get(i).getValue()>max)
						{
							this.setMax(play.getPiece(i).getValue());
						}
						
							moveList.push(enemy.postOrder.get(i).getX());
							moveList.push(enemy.postOrder.get(i).getY());
							moveList.push(play.inOrder.get(i).getX());
							moveList.push(play.inOrder.get(i).getY());	
							
					}
					
			}
		}
		
	

	}
	
	/*
	 * loop through and find the best two moves the player can currently make, 
	 * using getBoardEvaluation, doMockMove, revertMockMove
	 * fills bestMove and scndBestMove Integer stacks. 
	*/
	public void getBestMoves(Player play, Player enemy, Piece[][] board)
	{
		//
		int best=-100;
		int scndBest=-100;
		
		
		for(int i=0; i<moveList.size(); i+=4)
		{
			//create mock move here
			int yPos=moveList.pop();
			int xPos=moveList.pop();
			int movePosY=moveList.pop();
			int movePosX=moveList.pop();
	
			doMockMove( board,  xPos,  yPos,  movePosY,  movePosX);
			int temp= getBoardEvaluation( tempBrd,  play,  enemy);
		
			if (temp>best)
			{
				//remove any move that is worse than the current move
				while(bestMove.peek()!=null)
				{
					bestMove.pop();
				}
				//fill best 
				bestMove.push(movePosX);
				bestMove.push(movePosY);
				bestMove.push(xPos);
				bestMove.push(yPos);
				
				//fill best board
				for(int w=0; w<8; w++)
				{
					for(int j=0; j<8; j++)
					{
						lBoard[w][j]=tempBrd[w][j];
					}
				}
				//set best value
				best=temp;
			}
			else
			{
				//remove any move that is worse than the current move
				while(scndBestMove.peek()!=null)
				{
					scndBestMove.pop();
				}
				scndBestMove.push(movePosX);
				scndBestMove.push(movePosY);
				scndBestMove.push(xPos);
				scndBestMove.push(yPos);
				//fill second best board
				for(int w=0; w<8; w++)
				{
					for(int j=0; j<8; j++)
					{
						rBoard[w][j]=tempBrd[w][j];
					}
				}
			
				//fill scndbest
				scndBest=temp;
			}
			revertMockMove( tempBrd,  xPos,  yPos,  movePosY,  movePosX);
			
		}
	}

	/*
	 * Creates a mock move in order to calculate the value
	 */
												//where the piece is	//where the piece is going
	public void doMockMove(Piece[][] board, int xPos,int  yPos, int movePosY, int movePosX)
	{
		
		
		//1: fill a temp board
		for(int i=0; i<8; i++)
		{
			for(int j=0; j<8; j++)
			{
				tempBrd[i][j]=board[i][j];
			}
		}
		//2: check if the move spot is null, if not save the piece.
		if(board[movePosY][movePosX]!=null) 
		{
			savePiece[movePosY][movePosX]=board[movePosY][movePosX];
			
			//set the boolean
			pieceWasInSpot=true;
		}
		
		//3:do the mock move and return the mock board
			tempBrd[movePosY][movePosX]=tempBrd[yPos][xPos];
			tempBrd[yPos][xPos]=null;

	}
		
	/*
	 *Undo the previous mock move 
	 */
													//where the piece is 	//where the piece is going
	public void revertMockMove(Piece[][] board, int xPos,int  yPos, int movePosY, int movePosX)
	{
		

		//1: fill a temp board
		for(int i=0; i<8; i++)
		{
			for(int j=0; j<8; j++)
			{
				tempBrd[i][j]=board[i][j];
			}
		}
		//2: undo the previous move
		tempBrd[movePosY][movePosX]=tempBrd[yPos][xPos];
		tempBrd[yPos][xPos]=null;
		
		//3: check if there was a piece removed from the previous mock move 
		if(pieceWasInSpot)
		{
			
			tempBrd[yPos][xPos]=savePiece[yPos][xPos];
			
			//reset the boolean
			pieceWasInSpot=false;
		}
		
	
	}
	
	
	
	
	  /* Given a binary tree, print its nodes according to the
    "bottom-up" postorder traversal. */
	
	 public void printPostorder(node node)
  {
      if (node == null)
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

}
