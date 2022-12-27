package npm.admin.serviceImpl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import npm.admin.beans.AddNodeBean;
import npm.admin.beans.AddNodeParameterBean;
import npm.admin.dao.NodeDao;
import npm.admin.service.NodeService;
import npm.model.AddNodeModel;
import npm.model.ConfigBackup;
import npm.model.NodeParameterModel;

@Service
public class NodeServiceImpl implements NodeService{
	
	
	@Autowired
	NodeDao dao;
	
	
	//Add Node
	public Map<String, String> getdeviceType() {
		// TODO Auto-generated method stub
		return dao.getdeviceType();
	}
	
	public Map<String, String> getgroupName() {
		// TODO Auto-generated method stub
		return dao.getgroupName();
	}
	
	public Map<String, String> getserviceProvider() {
		// TODO Auto-generated method stub
		return dao.getserviceProvider();
	}
	
	public Map<String, String> getSnmp() {
		// TODO Auto-generated method stub
		return dao.getSnmp();
	}
	
	public String addNode(String deviceIp, String deviceType, String deviceName, String groupName, String snmp, String serviceProvider, String company, String state, String zone, String location, String district) {
		// TODO Auto-generated method stub
		
		return dao.addNode(deviceIp, deviceName, deviceType, groupName, snmp, serviceProvider, company, state, zone, location, district);
		
	}
	
	public List<AddNodeModel> viewAddNode() {
		// TODO Auto-generated method stub
		return dao.viewAddNode();
	}
	
	public String deleteAddNode(long id) {
		// TODO Auto-generated method stub
		
		return dao.deleteAddNode(id);
		
	}
	
	public AddNodeBean editAddNode(long id) {
		// TODO Auto-generated method stub
		return dao.editAddNode(id);
	}
	
	public String updateAddNode(String deviceIp, String deviceType, String deviceName, String groupName, String snmp, String serviceProvider, String company, String state, String zone, String location, String district, long id) {
		// TODO Auto-generated method stub
		
		return dao.updateAddNode(deviceIp, deviceName, deviceType, groupName, snmp, serviceProvider, company, state, zone, location, district, id);
		
	}

	
	
	
	//Node Parameter
	public String addNodeParameter(String ip, int memoryThreshold, int cpuThreshold, int latencyThreshold, String monitoring, String cpuHistory, String latencyHistory, String memoryHistory, String snmpDiscovery) {
		// TODO Auto-generated method stub
		
		return dao.addNodeParameter(ip, memoryThreshold, cpuThreshold, latencyThreshold, monitoring, cpuHistory, memoryHistory, latencyHistory, snmpDiscovery);
		
	}
	
	public List<NodeParameterModel> viewNodeParameter() {
		// TODO Auto-generated method stub
		return dao.viewNodeParameter();
	}
	
	public String deleteNodeParameter(long id) {
		// TODO Auto-generated method stub
		
		return dao.deleteNodeParameter(id);
		
	}
	
	public AddNodeParameterBean editNodeParameter(long id) {
		// TODO Auto-generated method stub
		return dao.editNodeParameter(id);
	}
	
	public String updateNodeParameter(String ip, int memoryThreshold, int cpuThreshold, int latencyThreshold, String monitoring, String cpuHistory, String latencyHistory, String memoryHistory, long id) {
		// TODO Auto-generated method stub
		
		return dao.updateNodeParameter(ip, memoryThreshold, cpuThreshold, latencyThreshold, monitoring, cpuHistory, memoryHistory, latencyHistory, id);
		
	}

	public String addConfigBackup(String backupPath, String runConfig, String startupConfig, String backup,
			String timepicker, String group_name, String ip_address) {
		// TODO Auto-generated method stub
		return dao.addConfigBackup(backupPath, runConfig, startupConfig, backup, timepicker, group_name, ip_address);
	}

	public List<ConfigBackup> viewconfigBackup() {
		// TODO Auto-generated method stub
		return dao.viewconfigBackup();
	}

	public String deleteConfigBackup(long id) {
		// TODO Auto-generated method stub
		return dao.deleteConfigBackup(id);
	}

	//19-12-2022
	public String importNodeIP(String nodeIP) {
		// TODO Auto-generated method stub
		return dao.importNodeIP(nodeIP);
	}
	

	
	
	

}
