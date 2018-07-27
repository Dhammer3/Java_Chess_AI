package chess;

public class node
{
	int xPos;
	int yPos;
	int movePosX;
	int movePosY;
	Piece[][] board;
	int value;
	node left; 
	node right; 
	int count =0;
	
	//root node constructor
	public node(Piece[][] board, int value, node l, node r)
	{
	this.value=value;
	this.board=board;
	this.left=l;
	this.right=r;
	}
	//child node constructor
	public node(int xPos, int yPos, int movePosX, int movePosY, Piece[][] board, int value)
	{
		this.xPos=xPos;
		this.yPos=yPos;
		this.movePosX=movePosX;
		this.movePosY=movePosY;
		this.board=board;
		this.value=value;
		this.right=null;
		this.left=null;
	
	}
	public node getLeftMost()
	{
		if(this.getLeft()==null)
		{
			return this;
		}
		else
		{
			this.getLeft().getLeftMost();
		}
			return left;
	}
	public node getRightMost()
	{
		
		if(this.getRight()==null)
		{
			System.out.println("Count"+count);
			return this;
			
		}
		else
		{
			this.getRight().getRightMost();
			count++;
		}
			return right;
	}
	public void setLeft(node n)
	{
		this.left=n;
	}
	public void setRight(node n)
	{
		this.right=n;
	}
	public node getLeft()
	{
		return this.left;
	}
	public node getRight()
	{
		return this.right;
	}

	public int getxPos() {
		return xPos;
	}

	public void setxPos(int xPos) {
		this.xPos = xPos;
	}

	public int getyPos() {
		return yPos;
	}

	public void setyPos(int yPos) {
		this.yPos = yPos;
	}

	public int getMovePosX() {
		return movePosX;
	}

	public void setMovePosX(int movePosX) {
		this.movePosX = movePosX;
	}

	public int getMovePosY() {
		return movePosY;
	}

	public void setMovePosY(int movePosY) {
		this.movePosY = movePosY;
	}

	public Piece[][] getBoard() {
		return board;
	}

	public void setBoard(Piece[][] board) {
		this.board = board;
	}

	public int getValue() {
		return value;
	}

	public void setValue(int value) {
		this.value = value;
	}

}
