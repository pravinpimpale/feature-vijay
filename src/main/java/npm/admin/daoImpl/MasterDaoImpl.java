package npm.admin.daoImpl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.hibernate.Criteria;
import org.hibernate.Query;
import org.json.JSONArray;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import dynamicPassword.Encryption;
import npm.admin.beans.ADConfigMasterBean;
import npm.admin.beans.CompanyMasterBean;
import npm.admin.beans.EmailConfigMasterBean;
import npm.admin.beans.IcmpMasterBean;
import npm.admin.beans.LocationMasterBean;
import npm.admin.beans.NwIpScanBean;
import npm.admin.beans.UserMasterBean;
import npm.admin.dao.MasterDao;
import npm.configuration.AbstractDao;
import npm.model.ADConfigMasterModel;
import npm.model.CompanyMasterModel;
import npm.model.DeviceTypeMasterModel;
import npm.model.EmailConfigMasterModel;
import npm.model.GroupMasterModel;
import npm.model.IcmpConfigModel;
import npm.model.LocationMasterModel;
import npm.model.MakeAndModelMasterModel;
import npm.model.NWIPScanModel;
import npm.model.Network_Discovery;
import npm.model.ServiceProviderMasterModel;
import npm.model.SnmpConfigMasterModel;
import npm.model.UserMasterModel;

@Repository
@Transactional
public class MasterDaoImpl extends AbstractDao<Integer, UserMasterModel> implements MasterDao {

	public String addUser(String username, String password,String email,String mobileNo, String name, String confirmPassword, String role, String department) {
		// TODO Auto-generated method stub
		System.out.println("Dao called");
		System.out.println("username:" + username);
		System.out.println("password:" + password);
		System.out.println("email:" + email);
		System.out.println("mobileNo:" + mobileNo);
		System.out.println("department:" +department);
		System.out.println("role:" +role);
		System.out.println("name:" +name);
		System.out.println("confirmPassword: "+confirmPassword);

		String result = null;
		try {
			Encryption encrypt = new Encryption();
			UserMasterModel user = new UserMasterModel();
			user.setUSERNAME(username);
			user.setPASSWORD(encrypt.encrypt(password));
			user.setEMAIL(email);
			user.setMOBILE_NO(mobileNo);
			user.setCONFIRM_PASSWORD(encrypt.encrypt(confirmPassword));
			user.setDEPARTMENT(department);
			user.setROLE(role);
			user.setNAME(name);
			getSession().save(user);
			result = "success";
		} catch (Exception e) {
			System.out.println("Exception:" + e);
			result = "fail";
		}

		return result;
	}

	
	public String updateUser(long id, String username, String password,String email,String mobileNo, String name, String confirmPassword, String role, String department) {
		String result =null;
		try
		{
		Query q = getSession().createQuery(
				"Update UserMasterModel set username=:username,email=:email,mobile_no=:mobileNo, name=:name, password=:password, confirm_password=:confirmPassword, role=:role, department=:department where id=:id");
		q.setParameter("username", username);
		q.setParameter("email", email);
		q.setParameter("mobileNo", mobileNo);
		q.setParameter("id", id);
		q.setParameter("password", password);
		q.setParameter("confirmPassword", confirmPassword);
		q.setParameter("role", role);
		q.setParameter("department", department);
		q.setParameter("name", name);
		int i = q.executeUpdate();
		result="success";
		} catch (Exception e) {
			System.out.println("Exception:" + e);
			result = "fail";
		}

		return result;
	}

	public List<UserMasterModel> viewUsers() {

//		Query q = getSession().createQuery("from User_Master");
//		return q.list();

		Criteria criteria = getSession().createCriteria(UserMasterModel.class);
		return criteria.list();
	}

	public String deleteUser(long id) {
		// TODO Auto-generated method stub
		System.out.println("delete user:" + id);
		String result = null;

		try {
			Query q = getSession().createQuery("Delete from UserMasterModel where ID=:id");
			q.setParameter("id", id);
			int i = q.executeUpdate();
			result = "success";
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("Exceptioon e***" + e);
			result = "fail";
		}

		return result;
	}

