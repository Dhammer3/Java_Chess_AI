package chess;

public class knight extends Piece 
{


	public  String toString()
	{
		String k="";
		if(this.getPlayer().toString().equals("White"))
		{
		 k="[*k*]";
		}
		else
		{
		k="[!k!]";
		}
		return k;
	}
	public knight(Player play)
	{
	this.play=play;
	}
	public  Player getPlayer()
	{
		return play;
	}
	public  int kingInCheck(board gameBoard)
	{
		
		
		
		return -1;
	}
	
	/* Quadrant Diagram
	 * move
	 *       0,-  
	 *   [ ][5][ ]
	 *-,0[6][k][8] +,0 
	 *   [ ][7][ ]
	 *      0,+
	 */  
	
	public  boolean  move(board gameBoard, int movePosX, int movePosY )
	{
		Piece [][] board = new Piece[8][8];
		gameBoard.copyBoard(board);
	
		int xPos=getX(this, board);
		
		int yPos=getY(this, board);
		int checkX=0;
		int checkY=0;
		
		
	
		
		 if(movePosX-xPos==2)
		 {
			 
			 if(movePosY-yPos==1)
			 {
				 if((board[movePosY][movePosX]==null)||(board[movePosY][movePosX].getPlayer().toString().equals("Black")))
					{
		
						return true;
					}
			 }
			 if(movePosY-yPos==-1)
			 {
				 if((board[movePosY][movePosX]==null)||(board[movePosY][movePosX].getPlayer().toString().equals("Black")))
					{
				
						return true;
					}
			 }
			 
			
		 }
		 if(movePosX-xPos==1)
		 {
			 if(movePosY-yPos==2)
			 {
				 if((board[movePosY][movePosX]==null)||(board[movePosY][movePosX].getPlayer().toString().equals("Black")))
					{
				
						return true;
					}
			 }
			 if(movePosY-yPos==-2)
			 {
				 if((board[movePosY][movePosX]==null)||(board[movePosY][movePosX].getPlayer().toString().equals("Black")))
					{
					
						return true;
					}
			 }
			
	
		 }
		 if(movePosX-xPos==-1)
		 {
			 if(movePosY-yPos==2)
			 {
				 if((board[movePosY][movePosX]==null)||(board[movePosY][movePosX].getPlayer().toString().equals("Black")))
					{
				
						return true;
					}
			 }
			
			 if(movePosY-yPos==-2)
			 {
				 if((board[movePosY][movePosX]==null)||(board[movePosY][movePosX].getPlayer().toString().equals("Black")))
					{
			
						return true;
					}
			 }
			 
		 }
		 if(movePosX-xPos==-2)
		 {
			
			 if(movePosY-yPos==1)
			 {
				 if((board[movePosY][movePosX]==null)||(board[movePosY][movePosX].getPlayer().toString().equals("Black")))
					{
			
						return true;
					}
			 }
			 if(movePosY-yPos==-1)
			 {
				 if((board[movePosY][movePosX]==null)||(board[movePosY][movePosX].getPlayer().toString().equals("Black")))
					{
			
						return true;
					}
			 }
			
			
		 }
		 
		 
		 
		 
		 
		 
		 
		 if(movePosX-xPos==2)
		 {
			 
			 if(movePosY-yPos==1)
			 {
				 if((board[movePosY][movePosX]==null)||(board[movePosY][movePosX].getPlayer().toString().equals("White")))
					{
	
						return true;
					}
			 }
			 if(movePosY-yPos==-1)
			 {
				 if((board[movePosY][movePosX]==null)||(board[movePosY][movePosX].getPlayer().toString().equals("White")))
					{
			
						return true;
					}
			 }
			 
			
		 }
		 if(movePosX-xPos==1)
		 {
			 if(movePosY-yPos==2)
			 {
				 if((board[movePosY][movePosX]==null)||(board[movePosY][movePosX].getPlayer().toString().equals("White")))
					{
				
						return true;
					}
			 }
			 if(movePosY-yPos==-2)
			 {
				 if((board[movePosY][movePosX]==null)||(board[movePosY][movePosX].getPlayer().toString().equals("White")))
					{
		
						return true;
					}
			 }
			
	
		 }
		 if(movePosX-xPos==-1)
		 {
			 if(movePosY-yPos==2)
			 {
				 if((board[movePosY][movePosX]==null)||(board[movePosY][movePosX].getPlayer().toString().equals("White")))
					{
				
						return true;
					}
			 }
			
			 if(movePosY-yPos==-2)
			 {
				 if((board[movePosY][movePosX]==null)||(board[movePosY][movePosX].getPlayer().toString().equals("White")))
					{
		
						return true;
					}
			 }
			 
		 }
		 if(movePosX-xPos==-2)
		 {
			
			 if(movePosY-yPos==1)
			 {
				 if((board[movePosY][movePosX]==null)||(board[movePosY][movePosX].getPlayer().toString().equals("White")))
					{

						return true;
					}
			 }
			 if(movePosY-yPos==-1)
			 {
				 if((board[movePosY][movePosX]==null)||(board[movePosY][movePosX].getPlayer().toString().equals("White")))
					{

						return true;
					}
			 }
			
			
		 }
		
	
		 return false;
}

}
