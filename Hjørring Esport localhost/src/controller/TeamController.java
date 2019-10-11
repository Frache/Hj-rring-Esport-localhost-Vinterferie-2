package controller;

import model.*;

import java.util.ArrayList;

import db.*;


public class TeamController 
{
	private static TeamDBIF teamDB;
	
	public TeamController() throws NoConnectionException
	{
		teamDB = new TeamDB();
	}

	public ArrayList<Team> findTeams() throws NoConnectionException, MisMatchException 
	{
		ArrayList<Team> teams = teamDB.findAllTeams();
		return teams;	
	}

	public void createTeam(String name, String teamTag) throws NoConnectionException, MisMatchException
	{
		Team team = new Team(name, teamTag);
		addTeam(team);
	}
	
	public void addTeam(Team team) throws NoConnectionException, MisMatchException 
	{
		teamDB.createTeam(team);	
	}

	public ArrayList<Team> searchTeams(String text) throws NoConnectionException, MisMatchException {
		ArrayList<Team> teams = findTeams();
		ArrayList <Team> teamsFound = new ArrayList<>();
		for(Team t: teams)
		{
			if(t.getName().contains(text) || t.getTeamTag().contains(text))
			{
				teamsFound.add(t);
			}
		}
		
		return teamsFound;
	}

	public void deleteTeam(Team team) throws WrongInputException, NoConnectionException, MisMatchException 
	{
		teamDB.removeTeam(team);
	}
	
	public void updateTeam(String name, String teamTag, int id) throws WrongInputException
	{
		teamDB.updateTeam(name, teamTag, id);
	}

	public static void updateGroupNumbers(ArrayList<Team> teams) throws MisMatchException 
	{
		teamDB.updateGroupNumbers(teams);
	}

	public void resetTeamVariables(ArrayList<Team> teams) throws MisMatchException 
	{
		for(Team team: teams)
		{
			team.resetStats();
		}
		teamDB.resetTeamVariables(teams);
	}

	public void updateTeamVariables(ArrayList<Team> teams) throws MisMatchException 
	{
		teamDB.updateTeamVariables(teams);
	}

	public void updateMatchResults(Round currentRound) 
	{
		for(Match match: currentRound.getMatches())
		{
			match.getTeamOne().setRoundsWon(match.getTeamOne().getRoundsWon() + match.getTeamOneScore());
			match.getTeamOne().setRoundsLost(match.getTeamOne().getRoundsLost() + match.getTeamTwoScore());
			match.getTeamTwo().setRoundsWon(match.getTeamTwo().getRoundsWon() + match.getTeamTwoScore());
			match.getTeamTwo().setRoundsLost(match.getTeamTwo().getRoundsLost() + match.getTeamOneScore());
		}
	}
	
}
