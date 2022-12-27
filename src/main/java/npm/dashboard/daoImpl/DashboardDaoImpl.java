package npm.dashboard.daoImpl;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;
import java.util.ArrayList;
import java.util.Date;

import org.hibernate.Criteria;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import org.hibernate.Query;
import org.hibernate.SQLQuery;

import npm.configuration.AbstractDao;
import npm.dashboard.dao.DashboardDao;
import npm.model.GroupMasterModel;
import npm.model.LatencyHisotryModel;
import npm.model.NodeMonitoringModel;

@Repository
@Transactional
public class DashboardDaoImpl extends AbstractDao<Integer, NodeMonitoringModel> implements DashboardDao {

	@Autowired
	private Environment environment;

	/* Get Device Summary Count */
	public JSONArray deviceSummaryCount() {

		JSONArray array = null;
		JSONArray array1 = null;
		JSONObject jsonObject = null;

		try {

			Query q = getSession().createQuery(
					"SELECT NODEMON.NODE_STATUS,COUNT(*) FROM NodeMonitoringModel NODEMON,AddNodeModel AN WHERE AN.DEVICE_IP=NODEMON.NODE_IP AND AN.MONITORING_PARAM='Yes'  GROUP BY NODEMON.NODE_STATUS");

			List<?> dataList = q.list();
			array1 = new JSONArray();

			for (int i = 0; i < dataList.size(); i++) {
				// array = new JSONArray();
				jsonObject = new JSONObject();
				Object[] row = (Object[]) dataList.get(i);
				// array.put("status:"+row[0]);
				// array.put("count:"+row[1]);
				jsonObject.put("status", row[0]);
				jsonObject.put("count", row[1]);

				array1.put(jsonObject);
			}
			System.out.println("DeviceCount Array:" + array1.toString());
		} catch (Exception e) {
			e.printStackTrace();
			// TODO: handle exception
		}

		return array1;
	}

	/* Get Link Summary Count */
	public JSONArray linkSummaryCount() {

		JSONArray array = null;
		JSONArray array1 = null;
		JSONObject jsonObject = null;

		try {

			Query q = getSession().createQuery(
					"SELECT IFMON.OPER_STATUS,COUNT(*) FROM InterfaceMonitoring AS IFMON WHERE IFMON.MONITORING_PARAM='Yes' GROUP BY IFMON.OPER_STATUS");

			List<?> dataList = q.list();
			array1 = new JSONArray();

			for (int i = 0; i < dataList.size(); i++) {
				// array = new JSONArray();
				jsonObject = new JSONObject();
				Object[] row = (Object[]) dataList.get(i);
				// array.put("status:"+row[0]);
				// array.put("count:"+row[1]);
				jsonObject.put("status", row[0]);
				jsonObject.put("count", row[1]);

				array1.put(jsonObject);
			}
			System.out.println("LinkCount Array:" + array1.toString());
		} catch (Exception e) {
			e.printStackTrace();
			// TODO: handle exception
		}

		return array1;
	}

