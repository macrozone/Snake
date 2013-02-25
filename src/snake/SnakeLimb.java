package snake;

import java.awt.Graphics2D;

import java.awt.geom.AffineTransform;

/**
 * Class for one GameLimb
 * @author macrozone
 *
 */
public class SnakeLimb
{
	// Position of the limb
	private Point position;
	
	// the direction it looks
	private Heading heading;
	
	// the form of the limb
	private Form form;
	
	// set it true, if the limb is the last one
	private boolean isTail;
	
	/**
	 * Constructor
	 * @param position 
	 * @param heading
	 * @param form
	 */
	public SnakeLimb(Point position, Heading heading, Form form)
	{
		this.position = position;
		this.heading = heading;
		this.form = form;
		this.isTail = false;
	}

	/**
	 * Check if the Snake limb is at the given Position
	 * @param position
	 * @return true, if the Snake-Limb is at the Given Position
	 */
	public boolean isAt(Point position)
	{
		return this.position.equals(position);
	}

	/**
	 * make the Limb to a Tail
	 */
	public void makeTail()
	{
		this.isTail = true;
	}




	/**
	 * Paints the snake limb
	 * @param g the Graphics2D on which the snake should be painted
	 * @param graphicIntervall enables the used graphics to toggle between to graphics, true meens one graphic, false the other
	 */
	public void paint(Graphics2D g,boolean graphicIntervall)
	{
	
		int sf = GraphicSettings.SCALE_FACTOR;
		// init a transform for the graphic
		AffineTransform trans = new AffineTransform(); 
		// translate it
		trans.translate(this.position.x*sf, this.position.y*sf);
		// rotate it
		trans.rotate(Math.toRadians(this.heading.getAngle()),sf/2,sf/2);
		
		// decide which graphic to draw
		if (this.isTail)
		{
			if (graphicIntervall)
				g.drawImage(Sprites.SNAKE_TAIL_1.getImage(sf,sf),trans, null);
			else
				g.drawImage(Sprites.SNAKE_TAIL_2.getImage(sf,sf),trans, null);
		}
		else
		{
			switch (this.form)
			{
			case STRAIGHT:
				g.drawImage(Sprites.SNAKE_STRAIGHT.getImage(sf,sf),trans, null);
				break;
			case BEND_LEFT:
				g.drawImage(Sprites.SNAKE_BEND_LEFT.getImage(sf,sf),trans, null);
				break;
			case BEND_RIGHT:
				g.drawImage(Sprites.SNAKE_BEND_RIGHT.getImage(sf,sf),trans, null);
				break;
			}

		}
		
	}

}
