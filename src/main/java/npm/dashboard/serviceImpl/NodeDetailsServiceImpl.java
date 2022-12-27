package npm.dashboard.serviceImpl;

import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import npm.dashboard.dao.NodeDetailsDao;
import npm.dashboard.service.NodeDetailsService;

@Service
public class NodeDetailsServiceImpl implements NodeDetailsService {

	@Autowired
	NodeDetailsDao dao;

	public JSONObject nodeLatencyPacketDrop(String ip_address) {

		return dao.nodeLatencyPacketDrop(ip_address);
	}

	public JSONObject nodeAddNode(String ip_address) {

		return dao.nodeAddNode(ip_address);
	}

	public JSONObject nodeAvailabilityDetails(String ip_address) {

		return dao.nodeAvailabilityDetails(ip_address);
	}

	public JSONArray nodeStatusHistoryDetails(String ip_address) {

		return dao.nodeStatusHistoryDetails(ip_address);
	}
	
	public JSONObject nodeHealthMonitoringDetails(String ip_address) {

		return dao.nodeHealthMonitoringDetails(ip_address);
	}

	public JSONArray InterfaceStatusHistoryDetails(String ip_address) {
		// TODO Auto-generated method stub
		return dao.InterfaceStatusHistoryDetails(ip_address);
	}

	public JSONArray basicInfoDetails(String ip_address) {
		// TODO Auto-generated method stub
		return dao.basicInfoDetails(ip_address);
	}

	public JSONArray nodeUpTimeStatusDetails(String ip_address) {
		// TODO Auto-generated method stub
		return dao.nodeUpTimeStatusDetails(ip_address);
	}

	public JSONArray nodeLatencyStatusDetails(String ip_address) {
		// TODO Auto-generated method stub
		return dao.nodeLatencyStatusDetails(ip_address);
	}
	public String getDBVerification(String username, String Password) {
		// TODO Auto-generated method stub
		return dao.getDBVerification(username,Password);
	}
	

}
