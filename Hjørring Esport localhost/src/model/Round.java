package model;

import java.util.ArrayList;
import java.util.List;

public class Round 
{
	private int roundNumber;
	private ArrayList<Match> matches;
	private int id;
	
	public Round(int roundNumber, ArrayList<Match> matches, int id)
	{
		this.roundNumber = roundNumber;
		this.matches = matches;
		this.id = id;
	}

	public Round(int roundNumber, ArrayList<Match> matches)
	{
		this.roundNumber = roundNumber;
		this.matches = matches;
	}

	public int getRoundNumber() 
	{
		return roundNumber;
	}

	public void setRoundNumber(int roundNumber) 
	{
		this.roundNumber = roundNumber;
	}

	public ArrayList<Match> getMatches() 
	{
		return matches;
	}

	public void setMatches(ArrayList<Match> matches) 
	{
		this.matches = matches;
	}
	
	public void submitMatchResult(String teamOneName, String teamTwoName, int teamOneScore, int teamTwoScore)
	{
		for(Match match: matches)
		{
			if(match.getTeamOne().getName().equals(teamOneName) && match.getTeamTwo().getName().equals(teamTwoName))
			{
				match.submitResult(teamOneScore, teamTwoScore);
			}
		}
	}

	public int getId() 
	{
		return id;
	}

	public void setId(int id)
	{
		this.id = id;
	}
	
	public ArrayList<Team> getTeams()
	{
		ArrayList<Team> teams = new ArrayList<>();
		for(Match match: matches)
		{
			teams.add(match.getTeamOne());
			teams.add(match.getTeamTwo());
		}
		return teams;
	}
}