package chess;

import java.util.ArrayList;

public class White extends Player


	{
		
		pawn p1;
		pawn p2;
		pawn p3;
		pawn p4;
		pawn p5;
		pawn p6;
		pawn p7;
		pawn p8;
		
		king k; 
		queen q;
		knight kn;
		bishop b;
		rook r; 
		ArrayList <Piece> whitePieces;
		boolean isPerson;
		
		public White()
		{

			this.isPerson=isPerson;
			whitePieces=new ArrayList<Piece>();
		}
		
		public void setPerson(boolean isPerson)
		{
			this.isPerson=isPerson;
		}
		public boolean getPerson()
		{
			return isPerson;
		}
		public  String toString()
		{
			return "White";
		}
	
		
		public  void addPieces(ArrayList<Piece> playerPieces)
		{
			this.whitePieces=playerPieces;
		
		}
		public Piece getPiece(int i)
		{
			return whitePieces.get(i);
		}
		public ArrayList<Piece> getPieceList()
		{
			return whitePieces;
		}
		public int[][] move(Piece[][] board, ArrayList<Piece> wpcs, Black blk )
		{
			int [][] tempArr= new int[8][8];
			
			
			int bKingXPos=-1;
			int bKingYPos=-1;
			
			int bQueenXPos=-1;
			int bQueenYPos=-1;
			
			int bRookXPos=-1;
			int bRookYPos=-1;
			
			int bBishopXPos=-1;
			int bBishopYPos=-1;
			
			int bKnightXPos=-1;
			int bKnightYPos=-1;
			
			int bPawnXPos=-1;
			int bPawnYPos=-1;
			
			Piece[][] temp=new Piece[8][8];
			int[][] tempInt=new int[8][8];
			temp=board;
			boolean [] bestEnemyPieces= new boolean[5];

			
			
			for(int i=0; i<blk.getPieceList().size(); i++)
			{
				if (blk.getPiece(i).toString().equals("!K!]"))
				{
					 bKingXPos=blk.getPiece(i).getX();
					 bKingYPos=blk.getPiece(i).getY();
				}
				if(bKingXPos!=-1)
				{
					for(int p=0; i<8; i++)
					{
						for(int j=0; j<8; j++)
						{
							
							if(blk.getPiece(i).move(board, p, j)==true)
							{
								board[p][j]=null;
								board[p][j]=blk.getPiece(i);
								
								//if any white piece can move to put the king in check, do that move.
								//else if
								// find the most valuable black piece, if white can capture that piece do that move. 
								//else
								//make a move toward one of the black pieces without putting the white piece in danger.
								if(blk.getPiece(i).move(board, bKingXPos , bKingYPos)==true)
								{
									//need a method that gets the last spot in this array and returns an int value that will 
									//be converted into coordinates.
									return tempInt;
								}
								//checking the rest of the board to find the most valuable piece available
								else if( board[i][j].toString().equals("[!q!]"))
								{
									bQueenXPos=j;
									bQueenYPos=i;
									bestEnemyPieces[0]=true;
								}
								else if( board[i][j].toString().equals("[!r!]"))
								{
									bRookXPos=j;
									bRookYPos=i;
									bestEnemyPieces[1]=true;
								}
								else if( board[i][j].toString().equals("[!b!]"))
								{
									bBishopXPos=j;
									bBishopYPos=i;
								
									bestEnemyPieces[2]=true;
								}
								else if( board[i][j].toString().equals("[!k!]"))
								{
									bKnightXPos=j;
									bKnightYPos=i;
									bestEnemyPieces[3]=true;
								}
								else if( board[i][j].toString().equals("[!p!]"))
								{
									bPawnXPos=j;
									bPawnYPos=i;
									bestEnemyPieces[4]=true;
								}
								
								
								
							
								
								
							//	else if(blk.getPiece(i).move(board, i//random int ,//random int j)==true)
								{
									
								}
								
							}
						}
					}
					int count=0;
					while(bestEnemyPieces[count]!=false)
					{
						//found the best move to make;
						if(bestEnemyPieces[0]==true)
						{
							if(bQueenXPos!=-1)
							{
								int [][] suggestedMove=new int[bQueenYPos][bQueenXPos];
								return suggestedMove;
							}
							else if(bRookXPos!=-1)
							{
								int [][] suggestedMove=new int[bRookYPos][bRookXPos];
								return suggestedMove;
							}
							else if(bBishopXPos!=-1)
							{
								int [][] suggestedMove=new int[bBishopYPos][bBishopXPos];
								return suggestedMove;
							}
							else if(bPawnXPos!=-1)
							{
								int [][] suggestedMove=new int[bPawnYPos][bPawnXPos];
								return suggestedMove;
							}
							
							
						}
						
						if(count<4)
						{
							count++;
						}
					}
					//if()
				
					while(true)
					{
						int xMovePos=randomNumGenerator(0, 8);
						int yMovePos=randomNumGenerator(0, 8);
						for(int k=0; k<blk.getPieceList().size(); k++)
						{
							//if not null statement here
							if(blk.getPiece(k).move(board, xMovePos , yMovePos)==true)
							{
							
								int [][] suggestedMove=new int[yMovePos][xMovePos];
								return suggestedMove;

							
							}
						}
						
					}
				}
				
			}
			//if any white piece can move to put the king in check, do that move.
			//else if
			// find the most valuable black piece, if white can capture that piece do that move. 
			//else
			//make a move toward one of the black pieces without putting the white piece in danger.
			
			
			
			
			
			int [][] suggestedMove=new int[bPawnYPos][bPawnXPos];
			return suggestedMove;
		}
		
		public int randomNumGenerator(int minValue, int maxValue)
		{
			// returns a positive random number <=8
		if (minValue == 0) {
			minValue = 1;
		}
		double increment = maxValue / minValue;
		int count = minValue;
		int rndm = -1;

		double dub = Math.random();

		while (rndm != -1) {
			if (dub <= increment) {
				rndm = count;
				return count;
			} else {
				increment += increment;
				count++;
			}
		}
		return -2;
	}
}
