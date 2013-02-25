package snake;



import java.awt.Graphics2D;

import java.awt.geom.AffineTransform;


/**
 * Class for Food
 * A food is repesented by a Mushroom.
 * It has a nutritive value. 
 * The bigger the nutritive Value, the more points it gives, the more grows the snake and the bigger the sprite is drawn
 * 
 * @author macrozone
 *	
 */
public class Food {

//	private static Image THE_ICON = 
	private Point position;
	private int nutritiveValue;
	
	/**
	 * Constructor, makes a Food on the given position with the given nutritiveValue
	 * @param position The Position of the new Food
	 * @param nutritiveValue The Nutritive Value of the food
	 */
	public Food(Point position, int nutritiveValue )
	{
		this.position = position;
		this.nutritiveValue = nutritiveValue;
	}
	
	/**
	 * checks if the food is at the given Position
	 * @param position the Position to check
	 * @return True if the Food is at the given Position
	 */
	public boolean isAt(Point position)
	{
		return this.position.equals(position);
	}
	
	/**
	 * gets the Nutritive value
	 * @return the Nutritive-value of the Food
	 */
	public int getNutritiveValue()
	{
		return this.nutritiveValue;
	}
	
	/**
	 * paints the graphic on the given Graphics2D
	 * @param g the Graphic to paint on
	 */
	public void paint(Graphics2D g) {
		
		// the scaleFactor given in the global-settings
		int sf = GraphicSettings.SCALE_FACTOR;
		
		// calc the size of the Image
		double size = 0.25+((double)this.nutritiveValue)/5;
		
		// define transformation
		AffineTransform trans = new AffineTransform(); 
		trans.translate(this.position.x*sf-(size-1), this.position.y*sf-(size-1));
		// draw the picture
		g.drawImage(Sprites.MUSHROOM.getImage((int)Math.round(sf*size),(int)Math.round(sf*size)),trans, null);

	}
}
