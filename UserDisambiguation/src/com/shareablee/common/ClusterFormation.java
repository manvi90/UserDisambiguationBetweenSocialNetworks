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
	/**
	 * Method to find the most similar cluster to the given user.
	 * @param user
	 * @return
	 */
	public static List<Cluster> findCluster(UserProfile user){
		
		List<Cluster> retVal = new ArrayList<>();
		
		for (Cluster cluster : Program.getClusterCollection()) {
			double similarityScore = 0;
			//pick randomly
			List<UserProfile> randomUsers = new ArrayList<>();
			// select random 25% users 
			int limit = cluster.getUsers().size() / 4 + 10;
			while(cluster.getUsers().size() > 0 && randomUsers.size() < limit) {
				randomUsers.add(cluster.getUsers().remove((int)(Math.random() * cluster.getUsers().size())));
			}
			
			int count = randomUsers.size();
			
			while(randomUsers.size() > 0) {
				similarityScore += Disambiguator.caluclateSimilarities(user, randomUsers.get(0));
				cluster.addUsers(randomUsers.remove(0));
			}
			
			double avgSimilarityScore = similarityScore / count;
			
			if(avgSimilarityScore >= ProgramConstants.CLUSTER_THRESHOLD) {
				retVal.add(cluster);
				
			}
		}
		
		return retVal;
	}
	
	/**
	 * Method that add in cluster the new user coming.
	 * @param cluster
	 * @param user
	 */
	public static void addToCluster(Cluster cluster, UserProfile user) {
		if(cluster == null) return;
		cluster.addUsers(user);
	}
	
	
	
	
}
