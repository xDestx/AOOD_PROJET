package game.entity;

import java.awt.Rectangle;
import java.util.ArrayList;

import game.Game;
import game.ai.Mind;
import game.ai.enemy.Shooter;
import game.graphic.PlayerHitAnimation;
import game.world.Location;
import game.world.Vector;

public class Archer extends Enemy {
	
	protected Mind m;
	private int cooldownTicks;
	//for attacking
	private int cooldownTicksDefault;
	
	public Archer(Location l, int health, int strength)
	{
		super(l, health, strength);
		m = new Shooter(this);
		setBounds(new Rectangle((int)getLocation().getX(),(int) getLocation().getY(), 100, 100));
		setAttackBounds(new Rectangle((int)(getLocation().getX() - (LivingEntity.WIDTH * 2.5)), ((int)(getLocation().getY() - (LivingEntity.HEIGHT * 2.5))), (int)LivingEntity.WIDTH * 4, (int)LivingEntity.HEIGHT * 4));
		cooldownTicksDefault = 100;
		cooldownTicks = cooldownTicksDefault;
	}
	
	public Archer()
	{
		super(new Location(0,0), 100, 10);
		m = new Shooter(this);
		setBounds(new Rectangle((int)getLocation().getX(),(int) getLocation().getY(), 100, 100));
		setAttackBounds(new Rectangle((int)(getLocation().getX() - (LivingEntity.WIDTH * 2.5)), ((int)(getLocation().getY() - (LivingEntity.HEIGHT * 2.5))), (int)LivingEntity.WIDTH * 4, (int)LivingEntity.HEIGHT * 4));
		cooldownTicksDefault = 100;
		cooldownTicks = cooldownTicksDefault;
	}
	
	@Override
	public void attack() {
		if(cooldownTicks >= cooldownTicksDefault)
		{
			if(Game.getCurrentGame().getPlayer().getBounds().intersects(getAttackBounds()))
			{
				//Game.getCurrentGame().getPlayer().wasHit(strength);
				Location l = Game.getCurrentGame().getPlayer().getLocation();
				double lx = l.getX()-getLocation().getX();
				double ly = l.getY()-getLocation().getY();
				double hypo = Math.sqrt(Math.pow(lx, 2)+Math.pow(ly, 2));
			//	double d = Math.acos(Math.abs(l.getX()-getLocation().getX())/hypo);
				double xp = lx/hypo;
				double yp = ly/hypo;
				/*
				 * Proportional
				 */
				double xv = xp * 10;
				double yv = yp * 10;
				//Total V (Hypotenuse) = 10
				Vector v = new Vector(xv,yv);
				Game.getCurrentGame().getLevel().addProjectile(new Projectile(new Location(getLocation()), v, this, strength));//Total speed of 10 p/t (pixels/tick)
			}
			//addAnimation(new PlayerHitAnimation(this));
			cooldownTicks = 0;
		}
	}
	
	
	public void tick()
	{
		super.tick();
		setAttackBoundsL((int)(getLocation().getX() - (LivingEntity.WIDTH * 2.5)), ((int)(getLocation().getY() - (LivingEntity.HEIGHT * 2.5))));
		m.think();
		cooldownTicks++;
	}

}
