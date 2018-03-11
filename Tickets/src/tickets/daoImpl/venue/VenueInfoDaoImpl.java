package tickets.daoImpl.venue;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import tickets.dao.venue.VenueInfoDao;
import tickets.daoImpl.DaoHelperImpl;
import tickets.daoImpl.ParaName;
import tickets.model.VenueInfo;
import tickets.model.VenueInfoChange;
import tickets.model.VenueInfoRedundancy;
import tickets.rowMapper.VenueIDRowMapper;
import tickets.rowMapper.VenueInfoChangeRowMapper;
import tickets.rowMapper.VenueInfoRowMapper;

import java.util.ArrayList;
import java.util.List;

@Repository("venueInfoDao")
public class VenueInfoDaoImpl implements VenueInfoDao {
	
	private static JdbcTemplate jdbcTemplate = DaoHelperImpl.getJdbcTemplate();
	
	@Override
	public void addVenueInfo(VenueInfo venueInfo){
		String venueID = venueInfo.getVenueID();
		String province = venueInfo.getProvince();
		String city = venueInfo.getCity();
		String address = venueInfo.getAddress();
		String telephone = venueInfo.getTelephone();
		Boolean isChanging = false;
		String infoSql = "INSERT INTO " + ParaName.Table_venueInfo + " VALUES (?,?,?,?,?,?)";
		jdbcTemplate.update(infoSql, venueID,province, city, address, telephone, isChanging);
	}
	
	@Override
	public List<VenueInfo> selectAllUnconfirmedVenues(){
		String sql = "SELECT * FROM " + ParaName.Table_venueInfo + " WHERE venueID IN " +
				"( SELECT venueID FROM " + ParaName.Table_venueAccount + " WHERE isConfirmed=FALSE )";
		List<VenueInfo> unconfirmedVenues = jdbcTemplate.query(sql, new VenueInfoRowMapper());
		return unconfirmedVenues;
	}
	
	@Override
	public VenueInfo selectVenueInfo(String venueID){
		String sql = "SELECT * FROM " + ParaName.Table_venueInfo + " WHERE venueID=?";
		VenueInfo venueInfo = jdbcTemplate.queryForObject(sql, new VenueInfoRowMapper(), venueID);
		return venueInfo;
	}
	
	@Override
	public boolean preUpdateVenueInfo(VenueInfoChange venueInfo){
		String allVenueIDSql = "SELECT venueID FROM " + ParaName.Table_venueInfoChange;
		List<String> venueIDs = jdbcTemplate.query(allVenueIDSql, new VenueIDRowMapper());
		if( venueIDs.contains(venueInfo.getVenueID()) ){
			updateVenueInfoIsChanging(venueInfo.getVenueID(), true);
			return false;
		}
		else {
			String venueID = venueInfo.getVenueID();
			String province = venueInfo.getProvince();
			String city = venueInfo.getCity();
			String address = venueInfo.getAddress();
			String telephone = venueInfo.getTelephone();
			String sql = "INSERT INTO " + ParaName.Table_venueInfoChange + " VALUES(?,?,?,?,?)";
			jdbcTemplate.update(sql, venueID, province, city, address, telephone);
			
			updateVenueInfoIsChanging(venueID, true);
			return true;
		}
	}
	
	@Override
	public List<VenueInfoChange> selectAllVenueInfoChanges(){
		String sql = "SELECT * FROM " + ParaName.Table_venueInfoChange;
		List<VenueInfoChange> venueInfos = jdbcTemplate.query(sql, new VenueInfoChangeRowMapper());
		return venueInfos;
	}
	
	@Override
	public List<VenueInfoRedundancy> selectAllVenueInfoRedundancies(){
		List<VenueInfoChange> venueInfoChanges = selectAllVenueInfoChanges();
		for( VenueInfoChange venueInfoChange : venueInfoChanges ){
			System.out.println( venueInfoChange.getVenueID() );
			System.out.println( venueInfoChange.getProvince() + venueInfoChange.getCity() );
			System.out.println( venueInfoChange.getAddress() );
			System.out.println( venueInfoChange.getTelephone() );
			System.out.println();
		}
		
		String sql = "SELECT * FROM " + ParaName.Table_venueInfo + " WHERE isChanging=true";
		List<VenueInfo> venueInfos = jdbcTemplate.query(sql, new VenueInfoRowMapper());
		
		List<VenueInfoRedundancy> venueInfoRedundancies = new ArrayList<>();
		for( VenueInfoChange venueInfoChange : venueInfoChanges ){
			for( int i=0; i<venueInfos.size(); i++ ){
				VenueInfo venueInfo = venueInfos.get(i);
				if( venueInfo.getVenueID().equals(venueInfoChange.getVenueID()) ){
					VenueInfoRedundancy venueInfoRedundancy = new VenueInfoRedundancy();
					venueInfoRedundancy.setVenueInfo(venueInfo);
					venueInfoRedundancy.setVenueInfoChange(venueInfoChange);
					venueInfoRedundancies.add(venueInfoRedundancy);
					venueInfos.remove( venueInfo );
					break;
				}
			}
		}
		
		for( VenueInfoRedundancy venueInfoRedundancy : venueInfoRedundancies ){
			VenueInfo venueInfo = venueInfoRedundancy.getVenueInfo();
			venueInfo.show();
			VenueInfoChange venueInfoChange = venueInfoRedundancy.getVenueInfoChange();
			venueInfoChange.show();
			System.out.println();
		}
		return venueInfoRedundancies;
	}
	
	@Override
	public boolean updateVenueInfo(String venueID){
		String getVenueInfoChangeSql =
				"SELECT * FROM " + ParaName.Table_venueInfoChange + " WHERE venueID=?";
		VenueInfoChange venueInfo = jdbcTemplate.queryForObject(
				getVenueInfoChangeSql, new VenueInfoChangeRowMapper(), venueID);
		
		String province = venueInfo.getProvince();
		String city = venueInfo.getCity();
		String address = venueInfo.getAddress();
		String telephone = venueInfo.getTelephone();
		String updateVenueInfoSql = "UPDATE " + ParaName.Table_venueInfo +
				" SET province=?, city=?, address=?, telephone=?, isChanging=? WHERE venueID=?";
		jdbcTemplate.update(updateVenueInfoSql, province, city, address, telephone, false, venueID);
		
		String deleteVenueInfoChangeSql =
				"DELETE FROM " + ParaName.Table_venueInfoChange + " WHERE venueID=?";
		jdbcTemplate.update(deleteVenueInfoChangeSql, venueID);
		
		return true;
	}
	
	@Override
	public void updateVenueInfoIsChanging(String venueID, boolean isChanging){
		String updateIsChangingSql =
				"UPDATE " + ParaName.Table_venueInfo + " SET isChanging=? WHERE venueID=?";
		jdbcTemplate.update(updateIsChangingSql, isChanging, venueID);
	}
	
}
