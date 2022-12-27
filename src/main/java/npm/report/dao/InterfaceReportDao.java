package npm.report.dao;

import java.util.List;

import org.json.JSONArray;

import npm.admin.beans.BWThresholdHistoryBean;
import npm.admin.beans.InterfaceAvailabilityBean;
import npm.admin.beans.InterfaceBandwidthHistoryBean;
import npm.admin.beans.InterfaceCRCHistoryBean;
import npm.admin.beans.InterfaceStatusReportBean;
import npm.model.InterfaceMonitoring;

public interface InterfaceReportDao {
	
	
	JSONArray getGroupInterfaceDetails(String group_name);
	
	
	List<InterfaceStatusReportBean> InterfaceStatusReport( String from_date, String to_date, List<String> ip_list);
	
	List<InterfaceBandwidthHistoryBean> InterfaceBandwidthHistoryReport( String from_date, String to_date, List<String> ip_list);
	
	List<BWThresholdHistoryBean> bandwidthThresholdHistoryReport( String from_date, String to_date, List<String> ip_list);
	
	List<InterfaceCRCHistoryBean> interfaceCrcHistoryReport( String from_date, String to_date, List<String> ip_list);
	
	List<InterfaceAvailabilityBean> interfaceAvailabilityReport( String from_date, String to_date, List<String> ip_list);
	
	String getInterfaceNameByIP(String ip_address);
}
