package tickets.daoImpl.venue;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import tickets.dao.venue.VenueBaseInfoDao;
import tickets.daoImpl.DaoHelperImpl;
import tickets.daoImpl.ParaName;
import tickets.model.venue.VenueBaseInfo;
import tickets.model.venue.VenueBaseInfoChange;
import tickets.model.venue.VenueBaseInfoRedundancy;
import tickets.rowMapper.venue.VenueIDRowMapper;
import tickets.rowMapper.venue.VenueInfoChangeRowMapper;
import tickets.rowMapper.venue.VenueInfoRowMapper;

import java.util.ArrayList;
import java.util.List;

@Repository( "venueBaseInfoDao" )
public class VenueBaseInfoDaoImpl implements VenueBaseInfoDao {
	
	private static JdbcTemplate jdbcTemplate = DaoHelperImpl.getJdbcTemplate();
	
	@Override
	public void addVenueInfo(VenueBaseInfo venueBaseInfo){
		String venueID = venueBaseInfo.getVenueID();
		String province = venueBaseInfo.getProvince();
		String city = venueBaseInfo.getCity();
		String address = venueBaseInfo.getAddress();
		String telephone = venueBaseInfo.getTelephone();
		Boolean isChanging = false;
		String infoSql = "INSERT INTO " + ParaName.Table_venueBaseInfo + " VALUES (?,?,?,?,?,?)";
		jdbcTemplate.update(infoSql, venueID,province, city, address, telephone, isChanging);
	}
	
	@Override
	public List<VenueBaseInfo> selectAllUnconfirmedVenues(){
		String sql = "SELECT * FROM " + ParaName.Table_venueBaseInfo + " WHERE venueID IN " +
				"( SELECT venueID FROM " + ParaName.Table_venueAccount + " WHERE isConfirmed=FALSE )";
		List<VenueBaseInfo> unconfirmedVenues = jdbcTemplate.query(sql, new VenueInfoRowMapper());
		return unconfirmedVenues;
	}
	
	@Override
	public VenueBaseInfo selectVenueInfo(String venueID){
		String sql = "SELECT * FROM " + ParaName.Table_venueBaseInfo + " WHERE venueID=?";
		VenueBaseInfo venueBaseInfo = jdbcTemplate.queryForObject(sql, new VenueInfoRowMapper(), venueID);
		return venueBaseInfo;
	}
	
	@Override
	public boolean preUpdateVenueInfo(VenueBaseInfoChange venueInfo){
		String allVenueIDSql = "SELECT venueID FROM " + ParaName.Table_venueBaseInfoChange;
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
			String sql = "INSERT INTO " + ParaName.Table_venueBaseInfoChange + " VALUES(?,?,?,?,?)";
			jdbcTemplate.update(sql, venueID, province, city, address, telephone);
			
			updateVenueInfoIsChanging(venueID, true);
			return true;
		}
	}
	
	@Override
	public List<VenueBaseInfoChange> selectAllVenueInfoChanges(){
		String sql = "SELECT * FROM " + ParaName.Table_venueBaseInfoChange;
		List<VenueBaseInfoChange> venueInfos = jdbcTemplate.query(sql, new VenueInfoChangeRowMapper());
		return venueInfos;
	}
	
	@Override
	public List<VenueBaseInfoRedundancy> selectAllVenueInfoRedundancies(){
		List<VenueBaseInfoChange> venueBaseInfoChanges = selectAllVenueInfoChanges();
		for( VenueBaseInfoChange venueBaseInfoChange : venueBaseInfoChanges ){
			System.out.println( venueBaseInfoChange.getVenueID() );
			System.out.println( venueBaseInfoChange.getProvince() + venueBaseInfoChange.getCity() );
			System.out.println( venueBaseInfoChange.getAddress() );
			System.out.println( venueBaseInfoChange.getTelephone() );
			System.out.println();
		}
		
		String sql = "SELECT * FROM " + ParaName.Table_venueBaseInfo + " WHERE isChanging=true";
		List<VenueBaseInfo> venueBaseInfos = jdbcTemplate.query(sql, new VenueInfoRowMapper());
		
		List<VenueBaseInfoRedundancy> venueInfoRedundancies = new ArrayList<>();
		for( VenueBaseInfoChange venueBaseInfoChange : venueBaseInfoChanges ){
			for( int i = 0; i< venueBaseInfos.size(); i++ ){
				VenueBaseInfo venueBaseInfo = venueBaseInfos.get(i);
				if( venueBaseInfo.getVenueID().equals(venueBaseInfoChange.getVenueID()) ){
					VenueBaseInfoRedundancy venueBaseInfoRedundancy = new VenueBaseInfoRedundancy();
					venueBaseInfoRedundancy.setVenueBaseInfo(venueBaseInfo);
					venueBaseInfoRedundancy.setVenueBaseInfoChange(venueBaseInfoChange);
					venueInfoRedundancies.add(venueBaseInfoRedundancy);
					venueBaseInfos.remove(venueBaseInfo);
					break;
				}
			}
		}
		return venueInfoRedundancies;
	}
	
	@Override
	public boolean updateVenueInfo(String venueID){
		String getVenueInfoChangeSql =
				"SELECT * FROM " + ParaName.Table_venueBaseInfoChange + " WHERE venueID=?";
		VenueBaseInfoChange venueInfo = jdbcTemplate.queryForObject(
				getVenueInfoChangeSql, new VenueInfoChangeRowMapper(), venueID);
		
		String province = venueInfo.getProvince();
		String city = venueInfo.getCity();
		String address = venueInfo.getAddress();
		String telephone = venueInfo.getTelephone();
		String updateVenueInfoSql = "UPDATE " + ParaName.Table_venueBaseInfo +
				" SET province=?, city=?, address=?, telephone=?, isChanging=? WHERE venueID=?";
		jdbcTemplate.update(updateVenueInfoSql, province, city, address, telephone, false, venueID);
		
		String deleteVenueInfoChangeSql =
				"DELETE FROM " + ParaName.Table_venueBaseInfoChange + " WHERE venueID=?";
		jdbcTemplate.update(deleteVenueInfoChangeSql, venueID);
		
		return true;
	}
	
	@Override
	public void updateVenueInfoIsChanging(String venueID, boolean isChanging){
		String updateIsChangingSql =
				"UPDATE " + ParaName.Table_venueBaseInfo + " SET isChanging=? WHERE venueID=?";
		jdbcTemplate.update(updateIsChangingSql, isChanging, venueID);
	}
	
}
