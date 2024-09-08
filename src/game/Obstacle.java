package game;

import java.awt.Color;
import java.awt.Graphics;
import java.util.Random;
import java.util.Timer;
import java.util.TimerTask;

import javax.swing.ImageIcon;
import javax.swing.JPanel;

@SuppressWarnings("serial")
public class Obstacle extends JPanel {

	private ImageIcon image;
	Timer timer = new Timer();

	public Obstacle() {
	}

	public Obstacle(int num, int x) {
		this.image = new ImageIcon(getURL(num));
		setBackground(new Color(0, 0, 0, 0));
		setBounds(x, 10, image.getIconWidth(), image.getIconHeight());
		setVisible(true);
	}

	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		g.drawImage(image.getImage(), 0, 0, this);
	}

	public void move(Car car) {
		TimerTask task = new TimerTask() {
			@Override
			public void run() {

				if (!car.colisation) {
					setLocation(getX(), getY() + 5);
					repaint();
					if (getY() > 800) {
						this.cancel();
						car.addPoints();
						getParent().remove(Obstacle.this);
					}
				} else {
					this.cancel();
					
				}
				car.checkCollision(getX(), getY(), getWidth(), getHeight());
			}
		};
		timer.scheduleAtFixedRate(task, 0, 20);
	}

	public void addObstacle(JPanel contentPane, Car car) {
		Random random = new Random();
		int num = random.nextInt(2);
		Obstacle obs = new Obstacle(num, car.getX());
		contentPane.add(obs);
		obs.move(car);

	}

	private static String getURL(int num) {
		switch (num) {
		case 0:
			return "C:\\Users\\marcos\\eclipse-workspaces\\eclipse-workspace\\jogo-de-carro-de-desviar-obstaculos\\src\\game/big rock.png";
		default:
			return "C:\\Users\\marcos\\eclipse-workspaces\\eclipse-workspace\\jogo-de-carro-de-desviar-obstaculos\\src\\game/small rock.png";
		}
	}
}