	public UserMasterBean editUser(long id) {
		// TODO Auto-generated method stub
		System.out.println("edit user:" + id);
		String result = null;
		UserMasterBean newObj = null;

		UserMasterBean user= new UserMasterBean();
		try {
			Query q = getSession().createQuery("from UserMasterModel where ID=:id");
			q.setParameter("id", id);
		//List<UserMasterModel> listData = q.list();
			 List<UserMasterModel> listdata=q.list();
			 user.setName(listdata.get(0).getNAME());
			 user.setMobileNo(listdata.get(0).getMOBILE_NO());
			 user.setEmail(listdata.get(0).getEMAIL());
			 user.setDepartment(listdata.get(0).getDEPARTMENT());
			 user.setRole(listdata.get(0).getROLE());
			 user.setUsername(listdata.get(0).getUSERNAME());
			 user.setId(id);
			 
			
			
			result = "success";
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("Exceptioon e***" + e);
			result = "fail";
		}

		return user;
	}

	
	//ICMP Configuration
	public String addIcmp(String packetSize, String packetCount,String poolingTime,String timeout, String ttl, String customerName, String location) {
		// TODO Auto-generated method stub
		System.out.println("Dao called");
		System.out.println("packetSize:" + packetSize);
		System.out.println("packetCount:" + packetCount);
		System.out.println("poolingTime:" + poolingTime);
		System.out.println("timeout:" + timeout);
		System.out.println("ttl:" +ttl);
		System.out.println("customerName:" +customerName);
		System.out.println("location:" +location);

		String result = null;
		try {
			IcmpConfigModel icmp = new IcmpConfigModel();
			icmp.setPACKET_SIZE(packetSize);
			icmp.setPACKET_COUNT(packetCount);
			icmp.setPOOLING_TIME(poolingTime);
			icmp.setTIMEOUT(timeout);
			icmp.setTTL(ttl);
			icmp.setCUSTOMER_NAME(customerName);
			icmp.setLOCATION(location);
			
			getSession().save(icmp);
			result = "success";
		} catch (Exception e) {
			System.out.println("Exception:" + e);
			result = "fail";
		}

		return result;
	}
	
	public Map<String, String> getCompanyMap()
	{
		Map<String, String>  companyMap=new HashMap<String, String>();
		try {
			Query q = getSession().createQuery("from CompanyMasterModel ");

			 List<CompanyMasterModel> listdata=q.list();
			
			 for(CompanyMasterModel company : listdata)
			 {
				 companyMap.put(company.getCOMPANY_NAME(), company.getCOMPANY_NAME());
			 }
			
			
		} catch (Exception e) {
			
			System.out.println("Exceptioon e***" + e);
		
		}
		
		return companyMap;
	}
	
	public List<IcmpConfigModel> viewIcmp() {

		Criteria criteria = getSession().createCriteria(IcmpConfigModel.class);
		return criteria.list();
	}
	
	public String deleteIcmp(long id) {
		// TODO Auto-generated method stub
		System.out.println("delete user:" + id);
		String result = null;

		try {
			Query q = getSession().createQuery("Delete from IcmpConfigModel where ID=:id");
			q.setParameter("id", id);
			int i = q.executeUpdate();
			result = "success";
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("Exceptioon e***" + e);
			result = "fail";
		}

		return result;
	}
	
	public IcmpMasterBean editIcmp(long id) {
		// TODO Auto-generated method stub
		System.out.println("edit Icmp:" + id);
		String result = null;
		IcmpMasterBean newObj = null;

		IcmpMasterBean icmp= new IcmpMasterBean();
		try {
			Query q = getSession().createQuery("from IcmpConfigModel where ID=:id");
			q.setParameter("id", id);
		//List<UserMasterModel> listData = q.list();
			 List<IcmpConfigModel> listdata=q.list();
			 icmp.setPacketSize(listdata.get(0).getPACKET_SIZE());
			 icmp.setPacketCount(listdata.get(0).getPACKET_COUNT());
			 icmp.setPoolingTime(listdata.get(0).getPOOLING_TIME());
			 icmp.setTimeout(listdata.get(0).getTIMEOUT());
			 icmp.setTtl(listdata.get(0).getTTL());
			 icmp.setCustomerName(listdata.get(0).getCUSTOMER_NAME());
			 icmp.setLocation(listdata.get(0).getLOCATION());
			 icmp.setId(id);
			 
			
			
			result = "success";
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("Exceptioon e***" + e);
			result = "fail";
		}

		return icmp;
	}
	
	public String updateIcmp(long id, String packetSize, String packetCount,String poolingTime,String timeout, String ttl, String customerName, String location) {
		String result =null;
		try
		{
		Query q = getSession().createQuery(
				"Update IcmpConfigModel set packet_size=:packetSize,packet_count=:packetCount,pooling_time=:poolingTime, timeout=:timeout, ttl=:ttl, customer_name=:customerName, location=:location where id=:id");
		q.setParameter("packetSize", packetSize);
		q.setParameter("packetCount", packetCount);
		q.setParameter("poolingTime", poolingTime);
		q.setParameter("id", id);
		q.setParameter("timeout", timeout);
		q.setParameter("ttl", ttl);
		q.setParameter("customerName", customerName);
		q.setParameter("location", location);
		int i = q.executeUpdate();
		result="success";
		} catch (Exception e) {
			System.out.println("Exception:" + e);
			result = "fail";
		}

		return result;
	}


	//Company Master
	public String addCompany(String companyName, String address,String email,String number) {
		// TODO Auto-generated method stub
		System.out.println("Dao called");
		System.out.println("companyName:" + companyName);
		System.out.println("address:" + address);
		System.out.println("email:" + email);
		System.out.println("number:" + number);
		String result = null;
		try {
			CompanyMasterModel companymaster = new CompanyMasterModel();
			companymaster.setCOMPANY_NAME(companyName);
			companymaster.setADDRESS(address);
			companymaster.setEMAIL(email);
			companymaster.setNUMBER(number);
		
			getSession().save(companymaster);
			result = "success";
		} catch (Exception e) {
			System.out.println("Exception:" + e);
			result = "fail";
		}

		return result;
	}
	
