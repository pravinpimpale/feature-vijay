package npm.dashboard.service;

import org.json.JSONArray;

public interface DashboardService {
	
	/* Get Device Count Summary */
	public JSONArray deviceSummaryCount();
	
	/* Get Link Count Summary */
	public JSONArray linkSummaryCount();
	
	/* Get Down Devices Summary Listing */
	public JSONArray deviceSummaryList();
	
	/* Get Down Link Summary Listing */
	public JSONArray linkSummaryList();
	
	/* Get Device Info */
	public JSONArray getDeviceInfo(String deviceInfo);
	
	/* Get Link Info */
	public JSONArray getLinkInfo(String linkInfo);
	
	/* Top CPU Summary Listing */
	public JSONArray topCPUSummary();
	
	/* Get Interface Info */
	public JSONArray interfaceInfo(String deviceIP, String intName);
	
	/* Get Interface Status History */
	public JSONArray interfaceStatusHistory(String deviceIP, String intName);
	
	/* Get Interface CRC Log */
	public JSONArray interfaceCRCLog(String deviceIP, String intName);
	
	/* Get Interface Uptime Log */
	public JSONArray interfaceUptimeLog(String deviceIP, String intName);
	
	/* Get Interface Bandwidth */
	public JSONArray interfaceBandwidthUtil(String deviceIP, String intName);
	
	/* Get Toptalker Link Summary Listing */
	public JSONArray topTalkerLinkSummaryList();
	
	//Export Node Latency
	JSONArray exportNodeLatency(String from_date, String to_date, String ip_address);
	

}
