package chess;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;

public  class  board 
{



	Piece [][] board;
	
	int count;
	Player white;
	Player black;
	
	Piece wp;
	Piece wp2;
	Piece wp3;
	Piece wp4;
	Piece wp5;
	Piece wp6;
	Piece wp7;
	Piece wp8;
	
	Piece wr;
	Piece wr2;
	
	Piece wkn;
	Piece wkn2;
	
	Piece wb;
	Piece wb2;
	 
	Piece wk;
	Piece wk2;
	
	Piece wq;

	
	Piece bp;
	Piece bp2;
	Piece bp3;
	Piece bp4;
	Piece bp5;
	Piece bp6;
	Piece bp7;
	Piece bp8;
	
	Piece br;
	Piece br2;
	
	Piece bkn;
	Piece bkn2;
	
	Piece bb;
	Piece bb2;
	 
	Piece bk;
	Piece bk2;
	
	Piece bq;
	
	
	public board()
	{
		
		white=new White();
		black=new Black();
		board=new Piece[8][8];
		
		wp=new pawn(white);
		wp2=new pawn(white);
		wp3=new pawn(white);
		wp4=new pawn(white);
		wp5=new pawn(white);
		wp6=new pawn(white);
		wp7=new pawn(white);
		wp8=new pawn(white);
		
		wr=new rook(white);
		wr2=new rook(white);
		wkn=new knight(white);
		wkn2=new knight(white);
		wb=new bishop(white);
		wb2=new bishop(white);
		wk=new king(white);
		wk2=new king(white);
		wq=new queen(white);
		
		
		bp=new pawn(black);
		bp2=new pawn(black);
		bp3=new pawn(black);
		bp4=new pawn(black);
		bp5=new pawn(black);
		bp6=new pawn(black);
		bp7=new pawn(black);
		bp8=new pawn(black);
		
		br=new rook(black);
		br2=new rook(black);
		bkn=new knight(black);
		bkn2=new knight(black);
		bb=new bishop(black);
		bb2=new bishop(black);
		bk=new king(black);
		bk2=new king(black);
		bq=new queen(black);
		for(int i=0; i<8; i++)
		{
			//y
			for(int j=0; j<8; j++)
			{
				
				if ((j==0)&&(i==0))
				{
					board[i][j]=br;
					j++;
					
					board[i][j]=bkn;
					j++;
					board[i][j]=bb;
					j++;
					board[i][j]=bk;
					j++;
					board[4][4]=bq;
					j++;
					board[i][j]=bb2;
					j++;
					board[i][j]=bkn2;
					j++;
					board[i][j]=br2;
					j++;
					
				}
				if ((i==1)&&(j==0))
				{
					
						board[5][5]=bp;
						j++;
						board[i][j]=bp2;
						j++;
						board[i][j]=bp3;
						j++;
						board[i][j]=bp4;
						j++;
						board[i][j]=bp5;
						j++;
						board[i][j]=bp6;
						j++;
						board[i][j]=bp7;
						j++;
						board[i][j]=bp8;
						j++;
				}
				if ((i==7)&&(j==0))
				{
					board[i][j]=wr;
					j++;
				
					board[i][j]=wkn;
					j++;
					board[i][j]=wb;
					j++;
					board[i][j]=wk;
					j++;
					board[i][j]=null;//wq;
					j++;
					board[i][j]=wb2;
					j++;
					board[i][j]=wkn2;
					j++;
					board[3][3]=wr2;
					j++;
					
				}
				if ((i==6)&&(j==0))
				{
					
					board[i][j]=wp;
					j++;
					board[i][j]=wp2;
					j++;
					board[i][j]=wp3;
					j++;
					board[i][j]=wp4;
					j++;
					board[i][j]=wp5;
					j++;
					board[i][j]=wp6;
					j++;
					board[i][j]=wp7;
					j++;
					board[i][j]=wp8;
					j++;
				}
				//board[6][1]=wr;
			}
		}
		
	}

	
	public  void newBoard()
	{
		
		//x
		for(int i=0; i<8; i++)
		{
			//y
			for(int j=0; j<8; j++)
			{
				
				if ((j==0)&&(i==0))
				{
					board[i][j]=br;
					j++;
					
					board[i][j]=bkn;
					j++;
					board[i][j]=bb;
					j++;
					board[i][j]=bk;
					j++;
					board[i][j]=bq;
					j++;
					board[i][j]=bb2;
					j++;
					board[i][j]=bkn2;
					j++;
					board[i][j]=br2;
					j++;
					
				}
				if ((i==1)&&(j==0))
				{
					
						board[i][j]=bp;
						j++;
						board[i][j]=bp2;
						j++;
						board[i][j]=bp3;
						j++;
						board[i][j]=bp4;
						j++;
						board[i][j]=bp5;
						j++;
						board[i][j]=bp6;
						j++;
						board[i][j]=bp7;
						j++;
						board[i][j]=bp8;
						j++;
				}
				if ((i==7)&&(j==0))
				{
					board[i][j]=wr;
					j++;
				
					board[i][j]=wkn;
					j++;
					board[i][j]=wb;
					j++;
					board[i][j]=wk;
					j++;
					board[i][j]=wq;
					j++;
					board[i][j]=wb2;
					j++;
					board[i][j]=wkn2;
					j++;
					board[i][j]=wr2;
					j++;
					
				}
				if ((i==6)&&(j==0))
				{
					
					board[i][j]=wp;
					j++;
					board[i][j]=wr;//wp2;
					j++;
					board[i][j]=wp3;
					j++;
					board[i][j]=wp4;
					j++;
					board[i][j]=wp5;
					j++;
					board[i][j]=wp6;
					j++;
					board[i][j]=wp7;
					j++;
					board[i][j]=wp8;
					j++;
				}
				
			}
		}
		
	}
	//public static Piece[][] updateBoard(Piece[][] board)
	//fix
	public  void printBoard()
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
				if(board[i][j]==null)
				{
					System.out.print("[   ]");
				}
				else
				{
					System.out.print(board[i][j].toString());
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
	
	public void updateBoard(Piece [][] arr)
	{
			this.board=arr;	
			kingInCheck();
		
	}
	//FINISH CHECKMATE CONDITION
	//returns 0 if neither king is in check
	//returns -1 if black king is in check
	//returns -2 if black king is in checkmate
	//returns 1 if white king is in check
	//returns 2 if the white king is in checkmate
	public int kingInCheck()
	{
		
	Queue <Piece> bq = new LinkedList<Piece>();
	Queue <Piece> wq = new LinkedList<Piece>();
	
	int bkXPos=0;
	int bkYPos=0;
	
	int wkXPos=0;
	int wkYPos=0;
	
	for(int i=0; i<8; i++)
	{
		for (int j=0; j<8; j++)
		{
			if(board[i][j]!=null)
			{
				if(board[i][j].getPlayer().toString().equals("White"))
				{
					if(board[i][j].toString().equals("[*K*]"))
					{
						wkXPos=j;
						System.out.println(wkXPos);
						wkYPos=i;
					}
					wq.offer(board[i][j]);
				}
				if(board[i][j].getPlayer().toString().equals("Black"))
				{
					if(board[i][j].toString().equals("[!K!]"))
					{
						bkXPos=j;
						bkYPos=i;
					}
					}
					bq.offer(board[i][j]);
				}
			}
		}
	
	while(wq.peek()!=null)
	{
	
		wq.peek().getX(wq.peek(), board);
		if(wq.peek().move(this, bkXPos, bkYPos)==true)
		{
		
		
			System.out.println(wq.peek().toString());
			System.out.println("The Black King is in Check!");
		
		}
		wq.remove();
	}
	while(bq.peek()!=null)
	{
		
		if(bq.peek().move(this, wkXPos, wkYPos)==true)
		{
			System.out.println(bq.peek().toString());
			System.out.println("The White King is in Check!");
			
		}
		bq.remove();
	}
		return 0;
}

public Piece [][] getBoard()
{
	return board;
}


public Piece getPiece(int x, int y)
{
	//y=y-1;
	if(board[y][x]==null)
	{
		System.out.println("You didnt select a Piece");
		return null;
	}
	return board[y][x];
}


public void copyBoard(Piece[][] arr)
{
	for(int i=0; i<8; i++)
	{
		for (int j=0; j<8; j++)
		{
			arr[i][j]=board[i][j];
		}
	}
}
}
	

