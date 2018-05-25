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

			isPerson=true;
			whitePieces=new ArrayList<Piece>();
		}
		
		public void setPerson(boolean isPerson)
		{
			this.isPerson=isPerson;
		}
		public  String toString()
		{
			return "White";
		}
	
		public Piece setPawn()
		{
		
			 p1=new pawn(white);
			
			return p1;
		}
		public  Piece setRook()
		{
			r=new rook(white);
			
			return r ;
		}
		public  Piece setKnight()
		{
			kn=new knight(white);
			
			return kn;
		}
		public  Piece setBishop()
		{
			b=new bishop(white);
			
			return b;
		}
		public  Piece setKing()
		{
			k=new king(white);
			
			return k;
		}
		public  Piece setQueen()
		{
			q=new queen(white);
	
			return q;
		}
		public  void addPieces(ArrayList<Piece> playerPieces)
		{
			this.whitePieces=playerPieces;
		
		}
		public Piece getPiece(int i)
		{
			return whitePieces.get(i);
		}
		
	}

