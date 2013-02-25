package snake.gui;

/**
 * Keyboard Handler interface.
 * 
 * @author Roger H. Joerg et altera
 * @version $Revision: 1.3 $, $Date: 2003/10/13 18:05:14 $
 */
public interface SEngineKeyHandler {
	/**
	 * This method is called when the user wants to pause the game.
	 */
	public void onPause();

	/**
	 * This method is called when the user wants to turn north.
	 */
	public void onNorth();

	/**
	 * This method is called when the user wants to go to the east.
	 */
	public void onEast();

	/**
	 * This method is called when the user wants to go to the south.
	 */
	public void onSouth();

	/**
	 * This method is called when the user wants to go to the west.
	 */
	public void onWest();

}