	/* Get Down Devices Summary Listing */
	public JSONArray deviceSummaryList() {

		JSONArray array = null;
		JSONArray array1 = null;
		int srno = 0;
		try {

			String sql = "SELECT nodemon.NODE_IP, nodemon.STATUS_TIMESTAMP, addnode.LOCATION, addnode.COMPANY  FROM node_monitoring nodemon, add_node addnode WHERE nodemon.NODE_STATUS='Down' AND nodemon.NODE_IP=addnode.DEVICE_IP";

			String db_type = environment.getRequiredProperty("DATABASE_TYPE");
			if (db_type.equalsIgnoreCase("mysql")) {
				sql = "SELECT nodemon.NODE_IP, nodemon.STATUS_TIMESTAMP, addnode.LOCATION, addnode.COMPANY  FROM node_monitoring nodemon, add_node addnode WHERE nodemon.NODE_STATUS='Down' AND nodemon.NODE_IP=addnode.DEVICE_IP";
			} else if (db_type.equalsIgnoreCase("mssql")) {

			} else if (db_type.equalsIgnoreCase("oracle")) {

			}

			SQLQuery query = getSession().createSQLQuery(sql);
			query.setResultTransformer(Criteria.ALIAS_TO_ENTITY_MAP);

			List results = query.list();
			array1 = new JSONArray();

			for (Object object : results) {
				Map row = (Map) object;

				// System.out.println("Node IP: " + row.get("NODE_IP"));
				// System.out.println("Branch: " + row.get("LOCATION"));
				// System.out.println("Customer: " + row.get("COMPANY"));
				// System.out.println("Event Timestamp: " + row.get("STATUS_TIMESTAMP"));
				String downDate = row.get("STATUS_TIMESTAMP").toString();

				String downSince = "NA";

				try {
					SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.S");
					Date parsedDate = dateFormat.parse(downDate);
					Timestamp dbtimestamp = new Timestamp(parsedDate.getTime());
					// System.out.println("DB TimeStamp:" + dbtimestamp);
					Timestamp currenttimestamp = new Timestamp(new Date().getTime());
					// System.out.println("Current Timestamp:" + currenttimestamp);
					long milliseconds = currenttimestamp.getTime() - dbtimestamp.getTime();

					long days = TimeUnit.MILLISECONDS.toDays(milliseconds);
					long hours = TimeUnit.MILLISECONDS.toHours(milliseconds)
							- TimeUnit.DAYS.toHours(TimeUnit.MILLISECONDS.toDays(milliseconds));
					long minutes = TimeUnit.MILLISECONDS.toMinutes(milliseconds)
							- TimeUnit.HOURS.toMinutes(TimeUnit.MILLISECONDS.toHours(milliseconds));
					long seconds = TimeUnit.MILLISECONDS.toSeconds(milliseconds)
							- TimeUnit.MINUTES.toSeconds(TimeUnit.MILLISECONDS.toMinutes(milliseconds));

					downSince = days + " Days " + hours + " Hours " + minutes + " Minutes " + seconds + " Seconds";

				} catch (Exception e) {
					// TODO: handle exception
					System.out.println("Exception in Parse Timestamp:" + e);
				}

				array = new JSONArray();
				array.put(row.get("NODE_IP"));
				array.put(row.get("LOCATION"));
				array.put(row.get("COMPANY"));
				if (!downSince.equalsIgnoreCase("NA")) {
					array.put(downSince);
				} else {
					array.put(row.get("STATUS_TIMESTAMP"));
				}
				array1.put(array);
			}

		} catch (Exception e) {
			e.printStackTrace();
			// TODO: handle exception
		}

		return array1;
	}

