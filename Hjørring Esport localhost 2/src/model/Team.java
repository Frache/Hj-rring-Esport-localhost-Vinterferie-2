package model;

import java.util.ArrayList;
import java.util.List;

public class Team 
{
	private int id;
	private String name;
	private List<Player> players;
	private String teamTag;
	private int amountOfMatches;
	private int wins;
	private int losses;
	private int roundsWon;
	private int roundsLost;
	private int groupNumber;
	private boolean isInFinals;
	private int points;
	
	public Team(String name, String teamTag, int id)
	{
		this.name = name;
		this.teamTag = teamTag;
		players = new ArrayList<>();
		isInFinals = false;
		this.id = id;
		
	}

	public Team(String name, String teamTag)
	{
		this.name = name;
		this.teamTag = teamTag;
		id = 0;
		players = new ArrayList<>();
		isInFinals = false;
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

	public List<Player> getPlayers() 
	{
		return players;
	}

	public void setPlayers(List<Player> players) 
	{
		this.players = players;
	}

	public String getTeamTag() 
	{
		return teamTag;
	}

	public void setTeamTag(String teamTag) 
	{
		this.teamTag = teamTag;
	}

	public int getAmountOfMatches() 
	{
		return amountOfMatches;
	}

	public void setAmountOfMatches(int amountOfMatches) 
	{
		this.amountOfMatches = amountOfMatches;
	}

	public int getWins() 
	{
		return wins;
	}

	public void setWins(int wins) 
	{
		this.wins = wins;
	}

	public int getLosses() 
	{
		return losses;
	}

	public void setLosses(int losses) 
	{
		this.losses = losses;
	}

	public int getRoundsWon() 
	{
		return roundsWon;
	}

	public void setRoundsWon(int roundsWon) 
	{
		this.roundsWon = roundsWon;
	}

	public int getRoundsLost() 
	{
		return roundsLost;
	}

	public void setRoundsLost(int roundsLost) 
	{
		this.roundsLost = roundsLost;
	}

	public int getGroupNumber() 
	{
		return groupNumber;
	}

	public void setGroupNumber(int groupNumber) 
	{
		this.groupNumber = groupNumber;
	}

	public boolean isInFinals() 
	{
		return isInFinals;
	}


	public void setInFinals(boolean isInFinals) 
	{
		this.isInFinals = isInFinals;
	}
	
	public void incrementWins()
	{
		amountOfMatches++;
		wins++;
	}
	
	public void incrementLosses()
	{
		amountOfMatches++;
		losses++;
	}
	
	public int getPoints()
	{
		return points;
	}
	
	public void addPoints(int addScore)
	{
		points = points + addScore;
	}
	
	public void setPoints(int points)
	{
		this.points = points;
	}
	
	public void resetStats()
	{
		amountOfMatches = 0;
		wins = 0;
		losses = 0;
		roundsWon = 0;
		roundsLost = 0;
	}
}