package snake;

/**
 * class for an Exception which is thrown when a map.txt-file is somehow missbuilt.
 * @author macrozone
 *
 */
public class MapMissbuiltException extends Exception{

	/**
	 * constructor
	 * @param string should be the reason why the map is missbuilt
	 */
	public MapMissbuiltException(String string) {
		super(string);
	}

	/**
	 * 
	 */
	private static final long serialVersionUID = -8303715645291258970L;

}
