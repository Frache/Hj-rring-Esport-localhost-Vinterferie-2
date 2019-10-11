package db;

import java.sql.ResultSet;
import java.sql.SQLException;

import model.Tournament;

public interface TournamentDBIF 
{
	void addTournament(Tournament tournament) throws WrongInputException;
	Tournament findTournament() throws MisMatchException;
	Tournament buildObject(ResultSet rs) throws SQLException, MisMatchException;
	int getTournamentId() throws MisMatchException;
}