	public List<CompanyMasterModel> viewCompanyMaster() {

		Criteria criteria = getSession().createCriteria(CompanyMasterModel.class);
		return criteria.list();
	}
	
	public String deleteCompanyMaster(long id) {
		// TODO Auto-generated method stub
		System.out.println("delete company:" + id);
		String result = null;

		try {
			Query q = getSession().createQuery("Delete from CompanyMasterModel where ID=:id");
			q.setParameter("id", id);
			int i = q.executeUpdate();
			result = "success";
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("Exceptioon e***" + e);
			result = "fail";
		}

		return result;
	}
	
	public CompanyMasterBean editCompanyMaster(long id) {
		// TODO Auto-generated method stub
		System.out.println("edit Company Master:" + id);
		String result = null;

		CompanyMasterBean companyMaster= new CompanyMasterBean();
		try {
			Query q = getSession().createQuery("from CompanyMasterModel where ID=:id");
			q.setParameter("id", id);
		//List<UserMasterModel> listData = q.list();
			 List<CompanyMasterModel> listdata=q.list();
			 
			 companyMaster.setCompanyName(listdata.get(0).getCOMPANY_NAME());
			 companyMaster.setAddress(listdata.get(0).getADDRESS());
			 companyMaster.setEmail(listdata.get(0).getEMAIL());
			 companyMaster.setContact(listdata.get(0).getNUMBER());
			 companyMaster.setId(id);
			 
			
			
			result = "success";
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("Exceptioon e***" + e);
			result = "fail";
		}

		return companyMaster;
	}

	public String updateCompanyMaster(long id, String companyName, String address,String email,String number) {
		String result =null;
		try
		{
		Query q = getSession().createQuery(
				"Update CompanyMasterModel set company_name=:companyName,address=:address,email=:email, number=:number where id=:id");
		q.setParameter("companyName", companyName);
		q.setParameter("address", address);
		q.setParameter("email", email);
		q.setParameter("id", id);
		q.setParameter("number", number);
		int i = q.executeUpdate();
		result="success";
		} catch (Exception e) {
			System.out.println("Exception:" + e);
			result = "fail";
		}

		return result;
	}
	
	
	//Location Master
	public String addlocationMaster(String companyName, String location) {
		// TODO Auto-generated method stub
		System.out.println("Dao called");
		System.out.println("companyName:" + companyName);
		System.out.println("address:" + location);
	
		String result = null;
		try {
			LocationMasterModel lmm = new LocationMasterModel();
			lmm.setCOMPANY_NAME(companyName);
			lmm.setLOCATION_NAME(location);
			getSession().save(lmm);
			result = "success";
		} catch (Exception e) {
			System.out.println("Exception:" + e);
			result = "fail";
		}

		return result;
	}
	
	public List<LocationMasterModel> viewLocationMaster() {

		Criteria criteria = getSession().createCriteria(LocationMasterModel.class);
		return criteria.list();
	}
	
	public String deleteLocationMaster(long id) {
		// TODO Auto-generated method stub
		System.out.println("delete location:" + id);
		String result = null;

		try {
			Query q = getSession().createQuery("Delete from LocationMasterModel where ID=:id");
			q.setParameter("id", id);
			int i = q.executeUpdate();
			result = "success";
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("Exceptioon e***" + e);
			result = "fail";
		}

		return result;
	}
	
	public LocationMasterBean editLocationMaster(long id) {
		// TODO Auto-generated method stub
		System.out.println("edit Location Master:" + id);
		String result = null;

		LocationMasterBean locationMaster = new LocationMasterBean();
		try {
			Query q = getSession().createQuery("from LocationMasterModel where ID=:id");
			q.setParameter("id", id);
		//List<UserMasterModel> listData = q.list();
			 List<LocationMasterModel> listdata=q.list();
			 
			 locationMaster.setCompanyName(listdata.get(0).getCOMPANY_NAME());
			 locationMaster.setLocation(listdata.get(0).getLOCATION_NAME());
			 locationMaster.setId(id);
			 
			
			
			result = "success";
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("Exceptioon e***" + e);
			result = "fail";
		}

		return locationMaster;
	}
	
	public String updateLocationMaster(String companyName, String location, long id) {
		String result =null;
		try
		{
		Query q = getSession().createQuery(
				"Update LocationMasterModel set company_name=:companyName,location_name=:location where id=:id");
		q.setParameter("companyName", companyName);
		q.setParameter("location", location);
		q.setParameter("id", id);
	
		int i = q.executeUpdate();
		result="success";
		} catch (Exception e) {
			System.out.println("Exception:" + e);
			result = "fail";
		}

		return result;
	}
	
