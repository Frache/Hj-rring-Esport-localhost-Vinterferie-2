package model;

public class Player {

	private int id;
	private String fname;
	private String lname;
	private int age;
	private String zipCode;
	private String email;
	private String gamerTag;
	private int wins;
	private int losses;
	

	
	public Player(String fname, String lname, int age, String zipCode, String email, String gamerTag)
	{
		this.id = 0;
		this.fname = fname;
		this.lname = lname;
		this.age = age;
		this.setZipCode(zipCode);
		this.email = email;
		this.gamerTag = gamerTag;
		this.wins = 0;
		this.losses = 0;

		
		
		
	}
	
	public Player(int id, String fname, String lname, int age, String zipCode, String email, String gamerTag, int wins, int losses)
	{
		this.id = id;
		this.fname = fname;
		this.lname = lname;
		this.age = age;
		this.setZipCode(zipCode);
		this.email = email;
		this.gamerTag = gamerTag;
		this.wins = wins;
		this.losses = losses;
	}

	public int getId() 
	{
		return id;
	}

	public void setId(int id) 
	{
		this.id = id;
	}

	public String getFname() 
	{
		return fname;
	}

	public void setFname(String fname) 
	{
		this.fname = fname;
	}

	public String getLname() 
	{
		return lname;
	}

	public void setLname(String lname) 
	{
		this.lname = lname;
	}

	public String getEmail() 
	{
		return email;
	}

	public void setEmail(String email) 
	{
		this.email = email;
	}

	public String getGamerTag() 
	{
		return gamerTag;
	}

	public void setGamerTag(String gamerTag) 
	{
		this.gamerTag = gamerTag;
	}

	public int getWins() 
	{
		return wins;
	}

	public void setWins(int wins) 
	{
		this.wins = wins;
	}

	public int getLosses() 
	{
		return losses;
	}

	public void setLosses(int losses) 
	{
		this.losses = losses;
	}

	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}

	public String getZipCode() {
		return zipCode;
	}

	public void setZipCode(String zipCode) {
		this.zipCode = zipCode;
	}
	



	
}
