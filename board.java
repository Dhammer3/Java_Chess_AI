package chess;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;

public  class  board 
{



	ArrayList <Piece> whitePieces;
	ArrayList <Piece> blackPieces;
	Piece [][] board;
	Piece [][] prev;
	
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
		whitePieces=new ArrayList<Piece>();
		blackPieces=new ArrayList<Piece>();
		white=new White();
		black=new Black();
		board=new Piece[8][8];
		
		wp=new pawn(white);  
		whitePieces.add(wp);
		wp2=new pawn(white); 
		whitePieces.add(wp2);
		wp3=new pawn(white); 
		whitePieces.add(wp3);
		wp4=new pawn(white);
		whitePieces.add(wp4);
		wp5=new pawn(white);
		whitePieces.add(wp5);
		wp6=new pawn(white);
		whitePieces.add(wp6);
		wp7=new pawn(white);
		whitePieces.add(wp7);
		wp8=new pawn(white);
		whitePieces.add(wp8);
		
		wr=new rook(white);
		whitePieces.add(wr);
		wr2=new rook(white);
		whitePieces.add(wr2);
		wkn=new knight(white);
		whitePieces.add(wkn);
		wkn2=new knight(white);
		whitePieces.add(wkn2);
		wb=new bishop(white);
		whitePieces.add(wb);
		wb2=new bishop(white);
		whitePieces.add(wb2);
		wk=new king(white);
		whitePieces.add(wk);
		wq=new queen(white);
		whitePieces.add(wq);
		
	//	white.addPieces(whitePieces);
		
		
		
		bp=new pawn(black);
		blackPieces.add(bp);
		bp2=new pawn(black);
		blackPieces.add(bp2);
		bp3=new pawn(black);
		blackPieces.add(bp3);
		bp4=new pawn(black);
		blackPieces.add(bp4);
		bp5=new pawn(black);
		blackPieces.add(bp5);
		bp6=new pawn(black);
		blackPieces.add(bp6);
		bp7=new pawn(black);
		blackPieces.add(bp7);
		bp8=new pawn(black);
		blackPieces.add(bp8);
		
		br=new rook(black);
		blackPieces.add(br);
		br2=new rook(black);
		blackPieces.add(br2);
		bkn=new knight(black);
		blackPieces.add(bkn);
		bkn2=new knight(black);
		blackPieces.add(bkn2);
		bb=new bishop(black);
		blackPieces.add(bb);
		bb2=new bishop(black);
		blackPieces.add(bb2);
		bk=new king(black);
		blackPieces.add(bk);
		bq=new queen(black);
		blackPieces.add(bq);
		