	/* Get Down Link Summary Listing */
	public JSONArray linkSummaryList() {

		JSONArray array = null;
		JSONArray array1 = null;
		int srno = 0;
		try {

			String sql = "SELECT intmon.NODE_IP,intmon.INTERFACE_NAME, intmon.STATUS_TIMESTAMP, addnode.LOCATION, addnode.COMPANY  FROM interface_monitoring intmon, add_node addnode WHERE intmon.OPER_STATUS='down' AND intmon.NODE_IP=addnode.DEVICE_IP";

			String db_type = environment.getRequiredProperty("DATABASE_TYPE");
			if (db_type.equalsIgnoreCase("mysql")) {
				sql = "SELECT intmon.NODE_IP,intmon.INTERFACE_NAME, intmon.STATUS_TIMESTAMP, addnode.LOCATION, addnode.COMPANY  FROM interface_monitoring intmon, add_node addnode WHERE intmon.OPER_STATUS='down' AND intmon.MONITORING_PARAM='Yes' AND intmon.NODE_IP=addnode.DEVICE_IP";
			} else if (db_type.equalsIgnoreCase("mssql")) {

			} else if (db_type.equalsIgnoreCase("oracle")) {

			}

			SQLQuery query = getSession().createSQLQuery(sql);
			query.setResultTransformer(Criteria.ALIAS_TO_ENTITY_MAP);

			List results = query.list();
			array1 = new JSONArray();

			for (Object object : results) {
				Map row = (Map) object;

				// System.out.println("Node IP: " + row.get("NODE_IP"));
				// System.out.println("Interface Name: " + row.get("INTERFACE_NAME"));
				// System.out.println("Branch: " + row.get("LOCATION"));
				// System.out.println("Customer: " + row.get("COMPANY"));
				// System.out.println("Event Timestamp: " + row.get("STATUS_TIMESTAMP"));
				String downDate = row.get("STATUS_TIMESTAMP").toString();

				String downSince = "NA";

				try {
					SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.S");
					Date parsedDate = dateFormat.parse(downDate);
					Timestamp dbtimestamp = new Timestamp(parsedDate.getTime());
					// System.out.println("DB TimeStamp:" + dbtimestamp);
					Timestamp currenttimestamp = new Timestamp(new Date().getTime());
					// System.out.println("Current Timestamp:" + currenttimestamp);
					long milliseconds = currenttimestamp.getTime() - dbtimestamp.getTime();

					long days = TimeUnit.MILLISECONDS.toDays(milliseconds);
					long hours = TimeUnit.MILLISECONDS.toHours(milliseconds)
							- TimeUnit.DAYS.toHours(TimeUnit.MILLISECONDS.toDays(milliseconds));
					long minutes = TimeUnit.MILLISECONDS.toMinutes(milliseconds)
							- TimeUnit.HOURS.toMinutes(TimeUnit.MILLISECONDS.toHours(milliseconds));
					long seconds = TimeUnit.MILLISECONDS.toSeconds(milliseconds)
							- TimeUnit.MINUTES.toSeconds(TimeUnit.MILLISECONDS.toMinutes(milliseconds));

					downSince = days + " Days " + hours + " Hours " + minutes + " Minutes " + seconds + " Seconds";

				} catch (Exception e) {
					// TODO: handle exception
					System.out.println("Exception in Parse Timestamp:" + e);
				}

				array = new JSONArray();
				array.put(row.get("NODE_IP"));
				array.put(row.get("INTERFACE_NAME"));
				array.put(row.get("LOCATION"));
				array.put(row.get("COMPANY"));
				if (!downSince.equalsIgnoreCase("NA")) {
					array.put(downSince);
				} else {
					array.put(row.get("STATUS_TIMESTAMP"));
				}
				array1.put(array);
			}

		} catch (Exception e) {
			e.printStackTrace();
			// TODO: handle exception
		}

		return array1;
	}

	/* Get Device Info */
	public JSONArray getDeviceInfo(String deviceInfo) {

		JSONArray array = null;
		JSONArray array1 = null;
		JSONObject jsonObject = null;
		int srno = 0;
		try {

			Query q = getSession().createQuery(
					"SELECT AN.DEVICE_IP,AN.DEVICE_NAME,AN.LOCATION,AN.COMPANY FROM AddNodeModel AN, NodeMonitoringModel NM WHERE AN.DEVICE_IP=NM.NODE_IP AND NM.NODE_STATUS=:deviceStatus AND AN.MONITORING_PARAM='Yes'");
			q.setParameter("deviceStatus", deviceInfo);
			List<?> dataList = q.list();
			array1 = new JSONArray();

			for (int i = 0; i < dataList.size(); i++) {
				// array = new JSONArray();
				jsonObject = new JSONObject();
				Object[] row = (Object[]) dataList.get(i);
				jsonObject.put("deviceip", row[0]);
				jsonObject.put("devicename", row[1]);
				jsonObject.put("location", row[2]);
				jsonObject.put("company", row[3]);

				array1.put(jsonObject);
			}

		} catch (Exception e) {
			e.printStackTrace();
			// TODO: handle exception
		}
		System.out.println("Device Status Array:" + array1);
		return array1;
	}

