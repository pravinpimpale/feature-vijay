package npm.report.daoImpl;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.StringTokenizer;
import java.util.concurrent.TimeUnit;

import javax.servlet.http.HttpSession;

import org.hibernate.Criteria;
import org.hibernate.Query;
import org.json.JSONArray;
import org.json.JSONObject;
import org.json.simple.parser.JSONParser;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import npm.admin.beans.CpuThresholdHealthReportBean;
import npm.admin.beans.LatencyHistoryReportBean;
import npm.admin.beans.LatencyThresholdReportBean;
import npm.admin.beans.MemoryThresholdReportBean;
import npm.admin.beans.NodeAvailabilityBean;
import npm.admin.beans.NodeHealthHistoryReportBean;
import npm.admin.beans.NodeStatusReportBean;
import npm.configuration.AbstractDao;
import npm.model.AddNodeModel;
import npm.model.CUSTOME_TOPOLOGY_MAP;
import npm.model.ConfigBackup;
import npm.model.DeviceAlertLog;
import npm.model.InterfaceAvailablity;
import npm.model.InterfaceBWHistory;
import npm.model.InterfaceMonitoring;
import npm.model.LatencyHisotryModel;
import npm.model.NodeAvailablity;
import npm.model.NodeHealthHistory;
import npm.model.NodeHealthMonitoring;
import npm.model.NodeMonitoringModel;
import npm.model.TOPOLOGY_NODE_POSTIONS;
import npm.model.custome_topology_view;
import npm.report.dao.NodeReportDao;

@Repository
@Transactional
public class NodeReportDaoImpl extends AbstractDao<Integer, AddNodeModel> implements NodeReportDao {

	public List<NodeStatusReportBean> NodeStatusReport(String from_date, String to_date, List<String> ip_list) {

		String ip_data = ip_list.toString().replace("[", "").replace("]", "").replace(",", "','").replace(" ", "");
		System.out.println("IP List:" + ip_data + ":" + from_date + to_date);
		String query = "select report.ID,report.NODE_IP,report.NODE_STATUS,report.EVENT_TIMESTAMP,node.DEVICE_NAME,node.LOCATION,node.DISTRICT,node.STATE,node.ZONE,node.GROUP_NAME from add_node node join node_status_history report on node.DEVICE_IP=report.NODE_IP where node.DEVICE_IP in ('"
				+ ip_data + "') and  report.EVENT_TIMESTAMP BETWEEN '" + from_date + "' AND '" + to_date + "'";
		System.out.println("query :: " + query);
		Query q = getSession().createSQLQuery(query);
		List<NodeStatusReportBean> dataList = new ArrayList<NodeStatusReportBean>();

		List<Object[]> data = q.list();
		System.out.println("Size Data:" + data.size());
		long id = 0;
		for (Object[] a : data) {
			id++;
			NodeStatusReportBean bean = new NodeStatusReportBean();
			bean.setID(id);
			bean.setNODE_IP(a[1].toString());
			bean.setNODE_STATUS(a[2].toString());
			bean.setEVENT_TIMESTAMP(a[3].toString());
			bean.setDEVICE_NAME(a[4].toString());
			bean.setLOCATION(a[5].toString());
			bean.setDISTRICT(a[6].toString());
			bean.setSTATE(a[7].toString());
			bean.setZONE(a[8].toString());
			bean.setGROUP_NAME(a[9].toString());
			dataList.add(bean);
		}

		return dataList;
	}

	public List<LatencyHistoryReportBean> latencyHistoryReport(String from_date, String to_date, List<String> ip_list) {

		String ip_data = ip_list.toString().replace("[", "").replace("]", "").replace(",", "','").replace(" ", "");
		System.out.println("IP List:" + ip_data + ":" + from_date + to_date);
		String query = "select report.ID,report.NODE_IP,report.MIN_LATENCY,report.MAX_LATENCY,report.AVG_LATENCY,report.PACKET_LOSS,report.EVENT_TIMESTAMP,node.DEVICE_NAME,node.LOCATION,node.DISTRICT,node.STATE,node.ZONE,node.GROUP_NAME from add_node node join LATENCY_HISTORY report on node.DEVICE_IP=report.NODE_IP where node.DEVICE_IP in ('"
				+ ip_data + "') and  report.EVENT_TIMESTAMP BETWEEN '" + from_date + "' AND '" + to_date + "'";
		Query q = getSession().createSQLQuery(query);
		List<LatencyHistoryReportBean> dataList = new ArrayList<LatencyHistoryReportBean>();

		List<Object[]> data = q.list();
		System.out.println("Size Data:" + data.size());
		long id = 0;
		for (Object[] a : data) {
			try {

				id++;
				LatencyHistoryReportBean bean = new LatencyHistoryReportBean();
				bean.setID(id);
				bean.setNODE_IP(a[1].toString());
				bean.setMIN_LATENCY(Double.parseDouble(a[2].toString()));
				bean.setMAX_LATENCY(Double.parseDouble(a[3].toString()));
				bean.setAVG_LATENCY(Double.parseDouble(a[4].toString()));
				bean.setPACKET_LOSS(Double.parseDouble(a[5].toString()));
				bean.setEVENT_TIMESTAMP(a[6].toString());
				bean.setDEVICE_NAME(a[7].toString());
				bean.setLOCATION(a[8].toString());
				bean.setDISTRICT(a[9].toString());
				bean.setSTATE(a[10].toString());
				bean.setZONE(a[11].toString());
				bean.setGROUP_NAME(a[12].toString());
				dataList.add(bean);
			} catch (Exception e) {
				// TODO: handle exception
			}
		}

		return dataList;
	}

