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
	 * Method to find the most similar clusters to the given profile.
	 * 
	 * @param profile
	 * @return Collection of potential clusters
	 */
	public static List<Cluster> findCluster(Profile profile) {

		List<Cluster> retVal = new ArrayList<>();

		for (Cluster cluster : Program.getClusterCollection()) {
			double similarityScore = 0;
			List<Profile> randomProfiles = new ArrayList<>();
			// select 25% profiles + 10 randomly from a cluster
			int limit = cluster.getProfiles().size() / 4 + 10;
			while (cluster.getProfiles().size() > 0
					&& randomProfiles.size() < limit) {
				randomProfiles.add(cluster.getProfiles().remove(
						(int) (Math.random() * cluster.getProfiles().size())));
			}

			int count = randomProfiles.size();

			Disambiguator disambiguator = new Disambiguator();

			// measure the similarity between the random profiles and the new
			// incoming profile
			while (randomProfiles.size() > 0) {
				similarityScore += disambiguator.caluclateSimilarities(profile,
						randomProfiles.get(0));
				cluster.addProfile(randomProfiles.remove(0));
			}

			double avgSimilarityScore = similarityScore / count;

			// if the average similarity is greater, selet the cluster as one of
			// the potential candidates
			if (avgSimilarityScore >= Constants.CLUSTER_THRESHOLD) {
				retVal.add(cluster);

			}
		}

		return retVal;
	}

	/**
	 * Adds a new profile to the cluster
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
