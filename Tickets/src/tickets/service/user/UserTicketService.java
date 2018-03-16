package tickets.service.user;

import tickets.model.venue.VenuePlan;

import java.util.List;

public interface UserTicketService {
	
	List<VenuePlan> searchPlanByPlanName(String planName);
	
}