	//Network Discovery
	//IP SCAN
	public Map<String, String> getGroupMap()
	{
		Map<String, String>  groupMap = new HashMap<String, String>();
		try {
			Query q = getSession().createQuery("from GroupMasterModel ");

			 List<GroupMasterModel> listdata=q.list();
			
			 for(GroupMasterModel group : listdata)
			 {
				 groupMap.put(group.getGROUP_NAME(), group.getGROUP_NAME());
			 }
			
			
		} catch (Exception e) {
			
			System.out.println("Exceptioon e***" + e);
		
		}
		
		return groupMap;
	}
	
	
	public Map<String, String> getGroupDeviceDetails()
	{
		Map<String, String>  groupMap = new HashMap<String, String>();
		try {
			Query q = getSession().createQuery("from GroupMasterModel ");

			 List<GroupMasterModel> listdata=q.list();
			
			 for(GroupMasterModel group : listdata)
			 {
				 groupMap.put(group.getGROUP_NAME(), group.getGROUP_NAME());
			 }
			
			
		} catch (Exception e) {
			
			System.out.println("Exceptioon e***" + e);
		
		}
		
		return groupMap;
	}
	
	public String addIpScan(String groupName, String ipScan, String endIpScan) {
		// TODO Auto-generated method stub
		System.out.println("Dao called");
		System.out.println("groupName:" + groupName);
		System.out.println("ipScan:" + ipScan);
	
		String result = null;
		try {
			NWIPScanModel lmm = new NWIPScanModel();
			lmm.setGROUP_NAME(groupName);
			lmm.setSTART_IP(ipScan);
			lmm.setEND_IP(endIpScan);
			getSession().save(lmm);
			result = "success";
		} catch (Exception e) {
			System.out.println("Exception:" + e);
			result = "fail";
		}

		return result;
	}
	
	public List<NWIPScanModel> viewIpScan() {

		Criteria criteria = getSession().createCriteria(NWIPScanModel.class);
		return criteria.list();
	}
	
	public String deleteIpScan(long id) {
		// TODO Auto-generated method stub
		System.out.println("delete deleteIpScan:" + id);
		String result = null;

		try {
			Query q = getSession().createQuery("Delete from NWIPScanModel where ID=:id");
			q.setParameter("id", id);
			int i = q.executeUpdate();
			result = "success";
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("Exceptioon e***" + e);
			result = "fail";
		}

		return result;
	}
	
	public NwIpScanBean editIpScan(long id) {
		// TODO Auto-generated method stub
		System.out.println("edit Location Master:" + id);
		String result = null;

		NwIpScanBean ipScan = new NwIpScanBean();
		try {
			Query q = getSession().createQuery("from NWIPScanModel where ID=:id");
			q.setParameter("id", id);
		//List<UserMasterModel> listData = q.list();
			 List<NWIPScanModel> listdata=q.list();
			 
			 ipScan.setGroupName(listdata.get(0).getGROUP_NAME());
			 ipScan.setStartIp(listdata.get(0).getSTART_IP());
			 ipScan.setEndIp(listdata.get(0).getEND_IP());
			 ipScan.setId(id);
			 
			
			
			result = "success";
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("Exceptioon e***" + e);
			result = "fail";
		}

		return ipScan;
	}
	
	public String updateIpScan(String groupName, String ipScan, String endIpScan, long id) {
		String result =null;
		try
		{
		Query q = getSession().createQuery(
				"Update NWIPScanModel set group_name=:groupName,start_ip=:ipScan, end_ip=:endIpScan where id=:id");
		q.setParameter("groupName", groupName);
		q.setParameter("ipScan", ipScan);
		q.setParameter("endIpScan", endIpScan);
		q.setParameter("id", id);
	
		int i = q.executeUpdate();
		result="success";
		} catch (Exception e) {
			System.out.println("Exception:" + e);
			result = "fail";
		}

		return result;
	}

	/* Add Group Master */
	public String addGroup(String groupName) {
		// TODO Auto-generated method stub
		System.out.println("Dao called");
		System.out.println("groupName:" + groupName);

		String result = null;
		try {
			GroupMasterModel groupMasterModel = new GroupMasterModel();
			groupMasterModel.setGROUP_NAME(groupName);
			getSession().save(groupMasterModel);
			result = "success";
		} catch (Exception e) {
			System.out.println("Exception:" + e);
			result = "fail";
		}

		return result;
	}

	/* Add Group Listing */
	public JSONArray groupListing() {

		JSONArray array = null;
		JSONArray array1 = null;
		int srno = 0;
		try {

			Criteria criteria = getSession().createCriteria(GroupMasterModel.class);

			List<GroupMasterModel> dataList = criteria.list();
			array1 = new JSONArray();
			for (GroupMasterModel groupmaster : dataList) {
				srno = srno + 1;
				array = new JSONArray();

				array.put(srno);
				array.put(groupmaster.getGROUP_NAME());
				array.put("<button class='btn btn-sm btn-danger' id='Delete'  name='Doem' onclick='deleteGroup(\""
						+ groupmaster.getID() + "\" )'><i class='fa fa-trash'></i></button>");
				array1.put(array);

			}

		} catch (Exception e) {
			e.printStackTrace();
			// TODO: handle exception
		}

		return array1;
	}

