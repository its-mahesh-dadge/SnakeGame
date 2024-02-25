package SnakeGame;
import javax.swing.*;
public class App {

	public static void main(String[] args) {
		 int BoardWidth = 600;
		 int BoardHight = BoardWidth;
		 
		 JFrame frame = new JFrame("Snake Game 🐍");
		 //frame visible
		 frame.setVisible(true);
		 //for set size of frame
		 frame.setSize( BoardWidth, BoardHight);
		 //for show the frame in center
		 frame.setLocationRelativeTo(null);
		 //frame not resizeable
		 frame.setResizable(false);
		 frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		 
		 SnakeGame SnakeGame = new SnakeGame(BoardHight, BoardHight);
		 frame.add(SnakeGame);
		 frame.pack();
		 SnakeGame.requestFocus();
		 
		 
		 

	}

}
