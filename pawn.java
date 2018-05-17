package chess;

import java.util.NoSuchElementException;

public class pawn extends Piece
{

	int moveCount=0;
	Player play;

	

	
	public pawn(Player play)
	{
	this.play=play;
	}
	
	public pawn()
	{
	play=null;
	moveCount=0;
	
	}
	public  Player getPlayer()
	{
		return play;
	}

	public  String toString()
	{
		String k="";
		if(this.getPlayer().toString().equals("White"))
		{
		 k="[*p*]";
		}
		else
		{
		k="[!p!]";
		}
		return k;
	}
	public int getCount()
	{
		return moveCount; 
	}
	//fix, can move pawn backword, //can move two spaces when not in spawn. 
	public boolean move(board gameBoard, int movePosX, int movePosY)
	{
		//cant move backward
		Piece [][] board = new Piece[8][8];
		gameBoard.copyBoard(board);
	
		int xPos=getX(this, board);
		
		int yPos=getY(this, board);
		System.out.println("xpos:"+xPos);
		System.out.println("ypos:"+yPos);
		System.out.println("movePosx:"+movePosX);
		System.out.println("movePosy:"+movePosY);
		
		//start off with some error handling
	
		//the only time the player would be moving in X direction would be to capture a piece.
		/*
		if (movePosX!=xPos)//&&(canCapture(gameBoard, p)!=true))
		{
			return false;
		}*/
		
		//if its not in the spawn but trying to move 2 space in y-direction.
		if(movePosX-xPos>1)
		{
			System.out.println("That is not a valid move");
			return false;
		}
		if(movePosX-xPos<-1)
		{
			System.out.println("That is not a valid move");
			return false;
		}
		if ((board[movePosY][movePosX]==null)&&(movePosX!=xPos))
		{
			System.out.println("can capture false");
			return false;
		}
		
		if (this.getPlayer().toString().equals("White")) {
			
			if(movePosY>yPos)
			{
				System.out.println("Cannot move the pawn backwards");
				return false;
			}
			
			if (movePosX != xPos)
			{

				if ((movePosX - xPos == 1)&&(movePosY-yPos==-1)) {
					try {

						if (board[movePosY][movePosX].getPlayer().toString().equals("Black")) {

							//System.out.println("Here");
							return true;
							
						}
						else
						{
							System.out.println("Cannot capture your own piece!");
							return false;
						}
					} catch (NullPointerException e) {
						return false;
					}
				}

				if ((movePosX - xPos == -1)&&(movePosY-yPos==-1)) 
				{
					try {
						
						if (board[movePosY][movePosX].getPlayer().toString().equals("Black")) {

							System.out.println("Here");
							return true;
						}
						else
						{
							System.out.println("Cannot capture your own piece!");
							return false;
						}
					} catch (NullPointerException e) {
						return false;
					}
				} 
				

			}

			if ((isInSpawn(gameBoard) == true) && (yPos - movePosY == 2)) {
				if((board[movePosY][movePosX]==null)&&(board[movePosY+1][movePosX]==null))
				{
				
				return true;
				}
			}
			if ((isInSpawn(gameBoard) == true) && (yPos - movePosY == 1)) {
				if(board[movePosY][movePosX]==null)
				{
				
				return true;
				}
			}
			if ((isInSpawn(gameBoard) == false) && (yPos - movePosY == 1)) {
				if(board[movePosY][movePosX]==null)
				{
			
				return true;
				}
			}
			System.out.println("here");
		
				
		}
if (this.getPlayer().toString().equals("Black")) {
			

	if(movePosY<yPos)
	{
		System.out.println("Cannot move the pawn backwards");
		return false;
		
	}
		
		
		if (movePosX != xPos)
		{

			if ((movePosX - xPos == 1)&&(movePosY-yPos==1))  {
				try {

					if (board[movePosY][movePosX].getPlayer().toString().equals("White")) {

						return true;
					}
					else
					{
						System.out.println("Cannot capture your own piece!");
						return false;
					}
				} catch (NullPointerException e) {
					return false;
				}
			}

			if ((movePosX - xPos == -1) &&(movePosY-yPos==1)) 
			{
				try {
					
					if (board[movePosY][movePosX].getPlayer().toString().equals("White")) {

						
						return true;
					}
					else
					{
						System.out.println("Cannot capture your own piece!");
						return false;
					}
				} catch (NullPointerException e) {
					return false;
				}
			} 
			

		}

		if ((isInSpawn(gameBoard) == true) && (movePosY-yPos == -2)) {
			if((board[movePosY][movePosX]==null)&&(board[movePosY-1][movePosX]==null))
			{
				
			
			return true;
			}
		}
		if ((isInSpawn(gameBoard) == true) && (movePosY-yPos  == -1)) {
			if(board[movePosY][movePosX]==null)
			{
		
			return true;
			}
		}
		if ((isInSpawn(gameBoard) == false) && (movePosY-yPos  == -1)) {
			if(board[movePosY][movePosX]==null)
			{
	
			return true;
			}
		}
	
	
			
	}
	


	System.out.println("that is not a valid move");
		return false;
	}
	//check
	


	//works
	public boolean isInSpawn(board gameBoard)
	{
		Piece [][] board= new Piece[8][8];
		gameBoard.copyBoard(board);
		
		for(int x=0; x<8; x++)
		{
			for(int y=0; y<8; y++)
			{
					//if the pawn is white  
					if(this.getPlayer().toString().equals("White"))
					{
						
						//white piece in the white spawn
						if (board[x][y]==this)
						{
							//
							if (x==6)
							{
							
								return true; 
							}
							
						}
					}
					//if the pawn is black  
					if(this.getPlayer().toString().equals("Black"))
					{
						
						//black pawn in the black spawn
						if (board[x][y]==this)
						{
							if (x==1)
							{
								System.out.println("true");
								return true; 
							}
						
						}
					}								
			}
		}
		return false;
	}


	



}