	public List<LatencyThresholdReportBean> latencyThresholdReport(String from_date, String to_date,
			List<String> ip_list) {

		String ip_data = ip_list.toString().replace("[", "").replace("]", "").replace(",", "','").replace(" ", "");
		System.out.println("IP List:" + ip_data + ":" + from_date + to_date);
		String query = "select report.ID,report.NODE_IP,report.LATENCY_THRESHOLD,report.LATENCY_VAL,report.LATENCY_STATUS,report.EVENT_TIMESTAMP,node.DEVICE_NAME,node.LOCATION,node.DISTRICT,node.STATE,node.ZONE,node.GROUP_NAME from add_node node join LATENCY_THRESHOLD_HISTORY report on node.DEVICE_IP=report.NODE_IP where node.DEVICE_IP in ('"
				+ ip_data + "') and  report.EVENT_TIMESTAMP BETWEEN '" + from_date + "' AND '" + to_date + "'";
		Query q = getSession().createSQLQuery(query);
		List<LatencyThresholdReportBean> dataList = new ArrayList<LatencyThresholdReportBean>();

		List<Object[]> data = q.list();
		System.out.println("Size Data:" + data.size());
		long id = 0;
		for (Object[] a : data) {
			try {

				id++;
				LatencyThresholdReportBean bean = new LatencyThresholdReportBean();
				bean.setID(id);
				bean.setNODE_IP(a[1].toString());
				bean.setLATENCY_THRESHOLD(Integer.parseInt(a[2].toString()));
				bean.setLATENCY_VAL(Double.parseDouble(a[3].toString()));
				bean.setLATENCY_STATUS(a[4].toString());

				bean.setEVENT_TIMESTAMP(a[5].toString());
				bean.setDEVICE_NAME(a[6].toString());
				bean.setLOCATION(a[7].toString());
				bean.setDISTRICT(a[8].toString());
				bean.setSTATE(a[9].toString());
				bean.setZONE(a[10].toString());
				bean.setGROUP_NAME(a[11].toString());
				dataList.add(bean);
			} catch (Exception e) {
				// TODO: handle exception
			}
		}

		return dataList;
	}

