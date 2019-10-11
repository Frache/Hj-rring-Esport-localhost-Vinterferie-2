package db;

import model.Player;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class PlayerDB 
{
	private static final String ADD_PLAYER_TO_DATABASE_Q = "insert into player(fname, lname, age, zipCode, email, gamerTag) values (?, ?, ?, ?, ?, ?)";
	private PreparedStatement addPlayerToDatabasePS;
	private static final String GET_HIGHEST_ID_Q = "select MAX(id) from player";
	private PreparedStatement getHighestIdPS;
	
	private Connection connection;
	
	public PlayerDB() throws NoConnectionException
	{
		connection = DBConnection.getInstance().getConnection();
		prepareStatements();
	}

	private void prepareStatements() throws NoConnectionException 
	{
		try 
		{
			addPlayerToDatabasePS = connection.prepareStatement(ADD_PLAYER_TO_DATABASE_Q);
			getHighestIdPS = connection.prepareStatement(GET_HIGHEST_ID_Q);
		}
		catch (SQLException e) 
		{
			throw new NoConnectionException(e, "Der er ikke forbindelse til databasen.");
		}
		
	}

	public int addPlayer(Player player) throws MisMatchException 
	{
		int id = 0;
		try 
		{
			addPlayerToDatabasePS.setString(1, player.getFname());
			addPlayerToDatabasePS.setString(2, player.getLname());
			addPlayerToDatabasePS.setInt(3, player.getAge());
			addPlayerToDatabasePS.setString(4, player.getZipCode());
			addPlayerToDatabasePS.setString(5, player.getEmail());
			addPlayerToDatabasePS.setString(6, player.getGamerTag());
			addPlayerToDatabasePS.executeUpdate();
			ResultSet rs = getHighestIdPS.executeQuery();
			if(rs.next())
			{
				id = rs.getInt(1);
			}
		}
		catch (SQLException e) 
		{
			throw new MisMatchException(e, "Kunne ikke gemme spillerens data i databasen.");
		}
		
		
		return id;
	}
	
	

}
