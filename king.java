package chess;

public class king extends Piece 
{
	Player play;

	public king (Player play)
	{
		this.play=play;
	}
	public  String toString()
	{
		String k="";
		if(this.getPlayer().toString().equals("White"))
		{
		 k="[*K*]";
		}
		else
		{
		k="[!K!]";
		}
		return k;
	}
	public  Player getPlayer()
	{
		return play;
	}
	public  boolean  move(board gameBoard, int movePosX, int movePosY )
	{
		Piece [][] board = new Piece[8][8];
		gameBoard.copyBoard(board);
	
		int xPos=getX(this, board);
		
		int yPos=getY(this, board);
		
	
	
		System.out.println("xpos:"+xPos);
		System.out.println("ypos:"+yPos);
		System.out.println("movePosx:"+movePosX);
		System.out.println("movePosy:"+movePosY);
		
		int checkX=0;
		int checkY=0;
		
		

		
			//if the white king is currently in check
			if(kingInCheck(gameBoard)==0)
			{
				System.out.println("You must move the white king out of check first!");
				return false;
			}		
		
			//quadrant 2 and 3 
			//if the current xPos is greater than the movePosX then the player is trying to go to either quad 2 or 3
			if(this.getX(this, board)>movePosX)
			{
				//quad 2
				if(this.getY(this, board)>movePosY)
				{
					if(piecesInWay(2,  gameBoard,  movePosY,  movePosX)==false)
					{
						
						return true;
					}
					
					
				}
				
				//quad 3
				if(this.getY(this, board)<movePosY)
				{
					if(piecesInWay(3,  gameBoard,  movePosY,  movePosX)==false)
					{
						
						return true;
					}
				}
			}
			//quadrant 1 and 4 
			//if the current xPos is greater than the movePosX then the player is trying to go to either quad 1 or 4
			if(this.getX(this, board)<movePosX)
			{
				//quad 1
				if(this.getY(this, board)>movePosY)
				{
					if(piecesInWay(1,  gameBoard,  movePosY,  movePosX)==false)
					{
						
				
						
						System.out.println("we made it here");
						
						return true;
					}
					
				}
				
				//quad 4
				if(this.getY(this, board)<movePosY)
				{
					if(piecesInWay(4,  gameBoard,  movePosY,  movePosX)==false)
					{
						
						return true;
					}
				}
			}
			//quadrant 5 and 7 
			//if the current xPos minus the movePosX equals 0 the player is trying to go to either quad 5 or 7
			if(this.getX(this, board)-movePosX==0)
			{
				System.out.println("there");
				//quad 3
				if(this.getY(this, board)>movePosY)
				{
					System.out.println("there");
					if(piecesInWay(5,  gameBoard,  movePosY,  movePosX)==false)
					{
						System.out.println("there");
						return true;
					}
					
					
				}
				
				//quad 3
				if(this.getY(this, board)<movePosY)
				{
					System.out.println("there");
					if(piecesInWay(7,  gameBoard,  movePosY,  movePosX)==false)
					{
						System.out.println("there");
						return true;
					}
				}
			}
			//quadrant 6 and 8 
			//if the current yPos minus the movePosY then the player is trying to go to either quad 6 or 8
			if(this.getY(this, board)-movePosY==0)
			{
				System.out.println("here");
				//quad 2
				if(this.getX(this, board)>movePosX)
				{
					if(piecesInWay(6,  gameBoard,  movePosY,  movePosX)==false)
					{
						return true;
					}
					
				}
				
				//quad 4
				if(this.getX(this, board)<movePosX)
				{
					if(piecesInWay(8,  gameBoard,  movePosY,  movePosX)==false)
					{
						return true;
					}
				}
			
		
		
		}
		System.out.println("that is not a valid move");
		return false;
	}
	public boolean piecesInWay(int quadrant, board gameBoard, int movePosY, int movePosX)
	{
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
						if (board[checkY][checkX]!=null) {
							
							return false;
						}
					}
					// increment and check the next spot

					else {

						if (checkX > movePosX) 
						{
							
							checkX--;
						
						}
						if (checkY > movePosY)
						{
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
						if (board[checkY][checkX]!=null) {
					
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
						if (board[checkY][checkX]!=null) {
				
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

					System.out.println(checkX);
					System.out.println(checkY);
					System.out.println(movePosX);
					System.out.println(movePosY);
					System.out.println();
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
						if (board[checkY][checkX]!=null) {
							
							return false;
						}
					}
					// increment and check the next spot

					else {

						if (checkX < movePosX) 
						{
							
							checkX++;
						
						}
						if (checkY < movePosY)
						{
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
						if (board[checkY][checkX]!=null) {
						
							return false;
						}
					}
					// increment and check the next spot

					else {

						if ((checkX < 8) && (checkY > 0)) {
							//checkX++;
							checkY--;
						}
					}
				}
			}
			//moving left
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
						if (board[checkY][checkX]!=null) {
						
							return false;
						}
					}
					// increment and check the next spot

					else {

						if (checkX > 0)  {
							checkX--;
							//checkY--;
						}
					}
				}
			}

			//moving down
			if (quadrant == 7) {
				

				checkX = this.getX(this, board);
				checkY = this.getY(this, board) + 1;

				// finish the other quadrants

				// if in quad 1, checkX will always equal checkY
				for (int i = 0; i < 1; i++) {

					// if there is a piece in the way and we have not reached move position

					System.out.println("again");
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
						if (board[checkY][checkX]!=null) {
					
							return false;
						}
					}
					// increment and check the next spot

					else {

						if (checkX >= 0)  {
							//checkX--;
							checkY++;
						}
					}
				}
			}
			//moving right
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
						if ((board[checkY][checkX]!=null)
								&& (board[checkY][checkX].toString().equals("[K]"))) {
							return true;
						}
						// trying to capture any other enemy piece
						if (board[checkY][checkX]!=null) {
						
							return false;
						}
					}
					// increment and check the next spot

					else {

						if (checkX < movePosX) {
							checkX++;
							//checkY--;
						}
					}
				
			}
			
			
		}
		System.out.println("that is not a valid move");
		return true;

	}
	
	
	public int kingInCheck(board gameBoard)
	{
		return -1;
	}
	public boolean canMove(board gameBoard)
	{
		return false;
	}
	
}
