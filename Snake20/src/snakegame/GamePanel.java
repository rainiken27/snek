package snakegame;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import java.awt.Color;
import java.awt.Graphics;

import javax.swing.JPanel;
import javax.swing.Timer;

public class GamePanel extends JPanel implements ActionListener{

	private static final long serialVersionUID = 1L;

	private int gameDelay = 150;
	private Timer timer = new Timer(gameDelay, this);
	
	private Snake playerSnake;
	private Apple gameApple;
	private Game game;
	
	
	public GamePanel(Game drawnGame) {
		timer.start();
		
		game = drawnGame;
		drawnGame.begin();
		
		playerSnake = drawnGame.getPlayer();
		gameApple = drawnGame.getApple();
		
		this.addKeyListener(drawnGame);
		this.setFocusable(true);
	}
	
	
	public void paintComponent(Graphics g) {
		super.paintComponent(g);

		drawBackground(g);
		
		if(game.state == game.beginState) {
			drawIntroScreen(g);
		}
		
		else if(game.state == game.playState) {
			drawApple(g);
			drawSnake(g);
			drawScoreBoard(g);
			
		}

		else if (game.state == game.endState){
			//GameOver Screen
		}
	}
	
	public void drawBackground(Graphics g) {
		g.setColor(Color.BLACK);
		g.fillRect(0, 0, Game.WIDTH * Game.SCALE, Game.HEIGHT * Game.SCALE);
	}
	
	public void drawIntroScreen(Graphics g) {
		g.setColor(Color.WHITE);
		g.drawString("Snake", Game.WIDTH/2 * Game.SCALE, Game.HEIGHT / 2 * Game.SCALE - 40);
		g.drawString("Press Any Key To Start", Game.WIDTH/2 * Game.SCALE - 40, Game.HEIGHT / 2 * Game.SCALE - 20);
	}
	
	public void drawApple(Graphics g) {
		g.setColor(Color.RED);
		g.fillOval(gameApple.getX() * Game.SCALE, gameApple.getY() * Game.SCALE, Game.SCALE, Game.SCALE);
	}
	
	public void drawScoreBoard(Graphics g) {
		g.setColor(Color.WHITE);
		g.drawString("Your Score: " + (playerSnake.getLength() - 1),Game.SCALE,Game.SCALE);
	}
	
	public void drawSnake(Graphics g) {
		g.setColor(Color.GREEN);
		for(int i = 0; i < playerSnake.getLength(); i++) {
			g.fillRect(playerSnake.getSnakeX()[i], playerSnake.getSnakeY()[i], Game.SCALE, Game.SCALE);
		}
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		repaint();
		game.update();
	}
	
}