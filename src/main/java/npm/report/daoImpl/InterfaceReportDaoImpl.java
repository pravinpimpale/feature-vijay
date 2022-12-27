package npm.report.daoImpl;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.hibernate.Query;
import org.json.JSONArray;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import npm.admin.beans.BWThresholdHistoryBean;
import npm.admin.beans.InterfaceAvailabilityBean;
import npm.admin.beans.InterfaceBandwidthHistoryBean;
import npm.admin.beans.InterfaceCRCHistoryBean;
import npm.admin.beans.InterfaceStatusReportBean;
import npm.configuration.AbstractDao;
import npm.model.AddNodeModel;
import npm.report.dao.InterfaceReportDao;

@Repository
@Transactional
public class InterfaceReportDaoImpl extends AbstractDao<Integer, AddNodeModel> implements InterfaceReportDao {

	public JSONArray getGroupInterfaceDetails(String group_name) {
		JSONArray arrayData = null;
		JSONArray arrayMain = new JSONArray();
		int srno = 0;
		String query = "select  interface.INTERFACE_NAME,interface.INTERFACE_IP,interface.ALIAS_NAME,interface.OPER_STATUS,interface.NODE_IP,node.DEVICE_NAME,node.LOCATION,node.DISTRICT,node.STATE,node.ZONE,node.GROUP_NAME from  interface_monitoring interface LEFT JOIN add_node node  on node.DEVICE_IP=interface.NODE_IP where node.GROUP_NAME='"
				+ group_name
				+ "' and interface.MONITORING_PARAM='Yes' group by interface.NODE_IP,interface.INTERFACE_NAME ";
		Query q = getSession().createSQLQuery(query);
		List<Object[]> data = q.list();
		System.out.println("Size Data:" + data.size());

		for (Object[] a : data) {
			srno = srno + 1;
			arrayData = new JSONArray();
			arrayData.put("<input type='checkbox' id=" + a[4] + "~" + a[0]
					+ " name='ipAddressCheck' class='checkers' value='" + a[4] + "~" + a[0] + "'/>");
			arrayData.put(srno);
			arrayData.put(a[0]);
			arrayData.put(a[1]);
			arrayData.put(a[2]);
			arrayData.put(a[3]);
			arrayData.put(a[4]);
			arrayData.put(a[5]);
			arrayData.put(a[6]);
			arrayData.put(a[7]);
			arrayData.put(a[8]);
			arrayData.put(a[9]);
			arrayData.put(a[10]);
			arrayMain.put(arrayData);
		}
		return arrayMain;
	}

	public List<InterfaceStatusReportBean> InterfaceStatusReport(String from_date, String to_date,
			List<String> ip_interface_list) {

		String ip_data = ip_interface_list.toString().replace("[", "").replace("]", "").replace(",", "','").replace(" ",
				"");
		System.out.println("IP List:" + ip_data + ":" + from_date + to_date);

		String query = "select report.ID,report.INTERFACE_NAME,report.INTERFACE_STATUS,report.INTERFACE_STATUS_TYPE,report.EVENT_TIMESTAMP,report.NODE_IP,node.DEVICE_NAME,node.LOCATION,node.DISTRICT,node.STATE,node.ZONE,node.GROUP_NAME from add_node node join interface_status_history report on node.DEVICE_IP=report.NODE_IP where report.IP_INTERFACE in ('"
				+ ip_data + "') and  report.EVENT_TIMESTAMP BETWEEN '" + from_date + "' AND '" + to_date + "'";
		Query q = getSession().createSQLQuery(query);
		List<InterfaceStatusReportBean> dataList = new ArrayList<InterfaceStatusReportBean>();

		List<Object[]> data = q.list();
		System.out.println("Size Data:" + data.size());
		long id = 0;
		for (Object[] a : data) {
			try {
				id++;
				InterfaceStatusReportBean bean = new InterfaceStatusReportBean();
				bean.setID(id);
				bean.setINTERFACE_NAME(a[1].toString());
				bean.setINTERFACE_STATUS(a[2].toString());
				bean.setINTERFACE_STATUS_TYPE(a[3].toString());
				bean.setEVENT_TIMESTAMP(a[4].toString());

				bean.setNODE_IP(a[5].toString());
				bean.setDEVICE_NAME(a[6].toString());
				bean.setLOCATION(a[7].toString());
				bean.setDISTRICT(a[8].toString());
				bean.setSTATE(a[9].toString());
				bean.setZONE(a[10].toString());
				bean.setGROUP_NAME(a[11].toString());
				dataList.add(bean);
			} catch (Exception e) {
				System.out.println("Exception:" + e);
			}
		}

		return dataList;
	}
	
