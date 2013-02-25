package snake.gui.impl;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;

import javax.swing.JPanel;

import snake.gui.SEngineScenePainter;

/**
 * Snake Scene Panel
 * 
 * This class is used to display the "scene" part of the game.
 * 
 * @author Roger H. Joerg et altera
 * @version $Revision: 1.3 $, $Date: 2003/10/13 18:05:13 $
 */
public class SEngineScenePanel extends JPanel {

	// --------------------------------------------------------------------------
	// Private member variables.

	/**
	 * Used only to shut up Eclipse
	 */
	private static final long serialVersionUID = 9002364539737370328L;
	/**
	 * The painter.
	 */
	private SEngineScenePainter painter;

	// --------------------------------------------------------------------------
	// Constructors.

	/**
	 * Default constructor.
	 * 
	 * Do not create an instance of this class by yourself. This class is used
	 * by the SnakePanel to display the "scene" part of the game.
	 */
	public SEngineScenePanel() {
		this.painter = null;

		// Set the background color to white
		this.setBackground(Color.WHITE);
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
	 *            the painter to set
	 */
	public void setPainter(SEngineScenePainter painter) {
		this.painter = painter;
		this.invalidate();
	}

	/**
	 * Returns the preferred size.
	 * 
	 * This method overrides the version of the superclass to make sure the
	 * content has the correct size.
	 * 
	 * @return the preferred size
	 */
	public Dimension getPreferredSize() {
		return new Dimension(SEngineSwingImpl.CX_SCENE, SEngineSwingImpl.CY_SCENE);
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

		this.painter.paintScene(g2);
	}
}
