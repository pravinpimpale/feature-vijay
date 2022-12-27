package npm.dashboard.daoImpl;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;


import dynamicPassword.Decryption;
import npm.configuration.AbstractDao;
import npm.dashboard.dao.NodeDetailsDao;
import npm.model.AddNodeModel;
import npm.model.InterfaceMonitoring;
import npm.model.NodeAvailablity;
import npm.model.NodeHealthMonitoring;
import npm.model.NodeMonitoringModel;
import npm.model.NodeStatusHistory;
import npm.model.UserMasterModel;

@Repository
@Transactional
public class NodeDetailsDaoImpl extends AbstractDao<Integer, AddNodeModel> implements NodeDetailsDao {

	public JSONObject nodeLatencyPacketDrop(String ip_address) {

		JSONObject jsonObj = new JSONObject();
		JSONArray latency_obj = null;
		JSONArray packetLoss_obj = null;
		try {
			System.out.println("ip address = " + ip_address);
			Query q = getSession().createQuery("from NodeMonitoringModel where NODE_IP =:NODE_IP");
			q.setParameter("NODE_IP", ip_address);
			List<NodeMonitoringModel> list = q.list();

			for (NodeMonitoringModel data : list) {
				float latency = data.getLATENCY();
				float packetLoss = data.getPACKET_LOSS();

				latency_obj = new JSONArray();
				latency_obj.put(latency);

				packetLoss_obj = new JSONArray();
				packetLoss_obj.put(packetLoss);
			}

		} catch (Exception e) {
			System.out.println("Exception:" + e);
		}

		jsonObj.put("latency", latency_obj);
		jsonObj.put("packetLoss", packetLoss_obj);
		System.out.println("data = " + jsonObj);
		return jsonObj;
	}

	public JSONObject nodeAddNode(String ip_address) {

		JSONObject jsonObj = new JSONObject();
		JSONArray dataObj = null;

		try {
			System.out.println("ip address = " + ip_address);
			Query q = getSession().createQuery("from AddNodeModel where DEVICE_IP =:DEVICE_IP");
			q.setParameter("DEVICE_IP", ip_address);
			List<AddNodeModel> list = q.list();

			for (AddNodeModel data : list) {

				dataObj = new JSONArray();

				dataObj.put(data.getCOMPANY());
				dataObj.put(data.getDEVICE_NAME());
				dataObj.put(data.getDEVICE_TYPE());
				dataObj.put(data.getGROUP_NAME());
				dataObj.put(data.getDISTRICT());
				dataObj.put(data.getLOCATION());
				dataObj.put(data.getSERVICE_PROVIDER());
				dataObj.put(data.getSNMP());
				dataObj.put(data.getSTATE());
				dataObj.put(data.getZONE());

			}

		} catch (Exception e) {
			System.out.println("Exception:" + e);
		}
		System.out.println(dataObj);
		jsonObj.put("allData", dataObj);
		return jsonObj;
	}

	public JSONObject nodeAvailabilityDetails(String ip_address) {

		JSONObject jsonObj = new JSONObject();

		JSONArray downtime_percent = new JSONArray();
		JSONArray uptime_percent = new JSONArray();
		JSONArray event_timestamp = new JSONArray();

		JSONArray downtime_percent_obj = null;
		JSONArray uptime_percent_obj = null;
		JSONArray event_timestamp_obj = null;

		try {
			System.out.println("ip address = " + ip_address);
			Query q = getSession().createQuery("from NodeAvailablity where NODE_IP =:NODE_IP");
			q.setParameter("NODE_IP", ip_address);
			List<NodeAvailablity> list = q.list();

			for (NodeAvailablity data : list) {

				double downtimePercent = data.getDOWNTIME_PERCENT();
				double uptimePercent = data.getUPTIME_PERCENT();
				Date eventTimestamp = data.getEVENT_TIMESTAMP();

				downtime_percent_obj = new JSONArray();
				downtime_percent_obj.put(downtimePercent);
				downtime_percent.put(downtime_percent_obj);

				uptime_percent_obj = new JSONArray();
				uptime_percent_obj.put(uptimePercent);
				uptime_percent.put(uptime_percent_obj);

				event_timestamp_obj = new JSONArray();
				event_timestamp_obj.put(eventTimestamp);
				event_timestamp.put(event_timestamp_obj);

			}

		} catch (Exception e) {
			System.out.println("Exception:" + e);
		}

		jsonObj.put("uptimePercent", uptime_percent);
		jsonObj.put("downtimePercent", downtime_percent);
		jsonObj.put("eventTimestamp", event_timestamp);
		return jsonObj;
	}