	/* Delete Group */

	public String deleteGroup(long id) {
		// TODO Auto-generated method stub
		System.out.println("delete Group:" + id);
		String result = null;

		try {
			Query q = getSession().createQuery("Delete from GroupMasterModel where ID=:id");
			q.setParameter("id", id);
			int i = q.executeUpdate();
			result = "success";
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("Exception in Delete Group" + e);
			result = "fail";
		}

		return result;
	}

	/* Add Service Provider Master */
	public String addServiceProvider(String serviceProviderName) {
		// TODO Auto-generated method stub
		// System.out.println("Dao called");
		// System.out.println("serviceProviderName:" + serviceProviderName);

		String result = null;
		try {
			ServiceProviderMasterModel serviceProviderMasterModel = new ServiceProviderMasterModel();
			serviceProviderMasterModel.setSERVICE_PROVIDER_NAME(serviceProviderName);
			getSession().save(serviceProviderMasterModel);
			result = "success";
		} catch (Exception e) {
			System.out.println("Exception:" + e);
			result = "fail";
		}

		return result;
	}

	/* Add Service Provider Listing */
	public JSONArray serviceProviderListing() {

		JSONArray array = null;
		JSONArray array1 = null;
		int srno = 0;
		try {

			Criteria criteria = getSession().createCriteria(ServiceProviderMasterModel.class);

			List<ServiceProviderMasterModel> dataList = criteria.list();
			array1 = new JSONArray();
			for (ServiceProviderMasterModel serviceprovidermaster : dataList) {
				srno = srno + 1;
				array = new JSONArray();

				array.put(srno);
				array.put(serviceprovidermaster.getSERVICE_PROVIDER_NAME());
				array.put(
						"<button class='btn btn-sm btn-danger' id='Delete'  name='Doem' onclick='deleteServiceProvider(\""
								+ serviceprovidermaster.getID() + "\" )'><i class='fa fa-trash'></i></button>");
				array1.put(array);

			}

		} catch (Exception e) {
			e.printStackTrace();
			// TODO: handle exception
		}

		return array1;
	}

	/* Delete Service Provider */

	public String deleteServiceProvider(long id) {
		// TODO Auto-generated method stub
		System.out.println("delete Service Provider:" + id);
		String result = null;

		try {
			Query q = getSession().createQuery("Delete from ServiceProviderMasterModel where ID=:id");
			q.setParameter("id", id);
			int i = q.executeUpdate();
			result = "success";
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("Exception in Delete Service Provider" + e);
			result = "fail";
		}

		return result;
	}

	/* Add Make Model Master */
	public String addMakeModel(String makeModelName) {
		// TODO Auto-generated method stub
		String result = null;
		try {
			MakeAndModelMasterModel makeAndModelMasterModel = new MakeAndModelMasterModel();
			makeAndModelMasterModel.setMAKE_MODEL_NAME(makeModelName);
			getSession().save(makeAndModelMasterModel);
			result = "success";
		} catch (Exception e) {
			System.out.println("Exception:" + e);
			result = "fail";
		}

		return result;
	}

	/* Add Make Model Listing */
	public JSONArray makeModelListing() {

		JSONArray array = null;
		JSONArray array1 = null;
		int srno = 0;
		try {

			Criteria criteria = getSession().createCriteria(MakeAndModelMasterModel.class);

			List<MakeAndModelMasterModel> dataList = criteria.list();
			array1 = new JSONArray();
			for (MakeAndModelMasterModel makeModelmaster : dataList) {
				srno = srno + 1;
				array = new JSONArray();

				array.put(srno);
				array.put(makeModelmaster.getMAKE_MODEL_NAME());
				array.put("<button class='btn btn-sm btn-danger' id='Delete'  name='Doem' onclick='deleteMakeModel(\""
						+ makeModelmaster.getID() + "\" )'><i class='fa fa-trash'></i></button>");
				array1.put(array);

			}

		} catch (Exception e) {
			e.printStackTrace();
			// TODO: handle exception
		}

		return array1;
	}

	/* Delete Make & Model */

	public String deleteMakeModel(long id) {
		// TODO Auto-generated method stub
		System.out.println("delete Make & Model:" + id);
		String result = null;

		try {
			Query q = getSession().createQuery("Delete from MakeAndModelMasterModel where ID=:id");
			q.setParameter("id", id);
			int i = q.executeUpdate();
			result = "success";
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("Exception in Delete MakeModel" + e);
			result = "fail";
		}

		return result;
	}

	/* Add Device Type Master */
	public String addDeviceType(String deviceTypeName) {
		// TODO Auto-generated method stub
		String result = null;
		try {
			DeviceTypeMasterModel deviceTypeMasterModel = new DeviceTypeMasterModel();
			deviceTypeMasterModel.setDEVICE_TYPE(deviceTypeName);
			getSession().save(deviceTypeMasterModel);
			result = "success";
		} catch (Exception e) {
			System.out.println("Exception:" + e);
			result = "fail";
		}

		return result;
	}

