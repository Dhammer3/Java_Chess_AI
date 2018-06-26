package chess;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;
import java.util.concurrent.ThreadLocalRandom;

public abstract class Player 
{



	Player white;
	Player black;
	 int count;
	 Queue<Piece> iOrder= new LinkedList<Piece>();
	 Stack <Piece> pOrder= new Stack<Piece>();

	 ArrayList<Piece> inOrder= new ArrayList<Piece>();
	 ArrayList<Piece> postOrder= new ArrayList<Piece>();
	 
	
	Player()
	{/*
		Queue<Piece> pawnQ= new LinkedList<Piece>();
		Queue<Piece> knightQ= new LinkedList<Piece>();
		Queue<Piece> rookQ= new LinkedList<Piece>();
		Queue<Piece> queenQ= new LinkedList<Piece>();
		Queue<Piece> kingQ= new LinkedList<Piece>();
		
		Stack<Piece> postOrder= new Stack<Piece>();
		
		
		for (int i = 0; i < this.getPieceList().size(); i++)
		{
			if(this.getPiece(i).getValue()==1)
			{
				pawnQ.add(this.getPiece(i));
			}
			if(this.getPiece(i).getValue()==3)
			{
				knightQ.add(this.getPiece(i));
			}
			if(this.getPiece(i).getValue()==5)
			{
				rookQ.add(this.getPiece(i));
			}
			if(this.getPiece(i).getValue()==9)
			{
				queenQ.add(this.getPiece(i));
			}
			if(this.getPiece(i).getValue()==100)
			{
				kingQ.add(this.getPiece(i));
			}
			
			
		}
		while(pawnQ.peek()!=null)
		{
			inOrder.add(pawnQ.peek());
			postOrder.push(pawnQ.remove());
			//this.postOrder.add(postOrder.pop());
		}
		while(knightQ.peek()!=null)
		{
			inOrder.add(knightQ.peek());
			postOrder.push(knightQ.remove());
			//this.postOrder.add(postOrder.pop());
			
		}
		while(rookQ.peek()!=null)
		{
			inOrder.add(rookQ.remove());
			postOrder.push(rookQ.remove());
			//this.postOrder.add(postOrder.pop());
		}
	
		inOrder.add(queenQ.peek());
		postOrder.push(queenQ.remove());

		inOrder.add(kingQ.peek());
		postOrder.push(queenQ.remove());
		
		while(postOrder.peek()!=null)
		{
			this.postOrder.add(postOrder.pop());
		}
		
		
	count=1;
	/*/
	}

	
	public abstract Piece getPiece(int i); 
		
	

	public abstract ArrayList<Piece> getPieceList();
	
	
	

	public  Piece getPiece(Piece [][] arr, int currentLocationX, int currentLocationY )
	{
		
	

		Piece pieceOnarr=arr[currentLocationY][currentLocationX];
	
		return pieceOnarr;
		
			
	}
	
	public abstract String toString();
	public abstract void addPieces(ArrayList<Piece> playerPieces);
	
