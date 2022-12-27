package npm.report.serviceImpl;

import java.util.List;

import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
import npm.report.dao.NodeReportDao;
import npm.report.service.NodeReportService;

@Service
public class NodeReportServiceImpl implements NodeReportService {

	@Autowired
	NodeReportDao dao;

	public List<NodeStatusReportBean> NodeStatusReport(String from_date, String to_date, List<String> ip_list) {

		return dao.NodeStatusReport(from_date, to_date, ip_list);

	}

	public List<LatencyHistoryReportBean> latencyHistoryReport(String from_date, String to_date, List<String> ip_list) {

		return dao.latencyHistoryReport(from_date, to_date, ip_list);

	}

	public List<LatencyThresholdReportBean> latencyThresholdReport(String from_date, String to_date,
			List<String> ip_list) {

		return dao.latencyThresholdReport(from_date, to_date, ip_list);

	}

	public List<NodeAvailabilityBean> nodeAvailabilityReport(String from_date, String to_date, List<String> ip_list) {

		return dao.nodeAvailabilityReport(from_date, to_date, ip_list);

	}

	public List<NodeHealthHistoryReportBean> nodeHealthHistoryReport(String from_date, String to_date,
			List<String> ip_list) {

		return dao.nodeHealthHistoryReport(from_date, to_date, ip_list);

	}

	public List<CpuThresholdHealthReportBean> cpuThresholdHistoryReport(String from_date, String to_date,
			List<String> ip_list) {

		return dao.cpuThresholdHistoryReport(from_date, to_date, ip_list);

	}

	public List<MemoryThresholdReportBean> memoryThresholdHistoryReport(String from_date, String to_date,
			List<String> ip_list) {

		return dao.memoryThresholdHistoryReport(from_date, to_date, ip_list);

	}

	public List<NodeHealthMonitoring> nodeHealthMonitoringView() {

		return dao.nodeHealthMonitoringView();
	}

	public List<NodeMonitoringModel> nodeMonitoringView() {

		return dao.nodeMonitoringView();
	}

	public List<InterfaceMonitoring> interfaceMonitoringView() {

		return dao.interfaceMonitoringView();
	}


	public JSONObject latencyHistoricalData(String from_date, String to_date, String ip_address) {

		return dao.latencyHistoricalData( from_date, to_date, ip_address);

	}
	
	public JSONObject interfaceBWHistoryData(String from_date, String to_date, String ip_address, String interface_name) {

		return dao.interfaceBWHistoryData( from_date, to_date, ip_address, interface_name);

	}
	
	public JSONObject nodeHealthHistoryData(String from_date, String to_date, String ip_address) {

		return dao.nodeHealthHistoryData( from_date, to_date, ip_address);

	}
	
	public JSONObject nodeAvailabilityData(String from_date, String to_date, String ip_address) {

		return dao.nodeAvailabilityData( from_date, to_date, ip_address);

	}
	
	public JSONObject interfaceAvailabilityData(String from_date, String to_date, String ip_address, String interface_name) {

		return dao.interfaceAvailabilityData( from_date, to_date, ip_address, interface_name);

	}

	public String getIPAddress() {
		// TODO Auto-generated method stub
		return dao.getIPAddress();
	}

	public String getInterfaceOnIPAddress(String ipAddress) {
		// TODO Auto-generated method stub
		return dao.getInterfaceOnIPAddress(ipAddress);
	}

	public String SaveNodePositions(String fromTo, String positions, String ipAddress, String username) {
		// TODO Auto-generated method stub
		return dao.SaveNodePositions(fromTo,positions,ipAddress,username);
	}

	public String NetworkTopologyEditMode(String grpName) {
		// TODO Auto-generated method stub
		return dao.NetworkTopologyEditMode(grpName);
	}

	public String NetworkTopologyInsertInterface(String fromIpAddress, String toIpAddress, String fromInterface,
			String toInterface) {
		// TODO Auto-generated method stub
		return dao.NetworkTopologyInsertInterface(fromIpAddress,toIpAddress,fromInterface,toInterface);
	}

	public List<DeviceAlertLog> viewDeviceAlertLog() {
		// TODO Auto-generated method stub
		return dao.viewDeviceAlertLog();
	}

	
	

	
}
