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
	int max; 
	 int count;
	 Queue<Piece> iOrder= new LinkedList<Piece>();
	 Stack <Piece> pOrder= new Stack<Piece>();

	 ArrayList<Piece> inOrder= new ArrayList<Piece>();
	 ArrayList<Piece> postOrder= new ArrayList<Piece>();
	 
		Stack <Integer> moveList= new Stack<Integer>();
		Stack <Integer> best= new Stack<Integer>();
		Stack <Integer> scndbest= new Stack<Integer>();
	 
	
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
	
	// this will call traverse tree and return best move
	public /*Stack<Integer>*/ void  move(Piece[][] board, Player enemy) 
	{
	
	
	System.out.println("Player eval");
	System.out.println(getBoardEvaluation( board,  this,  enemy));
	System.out.println("enemy eval");
	getBoardEvaluation( board,  enemy,  this);
	//return sgstMove;
	
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

	public void fillMoveList(Player play, Player enemy, Piece[][] board)
	{
	
		for(int i=0; i<play.getPieceList().size(); i++)
		{
			for(int j=0; j<enemy.getPieceList().size(); j++)
			{
				
				//fill non attack moves first
				if(j==0)
				{
					//a loop for each possible move direction starting at 12 o'clock position and moving clockwise
					int iteratorX=play.getPiece(i).getX();
					int iteratorY=0;
					iteratorX=play.getPiece(i).getX();
					iteratorY= play.getPiece(i).getY()-1;
					while(play.getPiece(i).move(board, iteratorX, iteratorY)==true)
					{
						//y then x
						moveList.push(iteratorX);
						moveList.push(iteratorY);
						moveList.push(play.getPiece(i).getX());
						moveList.push(play.getPiece(i).getY());
						
						
						iteratorY--;
						if((iteratorX>8)||(iteratorX<0)||(iteratorY>8)|(iteratorY<0))
						{
							break;
						}
					
					}
					iteratorX=play.getPiece(i).getX()-1;
					iteratorY= play.getPiece(i).getY()-1;
					while(play.getPiece(i).move(board, iteratorX, iteratorY)==true)
					{
						moveList.push(iteratorX);
						moveList.push(iteratorY);
						moveList.push(play.getPiece(i).getX());
						moveList.push(play.getPiece(i).getY());
						
						iteratorX--;
						iteratorY--;
						if((iteratorX>8)||(iteratorX<0)||(iteratorY>8)|(iteratorY<0))
						{
							break;
						}
					}
					iteratorX=play.getPiece(i).getX()-1;
					iteratorY= play.getPiece(i).getY();
					while(play.getPiece(i).move(board, iteratorX, iteratorY)==true)
					{
						moveList.push(iteratorX);
						moveList.push(iteratorY);
						moveList.push(play.getPiece(i).getX());
						moveList.push(play.getPiece(i).getY());
					
						iteratorX++;
						if((iteratorX>8)||(iteratorX<0)||(iteratorY>8)|(iteratorY<0))
						{
							break;
						}
					
					}
					iteratorX=play.getPiece(i).getX()+1;
					iteratorY= play.getPiece(i).getY()+1;
					while(play.getPiece(i).move(board, iteratorX, iteratorY)==true)
					{
						moveList.push(iteratorX);
						moveList.push(iteratorY);
						moveList.push(play.getPiece(i).getX());
						moveList.push(play.getPiece(i).getY());
						
						iteratorX++;
						iteratorY++;
						if((iteratorX>8)||(iteratorX<0)||(iteratorY>8)|(iteratorY<0))
						{
							break;
						}
					}
					iteratorX=play.getPiece(i).getX();
					iteratorY= play.getPiece(i).getY()+1;
					while(play.getPiece(i).move(board, iteratorX, iteratorY)==true)
					{
						moveList.push(iteratorX);
						moveList.push(iteratorY);
						moveList.push(play.getPiece(i).getX());
						moveList.push(play.getPiece(i).getY());
					
				
						iteratorY++;
						if((iteratorX>8)||(iteratorX<0)||(iteratorY>8)|(iteratorY<0))
						{
							break;
						}
					}
					iteratorX=play.getPiece(i).getX()-1;
					iteratorY= play.getPiece(i).getY()+1;
					while(play.getPiece(i).move(board, iteratorX, iteratorY)==true)
					{
						moveList.push(iteratorX);
						moveList.push(iteratorY);
						moveList.push(play.getPiece(i).getX());
						moveList.push(play.getPiece(i).getY());
						
						iteratorX--;
						iteratorY++;
						if((iteratorX>8)||(iteratorX<0)||(iteratorY>8)|(iteratorY<0))
						{
							break;
						}
					}
					iteratorX=play.getPiece(i).getX()-1;
					iteratorY= play.getPiece(i).getY();
					while(play.getPiece(i).move(board, iteratorX, iteratorY)==true)
					{
						moveList.push(iteratorX);
						moveList.push(iteratorY);
						moveList.push(play.getPiece(i).getX());
						moveList.push(play.getPiece(i).getY());
						
						iteratorX--;
						if((iteratorX>8)||(iteratorX<0)||(iteratorY>8)|(iteratorY<0))
						{
							break;
						}
					
					}
					iteratorX=play.getPiece(i).getX()-1;
					iteratorY= play.getPiece(i).getY()-1;
					while(play.getPiece(i).move(board, iteratorX, iteratorY)==true)
					{
						moveList.push(iteratorX);
						moveList.push(iteratorY);
						moveList.push(play.getPiece(i).getX());
						moveList.push(play.getPiece(i).getY());
						
						
						
						iteratorX--;
						iteratorY--;
						if((iteratorX>8)||(iteratorX<0)||(iteratorY>8)|(iteratorY<0))
						{
							break;
						}
					}
					
				
					
				}
				//fix the list of pieces
					if(play.inOrder.get(i).move(board, enemy.postOrder.get(j).getX(), enemy.postOrder.get(j).getY())==true)
					{
						//finding the best move
						if(play.inOrder.get(i).getValue()>max)
						{
							this.setMax(play.getPiece(i).getValue());
						}
						
							moveList.push(enemy.postOrder.get(i).getX());
							moveList.push(enemy.postOrder.get(i).getY());
							moveList.push(play.inOrder.get(i).getX());
							moveList.push(play.inOrder.get(i).getY());	
							
					}
					
			}
		}
		
	

	}
		public void setMax(int a)
	{
		max=a;
		
	}
	public int getMax()
	{
		return max;
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
