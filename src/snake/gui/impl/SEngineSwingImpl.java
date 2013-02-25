package snake.gui.impl;

import snake.gui.*;

public class SEngineSwingImpl implements SEngine {

	// --------------------------------------------------------------------------
	// Public constants

	/**
	 * With of the scene.
	 */
	final static int CX_SCENE = 320;

	/**
	 * Height of the scene.
	 */
	final static int CY_SCENE = 320;

	/**
	 * With of the status panel.
	 */
	final static int CX_STATUS = 120;

	/**
	 * Height of the status panel.
	 */
	final static int CY_STATUS = CY_SCENE;

	/**
	 * Command string for menu item "Game->New Game"
	 */
	final static String CMD_GAME_NEW_GAME = "Game_NewGame";

	/**
	 * Command string for menu item "Game->Pause Game"
	 */
	final static String CMD_GAME_PAUSE_GAME = "Game_PauseGame";

	/**
	 * Command string for menu item "Game->Quit"
	 */
	final static String CMD_GAME_END_GAME = "Game_ExitGame";

	/**
	 * Command string for menu item "Game->Load Map"
	 */
	final static String CMD_GAME_LOAD_MAP = "Game_LoadMap";

	// --------------------------------------------------------------------------
	// Private member variables

	/**
	 * The main window.
	 */
	private SEngineFrame frame;

	/**
	 * The timer.
	 */
	private SEngineTimer timer;

	// --------------------------------------------------------------------------
	// Constructors

	/**
	 * Default constructor. Initializes the Snake Game Engine.
	 */
	public SEngineSwingImpl() {
		this.frame = new SEngineFrame();
		this.timer = new SEngineTimer();
	}

	/**
	 * @see SEngine.setMenuHandler
	 */
	@Override
	public void setMenuHandler(SEngineMenuHandler menuHandler) {
		this.frame.setMenuHandler(menuHandler);
	}

	/**
	 * @see SEngine.setKeyHandler
	 */
	@Override
	public void setKeyHandler(SEngineKeyHandler keyHandler) {
		this.frame.setKeyHandler(keyHandler);
	}

	/**
	 * @see SEngine.setTimerHandler
	 */
	@Override
	public void setTimerHandler(SEngineTimerHandler timerHandler) {
		this.timer.setTimerHandler(timerHandler);
	}

	/**
	 * @see SEngine.setTimer
	 */
	@Override
	public void setTimerIntervall(int delay) {
		this.timer.setTimer(delay);
	}

	/**
	 * @see SEngine.setScenePainter
	 */
	@Override
	public void setScenePainter(SEngineScenePainter painter) {
		this.frame.setPainter(painter);
	}

	/**
	 * @see SEngine.setStatusPainter
	 */
	@Override
	public void setStatusPainter(SEngineStatusPainter painter) {
		this.frame.setPainter(painter);
	}

	/**
	 * @see SEngine.show
	 */
	@Override
	public void show() {
		this.frame.setVisible(true);
	}

	/**
	 * @see SEngine.invalidate
	 */
	@Override
	public void invalidate() {
		this.frame.getContentPane().repaint();
	}

	@Override
	public int getScenePixelHeight() {
		return CY_SCENE;
	}

	@Override
	public int getScenePixelWidth() {
		return CX_SCENE;
	}

	@Override
	public int getStatusPixelHeight() {
		return CY_STATUS;
	}

	@Override
	public int getStatusPixelWidth() {
		return CX_STATUS;
	}
}
