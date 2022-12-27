package npm.report.service;

import java.util.List;

import org.json.JSONArray;
import org.json.JSONObject;

import npm.admin.beans.CpuThresholdHealthReportBean;
import npm.admin.beans.LatencyHistoryReportBean;
import npm.admin.beans.LatencyThresholdReportBean;
import npm.admin.beans.MemoryThresholdReportBean;
import npm.admin.beans.NodeAvailabilityBean;
import npm.admin.beans.NodeHealthHistoryReportBean;
import npm.admin.beans.NodeStatusReportBean;
import npm.model.DeviceAlertLog;
import npm.model.InterfaceMonitoring;
import npm.model.NodeHealthMonitoring;
import npm.model.NodeMonitoringModel;
import npm.model.NodeStatusHistory;

public interface NodeReportService {

	
	List<NodeStatusReportBean> NodeStatusReport( String from_date, String to_date, List<String> ip_list);
	
	List<LatencyHistoryReportBean> latencyHistoryReport( String from_date, String to_date, List<String> ip_list);
	
	List<LatencyThresholdReportBean> latencyThresholdReport(String from_date, String to_date, List<String> ip_list);
	
	List<NodeAvailabilityBean> nodeAvailabilityReport(String from_date, String to_date, List<String> ip_list);
	
	List<NodeHealthHistoryReportBean> nodeHealthHistoryReport( String from_date, String to_date, List<String> ip_list);
	
	List<CpuThresholdHealthReportBean> cpuThresholdHistoryReport(String from_date, String to_date, List<String> ip_list);
	
	List<MemoryThresholdReportBean> memoryThresholdHistoryReport(String from_date, String to_date, List<String> ip_list);
	
	List<NodeHealthMonitoring> nodeHealthMonitoringView();
	
	List<NodeMonitoringModel> nodeMonitoringView();
	
	List<InterfaceMonitoring> interfaceMonitoringView();
	
	JSONObject latencyHistoricalData(String from_date, String to_date, String ip_address);
	
	JSONObject interfaceBWHistoryData(String from_date, String to_date, String ip_address, String interface_name);
	
	JSONObject nodeHealthHistoryData(String from_date, String to_date, String ip_address);
	
	JSONObject nodeAvailabilityData(String from_date, String to_date, String ip_address);
	
	JSONObject interfaceAvailabilityData(String from_date, String to_date, String ip_address, String interface_name);

	String getIPAddress();

	String getInterfaceOnIPAddress(String ipAddress);

	String SaveNodePositions(String fromTo, String positions, String ipAddress, String username);

	String NetworkTopologyEditMode(String grpName);

	String NetworkTopologyInsertInterface(String grpName, String toIpAddress, String fromInterface, String toInterface);
	
	List<DeviceAlertLog> viewDeviceAlertLog();
	

	
	
}
