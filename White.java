package chess;

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
		
		public White()
		{
		//	white=new White();
		}
		public  String toString()
		{
			return "White";
		}
		/*
		public Piece < P > setWhitePeace()
		{
			return new P(white);
		
		}*/
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
	}

