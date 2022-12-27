package npm.admin.daoImpl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.StringTokenizer;

import org.hibernate.Criteria;
import org.hibernate.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import npm.admin.beans.AddNodeBean;
import npm.admin.beans.AddNodeParameterBean;
import npm.admin.dao.NodeDao;
import npm.configuration.AbstractDao;
import npm.model.AddNodeModel;
import npm.model.ConfigBackup;
import npm.model.DeviceTypeMasterModel;
import npm.model.GroupMasterModel;
import npm.model.NodeMonitoringModel;
import npm.model.NodeParameterModel;
import npm.model.ServiceProviderMasterModel;
import npm.model.SnmpConfigMasterModel;

@Repository
@Transactional
public class NodeDaoImpl extends AbstractDao<Integer, NodeParameterModel> implements NodeDao {

	// Add Node

	public Map<String, String> getdeviceType() {
		Map<String, String> deviceType = new HashMap<String, String>();
		try {
			Query q = getSession().createQuery("from DeviceTypeMasterModel ");

			List<DeviceTypeMasterModel> listdata = q.list();

			for (DeviceTypeMasterModel devicetype : listdata) {
				deviceType.put(devicetype.getDEVICE_TYPE(), devicetype.getDEVICE_TYPE());
			}

		} catch (Exception e) {

			System.out.println("Exceptioon e***" + e);

		}

		return deviceType;
	}

	public Map<String, String> getgroupName() {
		Map<String, String> groupName = new HashMap<String, String>();
		try {
			Query q = getSession().createQuery("from GroupMasterModel ");

			List<GroupMasterModel> listdata = q.list();

			for (GroupMasterModel groupname : listdata) {
				groupName.put(groupname.getGROUP_NAME(), groupname.getGROUP_NAME());
			}

		} catch (Exception e) {

			System.out.println("Exceptioon e***" + e);

		}

		return groupName;
	}

	public Map<String, String> getserviceProvider() {
		Map<String, String> serviceProvider = new HashMap<String, String>();
		try {
			Query q = getSession().createQuery("from ServiceProviderMasterModel ");

			List<ServiceProviderMasterModel> listdata = q.list();

			for (ServiceProviderMasterModel sp : listdata) {
				serviceProvider.put(sp.getSERVICE_PROVIDER_NAME(), sp.getSERVICE_PROVIDER_NAME());
			}

		} catch (Exception e) {

			System.out.println("Exceptioon e***" + e);

		}

		return serviceProvider;
	}

	public Map<String, String> getSnmp() {
		Map<String, String> snmp = new HashMap<String, String>();
		try {
			Query q = getSession().createQuery("from SnmpConfigMasterModel ");

			List<SnmpConfigMasterModel> listdata = q.list();

			for (SnmpConfigMasterModel sp : listdata) {
				snmp.put(sp.getNAME(), sp.getNAME());
			}

		} catch (Exception e) {

			System.out.println("Exceptioon e***" + e);

		}

		return snmp;
	}

	public String addNode(String deviceIp, String deviceType, String deviceName, String groupName, String snmp,
			String serviceProvider, String company, String state, String zone, String location, String district) {
		String nodeStatus = "Up";
		String latencyThreshold = "100";
		String result = null;
		try {

			NodeMonitoringModel nm = new NodeMonitoringModel();
			nm.setNODE_STATUS(nodeStatus);
			nm.setLATENCY_THRESHOLD(latencyThreshold);
			nm.setNODE_IP(deviceIp);

			AddNodeModel addNode = new AddNodeModel();
			addNode.setDEVICE_IP(deviceIp);
			;
			addNode.setDEVICE_NAME(deviceName);
			addNode.setDEVICE_TYPE(deviceType);
			addNode.setGROUP_NAME(groupName);
			addNode.setSNMP(snmp);
			addNode.setSERVICE_PROVIDER(serviceProvider);
			addNode.setMONITORING_PARAM("Yes");
			addNode.setCOMPANY(company);
			addNode.setSTATE(state);
			addNode.setDISTRICT(district);
			addNode.setZONE(zone);
			addNode.setLOCATION(location);

			getSession().save(addNode);
			getSession().save(nm);
			result = "success";

		} catch (Exception e) {
			System.out.println("Exception:" + e);
			result = "fail";
		}

		return result;
	}

	public List<AddNodeModel> viewAddNode() {

//		Query q = getSession().createQuery("from User_Master");
//		return q.list();

		Criteria criteria = getSession().createCriteria(AddNodeModel.class);
		return criteria.list();
	}

