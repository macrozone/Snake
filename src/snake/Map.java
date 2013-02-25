package snake;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

/**
 * Map Class, reads a map from a txt-File and generates obstacles out of it
 * 
 * @author macrozone
 * 
 */
public class Map
{
	private int width = 20;
	private int height = 20;
	private ArrayList<Obstacle> obstacles;
	
	
	public Map(String filename)
	{
		this.obstacles = new ArrayList<Obstacle>();
		loadFile(filename);
	}

	/**
	 * Loads the file given
	 */
	private void loadFile(String filename)
	{
		BufferedReader f = null;
		try
		{
			f = new BufferedReader(new FileReader(filename));
			this.parseMap(f);
		}
		catch (FileNotFoundException e)
		{
			e.printStackTrace();

		}

		catch (MapMissbuiltException e)
		{
			e.printStackTrace();
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
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}

	}

	/**
	 * Parses the map from the BufferedReader given
	 * @param f : the buffered Reader
	 * @throws MapMissbuiltException is thrown when the loaded map is Missbuilt in some way
	 */
	private void parseMap(BufferedReader f) throws MapMissbuiltException
	{

		int oneCharVal;
		char oneChar;
		int row = 0;
		int col = 0;
		try
		{
			// take char form the buffer and decide what to do
			while ((oneCharVal = f.read()) != -1)
			{
				oneChar = (char) oneCharVal;
				switch (oneChar)
				{
				case 'o':
					// this is an obstacle
					this.obstacles.add(new Obstacle(new Point(col, row)));
					col++;
					break;
				case '\n':
					// now comes a new line
					col = 0;
					row++;
					break;

				case ' ':
					// just a space
					col++;
					break;
				default:
					// anything else is also a space
					col++;
					break;
				}
				// throw an exception if  the map is too wide or too high
				if (row > this.height + 1 || col > this.width + 2)
					throw new MapMissbuiltException("Map is too large!");
			}
		}
		catch (IOException e)
		{
			// if there occures an error while reading
			e.printStackTrace();
		}
	}
	/**
	 * Get the width of the Map
	 * @return the Width of the Map
	 */
	public int getWidth()
	{
		return width;
	}
	
	/**
	 * Get the height of the Map
	 * @return the Height of the Map
	 */
	public int getHeight()
	{
		return height;
	}

	/**
	 * get the ArrayList of obstacles on this map
	 * @return the ArrayList of Obstacles on this map
	 */
	public ArrayList<Obstacle> getObstacles()
	{
		return this.obstacles;
	}

}
