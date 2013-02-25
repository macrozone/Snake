package snake;


/**
 * class for a Point in a 2-dimensional space
 * @author macrozone
 *
 */
public class Point 
{
	public final int x;
	public final int y;
	
	/**
	 * Constructor, needs the x- and y-coordinate
	 * @param x
	 * @param y
	 */
	public Point(int x, int y)
	{
		this.x = x;
		this.y = y;
		
	}
	
	/** 
	 * checks if two points are equal (means x and y are the same and the object is also a point)
	 * @param p another object.
	 * @return true, if the param is a point and has the same x and y coords as this, false otherwise
	 */
	@Override
	public boolean equals(Object p)
	{
		if (p instanceof Point)
			return this.x ==((Point)p).x && this.y == ((Point)p).y;
		else 
			return false;
	}
	
	/**
	 * gets the next nearby point in the direction of the heading
	 * @param heading in which direction we need another point
	 * @return the point that is next to this (in the direction of heading)
	 */
	public Point getNearbyPoint(Heading heading)
	{
		Point p = null;
		switch (heading)
		{
			case NORTH: 
				p = new Point(this.x, this.y-1);
				break;

			case EAST: 
				p = new Point(this.x+1, this.y);
				break;

			case SOUTH: 
				p = new Point(this.x, this.y+1);
				break;

			case WEST: 
				p = new Point(this.x-1, this.y);
				break;
				
		}
		
		return p;
	
	}
	
	
}
