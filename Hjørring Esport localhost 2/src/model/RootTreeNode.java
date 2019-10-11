package model;

public class RootTreeNode 
{
	private Team team;
	private RootTreeNode loser;
	private boolean hasLoser;
	
	public RootTreeNode(Team team)
	{
		this.team = team;
		this.loser = null;
		hasLoser = false;
	}

	public Team getTeam() 
	{
		return team;
	}

	public void setTeam(Team team) 
	{
		this.team = team;
	}

	public RootTreeNode getLoser() 
	{
		return loser;
	}

	public void setLoser(RootTreeNode loser) 
	{
		this.loser = loser;
		hasLoser = true;
	}
	
	public boolean hasLoser()
	{
		return hasLoser;
	}
	
	public void removeLoser()
	{
		this.loser = null;
		hasLoser = false;
	}
}
