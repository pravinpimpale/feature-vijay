package npm.dashboard.serviceImpl;

import org.json.JSONArray;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import npm.dashboard.dao.DashboardDao;
import npm.dashboard.service.DashboardService;

@Service
public class DashboardServiceImpl implements DashboardService {

	@Autowired
	DashboardDao dao;

	/* Get Device Summary Count */
	public JSONArray deviceSummaryCount() {

		JSONArray jsonarray_dao_output = dao.deviceSummaryCount();

		return jsonarray_dao_output;
	}

	/* Get Link Summary Count */
	public JSONArray linkSummaryCount() {

		JSONArray jsonarray_dao_output = dao.linkSummaryCount();

		return jsonarray_dao_output;
	}

	/* Get Down Devices Summary Listing */
	public JSONArray deviceSummaryList() {

		JSONArray jsonarray_dao_output = dao.deviceSummaryList();

		return jsonarray_dao_output;
	}

	/* Get Down Link Summary Listing */
	public JSONArray linkSummaryList() {

		JSONArray jsonarray_dao_output = dao.linkSummaryList();

		return jsonarray_dao_output;
	}
	
	/* Get Device Info */
	public JSONArray getDeviceInfo(String deviceInfo) {

		JSONArray jsonarray_dao_output = dao.getDeviceInfo(deviceInfo);

		return jsonarray_dao_output;
	}
	
	/* Get Device Info */
	public JSONArray getLinkInfo(String linkInfo) {

		JSONArray jsonarray_dao_output = dao.getLinkInfo(linkInfo);

		return jsonarray_dao_output;
	}

	/* Top CPU Summary Listing */
	public JSONArray topCPUSummary() {

		JSONArray jsonarray_dao_output = dao.topCPUSummary();

		return jsonarray_dao_output;
	}

	/* Get Interface Info */
	public JSONArray interfaceInfo(String deviceIP, String intName) {

		JSONArray jsonarray_dao_output = dao.interfaceInfo(deviceIP, intName);

		return jsonarray_dao_output;
	}

	/* Get Interface Status History */
	public JSONArray interfaceStatusHistory(String deviceIP, String intName) {

		JSONArray jsonarray_dao_output = dao.interfaceStatusHistory(deviceIP, intName);

		return jsonarray_dao_output;
	}
	
	/* Get Interface CRC Log */
	public JSONArray interfaceCRCLog(String deviceIP, String intName) {

		JSONArray jsonarray_dao_output = dao.interfaceCRCLog(deviceIP, intName);

		return jsonarray_dao_output;
	}
	
	/* Get Interface Uptime Log */
	public JSONArray interfaceUptimeLog(String deviceIP, String intName) {

		JSONArray jsonarray_dao_output = dao.interfaceUptimeLog(deviceIP, intName);

		return jsonarray_dao_output;
	}
	
	/*  Get Interface Bandwidth */
	public JSONArray interfaceBandwidthUtil(String deviceIP, String intName) {

		JSONArray jsonarray_dao_output = dao.interfaceBandwidthUtil(deviceIP, intName);

		return jsonarray_dao_output;
	}
	
	/* Get Toptalker Link Summary Listing */
	public JSONArray topTalkerLinkSummaryList() {

		JSONArray jsonarray_dao_output = dao.topTalkerLinkSummaryList();

		return jsonarray_dao_output;
	}
	
	//Export Node Latency
		public JSONArray exportNodeLatency(String from_date, String to_date, String ip_address) {

			return dao.exportNodeLatency( from_date, to_date, ip_address);

		}
		
}
