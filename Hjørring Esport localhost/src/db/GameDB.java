package db;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import model.Game;
import model.Tournament;

public class GameDB implements GameDBIF 
{
	private Connection connection;
	private static final String ADD_GAME_Q = "insert into game(name) values (?)";
	private PreparedStatement addGamePS;
	private static final String FIND_GAME_BY_ID_Q = "select id, name from game where id = ?";
	private PreparedStatement findGameByIdPS;
	
	public GameDB() //throws NoConnectionException
	{
		connection = DBConnection.getInstance().getConnection();
		prepareStatements();
	}
	
	
	private void prepareStatements()
	{
		try 
		{
			addGamePS = connection.prepareStatement(ADD_GAME_Q);
			findGameByIdPS = connection.prepareStatement(FIND_GAME_BY_ID_Q);
		} 
		catch (SQLException e) 
		{
			e.printStackTrace();
		}
	}

	public void addGame(Game game) throws WrongInputException
	{
		try 
		{
			addGamePS.setString(1, game.getName());
			addGamePS.executeUpdate();
		} 
		catch (SQLException e) 
		{
			throw new WrongInputException(e, "The input is invalid.");
		}
	}

	public Game findGame(int id) throws WrongInputException 
	{
		Game game = null;
		try 
		{
			findGameByIdPS.setInt(1, id);
			ResultSet rs = findGameByIdPS.executeQuery();
			if(rs.next())
				{
					buildObject(rs); 
				}
		}
		catch(SQLException e)
		{
			throw new WrongInputException(e, "Kunne ikke finde nogle spil med id: " + id);
		}
		return game;
	}


	private Game buildObject(ResultSet rs) throws SQLException 
	{	
		Game game = new Game(rs.getString("name"), rs.getInt("id"));
		return game;
	}
}