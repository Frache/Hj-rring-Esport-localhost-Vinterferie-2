package model;

public class Game
{
	private int id;
	private String name;

	public Game(String name, int id) 
	{
		this.name = name;
		this.id = id;
	}

	public Game(int id)
	{
		this.id = id;
		name = null;
	}
	
	public Game(String name)
	{
		this.name = name;
	}
	
	public int getId() 
	{
		return id;
	}

	public void setId(int id) 
	{
		this.id = id;
	}

	public String getName() 
	{
		return name;
	}

	public void setName(String name) 
	{
		this.name = name;
	}
}
