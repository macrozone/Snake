package snake;

import java.awt.Graphics2D;
import java.awt.geom.AffineTransform;
import java.util.LinkedList;
import java.util.Queue;

/**
 * Class for the Snake itself
 * @author macrozone
 *
 */
public class Snake 
{
	private Point headPosition;
	private Queue<SnakeLimb> body;
	private Heading heading;
	private Heading lastHeading;
	private boolean graphicIntervall = true;
	
	private int sizeToGrow;
	
	/**
	 * Makes a new snake
	 * @param headPosition the position of the head of the snake
	 * @param length the length of the snake incl. the head
	 * @param heading the direction the snake is looking
	 */
	public Snake(Point headPosition, int length, Heading heading)
	{
		// int values
		this.sizeToGrow = 0;
		this.heading = heading;
		this.lastHeading = heading;
		// make head
		this.headPosition = headPosition;
		// make body 
		Point p = null;
		this.body = new LinkedList<SnakeLimb>();
		
		for (int i = length-1;i>0; i--)
		{
			switch (heading)
			{
				case NORTH: 
					p = new Point(headPosition.x, headPosition.y+i);
					break;

				case EAST: 
					p = new Point(headPosition.x-i, headPosition.y);
					break;

				case SOUTH: 
					p = new Point(headPosition.x, headPosition.y-i);
					break;

				case WEST: 
					p = new Point(headPosition.x+i, headPosition.y);
					break;
				default:
				break;		
					
			}
			// add the new SnakeLimb to the snakebody
			this.body.add(new SnakeLimb(p, heading, Form.STRAIGHT ));
		}
		// last item will be the tail
		this.body.element().makeTail();
		
	}
	/**
	 * Gives the next position the snake is going to
	 * 
	 * @return the position
	 */
	public Point move()
	{
		Point newPosition = this.headPosition.getNearbyPoint(this.heading);
		return newPosition;
	}
	
	/**
	 * Moves the snake to the given position
	 * @param newPosition
	 */
	public void moveTo(Point newPosition)
	{
		Form form = null;
		// first, we make a new limb
		// we decide wich form this limb has to have
		if ((this.heading.getAngle() - this.lastHeading.getAngle() == 90 || this.heading.getAngle() - this.lastHeading.getAngle() == -270)) 
			form = Form.BEND_RIGHT;
		else if (this.heading.getAngle() - this.lastHeading.getAngle() == -90 || this.heading.getAngle() - this.lastHeading.getAngle() == 270)
			form = Form.BEND_LEFT;
		else
			form = Form.STRAIGHT;
		
		// add a new limb with this form to the snake body
		this.body.add(new SnakeLimb(this.headPosition, this.heading, form));
		// move the head
		this.headPosition = newPosition;
		// set the lastHeading to the actual heading
		this.lastHeading = this.heading;
		
		// check if the snake has eaten
		if (this.sizeToGrow == 0)
			this.body.remove();	// the snake will not grow, because it's "stomach" is empty. We remove the last limb
		else
			this.sizeToGrow--;	// decrease the "food in the stomache" of the snake
		// make the last limb the tail
		this.body.element().makeTail();
		// toggle the intervall
		this.graphicIntervall = !this.graphicIntervall;
	}
	
	/**
	 * Check if the body of the snake is at the given position
	 * head will not be checked!
	 * @param position the position to check
	 * @return true if the snake is at this position, false if not
	 */
	public boolean isAt(Point position)
	{
		boolean crashed = false;
		
		for (SnakeLimb oneLimb : this.body)
		{
			crashed = oneLimb.isAt(position);
			if (crashed) break;
		}
		return crashed;
	}
	
	/**
	 * Eats the food and fills "the stomach" of the snake (sizeToGrow)
	 * and gives back the nutritiveValue of the food
	 * waring: the food will not be removed here from the gameField
	 * @param food the food of the snake
	 * @return the nutritive Value of the food
	 */
	public int eat(Food food)
	{
		this.sizeToGrow += food.getNutritiveValue();
		return food.getNutritiveValue();
	}
	
	/** 
	 * Sets the heading of the snake
	 * checks if this heading is legal
	 * @param heading
	 */
	public void setHeading(Heading heading)
	{
		// be carefull that the snake doesn't do a U-Turn
		// we compare the last heading (=the heading of the last limb) with he new heading
		switch(this.lastHeading)
		{
			case NORTH: 
				if (heading != Heading.SOUTH)
					this.heading = heading;
				break;
			case EAST: 
				if (heading != Heading.WEST)
					this.heading = heading;
				break;
			case SOUTH: 
				if (heading != Heading.NORTH)
					this.heading = heading;
				break;
			case WEST: 
				if (heading != Heading.EAST)
					this.heading = heading;
				break;
		}
		
	}

	/** 
	 * Paints the snake and it's limbs on the given Graphics2D g
	 * @param g the Graphics2D on which the snake is to be painted
	 */
	public void paint(Graphics2D g) {
		int sf = GraphicSettings.SCALE_FACTOR;
		
		AffineTransform trans = new AffineTransform(); 
		trans.translate(this.headPosition.x*sf, this.headPosition.y*sf);
		trans.rotate(Math.toRadians(this.lastHeading.getAngle()),sf/2,sf/2);
		if (this.graphicIntervall)
			g.drawImage(Sprites.SNAKE_HEAD_1.getImage(sf,sf),trans, null);
		else
			g.drawImage(Sprites.SNAKE_HEAD_2.getImage(sf,sf),trans, null);
		
		for (SnakeLimb oneLimb : this.body)
			oneLimb.paint(g, this.graphicIntervall);
		
	}


}
 