package Recommendation;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.apache.mahout.cf.taste.common.TasteException;
import org.apache.mahout.cf.taste.impl.model.file.FileDataModel;
import org.apache.mahout.cf.taste.impl.neighborhood.NearestNUserNeighborhood;
import org.apache.mahout.cf.taste.impl.neighborhood.ThresholdUserNeighborhood;
import org.apache.mahout.cf.taste.impl.recommender.GenericUserBasedRecommender;
import org.apache.mahout.cf.taste.impl.similarity.EuclideanDistanceSimilarity;
import org.apache.mahout.cf.taste.impl.similarity.PearsonCorrelationSimilarity;
import org.apache.mahout.cf.taste.model.DataModel;
import org.apache.mahout.cf.taste.neighborhood.UserNeighborhood;
import org.apache.mahout.cf.taste.recommender.RecommendedItem;
import org.apache.mahout.cf.taste.recommender.UserBasedRecommender;
import org.apache.mahout.cf.taste.similarity.UserSimilarity;

public class CollaborativeFiltering {
	
	
	
	public List<Long> filter() throws IOException, TasteException {
		
		
		
		
		File preferenceValues = new File("C:\\Bitnami\\wampstack-5.6.19-0\\frameworks\\laravel\\storage\\exports\\hotel_preferences.csv");
		DataModel recommendationModel = new FileDataModel(preferenceValues);
		
		UserSimilarity pearson = new EuclideanDistanceSimilarity(recommendationModel);
		UserNeighborhood nh = new ThresholdUserNeighborhood(0.1, pearson, recommendationModel);
		
		long[] myArray = nh.getUserNeighborhood(1);
		
		UserBasedRecommender recommender = new GenericUserBasedRecommender(recommendationModel, nh, pearson);
		
		System.out.println(myArray[0]);
		
		List<Long> returnArray = new ArrayList<Long>();
		int i=0;
		
		List<RecommendedItem> recommendations = recommender.recommend(1, 3);
		for (RecommendedItem recommendation : recommendations) {
		  System.out.println(recommendation);
		  
		  returnArray.add(recommendation.getItemID());
		  i++;
		  
		}
		
		return returnArray;
		
	}

}
