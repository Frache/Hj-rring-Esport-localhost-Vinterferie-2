package controller;

import java.util.ArrayList;

import model.*;
import db.*;

public class TournamentController 
{
	private GameController gameController;
	private TournamentDBIF tournamentDB;
	private Tournament tournament;
	private TeamController teamController;
	
	public TournamentController() throws WrongInputException, MisMatchException
	{
		try
		{
			gameController = new GameController();
			tournamentDB = new TournamentDB();
			teamController = new TeamController();
			tournament = tournamentDB.findTournament();
			tournament.setGame(gameController.findGame(tournament.getGame().getId()));
			tournament.setRankedTeams(teamController.findTeams());
		}
		catch(NoConnectionException e)
		{
			e.printStackTrace();
		}
	}
	
	public GameController getGameController() 
	{
		return gameController;
	}

	public TournamentDBIF getTournamentDB() 
	{
		return tournamentDB;
	}

	public Tournament getTournament() 
	{
		return tournament;
	}

	public TeamController getTeamController() 
	{
		return teamController;
	}

	public void setGameController(GameController gameController) 
	{
		this.gameController = gameController;
	}

	public void setTournamentDB(TournamentDBIF tournamentDB) 
	{
		this.tournamentDB = tournamentDB;
	}

	public void setTournament(Tournament tournament) 
	{
		this.tournament = tournament;
	}

	public void setTeamController(TeamController teamController) 
	{
		this.teamController = teamController;
	}

	public ArrayList<Team> updateTeamsRanked() 
	{
		ArrayList<Team> listedTeams = tournament.getTeamsRanked();
		tournament.setRankedTeams(listedTeams);
		return listedTeams;
	}

	public void addRound(Round round) 
	{
		tournament.addRound(round);	
	}
	
	public ArrayList<Team> findTeams() throws NoConnectionException, MisMatchException
	{
		ArrayList<Team> teams = teamController.findTeams();
		return teams;
	}
	
	public void setTournament() throws MisMatchException
	{
		if(tournament != null)
		{
			tournament = tournamentDB.findTournament();
		}
	}
	
	public void updateRankedTeams() throws NoConnectionException, MisMatchException
	{
		tournament.setRankedTeams(teamController.findTeams());
	}
	
	public void updateWinner(String winner, String loser)
	{
		tournament.updateWinner(winner, loser);
	}

	public void removeLosers() 
	{
		tournament.removeLosers();
	}

}
