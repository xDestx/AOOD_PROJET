package game.entity;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;

import game.world.Vector;
import game.Game;
import game.util.Task;
import game.world.Location;

public class Projectile extends Entity {

	private Vector velocity;
	private Entity owner;
	private int damage;
	/*
	 * No collision
	 * Why?
	 * Lazy.
	 */
	
	public Projectile(Location l, Vector v, Entity owner, int damage) {
		super(l);
		this.owner = owner;
		this.damage = damage;
		this.velocity=v;
		setBounds(new Rectangle((int)l.getX(),(int)l.getY(),20,20));
		/*
		 * Default life of 5 seconds
		 */
	}

	@Override
	public void render(Graphics g, int xo, int yo) {
		//Render circle
		Color last = g.getColor();
		g.setColor(Color.blue);
		g.fillOval(xo, yo, 20, 20);
		g.setColor(last);
	}
	
	public void moveX(double x)
	{
		this.getLocation().setX(this.getLocation().getX()+x);
		this.getBounds().setLocation(this.getLocation().toPoint());
	}
	
	public void moveY(double y)
	{

		this.getLocation().setY(this.getLocation().getY()+y);
		this.getBounds().setLocation(this.getLocation().toPoint());
	}

	@Override
	public void tick() {
		moveX(velocity.getX());
		moveY(velocity.getY());
		/*
		 * Check collision later
		 */
		Player p = Game.getCurrentGame().getPlayer();
		if(p.getBounds().intersects(getBounds()))
		{
			if(owner != p)
			{
				p.wasHit(damage);
				final Projectile bye = this;
				Game.getCurrentGame().addTask(new Task(0)
						{
					@Override
					public void run()
					{
						Game.getCurrentGame().getLevel().removeProjectile(bye);
					}
						});
			}
		}
	}

}
