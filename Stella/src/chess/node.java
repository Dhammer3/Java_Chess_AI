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
	String side="";
	Player play;
	//root node constructor
	public node(Player play, Piece[][] board, int value, node l, node r)
	{
		this.play=play;
		this.setSide("Root");
	this.value=value;
	this.board=board;
	this.left=l;
	this.left.setSide("Left");
	this.right=r;
	this.right.setSide("right");
	}
	//child node constructor
	public node(Player play, int xPos, int yPos, int movePosX, int movePosY, Piece[][] board, int value)
	{
		this.play=play;
		
		this.xPos=xPos;
		this.yPos=yPos;
		this.movePosX=movePosX;
		this.movePosY=movePosY;
		this.board=board;
		this.value=value;
		this.right=null;
		this.left=null;
	
	}
	public void setSide(String side)
	{
		this.side=side;
	}
	public node getLeftMost(node root)
	{
		
		    if (root == null) {
		        return null;
		    }
		    if (root.left == null) {
		        return root;
		    }
		    return getLeftMost(root.left);
	}
	public node getRightMost(node root)
	{
		
		if (root == null) {
	        return null;
	    }
	    if (root.right == null) {
	        return root;
	    }
	    return getRightMost(root.right);
	}
	public void setLeft(node n)
	{
		this.left=n;
		n.setSide("Left");
	}
	public void setRight(node n)
	{
		this.right=n;
		n.setSide("right");
	}
	public node getLeft()
	{
		return this.left;
	}
	public String getSide()
	{
		return this.side;
	}
	public node getRight()
	{
		return this.right;
	}
	public Player getPlayer()
	{
		return this.play;
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
	public node inOrderTraverseTree(node nextNode)
	{
		if(nextNode!=null)
		{
			System.out.print(nextNode.getSide());
			System.out.print(" "+nextNode.getPlayer().toString());
			System.out.println();
			inOrderTraverseTree(nextNode.left);
			inOrderTraverseTree(nextNode.right);
		}
		
		return nextNode;	
	}

}



