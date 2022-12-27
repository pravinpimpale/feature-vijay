package npm.dashboard.service;

import org.json.JSONArray;
import org.json.JSONObject;

public interface NodeDetailsService {
	JSONObject nodeLatencyPacketDrop(String ip_address);
	
	JSONObject nodeAddNode(String ip_address);
	
	JSONObject nodeAvailabilityDetails(String ip_address);
	
	JSONArray nodeStatusHistoryDetails(String ip_address);
	
	JSONObject nodeHealthMonitoringDetails(String ip_address);

	JSONArray InterfaceStatusHistoryDetails(String ip_address);

	JSONArray basicInfoDetails(String ip_address);

	JSONArray nodeUpTimeStatusDetails(String ip_address);

	JSONArray nodeLatencyStatusDetails(String ip_address);
	String getDBVerification(String username, String Password);

}
