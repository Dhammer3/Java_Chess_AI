package chess;

import java.util.Stack;

public class miniMaxTree 

{
	int max;
	int min;
	Stack <Integer> moveList= new Stack<Integer>();
	public miniMaxTree(Piece[][] board, int depth, Player play, Player enemy)
	{
		//fill the moveList
		
		//LinkedList<> 
		for(int i=0; i<play.getPieceList().size(); i++)
		{
			
		}
	}
	public Stack<Integer> getMoveList(Player play, Player enemy, Piece[][] board)
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
						moveList.push(iteratorY);
						moveList.push(iteratorX);
						moveList.push(play.getPiece(i).getY());
						moveList.push(play.getPiece(i).getX());
						
						
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
						moveList.push(iteratorY);
						moveList.push(iteratorX);
						moveList.push(play.getPiece(i).getY());
						moveList.push(play.getPiece(i).getX());
						
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
						moveList.push(iteratorY);
						moveList.push(iteratorX);
						moveList.push(play.getPiece(i).getY());
						moveList.push(play.getPiece(i).getX());
					
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
						moveList.push(iteratorY);
						moveList.push(iteratorX);
						moveList.push(play.getPiece(i).getY());
						moveList.push(play.getPiece(i).getX());
						
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
						moveList.push(iteratorY);
						moveList.push(iteratorX);
						moveList.push(play.getPiece(i).getY());
						moveList.push(play.getPiece(i).getX());
					
				
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
						moveList.push(iteratorY);
						moveList.push(iteratorX);
						moveList.push(play.getPiece(i).getY());
						moveList.push(play.getPiece(i).getX());
						
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
						moveList.push(iteratorY);
						moveList.push(iteratorX);
						moveList.push(play.getPiece(i).getY());
						moveList.push(play.getPiece(i).getX());
						
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
						moveList.push(iteratorY);
						moveList.push(iteratorX);
						moveList.push(play.getPiece(i).getY());
						moveList.push(play.getPiece(i).getX());
						
						
						
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
						
							moveList.push(enemy.postOrder.get(i).getY());
							moveList.push(enemy.postOrder.get(i).getX());
							moveList.push(play.inOrder.get(i).getY());
							moveList.push(play.inOrder.get(i).getX());	
							
					}
					
			}
		}
		
		return moveList;

	}
	public void setMax(int a)
	{
		max=a;
		
	}
	public int getMax()
	{
		return max;
	}

}
