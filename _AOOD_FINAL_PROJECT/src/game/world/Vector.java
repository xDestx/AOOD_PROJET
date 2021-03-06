package game.world;

public class Vector {

	private double x, y;

	public Vector(double x, double y) {
		this.x = x;
		this.y = y;
	}
	
	public Vector(double radians, int speed)
	{
		this.x = speed * Math.sin(radians);
		this.y = speed * Math.cos(radians);
	}

	public void setX(double x) {
		this.x = x;
	}

	public void setY(double y) {
		this.y = y;
	}

	public double getX() {
		return this.x;
	}

	public double getY() {
		return this.y;
	}
	
	public void set(double d)
	{
		this.x = d;
		this.y = d;
	}

}