	public List<NodeAvailabilityBean> nodeAvailabilityReport(String from_date, String to_date, List<String> ip_list) {

		String ip_data = ip_list.toString().replace("[", "").replace("]", "").replace(",", "','").replace(" ", "");
		System.out.println("IP List:" + ip_data + ":" + from_date + to_date);
		String query = "select report.ID,report.NODE_IP,report.UPTIME_PERCENT,report.UPTIME_STR,report.DOWNTIME_PERCENT,report.DOWNTIME_STR,report.EVENT_TIMESTAMP,node.DEVICE_NAME,node.LOCATION,node.DISTRICT,node.STATE,node.ZONE,node.GROUP_NAME from add_node node join NODE_AVAILABILITY report on node.DEVICE_IP=report.NODE_IP where node.DEVICE_IP in ('"
				+ ip_data + "') and  report.EVENT_TIMESTAMP BETWEEN '" + from_date + "' AND '" + to_date + "'";
		Query q = getSession().createSQLQuery(query);
		List<NodeAvailabilityBean> dataList = new ArrayList<NodeAvailabilityBean>();

		List<Object[]> data = q.list();
		System.out.println("Size Data:" + data.size());
		long id = 0;
		for (Object[] a : data) {
			try {
				
				String var_uptime="NA";
//				 try {
//					 int up_second_day=(Integer) a[3];
//					 
//                     int day = (int) TimeUnit.SECONDS.toDays(up_second_day);
//                     long hours = TimeUnit.SECONDS.toHours(up_second_day) - (day * 24);
//                     long minute = TimeUnit.SECONDS.toMinutes(up_second_day) - (TimeUnit.SECONDS.toHours(up_second_day) * 60);
//                     long second = TimeUnit.SECONDS.toSeconds(up_second_day) - (TimeUnit.SECONDS.toMinutes(up_second_day) * 60);
//                     var_uptime = day + " Days, " + hours + " Hours, " + minute + " Minutes, " + second + " Seconds";
//
//                 } catch (Exception ex) {
//                     System.out.println("Exception Uptime cal:" + ex);
//                 }
				 System.out.println(a[1].toString()+"var_uptime:" + var_uptime);

					String var_downtime="NA";
//					 try {
//						 int up_second_day=(Integer) a[5];
//	                     int day = (int) TimeUnit.SECONDS.toDays(up_second_day);
//	                     long hours = TimeUnit.SECONDS.toHours(up_second_day) - (day * 24);
//	                     long minute = TimeUnit.SECONDS.toMinutes(up_second_day) - (TimeUnit.SECONDS.toHours(up_second_day) * 60);
//	                     long second = TimeUnit.SECONDS.toSeconds(up_second_day) - (TimeUnit.SECONDS.toMinutes(up_second_day) * 60);
//	                     var_downtime = day + " Days, " + hours + " Hours, " + minute + " Minutes, " + second + " Seconds";
//
//	                 } catch (Exception ex) {
//	                     System.out.println("excep 333 util:" + ex);
//	                 }

				id++;
				NodeAvailabilityBean bean = new NodeAvailabilityBean();
				bean.setID(id);
				bean.setNODE_IP(a[1].toString());
				bean.setUPTIME_PERCENT(Double.parseDouble(a[2].toString()));
				bean.setUPTIME(a[3].toString());
				bean.setDOWNTIME_PERCENT(Double.parseDouble(a[4].toString()));
				bean.setDOWNTIME(a[5].toString());

				bean.setEVENT_TIMESTAMP(a[6].toString());

				bean.setDEVICE_NAME(a[7].toString());
				bean.setLOCATION(a[8].toString());
				bean.setDISTRICT(a[9].toString());
				bean.setSTATE(a[10].toString());
				bean.setZONE(a[11].toString());
				bean.setGROUP_NAME(a[12].toString());
				dataList.add(bean);
			} catch (Exception e) {
				// TODO: handle exception
			}
		}

		return dataList;
	}

	public List<NodeHealthHistoryReportBean> nodeHealthHistoryReport(String from_date, String to_date,
			List<String> ip_list) {

		String ip_data = ip_list.toString().replace("[", "").replace("]", "").replace(",", "','").replace(" ", "");
		System.out.println("IP List:" + ip_data + ":" + from_date + to_date);
		String query = "select report.ID,report.NODE_IP,report.CPU_UTILIZATION,report.MEMORY_UTILIZATION,report.TEMPERATURE,report.EVENT_TIMESTAMP,node.DEVICE_NAME,node.LOCATION,node.DISTRICT,node.STATE,node.ZONE,node.GROUP_NAME from add_node node join NODE_HEALTH_HISTORY report on node.DEVICE_IP=report.NODE_IP where node.DEVICE_IP in ('"
				+ ip_data + "') and  report.EVENT_TIMESTAMP BETWEEN '" + from_date + "' AND '" + to_date + "'";
		Query q = getSession().createSQLQuery(query);
		List<NodeHealthHistoryReportBean> dataList = new ArrayList<NodeHealthHistoryReportBean>();

		List<Object[]> data = q.list();
		System.out.println("Size Data:" + data.size());
		long id = 0;
		for (Object[] a : data) {
			try {

				id++;
				NodeHealthHistoryReportBean bean = new NodeHealthHistoryReportBean();
				bean.setID(id);
				bean.setNODE_IP(a[1].toString());
				bean.setCPU_UTILIZATION(Double.parseDouble(a[2].toString()));
				bean.setMEMORY_UTILIZATION(Double.parseDouble(a[3].toString()));
				bean.setTEMPERATURE(Double.parseDouble(a[4].toString()));

				bean.setEVENT_TIMESTAMP(a[5].toString());

				bean.setDEVICE_NAME(a[6].toString());
				bean.setLOCATION(a[7].toString());
				bean.setDISTRICT(a[8].toString());
				bean.setSTATE(a[9].toString());
				bean.setZONE(a[10].toString());
				bean.setGROUP_NAME(a[11].toString());
				dataList.add(bean);
			} catch (Exception e) {
				// TODO: handle exception
			}
		}

		return dataList;
	}

