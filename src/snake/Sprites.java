package snake;


import java.awt.Image;

import java.io.File;
import java.io.IOException;
import java.net.URL;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;

/**
 * Enum for the sprites available
 * each sprite has a path to the representing Image-File
 * @author macrozone
 *
 */
public enum Sprites
{
	MUSHROOM("mushroom.png"), 
	SNAKE_HEAD_1("snakeheadclose.png"), 
	SNAKE_HEAD_2("snakeheadopen.png"), 
	SNAKE_STRAIGHT("body.png"), 
	SNAKE_BEND_LEFT("kurvelinks.png"), 
	SNAKE_BEND_RIGHT("kurverechts.png"),
	SNAKE_TAIL_1("snaketail1.png"),
	SNAKE_TAIL_2("snaketail2.png");
	;

	// the image of one Sprite
	private final Image theImage;
	private static final String resourcePath = "rsc/";

	/**
	 * constructor
	 * @param path Path of the Image
	 */
	Sprites(String path)
	{
		
		
		Image img = null;
		// load graphic
		try
		{
			
			img = new ImageIcon(ImageIO.read(this.getClass().getResource(resourcePath+path))).getImage();
		}
		catch (IOException e)
		{
			e.printStackTrace();
		}
		
		this.theImage = img;
	}

	/**
	 * Gets the Image of one Element
	 * @return the Image of the Element
	 */
	public Image getImage()
	{
		return this.theImage;
	}
	
	/**
	 * Gets the Image of one Element scaled to the given width and height
	 * @param width Scale the Image to this Width
	 * @param height Scale the Image to this Height
	 * @return the Image of the Element
	 */
	public Image getImage(int width, int height)
	{	
		return this.theImage.getScaledInstance(width, height,Image.SCALE_SMOOTH);
	
	}
	

	
}
