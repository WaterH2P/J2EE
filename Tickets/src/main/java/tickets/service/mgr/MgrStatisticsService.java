package tickets.service.mgr;

import tickets.model.mgr.UserStatistic;
import tickets.model.mgr.VenueStatistic;

import java.util.List;

public interface MgrStatisticsService {

	List<UserStatistic> getAllUserStatistics();
	
	List<VenueStatistic> getAllVenueStatistics();

}