	public List<CpuThresholdHealthReportBean> cpuThresholdHistoryReport(String from_date, String to_date,
			List<String> ip_list) {

		String ip_data = ip_list.toString().replace("[", "").replace("]", "").replace(",", "','").replace(" ", "");
		System.out.println("IP List:" + ip_data + ":" + from_date + to_date);
		String query = "select report.ID,report.NODE_IP,report.CPU_UTILIZATION,report.CPU_THRESHOLD,report.CPU_STATUS,report.EVENT_TIMESTAMP,node.DEVICE_NAME,node.LOCATION,node.DISTRICT,node.STATE,node.ZONE,node.GROUP_NAME from add_node node join CPU_THRESHOLD_HISTORY report on node.DEVICE_IP=report.NODE_IP where node.DEVICE_IP in ('"
				+ ip_data + "') and  report.EVENT_TIMESTAMP BETWEEN '" + from_date + "' AND '" + to_date + "'";
		Query q = getSession().createSQLQuery(query);
		List<CpuThresholdHealthReportBean> dataList = new ArrayList<CpuThresholdHealthReportBean>();

		List<Object[]> data = q.list();
		System.out.println("Size Data:" + data.size());
		long id = 0;
		for (Object[] a : data) {
			try {

				id++;
				CpuThresholdHealthReportBean bean = new CpuThresholdHealthReportBean();
				bean.setID(id);
				bean.setNODE_IP(a[1].toString());
				bean.setCPU_UTILIZATION(Double.parseDouble(a[2].toString()));
				bean.setCPU_THRESHOLD(Double.parseDouble(a[3].toString()));
				bean.setCPU_STATUS(a[4].toString());

				bean.setEVENT_TIMESTAMP(a[5].toString());

				bean.setDEVICE_NAME(a[6].toString());
				bean.setLOCATION(a[7].toString());
				bean.setDISTRICT(a[8].toString());
				bean.setSTATE(a[9].toString());
				bean.setZONE(a[10].toString());
				bean.setGROUP_NAME(a[11].toString());
				dataList.add(bean);
			} catch (Exception e) {
				// TODO: handle exception
			}
		}

		return dataList;
	}

	public List<MemoryThresholdReportBean> memoryThresholdHistoryReport(String from_date, String to_date,
			List<String> ip_list) {

		String ip_data = ip_list.toString().replace("[", "").replace("]", "").replace(",", "','").replace(" ", "");
		System.out.println("IP List:" + ip_data + ":" + from_date + to_date);
		String query = "select report.ID,report.NODE_IP,report.MEMORY_UTILIZATION,report.MEMORY_THRESHOLD,report.MEMORY_STATUS,report.EVENT_TIMESTAMP,node.DEVICE_NAME,node.LOCATION,node.DISTRICT,node.STATE,node.ZONE,node.GROUP_NAME from add_node node join MEMORY_THRESHOLD_HISTORY report on node.DEVICE_IP=report.NODE_IP where node.DEVICE_IP in ('"
				+ ip_data + "') and  report.EVENT_TIMESTAMP BETWEEN '" + from_date + "' AND '" + to_date + "'";
		Query q = getSession().createSQLQuery(query);
		List<MemoryThresholdReportBean> dataList = new ArrayList<MemoryThresholdReportBean>();

		List<Object[]> data = q.list();
		System.out.println("Size Data:" + data.size());
		long id = 0;
		for (Object[] a : data) {
			try {

				id++;
				MemoryThresholdReportBean bean = new MemoryThresholdReportBean();
				bean.setID(id);
				bean.setNODE_IP(a[1].toString());
				bean.setMEMORY_UTILIZATION(Double.parseDouble(a[2].toString()));
				bean.setMEMORY_THRESHOLD(Double.parseDouble(a[3].toString()));
				bean.setMEMORY_STATUS(a[4].toString());

				bean.setEVENT_TIMESTAMP(a[5].toString());

				bean.setDEVICE_NAME(a[6].toString());
				bean.setLOCATION(a[7].toString());
				bean.setDISTRICT(a[8].toString());
				bean.setSTATE(a[9].toString());
				bean.setZONE(a[10].toString());
				bean.setGROUP_NAME(a[11].toString());
				dataList.add(bean);
			} catch (Exception e) {
				// TODO: handle exception
			}
		}

		return dataList;
	}

	public List<NodeHealthMonitoring> nodeHealthMonitoringView() {

		Criteria criteria = getSession().createCriteria(NodeHealthMonitoring.class);
		return criteria.list();
	}

	public List<NodeMonitoringModel> nodeMonitoringView() {

		Criteria criteria = getSession().createCriteria(NodeMonitoringModel.class);
		return criteria.list();
	}

	public List<InterfaceMonitoring> interfaceMonitoringView() {

		Criteria criteria = getSession().createCriteria(InterfaceMonitoring.class);
		return criteria.list();
	}

