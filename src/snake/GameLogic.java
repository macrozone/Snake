package snake;

import java.awt.Graphics2D;

import java.util.ArrayList;
import java.util.Random;

import snake.gui.SEngineScenePainter;
import snake.gui.SEngineStatusPainter;

/**
 * Class that is controlling one running game,
 * it implements the Scene-Painter and the StatusPainter
 * @author macrozone
 *
 */
public class GameLogic implements SEngineScenePainter, SEngineStatusPainter
{
	// the map loaded with it's obstacles
	private Map theMap;
	
	// the food on the map
	private ArrayList <Food> foods;
	
	// the snake 
	private Snake snake;
	
	// current score
	private int score;
	
	// current highscore
	private int highScore;

	private String statusMessage = "";
	
	
	/**
	 * Initializes a new Game with the given Map
	 * @param theMap : the Map to load
	 */
	public GameLogic(Map theMap) {	
		this.setMap(theMap);
	
	}
	
	/**
	 * Sets the Map to the given Map
	 * @param theMap the new Map to play on
	 */
	public void setMap(Map theMap)
	{
		this.theMap = theMap;
	}
	/**
	 * Sets the Highscore to show on the status-frame
	 * it's needed because we do the loading of the highscore in the gameApplication-class, not in this class here
	 * @param theScore the score that is to be shown as the highscore
	 */
	public void setHighScore(int theScore)
	{
		this.highScore = theScore;
	}
	
	/**
	 * Gets the Highscore
	 * @return the Highscore of the Game
	 */
	public int getHighScore()
	{
		return this.highScore;
	}

	/**
	 * Initializes a new Game with the same map, resets food, snake and score
	 */
	public void newGame()
	{
		this.statusMessage = "";
		this.foods = new ArrayList<Food>();
		this.placeSnake();
		this.placeRandomFood();
		this.score = 0;
	}
	
	/**
	 * Set the heading of the snake
	 * @param heading the New Heading
	 */
	public void setSnakeHeading(Heading heading) 
	{
		this.snake.setHeading(heading);
	}
	
	/**
	 * Do a turn
	 * @throws GameOverException : is thrown when the game is over
	 */
	public void doOneTurn() throws GameOverException 
	{
		this.moveSnake();
	}
	
	/**
	 * check if there is an obstacle at the given position
	 * @param position : the position
	 * @return true or false
	 */
	private boolean checkObstacles(Point position) {
		boolean crashed = false;
		
		for (Obstacle oneObstacle : this.theMap.getObstacles())
		{
			crashed = oneObstacle.isAt(position);
			if (crashed) break;
		}
		return crashed;
	}
	/**
	 * check if there is a Food at the given Position
	 * gives back the instance of the food found
	 * @param position The Position to check
	 * @return the Food found or null if no food is found at this position
	 */
	private Food checkFood(Point position) {
		Food foodFound = null;
		
		for (Food oneFood : this.foods)
		{
			if (oneFood.isAt(position))
			{
				foodFound = oneFood;
				break;
			}
		
		}
		return foodFound;
	}
	/**
	 * Checks if one part of the snake is at the given position
	 * @param position the Position to check
	 * @return true, if the Snake is at the given Position
	 */
	private boolean checkSnake(Point position) {
		
		return this.snake.isAt(position);
	}
	
	/**
	 * moves the snake at the given position 
	 * and handles all Events that can occur, 
	 * like find food, crash into obstacle or into itself
	 * @throws GameOverException : is thrown if the snake crashs
	 */
	private void moveSnake() throws GameOverException
	{
		Point newPosition = null;
		newPosition = this.snake.move();
		
		// check for "torus-warp"
		if (newPosition.x > this.theMap.getWidth()-1)
			newPosition = new Point(0, newPosition.y);
		else if (newPosition.x < 0)
			newPosition = new Point(this.theMap.getWidth()-1, newPosition.y);
		else if (newPosition.y > this.theMap.getHeight()-1)
			newPosition = new Point(newPosition.x, 0);
		else if (newPosition.y < 0)
			newPosition = new Point(newPosition.x,this.theMap.getHeight()-1);
		
		// check for food
		Food foodFound = checkFood(newPosition);
		if (foodFound!= null)
		{
			// set score, feed snake and place a new food on the map
			this.score += this.snake.eat(foodFound);
			if (this.score > this.highScore)
				this.onNewHighscore();
			
			this.foods.remove(foodFound);
			this.placeRandomFood();
		}
		else if (checkObstacles(newPosition) || checkSnake(newPosition))
		{
			// snake crashed! Game is over...
			throw new GameOverException(this.score);
		}
		// move the snake to the new Position
		this.snake.moveTo(newPosition);
			
	}
	
	/** 
	 * Is called when the highscore is beaten
	 */
	private void onNewHighscore()
	{
		this.statusMessage = "New HighScore!";
		this.highScore = this.score;
		
	}
	/** 
	 * Places one food on the map with a random size between 1 and 5
	 */
	private void placeRandomFood() 
	{
		Random r = new Random();
		this.foods.add(new Food(findEmptySpot(), 1+r.nextInt(5)));
	}
	
	
	/**
	 * Places the snake at a given position
	 */
	private void placeSnake() 
	{
		// todo make random
		this.snake = new Snake(new Point(4,2),3,Heading.EAST);
	}
	
	/** 
	 * Finds a random emptyposition on the map
	 * @return a random position on the map
	 */
	private Point findEmptySpot() {
		Random r = new Random();
		Point returnPoint = null;
		boolean goodPosition = false;
		
		while(!goodPosition)
		{
			returnPoint = new Point(r.nextInt(this.theMap.getWidth()),r.nextInt(this.theMap.getHeight()));
			
			if (!this.checkObstacles(returnPoint) && !this.checkSnake(returnPoint) && this.checkFood(returnPoint)==null)
			{
				goodPosition = true;
			}
		}
		
		return returnPoint;
	}

	/**
	 * Paints the whole scene on the game-frame with the snake, all the food and obstacles
	 * @param g the Graphics2D-Object to draw on
	 */
	@Override
	public void paintScene(Graphics2D g) {
		this.snake.paint(g);
		for(Obstacle oneObstacle:this.theMap.getObstacles()) oneObstacle.paint(g);
		for(Food oneFood:this.foods) oneFood.paint(g);
		
	}
	
	/**
	 * Paints the current score, Highscore and the StatusMessage on the status-screen
	 * @param g the Graphics2D-Object to draw on
	 */
	@Override
	public void paintStatus(Graphics2D g) {
		g.drawString("Score: "+this.score, 10, 10);
		g.drawString("Highscore: "+this.highScore, 10, 50);
		g.drawString(this.statusMessage , 10, 90);
		
	}
	
	/**
	 * Sets a message on the status-screen
	 * @param theMessage the Message to show
	 */
	public void setStatus(String theMessage)
	{
		this.statusMessage = theMessage;
	}
	
	
	
}
