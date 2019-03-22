import java.util.Scanner;

import javax.swing.JFrame;
import javax.swing.JOptionPane;

public class TicTacToe
{	
	public static void main(String[] args)
	{
		Game game = new Game();
		GamePlay f = new GamePlay(game);
		f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		f.setSize(500, 500);
		f.setVisible(true);
    	f.setLocationRelativeTo(null);
		f.setResizable(false);
	}
}

