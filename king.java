package chess;

import java.util.LinkedList;
import java.util.Queue;

public class king extends Piece {
	Player play;

	public king(Player play) {
		this.play = play;
	}

	public String toString() {
		String k = "";
		if (this.getPlayer().toString().equals("White")) {
			k = "[*K*]";
		} else {
			k = "[!K!]";
		}
		return k;
	}

	public Player getPlayer() {
		return play;
	}

	public boolean move(board gameBoard, int movePosX, int movePosY) {
		Piece[][] board = new Piece[8][8];
		gameBoard.copyBoard(board);
		
		if((movePosX<0)||(movePosX>7))
		{
			return false;
		}
		if((movePosY<0)||(movePosY>7))
		{
			return false;
		}
		System.out.println(movePosX);
		System.out.println(movePosY);
		

		int xPos = getX(this, board);

		int yPos = getY(this, board);

		int check1 = (Math.abs(xPos - movePosX));
		int check2 = (Math.abs(yPos - movePosY));
		
		if ((check1 > 1)||(check2 > 1)) {

			
			return false;
		}
	
		
		
		
		if(board[movePosY][movePosX]!=null)
		{
			if(this.getPlayer().toString().equals("White"))
			{
				if(board[movePosY][movePosX].getPlayer().toString().equals("White"))
				{
					return false;
				}
				if(board[movePosY][movePosX].toString().equals("[!K!]"))
				{
					return false;
				}
			}
		
		else
		{
				if(board[movePosY][movePosX].getPlayer().toString().equals("Black"))
				{
					return false;
				}
				if(board[movePosY][movePosX].toString().equals("[*K*]"))
				{
					return false;
				}
		}	
		}
		
		
		return true;
	}

