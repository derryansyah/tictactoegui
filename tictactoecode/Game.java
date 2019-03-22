public class Game 
{
	
	private int turn =0;
	private Board board = new Board();
	private char player;
	
	
	public Board getBoard()
	{
		return this.board;
	}
	
	public void makeMove(int row, int col)
	{
		turn++;
		board.setBoard(this.getPlayer(), row, col);
	}
	
	public char getPlayer()
	{
		if (turn % 2 == 0)
		{
			player = 'O';
			return player;
		}
		else 
		{
			player = 'X';
			return player;
		}
	}
	
	
}
