package tickets.controller.mgr;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import tickets.controller.CommonCon;
import tickets.daoImpl.ParaName;
import tickets.model.mgr.UserStatistic;
import tickets.model.mgr.VenueStatistic;
import tickets.model.user.UserOd;
import tickets.service.mgr.MgrStatisticsService;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.List;

@Controller
public class MgrStatisticCon {
	
	@Autowired
	private HttpServletRequest request;
	
	@Resource(name = "mgrStatisticsService")
	private MgrStatisticsService mgrStatisticsService;
	
	@RequestMapping(value = "/Mgr/MgrStatistics", method = RequestMethod.GET)
	public String mgrStatistics(){
		HttpSession session = request.getSession(false);
		if( CommonCon.hasLogin(session) ){
			String mgrID = (String)session.getAttribute(ParaName.VerificationCode);
			if( CommonCon.isMgr(mgrID) ){
				return CommonMgr.toMgrStatisticsPage();
			}
			else{
				return CommonCon.redirectToInfoPage();
			}
		}
		else {
			return CommonCon.redirectToLoginPage();
		}
	}
	
	@ResponseBody
	@RequestMapping(value = "/Mgr/GetAllUserStatistics", method = RequestMethod.POST)
	public List<UserStatistic> getAllUserStatistics(){
		List<UserStatistic> userStatistics = new ArrayList<>();
		HttpSession session = request.getSession(false);
		if( CommonCon.hasLogin(session) ){
			String mgrID = (String)session.getAttribute(ParaName.VerificationCode);
			if( CommonCon.isMgr(mgrID) ){
				userStatistics = mgrStatisticsService.getAllUserStatistics();
			}
		}
		return userStatistics;
	}
	
	@ResponseBody
	@RequestMapping(value = "/Mgr/GetAllVenueStatistics", method = RequestMethod.POST)
	public List<VenueStatistic> getAllVenueStatistics(){
		List<VenueStatistic> venueStatistics = new ArrayList<>();
		HttpSession session = request.getSession(false);
		if( CommonCon.hasLogin(session) ){
			String mgrID = (String)session.getAttribute(ParaName.VerificationCode);
			if( CommonCon.isMgr(mgrID) ){
				venueStatistics = mgrStatisticsService.getAllVenueStatistics();
			}
		}
		return venueStatistics;
	}
	
}
