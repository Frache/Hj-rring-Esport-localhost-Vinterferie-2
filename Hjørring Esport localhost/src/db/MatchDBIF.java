package db;

import java.util.ArrayList;

import model.Match;
import model.Round;

public interface MatchDBIF 
{
	void addMatchesToDatabase(ArrayList<Match> matches, int roundId) throws WrongInputException, NoConnectionException;
	void addMatchToDatabase(Match m, int roundId) throws WrongInputException;
	void updateResultInDatabase(Match match, Round round) throws MisMatchException;
	void deleteAllMatches();
}