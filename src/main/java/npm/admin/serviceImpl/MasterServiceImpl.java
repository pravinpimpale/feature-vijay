package npm.admin.serviceImpl;

import java.util.List;
import java.util.Map;

import org.json.JSONArray;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import npm.admin.beans.ADConfigMasterBean;
import npm.admin.beans.CompanyMasterBean;
import npm.admin.beans.EmailConfigMasterBean;
import npm.admin.beans.IcmpMasterBean;
import npm.admin.beans.LocationMasterBean;
import npm.admin.beans.NwIpScanBean;
import npm.admin.beans.UserMasterBean;
import npm.admin.dao.MasterDao;
import npm.admin.service.MasterService;
import npm.model.CompanyMasterModel;
import npm.model.IcmpConfigModel;
import npm.model.LocationMasterModel;
import npm.model.NWIPScanModel;
import npm.model.Network_Discovery;
import npm.model.UserMasterModel;

@Service
public class MasterServiceImpl implements MasterService {
	
	@Autowired
	MasterDao dao;
	

	public String addUser(String username, String password,String email,String mobileNo, String name, String confirmPassword, String role, String department) {
		// TODO Auto-generated method stub
		
		return dao.addUser(username, password, email, mobileNo, name, confirmPassword, role, department);
		
	}
	public String updateUser(long id,String username, String password,String email,String mobileNo, String name, String confirmPassword, String role, String department) {
		// TODO Auto-generated method stub
		
		return dao.updateUser(id, username, password, email, mobileNo, name, confirmPassword, role, department);
		
	}
	
	public List<UserMasterModel> viewUsers() {
		// TODO Auto-generated method stub
		return dao.viewUsers();
	}
	
	public String deleteUser(long id) {
		// TODO Auto-generated method stub
		
		return dao.deleteUser(id);
		
	}
	
	public UserMasterBean editUser(long id) {
		// TODO Auto-generated method stub
		return dao.editUser(id);
	}
	
	
	//ICMP Configuration
	public String addIcmp(String packetSize, String packetCount,String poolingTime,String timeout, String ttl, String customerName, String location) {
		// TODO Auto-generated method stub
		
		return dao.addIcmp(packetSize, packetCount, poolingTime, timeout, ttl, customerName, location);
		
	}
	
	
	public List<IcmpConfigModel> viewIcmp() {
		// TODO Auto-generated method stub
		return dao.viewIcmp();
	}
	
	public String deleteIcmp(long id) {
		// TODO Auto-generated method stub
		
		return dao.deleteIcmp(id);
		
	}
	
	public IcmpMasterBean editIcmp(long id) {
		// TODO Auto-generated method stub
		return dao.editIcmp(id);
	}
	
	public String updateIcmp(long id,String packetSize, String packetCount,String poolingTime,String timeout, String ttl, String customerName, String location) {
		// TODO Auto-generated method stub
		
		return dao.updateIcmp(id, packetSize, packetCount, poolingTime, timeout, ttl, customerName, location);
		
	}
	
	
	
	//Company Master
	
	public String addCompany(String companyName, String address,String email,String number) {
		// TODO Auto-generated method stub
		
		return dao.addCompany(companyName, address, address, number);
		
	}
	
	public List<CompanyMasterModel> viewCompanyMaster() {
		// TODO Auto-generated method stub
		return dao.viewCompanyMaster();
	}
	
	public String deleteCompanyMaster(long id) {
		// TODO Auto-generated method stub
		
		return dao.deleteCompanyMaster(id);
		
	}
	
	public CompanyMasterBean editCompanyMaster(long id) {
		// TODO Auto-generated method stub
		return dao.editCompanyMaster(id);
	}
	
	public String updateCompanyMaster(long id,String companyName, String address,String email,String number) {
		// TODO Auto-generated method stub
		
		return dao.updateCompanyMaster(id, companyName, address, address, number);
		
	}
	
	//Location Master
	public String addlocationMaster(String companyName, String location) {
		// TODO Auto-generated method stub
		
		return dao.addlocationMaster(companyName, location);
		
	}
	
	public List<LocationMasterModel> viewLocationMaster() {
		// TODO Auto-generated method stub
		return dao.viewLocationMaster();
	}
	
	public String deleteLocationMaster(long id) {
		// TODO Auto-generated method stub
		
		return dao.deleteLocationMaster(id);
		
	}
	
	public LocationMasterBean editLocationMaster(long id) {
		// TODO Auto-generated method stub
		return dao.editLocationMaster(id);
	}
	
	public String updateLocationMaster(String companyName, String location, long id) {
		// TODO Auto-generated method stub
		
		return dao.updateLocationMaster(companyName, location, id);
		
	}
	
	public Map<String, String> getCompanyMap()
	{
		return dao.getCompanyMap();
		
	}
	
	
	//Network Discovery
	//IP SCAN
	public Map<String, String> getGroupMap()
	{
		return dao.getGroupMap();
	}
	
	public String addIpScan(String groupName, String ipScan, String endIpScan) {
		// TODO Auto-generated method stub
		
		return dao.addIpScan(groupName, ipScan, endIpScan);
		
	}
	
	public List<NWIPScanModel> viewIpScan() {
		// TODO Auto-generated method stub
		return dao.viewIpScan();
	}
	
	public String deleteIpScan(long id) {
		// TODO Auto-generated method stub
		
		return dao.deleteIpScan(id);
		
	}
	
