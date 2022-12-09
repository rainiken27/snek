package snakegame;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.JFrame;

public class Game extends JFrame  implements KeyListener{

	private static final long serialVersionUID = 1L;
	
	public static final int WIDTH = 30;
	public static final int HEIGHT = 30;
	public static final int SCALE = 20;
	
	public int state;
	public final int beginState = 0;
	public final int playState = 1;
	public final int pauseState = 2;
	public final int endState = 3;
	
	private Snake player;
	private Apple apple;
	private GamePanel gamePanel;
	private JFrame window;
	
	public Game() {
		
		window = new JFrame("Snake");
		player = new Snake();
		apple = new Apple(player);
		
		gamePanel = new GamePanel(this);
		window.add(gamePanel);
		
		window.setSize((WIDTH + 1) * SCALE, (HEIGHT + 2)* SCALE );
		window.setVisible(true);
		window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
	
	public void begin() {
		state = beginState;
	}
	
	public void start() {
		state = playState;
	}
	
	public void end() {
		state = endState;
	}
	
	public void update() {
		if(state == playState) {
			
			if(isSnakeEatingFood()) {
				player.grow();
				apple.spawnApple(player);
			}
			else if (isSnakeOutOfBounds() || isSnakeEatingSelf()) {
				this.end();
			}
			player.move();
		}
	}
	
	private boolean isSnakeOutOfBounds() {
		if(player.getSnakeX()[0] < 0 || player.getSnakeX()[0] >= WIDTH * Game.SCALE
				|| player.getSnakeY()[0] < 0|| player.getSnakeY()[0] >= HEIGHT * Game.SCALE) {
			return true;
		}
		return false;
	}
	
	private boolean isSnakeEatingFood() {
		if(player.getSnakeHeadX() == apple.getX() * Game.SCALE 
				&& player.getSnakeHeadY() == apple.getY() * Game.SCALE) {
			return true;
		}
		return false;
	}
	
	private boolean isSnakeEatingSelf() {
		for(int i = player.getLength(); i > 0; i--) {
			if(player.getSnakeHeadX() == player.getSnakeX()[i]
					&& player.getSnakeHeadY() == player.getSnakeY()[i]) {
				return true;
			}
		}
		return false;
	}

	@Override
	public void keyPressed(KeyEvent e) {
		
		int keyCode = e.getKeyCode();
		
		if(state == playState) {
			if(keyCode == KeyEvent.VK_W && player.getMove() != "DOWN") {
				player.up();
			}
		
			if(keyCode == KeyEvent.VK_S && player.getMove() != "UP") {
				player.down();
			}
		
			if(keyCode == KeyEvent.VK_A && player.getMove() != "RIGHT") {
				player.left();
			}
		
			if(keyCode == KeyEvent.VK_D && player.getMove() != "LEFT") {
				player.right();
			}
		}
		else {
			this.start();
		}
	}

	public Snake getPlayer() {
		return player;
	}

	public Apple getApple() {
		return apple;
	}
	
	@Deprecated
	public void keyReleased(KeyEvent e) {}
	
	@Deprecated
	public void keyTyped(KeyEvent e) {}
}
