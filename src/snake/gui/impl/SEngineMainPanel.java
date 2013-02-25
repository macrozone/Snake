package snake.gui.impl;

import javax.swing.JPanel;

import snake.gui.SEngineScenePainter;
import snake.gui.SEngineStatusPainter;

/**
 * Snake Panel.
 * 
 * This is the main content panel of the main window. It has two sub panels: a
 * scene panel on the left side and a status panel on the right side.
 * 
 * @author Roger H. Joerg et altera
 * @version $Revision: 1.3 $, $Date: 2003/10/13 18:05:14 $
 */
public class SEngineMainPanel extends JPanel {

	// --------------------------------------------------------------------------
	// Private member variables

	/**
	 * Used only to shut up Eclipse
	 */
	private static final long serialVersionUID = -8968210213859945273L;

	/**
	 * The scene panel (left side).
	 */
	protected SEngineScenePanel scenePanel;

	/**
	 * The status panel (right side).
	 */
	protected SEngineStatusPanel statusPanel;

	// --------------------------------------------------------------------------
	// Constructors.

	/**
	 * Default constructor.
	 * 
	 * Do not create an instance of this class by yourself. This class is used
	 * by the SnakeWindow class to display its main content.
	 */
	public SEngineMainPanel() {
		// Create the scene panel.
		this.scenePanel = new SEngineScenePanel();
		this.add(this.scenePanel);

		// Create the status panel.
		this.statusPanel = new SEngineStatusPanel();
		this.add(this.statusPanel);
	}

	// --------------------------------------------------------------------------
	// Methods

	/**
	 * Sets the scene painter.
	 * 
	 * Do not call this method directly. It is called by the engine's method
	 * setScenePainter
	 */
	public void setPainter(SEngineScenePainter painter) {
		this.scenePanel.setPainter(painter);
	}

	/**
	 * Sets the status painter.
	 * 
	 * Do not call this method directly. It is called by the engine's method
	 * setScenePainter
	 */
	public void setPainter(SEngineStatusPainter painter) {
		this.statusPanel.setPainter(painter);
	}
}
