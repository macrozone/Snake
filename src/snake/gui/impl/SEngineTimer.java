package snake.gui.impl;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.Timer;

import snake.gui.SEngineTimerHandler;

/**
 * Snake Timer
 * 
 * @author Roger H. Joerg et altera
 * @version $Revision: 1.3 $, $Date: 2003/10/13 18:05:14 $
 */
public class SEngineTimer implements ActionListener {

	// --------------------------------------------------------------------------
	// Attributes

	/**
	 * The Swing timer.
	 */
	protected Timer timer;

	/**
	 * The timer handler
	 */
	protected SEngineTimerHandler timerHandler;

	// --------------------------------------------------------------------------
	// Constructors

	/**
	 * Default constructor.
	 */

	public SEngineTimer() {
		this.timer = new Timer(0, this);
		this.timerHandler = null;
	}

	// --------------------------------------------------------------------------
	// Methods

	/**
	 * Sets the delay between timer events.
	 * 
	 * @param delay
	 *            Delay between timer events in milliseconds. Use zero or a
	 *            negative value to stop the timer. Don't call this method
	 *            directly. It is used by the SEngine.
	 */
	public void setTimer(int delay) {
		// Stop the timer if it is currenly running

		if (this.timer.isRunning()) {
			this.timer.stop();
		}

		// Done if given delay is zero or negative.

		if (delay <= 0) {
			return;
		}

		// Set the new delay and start the timer again.

		this.timer.setDelay(delay);
		this.timer.setInitialDelay(delay);
		this.timer.start();
	}

	/**
	 * Sets the timer handler. Don't call this method directly. It is used by
	 * the SEngine.
	 * 
	 * @param timerHandler
	 *            the handler to set
	 */
	public void setTimerHandler(SEngineTimerHandler timerHandler) {
		this.timerHandler = timerHandler;
	}

	// --------------------------------------------------------------------------
	// ActionListener implementation

	/**
	 * Handles a timer event.
	 */
	public void actionPerformed(ActionEvent e) {
		if (this.timerHandler == null)
			return;

		this.timerHandler.onTimer();
	}

}
