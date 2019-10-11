package controller;
import model.Player;
import db.MisMatchException;
import db.NoConnectionException;
import db.PlayerDB;


public class PlayerController 
{
	private PlayerDB playerDB;
	
	public PlayerController() throws NoConnectionException
	{
		playerDB = new PlayerDB();
		
	}

	public void createPlayer(String fname, String lname, int age, String zipCode, String email, String gamerTag) throws MisMatchException 
	{
		Player player = new Player(fname, lname, age, zipCode, email, gamerTag);
		int playerID = playerDB.addPlayer(player);
		player.setId(playerID);
		
	}

}
