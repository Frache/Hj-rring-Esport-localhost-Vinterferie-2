package controller;

import model.*;
import java.util.List;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

import db.*;

public class RoundController 
{
	private RoundDBIF roundDB;
	private TournamentController tournamentController;
	private MatchController matchController;
	private TeamController teamController;
	private Round currentRound;
	



	public RoundController() throws NoConnectionException, WrongInputException, MisMatchException
	{
		roundDB = new RoundDB();
		tournamentController = new TournamentController();
		matchController = new MatchController();
		teamController = new TeamController();
		currentRound = null;
	}
	
	/**
	 * Finds an ArrayList containing all the teams that are registered for the tournament.
	 * If the amount of teams is an odd number, create a dummy object (Team) and add this to the database and update the 
	 * ArrayList containing teams. Now we are sure that the amount of teams is an equal number.
	 * A match is created containing the first and second team from the fixed list of teams.
	 * Matches are created, containing the next two teams of the fixed list, until all teams are associated with a match.
	 * Then all matches are added to the database. An object of the class Round is created and all matches are added to this round.
	 * 
	 * @return an object of the class Round containing all matches created.
	 * @throws MisMatchException
	 * @throws WrongInputException
	 * @throws NoConnectionException
	 */
	
	public Round createRound(int roundNumber) throws MisMatchException, WrongInputException, NoConnectionException
	{
		
		if(tournamentController.getTournament().getCurrentRound()+1 == roundNumber)
		{
			//new ArrayList holding the teams in the correct order
			if(roundNumber == 1)
			{
				tournamentController.updateRankedTeams();
				teamController.resetTeamVariables(tournamentController.getTournament().getTeamsRanked());
			}
			ArrayList<Team> rankedTeams = tournamentController.updateTeamsRanked();
			
			//ensure that there is an equal amount of teams
			if(rankedTeams.size()%2 == 1)
			{
				//create dummy object of Team
				Team team = new Team("Intet hold", "N/A");
				//add to database
				tournamentController.getTeamController().addTeam(team);
				//update the ArrayList of teams on the tournament
				tournamentController.updateRankedTeams();
				//update the local variable rankedTeams to ensure that the dummy object is added to the collection
				rankedTeams = tournamentController.updateTeamsRanked();
				teamController.resetTeamVariables(tournamentController.getTournament().getTeamsRanked());
			}
			tournamentController.getTournament().setCurrentRound(tournamentController.getTournament().getCurrentRound()+1);
			ArrayList<Match> matches = matchController.createMatches(rankedTeams);
			currentRound = new Round(tournamentController.getTournament().getCurrentRound(), matches);
			int roundId = roundDB.addRound(currentRound);
			currentRound.setId(roundId);
			tournamentController.addRound(currentRound);
			matchController.addMatchesToDatabase(matches, currentRound.getId());
		}
		return currentRound;
	
	}
	
	public void submitMatchResult(String teamOneName,String teamTwoName, int teamOneScore, int teamTwoScore)
	{
		if(teamOneScore > teamTwoScore)
		{
			tournamentController.updateWinner(teamOneName, teamTwoName);
		}
		if(teamTwoScore > teamOneScore)
		{
			tournamentController.updateWinner(teamTwoName, teamOneName);
		}
		currentRound.submitMatchResult(teamOneName, teamTwoName, teamOneScore, teamTwoScore);
	}
	
	public Round getCurrentRound() 
	{
		return currentRound;
	}

	public void setCurrentRound(Round currentRound) 
	{
		this.currentRound = currentRound;
	}

	public void updateResultInDatabase() throws MisMatchException 
	{
		matchController.updateMatches(currentRound);
		teamController.updateTeamVariables(currentRound.getTeams());
	}
	
	public boolean readyForNextRound()
	{
		boolean ready = true;
		for(Match m: currentRound.getMatches())
		{
			if(!m.isPlayed())
			{
				ready = false;
			}
		}
		return ready;
	}
	
	public void updateTreeStructure() throws MisMatchException
	{
		tournamentController.removeLosers();
		teamController.updateMatchResults(currentRound);
		teamController.updateTeamVariables(currentRound.getTeams());
	}
	
