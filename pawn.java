package chess;

import java.util.NoSuchElementException;

public class pawn extends Piece {

	int moveCount=0;
	//Queue<int> pos=new Queue<int>();
	Player play;
	int value;


	public pawn(Player play) {
		this.play = play;
		value=1;
		//moveCount=0;
	}

	public pawn() {
		play = null;
		//moveCount = 0;

	}
	public  int getValue()
	{
		return value;
	}

	public Player getPlayer() {
		return play;
	}

	public String toString() {
		String k = "";
		if (this.getPlayer().toString().equals("White")) {
			k = "[*p*]";
		} else {
			k = "[!p!]";
		}
		return k;
	}

	public int getCount() {
		return moveCount;
	}
	



	// fix, can move pawn backword, //can move two spaces when not in spawn.
	public boolean move(Piece[][] board, int movePosX, int movePosY) {
		// cant move backward
		//Piece[][] board = new Piece[8][8];
		//gameBoard.copyBoard(board);
		//board[movePosY][movePosX]=this;
		//System.out.println(moveCount);
		

		int xPos = getX();

		int yPos = getY();
		
		if((movePosX<0)||(movePosX>7))
		{
		
			return false;
		}
		if((movePosY<0)||(movePosY>7))
		{
			
			return false;
		}
		if(board[movePosY][movePosX]!=null)
		{
			
			if (board[movePosY][movePosX] == this) {
				return false;
			}
			if(board[movePosY][movePosX].getPlayer().toString().equals(this.getPlayer().toString()))
			{
				return false;
			}
		}
		

		// start off with some error handling

		// the only time the player would be moving in X direction would be to capture a
		// piece.
		/*
		 * if (movePosX!=xPos)//&&(canCapture(gameBoard, p)!=true)) { return false; }
		 */

		// if its not in the spawn but trying to move 2 space in y-direction.
		if (movePosX - xPos > 1) {

	
			return false;
		}
		if (movePosX - xPos < -1) {
		

			return false;
		}
		if ((board[movePosY][movePosX] == null) && (movePosX != xPos)) {

			return false;
		}

		if (this.getPlayer().toString().equals("White")) {

			if (movePosY > yPos) {

				return false;
			}

			if (movePosX != xPos) {

				if ((movePosX - xPos == 1) && (movePosY - yPos == -1)) {
					try {

						if (board[movePosY][movePosX].getPlayer().toString().equals("Black")) {

							// System.out.println("Here");
							return true;

						} else {

							return false;
						}
					} catch (NullPointerException e) {
						return false;
					}
				}

				if ((movePosX - xPos == -1) && (movePosY - yPos == -1)) {
					try {

						if (board[movePosY][movePosX].getPlayer().toString().equals("Black")) {

							return true;
						} else {

							return false;
						}
					} catch (NullPointerException e) {
						return false;
					}
				}

			}

			if ((isInSpawn(board) == true) && (yPos - movePosY == 2)) {
				if ((board[movePosY][movePosX] == null) && (board[movePosY + 1][movePosX] == null)) {

					return true;
				}
			}
			if ((isInSpawn(board) == true) && (yPos - movePosY == 1)) {
				if (board[movePosY][movePosX] == null) {

					return true;
				}
			}
			if ((isInSpawn(board) == false) && (yPos - movePosY == 1)) {
				if (board[movePosY][movePosX] == null) {

					return true;
				}
			}

		}
		if (this.getPlayer().toString().equals("Black")) {

			if (movePosY < yPos) {

				return false;

			}

			if (movePosX != xPos) {

				if ((movePosX - xPos == 1) && (movePosY - yPos == 1)) {
					try {

						if (board[movePosY][movePosX].getPlayer().toString().equals("White")) {

							return true;
						} else {

							return false;
						}
					} catch (NullPointerException e) {
						return false;
					}
				}

				if ((movePosX - xPos == -1) && (movePosY - yPos == 1)) {
					try {

						if (board[movePosY][movePosX].getPlayer().toString().equals("White")) {

							return true;
						} else {

							return false;
						}
					} catch (NullPointerException e) {
						return false;
					}
				}

			}

			if ((isInSpawn(board) == true) && (movePosY - yPos == 2)) {
				if ((board[movePosY][movePosX] == null) && (board[movePosY - 1][movePosX] == null)) {

					return true;
				}
			}
			if ((isInSpawn(board) == true) && (movePosY - yPos == 1)) {
				if (board[movePosY][movePosX] == null) {

					return true;
				}
			}
			if ((isInSpawn(board) == false) && (movePosY - yPos == 1)) {
				if (board[movePosY][movePosX] == null) {

					return true;
				}
			}

		}

		return false;
	}
	// check

	// works
	public boolean isInSpawn(Piece[][] board) {
		// Piece[][] board = new Piece[8][8];
		// gameBoard.copyBoard(board);

		// if the pawn is white
		if (this.getPlayer().toString().equals("White")) {

			// white piece in the white spawn
			if (this.getY() == 6) {
				//

				return true;
			}

		}

		// if the pawn is black
		if (this.getPlayer().toString().equals("Black")) {

			// black pawn in the black spawn
			if (this.getY() == 1) {

				return true;
			}

		}
		return false;
	}
		
		
	

	public void moveCounter()
	{
		this.moveCount++;
	}
	public  int getMoveCount()
	{
		return this.moveCount;
	}
}