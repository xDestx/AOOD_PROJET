package game.listener;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

import game.Game;
import game.GameState;

public class CKeyListener extends KeyAdapter {

	private Game g;
	
	public CKeyListener(Game g)
	{
		this.g = g;
	}
	
	
	@Override
	public void keyPressed(KeyEvent e)
	{
		if(g.getState() == GameState.WORLD)
		{
			keyPressedWorld(e);
		}
	}
	
	@Override
	public void keyReleased(KeyEvent e)
	{
		if(g.getState() == GameState.WORLD)
		{
			keyReleasedWorld(e);
		}
	}
	
	
	
	private void keyPressedWorld(KeyEvent e)
	{
		if(e.getKeyCode() == KeyEvent.VK_D && !g.getPlayer().getHoriz())
		{
			g.getPlayer().getVelocity().setX(5);
			g.getPlayer().setHoriz(true);
		}
		if(e.getKeyCode() == KeyEvent.VK_A && !g.getPlayer().getHoriz())
		{
			g.getPlayer().getVelocity().setX(-5);
			g.getPlayer().setHoriz(true);
		}
		if(e.getKeyCode() == KeyEvent.VK_W && !g.getPlayer().getVert())
		{
			g.getPlayer().getVelocity().setY(-5);
			g.getPlayer().setVert(true);
		}
		if(e.getKeyCode() == KeyEvent.VK_S && !g.getPlayer().getVert())
		{
			g.getPlayer().getVelocity().setY(5);
			g.getPlayer().setVert(true);
		}
	}
	
	private void keyReleasedWorld(KeyEvent e)
	{
		if(e.getKeyCode() == KeyEvent.VK_D && g.getPlayer().getHoriz())
		{
			g.getPlayer().getVelocity().setX(0);
			g.getPlayer().setHoriz(false);
		}
		if(e.getKeyCode() == KeyEvent.VK_A && g.getPlayer().getHoriz())
		{
			g.getPlayer().getVelocity().setX(0);
			g.getPlayer().setHoriz(false);
		}
		if(e.getKeyCode() == KeyEvent.VK_W && g.getPlayer().getVert())
		{
			g.getPlayer().getVelocity().setY(0);
			g.getPlayer().setVert(false);
		}
		if(e.getKeyCode() == KeyEvent.VK_S && g.getPlayer().getVert())
		{
			g.getPlayer().getVelocity().setY(0);
			g.getPlayer().setVert(false);
		}
	}
	
	
}