	public ArrayList<ArrayList<Team>> createGroupsModulo(ArrayList<Team> teams) throws MisMatchException
	{
		ArrayList<ArrayList<Team>> groups = new ArrayList<>();
		ArrayList<ArrayList<Team>> temporaryGroups = new ArrayList<>();
		ArrayList<Team> group1 = new ArrayList<>();
		temporaryGroups.add(group1);
		ArrayList<Team> group2 = new ArrayList<>();
		temporaryGroups.add(group2);
		ArrayList<Team> group3 = new ArrayList<>();
		temporaryGroups.add(group3);
		ArrayList<Team> group4 = new ArrayList<>();
		temporaryGroups.add(group4);
		ArrayList<Team> group5 = new ArrayList<>();
		temporaryGroups.add(group5);
		ArrayList<Team> group6 = new ArrayList<>();
		temporaryGroups.add(group6);
		ArrayList<Team> group7 = new ArrayList<>();
		temporaryGroups.add(group7);
		ArrayList<Team> group8 = new ArrayList<>();
		temporaryGroups.add(group8);
		ArrayList<Team> group9 = new ArrayList<>();
		temporaryGroups.add(group9);
		ArrayList<Team> group10 = new ArrayList<>();
		temporaryGroups.add(group10);
		
		int index = 0;
		int modulo = teams.size()%4;
		int amountOfGroups = teams.size()/4;
		if(modulo == 2)
		{
			amountOfGroups++;
		}
		
		if(teams.size() < 6)
		{
			
			for(Team team: teams)
			{
				team.setGroupNumber(1);
				group1.add(team);
			}	
			groups.add(group1);
		}
		else
		{
			if(modulo == 0 || modulo == 3)
			{
				while(index < teams.size())
				{
					int groupNumber = (index / 4) + 1;
					teams.get(index).setGroupNumber(groupNumber);
					
					switch(groupNumber)
					{
					case 1:
						group1.add(teams.get(index));
						break;
					case 2:
						group2.add(teams.get(index));
						break;
					case 3:
						group3.add(teams.get(index));
						break;
					case 4:
						group4.add(teams.get(index));
						break;
					case 5:
						group5.add(teams.get(index));
						break;
					case 6:
						group6.add(teams.get(index));
						break;
					case 7:
						group7.add(teams.get(index));
						break;
					case 8:
						group8.add(teams.get(index));
						break;
					case 9:
						group9.add(teams.get(index));
						break;
					case 10:
						group10.add(teams.get(index));
						break;					
					}
					index++;
						
				}
			}
			if(modulo == 1)
			{
				while(index < teams.size())
				{
					if(index + 1 < teams.size())
					{
						int groupNumber = (index / 4) + 1;
						teams.get(index).setGroupNumber(groupNumber);
					}
					else
					{
						int groupNumber = (index / 4);
						teams.get(index).setGroupNumber(groupNumber);
					}
					switch(teams.get(index).getGroupNumber())
					{
					case 1:
						group1.add(teams.get(index));
						break;
					case 2:
						group2.add(teams.get(index));
						break;
					case 3:
						group3.add(teams.get(index));
						break;
					case 4:
						group4.add(teams.get(index));
						break;
					case 5:
						group5.add(teams.get(index));
						break;
					case 6:
						group6.add(teams.get(index));
						break;
					case 7:
						group7.add(teams.get(index));
						break;
					case 8:
						group8.add(teams.get(index));
						break;
					case 9:
						group9.add(teams.get(index));
						break;
					case 10:
						group10.add(teams.get(index));
						break;					
					}
					index++;
				}
			}
			if(modulo == 2)
			{
				while(index < teams.size())
				{
					//all groups of 4 teams
					if(index + 6 < teams.size())
					{
						int groupNumber = (index / 4) + 1;
						teams.get(index).setGroupNumber(groupNumber);
					}
					//first group of 3 teams
					else if(index + 3 < teams.size())
					{
						int groupNumber = amountOfGroups - 1;
						teams.get(index).setGroupNumber(groupNumber);
					}
					//last group of 3 teams
					else
					{
						int groupNumber = amountOfGroups;
						teams.get(index).setGroupNumber(groupNumber);
					}
					switch(teams.get(index).getGroupNumber())
					{
					case 1:
						group1.add(teams.get(index));
						break;
					case 2:
						group2.add(teams.get(index));
						break;
					case 3:
						group3.add(teams.get(index));
						break;
					case 4:
						group4.add(teams.get(index));
						break;
					case 5:
						group5.add(teams.get(index));
						break;
					case 6:
						group6.add(teams.get(index));
						break;
					case 7:
						group7.add(teams.get(index));
						break;
					case 8:
						group8.add(teams.get(index));
						break;
					case 9:
						group9.add(teams.get(index));
						break;
					case 10:
						group10.add(teams.get(index));
						break;					
					}
					index++;
				}
			}
		}
		for(ArrayList<Team> group: temporaryGroups)
		{
			if(!group.isEmpty())
			{
				groups.add(group);
			}
		}
		TeamController.updateGroupNumbers(teams);
		return groups;
	}		

