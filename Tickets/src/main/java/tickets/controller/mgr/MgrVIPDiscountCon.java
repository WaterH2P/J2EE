package tickets.controller.mgr;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import tickets.controller.CommonCon;
import tickets.daoImpl.ParaName;
import tickets.model.Result;
import tickets.model.mgr.CouponInfo;
import tickets.model.mgr.VIPLevelInfo;
import tickets.service.mgr.MgrCouponService;
import tickets.service.mgr.MgrVIPLevelService;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Controller
public class MgrVIPDiscountCon {
	
	@Autowired
	HttpServletRequest request;
	
	@Resource(name = "mgrVIPLevelService")
	private MgrVIPLevelService mgrVIPLevelService;
	
	@Resource(name = "mgrCouponService")
	private MgrCouponService mgrCouponService;
	
	@RequestMapping(value = "/Mgr/MgrSetVIPDiscount", method = RequestMethod.GET)
	public String mgrSetVIPDiscountPage(){
		HttpSession session = request.getSession(false);
		if( CommonCon.hasLogin(session) ){
			String mgrID = (String)session.getAttribute(ParaName.VerificationCode);
			if( CommonCon.isMgr(mgrID) ){
				return CommonMgr.toMgrSetVIPDiscountChangePage();
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
	@RequestMapping(value = "/Mgr/GetAllVIPLevelInfos", method = RequestMethod.POST)
	public List<VIPLevelInfo> getAllVIPLevelInfos(){
		List<VIPLevelInfo> vipLevelInfos = new ArrayList<>();
		HttpSession session = request.getSession(false);
		if( CommonCon.hasLogin(session) ){
			String mgrID = (String)session.getAttribute(ParaName.VerificationCode);
			if( CommonCon.isMgr(mgrID) ){
				vipLevelInfos = mgrVIPLevelService.getAllVIPLevelInfos();
			}
		}
		return vipLevelInfos;
	}
	
	@ResponseBody
	@RequestMapping(value = "/Mgr/ChangeVIPLevelInfos", method = RequestMethod.POST)
	public Result changeVIPLevelInfos(String vipInfos){
		Result result = new Result();
		result.setResult(false);
		HttpSession session = request.getSession(false);
		if( CommonCon.hasLogin(session) ){
			String mgrID = (String)session.getAttribute(ParaName.VerificationCode);
			if( CommonCon.isMgr(mgrID) ){
				ObjectMapper mapper = new ObjectMapper();
				try{
					List<VIPLevelInfo> vipLevelInfos = mapper.readValue(vipInfos, new TypeReference<List<VIPLevelInfo>>() {});
					mgrVIPLevelService.setVIPLevelInfo(vipLevelInfos);
					result.setResult(true);
				}catch( IOException e ){
					e.printStackTrace();
				}
			}
		}
		if( !result.getResult() && result.getMessage().length()==0 ){
			result.setMessage(ParaName.message_failToChange);
		}
		return result;
	}
	
	@ResponseBody
	@RequestMapping(value = "/Mgr/GetAllCouponInfos", method = RequestMethod.POST)
	public List<CouponInfo> getAllCouponInfos(){
		List<CouponInfo> couponInfos = new ArrayList<>();
		HttpSession session = request.getSession(false);
		if( CommonCon.hasLogin(session) ){
			String mgrID = (String)session.getAttribute(ParaName.VerificationCode);
			if( CommonCon.isMgr(mgrID) ){
				couponInfos = mgrCouponService.getAllCouponInfos();
			}
		}
		return couponInfos;
	}
	
	@ResponseBody
	@RequestMapping(value = "/Mgr/AddNewCoupon", method = RequestMethod.POST)
	public Result addNewCoupon(@ModelAttribute("couponInfo") CouponInfo couponInfo){
		Result result = new Result();
		result.setResult(false);
		HttpSession session = request.getSession(false);
		if( CommonCon.hasLogin(session) ){
			String mgrID = (String)session.getAttribute(ParaName.VerificationCode);
			if( CommonCon.isMgr(mgrID) ){
				String couponID = mgrCouponService.addNewCoupon(couponInfo);
				result.setResult(true);
				result.setMessage(couponID);
			}
		}
		if( !result.getResult() && result.getMessage().length()==0 ){
			result.setMessage(ParaName.message_ownNoAuthority);
		}
		return result;
	}
	
	@ResponseBody
	@RequestMapping(value = "/Mgr/DeleteCoupon", method = RequestMethod.POST)
	public Result deleteCoupon(String couponID){
		Result result = new Result();
		result.setResult(false);
		HttpSession session = request.getSession(false);
		if( CommonCon.hasLogin(session) ){
			String mgrID = (String)session.getAttribute(ParaName.VerificationCode);
			if( CommonCon.isMgr(mgrID) ){
				mgrCouponService.deleteCoupon(couponID);
				result.setResult(true);
			}
		}
		if( !result.getResult() && result.getMessage().length()==0 ){
			result.setMessage(ParaName.message_ownNoAuthority);
		}
		return result;
	}
	
}