	public Stack<Integer> move(Piece[][] board, Player enemy) 
	{
	Queue<Piece> pawnQ= new LinkedList<Piece>();
	Queue<Piece> knightQ= new LinkedList<Piece>();
	Queue<Piece> rookQ= new LinkedList<Piece>();
	Queue<Piece> queenQ= new LinkedList<Piece>();
	ArrayList<Piece> orderedPieces= new ArrayList<Piece>();
	
	Stack<Piece> pawnS= new Stack<Piece>();
	Stack<Piece> knightS= new Stack<Piece>();
	Stack<Piece> rookS= new Stack<Piece>();
	Stack<Piece> queenS= new Stack<Piece>();
	ArrayList<Piece> enemyOrderedPieces= new ArrayList<Piece>();
	

	Stack<Integer> sgstMove= new Stack<Integer>();
	
	
		for (int i = 0; i < enemy.getPieceList().size(); i++)
		{
			if(enemy.getPiece(i).getValue()==1)
			{
				pawnS.push(enemy.getPiece(i));
			}
			if(enemy.getPiece(i).getValue()==3)
			{
				knightS.push(enemy.getPiece(i));
			}
			if(enemy.getPiece(i).getValue()==5)
			{
				rookS.push(enemy.getPiece(i));
			}
			if(enemy.getPiece(i).getValue()==9)
			{
				queenS.push(enemy.getPiece(i));
			}
			
		}
		for(int u=0; u<pawnS.size(); u++)
		{
			
			enemyOrderedPieces.add(pawnS.peek());
			pawnS.pop();
			
		}
		for(int u=0; u<knightS.size(); u++)
		{
		
			enemyOrderedPieces.add(knightS.peek());
			knightS.pop();
		}
		for(int u=0; u<rookS.size(); u++)
		{
			
			enemyOrderedPieces.add(rookS.peek());
			rookS.pop();
		}
		if(queenS.peek()!=null)
		{
		enemyOrderedPieces.add(queenS.pop());
		}
	
		
		for (int i = 0; i < this.getPieceList().size(); i++)
		{
			if(this.getPiece(i).getValue()==1)
			{
				pawnQ.add(this.getPiece(i));
			}
			if(this.getPiece(i).getValue()==3)
			{
				knightQ.add(this.getPiece(i));
			}
			if(this.getPiece(i).getValue()==5)
			{
				rookQ.add(this.getPiece(i));
			}
			if(this.getPiece(i).getValue()==9)
			{
				queenQ.add(this.getPiece(i));
			}
			
			
		}
		while(pawnQ.peek()!=null)
		{
			orderedPieces.add(pawnQ.remove());
		}
		while(knightQ.peek()!=null)
		{
			orderedPieces.add(knightQ.remove());
		}
		while(rookQ.peek()!=null)
		{
			orderedPieces.add(rookQ.remove());
		}
	
			orderedPieces.add(queenQ.remove());
			
			//checking if the worst white piece can capture the best black piece
			outerloop:
			for(int j=0; j<orderedPieces.size(); j++)
			{
				for(int h=0; h<enemyOrderedPieces.size(); h++)
				{
					int xPos=enemyOrderedPieces.get(h).getX();
					int yPos=enemyOrderedPieces.get(h).getY();
							if(orderedPieces.get(j).move(board,xPos ,yPos)==true)
							{
								sgstMove.push(yPos+1);
								sgstMove.push(xPos);
								sgstMove.push(orderedPieces.get(j).getY()+1);
								sgstMove.push(orderedPieces.get(j).getX());
								System.out.println("here");
								break outerloop;
							}
				}
			}

	while(sgstMove.isEmpty())
	{
		int xMove=ThreadLocalRandom.current().nextInt(0, 7);
		int yMove= ThreadLocalRandom.current().nextInt(0, 7);
		int randomNum = ThreadLocalRandom.current().nextInt(0, this.getPieceList().size() );
		//System.out.println(randomNum);
		if(this.getPiece(randomNum).move(board,xMove ,yMove)==true)
				{
		//	System.out.println(this.getPiece(randomNum).toString());
		//	System.out.println("xPos"+this.getPiece(randomNum).getX());
		//	System.out.println("ypos"+this.getPiece(randomNum).getY());
		//	System.out.println("xmove"+xMove);
		//System.out.println("ymove"+yMove); 
			sgstMove.push(yMove+1);
			sgstMove.push(xMove);
			sgstMove.push(this.getPiece(randomNum).getY()+1);
			sgstMove.push(this.getPiece(randomNum).getX());
			System.out.println("We are just guessing here..");
			
			break;
				}
	}
	System.out.println("Player eval");
	System.out.println(getBoardEvaluation( board,  this,  enemy));
	System.out.println("enemy eval");
	getBoardEvaluation( board,  enemy,  this);
	return sgstMove;
	
	}

	public int getBoardEvaluation(Piece[][] board, Player play, Player enemy) {
		Piece[][] temp = new Piece[8][8];
		temp = board;
		int piecesThreatened = 0;
		int piecesGuarded = 0;
		int piecesVulnerable = 0;

		for (int i = 0; i < play.getPieceList().size(); i++) {
			for (int j = 0; j < enemy.getPieceList().size(); j++) {
				
	
				if (play.getPiece(i).move(board, enemy.getPiece(j).getX(), enemy.getPiece(j).getY()) == true) {
				
					piecesThreatened += enemy.getPiece(j).getValue();
					System.out.println(piecesThreatened);
				}
				if(i+1>=play.getPieceList().size())
				{
					break;
				}
				int guardedPieceY = play.getPiece(i+1).getY();
				int guardedPieceX = play.getPiece(i+1).getX();
				board[guardedPieceY][guardedPieceX]=null;

				if (play.getPiece(i).move(board, guardedPieceX, guardedPieceY) == true)
						 {
					piecesGuarded += play.getPiece(i+1).getValue();
				}
				board[guardedPieceY][guardedPieceX]=play.getPiece(i+1);
				

			}
		}
		for (int y = 0; y < enemy.getPieceList().size(); y++) {
			for (int c = 0; c < play.getPieceList().size(); c++) {
				if (enemy.getPiece(y).move(board, play.getPiece(c).getX(), play.getPiece(c).getY()) == true) {
					piecesVulnerable += play.getPiece(c).getValue();

				}
			}
		}
		System.out.println("piecesThreatened " + piecesThreatened);
		System.out.println("piecesGuarded " + piecesGuarded);
		System.out.println("piecesVulnerable " + piecesVulnerable);

		return (piecesThreatened + piecesGuarded) - piecesVulnerable;
			
		
		//return 0;
	}

	
	// if any white piece can move to put the king in check, do that move.
	// else if
	// find the most valuable black piece, if white can capture that piece do that
	// move.
	// else
	// make a move toward one of the black pieces without putting the white piece in
	// danger.

	public int randomNumGenerator() {
		// returns a positive random number <=8

	

		double dub = Math.random();
		
		double increment=0.125;
		int count=0;

		while(dub>increment)
		{
			increment+=increment;
			count++;
		}
		return count;
	
	
	}
	public void removePiece(Piece p)
	{
		for(int i=0; i<inOrder.size(); i++)
		{
			if(inOrder.get(i).equals(p))
			{
				inOrder.remove(i);
			}
		}
	}
	

}

