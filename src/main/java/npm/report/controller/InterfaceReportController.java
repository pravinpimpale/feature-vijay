package npm.report.controller;

import java.io.PrintWriter;
import java.util.Arrays;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import npm.admin.beans.BWThresholdHistoryBean;
import npm.admin.beans.InterfaceAvailabilityBean;
import npm.admin.beans.InterfaceBandwidthHistoryBean;
import npm.admin.beans.InterfaceCRCHistoryBean;
import npm.admin.beans.InterfaceStatusReportBean;
import npm.admin.beans.NodeStatusReportBean;
import npm.admin.dao.MasterDao;
import npm.model.InterfaceMonitoring;
import npm.report.service.InterfaceReportService;

@Controller
@RequestMapping("/interfaceReport")
public class InterfaceReportController {
	@Autowired
	MasterDao dao;

	@Autowired
	InterfaceReportService service;

	@RequestMapping("/getGroupInterfaceDetails")
	public void getGroupDeviceDetails(HttpServletRequest req, HttpServletResponse res,
			@RequestParam("groupName") String group_name) {
		System.out.println("Controller getGroupInterfaceDetails" + group_name);
		PrintWriter out = null;
		try {
			out = res.getWriter();
			out.print(service.getGroupInterfaceDetails(group_name));
		} catch (Exception e54) {
			e54.printStackTrace();
		} finally {
			out.close();
		}

	}

	@RequestMapping("/interfaceStatusReportForm")
	public ModelAndView DeviceReportForm(HttpServletRequest req, HttpServletResponse res) {
		System.out.println("Controller Called interfaceStatusReport");
		ModelAndView m = new ModelAndView();
		m.addObject("groupName", dao.getGroupMap());
		m.setViewName("InterfaceStatusReportForm");
		return m;
	}

	@RequestMapping("/interfaceStatusReport")
	public ModelAndView nodeStatusReport(HttpServletRequest req, HttpServletResponse res) {
		System.out.println("Controller interfaceStatusReport");
		String from_date = req.getParameter("from_date");
		String to_date = req.getParameter("to_date");
		String[] ip_address = req.getParameterValues("ipAddressCheck");
		List<String> list = Arrays.asList(ip_address);
		System.out.println("ip_list :" + list);
		System.out.println("to_date :" + to_date);
		System.out.println("from date:" + from_date);

		List<InterfaceStatusReportBean> historyData = service.InterfaceStatusReport(from_date, to_date, list);
		ModelAndView m = new ModelAndView();
		m.addObject("InterfaceReportData", historyData);
		m.addObject("fdate", from_date);
		m.addObject("tdate", to_date);
		m.setViewName("InterfaceStatusReportView");
		return m;

	}

	// Interface Bandwidth History

	@RequestMapping("/interfaceBandwidthHistoryForm")
	public ModelAndView interfaceBandwidthHistoryForm(HttpServletRequest req, HttpServletResponse res) {
		System.out.println("Controller Called interfaceBandwidthHistoryForm");
		ModelAndView m = new ModelAndView();
		m.addObject("groupName", dao.getGroupMap());
		m.setViewName("interfaceBandwidthHistoryForm");
		return m;
	}

	@RequestMapping("/interfaceBandwidthHistoryReport")
	public ModelAndView InterfaceBandwidthHistoryReport(HttpServletRequest req, HttpServletResponse res) {
		System.out.println("Controller interfaceBandwidthHistoryReport");
		String from_date = req.getParameter("from_date");
		String to_date = req.getParameter("to_date");
		String[] ip_address = req.getParameterValues("ipAddressCheck");
		List<String> list = Arrays.asList(ip_address);
		System.out.println("ip_list :" + list);
		System.out.println("to_date :" + to_date);
		System.out.println("from date:" + from_date);

		List<InterfaceBandwidthHistoryBean> historyData = service.InterfaceBandwidthHistoryReport(from_date, to_date,
				list);
		ModelAndView m = new ModelAndView();
		m.addObject("InterfaceReportData", historyData);
		m.addObject("fdate", from_date);
		m.addObject("tdate", to_date);
		m.setViewName("interfaceBandwidthHistoryView");
		return m;

	}

	// Bandwidth Threshold History

	@RequestMapping("/bandwidthThresholdHistoryForm")
	public ModelAndView bandwidthThresholdHistoryForm(HttpServletRequest req, HttpServletResponse res) {
		System.out.println("Controller Called bandwidthThresholdHistoryForm");
		ModelAndView m = new ModelAndView();
		m.addObject("groupName", dao.getGroupMap());
		m.setViewName("BandwidthThresholdHistoryForm");
		return m;
	}