	/* Add Device Type Listing */
	public JSONArray deviceTypeListing() {

		JSONArray array = null;
		JSONArray array1 = null;
		int srno = 0;
		try {

			Criteria criteria = getSession().createCriteria(DeviceTypeMasterModel.class);

			List<DeviceTypeMasterModel> dataList = criteria.list();
			array1 = new JSONArray();
			for (DeviceTypeMasterModel devicetypemaster : dataList) {
				srno = srno + 1;
				array = new JSONArray();

				array.put(srno);
				array.put(devicetypemaster.getDEVICE_TYPE());
				array.put("<button class='btn btn-sm btn-danger' id='Delete'  name='Doem' onclick='deleteDeviceType(\""
						+ devicetypemaster.getID() + "\" )'><i class='fa fa-trash'></i></button>");
				array1.put(array);

			}

		} catch (Exception e) {
			e.printStackTrace();
			// TODO: handle exception
		}

		return array1;
	}

	/* Delete Device Type */

	public String deleteDeviceType(long id) {
		// TODO Auto-generated method stub
		System.out.println("delete Device Type:" + id);
		String result = null;

		try {
			Query q = getSession().createQuery("Delete from DeviceTypeMasterModel where ID=:id");
			q.setParameter("id", id);
			int i = q.executeUpdate();
			result = "success";
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("Exception in Delete Device Type" + e);
			result = "fail";
		}

		return result;
	}

	/* Add AD Configuration */

	public String addADConfig(String ldapServerName, String username, String logonDomain, String password,
			long serverPort) {
		// TODO Auto-generated method stub
		System.out.println("Dao called");
		System.out.println("ldapServerName:" + ldapServerName);
		System.out.println("username:" + username);
		System.out.println("logonDomain:" + logonDomain);
		System.out.println("password:" + password);
		System.out.println("serverPort:" + serverPort);

		String result = null;
		try {
			ADConfigMasterModel adconfig = new ADConfigMasterModel();
			adconfig.setLDAP_SERVER(ldapServerName);
			adconfig.setUSERNAME(username);
			adconfig.setLOGON_DOMAIN(logonDomain);
			adconfig.setPASSWORD(password);
			adconfig.setSERVER_PORT(serverPort);
			getSession().save(adconfig);
			result = "success";
		} catch (Exception e) {
			System.out.println("Exception:" + e);
			result = "fail";
		}

		return result;
	}

	/* Add AD Configuration Listing */
	public JSONArray adConfigListing() {

		JSONArray array = null;
		JSONArray array1 = null;
		int srno = 0;
		try {

			Criteria criteria = getSession().createCriteria(ADConfigMasterModel.class);

			List<ADConfigMasterModel> dataList = criteria.list();
			array1 = new JSONArray();
			for (ADConfigMasterModel adconfigmaster : dataList) {
				srno = srno + 1;
				array = new JSONArray();

				array.put(srno);
				array.put(adconfigmaster.getLDAP_SERVER());
				array.put(adconfigmaster.getLOGON_DOMAIN());
				array.put(adconfigmaster.getUSERNAME());
				array.put(adconfigmaster.getSERVER_PORT());
				array.put("<a href=\"editADConfig/" + adconfigmaster.getID() + "\"><i class=\"fas fa-edit\"></a>");
				array.put("<button class='btn btn-sm btn-danger' id='Delete'  name='Doem' onclick='deleteADConfig(\""
						+ adconfigmaster.getID() + "\" )'><i class='fa fa-trash'></i></button>");
				array1.put(array);

			}

		} catch (Exception e) {
			e.printStackTrace();
			// TODO: handle exception
		}

		return array1;
	}

	/* Delete AD Configuration */

	public String deleteADConfig(long id) {
		// TODO Auto-generated method stub
		System.out.println("delete AD Config:" + id);
		String result = null;

		try {
			Query q = getSession().createQuery("Delete from ADConfigMasterModel where ID=:id");
			q.setParameter("id", id);
			int i = q.executeUpdate();
			result = "success";
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("Exception in Delete AD Config" + e);
			result = "fail";
		}

		return result;
	}

	/* Edit AD Configuration */

	public ADConfigMasterBean editADConfig(long id) {
		// TODO Auto-generated method stub
		System.out.println("edit AD Config:" + id);
		String result = null;

		ADConfigMasterBean adConfig = new ADConfigMasterBean();
		try {
			Query q = getSession().createQuery("from ADConfigMasterModel where ID=:id");
			q.setParameter("id", id);
			List<ADConfigMasterModel> listdata = q.list();
			adConfig.setLdapServerName(listdata.get(0).getLDAP_SERVER());
			adConfig.setLogonDomain(listdata.get(0).getLOGON_DOMAIN());
			adConfig.setUsername(listdata.get(0).getUSERNAME());
			adConfig.setServerPort(listdata.get(0).getSERVER_PORT());
			adConfig.setId(id);

			result = "success";
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("Exceptioon e***" + e);
			result = "fail";
		}

		return adConfig;
	}

