package tickets.controller.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import tickets.controller.CommonCon;
import tickets.daoImpl.ParaName;
import tickets.model.Result;
import tickets.model.mgr.CouponInfo;
import tickets.model.user.UserCoupon;
import tickets.service.user.UserCouponService;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.List;

@Controller
public class UserCouponCon {
	
	@Autowired
	private HttpServletRequest request;
	
	@Resource(name = "userCouponService")
	private UserCouponService userCouponService;
	
	@RequestMapping(value = "/User/UserCoupon", method = RequestMethod.GET)
	public String userCoupon(){
		HttpSession session = request.getSession(false);
		if( CommonCon.hasLogin(session) ){
			String email = (String)session.getAttribute(ParaName.VerificationCode);
			if( CommonCon.isUser(email) ){
				return CommonUser.toUserCouponPage();
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
	@RequestMapping(value = "/User/GetAllUserCoupons", method = RequestMethod.POST)
	public List<UserCoupon> getAllUserCoupons(){
		List<UserCoupon> userCoupons = new ArrayList<>();
		HttpSession session = request.getSession(false);
		if( CommonCon.hasLogin(session) ){
			String email = (String)session.getAttribute(ParaName.VerificationCode);
			if( CommonCon.isUser(email) ){
				userCoupons = userCouponService.getAllUserCoupons(email);
			}
		}
		return userCoupons;
	}
	
	@ResponseBody
	@RequestMapping(value = "/User/GetAllCouponInfosExchanged", method = RequestMethod.POST)
	public List<CouponInfo> getAllCouponInfosExchanged(){
		List<CouponInfo> couponInfos = new ArrayList<>();
		HttpSession session = request.getSession(false);
		if( CommonCon.hasLogin(session) ){
			String email = (String)session.getAttribute(ParaName.VerificationCode);
			if( CommonCon.isUser(email) ){
				couponInfos = userCouponService.getAllCouponInfosExchanged();
			}
		}
		return couponInfos;
	}
	
	@ResponseBody
	@RequestMapping(value = "/User/ExchangeCoupon", method = RequestMethod.POST)
	public Result exchangeCoupon(String couponID){
		Result result = new Result();
		result.setResult(false);
		String message = "";
		HttpSession session = request.getSession(false);
		if( CommonCon.hasLogin(session) ){
			String email = (String)session.getAttribute(ParaName.VerificationCode);
			if( CommonCon.isUser(email) ){
				if( userCouponService.exchangeCoupon(email, couponID) ){
					result.setResult(true);
					message = "恭喜兑换成功!";
					result.setMessage(message);
				}
				else {
					message = "积分不足，兑换失败！";
					result.setMessage(message);
				}
			}
		}
		if( !result.getResult() && message.length()==0 ){
			message = ParaName.message_ownNoAuthority;
			result.setMessage(message);
		}
		return result;
	}
	
}
