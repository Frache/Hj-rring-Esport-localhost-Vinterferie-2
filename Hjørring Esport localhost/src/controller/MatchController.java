package controller;

import model.*;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.util.ArrayList;
import java.util.Collection;

import db.*;

public class MatchController 
{
	private MatchDBIF matchDB;
	
	public MatchController() throws NoConnectionException
	{
		matchDB = new MatchDB();
	}

	public ArrayList<Match> createMatches(ArrayList<Team> rankedTeams) throws WrongInputException, NoConnectionException
	{
		int amountOfMatches = rankedTeams.size()/2;
		int counter = 0;
		int index = 0;
		ArrayList<Match> matches = new ArrayList<>();
		while(amountOfMatches > counter)
		{
			Team teamOne = rankedTeams.get(index);
			index++;
			Team teamTwo = rankedTeams.get(index);
			index++;
			Match m = new Match(teamOne, teamTwo);
			matches.add(m);
			counter++;
		}
		return matches;
	}
	
	public void addMatchesToDatabase(ArrayList<Match> matches, int roundId) throws WrongInputException, NoConnectionException
	{
		matchDB.addMatchesToDatabase(matches, roundId);	
	}

	public void updateMatches(Round round) throws MisMatchException 
	{
		for(Match match: round.getMatches())
		{
			matchDB.updateResultInDatabase(match, round);
		}
	}

	public void deleteAllMatches() 
	{
		matchDB.deleteAllMatches();
	}

	public ArrayList<ArrayList<Match>> createGroupStageMatches(ArrayList<ArrayList<Team>> groups) 
	{
		ArrayList<ArrayList<Match>> groupMatches = new ArrayList<>();
		ArrayList<ArrayList<Match>> temporaryList = new ArrayList<>();
		ArrayList<Match> group1Matches = new ArrayList<>();
		temporaryList.add(group1Matches);
		ArrayList<Match> group2Matches = new ArrayList<>();
		temporaryList.add(group2Matches);
		ArrayList<Match> group3Matches = new ArrayList<>();
		temporaryList.add(group3Matches);
		ArrayList<Match> group4Matches = new ArrayList<>();
		temporaryList.add(group4Matches);
		ArrayList<Match> group5Matches = new ArrayList<>();
		temporaryList.add(group5Matches);
		ArrayList<Match> group6Matches = new ArrayList<>();
		temporaryList.add(group6Matches);
		ArrayList<Match> group7Matches = new ArrayList<>();
		temporaryList.add(group7Matches);
		ArrayList<Match> group8Matches = new ArrayList<>();
		temporaryList.add(group8Matches);
		ArrayList<Match> group9Matches = new ArrayList<>();
		temporaryList.add(group9Matches);
		ArrayList<Match> group10Matches = new ArrayList<>();
		temporaryList.add(group10Matches);
		int index = 0;
		for(ArrayList<Team> group: groups)
		{
			boolean found = false;
			while(!found)
			{
					if(temporaryList.get(index).isEmpty())
					{
						found = true;
						temporaryList.get(index).addAll(createGroupMatches(group));
					}
					else
					{
						index++;
					}
			}
		}
		for(ArrayList<Match> group: temporaryList)
		{
			if(!group.isEmpty())
			{
				groupMatches.add(group);
			}
		}
		return groupMatches;
	}

	private ArrayList<Match> createGroupMatches(ArrayList<Team> group) 
	{
		ArrayList<Match> matches = new ArrayList<>();
		switch(group.size())
		{
		case 3:
			Match m1 = new Match(group.get(0), group.get(1));
			matches.add(m1);
			Match m2 = new Match(group.get(0), group.get(2));
			matches.add(m2);
			Match m3 = new Match(group.get(1), group.get(2));
			matches.add(m3);
			break;
		case 4:
			Match match1 = new Match(group.get(0), group.get(1));
			matches.add(match1);
			Match match2 = new Match(group.get(2), group.get(3));
			matches.add(match2);
			Match match3 = new Match(group.get(0), group.get(2));
			matches.add(match3);
			Match match4 = new Match(group.get(1), group.get(3));
			matches.add(match4);
			Match match5 = new Match(group.get(0), group.get(3));
			matches.add(match5);
			Match match6 = new Match(group.get(1), group.get(2));
			matches.add(match6);
			break;
		case 5:
			Match matchOne = new Match(group.get(0), group.get(1));
			matches.add(matchOne);
			Match matchTwo = new Match(group.get(2), group.get(3));
			matches.add(matchTwo);
			Match matchThree = new Match(group.get(1), group.get(2));
			matches.add(matchThree);
			Match matchFour = new Match(group.get(3), group.get(4));
			matches.add(matchFour);
			Match matchFive = new Match(group.get(0), group.get(4));
			matches.add(matchFive);
			Match matchSix = new Match(group.get(1), group.get(3));
			matches.add(matchSix);
			Match matchSeven = new Match(group.get(0), group.get(3));
			matches.add(matchSeven);
			Match matchEight = new Match(group.get(2), group.get(4));
			matches.add(matchEight);
			Match matchNine = new Match(group.get(0), group.get(2));
			matches.add(matchNine);
			Match matchTen = new Match(group.get(1), group.get(4));
			matches.add(matchTen);
			break;
		}
		return matches;
	}
}