	public JSONArray nodeStatusHistoryDetails(String ip_address) {

		JSONArray jsonData = null;
		JSONArray jsonMain = new JSONArray();

		try {

			System.out.println("IP = " + ip_address);
			Query q = getSession().createQuery("from NodeStatusHistory where NODE_IP=:NODE_IP ORDER BY ID DESC");
			q.setParameter("NODE_IP", ip_address);
			q.setMaxResults(5);
			
//			Query q = getSession().createQuery(
//					"SELECT INFMON.INTERFACE_NAME,INFMON.INTERFACE_STATUS,INFMON.INTERFACE_STATUS_TYPE,INFMON.EVENT_TIMESTAMP FROM InterfaceStatusHistoryModel AS INFMON where INFMON.NODE_IP=:nodeIP AND INFMON.INTERFACE_NAME=:interfaceName");
//			q.setParameter("nodeIP", deviceIP);
//			q.setParameter("interfaceName", intName);
//			q.setMaxResults(5);
//			
			
			List<NodeStatusHistory> list = q.list();

			for (NodeStatusHistory data : list) {
				jsonData = new JSONArray();
				jsonData.put(data.getNODE_IP());
//				jsonData.put(data.getNODE_STATUS());
				if (data.getNODE_STATUS().equals("Up")) {
					jsonData.put("<small class='text-success mr-1'> <i class='fas fa-arrow-up'></i> Up </small>");
				} else {
					jsonData.put("<small class='text-danger mr-1'> <i class='fas fa-arrow-down'></i> Down </small>");
				}
				jsonData.put(data.getEVENT_TIMESTAMP());
				jsonMain.put(jsonData);

			}

		} catch (Exception e) {
			System.out.println("Exception:" + e);
		}
//		jsonObj.put("nodeStatus", node_status_array);
//		jsonObj.put("eventTimestamp", event_timestamp_array);
		return jsonMain;
	}

	public JSONObject nodeHealthMonitoringDetails(String ip_address) {

		JSONObject jsonObj = new JSONObject();
		JSONArray dataObj = null;

		try {
			System.out.println("ip address = " + ip_address);
			Query q = getSession().createQuery("from NodeHealthMonitoring where NODE_IP=:NODE_IP");
			q.setParameter("NODE_IP", ip_address);
			List<NodeHealthMonitoring> list = q.list();

			for (NodeHealthMonitoring data : list) {

				dataObj = new JSONArray();

				dataObj.put(data.getCPU_STATUS());
				dataObj.put(data.getCPU_UTILIZATION());
				dataObj.put(data.getFREE_MEMORY());
				dataObj.put(data.getMAKE_AND_MODEL());
				dataObj.put(data.getMEMORY_STATUS());
				dataObj.put(data.getMEMORY_UTILIZATION());
				dataObj.put(data.getNODE_NAME());
				dataObj.put(data.getSERIAL_NO());
				dataObj.put(data.getTEMPERATURE());
				dataObj.put(data.getTOTAL_MEMORY());
				dataObj.put(data.getUPTIME());
				dataObj.put(data.getUSED_MEMORY());
				dataObj.put(data.getVERSION());

			}

		} catch (Exception e) {
			System.out.println("Exception:" + e);
		}
		System.out.println(dataObj);
		jsonObj.put("allData", dataObj);
		return jsonObj;
	}

	public JSONArray InterfaceStatusHistoryDetails(String ip_address) {

		JSONArray jsonData = null;
		JSONArray jsonMain = new JSONArray();

		try {

			System.out.println("IP = " + ip_address);
			Query q = getSession().createQuery("from InterfaceMonitoring where NODE_IP=:NODE_IP and MONITORING_PARAM=:MONITORING_PARAM ");
			q.setParameter("NODE_IP", ip_address);
			q.setParameter("MONITORING_PARAM", "Yes");
			List<InterfaceMonitoring> list = q.list();

			for (InterfaceMonitoring data : list) {
				jsonData = new JSONArray();
				jsonData.put(data.getINTERFACE_NAME());
				jsonData.put(data.getINTERFACE_IP());
//				jsonData.put(data.getOPER_STATUS());
				if (data.getOPER_STATUS().equals("up")) {
					jsonData.put("<small class='text-success mr-1'> <i class='fas fa-arrow-up'></i> Up </small>");
				} else {
					jsonData.put("<small class='text-danger mr-1'> <i class='fas fa-arrow-down'></i> Down </small>");
				}

				jsonData.put(data.getALIAS_NAME());
				jsonMain.put(jsonData);

			}

		} catch (Exception e) {
			System.out.println("Exception:" + e);
		}
//		jsonObj.put("nodeStatus", node_status_array);
//		jsonObj.put("eventTimestamp", event_timestamp_array);
		return jsonMain;
	}

