import java.util.ArrayList;

public class AlgoAI
{
	private char playerAI, opponent;
	public Action bestChoice;

	public int endGameScore(Board gameBoard)
	{
		if (gameBoard.whoWon() == playerAI)
		{
			return 10;
		} 
		else if (gameBoard.whoWon() == opponent)
		{
			return -10;
		} 
		else
		{
			return 0;
		}
	}

	public Action getBestChoice()
	{
		return this.bestChoice;
	}

	public AlgoAI(Board board, char playerAI)
	{
		this.playerAI = playerAI;
		if (playerAI == 'O')
		{
			opponent = 'X';
		} else
		{
			opponent = 'O';
		}
		// making new board
		char[][] boardArrayChar = new char[3][3];
		for (int i = 0; i < 3; i++)
		{
			for (int j = 0; j < 3; j++)
			{
				boardArrayChar[i][j] = board.getArrayBoard()[i][j];
			}
		}
		Board receivedBoard = new Board(boardArrayChar);
		minimax(receivedBoard);
	}

	public int minimax(Board boardR)
	{
		// making new board
		char[][] boardArrayChar = new char[3][3];
		for (int i = 0; i < 3; i++)
		{
			for (int j = 0; j < 3; j++)
			{
				boardArrayChar[i][j] = boardR.getArrayBoard()[i][j];
			}
		}
		Board board = new Board(boardArrayChar);
		if (board.isGameOver())
		{
			return endGameScore(board);
		}

		ArrayList<Integer> scores = new ArrayList<Integer>();

		Action[] moves = board.possiblePlays();
		// making new board
		char[][] boardReceivedCharArray = new char[3][3];
		for (int i = 0; i < 3; i++)
		{
			for (int j = 0; j < 3; j++)
			{
				boardReceivedCharArray[i][j] = board.getArrayBoard()[i][j];
			}
		}
		
		// populate array scores
		for (int i = 0; i < moves.length; i++)
		{
			int x = moves[i].getAction()[0];
			int y = moves[i].getAction()[1];
			char player = board.getPlayer();
			char[][] copiedCharArray = new char[3][3];
			for (int j = 0; j < 3; j++)
			{
				for (int k = 0; k < 3; k++)
				{
					copiedCharArray[j][k] = boardReceivedCharArray[j][k];
				}
			}
			Board possibleBoard = new Board(copiedCharArray, player, x, y);
			scores.add(minimax(possibleBoard));
		}
		scores.trimToSize();
		char player = board.getPlayer();
		// Decide between choosing min or max Calculation
		if (player == playerAI)
		{
			// Do max
			int maxScoreIsAt = 0;
			int max = -10;
			Integer[] scoresArray = scores.toArray(new Integer[scores.size()]);
			for (int i = 0; i < scoresArray.length; i++)
			{
				if ((int) scoresArray[i] > max)
				{
					max = (int) scoresArray[i];
					maxScoreIsAt = i;
					bestChoice = moves[i];
				}
			}
			return (int) scoresArray[maxScoreIsAt];
		} 
		else
		{
			// Do min
			int minScoreIsAt = 0;
			int min = 10;
			Integer[] scoresArray = scores.toArray(new Integer[scores.size()]);
			for (int i = 0; i < scoresArray.length; i++)
			{
				if ((int) scoresArray[i] < min)
				{
					min = (int) scoresArray[i];
					minScoreIsAt = i;
					bestChoice = moves[i];
				}
			}
			return (int) scoresArray[minScoreIsAt];
		}
	}

}