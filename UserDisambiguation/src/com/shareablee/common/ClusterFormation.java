/**
 * 
 */
package com.shareablee.common;

import java.util.ArrayList;
import java.util.List;

import com.shareablee.utils.ProgramConstants;

/**
 * @author mm
 *
 */
public class ClusterFormation {
	
	public static Cluster findCluster(UserProfile user){
		
		Cluster retVal = null;
		
		for (Cluster cluster : Program.getClusterCollection()) {
			double similarityScore = 0;
			//pick randomly
			List<UserProfile> randomUsers = new ArrayList<>();
			
			while(cluster.getUsers().size() > 0 && randomUsers.size() < ProgramConstants.COUNT_USER_CLUSTER) {
				randomUsers.add(cluster.getUsers().remove((int)(Math.random() * cluster.getUsers().size())));
			}
			
			int count = randomUsers.size();
			
			while(randomUsers.size() > 0) {
				similarityScore += Disambiguator.caluclateSimilarities(user, randomUsers.get(0));
				cluster.addUsers(randomUsers.remove(0));
			}
			
			double avgSimilarityScore = similarityScore / count;
			
			if(avgSimilarityScore >= ProgramConstants.CLUSTER_THRESHOLD) {
				retVal = cluster;
				break;
			}
		}
		
		return retVal;
	}
	
	public static void addToCluster(Cluster cluster, UserProfile user) {
		if(cluster == null) return;
		cluster.addUsers(user);
	}
	
	
}
