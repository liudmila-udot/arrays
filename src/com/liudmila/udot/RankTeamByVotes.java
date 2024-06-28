package com.liudmila.udot;

import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collector;

/**
 * https://leetcode.com/problems/rank-teams-by-votes/
 * <p>
 * The ordering of teams is decided by who received the most position-one votes.
 * If two or more teams tie in the first position, we consider the second position to resolve the conflict,
 * if they tie again, we continue this process until the ties are resolved.
 * <p>
 * If two or more teams are still tied after considering all positions,
 * we rank them alphabetically based on their team letter.
 */
public class RankTeamByVotes {

    public static String rankTeams(String[] votes) {
        if (votes == null || votes.length == 0) {
            return "";
        }
        HashMap<Character, int[]> teamVotes = new HashMap<>();
        for (String vote : votes) {
            char[] teamPlace = vote.toCharArray();
            for (int i = 0; i <= teamPlace.length - 1; i++) {
                int[] teamVotesValue = teamVotes.getOrDefault(teamPlace[i], new int[teamPlace.length]);
                teamVotesValue[i]++;
                teamVotes.put(teamPlace[i], teamVotesValue);
            }
        }

        String result = teamVotes.entrySet().stream()
                .sorted(Map.Entry.comparingByValue((v1, v2) -> {
                    int comparingRank = 0;
                    while (comparingRank < v1.length) {
                        if (v1[comparingRank] == v2[comparingRank]) {
                            comparingRank++;
                        } else {
                            return -Integer.compare(v1[comparingRank], v2[comparingRank]);
                        }
                    }
                    return 0;
                }))
                .map(Map.Entry::getKey)
                .collect(Collector.of(
                        StringBuilder::new,
                        StringBuilder::append,
                        StringBuilder::append,
                        StringBuilder::toString));

        return result;
    }

    public static void main(String[] args) {
        String[] votes = {"ZMNAGUEDSJYLBOPHRQICWFXTVK"};
        String ret = rankTeams(votes);
        System.out.println(ret);
    }
}
