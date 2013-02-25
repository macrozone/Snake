package snake.gui;

import snake.gui.impl.SEngineSwingImpl;

/**
 * Factory Class
 * 
 * Erstellt passende Snake Engines
 * 
 * @author M. Werner
 * @version 1.00
 * 
 */
public class SEngineFactory {
	/**
	 * @return a swing engine
	 */
	public static SEngine makeSwingEngine() {
		return new SEngineSwingImpl();
	}

	/**
	 * @return an applet engine
	 */
	public static SEngine makeAppletEngine() {
		return null; // 2do
	}

	/**
	 * @return an Handy engine
	 */
	public static SEngine makeHandyEngine() {
		return null; // 2do
	}

	public static SEngine makeEngine(String engine) {
		try {
			Class<?> c = Class.forName(engine);
			return (SEngine)c.newInstance();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
}
