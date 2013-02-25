package snake.gui.impl;

import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;

import javax.swing.JPanel;

import snake.gui.SEngineStatusPainter;

/**
 * Snake Status Panel.
 * 
 * This class is used to display the "status" of the game. That is "level",
 * "points", "remaining lives" etc.
 * 
 * @author Roger H. Joerg et altera
 * @version $Revision: 1.3 $, $Date: 2003/10/13 18:05:13 $
 */
public class SEngineStatusPanel extends JPanel {

	// --------------------------------------------------------------------------
	// Private member variables.

	/**
	 * Used only to shut up Eclipse
	 */
	private static final long serialVersionUID = 8978361937033234025L;
	/**
	 * The painter.
	 */
	protected SEngineStatusPainter painter;

	// --------------------------------------------------------------------------
	// Constructors.

	/**
	 * Default constructor.
	 * 
	 * Do not create an instance of this class by yourself. This class is used
	 * by the SnakePanel to display the "status" part of the game.
	 */
	public SEngineStatusPanel() {
		this.painter = null;
	}

	// --------------------------------------------------------------------------
	// Methods

	/**
	 * Sets the painter.
	 * 
	 * Do not call this method directly. It is called by the engine's method
	 * setScenePainter.
	 * 
	 * @param painter
	 *            the painter to set.
	 */
	public void setPainter(SEngineStatusPainter painter) {
		this.painter = painter;
		this.invalidate();
	}

	/**
	 * Returns the preferred size. This method overrides the version of the
	 * superclass to make sure the content has the correct size.
	 * 
	 * @return the preferred size
	 */
	public Dimension getPreferredSize() {
		return new Dimension(SEngineSwingImpl.CX_STATUS, SEngineSwingImpl.CY_STATUS);
	}

	/**
	 * Draw the contents.
	 * 
	 * This method overrides the version of the superclass to make sure the
	 * correct content is drawn.
	 * 
	 * @param g
	 *            the graphics context to paint into
	 */
	public void paint(Graphics g) {
		super.paint(g);

		if (this.painter == null)
			return;

		Graphics2D g2 = (Graphics2D) g;

		this.painter.paintStatus(g2);
	}
}
