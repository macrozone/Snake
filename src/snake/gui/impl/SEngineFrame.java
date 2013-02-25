package snake.gui.impl;

import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;

import snake.gui.SEngineKeyHandler;
import snake.gui.SEngineMenuHandler;
import snake.gui.SEngineScenePainter;
import snake.gui.SEngineStatusPainter;

/**
 * Snake Window.
 * 
 * This window is the main window of the application. It is "fixed size" and
 * shows in the center of the screen.
 * 
 * @author Roger H. Joerg et altera
 * @version $Revision: 1.3 $, $Date: 2003/10/13 18:05:13 $
 */
public class SEngineFrame extends JFrame implements ActionListener {

	// --------------------------------------------------------------------------
	// Private member variables

	/**
	 * Used only to shut up Eclipse
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * The main content pane.
	 */
	protected SEngineMainPanel mainPanel;

	/**
	 * The menu handler.
	 */
	protected SEngineMenuHandler menuHandler;

	/**
	 * The key handler.
	 */
	protected SEngineKeyHandler keyHandler;

	// --------------------------------------------------------------------------
	// Constructors

	/**
	 * Default constructor.
	 * 
	 * Do not create an instance of SnakeWindow by yourself. This class is used
	 * by the "SnakeEngine".
	 */
	public SEngineFrame() {
		super("Snake");
		this.mainPanel = null;
		this.menuHandler = null;
		this.keyHandler = null;

		initGui();
	}

	// --------------------------------------------------------------------------
	// Methods

	/**
	 * Sets the menu handler
	 * 
	 * Do not call this method directly. It is called by the engine's method
	 * setMenuHandler.
	 * 
	 * @param menuHandler
	 *            the menu handler to set
	 */
	public void setMenuHandler(SEngineMenuHandler menuHandler) {
		this.menuHandler = menuHandler;
	}

	/**
	 * Sets the key handler
	 * 
	 * Do not call this method directly. It is called by the engine's method
	 * setKeyHandler.
	 * 
	 * @param keyHandler
	 *            the key handler to set
	 */
	public void setKeyHandler(SEngineKeyHandler keyHandler) {
		this.keyHandler = keyHandler;
	}

	/**
	 * Sets the "scene" painter.
	 * 
	 * Do not call this method directly. It is called by the engine's method
	 * setScenePainter.
	 * 
	 * @param painter
	 *            the painter to set
	 */
	public void setPainter(SEngineScenePainter painter) {
		this.mainPanel.setPainter(painter);
	}

	/**
	 * Sets the "status" painter.
	 * 
	 * Do not call this method directly. It is called by the engine's method
	 * setScenePainter.
	 * 
	 * @param painter
	 *            the painter to set
	 */
	public void setPainter(SEngineStatusPainter painter) {
		this.mainPanel.setPainter(painter);
	}

	// --------------------------------------------------------------------------
	// Private initialization methods.

	/**
	 * Initializes the GUI.
	 */
	protected void initGui() {
		this.initCloseOperation();
		this.initFrame();
		this.initMenuBar();
		this.initKeyboardEvents();
		this.initPanel();
		this.pack();
		this.initPosition();
	}

	/**
	 * Initializes the default close operation to "exit".
	 */
	protected void initCloseOperation() {
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}

	/**
	 * Initializes the frame to "non-resizable". The window can still be
	 * minimized.
	 */
	protected void initFrame() {
		this.setResizable(false);
	}

	/**
	 * Initializes the menu bar.
	 */
	protected void initMenuBar() {
		// Create the menu bar.
		JMenuBar mb = new JMenuBar();

		// Create and add the "game" menu.
		mb.add(this.createGameMenu());

		// Set the menu bar.
		this.setJMenuBar(mb);
	}

	/**
	 * Creates the "Game" menu.
	 */
	protected JMenu createGameMenu() {
		// Create the menu.
		JMenu mn = new JMenu("Game");
		mn.setMnemonic('G');

		// Add the "Game->New Game" menu item.
		mn.add(this.createLoadMapGameItem());
		mn.add(this.createGameNewGameItem());
		mn.add(this.createGamePauseGameItem());
		mn.add(new javax.swing.JSeparator());
		mn.add(this.createGameEndGameItem());

		return mn;
	}

	/**
	 * Creates the "Game->New Game" menu item.
	 */
	protected JMenuItem createGameNewGameItem() {
		return createMenuItem("New Game", 'N', SEngineSwingImpl.CMD_GAME_NEW_GAME, this);
	}

