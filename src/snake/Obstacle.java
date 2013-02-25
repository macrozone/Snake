package snake;

import java.awt.Color;
import java.awt.Graphics2D;

/**
 * class for an obstacle
 * @author macrozone
 *
 */
public class Obstacle {

	// location of the obstacle
	private Point position;
	
	/** 
	 * Constructor, makes a new obstacle at the given position
	 * @param position the position where to make an obstacle
	 */
	public Obstacle(Point position)
	{
		this.position = position;
		
	}
	
	
	/**
	 * checks if this obstacle is at a given position
	 * @param position the position to check
	 * @return true if the obstacle is at the given position, false otherwise
	 */
	public boolean isAt(Point position)
	{
		return this.position.equals(position);	
	}

	/**
	 * paint the obstacle on the given Graphics2D-Object g
	 * @param g the Graphics2D-Object on which to draw on
	 */
	public void paint(Graphics2D g) {
		int sf = GraphicSettings.SCALE_FACTOR;
		g.setColor(new Color(90, 90, 90));
		g.fill3DRect(this.position.x*sf, this.position.y*sf, sf, sf,true);
		
	}
}
