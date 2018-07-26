package chess;

public class rook extends Piece
{

	Player play;
	int moveCount;
	int value;
	
	public rook(Player play)
	{
	this.play=play;
	moveCount=0;
	value = 5;
	}
	public  Player getPlayer()
	{
		return play;
	}
	public  int getValue()
	{
		return value;
	}

	public  String toString()
	{
		String k="";
		if(this.getPlayer().toString().equals("White"))
		{
		 k="[*r*]";
		}
		else
		{
		k="[!r!]";
		}
		return k;
	}
	//checks to see if there any pieces in the way in the direction the player is trying to move 
	//checks every spot along the path of movement, if there is a piece in the way, returns false
	//if the player is trying to capture the enemy king, returns false
	/* Quadrant Diagram
	 *       0,-  
	 *   [ ][5][ ]
	 *-,0[6][r][8] +,0 
	 *   [ ][7][ ]
	 *      0,+
	 */  
	public  boolean  move(Piece[][] board, int movePosX, int movePosY )
	{
		//Piece [][] board = new Piece[8][8];
		//gameBoard.copyBoard(board);
	
		if((movePosX>7)||(movePosX<0))
		{
			return false;
		}
		if((movePosY>7)||(movePosY<0))
		{
			return false;
		}
		
		int xPos=getX();
		
		int yPos=getY();
		
		if(this.getPlayer().toString().equals("White"))
		{
			if(board[movePosY][movePosX]!=null)
			{
				if(board[movePosY][movePosX].getPlayer().toString().equals("White"))
				{
					return false;
				}
			}
		}
		else
		{
			if(board[movePosY][movePosX]!=null)
			{
				if(board[movePosY][movePosX].getPlayer().toString().equals("Black"))
				{
					return false;
				}
			}
		}
		if((xPos==movePosX)&&(yPos==movePosY))
		{
			return false;
		}
		

		
		int checkX=0;
		int checkY=0;
		
	
		
			//if the white king is currently in check
			
			//quadrant 5 and 7 
			//if the current xPos minus the movePosX equals 0 the player is trying to go to either quad 5 or 7
			if(this.getX()-movePosX==0)
			{
				
				//quad 3
				if(this.getY()>movePosY)
				{
				
					if(piecesInWay(5,  board,  movePosX,  movePosY)==false)
					{
						
						return true;
					}
					
					
				}
				
				//quad 3
				if(this.getY()<movePosY)
				{
				
					if(piecesInWay(7,  board,  movePosX,  movePosY)==false)
					{
						
						return true;
					}
				}
			}
			//quadrant 6 and 8 
			//if the current yPos minus the movePosY then the player is trying to go to either quad 6 or 8
			if(this.getY()-movePosY==0)
			{
				
				//quad 2
				if(this.getX()>movePosX)
				{
					if(piecesInWay(6,  board,  movePosX,  movePosY)==false)
					{
						return true;
					}
					
				}
				
				//quad 4
				if(this.getX()<movePosX)
				{
					if(piecesInWay(8,  board,  movePosX,  movePosY)==false)
					{
						return true;
					}
				}
			}
		
		
		

		return false;
	}
	

	/* Quadrant Diagram
	 *       0,-  
	 *   [ ][5][ ]
	 *-,0[6][r][8] +,0 
	 *   [ ][7][ ]
	 *      0,+
	 */  
	public boolean piecesInWay(int quadrant, Piece [][] board, int movePosX, int movePosY)

	{
		//Piece[][] board = new Piece[8][8];
	//	gameBoard.copyBoard(board);
		//System.out.println("movePosY "+ movePosY);
		int checkX = this.getX();
		int checkY = this.getY();

		int count = 0;

		int xPos = getX();

		int yPos = getY();

			
		//moving up
				if (quadrant == 5) {
					checkX = this.getX();
			
					for (int i = this.getY()-1; i >movePosY ; i--) 
					{
						if(board[i][checkX]!=null)
						{
					
							return true;
						}
						
						
					}
				
						return false;
				}
			
				// moving left
				if (quadrant == 6) {

				
					checkX = this.getX();
					checkY = this.getY();
					
					for (int i = this.getX()-1; i > movePosX; i--) 
					{
						if (board[checkY][i] != null)
						{
		
							return true;
						}
						
					}
				
						return false;
				}
				// moving down
				if (quadrant == 7) {

	
					checkX = this.getX();
					checkY = this.getY();
					
					for (int i = checkY; i < movePosY; i++) 
					{
						if (board[i][checkX] != null) 
						{
							return true;
						}
					//	System.out.println(board[i][checkX].toString());
					}
				
						return false;
				}
				// moving right
				if (quadrant == 8) {

		
					checkX = this.getX();
					checkY = this.getY();
					
					for (int i = checkX+1; i < movePosX; i++) 
					{
						if (board[checkY][i] != null)
						{
							return true;
						}
						
					}
				
						return false;
				}
		
		
		return true;

	}

	public void moveCounter()
	{
		this.moveCount++;
	}
	public  int getMoveCount()
	{
		return this.moveCount;
	}
}
