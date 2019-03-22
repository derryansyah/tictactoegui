import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.GridLayout;
import java.awt.BorderLayout;
import java.awt.Color;


import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JButton;
import javax.swing.JFrame;

public class GamePlay extends JFrame
{
	JButton[][] buttons = new JButton[3][3];
	private GridLayout gridLayout; 
	private char player, opponent;
	private Game game;
	
	
	public GamePlay(Game game)
	{
		super("TIC TAC TOE Game");
		this.game = game;
		drawFrame();
	}
	
	private void newGame()
	{
		clearFrame();
		Game game = new Game();
		GamePlay frame = new GamePlay(game);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setSize(500, 500);
		frame.setBackground(Color.BLACK);
    	frame.setLocationRelativeTo(null);
		frame.setVisible(true);	
	}
	private void clearFrame()
	{
		this.dispose();
	}

	public void drawFrame()
	{
		Object[] options1 = {"Player X", "Player O"};
		
		int mode = JOptionPane.showOptionDialog(null, "Choose your Player", "Player Selection", JOptionPane.YES_NO_OPTION, JOptionPane.PLAIN_MESSAGE, null, options1, null);
		if (mode == JOptionPane.YES_OPTION)
		{
			player = 'X';
		}
		else
		{
			player = 'O';
		}
		
		opponent = player == 'X' ? 'O' : 'X';
		gridLayout = new GridLayout(3, 3, 5, 5);
		getContentPane().setBackground(Color.BLACK);
		setLayout(gridLayout);
		ButtonHandler buttonHandler = new ButtonHandler();
		for (int i = 0; i < buttons.length; i++)
		{
			for (int j = 0; j < buttons[i].length; j++)
			{
				buttons[i][j] = new JButton();
				buttons[i][j].setBackground(Color.WHITE);
			    buttons[i][j].addActionListener(buttonHandler);
			    add(buttons[i][j]);
			}
		}
		if (player == 'O')
		{
			makeAIMove();
		}
		this.revalidate();
	}

	public void setButtonText(int i, int j)
	{
		buttons[i][j].setText("" + opponent);
		buttons[i][j].setFont(new Font("Arial", Font.PLAIN, 73));
	}
	public char getPlayer()
	{
		return this.player;
	}
	public void makeUserMove(JButton buttonPressed, int x, int y)
	{
		buttonPressed.setFont(new Font("Arial", Font.PLAIN, 73));
		buttonPressed.setText("" + player);
		game.makeMove(x, y);
	}
	public void makeAIMove()
	{
		AlgoAI AI = new AlgoAI(game.getBoard(), opponent);
		Action Move = AI.getBestChoice();
		game.makeMove(Move.getAction()[0], Move.getAction()[1]);
		setButtonText(Move.getAction()[0], Move.getAction()[1]);
	}
	public boolean checkGameOver()
	{
		if (game.getBoard().isGameOver())
		{
			String message;
			if(game.getBoard().whoWon() == player)
			{
				message = "YOU WON";
			}
			else if(game.getBoard().whoWon() == opponent)
			{
				message = "YOU LOSE, Good luck next time!";
			}
			else
			{
				message = "Game Draw!";
			}			
			Object[] options2 = { "New Game", "Cancel" };
			
			int mode = JOptionPane.showOptionDialog(null, message, "Game Over", JOptionPane.YES_NO_OPTION, JOptionPane.PLAIN_MESSAGE, null, options2, null);
			if (mode == JOptionPane.YES_OPTION)
			{
				game = new Game();
				newGame();

			} 
			else
			{
				clearFrame();
			}
			return true;
		}
		else
		{
			return false;
		}
	}
	private class ButtonHandler implements ActionListener
	{
		@Override
		public void actionPerformed(ActionEvent e)
		{
			int x = 0;
			int y = 0;
			JButton buttonPressed = (JButton) e.getSource();
			for (int i = 0; i < buttons.length; i++)
			{
				for (int j = 0; j < buttons[i].length; j++)
				{
					if (buttonPressed.equals(buttons[i][j]))
					{
						x = i;
						y = j;
					}
				}
			}		
			// do isValid Play here
			if (game.getBoard().isValidPlay(x, y))
			{
				makeUserMove(buttonPressed, x, y);
				if (!checkGameOver())
				{
					makeAIMove();
					checkGameOver();
				}
			}
			else
			{
				JOptionPane.showMessageDialog(null, "Choose Another Box !!!", "Invalid Move !!!", JOptionPane.ERROR_MESSAGE);
			}
		}
	}
}