	public ArrayList<ArrayList<Match>> createGroupStage() throws MisMatchException, WrongInputException, NoConnectionException 
	{
		
		ArrayList<ArrayList<Team>> groups = null;
		ArrayList<Team> temporaryTeamContainer = currentRound.getTeams();
		for(Team team: temporaryTeamContainer)
		{
			team.setPoints(team.getWins()*1000);
			team.addPoints(team.getRoundsWon()-team.getRoundsLost());
		}
		Collections.sort(temporaryTeamContainer, new Comparator<Team>() 
		{
		    public int compare(Team t1, Team t2) {
		        return Integer.valueOf(t1.getPoints()).compareTo(Integer.valueOf(t2.getPoints()));
		    }
		});
		Collections.reverse(temporaryTeamContainer);
		
		groups = createGroupsModulo(temporaryTeamContainer);
		
		ArrayList<ArrayList<Match>> groupStageMatches = matchController.createGroupStageMatches(groups);
		ArrayList<Match> allGroupMatches = new ArrayList<>();
		for(ArrayList<Match> matches: groupStageMatches)
		{
			allGroupMatches.addAll(matches);
		}
		tournamentController.getTournament().setCurrentRound(tournamentController.getTournament().getCurrentRound()+1);
		currentRound = new Round(tournamentController.getTournament().getCurrentRound(), allGroupMatches);
		int roundId = roundDB.addRound(currentRound);
		currentRound.setId(roundId);
		tournamentController.addRound(currentRound);
		matchController.addMatchesToDatabase(allGroupMatches, currentRound.getId());
		
		//reset team stats in database
		teamController.resetTeamVariables(temporaryTeamContainer);
		
		return groupStageMatches;
	}
	
	public void updateGroupStageResults(String teamOneName, String teamTwoName, int teamOneScore, int teamTwoScore)
	{
		currentRound.submitMatchResult(teamOneName, teamTwoName, teamOneScore, teamTwoScore);
	}

	public Round findFinalists() throws MisMatchException, WrongInputException, NoConnectionException 
	{
		for(Match match: currentRound.getMatches())
		{	
			//updates the values of each Team based on the value of each match
			match.submitGroupStageResults();	
		}
		teamController.updateTeamVariables(currentRound.getTeams());
		
		ArrayList<Team> finalists = findFinalistTeams();
		ArrayList<Match> matches = matchController.createMatches(finalists);
		tournamentController.getTournament().setCurrentRound(5);
		currentRound = new Round(5, matches);
		int roundId = roundDB.addRound(currentRound);
		currentRound.setId(roundId);
		tournamentController.addRound(currentRound);
		matchController.addMatchesToDatabase(matches, currentRound.getId());
		
		return currentRound;
	}
	
	public ArrayList<Team> findFinalistTeams()
	{
		int groupID = 1;
		ArrayList<Team> checkedTeams = new ArrayList<>();
		ArrayList<Team> finalists = new ArrayList<>();
		
		ArrayList<Team> allTeamsFromCurrentRound = currentRound.getTeams();
		
		ArrayList<Team> allTeams = new ArrayList<>();
		
		for(Team team: allTeamsFromCurrentRound)
		{
			if(!allTeams.contains(team))
			{
				allTeams.add(team);
			}
		}
		
		while(allTeams.size() > checkedTeams.size())
		{
			ArrayList<Team> temporaryGroup = new ArrayList<>();
			for(Team team: allTeams)
			{
				if(team.getGroupNumber() == groupID)
				{
					team.setPoints(team.getWins()*1000);
					team.addPoints(team.getRoundsWon()-team.getRoundsLost());
					checkedTeams.add(team);
					temporaryGroup.add(team);
				}
			}
			
			if(temporaryGroup.size()>0)
			{
				Collections.sort(temporaryGroup, new Comparator<Team>() 
				{
					public int compare(Team t1, Team t2) 
					{
						return Integer.valueOf(t1.getPoints()).compareTo(Integer.valueOf(t2.getPoints()));
					}
				});
				Collections.reverse(temporaryGroup);

				finalists.add(temporaryGroup.get(0));
				finalists.add(temporaryGroup.get(1));
			}
			groupID++;
		}
		return finalists;
	}
}
