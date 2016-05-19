package game.world;

import javax.imageio.ImageIO;
import javax.swing.*;

import game.GFrame;
import game.Game;
import game.GameObject;
import game.Renderable;
import game.entity.Enemy;

import java.util.ArrayList;
import java.util.LinkedList;
import java.io.Serializable;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;

public class Level implements Renderable {
	
	//To implement serializable later
	
    private ArrayList<Collidable> listOfCollidables;
    private ArrayList<Enemy> enemies;
    private int id;
    private BufferedImage bi;
    private GroundPattern pattern;
    private Location l;
    
    public Level(int idNumber){
        listOfCollidables = new ArrayList<Collidable>();
        id = idNumber;
        l = new Location(0,0);
        enemies = new ArrayList<Enemy>();
        try
        {
        	bi = ImageIO.read(getClass().getResourceAsStream("/images/grass.png"));
        } catch (Exception e)
        {
        	e.printStackTrace();
        }
        pattern = new GroundPattern();
    }
    
    public Level(int idNumber, BufferedImage b){
        listOfCollidables = new ArrayList<Collidable>();
        enemies = new ArrayList<Enemy>();
        id = idNumber;
        bi = b;
        pattern = new GroundPattern();
    }
    
    public Level(int idNumber, ArrayList<Collidable> cs){
        listOfCollidables = cs;
        enemies = new ArrayList<Enemy>();
        id = idNumber;
        try
        {
        	bi = ImageIO.read(getClass().getResourceAsStream("/images/grass.png"));
        } catch (Exception e)
        {
        	e.printStackTrace();
        }
        pattern = new GroundPattern();
    }
    
    public Level(int idNumber, BufferedImage b, ArrayList<Collidable> cs){
        listOfCollidables = cs;
        enemies = new ArrayList<Enemy>();
        id = idNumber;
        bi = b;
        pattern = new GroundPattern();
    }
    
    
    public int getId(){
        return id;
    }
    
    public ArrayList<Collidable> getListOfCollidables(){
        return listOfCollidables;
    }
    
    public ArrayList<Enemy> getEnemies(){
        return enemies;
    }
    
    public void addEnemy(Enemy e)
    {
    	enemies.add(e);
    }

	@Override
	public void render(Graphics g, int x, int y) {
		if(bi!=null)
		{
			int startX = 0-((int)Game.getCurrentGame().getCamera().getLocation().getX()%bi.getWidth())-bi.getWidth();
			int startY = 0-((int)Game.getCurrentGame().getCamera().getLocation().getY()%bi.getHeight())-bi.getHeight();
			int cX = startX;
			int cY = startY;
			while (cX < GFrame.WIDTH || cY < GFrame.HEIGHT)
			{
				g.drawImage(bi, cX, cY, null);
				cX+=bi.getWidth();
				if(cX >= GFrame.WIDTH)
				{
					cY+=bi.getWidth();
					if(cY >= GFrame.HEIGHT)
					{
						break;
					} else
					{
						cX=startX;
					}
				}
			}
		} else {
			int startX = 0-((int)Game.getCurrentGame().getCamera().getLocation().getX()%100)-100;
			int startY = 0-((int)Game.getCurrentGame().getCamera().getLocation().getY()%100)-100;
			int cX = startX;
			int cY = startY;
			while (cX < GFrame.WIDTH || cY < GFrame.HEIGHT)
			{
				pattern.render(g, cX, cY);
				cX+=100;
				if(cX >= GFrame.WIDTH)
				{
					cY+=100;
					if(cY >= GFrame.HEIGHT)
					{
						break;
					} else
					{
						cX=startX;
					}
				}
			}
		}
		
		//Draw WorldObjects
		
	}

	public void removeEnemy(Enemy e)
	{
		enemies.remove(e);
	}
	

	@Override
	public Location getLocation() {
		// TODO Auto-generated method stub
		return l;
	}

	@Override
	public Rectangle getBounds() {
		// TODO Auto-generated method stub
		return null;
	}


    
   
    
}
