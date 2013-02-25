package snake;

import java.awt.FileDialog;
import java.awt.Frame;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.OutputStreamWriter;

import javax.swing.JOptionPane;

import snake.gui.SEngine;
import snake.gui.SEngineFactory;
import snake.gui.SEngineKeyHandler;
import snake.gui.SEngineMenuHandler;
import snake.gui.SEngineTimerHandler;

/**
 * class for the whole Snake-GameApplication, 
 * it's managing the menu, gets inputs from the keyboard-interface and starts the game
 * by implementing the given Interfaces
 * @author macrozone
 *
 */
public class GameApplication implements SEngineKeyHandler, SEngineTimerHandler, SEngineMenuHandler
{
	// speed of the game in ms
	private static final int GAME_SPEED = 100;

	// defines if the game is paused
	private boolean isPaused = false;
	
	// the gamelogic of the game
	private GameLogic gameLogic;
	
	// the engine for the graphic, the menu and the keyboard-handling
	private SEngine sEngine;
	
	// is true as long as the game is 
	private boolean gameIsOver = false;

	
	
	/**
	 * constructor
	 */
	public GameApplication()
	{
		this.gameLogic = new GameLogic(new Map("defaultMap.map"));

		this.sEngine = SEngineFactory.makeSwingEngine();
		this.sEngine.setTimerHandler(this);
		this.sEngine.setKeyHandler(this);
		this.sEngine.setMenuHandler(this);

		this.sEngine.setScenePainter(this.gameLogic);
		this.sEngine.setTimerIntervall(GAME_SPEED);
		this.sEngine.setStatusPainter(this.gameLogic);
		this.sEngine.show();
		
		this.newGame();
	}

	/**
	 * Inits a new game
	 */
	public void newGame()
	{
		this.gameIsOver = false;
		this.loadHighScore();
		this.isPaused = true;
		this.gameLogic.newGame();
		this.sEngine.invalidate();

	}

	/**
	 * Quits the game and saves the HighScore
	 */
	public void endGame()
	{
		this.saveHighScore();
	}

	/**
	 * Is called when a EAST-presenting key is pressed
	 */
	@Override
	public void onEast()
	{
		this.gameLogic.setSnakeHeading(Heading.EAST);
	}
	/**
	 * Is called when a NORTH-presenting key is pressed
	 */
	@Override
	public void onNorth()
	{
		this.gameLogic.setSnakeHeading(Heading.NORTH);
	}

	/**
	 * Is called when a SOUTH-presenting key is pressed
	 */
	@Override
	public void onSouth()
	{
		this.gameLogic.setSnakeHeading(Heading.SOUTH);
	}

	/**
	 * Is called when a WEST-presenting key is pressed
	 */
	@Override
	public void onWest()
	{
		this.gameLogic.setSnakeHeading(Heading.WEST);
	}

	/**
	 * Is called when a PAUSE-presenting key is pressed
	 */
	@Override
	public void onPause()
	{
		
		isPaused = !isPaused;
		if (isPaused) this.gameLogic.setStatus("- Paused -");
		else this.gameLogic.setStatus("");
	}

	/**
	 * Is called on every time-tick
	 */
	@Override
	public void onTimer()
	{
		if (!this.isPaused && !this.gameIsOver)
		{
			try
			{
				// do a turn on the game-Logic
				this.gameLogic.doOneTurn();
			}
			catch (GameOverException e)
			{
				// game is over
				this.onGameOver(e);
			}
			
		}
		// draw the screen again
		this.sEngine.invalidate();

	}

	/**
	 * Is called when the game is over (gamelogic has thrown a GameOverException)
	 * @param e the Exception
	 */
	private void onGameOver(GameOverException e)
	{
		this.gameIsOver = true;
		JOptionPane.showMessageDialog(null, "Your Game is Over! \nYour Score was: " + e.getScore(), "Game Over", JOptionPane.ERROR_MESSAGE);
		this.gameLogic.setStatus("Game is Over!");
		this.saveHighScore();
	}

	/**
	 * Is called when the new-Game-menu-point is chosen
	 */
	@Override
	public void onNewGame()
	{
		this.newGame();
	}

	/**
	 * Is called when the Pause-Game-menu-point is chosen
	 */
	@Override
	public void onPauseGame()
	{
		this.onPause();
	}

	
	/**
	 * Is called when the Load-Map-Menu-point is chosen
	 */
	@Override
	public void onLoadMap()
	{
		
		FileDialog fd = new FileDialog(new Frame(), "Load Map", FileDialog.LOAD);
		fd.setFile("*.map");
		fd.setVisible(true);
		String loadedMap= fd.getDirectory() + System.getProperty("file.separator") + fd.getFile();
		this.gameLogic.setMap(new Map(loadedMap));
		this.newGame();
		
	}
	/**
	 * Loads the highscore from the textfile score.txt, sets highscore = 0 if
	 * file can't be loaded, read or if it's not a legal number
	 */
	private void loadHighScore()
	{

		BufferedReader f = null;
		try
		{
			f = new BufferedReader(new FileReader("score.txt"));
			this.gameLogic.setHighScore(Integer.parseInt(f.readLine()));
		}
		catch (FileNotFoundException e)
		{
			e.printStackTrace();
			this.gameLogic.setHighScore(0);
		}
		catch (IOException e)
		{
			e.printStackTrace();
			this.gameLogic.setHighScore(0);
		}
		catch (NumberFormatException e)
		{
			e.printStackTrace();
			this.gameLogic.setHighScore(0);
		}
		finally
		{
			if (f != null)
			{
				try
				{
					f.close();
				}
				catch (IOException e)
				{
					
					e.printStackTrace();
				}
			}
		}

	}

	/**
	 * Saves the highscore in the score.txt file
	 */
	private void saveHighScore()
	{
		BufferedWriter bw = null;
		try
		{
			bw = new BufferedWriter(new OutputStreamWriter(new FileOutputStream("score.txt", false)));
			try
			{
				bw.write(this.gameLogic.getHighScore() + "");
			}
			catch (IOException e)
			{
				
				e.printStackTrace();
			}
		}
		catch (FileNotFoundException e)
		{
			e.printStackTrace();
		}
		finally
		{
			if (bw != null)
			{
				try
				{
					bw.close();
				}
				catch (IOException e)
				{
					
					e.printStackTrace();
				}
			}
		}
	}

	

}
