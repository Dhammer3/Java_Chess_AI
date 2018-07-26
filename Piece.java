package chess;

import java.util.NoSuchElementException;

public abstract class Piece  
{
	Player play;
	Piece p;
	int xPos;
	int yPos;
	int moveCount;
	int value;
	public Piece()
	{
		
	}
	public abstract Player getPlayer();


	public abstract String toString();
	
	public int getX(Piece p, Piece[][] board)
	{
		for(int x=0; x<8; x++)
		{
			for(int y=0; y<8; y++)
			{
			
				if (board[x][y]==p)
				{
					return y;
				}						
			
			}
		}
		throw new NoSuchElementException();
	}
	public int getY(Piece p, Piece[][] board)
	{
		for(int x=0; x<8; x++)
		{
			for(int y=0; y<8; y++)
			{
				//found the pawn
				if (board[x][y]==p)
				{
					return x;
				}						
			
			}
		}
		throw new NoSuchElementException();
	}
	
	//public abstract boolean isValueTrue(board gameBoard, Piece p , int movePos, int movePosX );
	
	public abstract boolean  move(Piece[][] board, int movePos, int movePosX );

	//public abstract int[][] currentLocation(int xPos, int yPos);

	
	public void updatePos(int x, int y)
	{
		this.xPos=x;
		this.yPos=y;
	}
	public int getX()
	{
		return this.xPos;
	}
	public int getY()
	{
		return this.yPos;
	}
	public void moveCounter()
	{
		this.moveCount++;
	}
	public  int getMoveCount()
	{
		return this.moveCount;
	}
	public void setValue(int a)
	{
		this.value=a;
	}
	public abstract int getValue();

	//public boolean errorHandling()
	
	

	
	
	
	
}
