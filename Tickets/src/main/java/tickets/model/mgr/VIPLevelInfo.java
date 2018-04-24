package tickets.model.mgr;

public class VIPLevelInfo {
	
	private String vipLevel = "";
	private int percent = 0;
	private int point = 0;
	
	public String getVipLevel(){
		return vipLevel;
	}
	
	public void setVipLevel(String vipLevel){
		this.vipLevel = vipLevel;
	}
	
	public int getPercent(){
		return percent;
	}
	
	public void setPercent(int percent){
		this.percent = percent;
	}
	
	public int getPoint(){
		return point;
	}
	
	public void setPoint(int point){
		this.point = point;
	}
	
}
