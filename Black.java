package chess;

import java.util.ArrayList;

public class Black extends Player
{
	pawn p;
	king k; 
	queen q;
	knight kn;
	bishop b;
	rook r; 
	ArrayList <Piece> blackPieces;
	boolean isPerson;
	
	public Black()
	{
	isPerson=true;
	blackPieces=new ArrayList<Piece>();
	}
	
	public void setPerson(boolean isPerson)
	{
		this.isPerson=isPerson;
	}
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
	public  void addPieces(ArrayList<Piece> playerPieces)
	{
		this.blackPieces=playerPieces;
	
	}
	public Piece getPiece(int i)
	{
		return blackPieces.get(i);
	}
	public ArrayList<Piece> getPieceList()
	{
		return blackPieces;
	}
}