	//Interface Bandwidth History 
	
	public List<InterfaceBandwidthHistoryBean> InterfaceBandwidthHistoryReport(String from_date, String to_date,
			List<String> ip_interface_list) {

		String ip_data = ip_interface_list.toString().replace("[", "").replace("]", "").replace(",", "','").replace(" ",
				"");
		System.out.println("IP List:" + ip_data + ":" + from_date + to_date);

		String query = "select report.ID,report.INTERFACE_NAME,report.OUT_TRAFFIC,report.IN_TRAFFIC,report.IP_INTERFACE,report.EVENT_TIMESTAMP,report.NODE_IP,node.DEVICE_NAME,node.LOCATION,node.DISTRICT,node.STATE,node.ZONE,node.GROUP_NAME from add_node node join interface_bw_history report on node.DEVICE_IP=report.NODE_IP where report.IP_INTERFACE in ('"
				+ ip_data + "') and  report.EVENT_TIMESTAMP BETWEEN '" + from_date + "' AND '" + to_date + "'";
		Query q = getSession().createSQLQuery(query);
		List<InterfaceBandwidthHistoryBean> dataList = new ArrayList<InterfaceBandwidthHistoryBean>();

		List<Object[]> data = q.list();
		System.out.println("Size Data:" + data.size());
		long id = 0;
		for (Object[] a : data) {
			try {
				id++;
				InterfaceBandwidthHistoryBean bean = new InterfaceBandwidthHistoryBean();
				bean.setID(id);
				bean.setINTERFACE_NAME(a[1].toString());
				bean.setOUT_TRAFFIC(Double.parseDouble(a[2].toString()));
				bean.setIN_TRAFFIC(Double.parseDouble(a[3].toString()));
				bean.setIP_INTERFACE(a[4].toString());
				bean.setEVENT_TIMESTAMP(a[5].toString());

				bean.setNODE_IP(a[6].toString());
				bean.setDEVICE_NAME(a[7].toString());
				bean.setLOCATION(a[8].toString());
				bean.setDISTRICT(a[9].toString());
				bean.setSTATE(a[10].toString());
				bean.setZONE(a[11].toString());
				bean.setGROUP_NAME(a[12].toString());
				dataList.add(bean);
			} catch (Exception e) {
				System.out.println("Exception:" + e);
			}
		}

		return dataList;
	}
	
	
	// Bandwidth Threshold History
	
	public List<BWThresholdHistoryBean> bandwidthThresholdHistoryReport(String from_date, String to_date,
			List<String> ip_interface_list) {

		String ip_data = ip_interface_list.toString().replace("[", "").replace("]", "").replace(",", "','").replace(" ",
				"");
		System.out.println("IP List:" + ip_data + ":" + from_date + to_date);

		String query = "select report.ID,report.INTERFACE_NAME,report.BW_THRESHOLD,report.BW_VAL,report.BW_STATUS,report.IP_INTERFACE,report.EVENT_TIMESTAMP,report.NODE_IP,node.DEVICE_NAME,node.LOCATION,node.DISTRICT,node.STATE,node.ZONE,node.GROUP_NAME from add_node node join BW_THRESHOLD_HISTORY report on node.DEVICE_IP=report.NODE_IP where report.IP_INTERFACE in ('"
				+ ip_data + "') and  report.EVENT_TIMESTAMP BETWEEN '" + from_date + "' AND '" + to_date + "'";
		Query q = getSession().createSQLQuery(query);
		List<BWThresholdHistoryBean> dataList = new ArrayList<BWThresholdHistoryBean>();

		List<Object[]> data = q.list();
		System.out.println("Size Data:" + data.size());
		long id = 0;
		for (Object[] a : data) {
			try {
				id++;
				BWThresholdHistoryBean bean = new BWThresholdHistoryBean();
				bean.setID(id);
				bean.setINTERFACE_NAME(a[1].toString());
				bean.setBW_THRESHOLD(Double.parseDouble(a[2].toString()));
				bean.setBW_VAL(Double.parseDouble(a[3].toString()));
				bean.setBW_STATUS(a[4].toString());
				bean.setIP_INTERFACE(a[5].toString());
				bean.setEVENT_TIMESTAMP(a[6].toString());

				bean.setNODE_IP(a[7].toString());
				bean.setDEVICE_NAME(a[8].toString());
				bean.setLOCATION(a[9].toString());
				bean.setDISTRICT(a[10].toString());
				bean.setSTATE(a[11].toString());
				bean.setZONE(a[12].toString());
				bean.setGROUP_NAME(a[13].toString());
				dataList.add(bean);
			} catch (Exception e) {
				System.out.println("Exception:" + e);
			}
		}

		return dataList;
	}
	
	
	//Interface CRC History
	
