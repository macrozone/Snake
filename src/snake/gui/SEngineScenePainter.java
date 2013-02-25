package snake.gui;

import java.awt.Graphics2D;

/**
 * Snake Scene Painter interface.
 * 
 * The method of this interface is called to paint the "scene".
 * 
 * @author Roger H. Joerg et altera
 * @version $Revision: 1.3 $, $Date: 2003/10/13 18:05:14 $
 */
public interface SEngineScenePainter {
	/**
	 * This method is called when the scene needs to be painted.
	 * 
	 * @param g
	 *            the graphics context to paint into
	 */
	public void paintScene(Graphics2D g);
}