	/* Update AD Configuration */

	public String updateADConfig(String ldapServerName, String username, String logonDomain, String password,
			long serverPort, long id) {
		// TODO Auto-generated method stub

		String result = null;
		try {
			Query q = getSession().createQuery(
					"Update ADConfigMasterModel set LDAP_SERVER=:ldapServerName,USERNAME=:username,PASSWORD=:password, LOGON_DOMAIN=:logonDomain, SERVER_PORT=:serverPort where id=:id");
			q.setParameter("ldapServerName", ldapServerName);
			q.setParameter("username", username);
			q.setParameter("password", password);
			q.setParameter("logonDomain", logonDomain);
			q.setParameter("serverPort", serverPort);
			q.setParameter("id", id);
			int i = q.executeUpdate();
			result = "success";
		} catch (Exception e) {
			System.out.println("Exception:" + e);
			result = "fail";
		}

		return result;
	}

	/* Add Email Configuration */

	public String addEmailConfig(String emailId, long port, String smtpServer, long timeout, String isSslTls,
			String username, String isSmtpAuth, String password) {
		// TODO Auto-generated method stub
		System.out.println("Dao called");

		try {
			if (username.isEmpty()) {
				username = "NA";
			}
			if (password.isEmpty()) {
				password = "NA";
			}
		} catch (Exception e) {
			// TODO: handle exception
		}

		String result = null;
		try {
			EmailConfigMasterModel emailconfig = new EmailConfigMasterModel();
			emailconfig.setEMAIL_ID(emailId);
			emailconfig.setPORT(port);
			emailconfig.setSMTP_SERVER(smtpServer);
			emailconfig.setTIMEOUT(timeout);
			emailconfig.setIS_SSL_TLS(isSslTls);
			emailconfig.setUSERNAME(username);
			emailconfig.setSMTP_AUTH(isSmtpAuth);
			emailconfig.setPASSWORD(password);
			getSession().save(emailconfig);
			result = "success";
		} catch (Exception e) {
			System.out.println("Exception:" + e);
			result = "fail";
		}

		return result;
	}

	/* Add Email Configuration Listing */
	public JSONArray emailConfigListing() {

		JSONArray array = null;
		JSONArray array1 = null;
		int srno = 0;
		try {

			Criteria criteria = getSession().createCriteria(EmailConfigMasterModel.class);

			List<EmailConfigMasterModel> dataList = criteria.list();
			array1 = new JSONArray();
			for (EmailConfigMasterModel emailconfigmaster : dataList) {
				srno = srno + 1;
				array = new JSONArray();

				array.put(srno);
				array.put(emailconfigmaster.getEMAIL_ID());
				array.put(emailconfigmaster.getSMTP_SERVER());
				array.put(emailconfigmaster.getPORT());
				array.put(emailconfigmaster.getTIMEOUT());
				array.put(emailconfigmaster.getIS_SSL_TLS());
				array.put(emailconfigmaster.getSMTP_AUTH());
				array.put(emailconfigmaster.getUSERNAME());
				// array.put("<a href=\"editEmailConfig/" + emailconfigmaster.getID() + "\"><i
				// class=\"fas fa-edit\"></a>");
				array.put("<button class='btn btn-sm btn-danger' id='Delete'  name='Doem' onclick='deleteEmailConfig(\""
						+ emailconfigmaster.getID() + "\" )'><i class='fa fa-trash'></i></button>");
				array1.put(array);

			}

		} catch (Exception e) {
			e.printStackTrace();
			// TODO: handle exception
		}

		return array1;
	}

	/* Delete Email Configuration */

	public String deleteEmailConfig(long id) {
		// TODO Auto-generated method stub
		System.out.println("delete Email Config:" + id);
		String result = null;

		try {
			Query q = getSession().createQuery("Delete from EmailConfigMasterModel where ID=:id");
			q.setParameter("id", id);
			int i = q.executeUpdate();
			result = "success";
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("Exception in Delete Email Config" + e);
			result = "fail";
		}

		return result;
	}

	/* Edit Email Configuration */

	public EmailConfigMasterBean editEmailConfig(long id) {
		// TODO Auto-generated method stub
		System.out.println("edit Email Config:" + id);
		String result = null;

		EmailConfigMasterBean emailConfig = new EmailConfigMasterBean();
		try {
			Query q = getSession().createQuery("from EmailConfigMasterModel where ID=:id");
			q.setParameter("id", id);
			List<EmailConfigMasterModel> listdata = q.list();
			emailConfig.setEmailId(listdata.get(0).getEMAIL_ID());
			emailConfig.setSmtpServer(listdata.get(0).getSMTP_SERVER());
			emailConfig.setPort(listdata.get(0).getPORT());
			emailConfig.setTimeout(listdata.get(0).getTIMEOUT());
			emailConfig.setIsSslTls(String.valueOf(listdata.get(0).getIS_SSL_TLS()));
			emailConfig.setIsSmtpAuth(String.valueOf(listdata.get(0).getSMTP_AUTH()));
			emailConfig.setUsername(listdata.get(0).getUSERNAME());
			emailConfig.setPassword(listdata.get(0).getPASSWORD());
			emailConfig.setId(id);

			result = "success";
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("Exceptioon e***" + e);
			result = "fail";
		}

		return emailConfig;
	}

