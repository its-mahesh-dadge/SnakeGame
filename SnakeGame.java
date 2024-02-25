package SnakeGame;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;
import java.util.Random;

import javax.sound.sampled.UnsupportedAudioFileException;
import javax.swing.*;

public class SnakeGame extends JPanel implements ActionListener, KeyListener{
	
	private class Tiles{
		int x;
		int y;
		
		 Tiles(int x, int y){
			this.x = x;
			this.y = y;
			
			
		}
	}
	int BoardWidth;
	int BoardHight;
	int tilesize = 25;
	
	//snake
	
	Tiles snakeHead;
	ArrayList<Tiles> SnakeBody;
	
	//Food
	Tiles food;
	Random random;
	
	//Game Logic
	Timer gameloop;
	int velocityX;
	int velocityY;
	boolean gameover = false;
	
	
	//creating Constructor
	SnakeGame(int BoardWidth, int BoardHight){
		this.BoardWidth = BoardWidth;
		this.BoardHight = BoardHight;
		setPreferredSize(new Dimension(this.BoardWidth, this.BoardHight));
		setBackground(Color.black);
		addKeyListener(this);
		setFocusable(true);
		
		 snakeHead = new Tiles(5, 5);
		 SnakeBody = new ArrayList<Tiles>();
		 
		 
		 food = new Tiles(10,10);
		 random = new Random();
		 placefood();
		 
		 velocityX = 0;
		 velocityY = 0;
		 
		 
		 gameloop = new Timer(150,this);
		 gameloop.start();
		 
		
		
	}
		public void paintComponent(Graphics g) {
			super.paintComponent(g);
			draw(g);
		}
		private void draw(Graphics g) {
			//grid
//			for(int i=0; i<BoardWidth/tilesize;i++) {
//				//for vertical snake
//				g.drawLine(i*tilesize,0, i*tilesize, BoardHight);
//				//for horizontal snake
//				g.drawLine(0, i*tilesize, BoardWidth, i*tilesize);
//			}
			//Food
			g.setColor(Color.red);
			//g.fillRect(food.x*tilesize, food.y*tilesize, tilesize, tilesize );
			g.fill3DRect(food.x*tilesize, food.y*tilesize, tilesize, tilesize,true );
			
			//Snake
			 g.setColor(Color.green);
			 //g.fillRect(snakeHead.x*tilesize, snakeHead.y*tilesize, tilesize, tilesize);
			 g.fill3DRect(snakeHead.x*tilesize, snakeHead.y*tilesize, tilesize, tilesize,true);
			 
			 
			 //Snake Body
			 for(int i=0; i<SnakeBody.size();i++) {
				 Tiles snakepart = SnakeBody.get(i);
				// g.fillRect(snakepart.x*tilesize, snakepart.y*tilesize, tilesize, tilesize);
				 g.fill3DRect(snakepart.x*tilesize, snakepart.y*tilesize, tilesize, tilesize, true);
			 }
			 //Score 
			 g.setFont(new Font("Arial",Font.PLAIN,16));
			 if(gameover) {
				 g.setColor(Color.red);
				 g.drawString("Game Over "+String.valueOf(SnakeBody.size()),tilesize - 16, tilesize);
			 }else {
				 g.drawString("Score: "+String.valueOf(SnakeBody.size()),tilesize - 16, tilesize);
			 }
			 
		}
		public void placefood() {
			food.x = random.nextInt(BoardWidth/tilesize);//600/25 =24
			food.y = random.nextInt(BoardHight/tilesize);
		}
		
		public boolean collision(Tiles tile1, Tiles tile2) {
			return tile1.x == tile2.x && tile1.y == tile2.y; 
		}
		
		
		public void move() {
			if(collision(snakeHead,food)) {
				SnakeBody.add(new Tiles(food.x, food.y));
				placefood();
			}
			//move snake body
			for(int i=SnakeBody.size()-1;i>=0;i--) {
				Tiles snakepart = SnakeBody.get(i);
				if(i==0) {
					snakepart.x = snakeHead.x;
					snakepart.y = snakeHead.y;
				}else {
					Tiles prevsnakepart = SnakeBody.get(i-1);
					snakepart.x = prevsnakepart.x;
					snakepart.y = prevsnakepart.y;
					
					
				}
			}
			//snake head
			snakeHead.x += velocityX;
			snakeHead.y += velocityY;
			
			//game over
			for(int i=0;i<SnakeBody.size();i++) {
				Tiles snakepart = SnakeBody.get(i);
				//collide with the snake head
				if(collision(snakeHead,snakepart)) {
					gameover = true;
				}
			}
			if(snakeHead.x*tilesize < 0 || snakeHead.x*tilesize > BoardWidth ||
			   snakeHead.y*tilesize < 0 || snakeHead.y*tilesize > BoardHight) {
				gameover = true;
				
			}
		}
		
		@Override
		public void actionPerformed(ActionEvent e) {
			move();
			repaint();
			if(gameover) {
				gameloop.stop();
			}
			
		}
		@Override
		public void keyPressed(KeyEvent e) {
			if(e.getKeyCode()==KeyEvent.VK_UP && velocityY !=1) {
				velocityX = 0;
				velocityY = -1;
			}else if(e.getKeyCode()==KeyEvent.VK_DOWN && velocityY != -1) {
				velocityX = 0;
				velocityY = 1;
			}else if (e.getKeyCode()==KeyEvent.VK_LEFT && velocityX !=1) {
				velocityX = -1;
				velocityY = 0;
			}else if  (e.getKeyCode()==KeyEvent.VK_RIGHT && velocityX !=-1) {
				velocityX = 1;
				velocityY = 0;
				
			}
			
			
		}
		@Override
		public void keyReleased(KeyEvent e) {
			
		}
		@Override
		public void keyTyped(KeyEvent e) {
			
		}
		
		
}
