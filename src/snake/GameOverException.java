package snake;

/**
 * Exception that gets thrown if the game is over
 * Carries the Score with it
 * @author macrozone
 *
 */
public class GameOverException extends Exception{

	private int score;
	/**
	 * Constructor, 
	 * @param score the Score the Player achieved
	 */
	public GameOverException(int score)
	{
		this.score = score;
	}
	
	/**
	 * get the score of the GameOverException
	 * @return the Score of the last Game
	 */
	public int getScore()
	{
		return this.score;
	}

	private static final long serialVersionUID = 3036267551982302761L;

}
