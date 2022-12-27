package npm.report.serviceImpl;

import java.util.List;

import org.json.JSONArray;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import npm.admin.beans.BWThresholdHistoryBean;
import npm.admin.beans.InterfaceAvailabilityBean;
import npm.admin.beans.InterfaceBandwidthHistoryBean;
import npm.admin.beans.InterfaceCRCHistoryBean;
import npm.admin.beans.InterfaceStatusReportBean;
import npm.admin.beans.NodeStatusReportBean;
import npm.model.InterfaceMonitoring;
import npm.report.dao.InterfaceReportDao;
import npm.report.dao.NodeReportDao;
import npm.report.service.InterfaceReportService;

@Service
public class InterfaceReportServiceImpl implements InterfaceReportService {
	
	@Autowired
	InterfaceReportDao dao;

	public JSONArray getGroupInterfaceDetails(String group_name) {

		return dao.getGroupInterfaceDetails( group_name);

	}


	public List<InterfaceStatusReportBean> InterfaceStatusReport(String from_date, String to_date, List<String> ip_list) {

		return dao.InterfaceStatusReport( from_date, to_date, ip_list);

	}
	
	public List<InterfaceBandwidthHistoryBean> InterfaceBandwidthHistoryReport(String from_date, String to_date, List<String> ip_list) {

		return dao.InterfaceBandwidthHistoryReport( from_date, to_date, ip_list);

	}


	public List<BWThresholdHistoryBean> bandwidthThresholdHistoryReport(String from_date, String to_date,
			List<String> ip_list) {
		// TODO Auto-generated method stub
		return dao.bandwidthThresholdHistoryReport( from_date, to_date, ip_list);
	}


	public List<InterfaceCRCHistoryBean> interfaceCrcHistoryReport(String from_date, String to_date,
			List<String> ip_list) {
		// TODO Auto-generated method stub
		return dao.interfaceCrcHistoryReport( from_date, to_date, ip_list);
	}


	public List<InterfaceAvailabilityBean> interfaceAvailabilityReport(String from_date, String to_date,
			List<String> ip_list) {
		// TODO Auto-generated method stub
		return dao.interfaceAvailabilityReport( from_date, to_date, ip_list);
	}
	
	public String getInterfaceNameByIP(String ip_address) {
		// TODO Auto-generated method stub
		return dao.getInterfaceNameByIP(ip_address);
	}


}
