package game;

import java.awt.Graphics;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.Timer;
import java.util.TimerTask;

import javax.swing.JFrame;
import javax.swing.JPanel;

@SuppressWarnings("serial")
public class RaceScreen extends JFrame {

	private JPanel contentPane;
	Timer timer = new Timer();
	private Image backgroundImage;
	Car car = new Car();
	Obstacle obs = new Obstacle();

	public RaceScreen() {
		this.backgroundImage = Toolkit.getDefaultToolkit().createImage(
				"C:\\Users\\marcos\\eclipse-workspaces\\eclipse-workspace\\jogo-de-carro-de-desviar-obstaculos\\src\\game\\street.png");
		setSize(800, 800);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setLocationRelativeTo(null);
		setVisible(true);
		contentPane = new JPanel() {
			@Override
			protected void paintComponent(Graphics g) {
				super.paintComponent(g);
				g.drawImage(backgroundImage, 0, 0, getWidth(), getHeight(), this);
			}
		};
		contentPane.setLayout(null);
		setContentPane(contentPane);
		contentPane.add(car);
		addKeyListener(keyadapt);
		createBufferStrategy(2);
		newObs();
	}

	public void newObs() {
		TimerTask task = new TimerTask() {
			@Override
			public void run() {
				obs.addObstacle(contentPane, car);
				if (car.colisation) {
					this.cancel();
				}
			}
		};
		timer.scheduleAtFixedRate(task, 0, 1750);
	}

	KeyAdapter keyadapt = new KeyAdapter() {
		@Override
		public void keyPressed(KeyEvent e) {
			int key = e.getKeyCode();
			if ((key == KeyEvent.VK_RIGHT || key == KeyEvent.VK_D) && car.getX() < 680) {
				car.moveRight();
			} else if ((key == KeyEvent.VK_LEFT || key == KeyEvent.VK_A) && car.getX() > 10) {
				car.moveLeft();
			}
		}
	};

}
