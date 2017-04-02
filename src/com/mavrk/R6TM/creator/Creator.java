/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.mavrk.R6TM.creator;

import static java.lang.Math.abs;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Sanatt Abrol
 */
public class Creator {
    //This class creates the teams by taking input from main UI class as a List
    List<Integer> OrangeTeam = new ArrayList();
    List<Integer> BlueTeam = new ArrayList();
    List<Boolean> IsVisited = new ArrayList();
    List<String> OrangeTeamMembers = new ArrayList();
    List<String> BlueTeamMembers = new ArrayList();
    
    
    public void createIsVisitedList(List<Integer> RawDraft)
    {
        for(int i:RawDraft)
            IsVisited.add(Boolean.FALSE);
    }
    public int findNearest(List<Integer> RawDraft, int maxInRawDraft, int minInRawDraft, int CurrentPos)
    {
        int diff, NearestIndex = 0;
        int min = maxInRawDraft - minInRawDraft;
        for(int i=0; i<RawDraft.size();i++)
        {
            if(i == CurrentPos)
                continue;
            else if(IsVisited.get(i) == true)
                continue;
            else
            {
                diff = abs(RawDraft.get(CurrentPos) - RawDraft.get(i));
                if(diff < min)
                {
                    min = diff;
                    NearestIndex = i;
                }
            }
        }
        
        return NearestIndex;
    }
    
    public void createTeams(List<String> RawDraftMembers,List<Integer> RawDraft,int maxInRawDraft, int minInRawDraft)
    {
        createIsVisitedList(RawDraft);
        
        for(int i=0; i< RawDraft.size(); i++)
        {
            if(IsVisited.get(i) == true)
                continue;
            else
            {
                int j = findNearest(RawDraft,maxInRawDraft,minInRawDraft,i);
                System.out.println(""+j);
                IsVisited.set(i, Boolean.TRUE);
                IsVisited.set(j, Boolean.TRUE);
                if(sumOfList(OrangeTeam) <= sumOfList(BlueTeam))
                {
                    if(RawDraft.get(i)>=RawDraft.get(j))
                    {
                        OrangeTeam.add(RawDraft.get(i));
                        OrangeTeamMembers.add(RawDraftMembers.get(i));
                        BlueTeam.add(RawDraft.get(j));
                        BlueTeamMembers.add(RawDraftMembers.get(j));
                    }
                    else
                    {
                        OrangeTeam.add(RawDraft.get(j));
                        OrangeTeamMembers.add(RawDraftMembers.get(j));
                        BlueTeam.add(RawDraft.get(i));
                        BlueTeamMembers.add(RawDraftMembers.get(i));
                    
                    }
                }
                else
                {
                    if(RawDraft.get(i)>=RawDraft.get(j))
                    {
                        OrangeTeam.add(RawDraft.get(j));
                        OrangeTeamMembers.add(RawDraftMembers.get(j));
                        BlueTeam.add(RawDraft.get(i));
                        BlueTeamMembers.add(RawDraftMembers.get(i));
                    }
                    else
                    {
                        OrangeTeam.add(RawDraft.get(i));
                        OrangeTeamMembers.add(RawDraftMembers.get(i));
                        BlueTeam.add(RawDraft.get(j));
                        BlueTeamMembers.add(RawDraftMembers.get(j));
                    
                    }
                }
            }
        }
    }
    public int sumOfList(List<Integer> lst)
    {
        int sum = 0;
        for(int i=0 ; i<lst.size() ; i++)
            sum += lst.get(i);
        return sum;
    }
    public static void main(String[] args) {
        String s = "abcdefgh";
        System.out.println(""+s.substring(0,4));
    }
    
}