	public String deleteAddNode(long id) {
		// TODO Auto-generated method stub
		System.out.println("delete Node Parameter:" + id);
		String result = null;

		try {
			Query q = getSession().createQuery("Delete from AddNodeModel where ID=:id");
			q.setParameter("id", id);
			int i = q.executeUpdate();
			result = "success";
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("Exception e***" + e);
			result = "fail";
		}

		return result;
	}

	public AddNodeBean editAddNode(long id) {
		// TODO Auto-generated method stub
		System.out.println(" editAddNode :" + id);
		String result = null;

		AddNodeBean addNode = new AddNodeBean();
		try {
			Query q = getSession().createQuery("from AddNodeModel where ID=:id");
			q.setParameter("id", id);
			// List<UserMasterModel> listData = q.list();
			List<AddNodeModel> listdata = q.list();

			addNode.setDeviceIp(listdata.get(0).getDEVICE_IP());
			addNode.setDeviceName(listdata.get(0).getDEVICE_NAME());
			addNode.setDeviceType(listdata.get(0).getDEVICE_TYPE());
			addNode.setGroupName(listdata.get(0).getGROUP_NAME());
			addNode.setServiceProvider(listdata.get(0).getSERVICE_PROVIDER());
			addNode.setSnmp(listdata.get(0).getSNMP());
			addNode.setCompany(listdata.get(0).getCOMPANY());
			addNode.setState(listdata.get(0).getSTATE());
			addNode.setZone(listdata.get(0).getZONE());
			addNode.setDistrict(listdata.get(0).getDISTRICT());
			addNode.setLocation(listdata.get(0).getLOCATION());
			addNode.setId(id);

			result = "success";
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("Exceptioon e***" + e);
			result = "fail";
		}

		return addNode;
	}

	public String updateAddNode(String deviceIp, String deviceType, String deviceName, String groupName, String snmp,
			String serviceProvider, String company, String state, String zone, String location, String district,
			long id) {
		String result = null;
		try {
			Query q = getSession().createQuery(
					"Update AddNodeModel set device_ip=:deviceIp,device_type=:deviceType,device_name=:deviceName,group_name=:groupName,snmp=:snmp,"
							+ "service_provider=:serviceProvider,company=:company, state=:state, zone=:zone, location=:location, district=:district where id=:id");
			q.setParameter("deviceIp", deviceIp);
			q.setParameter("deviceType", deviceType);
			q.setParameter("deviceName", deviceName);
			q.setParameter("groupName", groupName);
			q.setParameter("snmp", snmp);
			q.setParameter("serviceProvider", serviceProvider);
			q.setParameter("company", company);
			q.setParameter("state", state);
			q.setParameter("zone", zone);
			q.setParameter("location", location);
			q.setParameter("district", district);
			q.setParameter("id", id);

			int i = q.executeUpdate();
			result = "success";
		} catch (Exception e) {
			System.out.println("Exception:" + e);
			result = "fail";
		}

		return result;
	}

	// Node Parameter

	public String addNodeParameter(String ip, int memoryThreshold, int cpuThreshold, int latencyThreshold,
			String monitoring, String cpuHistory, String latencyHistory, String memoryHistory, String snmpDiscovery) {
		// TODO Auto-generated method stub
		System.out.println("Dao called");
		System.out.println("ip:" + ip);
		System.out.println("memoryThreshold:" + memoryThreshold);
		System.out.println("cpuThreshold:" + cpuThreshold);
		System.out.println("latencyThreshold:" + latencyThreshold);
		System.out.println("monitoring:" + monitoring);
		System.out.println("cpuHistory:" + cpuHistory);
		System.out.println("latencyHistory:" + latencyHistory);
		System.out.println("memoryHistory: " + memoryHistory);

		String result = null;
		List query = getSession().createQuery("Select DEVICE_IP from NodeParameterModel where DEVICE_IP='" + ip + "'")
				.list();

		// System.out.println("ip == "+(query));
		try {
			NodeParameterModel nodeParameter = new NodeParameterModel();
			if (!query.isEmpty()) {

				Query q = getSession().createQuery(
						"Update NodeParameterModel set cpu_threshold=:cpuThreshold,memory_threshold=:memoryThreshold,latency_threshold=:latencyThreshold,monitoring=:monitoring,"
								+ "cpu_history=:cpuHistory,memory_history=:memoryHistory, latency_history=:latencyHistory, SNMP_DISCOVERY_PARAM=:snmpDiscovery where DEVICE_IP='"
								+ ip + "' ");

				q.setParameter("cpuThreshold", cpuThreshold);
				q.setParameter("memoryThreshold", memoryThreshold);
				q.setParameter("latencyThreshold", latencyThreshold);
				q.setParameter("monitoring", monitoring);
				q.setParameter("cpuHistory", cpuHistory);
				q.setParameter("memoryHistory", memoryHistory);
				q.setParameter("latencyHistory", latencyHistory);
				q.setParameter("snmpDiscovery", snmpDiscovery);
				q.executeUpdate();
				result = "update";

			} else {

				nodeParameter.setDEVICE_IP(ip);
				nodeParameter.setMEMORY_THRESHOLD(memoryThreshold);
				nodeParameter.setCPU_THRESHOLD(cpuThreshold);
				nodeParameter.setLATENCY_THRESHOLD(latencyThreshold);
				nodeParameter.setCPU_HISTORY(cpuHistory);
				nodeParameter.setLATENCY_HISTORY(latencyHistory);
				nodeParameter.setMEMORY_HISTORY(memoryHistory);
				nodeParameter.setMONITORING(monitoring);
				nodeParameter.setSNMP_DISCOVERY_PARAM(snmpDiscovery);

				getSession().save(nodeParameter);
				result = "success";
			}
		} catch (Exception e) {
			System.out.println("Exception:" + e);
			result = "fail";
		}

		return result;
	}