	public NwIpScanBean editIpScan(long id) {
		// TODO Auto-generated method stub
		return dao.editIpScan(id);
	}
	
	public String updateIpScan(String groupName, String ipScan, String endIpScan, long id) {
		// TODO Auto-generated method stub
		
		return dao.updateIpScan(groupName, ipScan, endIpScan, id);
		
	}
	
	/* Add Group */
	public String addGroup(String groupName) {
		// TODO Auto-generated method stub
		return dao.addGroup(groupName);
	}

	/* Add Group Listing */
	public JSONArray groupListing() {

		JSONArray jsonarray_dao_output = dao.groupListing();

		return jsonarray_dao_output;
	}

	/* Delete Group */
	public String deleteGroup(long id) {
		// TODO Auto-generated method stub
		return dao.deleteGroup(id);
	}

	/* Add ServiceProvider */
	public String addServiceProvider(String serviceProviderName) {
		// TODO Auto-generated method stub
		return dao.addServiceProvider(serviceProviderName);
	}

	/* Add Service Provider Listing */
	public JSONArray serviceProviderListing() {

		JSONArray jsonarray_dao_output = dao.serviceProviderListing();

		return jsonarray_dao_output;
	}

	/* Delete Service Provider */
	public String deleteServiceProvider(long id) {
		// TODO Auto-generated method stub
		return dao.deleteServiceProvider(id);
	}

	/* Add MakeModel */
	public String addMakeModel(String makeModelName) {
		// TODO Auto-generated method stub
		return dao.addMakeModel(makeModelName);
	}

	/* Add MakeModel Listing */
	public JSONArray makeModelListing() {

		JSONArray jsonarray_dao_output = dao.makeModelListing();

		return jsonarray_dao_output;
	}

	/* Delete Make & Model */
	public String deleteMakeModel(long id) {
		// TODO Auto-generated method stub
		return dao.deleteMakeModel(id);
	}

	/* Add MakeModel */
	public String addDeviceType(String deviceTypeName) {
		// TODO Auto-generated method stub
		return dao.addDeviceType(deviceTypeName);
	}

	/* Add Device Type Listing */
	public JSONArray deviceTypeListing() {

		JSONArray jsonarray_dao_output = dao.deviceTypeListing();

		return jsonarray_dao_output;
	}

	/* Delete Device Type */
	public String deleteDeviceType(long id) {
		// TODO Auto-generated method stub
		return dao.deleteDeviceType(id);
	}

	/* Add AD Configuration */
	public String addADConfig(String ldapServerName, String username, String logonDomain, String password,
			long serverPort) {
		// TODO Auto-generated method stub

		return dao.addADConfig(ldapServerName, username, logonDomain, password, serverPort);

	}

	/* Add AD Configuration Listing */
	public JSONArray adConfigListing() {

		JSONArray jsonarray_dao_output = dao.adConfigListing();

		return jsonarray_dao_output;
	}

	/* Delete AD Configuration */
	public String deleteADConfig(long id) {
		// TODO Auto-generated method stub
		return dao.deleteADConfig(id);
	}

	/* Edit AD Configuration */
	public ADConfigMasterBean editADConfig(long id) {
		// TODO Auto-generated method stub
		return dao.editADConfig(id);
	}

	/* Update AD Configuration */
	public String updateADConfig(String ldapServerName, String username, String logonDomain, String password,
			long serverPort, long id) {
		// TODO Auto-generated method stub

		return dao.updateADConfig(ldapServerName, username, logonDomain, password, serverPort, id);

	}

	/* Add Email Configuration */
	public String addEmailConfig(String emailId, long port, String smtpServer, long timeout, String isSslTls,
			String username, String isSmtpAuth, String password) {
		// TODO Auto-generated method stub

		return dao.addEmailConfig(emailId, port, smtpServer, timeout, isSslTls, username, isSmtpAuth, password);

	}

	/* Add Email Configuration Listing */
	public JSONArray emailConfigListing() {

		JSONArray jsonarray_dao_output = dao.emailConfigListing();

		return jsonarray_dao_output;
	}

	/* Delete Email Configuration */
	public String deleteEmailConfig(long id) {
		// TODO Auto-generated method stub
		return dao.deleteEmailConfig(id);
	}

	/* Edit Email Configuration */
	public EmailConfigMasterBean editEmailConfig(long id) {
		// TODO Auto-generated method stub
		return dao.editEmailConfig(id);
	}

	/* Add SNMP Configuration */
	public String addSNMPConfig(String name, String description, String version, String community, String username,
			String authProtocol, String context, String authPassword, String encryptProtocol, long port,
			String encryptPassword, long timeout, long retries) {
		// TODO Auto-generated method stub

		return dao.addSNMPConfig(name, description, version, community, username, authProtocol, context, authPassword,
				encryptProtocol, port, encryptPassword, timeout, retries);

	}

	/* Add SNMP Configuration Listing */
	public JSONArray snmpConfigListing() {

		JSONArray jsonarray_dao_output = dao.snmpConfigListing();

		return jsonarray_dao_output;
	}

	/* Delete SNMP Configuration */
	public String deleteSNMPConfig(long id) {
		// TODO Auto-generated method stub
		return dao.deleteSNMPConfig(id);
	}
	
	public List<Network_Discovery> discoveredNetwork() {
		// TODO Auto-generated method stub
		return dao.discoveredNetwork();
	}
	
}