	public boolean piecesInWay(int quadrant, board gameBoard, int movePosY, int movePosX) {
		Piece[][] board = new Piece[8][8];
		gameBoard.copyBoard(board);
		int checkX = 0;
		int checkY = 0;

		int count = 0;

		int xPos = getX(this, board);

		int yPos = getY(this, board);

		if (quadrant == 1) {

			checkX = this.getX(this, board) + 1;
			checkY = this.getY(this, board) - 1;

			// finish the other quadrants

			// if in quad 1, checkX will always equal checkY
			for (int i = 0; i < 1; i++) {

				// if there is a piece in the way and we have not reached move position

				if ((board[checkY][checkX] != null) && (checkX != movePosX)) {
					return true;
				}

				// if the move spot is null and every spot has been checked.
				if (((board[checkY][checkX] == null) && (checkX == movePosX) && (checkY == movePosY))) {

					return false;

				}
				// if we reached the move Spot and trying to capture a enemy piece
				if (((board[checkY][checkX] != null) && (checkX == movePosX) && (checkY == movePosY))) {

					// if trying to capture a king
					if ((board[checkY][checkX].getPlayer().toString().equals("Black"))
							&& (board[checkY][checkX].toString().equals("[K]"))) {
						return true;
					}
					// trying to capture any other enemy piece
					if (board[checkY][checkX] != null) {

						return false;
					}
				}
				// increment and check the next spot

				else {

					if (checkX > movePosX) {

						checkX--;

					}
					if (checkY > movePosY) {
						checkY--;
					}
				}
			}
		}
		if (quadrant == 2) {

			checkX = this.getX(this, board) - 1;
			checkY = this.getY(this, board) - 1;

			// finish the other quadrants

			// if in quad 1, checkX will always equal checkY
			for (int i = 0; i < 1; i++) {

				// if there is a piece in the way and we have not reached move position

				if ((board[checkY][checkX] != null) && (checkX != movePosX)) {

					return true;
				}

				// if the move spot is null and every spot has been checked.
				if (((board[checkY][checkX] == null) && (checkX == movePosX) && (checkY == movePosY))) {

					return false;

				}
				// if we reached the move Spot and trying to capture a enemy piece
				if (((board[checkY][checkX] != null) && (checkX == movePosX) && (checkY == movePosY))) {

					// if trying to capture a king
					if ((board[checkY][checkX].getPlayer().toString().equals("Black"))
							&& (board[checkY][checkX].toString().equals("[K]"))) {
						return true;
					}
					// trying to capture any other enemy piece
					if (board[checkY][checkX] != null) {

						return false;
					}
				}
				// increment and check the next spot

				else {

					if ((checkX >= 0) && (checkY >= 0)) {
						checkX--;
						checkY--;
					}
				}
			}
		}

		if (quadrant == 3) {

			checkX = this.getX(this, board) - 1;
			checkY = this.getY(this, board) + 1;

			// finish the other quadrants

			// if in quad 1, checkX will always equal checkY
			for (int i = 0; i < 1; i++) {

				// if there is a piece in the way and we have not reached move position

				if ((board[checkY][checkX] != null) && (checkX != movePosX)) {

					return true;
				}

				// if the move spot is null and every spot has been checked.
				if (((board[checkY][checkX] == null) && (checkX == movePosX) && (checkY == movePosY))) {

					return false;

				}
				// if we reached the move Spot and trying to capture a enemy piece
				if (((board[checkY][checkX] != null) && (checkX == movePosX) && (checkY == movePosY))) {

					// if trying to capture a king
					if ((board[checkY][checkX].getPlayer().toString().equals("Black"))
							&& (board[checkY][checkX].toString().equals("[K]"))) {
						return true;
					}
					// trying to capture any other enemy piece
					if (board[checkY][checkX] != null) {

						return false;
					}
				}
				// increment and check the next spot

				else {

					if ((checkX >= 0) && (checkY < 8)) {
						checkX--;
						checkY++;
					}
				}
			}
		}
		if (quadrant == 4) {

			checkX = this.getX(this, board) + 1;
			checkY = this.getY(this, board) + 1;

			// finish the other quadrants

			for (int i = 0; i < 1; i++) {

		
				// if there is a piece in the way and we have not reached move position

				if ((board[checkY][checkX] != null) && (checkX != movePosX)) {

					return true;
				}

				// if the move spot is null and every spot has been checked.
				if (((board[checkY][checkX] == null) && (checkX == movePosX) && (checkY == movePosY))) {

					return false;

				}
				// if we reached the move Spot and trying to capture a enemy piece
				if (((board[checkY][checkX] != null) && (checkX == movePosX) && (checkY == movePosY))) {

					// if trying to capture a king
					if ((board[checkY][checkX].getPlayer().toString().equals("Black"))
							&& (board[checkY][checkX].toString().equals("[K]"))) {

						return true;
					}
					// trying to capture any other enemy piece
					if (board[checkY][checkX] != null) {

						return false;
					}
				}
				// increment and check the next spot

				else {

					if (checkX < movePosX) {

						checkX++;

					}
					if (checkY < movePosY) {
						checkY++;
					}
				}
			}
		}

		if (quadrant == 5) {

			checkX = this.getX(this, board);
			checkY = this.getY(this, board) - 1;

			// finish the other quadrants

			// if in quad 1, checkX will always equal checkY
			for (int i = 0; i < 1; i++) {

				// if there is a piece in the way and we have not reached move position

				if ((board[checkY][checkX] != null) && (checkY != movePosY)) {
					return true;
				}

				// if the move spot is null and every spot has been checked.
				if (((board[checkY][checkX] == null) && (checkX == movePosX) && (checkY == movePosY))) {

					return false;

				}
				// if we reached the move Spot and trying to capture a enemy piece
				if (((board[checkY][checkX] != null) && (checkX == movePosX) && (checkY == movePosY))) {

					// if trying to capture a king
					if ((board[checkY][checkX].getPlayer().toString().equals("Black"))
							&& (board[checkY][checkX].toString().equals("[K]"))) {
						return true;
					}
					// trying to capture any other enemy piece
					if (board[checkY][checkX] != null) {

						return false;
					}
				}
				// increment and check the next spot

				else {

					if ((checkX < 8) && (checkY > 0)) {
						// checkX++;
						checkY--;
					}
				}
			}
		}
		// moving left
		if (quadrant == 6) {

			checkX = this.getX(this, board) - 1;
			checkY = this.getY(this, board);

			// finish the other quadrants

			// if in quad 1, checkX will always equal checkY
			for (int i = 0; i < 1; i++) {

				// if there is a piece in the way and we have not reached move position

				if ((board[checkY][checkX] != null) && (checkX != movePosX)) {

					return true;
				}

				// if the move spot is null and every spot has been checked.
				if (((board[checkY][checkX] == null) && (checkX == movePosX) && (checkY == movePosY))) {

					return false;

				}
				// if we reached the move Spot and trying to capture a enemy piece
				if (((board[checkY][checkX] != null) && (checkX == movePosX) && (checkY == movePosY))) {

					// if trying to capture a king
					if ((board[checkY][checkX].getPlayer().toString().equals("Black"))
							&& (board[checkY][checkX].toString().equals("[K]"))) {
						return true;
					}
					// trying to capture any other enemy piece
					if (board[checkY][checkX] != null) {

						return false;
					}
				}
				// increment and check the next spot

				else {

					if (checkX > 0) {
						checkX--;
						// checkY--;
					}
				}
			}
		}

		// moving down
		if (quadrant == 7) {

			checkX = this.getX(this, board);
			checkY = this.getY(this, board) + 1;

			// finish the other quadrants

			// if in quad 1, checkX will always equal checkY
			for (int i = 0; i < 1; i++) {

				// if there is a piece in the way and we have not reached move position

				if ((board[checkY][checkX] != null) && (checkY != movePosY)) {

					return true;
				}

				// if the move spot is null and every spot has been checked.
				if (((board[checkY][checkX] == null) && (checkX == movePosX) && (checkY == movePosY))) {

					return false;

				}
				// if we reached the move Spot and trying to capture a enemy piece
				if (((board[checkY][checkX] != null) && (checkX == movePosX) && (checkY == movePosY))) {

					// if trying to capture a king
					if ((board[checkY][checkX].getPlayer().toString().equals("Black"))
							&& (board[checkY][checkX].toString().equals("[K]"))) {
						return true;
					}
					// trying to capture any other enemy piece
					if (board[checkY][checkX] != null) {

						return false;
					}
				}
				// increment and check the next spot

				else {

					if (checkX >= 0) {
						// checkX--;
						checkY++;
					}
				}
			}
		}
		// moving right
		if (quadrant == 8) {

			checkX = this.getX(this, board) + 1;
			checkY = this.getY(this, board);

			// finish the other quadrants

			for (int i = 0; i < 1; i++) {

				// if there is a piece in the way and we have not reached move position

				if ((board[checkY][checkX] != null) && (checkX != movePosX)) {

					return true;
				}

				// if the move spot is null and every spot has been checked.
				if (((board[checkY][checkX] == null) && (checkX == movePosX) && (checkY == movePosY))) {

					return false;

				}
				// if we reached the move Spot and trying to capture a enemy piece
				if (((board[checkY][checkX] != null) && (checkX == movePosX) && (checkY == movePosY))) {

					// if trying to capture a king
					if ((board[checkY][checkX] != null) && (board[checkY][checkX].toString().equals("[K]"))) {
						return true;
					}
					// trying to capture any other enemy piece
					if (board[checkY][checkX] != null) {

						return false;
					}
				}
				// increment and check the next spot

				else {

					if (checkX < movePosX) {
						checkX++;
						// checkY--;
					}
				}

			}

		}
		System.out.println("that is not a valid move");
		return true;

	}

	public int enemyPieceCanMove(board gameBoard, int movePosX, int movePosY) {
		Piece[][] board = new Piece[8][8];
		gameBoard.copyBoard(board);
		Queue<Piece> bq = new LinkedList<Piece>();
		Queue<Piece> wq = new LinkedList<Piece>();
		
		for (int i = 0; i < 8; i++) {
			for (int j = 0; j < 8; j++) {
				if (board[i][j] != null) {
					if (board[i][j].getPlayer().toString().equals("White")) {
					
						wq.offer(board[i][j]);
					}
					if (board[i][j].getPlayer().toString().equals("Black")) {
						
						bq.offer(board[i][j]);
					}

				}
			}
		}

		while (wq.peek() != null) {
			wq.peek().getX(wq.peek(), board);
			if (wq.peek().move(gameBoard, movePosX, movePosY) == true) {
				
				System.out.println(wq.peek().toString());
				wq.remove();
				return -1;
			}
			wq.remove();
		}
		while (bq.peek() != null) {

			if (bq.peek().move(gameBoard, movePosX, movePosY) == true) {
				System.out.println(bq.peek().toString());
				bq.remove();
				return 1;
			}
			bq.remove();
		}
		return 0;
	}

}
