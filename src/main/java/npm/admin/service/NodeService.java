package npm.admin.service;

import java.util.List;
import java.util.Map;

import npm.admin.beans.AddNodeBean;
import npm.admin.beans.AddNodeParameterBean;
import npm.model.AddNodeModel;
import npm.model.ConfigBackup;
import npm.model.NodeParameterModel;

public interface NodeService {
	
	
	//Add Node
	
	Map<String, String> getdeviceType();
	Map<String, String> getgroupName();
	Map<String, String> getserviceProvider();
	Map<String, String> getSnmp();
	
	String addNode(String deviceip, String deviceType, String deviceName, String groupName, String snmp, String serviceProvider, String company, String state, String zone, String location, String district);
	
	 List<AddNodeModel> viewAddNode();
	 
	 String deleteAddNode(long id ) ;
	 
	 AddNodeBean editAddNode(long id ) ;
	 
	 String updateAddNode(String deviceip, String deviceType, String deviceName, String groupName, String snmp, String serviceProvider, String company, String state, String zone, String location, String district, long id) ;
	

	
	//Node Parameter
	String addNodeParameter(String ip, int memoryThreshold, int cpuThreshold, int latencyThreshold, String monitoring, String cpuHistory, String latencyHistory, String memoryHistory, String snmpDiscovery);
	
	 List<NodeParameterModel> viewNodeParameter();
	 
	 String deleteNodeParameter(long id ) ;
	 
	 AddNodeParameterBean editNodeParameter(long id ) ;
	 
	 String updateNodeParameter(String ip, int memoryThreshold, int cpuThreshold, int latencyThreshold, String monitoring, String cpuHistory, String latencyHistory, String memoryHistory, long id) ;
	 
	 //config backup
	 String addConfigBackup(String backupPath, String runConfig, String startupConfig, String backup, String timepicker, String group_name, String ip_address);
	 
	 List<ConfigBackup> viewconfigBackup();
	String deleteConfigBackup(long id);
	
	//19-12-2022
	String importNodeIP(String nodeIP);
	 
	 
	 
	 
	 
	 
	 
	 
	 
	
}
