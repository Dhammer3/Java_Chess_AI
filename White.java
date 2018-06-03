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
		public int[][] move(Piece[][] board, White wht, Black blk )
		{
			int [][] tempArr= new int[8][8];
			int[][] tempInt=new int[8][8];
			
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
			temp=board;
		
			temp=board;
			boolean [] bestEnemyPieces= new boolean[5];

			
			//create a list of the most valuable black pieces
			
			for(int i=0; i<blk.getPieceList().size(); i++)
			{
				if (blk.getPiece(i).toString().equals("!K!]"))
				{
					 bKingXPos=blk.getPiece(i).getX();
					 bKingYPos=blk.getPiece(i).getY();
				}
				
				//checking the rest of the board to find the most valuable piece available
				else if(blk.getPiece(i).toString().equals("[!q!]"))
				{
					bQueenXPos=blk.getPiece(i).getX();
					bQueenYPos=blk.getPiece(i).getY();
					bestEnemyPieces[0]=true;
				}
				else if( blk.getPiece(i).toString().equals("[!r!]"))
				{
					bRookXPos=blk.getPiece(i).getX();
					bRookYPos=blk.getPiece(i).getY();
					bestEnemyPieces[1]=true;
				}
				else if( blk.getPiece(i).toString().equals("[!b!]"))
				{
					bBishopXPos=blk.getPiece(i).getX();
					bBishopYPos=blk.getPiece(i).getY();
				
					bestEnemyPieces[2]=true;
				}
				else if(blk.getPiece(i).toString().equals("[!k!]"))
				{
					bKnightXPos=blk.getPiece(i).getX();
					bKnightYPos=blk.getPiece(i).getY();
					bestEnemyPieces[3]=true;
				}
				else if( blk.getPiece(i).toString().equals("[!p!]"))
				{
					bPawnXPos=blk.getPiece(i).getX();
					bPawnYPos=blk.getPiece(i).getY();
					bestEnemyPieces[4]=true;
				}
			}
			boolean switch1=false;
			int count=0;
				while (count<wht.getPieceList().size())
				{
					for(int p=0; p<8; p++)
					{
						for(int j=0; j<8; j++)
						{
							for(int y=0; y<wht.getPieceList().size(); y++)
							{
							if(wht.getPiece(y).move(temp, p, j)==true)
							{
								temp[p][j]=null;
								temp[p][j]=wht.getPiece(y);
							
									if(wht.getPiece(y).move(temp, bKingXPos , bKingYPos)==true)
									{
										//need a method that gets the last spot in this array and returns an int value that will 
										//be converted into coordinates.
										int[][] suggestedMove=new int[p][j];
										switch1 =true;
										return tempInt;
									}
									int counter=0;
									while(counter<4)
									{
										//found the best move to make;
										
											if(bQueenXPos!=-1)
											{
												if(wht.getPiece(y).move(temp, bQueenXPos , bQueenYPos)==true)
												{
												//if(blk.getPiece(i))
												int [][] suggestedMove=new int[bQueenYPos][bQueenXPos];
												return suggestedMove;
												}
											}
											else if(bRookXPos!=-1)
											{
												if(wht.getPiece(y).move(temp, bRookXPos , bRookYPos)==true)
												{
												//if(blk.getPiece(i))
													int [][] suggestedMove=new int[bRookYPos][bRookXPos];
													return suggestedMove;
												}
												
											}
											else if(bBishopXPos!=-1)
											{
												if(wht.getPiece(y).move(temp, bBishopXPos , bBishopYPos)==true)
												{
												//if(blk.getPiece(i))
													int [][] suggestedMove=new int[bBishopYPos][bBishopXPos];
													return suggestedMove;
												}
												
											}
											else if(bPawnXPos!=-1)
											{
												if(wht.getPiece(y).move(temp, bPawnXPos , bPawnYPos)==true)
												{
												//if(blk.getPiece(i))
													int [][] suggestedMove=new int[bPawnYPos][bPawnXPos];
													return suggestedMove;
												}
									
											}
											if(y==wht.getPieceList().size())
											{
												y=randomNumGenerator(1, 8);
												while(true)
												{
													int x=(randomNumGenerator(0,7));
													int l=(randomNumGenerator(0,7));
													if(wht.getPiece(randomNumGenerator(0,wht.getPieceList().size())).move(board, x, l )==true)
													{
														int [][] suggestedMove=new int[x][l];
														return suggestedMove;
													}
												}
											}
											counter++;
										
									}
									
									
									
								
									
									//return tempInt;
								
								
								else
								{
								
								//if any white piece can move to put the king in check, do that move.
								//else if
								// find the most valuable black piece, if white can capture that piece do that move. 
								//else
								//make a move toward one of the black pieces without putting the white piece in danger.
								
								
								
								
							
								
								
							//	else if(blk.getPiece(i).move(board, i//random int ,//random int j)==true)
						{

						}

					}
							}
							
							
				}
			}
		}
					count++;
				}
					
					
					//if()
				
					while(true)
					{
						int xMovePos=randomNumGenerator(0, 8);
						int yMovePos=randomNumGenerator(0, 8);
						for(int k=0; k<blk.getPieceList().size(); k++)
						{
							//if not null statement here
							if(blk.getPiece(k)!=null)
							{
								
							
							if(blk.getPiece(k).move(board, xMovePos , yMovePos)==true)
							{
							
								int [][] suggestedMove=new int[yMovePos][xMovePos];
								return suggestedMove;

							
							}
							}
						}
						break;
					}
					
					int [][] suggestedMove=new int[-1][-1];
					return suggestedMove;
		}
						
						
					
				
				
			
			//if any white piece can move to put the king in check, do that move.
			//else if
			// find the most valuable black piece, if white can capture that piece do that move. 
			//else
			//make a move toward one of the black pieces without putting the white piece in danger.
			
			
			
			
		
			
		
		public int randomNumGenerator(int minValue, int maxValue)
		{
			// returns a positive random number <=8

		double increment = maxValue - minValue;
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
		/*
		if(blk.getPiece(y).toString().equals("[!q!]"))
		{
			int pieceSearcher=0;
			while(wht.getPiece(pieceSearcher)!=null)
				
			{
				if (wht.getPiece(pieceSearcher).move(board, blk.getPiece(y).getX(), blk.getPiece(y).getY()))
				{
					int[][] suggestedMove=new int[p][j];
					return suggestedMove;
				}
				else
				{
					pieceSearcher++;
				}
			
			}
		}
		if(blk.getPiece(y).toString().equals("[!r!]"))
		{
			int pieceSearcher=0;
			while(wht.getPiece(pieceSearcher)!=null)
				
			{
				if (wht.getPiece(pieceSearcher).move(board, blk.getPiece(y).getX(), blk.getPiece(y).getY()))
				{
					int[][] suggestedMove=new int[p][j];
					return suggestedMove;
				}
				else
				{
					pieceSearcher++;
				}
			
			}
		}
		if(blk.getPiece(y).toString().equals("[!b!]"))
		{
			int pieceSearcher=0;
			while(wht.getPiece(pieceSearcher)!=null)
				
			{
				if (wht.getPiece(pieceSearcher).move(board, blk.getPiece(y).getX(), blk.getPiece(y).getY()))
				{
					int[][] suggestedMove=new int[p][j];
					return suggestedMove;
				}
				else
				{
					pieceSearcher++;
				}
			
			}
		}
		if(blk.getPiece(y).toString().equals("[!k!]"))
		{
			int pieceSearcher=0;
			while(wht.getPiece(pieceSearcher)!=null)
				
			{
				if (wht.getPiece(pieceSearcher).move(board, blk.getPiece(y).getX(), blk.getPiece(y).getY()))
				{
					int[][] suggestedMove=new int[p][j];
					return suggestedMove;
				}
				else
				{
					pieceSearcher++;
				}
			
			}
		}
		if(blk.getPiece(y).toString().equals("[!p!]"))
		{
			int pieceSearcher=0;
			while(wht.getPiece(pieceSearcher)!=null)
				
			{
				if (wht.getPiece(pieceSearcher).move(board, blk.getPiece(y).getX(), blk.getPiece(y).getY()))
				{
					int[][] suggestedMove=new int[p][j];
					return suggestedMove;
				}
				else
				{
					pieceSearcher++;
				}
			
			}
		}
		*/
}