	public List<InterfaceCRCHistoryBean> interfaceCrcHistoryReport(String from_date, String to_date,
			List<String> ip_interface_list) {

		String ip_data = ip_interface_list.toString().replace("[", "").replace("]", "").replace(",", "','").replace(" ",
				"");
		System.out.println("IP List:" + ip_data + ":" + from_date + to_date);

		String query = "select report.ID,report.INTERFACE_NAME,report.OLD_CRC_VAL,report.NEW_CRC_VAL,report.IP_INTERFACE,report.EVENT_TIMESTAMP,report.NODE_IP,node.DEVICE_NAME,node.LOCATION,node.DISTRICT,node.STATE,node.ZONE,node.GROUP_NAME from add_node node join INTERFACE_CRC_HISTORY report on node.DEVICE_IP=report.NODE_IP where report.IP_INTERFACE in ('"
				+ ip_data + "') and  report.EVENT_TIMESTAMP BETWEEN '" + from_date + "' AND '" + to_date + "'";
		Query q = getSession().createSQLQuery(query);
		List<InterfaceCRCHistoryBean> dataList = new ArrayList<InterfaceCRCHistoryBean>();

		List<Object[]> data = q.list();
		System.out.println("Size Data:" + data.size());
		long id = 0;
		for (Object[] a : data) {
			try {
				id++;
				InterfaceCRCHistoryBean bean = new InterfaceCRCHistoryBean();
				bean.setID(id);
				bean.setINTERFACE_NAME(a[1].toString());
				bean.setOLD_CRC_VAL(Long.parseLong(a[2].toString()));
				bean.setNEW_CRC_VAL(Long.parseLong(a[3].toString()));
				bean.setIP_INTERFACE(a[4].toString());
				bean.setEVENT_TIMESTAMP(a[5].toString());

				bean.setNODE_IP(a[6].toString());
				bean.setDEVICE_NAME(a[7].toString());
				bean.setLOCATION(a[8].toString());
				bean.setDISTRICT(a[9].toString());
				bean.setSTATE(a[10].toString());
				bean.setZONE(a[11].toString());
				bean.setGROUP_NAME(a[12].toString());
				dataList.add(bean);
			} catch (Exception e) {
				System.out.println("Exception:" + e);
			}
		}

		return dataList;
	}
	
	
	// Interface Availability
	