	@RequestMapping("/bandwidthThresholdHistoryReport")
	public ModelAndView bandwidthThresholdHistoryReport(HttpServletRequest req, HttpServletResponse res) {
		System.out.println("Controller bandwidthThresholdHistoryReport");
		String from_date = req.getParameter("from_date");
		String to_date = req.getParameter("to_date");
		String[] ip_address = req.getParameterValues("ipAddressCheck");
		List<String> list = Arrays.asList(ip_address);
		System.out.println("ip_list :" + list);
		System.out.println("to_date :" + to_date);
		System.out.println("from date:" + from_date);

		List<BWThresholdHistoryBean> historyData = service.bandwidthThresholdHistoryReport(from_date, to_date, list);
		ModelAndView m = new ModelAndView();
		m.addObject("InterfaceReportData", historyData);
		m.addObject("fdate",from_date);
		m.addObject("tdate",to_date);
		m.setViewName("BandwidthThresholdHistoryView");
		return m;

	}

	// Interface CRC Value

	@RequestMapping("/interfaceCrcHistoryForm")
	public ModelAndView interfaceCrcHistoryForm(HttpServletRequest req, HttpServletResponse res) {
		System.out.println("Controller Called interfaceCrcHistoryForm");
		ModelAndView m = new ModelAndView();
		m.addObject("groupName", dao.getGroupMap());
		m.setViewName("InterfaceCRCHistoryForm");
		return m;
	}

	@RequestMapping("/interfaceCrcHistoryReport")
	public ModelAndView interfaceCrcHistoryReport(HttpServletRequest req, HttpServletResponse res) {
		System.out.println("Controller interfaceBandwidthHistoryReport");
		String from_date = req.getParameter("from_date");
		String to_date = req.getParameter("to_date");
		String[] ip_address = req.getParameterValues("ipAddressCheck");
		List<String> list = Arrays.asList(ip_address);
		System.out.println("ip_list :" + list);
		System.out.println("to_date :" + to_date);
		System.out.println("from date:" + from_date);

		List<InterfaceCRCHistoryBean> historyData = service.interfaceCrcHistoryReport(from_date, to_date, list);
		ModelAndView m = new ModelAndView();
		m.addObject("InterfaceReportData", historyData);
		m.addObject("fdate",from_date);
		m.addObject("tdate",to_date);
		m.setViewName("InterfaceCRCHistoryView");
		return m;

	}

	// Interface Availability

	@RequestMapping("/interfaceAvailabilityForm")
	public ModelAndView interfaceAvailabilityForm(HttpServletRequest req, HttpServletResponse res) {
		System.out.println("Controller Called interfaceAvailabilityForm");
		ModelAndView m = new ModelAndView();
		m.addObject("groupName", dao.getGroupMap());
		m.setViewName("InterfaceAvailabilityForm");
		return m;
	}

	@RequestMapping("/interfaceAvailabilityReport")
	public ModelAndView interfaceAvailabilityReport(HttpServletRequest req, HttpServletResponse res) {
		System.out.println("Controller interfaceAvailabilityReport");
		String from_date = req.getParameter("from_date");
		String to_date = req.getParameter("to_date");
		String[] ip_address = req.getParameterValues("ipAddressCheck");
		List<String> list = Arrays.asList(ip_address);
		System.out.println("ip_list :" + list);
		System.out.println("to_date :" + to_date);
		System.out.println("from date:" + from_date);

		List<InterfaceAvailabilityBean> historyData = service.interfaceAvailabilityReport(from_date, to_date, list);
		ModelAndView m = new ModelAndView();
		m.addObject("InterfaceReportData", historyData);
		m.addObject("fdate",from_date);
		m.addObject("tdate",to_date);
		m.setViewName("InterfaceAvailabilityView");
		return m;

	}

	// Get interface name by IP

	@RequestMapping("/getInterfaceNameByIP")
	public void getInterfaceNameByIP(HttpServletRequest req, HttpServletResponse res) {
		System.out.println("Controller getInterfaceNameByIP");
		String ip_address = req.getParameter("ip_address");

		System.out.println("ip_address :" + ip_address);

		String interface_name = service.getInterfaceNameByIP(ip_address);
		System.out.println("interface Name = " + interface_name);
		PrintWriter out = null;
		try {
			out = res.getWriter();
			out.print(interface_name);
		} catch (Exception e54) {
			e54.printStackTrace();
		} finally {
			out.close();
		}
//		ModelAndView m = new ModelAndView();
//		m.addObject("InterfaceReportData", historyData);
//		m.setViewName("InterfaceAvailabilityView");
//		return m;
	}

}