	/* Get Link Info */
	public JSONArray getLinkInfo(String linkInfo) {

		JSONArray array = null;
		JSONArray array1 = null;
		JSONObject jsonObject = null;
		int srno = 0;
		try {

			Query q = getSession().createQuery(
					"SELECT IFMON.NODE_IP,IFMON.INTERFACE_NAME,AN.LOCATION,AN.COMPANY FROM AddNodeModel AN, InterfaceMonitoring IFMON WHERE AN.DEVICE_IP=IFMON.NODE_IP AND IFMON.OPER_STATUS=:linkStatus AND IFMON.MONITORING_PARAM='Yes'");
			q.setParameter("linkStatus", linkInfo);
			List<?> dataList = q.list();
			array1 = new JSONArray();

			for (int i = 0; i < dataList.size(); i++) {
				// array = new JSONArray();
				jsonObject = new JSONObject();
				Object[] row = (Object[]) dataList.get(i);
				jsonObject.put("nodeip", row[0]);
				jsonObject.put("interfacename", row[1]);
				jsonObject.put("location", row[2]);
				jsonObject.put("company", row[3]);

				array1.put(jsonObject);
			}

		} catch (Exception e) {
			e.printStackTrace();
			// TODO: handle exception
		}
		System.out.println("Link Status Array:" + array1);
		return array1;
	}

	/* Top CPU Summary Listing */
	public JSONArray topCPUSummary() {

		JSONArray array = null;
		JSONArray array1 = null;
		int srno = 0;
		try {

			String sql = "SELECT * FROM Customers LIMIT 3;";

			String db_type = environment.getRequiredProperty("DATABASE_TYPE");
			if (db_type.equalsIgnoreCase("mysql")) {
				sql = "SELECT intmon.NODE_IP,intmon.INTERFACE_NAME, intmon.STATUS_TIMESTAMP, addnode.LOCATION, addnode.COMPANY  FROM interface_monitoring intmon, add_node addnode WHERE intmon.OPER_STATUS='down' AND intmon.NODE_IP=addnode.DEVICE_IP";
			} else if (db_type.equalsIgnoreCase("mssql")) {

			} else if (db_type.equalsIgnoreCase("oracle")) {

			}

			SQLQuery query = getSession().createSQLQuery(sql);
			query.setResultTransformer(Criteria.ALIAS_TO_ENTITY_MAP);

			List results = query.list();
			array1 = new JSONArray();

			for (Object object : results) {
				Map row = (Map) object;

				// System.out.println("Node IP: " + row.get("NODE_IP"));
				// System.out.println("Interface Name: " + row.get("INTERFACE_NAME"));
				// System.out.println("Branch: " + row.get("LOCATION"));
				// System.out.println("Customer: " + row.get("COMPANY"));
				// System.out.println("Event Timestamp: " + row.get("STATUS_TIMESTAMP"));
				String downDate = row.get("STATUS_TIMESTAMP").toString();

				String downSince = "NA";

				try {
					SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.S");
					Date parsedDate = dateFormat.parse(downDate);
					Timestamp dbtimestamp = new Timestamp(parsedDate.getTime());
					// System.out.println("DB TimeStamp:" + dbtimestamp);
					Timestamp currenttimestamp = new Timestamp(new Date().getTime());
					// System.out.println("Current Timestamp:" + currenttimestamp);
					long milliseconds = currenttimestamp.getTime() - dbtimestamp.getTime();

					long days = TimeUnit.MILLISECONDS.toDays(milliseconds);
					long hours = TimeUnit.MILLISECONDS.toHours(milliseconds)
							- TimeUnit.DAYS.toHours(TimeUnit.MILLISECONDS.toDays(milliseconds));
					long minutes = TimeUnit.MILLISECONDS.toMinutes(milliseconds)
							- TimeUnit.HOURS.toMinutes(TimeUnit.MILLISECONDS.toHours(milliseconds));
					long seconds = TimeUnit.MILLISECONDS.toSeconds(milliseconds)
							- TimeUnit.MINUTES.toSeconds(TimeUnit.MILLISECONDS.toMinutes(milliseconds));

					downSince = days + " Days " + hours + " Hours " + minutes + " Minutes " + seconds + " Seconds";

				} catch (Exception e) {
					// TODO: handle exception
					System.out.println("Exception in Parse Timestamp:" + e);
				}

				array = new JSONArray();
				array.put(row.get("NODE_IP"));
				array.put(row.get("INTERFACE_NAME"));
				array.put(row.get("LOCATION"));
				array.put(row.get("COMPANY"));
				if (!downSince.equalsIgnoreCase("NA")) {
					array.put(downSince);
				} else {
					array.put(row.get("STATUS_TIMESTAMP"));
				}
				array1.put(array);
			}

		} catch (Exception e) {
			e.printStackTrace();
			// TODO: handle exception
		}

		return array1;
	}

