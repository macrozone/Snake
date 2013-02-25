package snake.gui;

/**
 * Menu Handler Interface.
 * 
 * @author Roger H. Joerg et altera
 * @version $Revision: 1.3 $, $Date: 2003/10/13 18:05:13 $
 */
public interface SEngineMenuHandler {
	/**
	 * This method is called when the user selects "Game -> New Game".
	 */
	public void onNewGame();

	/**
	 * This method is called when the user selects "Game -> Pause Game".
	 */
	public void onPauseGame();
	
	/**
	 * This method is called when the user selects "Game -> Load Map".
	 */
	public void onLoadMap();
	
	
}