	public JSONArray getGroupDeviceDetails(String group_name) {
		JSONArray array = null;
		JSONArray array1 = new JSONArray();
		try {
			System.out.println("Query:" + group_name);
			Query q = getSession().createQuery("from AddNodeModel where GROUP_NAME=:GROUP_NAME");
			q.setParameter("GROUP_NAME", group_name);
			int srno = 0;
			List<AddNodeModel> dataList = q.list();
			for (AddNodeModel node : dataList) {
				srno = srno + 1;
				array = new JSONArray();
				array.put("<input type='checkbox' id=" + node.getDEVICE_IP()
						+ " name='ipAddressCheck' class='checkers' value='" + node.getDEVICE_IP() + "'/>");
				array.put(srno);
				array.put(node.getDEVICE_IP());
				array.put(node.getDEVICE_NAME());
				array.put(node.getGROUP_NAME());
				array.put(node.getLOCATION());
				array.put(node.getDISTRICT());
				array.put(node.getSTATE());
				array.put(node.getZONE());
				array1.put(array);
			}
		} catch (Exception e) {
			System.out.println("Exception:" + e);
		}

		return array1;
	}

	public JSONObject latencyHistoricalData(String from_date, String to_date, String ip_address) {

		JSONObject jsonObj = null;
		jsonObj = new JSONObject();

		JSONArray latency_array = null;
		latency_array = new JSONArray();

		JSONArray packet_drop_array = null;
		packet_drop_array = new JSONArray();

		JSONArray latency_obj = null;
		JSONArray packet_drop_obj = null;

		Timestamp fromDateTimeStamp = Timestamp.valueOf(from_date);
		Timestamp toDateTimeStamp = Timestamp.valueOf(to_date);

		try {
			System.out.println("Query:" + ip_address);
			Query q = getSession().createQuery(
					"from LatencyHisotryModel where NODE_IP=:NODE_IP AND EVENT_TIMESTAMP BETWEEN :fromDate AND :toDate");
			q.setParameter("NODE_IP", ip_address);
			q.setParameter("fromDate", fromDateTimeStamp);
			q.setParameter("toDate", toDateTimeStamp);
			int srno = 0;
			List<LatencyHisotryModel> dataList = q.list();
			System.out.println(q.list());
			System.out.println("Data Size:" + dataList.size());
			for (LatencyHisotryModel data : dataList) {
				srno = srno + 1;

				double avg_latency = data.getAVG_LATENCY();
				double pkt_loss = data.getPACKET_LOSS();
				Timestamp time = data.getEVENT_TIMESTAMP();
				long datemili = time.getTime();

				latency_obj = new JSONArray();
				latency_obj.put(datemili);
				latency_obj.put(avg_latency);
				latency_array.put(latency_obj);

				packet_drop_obj = new JSONArray();
				packet_drop_obj.put(datemili);
				packet_drop_obj.put(pkt_loss);
				packet_drop_array.put(packet_drop_obj);

			}
		} catch (Exception e) {
			System.out.println("Exception:" + e);
		}
		System.out.println("latency Data:" + latency_array);
		System.out.println("packet drop = " + packet_drop_array);
		jsonObj.put("latency", latency_array);
		jsonObj.put("packet_drop", packet_drop_array);
		return jsonObj;
	}

	// Interface Bandwidth History Graph
	public JSONObject interfaceBWHistoryData(String from_date, String to_date, String ip_address,
			String interface_name) {

		JSONObject jsonObj = null;
		jsonObj = new JSONObject();

		JSONArray inTraffic_array = null;
		inTraffic_array = new JSONArray();

		JSONArray outTraffic_array = null;
		outTraffic_array = new JSONArray();

		JSONArray inTraffic_obj = null;
		JSONArray outTraffic_obj = null;

		Timestamp fromDateTimeStamp = Timestamp.valueOf(from_date);
		Timestamp toDateTimeStamp = Timestamp.valueOf(to_date);

		try {
			System.out.println("Query:" + ip_address);
			Query q = getSession().createQuery(
					"from InterfaceBWHistory where NODE_IP=:NODE_IP AND EVENT_TIMESTAMP BETWEEN :fromDate AND :toDate");
			q.setParameter("NODE_IP", ip_address);
			q.setParameter("fromDate", fromDateTimeStamp);
			q.setParameter("toDate", toDateTimeStamp);
//			q.setParameter("INTERFACE_NAME", interface_name);

			int srno = 0;
			List<InterfaceBWHistory> dataList = q.list();

			System.out.println("Data Size:" + dataList.size());
			for (InterfaceBWHistory data : dataList) {
				srno = srno + 1;

				double in_traffic = data.getIN_TRAFFIC();
				double out_traffic = data.getOUT_TRAFFIC();
				Timestamp time = data.getEVENT_TIMESTAMP();
				long datemili = time.getTime();

				inTraffic_obj = new JSONArray();
				inTraffic_obj.put(datemili);
				inTraffic_obj.put(in_traffic);
				inTraffic_array.put(inTraffic_obj);

				outTraffic_obj = new JSONArray();
				outTraffic_obj.put(datemili);
				outTraffic_obj.put(out_traffic);
				outTraffic_array.put(outTraffic_obj);

			}
		} catch (Exception e) {
			System.out.println("Exception:" + e);
		}
		System.out.println("IN Traffic Data:" + inTraffic_array);
		jsonObj.put("inTraffic", inTraffic_array);
		jsonObj.put("outTraffic", outTraffic_array);
		return jsonObj;
	}