	/* Get Interface Info */
	public JSONArray interfaceInfo(String deviceIP, String intName) {
		System.out.println("Method Called..");
		JSONArray array = null;
		JSONArray array1 = null;
		JSONObject jsonObject = null;

		try {

			Query q = getSession().createQuery(
					"SELECT INFMON.NODE_IP,INFMON.INTERFACE_NAME,INFMON.ALIAS_NAME,INFMON.INTERFACE_TYPE,INFMON.INTERFACE_ID,INFMON.ADMIN_STATUS,INFMON.OPER_STATUS,INFMON.INTERFACE_IP,INFMON.MTU,INFMON.CRC_ERROR,INFMON.PROCURED_BANDWIDTH,INFMON.IN_TRAFFIC,INFMON.OUT_TRAFFIC,INFMON.INTERFACE_MACADDRESS FROM InterfaceMonitoring AS INFMON where INFMON.NODE_IP=:nodeIP AND INFMON.INTERFACE_NAME=:interfaceName");
			q.setParameter("nodeIP", deviceIP);
			q.setParameter("interfaceName", intName);
			List<?> dataList = q.list();
			array1 = new JSONArray();

			for (int i = 0; i < dataList.size(); i++) {

				jsonObject = new JSONObject();
				Object[] row = (Object[]) dataList.get(i);
				jsonObject.put("node_ip", row[0]);
				jsonObject.put("int_name", row[1]);
				jsonObject.put("alias_name", row[2]);
				jsonObject.put("int_type", row[3]);
				jsonObject.put("int_id", row[4]);
				jsonObject.put("admin_status", row[5]);
				jsonObject.put("oper_status", row[6]);
				jsonObject.put("int_ip", row[7]);
				jsonObject.put("mtu", row[8]);
				jsonObject.put("crc", row[9]);
				jsonObject.put("procure_bw", row[10]);
				jsonObject.put("in_bw", row[11]);
				jsonObject.put("out_bw", row[12]);
				jsonObject.put("int_mac", row[13]);

				array1.put(jsonObject);
			}
			System.out.println("Interface Info Array:" + array1.toString());
		} catch (Exception e) {
			e.printStackTrace();
			// TODO: handle exception
		}

		return array1;
	}

	/* Get Interface Status History */
	public JSONArray interfaceStatusHistory(String deviceIP, String intName) {
		System.out.println("Method Called..");
		JSONArray array = null;
		JSONArray array1 = null;
		JSONObject jsonObject = null;

		try {

			Query q = getSession().createQuery(
					"SELECT INFMON.INTERFACE_NAME,INFMON.INTERFACE_STATUS,INFMON.INTERFACE_STATUS_TYPE,INFMON.EVENT_TIMESTAMP FROM InterfaceStatusHistoryModel AS INFMON where INFMON.NODE_IP=:nodeIP AND INFMON.INTERFACE_NAME=:interfaceName");
			q.setParameter("nodeIP", deviceIP);
			q.setParameter("interfaceName", intName);
			q.setMaxResults(5);
			List<?> dataList = q.list();
			array1 = new JSONArray();

			for (int i = 0; i < dataList.size(); i++) {

				jsonObject = new JSONObject();
				Object[] row = (Object[]) dataList.get(i);
				jsonObject.put("int_name", row[0]);
				jsonObject.put("int_status", row[1]);
				jsonObject.put("int_status_type", row[2]);
				jsonObject.put("event_timestamp", row[3]);
				array1.put(jsonObject);
			}
			System.out.println("Interface History Array:" + array1.toString());
		} catch (Exception e) {
			e.printStackTrace();
			// TODO: handle exception
		}

		return array1;
	}

