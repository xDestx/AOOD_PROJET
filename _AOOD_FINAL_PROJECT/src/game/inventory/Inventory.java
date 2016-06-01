package game.inventory;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

import game.GFrame;
import game.Game;
import game.Renderable;
import game.util.Task;
import game.world.Location;

public class Inventory {

	private boolean isOpen;
	private ArrayList<Item> items;
	private BufferedImage itemsImage;

	public Inventory() {
		isOpen = false;
		items = new ArrayList<Item>();
		for (int i = 0; i < 20; i++)
			items.add(i, new Item());
		drawItemsImage();
		removeItem(13);
		removeItem(4);
	}
	
	private void drawItemsImage()
	{
		itemsImage = new BufferedImage(GFrame.WIDTH-40, GFrame.HEIGHT-40, BufferedImage.TYPE_INT_RGB);
		for (int i = 0; i < 20; i++) {
			// Do things here!
			int xo = 0;
			int yo = 0;
			int col = i % 5; // 0 1 2 3 4
			int row = i / 5; // 0 1 2 3
			if(items.get(i) == null)
				continue;
			itemsImage.getGraphics().drawImage(items.get(i).getIcon(), (int) (xo + (col * (GFrame.WIDTH - 40) / 5.0)),
					(int) (yo + (row * (GFrame.HEIGHT - 40) / 4.0)), (int) ((GFrame.WIDTH - 40) / 5.0),
					(int) ((GFrame.HEIGHT - 40) / 4.0), null);
		}
		itemsImage.getGraphics().dispose();
	}

	public void setOpen(boolean o) {
		this.isOpen = o;
	}

	public boolean isOpen() {
		return isOpen;
	}
	
	public void addItem(Item i)
	{
		if(items.size() > 20)
			return;
		items.add(i);
		drawItemsImage();
	}
	
	public void removeItem(Item i)
	{
		int size1 = items.size();
		items.remove(i);
		if(size1 != items.size())
			drawItemsImage();	
	}

	public void removeItem(int pos)
	{
		items.set(pos, null);
		drawItemsImage();
	}
	
	/*
	 * Fixed position
	 */
	public void render(Graphics g) {
		if(!isOpen)
			return;
		Color last = g.getColor();
		g.setColor(new Color(0, 0, 0, 127));
		g.fillRect(10, 0, GFrame.WIDTH - 20, GFrame.HEIGHT - 10);
		g.drawImage(itemsImage, 20, 10, null);
		g.setColor(Color.gray);
		g.drawRect(20, 10, GFrame.WIDTH - 40, GFrame.HEIGHT - 40);
		

		// Vertical lines
		for (int i = 0; i < 5; i++) {
			double xo = 20 + ((i + 1) * ((GFrame.WIDTH - 40) / 5.0));
			g.drawLine((int) xo, 10, (int) xo, GFrame.HEIGHT - 10);
		}
		// Horizontal lines
		for (int i = 0; i < 4; i++) {
			double yo = 10 + ((i + 1) * ((GFrame.HEIGHT - 40) / 4.0));
			g.drawLine(20, (int) yo, GFrame.WIDTH - 20, (int) yo);
		}
		g.setColor(last);
	}

}
