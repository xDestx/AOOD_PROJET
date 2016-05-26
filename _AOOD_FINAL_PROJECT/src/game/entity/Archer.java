package game.entity;

import java.awt.Rectangle;

import game.Game;
import game.ai.Mind;
import game.ai.enemy.Shooter;
import game.graphic.PlayerHitAnimation;
import game.world.Location;

public class Archer extends Enemy{
	
	protected Mind m;
	private int cooldownTicks;
	//for attacking
	private int cooldownTicksDefault;
	
	public Archer(Location l, int health, int strength)
	{
		super(l, health, strength);
		m = new Shooter(this);
		setBounds(new Rectangle((int)getLocation().getX(),(int) getLocation().getY(), 100, 100));
		//only increases down and to the left, can't get more up + right
		setAttackBounds(new Rectangle(((int)getLocation().getX() + 1000), ((int)getLocation().getY() - 1000), -1000, -1000));
		cooldownTicksDefault = 100;
		cooldownTicks = cooldownTicksDefault;
	}
	
	@Override
	public void attack() {
		if(cooldownTicks >= cooldownTicksDefault)
		{
			if(Game.getCurrentGame().getPlayer().getBounds().intersects(getAttackBounds()))
			{
				Game.getCurrentGame().getPlayer().wasHit(strength);
			}
			addAnimation(new PlayerHitAnimation(this));
			cooldownTicks = 0;
		}
	}
	
	public void tick()
	{
		super.tick();
		m.think();
		cooldownTicks++;
	}

}