		for(int i=0; i<8; i++)
		{
			//y
			for(int j=0; j<8; j++)
			{
				
				if ((j==0)&&(i==0))
				{
					board[i][j]=br;
					board[i][j].updatePos(j, i);
					j++;
					
					board[i][j]=bkn;
					board[i][j].updatePos(j, i);
					j++;
					board[i][j]=bb;
					board[i][j].updatePos(j, i);
					j++;
					board[i][j]=bk;
					board[i][j].updatePos(j, i);
					j++;
					board[i][j]=bq;
					board[i][j].updatePos(j, i);
					j++;
					board[i][j]=bb2;
					board[i][j].updatePos(j, i);
					j++;
					board[i][j]=bkn2;
					board[i][j].updatePos(j, i);
					j++;
					board[i][j]=br2;
					board[i][j].updatePos(j, i);
					j++;
					
				}
				if ((i==1)&&(j==0))
				{
					
						board[i][j]=bp;
						board[i][j].updatePos(j, i);
						j++;
						board[i][j]=bp2;
						board[i][j].updatePos(j, i);
						j++;
						board[i][j]=bp3;
						board[i][j].updatePos(j, i);
						j++;
						board[i][j]=bp4;
						board[i][j].updatePos(j, i);
						j++;
						board[i][j]=bp5;
						board[i][j].updatePos(j, i);
						j++;
						board[i][j]=bp6;
						board[i][j].updatePos(j, i);
						j++;
						board[i][j]=bp7;
						board[i][j].updatePos(j, i);
						j++;
						board[i][j]=bp8;
						board[i][j].updatePos(j, i);
						j++;
				}
				if ((i==7)&&(j==0))
				{
					board[i][j]=wr;
					board[i][j].updatePos(j, i);
					
					j++;
					board[i][j]=wkn;
					board[i][j].updatePos(j, i);
					j++;
					board[i][j]=wb;
					board[i][j].updatePos(j, i);
					j++;
					board[i][j]=wk;
					board[i][j].updatePos(j, i);
					j++;
					board[i][j]=wq;
					board[i][j].updatePos(j, i);
					j++;
					board[i][j]=wb2;
					board[i][j].updatePos(j, i);
					j++;
					board[i][j]=wkn2;
					board[i][j].updatePos(j, i);
					j++;
					board[i][j]=wr2;
					board[i][j].updatePos(j, i);
					j++;
					
				}
				if ((i==6)&&(j==0))
				{
					
					board[i][j]=wp;
					board[i][j].updatePos(j, i);
					j++;
					board[i][j]=wp2;
					board[i][j].updatePos(j, i);
					j++;
					board[i][j]=wp3;
					board[i][j].updatePos(j, i);
					j++;
					board[i][j]=wp4;
					board[i][j].updatePos(j, i);
					j++;
					board[i][j]=wp5;
					board[i][j].updatePos(j, i);
					j++;
					board[i][j]=wp6;
					board[i][j].updatePos(j, i);
					j++;
					board[i][j]=wp7;
					board[i][j].updatePos(j, i);
					j++;
					board[i][j]=wp8;
					board[i][j].updatePos(j, i);
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
	
	public void updateBoard( Piece [][] arr)
	{	
			kingInCheck();
			this.board=arr;	
	}
	public void setPrev( Piece [][] arr)
	{	
			this.prev=arr;	
	}
	public Piece[][] getPrev()
	{
		return prev;
	}
	//FINISH CHECKMATE CONDITION
	//returns 0 if neither king is in check
	//returns -1 if black king is in check
	//returns -2 if black king is in checkmate
	//returns 1 if white king is in check
	//returns 2 if the white king is in checkmate
	public int kingInCheck()
	{
		boolean one=false;
		boolean two=false;
		boolean three=false;
		boolean four=false;
		boolean five=false;
		boolean six=false;
		boolean seven=false;
		boolean eight=false;
		int piecesPuttingInCheck=0;
	//these queues look for the in check condition.	
	Queue <Piece> bq = new LinkedList<Piece>();
	Queue <Piece> wq = new LinkedList<Piece>();
	
	//these queues look for the checkmate condition.
	Queue <Piece> bq2 = new LinkedList<Piece>();
	Queue <Piece> wq2 = new LinkedList<Piece>();
	Queue <Piece> wq3 = new LinkedList<Piece>();
	
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
						wkYPos=i;
					}
					wq.offer(board[i][j]);
					wq2.offer(board[i][j]);
					wq3.offer(board[i][j]);
				}
				if(board[i][j].getPlayer().toString().equals("Black"))
				{
					if(board[i][j].toString().equals("[!K!]"))
					{
						bkXPos=j;
						bkYPos=i;
					}
					bq.offer(board[i][j]);
					bq2.offer(board[i][j]);
				}
					
				}
			}
		}
	
	while(wq.peek()!=null)
	{
	
		//wq.peek().getX();
		
		if(wq.peek().move(board, bkXPos, bkYPos)==true)
		{
			System.out.println(wq.peek().toString());
			System.out.println("The Black King is in Check!");
			piecesPuttingInCheck++;
			
		
		
		 
			//wq.clear();
			boolean switch1 =false;
		while(wq2.peek()!=null)
		{
			
			if(switch1 ==false)
			{
				if(wq.peek().toString().equals("[*r*]"))
				{
					if(wq.peek().getX()==bkXPos)
					{
						six=true;
						eight=true;
					}
					else if(wq.peek().getY()==bkYPos)
					{
						five=true;
						seven=true;
					}
				}
				else if(wq.peek().toString().equals("[*b*]"))
				{
					if(wq.peek().getX()<bkXPos)
					{
						if(wq.peek().getY()<bkYPos)
						{
							two=true;
							four=true;
						}
						else if(wq.peek().getY()>bkYPos)
						{
							one=true;
							two=true;
						}
					}
					else if(wq.peek().getX()>bkXPos)
					{
						if(wq.peek().getY()<bkYPos)
						{
							one=true;
							three=true;
						}
						else if(wq.peek().getY()>bkYPos)
						{
							four=true;
							two=true;
						}
					}
				}
				else if(wq.peek().toString().equals("[*q*]"))
			{
				if(wq.peek().getX()==bkXPos)
				{
					six=true;
					eight=true;
				}
				else if(wq.peek().getY()==bkYPos)
				{
					five=true;
					seven=true;
				}
				
				else if(wq.peek().getX()<bkXPos)
				{
					if(wq.peek().getY()<bkYPos)
					{
						two=true;
						four=true;
					}
					else if(wq.peek().getY()>bkYPos)
					{
						one=true;
						two=true;
					}
				}
				else if(wq.peek().getX()>bkXPos)
				{
					if(wq.peek().getY()<bkYPos)
					{
						one=true;
						three=true;
					}
					else if(wq.peek().getY()>bkYPos)
					{
						four=true;
						two=true;
					}
				}
			}
			switch1=true;
			}
					if (one == false) 
					{
						if (bk.move(board, bkXPos + 1, bkYPos + 1) == true) 
						{
							if (wq2.peek().move(board, bkXPos + 1, bkYPos + 1) == true)
							{
								one = true;
							}
						}
						else
						{
							one = true;
						}
					}
					if (two == false) {
						if (bk.move(board, bkXPos + 1, bkYPos - 1) == true) {
							if (wq2.peek().move(board, bkXPos + 1, bkYPos - 1) == true) {
								
								two = true;
							}

						}
						else
						{
							two = true;
						}
					}
					if (three == false) {
						if (bk.move(board, bkXPos - 1, bkYPos + 1) == true) {
							if (wq2.peek().move(board, bkXPos - 1, bkYPos + 1) == true) {
								three = true;
							}
						}
						else
						{
							three = true;
						}
					}
					if (four == false) {
						if (bk.move(board, bkXPos - 1, bkYPos - 1) == true) {
							if (wq2.peek().move(board, bkXPos - 1, bkYPos - 1) == true) {
								four = true;
							}
						}
						else
						{
							four = true;
						}
					}
					if (five == false) {
						if (bk.move(board, bkXPos + 1, bkYPos) == true) {
							if (wq2.peek().move(board, bkXPos + 1, bkYPos) == true) {
								five = true;
							}
						}
						else
						{
							five = true;
						}
					}
					if (six == false) {
						if (bk.move(board, bkXPos, bkYPos + 1) == true)
						{
							if (wq2.peek().move(board, bkXPos, bkYPos + 1) == true)
							{
								six = true;
							}
						}
						else
						{
							six = true;
						}
					}
					if (seven == false) {
						if (bk.move(board, bkXPos - 1, bkYPos) == true) {
							if (wq2.peek().move(board, bkXPos - 1, bkYPos ) == true) {
								seven = true;
							}
						}
						else
						{
							seven = true;
						}
					}
					if (eight == false) {
						if (bk.move(board, bkXPos, bkYPos - 1) == true)
						{
							if (wq2.peek().move(board, bkXPos, bkYPos - 1) == true) {
								eight = true;
							}
						}
						else
						{
							eight = true;
						}
					}
			wq2.remove();
			
		}
		
		if((one==true)&&(two==true)&&(three==true)&&(four==true)&&(five==true)&&(six==true)&&(seven==true)&&(eight==true))
		{
			//if any black pieces can capture the piece that is putting the king in check
			while(bq.peek()!=null)
			{
				if(bq.peek().move(board, wq.peek().getX(), wq.peek().getY())==true)
				{
					
					System.out.println("The Black King is in Check!!!!!!!!!!");
					return 1;
				}
				bq.remove();
			}
			System.out.println("CheckMate");
			
		}
		else
		{
			return 1;
		}
		
	}
		wq.remove();
}
	
	
	while(bq.peek()!=null)
	{
	
		bq.peek().getX();
		
		if(bq.peek().move(board, wkXPos, wkYPos)==true)
		{
			System.out.println(bq.peek().toString());
			
			
			
			
			boolean switch2=false;
		while(bq2.peek()!=null)
		{
			if(switch2 ==false)
			{
				if(bq.peek().toString().equals("[!r!]"))
				{
					if(bq.peek().getX()==bkXPos)
					{
						six=true;
						eight=true;
					}
					else if(bq.peek().getY()==bkYPos)
					{
						five=true;
						seven=true;
					}
				}
				else if(bq.peek().toString().equals("[!b!]"))
				{
					if(bq.peek().getX()<bkXPos)
					{
						if(bq.peek().getY()<bkYPos)
						{
							two=true;
							four=true;
						}
						else if(bq.peek().getY()>bkYPos)
						{
							one=true;
							two=true;
						}
					}
					else if(bq.peek().getX()>bkXPos)
					{
						if(bq.peek().getY()<bkYPos)
						{
							one=true;
							three=true;
						}
						else if(bq.peek().getY()>bkYPos)
						{
							four=true;
							two=true;
						}
					}
				}
				else if(bq.peek().toString().equals("[!q!]"))
			{
				if(bq.peek().getX()==bkXPos)
				{
					six=true;
					eight=true;
				}
				else if(bq.peek().getY()==bkYPos)
				{
					five=true;
					seven=true;
				}
				
				else if(bq.peek().getX()<bkXPos)
				{
					if(bq.peek().getY()<bkYPos)
					{
						two=true;
						four=true;
					}
					else if(bq.peek().getY()>bkYPos)
					{
						one=true;
						two=true;
					}
				}
				else if(bq.peek().getX()>bkXPos)
				{
					if(bq.peek().getY()<bkYPos)
					{
						one=true;
						three=true;
					}
					else if(bq.peek().getY()>bkYPos)
					{
						four=true;
						two=true;
					}
				}
			}
			switch2=true;
			}
		
					if (one == false) {
						if (wk.move(board, wkXPos + 1, wkYPos + 1) == true) {
							if (bq2.peek().move(board, wkXPos + 1, wkYPos + 1) == true) {
								one = true;
							
							}
						}
						else
						{
							one = true;
					
						}
					}
					if (two == false) {
						if (wk.move(board, wkXPos + 1, wkYPos - 1) == true) {
							if (bq2.peek().move(board, wkXPos + 1, wkYPos - 1) == true) {
								two = true;
							
							}

						}
						else
						{
							two = true;
							
						}
					}
					if (three == false) {
						if (wk.move(board, wkXPos - 1, wkYPos + 1) == true) {
							if (bq2.peek().move(board, wkXPos + 1, wkYPos + 1) == true) {
								three = true;
								
							}
						}
						else
						{
							three = true;
						
						}
					}
					if (four == false) {
						if (wk.move(board, wkXPos - 1, wkYPos - 1) == true) {
							if (bq2.peek().move(board, wkXPos - 1, wkYPos - 1) == true) {
								four = true;
								
							}
						}
						else
						{
							four = true;
							
						}
					}
					if (five == false) {
						if (wk.move(board, wkXPos + 1, wkYPos) == true) {
							if (bq2.peek().move(board, wkXPos + 1, wkYPos) == true) {
								five = true;
						
							}
						}
						else
						{
							five = true;
						
						}
					}
					if (six == false) {
						if (wk.move(board, wkXPos, wkYPos + 1) == true) {
							if (bq2.peek().move(board, wkXPos, wkYPos + 1) == true) {
								six = true;
							}
						}
						else
						{
							six = true;
						}
					}
					if (seven == false) {
						if (wk.move(board, wkXPos - 1, wkYPos) == true) {
							if (bq2.peek().move(board, wkXPos - 1, wkYPos) == true) {
								seven = true;
								
							}
						}
						else
						{
							seven = true;
						
						}
					}
					if (eight == false) {
						if (wk.move(board, wkXPos, wkYPos - 1) == true) {
							if (bq2.peek().move(board, wkXPos, wkYPos - 1) == true) {
								
								eight = true;
							
							}
						}
						else
						{
							eight = true;
						
						}
					}
			bq2.remove();
			
		}
		
		if((one==true)&&(two==true)&&(three==true)&&(four==true)&&(five==true)&&(six==true)&&(seven==true)&&(eight==true))
		{
			while(wq3.peek()!=null)
			{
				if(wq3.peek().move(board, bq.peek().getX(), bq.peek().getY())==true)
				{
					
					return -1;
				}
				wq3.remove();
			}
		
			System.out.println("The White King is in CheckMate!");
			return -2;		
		}
		else
		{
			System.out.println("The White King is in Check!");
			return -1;
		}
		
	}
		bq.remove();
}
	return 0;
	}
	



	public int kingInCheck(int movePosX, int movePosY)
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
					bq.offer(board[i][j]);
				}
					
				}
			}
		}
	
	while(wq.peek()!=null)
	{
	
		wq.peek().getX();
		if(wq.peek().move(board, movePosX, movePosY)==true)
		{
		
		
			System.out.println(wq.peek().toString());
			System.out.println("Cannot put your own king in Check!");
			return 1;
		
		}
		wq.remove();
	}
	while(bq.peek()!=null)
	{
		
		if(bq.peek().move(board, movePosX, movePosY)==true)
		{
			System.out.println(bq.peek().toString());
			System.out.println("Cannot put your own king in Check!");
			return -1;
			
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

public ArrayList<Piece> getWhitePieces()
{
	return whitePieces;
}
public ArrayList<Piece> getBlackPieces()
{
	return blackPieces;
}
}
	

