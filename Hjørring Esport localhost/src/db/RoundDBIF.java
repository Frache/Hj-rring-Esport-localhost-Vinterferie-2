package db;

import model.Round;

public interface RoundDBIF 
{
	int addRound(Round round) throws MisMatchException, WrongInputException;
}