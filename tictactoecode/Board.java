import java.util.ArrayList;

public class Board
{
	private char[][] board;
	private int turn;
	private char player;

	public Board()
	{
		board = new char[][]
		{
				{ ' ', ' ', ' ' },
				{ ' ', ' ', ' ' },
				{ ' ', ' ', ' ' }, };
	}

	public Board(int turn, char[][] board)
	{
		this.board = board;
		this.turn = turn;
	}

	public Board(Board newBoard)
	{
		this.board = newBoard.getArrayBoard();
	}

	public Board(char[][] board, char player, int x, int y)
	{
		this.board = board;
		this.board[x][y] = player;
	}

	public Board(char[][] board)
	{
		this.board = board;
	}

	public char[][] getArrayBoard()
	{
		return this.board;
	}

	public void setTurn(int turn)
	{
		this.turn = turn;
	}

	public char[][] getBoard()
	{
		return board;
	}

	public void setBoard(char[][] board)
	{
		this.board = board;
	}

	public void setBoard(char player, int x, int y)
	{
		this.board[x][y] = player;
	}

	public void printBoard()
	{
		
		System.out.println(" " + this.board[0][0] + "  | " + this.board[0][1] + "  | " + this.board[0][2] + "  ");
		System.out.println("==============");
		System.out.println(" " + this.board[1][0] + "  | " + this.board[1][1] + "  | " + this.board[1][2] + "  ");
		System.out.println("==============");
		System.out.println(" " + this.board[2][0] + "  | " + this.board[2][1] + "  | " + this.board[2][2] + "  ");
		System.out.println();
		System.out.println();
		System.out.println();
		
	}

	public boolean isValidPlay(int row, int col)
	{
		if (row > 2 || col > 2 || this.board[row][col] != ' ')
		{
//			System.out.println("Ilegal play, try again");
			return false;
		} else
		{
			return true;
		}
	}

	public boolean isGameOver()
	{

		for (int i = 0; i < 3; i++)
		{
			// check vertical lines
			if (board[i][0] == board[i][1] && board[i][0] == board[i][2] && board[i][1] != ' ')
			{
				return true;
			}
			// check horizontal lines
			if (board[0][i] == board[1][i] && board[1][i] == board[2][i] && board[0][i] != ' ')
			{
				return true;
			}
		}
		// check diagonals
		if (((board[0][0] == board[1][1] && board[1][1] == board[2][2])
				|| (board[0][2] == board[1][1] && board[1][1] == board[2][0])) && board[1][1] != ' ')
		{
			return true;
		}
		if (this.getTurn() >= 9)
		{
			return true;
		} else
		{
			return false;
		}

	}

	public char whoWon()
	{
		char a = ' ';
		if (this.isGameOver())
		{
			for (int i = 0; i < 3; i++)
			{
				// check horizontal lines
				if (board[i][0] == board[i][1] && board[i][0] == board[i][2] && board[i][1] != ' ')
				{
					return board[i][1];
				}
				// check vertical lines
				else if (board[0][i] == board[1][i] && board[1][i] == board[2][i] && board[0][i] != ' ')
				{
					return board[0][i];
				}
			}
			// check diagonals
			if (((board[0][0] == board[1][1] && board[1][1] == board[2][2])
					|| (board[0][2] == board[1][1] && board[1][1] == board[2][0])) && board[1][1] != ' ')
			{
				return board[1][1];
			} else
			{
				return a;
			}
		} else
		{
			System.exit(2);
			return a;
		}
	}

	public Action[] possiblePlays()
	{
		ArrayList<Action> possiblePlays = new ArrayList<Action>();
		for (int i = 0; i < 3; i++)
		{
			for (int j = 0; j < 3; j++)
			{
				if (this.board[i][j] == ' ')
				{
					Action a = new Action(i, j);
					possiblePlays.add(a);
				}
			}
		}
		possiblePlays.trimToSize();
		return possiblePlays.toArray(new Action[possiblePlays.size()]);
	}

	public int getTurn()
	{
		int count = 0;
		for (int i = 0; i < 3; i++)
		{
			for (int j = 0; j < 3; j++)
			{
				if (this.board[i][j] != ' ')
				{
					count++;
				}
			}
		}
		return count;
	}

	public char getPlayer()
	{

		int turn = getTurn();
		if (turn % 2 == 0)
		{
			return 'X';
		} else
		{
			return 'O';
		}

	}
}
