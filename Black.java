package chess;

public class Black extends Player
{
	pawn p;
	king k; 
	queen q;
	knight kn;
	bishop b;
	rook r; 
	
	public  String toString()
	{
		return "Black";
	}
	
	public Piece setPawn()
	{
	
		 p=new pawn(black);
	
		return p;
	}
	public  Piece setRook()
	{
		r=new rook(black);
		return p;
	}
	public  Piece setKnight()
	{
		kn=new knight(black);
		return p;
	}
	public  Piece setBishop()
	{
		b=new bishop(black);
		return p;
	}
	public  Piece setKing()
	{
		k=new king(black);
		return p;
	}
	public  Piece setQueen()
	{
		q=new queen(black);
		return p;
	}
}