	public JSONArray basicInfoDetails(String ip_address) {
		System.out.println("basicInfoDetails Dao IMPL" );
		JSONArray arrayJson = new JSONArray();
		JSONObject jsonObjData = new JSONObject();
		try {
			Query q = getSession().createQuery("from AddNodeModel where DEVICE_IP=:NODE_IP ");
			q.setParameter("NODE_IP", ip_address);
			List<AddNodeModel> object = q.list();
			for (AddNodeModel list : object) {
				
				jsonObjData.put("Node_ip", list.getDEVICE_IP());
				jsonObjData.put("NodeNAme", list.getDEVICE_NAME());
				jsonObjData.put("Location", list.getLOCATION());
				jsonObjData.put("Company", list.getCOMPANY());
//				jsonObj.put("State", list.getSTATE());
				jsonObjData.put("District", list.getDISTRICT());
			}
		} catch (Exception e) {
			System.out.println("Exception:" + e);
		}

		try
		{
			Query q1 = getSession().createQuery("from NodeHealthMonitoring where NODE_IP=:NODE_IP ");
			q1.setParameter("NODE_IP", ip_address);
			List<NodeHealthMonitoring> nodeHealthList = q1.list();
			for (NodeHealthMonitoring list : nodeHealthList) {
				jsonObjData.put("Version", list.getVERSION());
				jsonObjData.put("Model", list.getMAKE_AND_MODEL());
				jsonObjData.put("Uptime", list.getUPTIME());
				jsonObjData.put("Total_RAM", list.getTOTAL_MEMORY());
				jsonObjData.put("Used_RAM", list.getUSED_MEMORY());
				jsonObjData.put("Free_RAM", list.getFREE_MEMORY());
				jsonObjData.put("cpuUtilization", list.getCPU_UTILIZATION());;
			}
		} catch (Exception e) {
			System.out.println("Exception:" + e);
		}
		
		try
		{
		arrayJson.put(jsonObjData);
		} catch (Exception e) {
			System.out.println("Exception:" + e);
		}

		System.out.println(arrayJson);
		
		return arrayJson;
	}

	public JSONArray nodeUpTimeStatusDetails(String ip_address) {

		JSONArray jsonData = null;

		JSONArray upArray = null;
		JSONArray downArray = null;
		JSONArray catArray = null;

		try {

			System.out.println("IP = " + ip_address);
//			Query q = getSession().createQuery("FROM NodeAvailablity where EVENT_TIMESTAMP BETWEEN CURDATE()-7 AND CURDATE() AND NODE_IP=:NODE_IP ");
			Query q = getSession().createQuery("FROM NodeAvailablity where NODE_IP=:NODE_IP");

			q.setParameter("NODE_IP", ip_address);
//			q.setFirstResult(start);
			q.setMaxResults(5);
			List<NodeAvailablity> list = q.list();

			String category = "";
			List<String> catList = new ArrayList();
			List<Double> upList = new ArrayList();
			List<Double> downList = new ArrayList();
			double uptime = 0;
			double downtime = 0;
			for (NodeAvailablity data : list) {

				downArray = new JSONArray();
				catArray = new JSONArray();
				upArray = new JSONArray();
				category = data.getEVENT_TIMESTAMP().toString();
				catList.add(category);

				uptime = data.getUPTIME_PERCENT();
				upList.add(uptime);

				downtime = data.getDOWNTIME_PERCENT();
				downList.add(downtime);

			}
			upArray = new JSONArray(upList);
			downArray = new JSONArray(downList);
			catArray = new JSONArray(catList);

			JSONObject jsonMain = new JSONObject();
			jsonMain.put("upArray", upArray);
			jsonMain.put("downArray", downArray);
			jsonMain.put("catArray", catArray);
			jsonData = new JSONArray();
			jsonData.put(jsonMain);
		} catch (Exception e) {
			System.out.println("Exception:" + e);
		}
//		jsonObj.put("nodeStatus", node_status_array);
//		jsonObj.put("eventTimestamp", event_timestamp_array);
		return jsonData;
	}

