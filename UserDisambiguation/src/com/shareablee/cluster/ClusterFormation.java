/**
 * 
 */
package com.shareablee.cluster;

import java.util.ArrayList;
import java.util.List;

import com.shareablee.common.Constants;
import com.shareablee.common.Disambiguator;
import com.shareablee.common.Profile;
import com.shareablee.common.Program;

/**
 * @author mm
 *
 */
public class ClusterFormation {
	/**
	 * Method to find the most similar cluster to the given user.
	 * 
	 * @param profile
	 * @return
	 */
	public static List<Cluster> findCluster(Profile profile) {

		List<Cluster> retVal = new ArrayList<>();

		for (Cluster cluster : Program.getClusterCollection()) {
			double similarityScore = 0;
			// pick randomly
			List<Profile> randomUsers = new ArrayList<>();
			// select random 25% users
			int limit = cluster.getProfiles().size() / 4 + 10;
			while (cluster.getProfiles().size() > 0
					&& randomUsers.size() < limit) {
				randomUsers.add(cluster.getProfiles().remove(
						(int) (Math.random() * cluster.getProfiles().size())));
			}

			int count = randomUsers.size();

			Disambiguator disambiguator = new Disambiguator();

			while (randomUsers.size() > 0) {
				similarityScore += disambiguator.caluclateSimilarities(profile,
						randomUsers.get(0));
				cluster.addProfile(randomUsers.remove(0));
			}

			double avgSimilarityScore = similarityScore / count;

			if (avgSimilarityScore >= Constants.CLUSTER_THRESHOLD) {
				retVal.add(cluster);

			}
		}

		return retVal;
	}

	/**
	 * Method that add in cluster the new user coming.
	 * 
	 * @param cluster
	 * @param profile
	 */
	public static void addToCluster(Cluster cluster, Profile profile) {
		if (cluster == null)
			return;
		cluster.addProfile(profile);
	}

}