	/* Get Interface CRC Log */
	public JSONArray interfaceCRCLog(String deviceIP, String intName) {
		System.out.println("Method Called..");
		JSONArray array = null;
		JSONArray array1 = null;
		JSONObject jsonObject = null;

		try {

			Query q = getSession().createQuery(
					"SELECT INFMON.INTERFACE_NAME,INFMON.OLD_CRC_VAL,INFMON.NEW_CRC_VAL,INFMON.EVENT_TIMESTAMP FROM InterfaceCRCHistory AS INFMON where INFMON.NODE_IP=:nodeIP AND INFMON.INTERFACE_NAME=:interfaceName");
			q.setParameter("nodeIP", deviceIP);
			q.setParameter("interfaceName", intName);
			q.setMaxResults(5);
			List<?> dataList = q.list();
			array1 = new JSONArray();

			for (int i = 0; i < dataList.size(); i++) {

				jsonObject = new JSONObject();
				Object[] row = (Object[]) dataList.get(i);
				jsonObject.put("int_name", row[0]);
				jsonObject.put("old_crc", row[1]);
				jsonObject.put("new_crc", row[2]);
				jsonObject.put("event_timestamp", row[3]);
				array1.put(jsonObject);
			}
			System.out.println("Interface CRC Array:" + array1.toString());
		} catch (Exception e) {
			e.printStackTrace();
			// TODO: handle exception
		}

		return array1;
	}

	/* Get Interface Uptime Log */
	public JSONArray interfaceUptimeLog(String deviceIP, String intName) {
		System.out.println("Method Called..");

		JSONArray array1 = null;
		JSONArray array2 = null;
		JSONArray array3 = null;
		JSONArray array4 = null;
		JSONObject jsonObject = null;

		try {

			Query q = getSession().createQuery(
					"SELECT INFMON.UPTIME_PERCENT,INFMON.DOWNTIME_PERCENT,DATE(INFMON.EVENT_TIMESTAMP) FROM InterfaceAvailablity AS INFMON where INFMON.NODE_IP=:nodeIP AND INFMON.INTERFACE_NAME=:interfaceName");
			q.setParameter("nodeIP", deviceIP);
			q.setParameter("interfaceName", intName);
			q.setMaxResults(5);

			List<?> dataList = q.list();
			array1 = new JSONArray();
			array2 = new JSONArray();
			array3 = new JSONArray();
			array4 = new JSONArray();
			

			for (int i = 0; i < dataList.size(); i++) {

				Object[] row = (Object[]) dataList.get(i);
				array2.put(row[0]);
				array3.put(row[1]);
				array4.put(row[2]);
			}
			jsonObject = new JSONObject();
			jsonObject.put("uptime", array2);
			jsonObject.put("downtime", array3);
			jsonObject.put("datetime", array4);
			array1.put(jsonObject);
			System.out.println("Interface Uptime Array:" + array1.toString());
		} catch (Exception e) {
			e.printStackTrace();
			// TODO: handle exception
		}

		return array1;
	}

