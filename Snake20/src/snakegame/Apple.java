package snakegame;

public class Apple {
	private int x;
	private int y;
	
	
	public Apple(Snake player) {
		this.spawnApple(player);
	}
	
	
	public boolean isAppleOnSnake(Snake player){
		for (int i = 0; i < player.getLength(); i++) {
			if(player.getSnakeX()[i] == x && player.getSnakeY()[i] == y) {
				return true;
			}
		}	
		return false;
	}
	
	public void spawnApple(Snake player) {
		do {
			x = (int)(Math.random() * Game.WIDTH - 1);
			y = (int)(Math.random() * Game.HEIGHT - 1);
		}  while ((isAppleOnSnake(player)));
	}

	public int getX() {
		return x;
	}

	public int getY() {
		return y;
	}
}