	/**
	 * Creates the "Game->Pause Game" menu item.
	 */
	protected JMenuItem createGamePauseGameItem() {
		return createMenuItem("Pause Game", 'P', SEngineSwingImpl.CMD_GAME_PAUSE_GAME, this);
	}
	/**
	 * Creates the "Game->LoadMap" menu item.
	 */
	protected JMenuItem createLoadMapGameItem() {
		return createMenuItem("Load Map", 'L', SEngineSwingImpl.CMD_GAME_LOAD_MAP, this);
	}


	/**
	 * Creates the "Quit" menu item.
	 */
	protected JMenuItem createGameEndGameItem() {
		return createMenuItem("Quit", 'Q', SEngineSwingImpl.CMD_GAME_END_GAME, this);
	}

	/**
	 * Create a new menu item.
	 * 
	 * @param caption
	 *            The text shown on the menu item.
	 * @param mnemonic
	 *            The character underlined in the menu item.
	 * @param actionCommand
	 *            The action command string associated with this menu item.
	 * @param actionListener
	 *            The action listener associated with this menu item.
	 */
	protected JMenuItem createMenuItem(String caption, char mnemonic, String actionCommand, ActionListener actionListener) {
		// Create the menu item
		JMenuItem mi = new JMenuItem(caption);

		// Set the mnemonic
		mi.setMnemonic(mnemonic);
		// does not work as expected
		// mi.setAccelerator(KeyStroke.getKeyStroke(mnemonic));

		// Set the action command string and action listener.
		mi.setActionCommand(actionCommand);
		mi.addActionListener(actionListener);

		return mi;
	}

	/**
	 * Initializes the handlers for the handled keyboard events.
	 */
	protected void initKeyboardEvents() {
		// Add the handler for the "key pressed" event.
		this.addKeyListener(new KeyAdapter() {
			public void keyPressed(KeyEvent e) {
				onKeyPressed(e);
			}
		});
	}

	/**
	 * Initializes the content pane
	 */
	protected void initPanel() {
		this.mainPanel = new SEngineMainPanel();

		this.setContentPane(this.mainPanel);
	}

	/**
	 * Initializes the position to "center screen"
	 */
	protected void initPosition() {
		Toolkit tk = Toolkit.getDefaultToolkit();
		Dimension dScreen = tk.getScreenSize();
		Dimension dFrame = this.getSize();

		int x = (int) ((dScreen.getWidth() - dFrame.getWidth()) / 2);
		int y = (int) ((dScreen.getHeight() - dFrame.getHeight()) / 2);

		this.setLocation(x, y);
	}

	// --------------------------------------------------------------------------
	// Event handlers

	/**
	 * Implementation for ActionListener.
	 */
	public void actionPerformed(ActionEvent e) {
		if (e.getActionCommand().compareTo(SEngineSwingImpl.CMD_GAME_NEW_GAME) == 0) {
			if (this.menuHandler != null) {
				this.menuHandler.onNewGame();
			}
		} else if (e.getActionCommand().compareTo(SEngineSwingImpl.CMD_GAME_PAUSE_GAME) == 0) {
			if (this.menuHandler != null) {
				this.menuHandler.onPauseGame();
			}
		} else if (e.getActionCommand().compareTo(SEngineSwingImpl.CMD_GAME_LOAD_MAP) == 0) {
			if (this.menuHandler != null) {
				this.menuHandler.onLoadMap();
			}
		} else if (e.getActionCommand().compareTo(SEngineSwingImpl.CMD_GAME_END_GAME) == 0) {
			System.exit(0); // und tschüss ...
		}
	}

	/**
	 * Implementation for key events.
	 */
	public void onKeyPressed(KeyEvent e) {

		if (this.menuHandler != null && e.getKeyCode() == KeyEvent.VK_N) {
			this.menuHandler.onNewGame();
		}

		if (this.keyHandler == null)
			return;

		switch (e.getKeyCode()) {
		case KeyEvent.VK_LEFT:
		case KeyEvent.VK_COMMA:
		case KeyEvent.VK_J:
			this.keyHandler.onWest();
			break;

		case KeyEvent.VK_RIGHT:
		case KeyEvent.VK_PERIOD:
		case KeyEvent.VK_L:
			this.keyHandler.onEast();
			break;

		case KeyEvent.VK_P:
			this.keyHandler.onPause();
			break;

		case KeyEvent.VK_UP:
		case KeyEvent.VK_I:
			this.keyHandler.onNorth();
			break;

		case KeyEvent.VK_DOWN:
		case KeyEvent.VK_K:
			this.keyHandler.onSouth();
			break;

		}
	}
}