	public List<NodeParameterModel> viewNodeParameter() {

//		Query q = getSession().createQuery("from User_Master");
//		return q.list();

		Criteria criteria = getSession().createCriteria(NodeParameterModel.class);
		return criteria.list();
	}

	public String deleteNodeParameter(long id) {
		// TODO Auto-generated method stub
		System.out.println("delete Node Parameter:" + id);
		String result = null;

		try {
			Query q = getSession().createQuery("Delete from NodeParameterModel where ID=:id");
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

	public AddNodeParameterBean editNodeParameter(long id) {
		// TODO Auto-generated method stub
		System.out.println(" editNodeParameter :" + id);
		String result = null;

		AddNodeParameterBean nodeParameter = new AddNodeParameterBean();
		try {
			Query q = getSession().createQuery("from NodeParameterModel where ID=:id");
			q.setParameter("id", id);
			// List<UserMasterModel> listData = q.list();
			List<NodeParameterModel> listdata = q.list();

			nodeParameter.setIp(listdata.get(0).getDEVICE_IP());
			nodeParameter.setCpuThreshold(listdata.get(0).getCPU_THRESHOLD());
			nodeParameter.setMemoryThreshold(listdata.get(0).getMEMORY_THRESHOLD());
			nodeParameter.setLatencyThreshold(listdata.get(0).getLATENCY_THRESHOLD());
			nodeParameter.setCpuHistory(listdata.get(0).getCPU_HISTORY());
			nodeParameter.setMemoryHistory(listdata.get(0).getMEMORY_HISTORY());
			nodeParameter.setLatencyHistory(listdata.get(0).getLATENCY_HISTORY());
			nodeParameter.setMonitoring(listdata.get(0).getMONITORING());
			nodeParameter.setId(id);

			result = "success";
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("Exceptioon e***" + e);
			result = "fail";
		}

		return nodeParameter;
	}

	public String updateNodeParameter(String ip, int memoryThreshold, int cpuThreshold, int latencyThreshold,
			String monitoring, String cpuHistory, String latencyHistory, String memoryHistory, long id) {
		String result = null;
		try {
			Query q = getSession().createQuery(
					"Update NodeParameterModel set ip=:ip,cpu_threshold=:cpuThreshold,memory_threshold=:memoryThreshold,latency_threshold=:latencyThreshold,monitoring=:monitoring,"
							+ "cpu_history=:cpuHistory,memory_history=:memoryHistory, latency_history=:latencyHistory where id=:id");
			q.setParameter("ip", ip);
			q.setParameter("cpuThreshold", cpuThreshold);
			q.setParameter("memoryThreshold", memoryThreshold);
			q.setParameter("latencyThreshold", latencyThreshold);
			q.setParameter("monitoring", monitoring);
			q.setParameter("cpuHistory", cpuHistory);
			q.setParameter("memoryHistory", memoryHistory);
			q.setParameter("latencyHistory", latencyHistory);
			q.setParameter("id", id);

			int i = q.executeUpdate();
			result = "success";
		} catch (Exception e) {
			System.out.println("Exception:" + e);
			result = "fail";
		}

		return result;
	}

	// config backup
	public String addConfigBackup(String backupPath, String runConfig, String startupConfig, String backup,
			String timepicker, String group_name, String ip_address) {

		String result = "";
		StringTokenizer ip = new StringTokenizer(ip_address, ",");

		try {

			while (ip.hasMoreTokens()) {
				String ipAdd = ip.nextToken();
				ConfigBackup cb = new ConfigBackup();
				cb.setBACKUP_PATH(backupPath);
				cb.setRUNNING_CONFIG(runConfig);
				cb.setSTARTUP_CONFIG(startupConfig);
				cb.setBACKUP(backup);
				cb.setTIME(timepicker);
				cb.setGROUP_NAME(group_name);
				cb.setNODE_IP(ipAdd);
				getSession().save(cb);

			}
			result = "success";

		} catch (Exception e) {
			System.out.println("Exception:" + e);
			result = "fail";
		}

		return result;
	}

	public List<ConfigBackup> viewconfigBackup() {

		Criteria criteria = getSession().createCriteria(ConfigBackup.class);
		return criteria.list();
	}

	public String deleteConfigBackup(long id) {
		// TODO Auto-generated method stub
		System.out.println("id:" + id);
		String result = null;

		try {
			Query q = getSession().createQuery("Delete from ConfigBackup where ID=:id");
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

	public String importNodeIP(String nodeIP) {
		System.out.println("iplist = " + nodeIP);
		String result = "";
		StringTokenizer ip = new StringTokenizer(nodeIP, ",");
		AddNodeModel ad = null;
		NodeMonitoringModel nm = null;
		NodeParameterModel np = null;

		try {

			while (ip.hasMoreTokens()) {

				String importNodeIP = ip.nextToken();
				//System.out.println("node ip:" + importNodeIP);

				List query1 = getSession().createQuery("from AddNodeModel where DEVICE_IP = '" + importNodeIP + "'")
						.list();
				List query2 = getSession()
						.createQuery("from NodeParameterModel where DEVICE_IP = '" + importNodeIP + "'").list();
				List query3 = getSession()
						.createQuery("from NodeMonitoringModel where NODE_IP = '" + importNodeIP + "'").list();

				if (query1.isEmpty()) {

					ad = new AddNodeModel();
					ad.setDEVICE_IP(importNodeIP);
					ad.setDEVICE_NAME("OEM");
					ad.setDEVICE_TYPE("Router");
					ad.setGROUP_NAME("A");
					ad.setSNMP("Switch SNMP");
					ad.setCOMPANY("NA");
					ad.setDISTRICT("NA");
					ad.setAUTO_TICKETING("NA");
					ad.setCPU_THRESHOLD(0);
					ad.setLATENCY_THRESHOLD(0);
					ad.setLOCATION("NA");
					ad.setMAIL_ALERT("NA");
					ad.setMEMORY_THRESHOLD(0);
					ad.setSERVICE_PROVIDER("NA");
					ad.setSMS_ALERT("NA");
					ad.setMONITORING_PARAM("Yes");
					ad.setLOCATION("NA");
					ad.setSTATE("NA");
					ad.setZONE("NA");
					getSession().save(ad);
				} else {
					Query updateQuery1 = getSession().createQuery(
							"update AddNodeModel set device_type='router',device_name='OEM', group_name='A',snmp='Switch SNMP', SERVICE_PROVIDER='NA',company='NA', state='NA', zone='NA', location='NA', district='NA' where device_ip='"
									+ importNodeIP + "'");
					updateQuery1.executeUpdate();
				}

				if (query3.isEmpty()) {
					nm = new NodeMonitoringModel();
					nm.setNODE_IP(importNodeIP);
					nm.setNODE_STATUS("Up");
					nm.setLATENCY_THRESHOLD("10");
					getSession().save(nm);
				} else {
					Query updateQuery3 = getSession().createQuery(
							"update NodeMonitoringModel set node_status='Up', latency_threshold='10' where node_ip='"
									+ importNodeIP + "'");
					updateQuery3.executeUpdate();
				}

				if (query2.isEmpty()) {

					np = new NodeParameterModel();
					np.setDEVICE_IP(importNodeIP);
					np.setCPU_HISTORY("Yes");
					np.setCPU_THRESHOLD(50);
					np.setLATENCY_HISTORY("Yes");
					np.setLATENCY_THRESHOLD(50);
					np.setMEMORY_HISTORY("Yes");
					np.setMEMORY_THRESHOLD(50);
					np.setMONITORING("NA");
					np.setSNMP_DISCOVERY_PARAM("No");
					getSession().save(np);
				} else {
					Query updateQuery3 = getSession().createQuery(
							"update NodeParameterModel set cpu_history='Yes', cpu_threshold='50', latency_history='Yes', latency_threshold='50', "
									+ "memory_history='Yes', memory_threshold='50', monitoring='NA', snmp_discovery_param='Yes' where device_ip='"
									+ importNodeIP + "'");
					updateQuery3.executeUpdate();
				}

				result = "success";
			}

		} catch (Exception e) {
			System.out.println("Exception:" + e);
			result = "fail";
		}

		return result;
	}

}
