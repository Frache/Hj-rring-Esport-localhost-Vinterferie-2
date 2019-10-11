package db;

import model.Game;

public interface GameDBIF 
{
	void addGame(Game game) throws WrongInputException;
	Game findGame(int id) throws WrongInputException;
}