package chess;

import java.util.LinkedList;
import java.util.Queue;

public class king extends Piece {
	Player play;
	int moveCount;
	int value;

	public king(Player play) {
		this.play = play;
		moveCount=0;
		value=20;
	}
	public  int getValue()
	{
		return value;
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

	public boolean move(Piece[][] board, int movePosX, int movePosY) {
		//Piece[][] board = new Piece[8][8];
		//gameBoard.copyBoard(board);
	
		Piece[][] temp = new Piece[8][8];
		for(int i=0; i<8; i++)
		{
			for(int j=0; j<8; j++)
			{
				temp[i][j]=board[i][j];
			}
		}
		Queue<Piece> aq = new LinkedList<Piece>();
		
		if((movePosX<0)||(movePosX>7))
		{
			return false;
		}
		if((movePosY<0)||(movePosY>7))
		{
			return false;
		}
		

		int xPos = getX();

		int yPos = getY();

		int check1 = (Math.abs(xPos - movePosX));
		int check2 = (Math.abs(yPos - movePosY));
		
		if ((check1 > 1)||(check2 > 1)) {

			return false;
		}
		if(enemyPieceCanMove( board,  movePosX,  movePosY)==true)
		{
			System.out.println("An enemy can move there.");
			return false;
			
		}

			if(board[movePosY][movePosX]!=null)
			{
				if(this.getPlayer().toString().equals("Black"))
				{
					if(board[movePosY][movePosX].getPlayer().toString().equals("White"))
					{
						aq.add(board[movePosY][movePosX]);
					}
				}
				if(this.getPlayer().toString().equals("White"))
				{
					if(board[movePosY][movePosX].getPlayer().toString().equals("Black"))
					{
						aq.add(board[movePosY][movePosX]);
					}
				}
				
			}
			temp[movePosY][movePosX]=this;
			if(enemyPieceCanMove( temp,  movePosX,  movePosY)==true)
			{
				temp=board;
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
				//check
				if(board[movePosY][movePosX].toString().equals("[!K!]"))
				{
					return true;
				}
			}
		
		else
		{
				if(board[movePosY][movePosX].getPlayer().toString().equals("Black"))
				{
					
					return false;
				}
				//check
				if(board[movePosY][movePosX].toString().equals("[*K*]"))
				{
					return true;
				}
		}	
		}
		
		
		return true;
	}

	public boolean piecesInWay(int quadrant, Piece[][] board, int movePosX, int movePosY) {
		//Piece[][] board = new Piece[8][8];
		//gameBoard.copyBoard(board);
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

	public void moveCounter()
	{
		moveCount++;
	}
	public  int getMoveCount()
	{
		return moveCount;
	}
	public boolean enemyPieceCanMove(Piece[][] board, int movePosX, int movePosY) {
		//Piece[][] temp = new Piece[8][8];
	//	gameBoard.copyBoard(board);
		Queue<Piece> bq = new LinkedList<Piece>();
		Queue<Piece> wq = new LinkedList<Piece>();
		
		for (int i = 0; i < 8; i++) {
			for (int j = 0; j < 8; j++) {
				//board[i][j]=temp[i][j];
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
		//temp[movePosY][movePosX]=null;

		if(this.getPlayer().toString().equals("Black"))
		{
		while (wq.peek() != null) {
			wq.peek().getX(wq.peek(), board);
			
			if (wq.peek().move(board, movePosX, movePosY) == true) 
			{
				
				System.out.println(wq.peek().toString());
				
			
				return true;
			}
			wq.remove();
		}
		}
		else
		{
		while (bq.peek() != null) {

			if (bq.peek().move(board, movePosX, movePosY) == true) {
				System.out.println(bq.peek().toString());
			
				return true;
			}
			bq.remove();
		}
		}
		return false;
	}

	//finish
	public boolean canCastle(Piece[][] board, int movePosX, int movePosY)
	{
		if((this.getMoveCount()!=0)||(board[movePosY][movePosX].getMoveCount()!=0))
		{
			System.out.println("the movecount");
			return false;
		}
	
		
		if(board[movePosY][movePosX].move(board, movePosX, movePosY)!=true)
		{
			System.out.println("not valid");
			return false;
		} 
		int i=this.getX(this, board);
		if(i-movePosX>0)
		{
			for(int x=i; x<movePosX; x--)
			{
				if(enemyPieceCanMove( board,  x,  movePosY)==true)
				{
					System.out.println("enemy piece ");
					return false;
				}
			}
		}
		else
		{
			for(int x=i; x<movePosX; x++)
			{
				if(enemyPieceCanMove( board,  x,  movePosY)==true)
				{
					System.out.println("enemy piece ");
					return false;
				}
			}
		}
		
		return true;
	}
}