	// Node Health History Graph

	public JSONObject nodeHealthHistoryData(String from_date, String to_date, String ip_address) {

		JSONObject jsonObj = null;
		jsonObj = new JSONObject();

		JSONArray cpu_utilization_array = null;
		cpu_utilization_array = new JSONArray();

		JSONArray memory_utilization_array = null;
		memory_utilization_array = new JSONArray();

		JSONArray memory_utilization_obj = null;
		JSONArray cpu_utilization_obj = null;

		Timestamp fromDateTimeStamp = Timestamp.valueOf(from_date);
		Timestamp toDateTimeStamp = Timestamp.valueOf(to_date);

		try {
			System.out.println("Query:" + ip_address);
			Query q = getSession().createQuery(
					"from NodeHealthHistory where NODE_IP=:NODE_IP AND EVENT_TIMESTAMP BETWEEN :fromDate AND :toDate");
			q.setParameter("NODE_IP", ip_address);
			q.setParameter("fromDate", fromDateTimeStamp);
			q.setParameter("toDate", toDateTimeStamp);
			int srno = 0;
			List<NodeHealthHistory> dataList = q.list();

			System.out.println("Data Size:" + dataList.size());
			for (NodeHealthHistory data : dataList) {
				srno = srno + 1;

				double cpu_utilization = data.getCPU_UTILIZATION();
				double memory_utilization = data.getMEMORY_UTILIZATION();
				Timestamp time = data.getEVENT_TIMESTAMP();
				long datemili = time.getTime();

				cpu_utilization_obj = new JSONArray();
				cpu_utilization_obj.put(datemili);
				cpu_utilization_obj.put(cpu_utilization);
				cpu_utilization_array.put(cpu_utilization_obj);

				memory_utilization_obj = new JSONArray();
				memory_utilization_obj.put(datemili);
				memory_utilization_obj.put(memory_utilization);
				memory_utilization_array.put(memory_utilization_obj);

			}
		} catch (Exception e) {
			System.out.println("Exception:" + e);
		}
		System.out.println("CPU Utilization Data:" + cpu_utilization_array);
		jsonObj.put("cpuUtilization", cpu_utilization_array);
		jsonObj.put("memoryUtilization", memory_utilization_array);
		return jsonObj;
	}

	// Node Availability Graph

	public JSONObject nodeAvailabilityData(String from_date, String to_date, String ip_address) {

		JSONObject jsonObj = null;
		jsonObj = new JSONObject();

		JSONArray downtime_percent_array = null;
		downtime_percent_array = new JSONArray();

		JSONArray uptime_percent_array = null;
		uptime_percent_array = new JSONArray();

		JSONArray downtime_percent_obj = null;
		JSONArray uptime_percent_obj = null;

		Timestamp fromDateTimeStamp = Timestamp.valueOf(from_date);
		Timestamp toDateTimeStamp = Timestamp.valueOf(to_date);

		try {
			System.out.println("Query:" + ip_address);
			Query q = getSession().createQuery(
					"from NodeAvailablity where NODE_IP=:NODE_IP AND EVENT_TIMESTAMP BETWEEN :fromDate AND :toDate");
			q.setParameter("NODE_IP", ip_address);
			q.setParameter("fromDate", fromDateTimeStamp);
			q.setParameter("toDate", toDateTimeStamp);
			int srno = 0;
			List<NodeAvailablity> dataList = q.list();

			System.out.println("Data Size:" + dataList.size());
			for (NodeAvailablity data : dataList) {
				srno = srno + 1;

				double downtime_percent = data.getDOWNTIME_PERCENT();
				double uptime_percent = data.getUPTIME_PERCENT();
				Date time = data.getEVENT_TIMESTAMP();
				long datemili = time.getTime();

				downtime_percent_obj = new JSONArray();
				downtime_percent_obj.put(datemili);
				downtime_percent_obj.put(downtime_percent);
				downtime_percent_array.put(downtime_percent_obj);

				uptime_percent_obj = new JSONArray();
				uptime_percent_obj.put(datemili);
				uptime_percent_obj.put(uptime_percent);
				uptime_percent_array.put(uptime_percent_obj);

			}
		} catch (Exception e) {
			System.out.println("Exception:" + e);
		}
		System.out.println("Downtime Percent:" + downtime_percent_array);
		jsonObj.put("downtimePercent", downtime_percent_array);
		jsonObj.put("uptimePercent", uptime_percent_array);
		return jsonObj;
	}

