package tickets.daoImpl.mgr;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import tickets.dao.DaoHelper;
import tickets.dao.mgr.MgrVIPLevelDao;
import tickets.daoImpl.DaoHelperImpl;
import tickets.daoImpl.ParaName;
import tickets.model.mgr.VIPLevelInfo;
import tickets.rowMapper.mgr.VIPLevelInfoRowMapper;

import java.util.ArrayList;
import java.util.List;

@Repository("mgrVIPLevelDao")
public class MgrVIPLevelDaoImpl implements MgrVIPLevelDao {
	
	private static JdbcTemplate jdbcTemplate = DaoHelperImpl.getJdbcTemplate();
	
	@Override
	public void insertVIPLevelInfo(List<VIPLevelInfo> vipLevelInfos){
		System.out.println("insertVIPLevelInfo Dao");
		List<VIPLevelInfo> vipLevelInfosExist = selectAllVIPLevelInfos();
		List<String> vipLevels = new ArrayList<>();
		for( VIPLevelInfo vipLevelInfo : vipLevelInfosExist ){
			vipLevels.add(vipLevelInfo.getVipLevel());
		}
		
		deletedAllVIPLevelInfos();
		
		for( VIPLevelInfo vipLevelInfo : vipLevelInfos ){
			String vipLevel = vipLevelInfo.getVipLevel();
			int percent = vipLevelInfo.getPercent();
			int point = vipLevelInfo.getPoint();
			if( vipLevels.contains(vipLevel) ){
				System.out.println("update vip level " + vipLevel);
				String sql = "UPDATE " + ParaName.Table_vipInfo + " SET percent=?, point=?, isDeleted=FALSE WHERE vipLevel=?";
				jdbcTemplate.update(sql, percent, point, vipLevel);
			}
			else {
				System.out.println("insert vip level " + vipLevel);
				final boolean isDeleted = false;
				String sql = "INSERT INTO " + ParaName.Table_vipInfo + " VALUES (?,?,?,?)";
				jdbcTemplate.update(sql, vipLevel, percent, point, isDeleted);
			}
		}
	}
	
	@Override
	public List<VIPLevelInfo> selectAllVIPLevelInfos(){
		String sql = "SELECT * FROM " + ParaName.Table_vipInfo + " WHERE isDeleted=FALSE ";
		List<VIPLevelInfo> vipLevelInfos = jdbcTemplate.query(sql, new VIPLevelInfoRowMapper());
		return vipLevelInfos;
	}
	
	private void deletedAllVIPLevelInfos(){
		String sql = "UPDATE " + ParaName.Table_vipInfo + " SET isDeleted=TRUE ";
		jdbcTemplate.update(sql);
	}
	
}
