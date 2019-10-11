package model;

public class Match 
{
	private Team teamOne;
	private Team teamTwo;
	private int teamOneScore;
	private int teamTwoScore;
	private Team winningTeam;
	private boolean isPlayed;
	private int id;
		
	public Match(Team teamOne, Team teamTwo)
	{
		this.teamOne = teamOne;
		this.teamTwo = teamTwo;
		this.winningTeam = null;
		id = -1;
		isPlayed = false;
	}

	public Team getTeamOne()
	{
		return teamOne;
	}

	public void setTeamOne(Team teamOne) 
	{
		this.teamOne = teamOne;
	}

	public Team getTeamTwo() 
	{
		return teamTwo;
	}

	public void setTeamTwo(Team teamTwo) 
	{
		this.teamTwo = teamTwo;
	}

	public int getTeamOneScore() 
	{
		return teamOneScore;
	}

	public void setTeamOneScore(int teamOneScore) 
	{
		this.teamOneScore = teamOneScore;
	}

	public int getTeamTwoScore() 
	{
		return teamTwoScore;
	}

	public void setTeamTwoScore(int teamTwoScore) 
	{
		this.teamTwoScore = teamTwoScore;
	}

	public Team getWinningTeam() 
	{
		return winningTeam;
	}

	public void setWinningTeam(Team winningTeam) 
	{
		this.winningTeam = winningTeam;
	}

	public boolean isPlayed() 
	{
		return isPlayed;
	}

	public void setPlayed(boolean isPlayed) 
	{
		this.isPlayed = isPlayed;
	}
	
	public void submitResult(int teamOneScore,int teamTwoScore)
	{
		this.teamOneScore = teamOneScore;
		this.teamTwoScore = teamTwoScore;
		
		if (teamOneScore > teamTwoScore)
		{
			winningTeam = teamOne;
			isPlayed = true;
		}
		if (teamOneScore < teamTwoScore)
		{
			winningTeam = teamTwo;
			isPlayed = true;
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
	
	public void submitGroupStageResults()
	{
		teamOne.setRoundsWon(teamOne.getRoundsWon() + teamOneScore);
		teamOne.setRoundsLost(teamOne.getRoundsLost() + teamTwoScore);
		teamTwo.setRoundsWon(teamTwo.getRoundsWon() + teamTwoScore);
		teamTwo.setRoundsLost(teamTwo.getRoundsLost() + teamOneScore);
		if(teamOneScore > teamTwoScore)
		{
			teamOne.incrementWins();
			teamTwo.incrementLosses();
		}
		if(teamTwoScore > teamOneScore)
		{
			teamTwo.incrementWins();
			teamOne.incrementLosses();
		}
	}
}
