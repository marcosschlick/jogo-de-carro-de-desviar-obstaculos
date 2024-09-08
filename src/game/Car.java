package game;

import java.awt.Color;
import java.awt.Graphics;

import javax.swing.ImageIcon;
import javax.swing.JPanel;

@SuppressWarnings("serial")
public class Car extends JPanel {
	private ImageIcon image;
	boolean colisation = false;
	private int points = 0;

	public Car() {
		this.image = new ImageIcon(
				"C:\\Users\\marcos\\eclipse-workspaces\\eclipse-workspace\\jogo-de-carro-de-desviar-obstaculos\\src\\game/car.png");
		setBackground(new Color(0, 0, 0, 0));
		setBounds(350, 550, image.getIconWidth(), image.getIconHeight());
		setVisible(true);
	}

	public void moveRight() {
	
			setLocation(this.getX() + 15, this.getY());
			repaint();
			
		
	}

	public void moveLeft() {

			setLocation(this.getX() - 15, this.getY());
			repaint();
			
		
	}

	public void checkCollision(int obstacleX, int obstacleY, int obstacleWidth, int obstacleHeight) {
		int carRight = this.getX() + this.getWidth();
		int carBottom = this.getY() + this.getHeight();

		int obstacleRight = obstacleX + obstacleWidth;
		int obstacleBottom = obstacleY + obstacleHeight;

		boolean collisionX = this.getX() < obstacleRight && carRight > obstacleX && this.getY() > obstacleY;

		boolean collisionY = this.getY() < obstacleBottom && carBottom > obstacleY;

		colisation = collisionX && collisionY;
	}

	public int getPoints() {
		return points;
	}

	public void addPoints() {
		this.points++;
	}

	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		g.drawImage(image.getImage(), 0, 0, this);
	}
}
