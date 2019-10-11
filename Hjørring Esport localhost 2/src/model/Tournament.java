package model;

import java.util.List;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;

public class Tournament
{
	int id;
	private String name;
	private LocalDate date;
	private int amountOfRounds;
	private int currentRound;
	private Game game;
	private boolean playInStageDone;
	ArrayList<RootTreeNode> rankedTeams;
	ArrayList<Round> rounds;
	
	public Tournament(String name, LocalDate date, int amountOfRounds, Game game)
	{
		id = -1;
		this.name = name;
		this.date = date;
		this.amountOfRounds = amountOfRounds;
		this.game = game;
		playInStageDone = false;
		rankedTeams = new ArrayList<>();
		rounds = new ArrayList<>();
	}

	public int getId() 
	{
		return id;
	}

	public void setId(int id) 
	{
		this.id = id;
	}

	public ArrayList<RootTreeNode> getRankedTeams() 
	{
		return rankedTeams;
	}

	public ArrayList<Round> getRounds() 
	{
		return rounds;
	}

	public void setRounds(ArrayList<Round> rounds) 
	{
		this.rounds = rounds;
	}

	public String getName() 
	{
		return name;
	}

	public void setName(String name) 
	{
		this.name = name;
	}

	public LocalDate getDate() 
	{
		return date;
	}

	public void setDate(LocalDate date) 
	{
		this.date = date;
	}

	public int getAmountOfRounds() 
	{
		return amountOfRounds;
	}

	public void setAmountOfRounds(int amountOfRounds) 
	{
		this.amountOfRounds = amountOfRounds;
	}

	public int getCurrentRound() 
	{
		return currentRound;
	}

	public void setCurrentRound(int currentRound) 
	{
		this.currentRound = currentRound;
	}

	public Game getGame() 
	{
		return game;
	}

	public void setGame(Game game) 
	{
		this.game = game;
	}

	public boolean isPlayInStageDone() 
	{
		return playInStageDone;
	}

	public void setPlayInStageDone(boolean playInStageDone) 
	{
		this.playInStageDone = playInStageDone;
	}

	public ArrayList<Team> getTeamsRanked() 
	{
		ArrayList<Team> listedTeams = new ArrayList<>();
		ArrayList<Team> losers = new ArrayList<>();
		for(RootTreeNode rtn: rankedTeams)
		{
			listedTeams.add(rtn.getTeam());
			if(rtn.getLoser() != null)
			{
				
				losers.add(rtn.getLoser().getTeam());
			}
		}
		listedTeams.addAll(losers);
		return listedTeams;
	}

	public void setRankedTeams(ArrayList<Team> teams) 
	{
		ArrayList<RootTreeNode> teamNodes = new ArrayList<>();
		for(Team team: teams)
		{
			RootTreeNode rtn = new RootTreeNode(team);
			teamNodes.add(rtn);
		}
		this.rankedTeams = teamNodes;
	}

	public void addRound(Round round) 
	{
		rounds.add(round);
	}

	public void updateWinner(String winner, String loser)
	{
		int index = 0;
		RootTreeNode winningTeam = null;
		RootTreeNode losingTeam = null;
		boolean winnerFound = false;
		boolean loserFound = false;
		while(rankedTeams.size() > index && (!winnerFound || !loserFound))
		{
			if(rankedTeams.get(index).getTeam().getName().equals(winner))
			{
				winningTeam = rankedTeams.get(index);
				winnerFound = true;
			}
			if(rankedTeams.get(index).getTeam().getName().equals(loser))
			{
				losingTeam = rankedTeams.get(index);
				loserFound = true;
			}
			index++;
		}
		winningTeam.setLoser(losingTeam);
		losingTeam.removeLoser();
	}

	public void removeLosers() 
	{
		ArrayList<RootTreeNode> nodesToRemove = new ArrayList<>();
		for(RootTreeNode rtn: rankedTeams)
		{
			if(rtn.hasLoser())
			{
				rtn.getTeam().incrementWins();
				rtn.getLoser().getTeam().incrementLosses();
				nodesToRemove.add(rtn.getLoser());
			}
		}
		rankedTeams.removeAll(nodesToRemove);
	}
}