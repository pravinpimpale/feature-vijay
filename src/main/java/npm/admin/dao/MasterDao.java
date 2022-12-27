package npm.admin.dao;

import java.util.List;
import java.util.Map;

import org.json.JSONArray;

import npm.admin.beans.ADConfigMasterBean;
import npm.admin.beans.CompanyMasterBean;
import npm.admin.beans.EmailConfigMasterBean;
import npm.admin.beans.IcmpMasterBean;
import npm.admin.beans.LocationMasterBean;
import npm.admin.beans.NwIpScanBean;
import npm.admin.beans.UserMasterBean;
import npm.model.CompanyMasterModel;
import npm.model.IcmpConfigModel;
import npm.model.LocationMasterModel;
import npm.model.NWIPScanModel;
import npm.model.Network_Discovery;
import npm.model.UserMasterModel;

public interface MasterDao {
	
	 String addUser(String username, String password,String email,String mobileNo, String name, String confirmPassword, String role, String department) ;
	 
	 String updateUser(long id,String username, String password,String email,String mobileNo, String name, String confirmPassword, String role, String department) ;
	 
	 List<UserMasterModel> viewUsers();
	 String deleteUser(long id) ;
	 

	 UserMasterBean editUser(long id);
	 
	 //ICMP Configuration
	 
	 String addIcmp(String packetSize, String packetCount,String poolingTime,String timeout, String ttl, String customerName, String location) ;
	 
	 List<IcmpConfigModel> viewIcmp();
	 
	 String deleteIcmp(long id) ;
	 
	 Map<String, String> getCompanyMap();
	 
	 IcmpMasterBean editIcmp(long id);

	 String updateIcmp(long id,String packetSize, String packetCount,String poolingTime,String timeout, String ttl, String customerName, String location) ;


	 //Company Master
	 
	 String addCompany(String companyName, String address,String email,String number) ;
	 
	 List<CompanyMasterModel> viewCompanyMaster();
	 
	 String deleteCompanyMaster(long id) ;
	 
	 CompanyMasterBean editCompanyMaster(long id);
	 
	 String updateCompanyMaster(long id,String companyName, String address,String email,String number) ;


	 //Location Master
	 String addlocationMaster(String companyName, String location) ;

	 List<LocationMasterModel> viewLocationMaster();
	 
	 String deleteLocationMaster(long id) ;
	 
	 LocationMasterBean editLocationMaster(long id);
	 
	 String updateLocationMaster(String companyName, String location, long id) ;
	 
	 //Network Discovery
	 //IP SCAN
	 Map<String, String> getGroupMap();

	 String addIpScan(String groupName, String ipScan, String endIpScan) ;
	 
	 List<NWIPScanModel> viewIpScan();
	 
	 String deleteIpScan(long id) ;
	 
	 NwIpScanBean editIpScan(long id);
	 
	 String updateIpScan(String groupName, String ipScan, String endIpScan, long id) ;
	 
	 /* Add Group */
		String addGroup(String groupName);

		/* Add Group Listing */
		public JSONArray groupListing();

		/* Delete Group */
		String deleteGroup(long id);

		/* Add Service Provider */
		String addServiceProvider(String serviceProviderName);

		/* Add Service Provider Listing */
		public JSONArray serviceProviderListing();

		/* Delete Service Provider */
		String deleteServiceProvider(long id);

		/* Add Make & Model */
		String addMakeModel(String makeModelName);

		/* Add Make & Model Listing */
		public JSONArray makeModelListing();

		/* Delete Make & Model */
		String deleteMakeModel(long id);

		/* Add Device Type */
		String addDeviceType(String deviceTypeName);

		/* Add Device Type Listing */
		public JSONArray deviceTypeListing();

		/* Delete Device Type */
		String deleteDeviceType(long id);

		/* Add AD Configuration */
		String addADConfig(String ldapServerName, String username, String logonDomain, String password, long serverPort);

		/* Add AD Configuration Listing */
		public JSONArray adConfigListing();

		/* Delete AD Configuration */
		String deleteADConfig(long id);

		/* Edit Ad Configuration */
		ADConfigMasterBean editADConfig(long id);

		/* Update AD Configuration */
		String updateADConfig(String ldapServerName, String username, String logonDomain, String password, long serverPort,
				long id);

		/* Add Email Configuration */
		String addEmailConfig(String emailId, long port, String smtpServer, long timeout, String isSslTls, String username,
				String isSmtpAuth, String password);

		/* Add Email Configuration Listing */
		public JSONArray emailConfigListing();

		/* Delete Email Configuration */
		String deleteEmailConfig(long id);

		/* Edit Email Configuration */
		EmailConfigMasterBean editEmailConfig(long id);

		/* Add SNMP Configuration */
		String addSNMPConfig(String name, String description, String version, String community, String username,
				String authProtocol, String context, String authPassword, String encryptProtocol, long port,
				String encryptPassword, long timeout, long retries);
		
		/* Add SNMP Configuration Listing */
		public JSONArray snmpConfigListing();

		/* Delete SNMP Configuration */
		String deleteSNMPConfig(long id);
		
		List<Network_Discovery> discoveredNetwork();


}