	/* Add SNMP Configuration */

	public String addSNMPConfig(String name, String description, String version, String community, String username,
			String authProtocol, String context, String authPassword, String encryptProtocol, long port,
			String encryptPassword, long timeout, long retries) {
		// TODO Auto-generated method stub
		System.out.println("Dao called");

		try {
			if (name.isEmpty()) {
				name = "NA";
			}
			if (description.isEmpty()) {
				description = "NA";
			}
			if (version.isEmpty()) {
				version = "NA";
			}
			if (community.isEmpty()) {
				community = "NA";
			}
			if (username.isEmpty()) {
				username = "NA";
			}
			if (authProtocol.isEmpty()) {
				authProtocol = "NA";
			}
			if (context.isEmpty()) {
				context = "NA";
			}
			if (authPassword.isEmpty()) {
				authPassword = "NA";
			}
			if (encryptProtocol.isEmpty()) {
				encryptProtocol = "NA";
			}
			if (port == 0) {
				port = 0;
			}
			if (encryptPassword.isEmpty()) {
				encryptPassword = "NA";
			}
			if (timeout == 0) {
				timeout = 0;
			}
			if (retries == 0) {
				retries = 0;
			}
		} catch (Exception e) {
			// TODO: handle exception
		}

		String result = null;
		try {
			SnmpConfigMasterModel snmpconfig = new SnmpConfigMasterModel();
			snmpconfig.setNAME(name);
			snmpconfig.setDESCRIPTION(description);
			snmpconfig.setVERSION(version);
			snmpconfig.setCOMMUNITY(community);
			snmpconfig.setUSERNAME(username);
			snmpconfig.setAUTH_PROTOCOL(authProtocol);
			snmpconfig.setCONTEXT(context);
			snmpconfig.setAUTH_PASS(authPassword);
			snmpconfig.setENCRYPT_PROTOCOL(encryptProtocol);
			snmpconfig.setPORT(port);
			snmpconfig.setENCRYPT_PASS(encryptPassword);
			snmpconfig.setTIMEOUT(timeout);
			snmpconfig.setRETRIES(retries);
			getSession().save(snmpconfig);
			result = "success";
		} catch (Exception e) {
			System.out.println("Exception:" + e);
			result = "fail";
		}

		return result;
	}
	
	/* Add SNMP Configuration Listing */
	public JSONArray snmpConfigListing() {

		JSONArray array = null;
		JSONArray array1 = null;
		int srno = 0;
		try {

			Criteria criteria = getSession().createCriteria(SnmpConfigMasterModel.class);

			List<SnmpConfigMasterModel> dataList = criteria.list();
			array1 = new JSONArray();
			for (SnmpConfigMasterModel snmpconfig : dataList) {
				srno = srno + 1;
				array = new JSONArray();

				array.put(srno);
				array.put(snmpconfig.getNAME());
				array.put(snmpconfig.getDESCRIPTION());
				array.put(snmpconfig.getVERSION());
				array.put(snmpconfig.getCOMMUNITY());
				array.put(snmpconfig.getUSERNAME());
				array.put(snmpconfig.getAUTH_PROTOCOL());
				array.put(snmpconfig.getCONTEXT());
				array.put(snmpconfig.getAUTH_PASS());
				array.put(snmpconfig.getENCRYPT_PROTOCOL());
				array.put(snmpconfig.getPORT());
				array.put(snmpconfig.getENCRYPT_PASS());
				array.put(snmpconfig.getTIMEOUT());
				array.put(snmpconfig.getRETRIES());
				// array.put("<a href=\"editSNMPConfig/" + emailconfig.getID() + "\"><i
				// class=\"fas fa-edit\"></a>");
				array.put("<button class='btn btn-sm btn-danger' id='Delete'  name='Doem' onclick='deleteSNMPConfig(\""
						+ snmpconfig.getID() + "\" )'><i class='fa fa-trash'></i></button>");
				array1.put(array);

			}

		} catch (Exception e) {
			e.printStackTrace();
			// TODO: handle exception
		}

		return array1;
	}

	/* Delete SNMP Configuration */

	public String deleteSNMPConfig(long id) {
		// TODO Auto-generated method stub
		System.out.println("delete SNMP Config:" + id);
		String result = null;

		try {
			Query q = getSession().createQuery("Delete from SnmpConfigMasterModel where ID=:id");
			q.setParameter("id", id);
			int i = q.executeUpdate();
			result = "success";
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("Exception in Delete SNMP Config" + e);
			result = "fail";
		}

		return result;
	}


	public List<Network_Discovery> discoveredNetwork() {
		Criteria criteria = getSession().createCriteria(Network_Discovery.class);
		return criteria.list();
	}
}
