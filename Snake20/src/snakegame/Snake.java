package snakegame;

public class Snake {
	private final int snakeX[] = new int [Game.WIDTH * Game.HEIGHT];
	private final int snakeY[] = new int [Game.WIDTH * Game.HEIGHT];
	
	private final int startPosX = (Game.WIDTH  / 2) * Game.SCALE;
	private final int startPosY = (Game.HEIGHT / 2) * Game.SCALE;
	
	private int length = 1;
	
	private String direction; 
	
	public Snake() {
		
		snakeX[0] = startPosX;
		snakeY[0] = startPosY;
		
		direction = "RIGHT";
	}
	
	
	public void move() {
		
		for (int i = length; i > 0; i--) {
			snakeX[i] = snakeX[i-1];
			snakeY[i] = snakeY[i-1];	
		}
		
		if (direction == "UP") {
			snakeY[0] -= Game.SCALE;
		}
		if (direction == "LEFT") {
			snakeX[0] -= Game.SCALE;
		}
		if (direction == "DOWN") {
			snakeY[0] += Game.SCALE;
		}
		if (direction == "RIGHT"){
			snakeX[0] += Game.SCALE;
		}
	}
	
	public void grow() {
		length++;
	}
	
	public int getLength() {
		return length;
	}
	
	public int[] getSnakeX() {
		return snakeX;
	}

	public int[] getSnakeY() {
		return snakeY;
	}
	
	public int getSnakeHeadX() {
		return snakeX[0];
	}
	
	public int getSnakeHeadY() {
		return snakeY[0];
	}

	public String getMove() {
		return direction;
	}
	
	public void up() {
		direction = "UP";
	}
	
	public void down() {
		direction = "DOWN";
	}
	
	public void left() {
		direction = "LEFT";
	}
	
	public void right() {
		direction = "RIGHT";
	}
}