	// Interface Availability Graph

	public JSONObject interfaceAvailabilityData(String from_date, String to_date, String ip_address,
			String interface_name) {

		JSONObject jsonObj = null;
		jsonObj = new JSONObject();

		JSONArray downtime_percent_array = null;
		downtime_percent_array = new JSONArray();

		JSONArray uptime_percent_array = null;
		uptime_percent_array = new JSONArray();

		JSONArray downtime_percent_obj = null;
		JSONArray uptime_percent_obj = null;

		Timestamp fromDateTimeStamp = Timestamp.valueOf(from_date);
		Timestamp toDateTimeStamp = Timestamp.valueOf(to_date);

		try {
			System.out.println("Query:" + ip_address);
			Query q = getSession().createQuery(
					"from InterfaceAvailablity where NODE_IP=:NODE_IP AND EVENT_TIMESTAMP BETWEEN :fromDate AND :toDate");
			q.setParameter("NODE_IP", ip_address);
			q.setParameter("fromDate", fromDateTimeStamp);
			q.setParameter("toDate", toDateTimeStamp);
			// q.setParameter("INTERFACE_NAME", interface_name);
			int srno = 0;
			List<InterfaceAvailablity> dataList = q.list();

			System.out.println("Data Size:" + dataList.size());
			for (InterfaceAvailablity data : dataList) {
				srno = srno + 1;

				double downtime_percent = data.getDOWNTIME_PERCENT();
				double uptime_percent = data.getUPTIME_PERCENT();
				Date time = data.getEVENT_TIMESTAMP();
				long datemili = time.getTime();

				downtime_percent_obj = new JSONArray();
				downtime_percent_obj.put(datemili);
				downtime_percent_obj.put(downtime_percent);
				downtime_percent_array.put(downtime_percent_obj);

				uptime_percent_obj = new JSONArray();
				uptime_percent_obj.put(datemili);
				uptime_percent_obj.put(uptime_percent);
				uptime_percent_array.put(uptime_percent_obj);

			}
		} catch (Exception e) {
			System.out.println("Exception:" + e);
		}
		System.out.println("Downtime Percent:" + downtime_percent_array);
		jsonObj.put("downtimePercent", downtime_percent_array);
		jsonObj.put("uptimePercent", uptime_percent_array);
		return jsonObj;
	}

	public String getIPAddress() {

		String output = "<option value=''>Please Select</option>";
//		String output = "";
		try {

			Query queryBranch = getSession().createQuery("select distinct DEVICE_IP from AddNodeModel");
			List<String> branches = queryBranch.list();

			for (String branch : branches) {

				int i = branch.indexOf(" ", 0);

				output += "<option>" + branch + "</option>";

			}
			System.out.println("Branches: " + output);
		} catch (Exception e) {
			e.printStackTrace();

		}
		return output;
	}

	public String getInterfaceOnIPAddress(String ipAddress) {

		String output = "<option value=''>Please Select</option>";
		try {
			Query queryBranch = getSession().createQuery("select distinct INTERFACE_IP from InterfaceMonitoring");
			List<String> branches = queryBranch.list();

			for (String branch : branches) {

				int i = branch.indexOf(" ", 0);

				output += "<option>" + branch + "</option>";

			}
			System.out.println("Branches: " + output);
		} catch (Exception e) {
			e.printStackTrace();

		}
		return output;
	}

	public String SaveNodePositions(String fromTo, String positions, String ipAddress, String username) {

		String result = "";
		Query q = getSession().createQuery("from TOPOLOGY_NODE_POSTIONS where VIEW_NAME='" + username + "'");
//	Query query1 = getSession().createQuery("from COMPANY where COMPANY_NAME='" + companyName + "'");
		List<TOPOLOGY_NODE_POSTIONS> dataList = q.list();
		if (dataList.size() > 0) {
//			result = "nameExist";
			Query q1 = getSession()
					.createQuery("delete from TOPOLOGY_NODE_POSTIONS where VIEW_NAME='" + username + "'");
			int i = q1.executeUpdate();
		} else {
			try {

				JSONParser parser = new JSONParser();
				JSONArray json = (JSONArray) parser.parse(positions);
				JSONParser parser2 = new JSONParser();
				for (int e = 0; e < ((List) json).size(); e++) {

//	                System.out.println("values in jason " + json.get(e));
					JSONObject jo = (JSONObject) parser2.parse(json.get(e).toString());

					TOPOLOGY_NODE_POSTIONS topology = new TOPOLOGY_NODE_POSTIONS();
					topology.setNODE_ID(jo.get("id").toString());
					topology.setX_POS(jo.get("x").toString());
					topology.setY_POS(jo.get("y").toString());
					topology.setVIEW_NAME(jo.get("username").toString());

					getSession().save(topology);
					result = "success";
				}
			} catch (Exception e) {
				result = "error";
				e.printStackTrace();
			}
		}
		return result;
	}