	/* Get Interface Bandwidth */
	public JSONArray interfaceBandwidthUtil(String deviceIP, String intName) {

		JSONArray array1 = null;
		JSONObject jsonObject = null;

		try {

			Query q = getSession().createQuery(
					"SELECT INFMON.IN_TRAFFIC,INFMON.OUT_TRAFFIC,PROCURED_BANDWIDTH FROM InterfaceMonitoring AS INFMON where INFMON.NODE_IP=:nodeIP AND INFMON.INTERFACE_NAME=:interfaceName");
			q.setParameter("nodeIP", deviceIP);
			q.setParameter("interfaceName", intName);

			List<?> dataList = q.list();
			array1 = new JSONArray();
			for (int i = 0; i < dataList.size(); i++) {

				jsonObject = new JSONObject();
				Object[] row = (Object[]) dataList.get(i);
				jsonObject.put("in_traffic", row[0]);
				jsonObject.put("out_traffic", row[1]);
				jsonObject.put("procured_bw", row[2]);
				array1.put(jsonObject);
			}

			System.out.println("Interface bandwidth Array:" + array1.toString());
		} catch (Exception e) {
			e.printStackTrace();
			// TODO: handle exception
		}

		return array1;
	}

	/* Get Toptalker Link Summary Listing */
	public JSONArray topTalkerLinkSummaryList() {

		JSONArray array = null;
		JSONArray array1 = null;
		int srno = 0;
		try {

			String sql = "SELECT intmon.NODE_IP,intmon.INTERFACE_NAME, addnode.LOCATION, addnode.COMPANY  FROM interface_monitoring intmon, add_node addnode WHERE intmon.MONITORING_PARAM='Yes' AND intmon.NODE_IP=addnode.DEVICE_IP";

			String db_type = environment.getRequiredProperty("DATABASE_TYPE");
			if (db_type.equalsIgnoreCase("mysql")) {
				sql = "SELECT intmon.NODE_IP,intmon.INTERFACE_NAME, addnode.LOCATION, addnode.COMPANY  FROM interface_monitoring intmon, add_node addnode WHERE intmon.MONITORING_PARAM='Yes' AND intmon.NODE_IP=addnode.DEVICE_IP";
			} else if (db_type.equalsIgnoreCase("mssql")) {

			} else if (db_type.equalsIgnoreCase("oracle")) {

			}

			SQLQuery query = getSession().createSQLQuery(sql);
			query.setResultTransformer(Criteria.ALIAS_TO_ENTITY_MAP);

			List results = query.list();
			array1 = new JSONArray();

			for (Object object : results) {
				Map row = (Map) object;
				array = new JSONArray();
				array.put(row.get("NODE_IP"));
				// array.put(row.get("INTERFACE_NAME"));
				array.put("<a href=\"toptalkerinfo\">" + row.get("INTERFACE_NAME") + "</a>");
				// array.put("<a href=\"/NPMWebConsole/dashboard/toptalkerinfo\">" +
				// row.get("INTERFACE_NAME") + "</a>");
				array.put(row.get("LOCATION"));
				array.put(row.get("COMPANY"));
				array1.put(array);
			}

		} catch (Exception e) {
			e.printStackTrace();
			// TODO: handle exception
		}

		return array1;
	}
	
	// Export Node Latency
		public JSONArray exportNodeLatency(String from_date, String to_date, String ip_address) {

			JSONArray jsonObj = new JSONArray();
			JSONArray array = null;

			try {
			
				Query q = getSession().createQuery("from LatencyHisotryModel where NODE_IP=:NODE_IP AND EVENT_TIMESTAMP BETWEEN :fromDate AND :toDate");
				q.setParameter("NODE_IP", ip_address);
				q.setParameter("fromDate", Timestamp.valueOf(from_date));
				q.setParameter("toDate", Timestamp.valueOf(to_date));
			
				int srno = 0;
				List<LatencyHisotryModel> dataList = q.list();

			
				for (LatencyHisotryModel data : dataList) {
					srno++;
					array = new JSONArray();
					array.put(data.getNODE_IP());
					array.put(data.getAVG_LATENCY());
					array.put(data.getMAX_LATENCY());
					array.put(data.getPACKET_LOSS());
					array.put(data.getEVENT_TIMESTAMP());
					
					jsonObj.put(array);
					

				}
			} catch (Exception e) {
				System.out.println("Exception:" + e);
			}
			System.out.println("list = "+jsonObj);
			return jsonObj;
		}

}
