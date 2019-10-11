package controller;

import model.*;
import db.*;

public class GameController 
{
	private GameDBIF gameDB;

	public GameController() throws NoConnectionException
	{
		gameDB = new GameDB();
	}
	
	public void createGame(String name) throws WrongInputException
	{
		Game game = new Game(name);
		gameDB.addGame(game);
	}

	public Game findGame(int id) throws WrongInputException 
	{
		Game game = gameDB.findGame(id);
		return game;
	}
}
