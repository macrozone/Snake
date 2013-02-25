package snake.gui;

/**
 * Snake Engine
 * 
 * This class is the "helper engine" of the game. Its public constants and
 * methods can be used by the "main engine".
 * 
 * @author Roger H. Joerg et altera
 * @version $Revision: 1.3 $, $Date: 2003/10/13 18:05:13 $
 */
public interface SEngine {

	/**
	 * With of the scene.
	 */
	public int getScenePixelWidth();

	/**
	 * Height of the scene.
	 */
	public int getScenePixelHeight();

	/**
	 * With of the status panel.
	 */
	public int getStatusPixelWidth();

	/**
	 * Height of the status panel.
	 */
	public int getStatusPixelHeight();


	// --------------------------------------------------------------------------
	// Initialization methods

	/**
	 * Sets the menu handler
	 * 
	 * Create a class which implements the interface SEngineMenuHandler. Create
	 * an instance of it and call this method with that instance as a parameter.
	 * Your class will be called to handle the menu selections.
	 * 
	 * @param menuHandler
	 *            the menu handler to set
	 */
	public void setMenuHandler(SEngineMenuHandler menuHandler);

	/**
	 * Sets the "keyboard" handler.
	 * 
	 * Create a class which implements the interface SEngineKeyHandler. Create
	 * an instance of it and call this method with that instance as a parameter.
	 * Your instance will be called if the user presses the ',' (comma) or left
	 * arrow key (to turn the snake to the left) or if the user presses the '.'
	 * (dot) or right arrow key (to turn the snake to the right).
	 * 
	 * @param keyHandler
	 *            the key handler to set
	 */
	public void setKeyHandler(SEngineKeyHandler keyHandler);

	/**
	 * Sets the "timer" handler.
	 * 
	 * Create a class which implements the interface SEngineTimerHandler. Create
	 * an instance of it and call this method with that instance as a parameter.
	 * Your instance will be called periodically to handle your event if the
	 * timer has elapsed its period.
	 * 
	 * @param timerHandler
	 *            the timer handler to set
	 */
	public void setTimerHandler(SEngineTimerHandler timerHandler);

	/**
	 * Sets the delay between timer events.
	 * 
	 * @param delay
	 *            Delay between timer events in milliseconds. Use zero or a
	 *            negative value to stop the timer.
	 */
	public void setTimerIntervall(int delay);

	/**
	 * Sets the "scene" painter.
	 * 
	 * Create a class which implements the interface SEngineScenePainter. Create
	 * an instance of it and call this method with that instance as a parameter.
	 * Your class will be called to paint the contents of the "scene".
	 * 
	 * @param painter
	 *            the painter to set
	 */
	public void setScenePainter(SEngineScenePainter painter);

	/**
	 * Sets the "status" painter.
	 * 
	 * Create a class which implements the interface SEngineStatusPainter.
	 * Create an instance of it and call this method with that instance as a
	 * parameter. Your class will be called to paint the contents of the
	 * "scene".
	 * 
	 * @param painter
	 *            the painter to set
	 */
	public void setStatusPainter(SEngineStatusPainter painter);

	// --------------------------------------------------------------------------
	// Public methods

	/**
	 * Shows the main window of the Snake Game Engine centered on the screen.
	 */
	public void show();

	/**
	 * Invalidates the window, such that it will repaint all contents.
	 */
	public void invalidate();
}
