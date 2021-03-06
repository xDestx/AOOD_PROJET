package game;

import java.awt.Frame;
import java.awt.Graphics;
import java.util.ArrayList;

import javax.swing.JFrame;

import game.entity.Player;
import game.entity.enemy.Enemy;
import game.entity.enemy.Stick;
import game.listener.CKeyListener;
import game.listener.CMouseListener;
import game.util.Task;
import game.world.Collidable;
import game.world.Level;
import game.world.Location;
import game.world.Wall;
import game.world.builder.LevelBuilder;

public class Game {

	private boolean playing;
	public static final int TICK = 60;
	private GFrame gf;
	private Ticker t;
	private Camera c;
	private GameState gs;
	private Player p;
	private Level currentLevel;

	//aaaa
	private static Game game;
	
	public static Game getCurrentGame()
	{
		return game;
	}
	
	public static void setCurrentGame(Game g)
	{
		Game.game = g;
	}
	
	public static void playNewGame()
	{
		game = new Game();
		game.play();
	}
	
	
	public Game() {
		playing = false;
		gf = new GFrame(this);
		gf.setVisible(true);
		gf.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		c = new Camera(new Location(0,0),this);
		t = new Ticker(this);
		p = new Player(new Location(GFrame.WIDTH/2,GFrame.HEIGHT/2), 100);
		this.addObject(p);
		t.addObject(c);
		gs = GameState.WORLD;
		LevelBuilder.init();
		currentLevel = LevelBuilder.getLevel(0);
		CMouseListener mouseList = new CMouseListener(this);
		gf.getCanvas().addMouseListener(mouseList);
		gf.getCanvas().addMouseMotionListener(mouseList);
		gf.getCanvas().addKeyListener(new CKeyListener(this));
		
	}
	
	public Camera getCamera()
	{
		return c;
	}
	
	public void addTask(Task t)
	{
		this.t.addObject(t);
	}
	
	public void setLevel(Level l)
	{
		this.currentLevel = l;
	}
	
	public Level getLevel()
	{
		return this.currentLevel;
	}
	
	public void addObject(GameObject o)
	{
		t.addObject(o);
	}
	
	public void addRenderable(Renderable r)
	{
		t.addRenderable(r);
	}

	public void play() {
		// Game loop
		playing = true;
		long last = System.nanoTime();
		double ns = 1000000000 / TICK;
		double d = 0;
		int k = 0;
		int f = 0;
		long s = 0;
		while (playing) {
			long n = System.nanoTime();
			d += (n - last) / ns;
			s += (n - last);
			last = n;
			if (d >= 1) {
				gameTick();
				k++;
				d--;
			}

			renderScreen();

			f++;
			if (s >= 1000000000) {
				System.out.println("Ticks: " + k + " | Fps: " + f);
				f = 0;
				s = 0;
				k = 0;
			}

		}
		System.exit(0);
	}

	private void gameTick() {
		t.tick();
	}

	public Player getPlayer()
	{
		return p;
	}
	
	public ArrayList<Enemy> getEnemies(){
		return getLevel().getEnemies();
	}
	
	private void renderScreen() {
		gf.render(t);
	}

	public GameState getState()
	{
		return this.gs;
	}
	
}