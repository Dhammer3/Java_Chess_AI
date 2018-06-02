package chess;

import java.util.ArrayList;

public abstract class Player 
{



	Player white;
	Player black;
	 int count;

	
	Player()
	{
		
	count=1;
	}

	public abstract Piece getPiece(int a);
	

	
	

	public  Piece getPiece(Piece [][] arr, int currentLocation1, int currentLocationX, Player play )
	{
		
	

		Piece pieceOnarr=arr[currentLocationX-1][currentLocation1];
		if (pieceOnarr!=null)
		{
			if ((pieceOnarr.getPlayer().toString().equals("Black"))&&(play.toString().equals("White")))
			{
				System.out.println("you selected a black piece!");
				System.out.println(pieceOnarr.getPlayer().toString());
				return null;
			}
			if ((pieceOnarr.getPlayer().toString().equals("White"))&&(play.toString().equals("Black")))
			{
				System.out.println("you selected a white piece!");
				System.out.println(pieceOnarr.getPlayer().toString());
				return null;
			}
			else
			{
			System.out.println("The Piece you Selected was: " +pieceOnarr.getPlayer().toString()+pieceOnarr.toString());
			}
		}
		else
		{
			System.out.println("There is no piece there");
		}
		return pieceOnarr;
		
			
	}
	
	public abstract String toString();
	public abstract void addPieces(ArrayList<Piece> playerPieces);
	
}