	public JSONArray nodeLatencyStatusDetails(String ip_address) {
		System.out.println("Latency Packet drop live graph ip:" +ip_address);
		JSONArray array1 = null;
		JSONObject jsonObject = null;

		try {

			Query q = getSession().createQuery(
					"SELECT LATENCY,PACKET_LOSS  FROM NodeMonitoringModel where NODE_IP=:nodeIP");
			q.setParameter("nodeIP", ip_address);

			List<?> dataList = q.list();
			array1 = new JSONArray();
			for (int i = 0; i < dataList.size(); i++) {
				jsonObject = new JSONObject();
				Object[] row = (Object[]) dataList.get(i);
				jsonObject.put("latency", row[0]);
				jsonObject.put("packetLoss", row[1]);
				array1.put(jsonObject);
			}

			System.out.println("Latency Packet drop Array:" + array1.toString());
		} catch (Exception e) {
			e.printStackTrace();
			// TODO: handle exception
		}

		return array1;


//		JSONArray jsonData = null;
//
//		JSONArray upArray = null;
//		JSONArray downArray = null;
//		JSONArray catArray = null;
//
//		try {
//
//			System.out.println("IP = " + ip_address);
//			Date date = new Date();
//			String currentDate= new SimpleDateFormat("yyyy-MM-dd").format(date);
//			currentDate = currentDate+"%";
//			Query q = getSession().createQuery("FROM NodeMonitoringModel where NODE_IP=:NODE_IP");
////			
////			Query q = getSession().createQuery("FROM NodeMonitoringModel where NODE_IP=:NODE_IP AND LATENCY_TIMESTAMP LIKE '"+currentDate+"'");
////			Query q = getSession().createQuery("FROM NodeMonitoringModel where NODE_IP=:NODE_IP");
//
//			q.setParameter("NODE_IP", ip_address);
////			q.setFirstResult(start);
////			q.setMaxResults(5);
//			List<NodeMonitoringModel> list = q.list();
//
//			String category = "";
//			List<String> catList = new ArrayList();
//			List<Double> upList = new ArrayList();
//			List<Double> downList = new ArrayList();
//			double uptime = 0;
//			double downtime = 0;
//			for (NodeMonitoringModel data : list) {
//
//				downArray = new JSONArray();
//				catArray = new JSONArray();
//				upArray = new JSONArray();
////				category = data.getLATENCY().toString();
////				catList.add(category);
//
//				uptime = data.getPACKET_LOSS();
//				upList.add(uptime);
//
//				downtime = data.getLATENCY();
//				downList.add(downtime);
//
//			}
//			upArray = new JSONArray(upList);
//			downArray = new JSONArray(downList);
////			catArray = new JSONArray(catList);
//
//			JSONObject jsonMain = new JSONObject();
//			jsonMain.put("packetLoss", upArray);
//			jsonMain.put("latency", downArray);
////			jsonMain.put("catArray", catArray);
//			jsonData = new JSONArray();
//			jsonData.put(jsonMain);
//		} catch (Exception e) {
//			System.out.println("Exception:" + e);
//		}
//		
//		System.out.println("LatencyPacket Drop:" + jsonData);
//		
////		jsonObj.put("nodeStatus", node_status_array);
////		jsonObj.put("eventTimestamp", event_timestamp_array);
//		return jsonData;
	}
//	@Override
	public String getDBVerification(String username, String Password) {
		System.out.println("Inside LoginDaoImpl getDBVerification method");
		String db_password = null;
		String db_username = null;
//		byte[] buffer = new byte[2000];
//		String usrname = "NA";
		try {
			System.out.println("Before Projection List ");
			Session s = getSession();
			System.out.println("s =" + s);
			Query query = getSession().createQuery("from UserMasterModel where USERNAME=:u");
			query.setParameter("u", username);
			List<UserMasterModel> dataList = query.list();
			for (UserMasterModel newObj : dataList) {
				db_username = newObj.getUSERNAME();
				Decryption decrypt = new Decryption();
				db_password = new String(decrypt.decrypt(newObj.getPASSWORD()));
//				db_password=newObj.getPASS();
			
			}
		} catch (

		Exception e) {
			e.printStackTrace();
			// TODO: handle exception
		}
		System.out.println("db_username = "+db_username+"\ndb_password = "+db_password);
		return db_password + "~" + db_username;
	}

}
