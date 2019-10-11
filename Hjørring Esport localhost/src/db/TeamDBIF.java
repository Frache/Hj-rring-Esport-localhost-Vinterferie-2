package db;

import java.util.ArrayList;

import model.Team;

public interface TeamDBIF 
{
	ArrayList<Team> findAllTeams() throws NoConnectionException, MisMatchException;
	void createTeam(Team team) throws NoConnectionException, MisMatchException;
	void removeTeam(Team team) throws WrongInputException, NoConnectionException, MisMatchException;
	void updateTeam(String name, String teamTag, int id) throws WrongInputException;
	public void updateGroupNumbers(ArrayList<Team> teams) throws MisMatchException;
	void resetTeamVariables(ArrayList<Team> teams) throws MisMatchException;
	void updateTeamVariables(ArrayList<Team> teams) throws MisMatchException;
}