	public String NetworkTopologyEditMode(String grpName) {
		String result = "";
		List<String> offRouter_list = new ArrayList<String>();
		List<String> offRouterSrList = new ArrayList<String>();
		List<String> offNodeSrList = new ArrayList<String>();
		try {
			ServletRequestAttributes requestAttributes = (ServletRequestAttributes) RequestContextHolder
					.getRequestAttributes();
			HttpSession session = requestAttributes.getRequest().getSession();
			String username = session.getAttribute("username").toString();

			Query q = getSession().createQuery("from InterfaceMonitoring where OPER_STATUS='down'");
//			Query query1 = getSession().createQuery("from COMPANY where COMPANY_NAME='" + companyName + "'");
			List<InterfaceMonitoring> dataList = q.list();

			for (InterfaceMonitoring node : dataList) {
				offRouter_list.add(node.getINTERFACE_IP());
				offRouterSrList.add(node.getINTERFACE_NAME());
			}

			for (String downSrNO : offRouter_list) {
				String ipAddressOfDown = downSrNO.substring(0, downSrNO.indexOf("~"));
				String interfaceOfDown = downSrNO.substring(downSrNO.indexOf("~") + 1);
//				String q2 = "from  where node_name='" + ipAddressOfDown + "'";
				
				Query q2 = getSession().createQuery("from custome_topology_view where node_name='" + ipAddressOfDown + "'");

				List<custome_topology_view> dataList2 = q2.list();
//				for (InterfaceMonitoring node : dataList) {
//					offNodeSrList.add(node.getID());
//					
//				}

//	                    rs3 = stmt3.executeQuery(q2);

//	                    while (rs3.next()) {
//	                        offNodeSrList.add(rs3.getString(1));

//	                    }
			}
		} catch (Exception e) {
			// TODO: handle exception
		}

		return result;
	}

	public String NetworkTopologyInsertInterface(String fromIpAddress, String toIpAddress, String fromInterface,
			String toInterface) {

		String result = "";
		// TODO Auto-generated method stub
		int fromIPID = 0;
		int toIPID = 0;
		try {
			Query q = getSession().createQuery("from custome_topology_view where node_name='" + fromIpAddress + "'");
			List<custome_topology_view> dataList = q.list();

			for (custome_topology_view node : dataList) {
				fromIPID = node.getId();
			}
			Query q1 = getSession().createQuery("from custome_topology_view)"
					+ " where node_name='" + toIpAddress + "'");
			List<custome_topology_view> dataList1 = q1.list();

			for (custome_topology_view node : dataList1) {
				toIPID = node.getId();
			}
			
			
			ServletRequestAttributes requestAttributes = (ServletRequestAttributes) RequestContextHolder
					.getRequestAttributes();
			HttpSession session = requestAttributes.getRequest().getSession();
//			String username = session.getAttribute("username").toString();
//			String grpName = session.getAttribute("groupname").toString();

			CUSTOME_TOPOLOGY_MAP map = new CUSTOME_TOPOLOGY_MAP();
//			map.setVIEW_NAME(username);
			map.setVIEW_NAME("User");
//			map.setId(id);
			map.setID_1(fromIpAddress + "~" + fromInterface);
			map.setLINK_FROM(String.valueOf(fromIPID));
			map.setLINK_TO(String.valueOf(toIPID));
			map.setINTERFACE_FROM(fromInterface);
			map.setINTERFACE_TO(toInterface);
			map.setIPADDRESS_FROM(fromIpAddress);
			map.setIPADDRESS_TO(toIpAddress);
//			map.setGROUP_NAME(grpName);
			map.setGROUP_NAME("grp");
			getSession().save(map);
			
		} catch (Exception e) {
			e.printStackTrace();
			// TODO: handle exception
		}
		return result;
	}

	public List<DeviceAlertLog> viewDeviceAlertLog() {
	
		List<DeviceAlertLog> list = getSession().createQuery("from DeviceAlertLog order by ID desc").list();
		return list;
		
	}
	
	
	
}
