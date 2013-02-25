package snake.gui;

import java.awt.Graphics2D;

/**
 * Snake Status Painter interface.
 * 
 * The method of this interface is called to paint the "status".
 * 
 * @author Roger H. Joerg et altera
 * @version $Revision: 1.3 $, $Date: 2003/10/13 18:05:13 $
 */
public interface SEngineStatusPainter {
	/**
	 * This method is called when the status pane needs to be painted.
	 * 
	 * @param g
	 *            the graphics context to paint into
	 */
	public void paintStatus(Graphics2D g);
}
