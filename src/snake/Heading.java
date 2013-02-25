package snake;

/** 
 * Enum for the heading. 
 * the heading represents a direction in the game. 
 * We call the 4 Directions north, east, south and west, where north means in the direction to the top of the screen and so on.
 * @author macrozone
 *
 */
public enum Heading {
	NORTH(270),
	EAST(0),
	SOUTH(90),
	WEST(180);
	
	private int angle;
	
	/**
	 * Constructor, store the angle of the direction too. We define east as 0°.
	 * Having the angle too makes some calculations much easier.
	 * @param angle the Angle of the Heading in Degree
	 */
	Heading(int angle)
	{
		this.angle = angle;
	}
	
	/**
	 * Gets the Angle of one Direction
	 * @return the Angle of the Heading in Degree
	 */
	public int getAngle()
	{
		return this.angle;
	}
	
}