	public List<InterfaceAvailabilityBean> interfaceAvailabilityReport(String from_date, String to_date,
			List<String> ip_interface_list) {

		String ip_data = ip_interface_list.toString().replace("[", "").replace("]", "").replace(",", "','").replace(" ",
				"");
		System.out.println("IP List:" + ip_data + ":" + from_date + to_date);

		String query = "select report.ID,report.INTERFACE_NAME,report.UPTIME_PERCENT,report.UPTIME_MILISECONDS,report.DOWNTIME_PERCENT,report.DOWNTIME_MILISECONDS,report.IP_INTERFACE,report.EVENT_TIMESTAMP,report.NODE_IP,node.DEVICE_NAME,node.LOCATION,node.DISTRICT,node.STATE,node.ZONE,node.GROUP_NAME from add_node node join INTERFACE_AVAILABILITY report on node.DEVICE_IP=report.NODE_IP where report.IP_INTERFACE in ('"
				+ ip_data + "') and  report.EVENT_TIMESTAMP BETWEEN '" + from_date + "' AND '" + to_date + "'";
		Query q = getSession().createSQLQuery(query);
		List<InterfaceAvailabilityBean> dataList = new ArrayList<InterfaceAvailabilityBean>();

		List<Object[]> data = q.list();
		System.out.println("Size Data:" + data.size());
		long id = 0;
		for (Object[] a : data) {
			try {
				id++;
				String var_uptime="NA";
				 try {
					 int up_second_day=(Integer) a[3];
					 
                    int day = (int) TimeUnit.SECONDS.toDays(up_second_day);
                    long hours = TimeUnit.SECONDS.toHours(up_second_day) - (day * 24);
                    long minute = TimeUnit.SECONDS.toMinutes(up_second_day) - (TimeUnit.SECONDS.toHours(up_second_day) * 60);
                    long second = TimeUnit.SECONDS.toSeconds(up_second_day) - (TimeUnit.SECONDS.toMinutes(up_second_day) * 60);
                    var_uptime = day + " Days, " + hours + " Hours, " + minute + " Minutes, " + second + " Seconds";

                } catch (Exception ex) {
                    System.out.println("Exception Uptime cal:" + ex);
                }
				 System.out.println(a[1].toString()+"var_uptime:" + var_uptime);

					String var_downtime="NA";
					 try {
						 int up_second_day=(Integer) a[5];
	                     int day = (int) TimeUnit.SECONDS.toDays(up_second_day);
	                     long hours = TimeUnit.SECONDS.toHours(up_second_day) - (day * 24);
	                     long minute = TimeUnit.SECONDS.toMinutes(up_second_day) - (TimeUnit.SECONDS.toHours(up_second_day) * 60);
	                     long second = TimeUnit.SECONDS.toSeconds(up_second_day) - (TimeUnit.SECONDS.toMinutes(up_second_day) * 60);
	                     var_downtime = day + " Days, " + hours + " Hours, " + minute + " Minutes, " + second + " Seconds";

	                 } catch (Exception ex) {
	                     System.out.println("excep 333 util:" + ex);
	                 }
				
				InterfaceAvailabilityBean bean = new InterfaceAvailabilityBean();
				bean.setID(id);
				bean.setINTERFACE_NAME(a[1].toString());
				bean.setUPTIME_PERCENT(Double.parseDouble(a[2].toString()));
				bean.setUPTIME(var_uptime);
				bean.setDOWNTIME_PERCENT(Double.parseDouble(a[4].toString()));
				bean.setDOWNTIME(var_downtime);
				bean.setIP_INTERFACE(a[6].toString());
				bean.setEVENT_TIMESTAMP(a[7].toString());

				bean.setNODE_IP(a[8].toString());
				bean.setDEVICE_NAME(a[9].toString());
				bean.setLOCATION(a[10].toString());
				bean.setDISTRICT(a[11].toString());
				bean.setSTATE(a[12].toString());
				bean.setZONE(a[13].toString());
				bean.setGROUP_NAME(a[14].toString());
				
				dataList.add(bean);
			} catch (Exception e) {
				System.out.println("Exception:" + e);
			}
		}

		return dataList;
	}

	// Get Interface Name By IP
	public String getInterfaceNameByIP(String ip_address) {
		
		String all_interface = "";
		System.out.println("IP = " +ip_address);
		String query = "select interface_name from INTERFACE_MONITORING where node_ip =:ip_address";
		Query q = getSession().createSQLQuery(query);
		q.setParameter("ip_address", ip_address);
		//List<InterfaceMonitoring> list = q.list();
		
		List<String> data = q.list();
		for(String a: data)
		{
			String interface_name= a.toString();
			all_interface += interface_name + "~";
		}
		System.out.println("all interface:" + all_interface);
		return all_interface;
	}

	
	


}
