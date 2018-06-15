package chess;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;

public class White extends Player {

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
	ArrayList<Piece> whitePieces;
	boolean isPerson;

	public White() {

		this.isPerson = isPerson;
		whitePieces = new ArrayList<Piece>();
	}

	public void setPerson(boolean isPerson) {
		this.isPerson = isPerson;
	}

	public boolean getPerson() {
		return isPerson;
	}

	public String toString() {
		return "White";
	}

	public void addPieces(ArrayList<Piece> playerPieces) {
		this.whitePieces = playerPieces;

	}

	public Piece getPiece(int i) {
		//System.out.println(i);
		return whitePieces.get(i);
		
	}

	public ArrayList<Piece> getPieceList() {
		return whitePieces;
	}


	
	
	
}
