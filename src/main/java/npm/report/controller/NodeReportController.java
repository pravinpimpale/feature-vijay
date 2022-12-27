package npm.report.controller;

import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONArray;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.mysql.cj.xdevapi.JsonArray;

import npm.admin.beans.CpuThresholdHealthReportBean;
import npm.admin.beans.LatencyHistoryReportBean;
import npm.admin.beans.LatencyThresholdReportBean;
import npm.admin.beans.MemoryThresholdReportBean;
import npm.admin.beans.NodeAvailabilityBean;
import npm.admin.beans.NodeHealthHistoryReportBean;
import npm.admin.beans.NodeStatusReportBean;
import npm.admin.beans.UserMasterBean;
import npm.admin.dao.MasterDao;
import npm.model.NodeStatusHistory;
import npm.report.dao.NodeReportDao;
import npm.report.service.NodeReportService;

@Controller
@RequestMapping("/nodeReport")
public class NodeReportController {

	@Autowired
	MasterDao dao;

	@Autowired
	NodeReportDao NodeRdao;

	@Autowired
	NodeReportService service;

	@RequestMapping("/deviceStatus")
	public ModelAndView DeviceReportForm(HttpServletRequest req, HttpServletResponse res) {
		System.out.println("Controller Called deviceStatus report");

		// m.addAttribute("fetching", new UserMasterBean());
		ModelAndView m = new ModelAndView();
		m.addObject("groupName", dao.getGroupMap());
		m.setViewName("DeviceStatusReport");
		return m;
	}
	
	@RequestMapping("/latencyHistoryReportForm")
	public ModelAndView latencyHistoryReportForm(HttpServletRequest req, HttpServletResponse res) {
		System.out.println("Controller Called latencyHistoryReportForm");

		// m.addAttribute("fetching", new UserMasterBean());
		ModelAndView m = new ModelAndView();
		m.addObject("groupName", dao.getGroupMap());
		m.setViewName("LatencyHistoryReport");
		return m;
	}

	@RequestMapping("/latencyThresholdReportForm")
	public ModelAndView latencyThresholdReportForm(HttpServletRequest req, HttpServletResponse res) {
		System.out.println("Controller Called latencyThresholdReportForm");

		// m.addAttribute("fetching", new UserMasterBean());
		ModelAndView m = new ModelAndView();
		m.addObject("groupName", dao.getGroupMap());
		m.setViewName("LatencyThresholdReport");
		return m;
	}
	
	@RequestMapping("/nodeAvailabilityForm")
	public ModelAndView nodeAvailabilityForm(HttpServletRequest req, HttpServletResponse res) {
		System.out.println("Controller Called nodeAvailabilityForm");

		// m.addAttribute("fetching", new UserMasterBean());
		ModelAndView m = new ModelAndView();
		m.addObject("groupName", dao.getGroupMap());
		m.setViewName("NodeAvailability");
		return m;
	}
	
	@RequestMapping("/nodeHealthHistoryForm")
	public ModelAndView nodeHealthHistoryForm(HttpServletRequest req, HttpServletResponse res) {
		System.out.println("Controller Called nodeHealthHistoryForm");

		// m.addAttribute("fetching", new UserMasterBean());
		ModelAndView m = new ModelAndView();
		m.addObject("groupName", dao.getGroupMap());
		m.setViewName("nodeHealthHistoryReport");
		return m;
	}
	
	@RequestMapping("/cpuThresholdHistoryForm")
	public ModelAndView cpuThresholdHistoryForm(HttpServletRequest req, HttpServletResponse res) {
		System.out.println("Controller Called nodeHealthHistoryForm");

		// m.addAttribute("fetching", new UserMasterBean());
		ModelAndView m = new ModelAndView();
		m.addObject("groupName", dao.getGroupMap());
		m.setViewName("CPUThresholdHistoryReport");
		return m;
	}
	@RequestMapping("/memoryThresholdHistoryForm")
	public ModelAndView memoryThresholdHistoryForm(HttpServletRequest req, HttpServletResponse res) {
		System.out.println("Controller Called nodeHealthHistoryForm");

		// m.addAttribute("fetching", new UserMasterBean());
		ModelAndView m = new ModelAndView();
		m.addObject("groupName", dao.getGroupMap());
		m.setViewName("MemoryThresholdHistoryReport");
		return m;
	}
	@RequestMapping("/getGroupDeviceDetails")
	public void getGroupDeviceDetails(HttpServletRequest req, HttpServletResponse res,
			@RequestParam("groupName") String group_name) {
		System.out.println("Controller getGroupDeviceDetails" + group_name);
		PrintWriter out = null;
		try {
			out = res.getWriter();
			out.print(NodeRdao.getGroupDeviceDetails(group_name));
		} catch (Exception e54) {
			e54.printStackTrace();
		} finally {
			out.close();
		}

	}

	@RequestMapping("/nodeStatusReport")
	public ModelAndView nodeStatusReport(HttpServletRequest req, HttpServletResponse res) {
		System.out.println("Controller nodeStatusReport");
		String from_date = req.getParameter("from_date");
		String to_date = req.getParameter("to_date");
		String[] ip_address = req.getParameterValues("ipAddressCheck");
//		String ip = "192.168.0.230";
		List<String> list = Arrays.asList(ip_address);

		System.out.println("ip_list :" + list);
		System.out.println("to_date :" + to_date);
		System.out.println("from date:" + from_date);
  
		List<NodeStatusReportBean> historyData=service.NodeStatusReport(from_date, to_date, list);
		System.out.println("Report Data:"+historyData);
		ModelAndView m = new ModelAndView();
		m.addObject("statusReportData", historyData);
		m.addObject("fdate",from_date);
		m.addObject("tdate",to_date);
		m.setViewName("DeviceStatusReportView");
		return m;

	}
	
	@RequestMapping("/latencyHistoryReport")
	public ModelAndView latencyHistoryReport(HttpServletRequest req, HttpServletResponse res) {
		System.out.println("Controller nodeStatusReport");
		String from_date = req.getParameter("from_date");
		String to_date = req.getParameter("to_date");
		String[] ip_address = req.getParameterValues("ipAddressCheck");
		List<String> list = Arrays.asList(ip_address);

		System.out.println("ip_list :" + list);
		System.out.println("to_date :" + to_date);
		System.out.println("from date:" + from_date);
  
		List<LatencyHistoryReportBean> historyData=service.latencyHistoryReport(from_date, to_date, list);
		System.out.println("Report Data:"+historyData);
		ModelAndView m = new ModelAndView();
		m.addObject("statusReportData", historyData);
		m.addObject("fdate",from_date);
		m.addObject("tdate",to_date);
		m.setViewName("LatencyHistoryReportView");
		return m;

	}
	
	@RequestMapping("/latencyThresholdReport")
	public ModelAndView latencyThresholdReport(HttpServletRequest req, HttpServletResponse res) {
		System.out.println("Controller latencyThresholdReport");
		String from_date = req.getParameter("from_date");
		String to_date = req.getParameter("to_date");
		String[] ip_address = req.getParameterValues("ipAddressCheck");
		List<String> list = Arrays.asList(ip_address);

		System.out.println("ip_list :" + list);
		System.out.println("to_date :" + to_date);
		System.out.println("from date:" + from_date);
  
		List<LatencyThresholdReportBean> historyData=service.latencyThresholdReport(from_date, to_date, list);
		System.out.println("Report Data:"+historyData);
		ModelAndView m = new ModelAndView();
		m.addObject("statusReportData", historyData);
		m.addObject("fdate",from_date);
		m.addObject("tdate",to_date);
		m.setViewName("LatencyThresholdReportView");
		return m;

	}
	
	@RequestMapping("/nodeAvailabilityReport")
	public ModelAndView nodeAvailabilityReport(HttpServletRequest req, HttpServletResponse res) {
		System.out.println("Controller latencyThresholdReport");
		String from_date = req.getParameter("from_date");
		String to_date = req.getParameter("to_date");
		String[] ip_address = req.getParameterValues("ipAddressCheck");
		List<String> list = Arrays.asList(ip_address);

		System.out.println("ip_list :" + list);
		System.out.println("to_date :" + to_date);
		System.out.println("from date:" + from_date);
  
		List<NodeAvailabilityBean> historyData=service.nodeAvailabilityReport(from_date, to_date, list);
		System.out.println("Report Data:"+historyData);
		ModelAndView m = new ModelAndView();
		m.addObject("statusReportData", historyData);
		m.addObject("fdate", from_date);
		m.addObject("tdate", to_date);
		m.setViewName("NodeAvailabilityView");
		return m;

	}
	
	@RequestMapping("/nodeHealthHistoryReport")
	public ModelAndView nodeHealthHistoryReport(HttpServletRequest req, HttpServletResponse res) {
		System.out.println("Controller nodeHealthHistoryReport");
		String from_date = req.getParameter("from_date");
		String to_date = req.getParameter("to_date");
		String[] ip_address = req.getParameterValues("ipAddressCheck");
		List<String> list = Arrays.asList(ip_address);

		System.out.println("ip_list :" + list);
		System.out.println("to_date :" + to_date);
		System.out.println("from date:" + from_date);
  
		List<NodeHealthHistoryReportBean> historyData=service.nodeHealthHistoryReport(from_date, to_date, list);
		System.out.println("Report Data:"+historyData);
		ModelAndView m = new ModelAndView();
		m.addObject("statusReportData", historyData);
		m.addObject("fdate",from_date);
		m.addObject("tdate",to_date);
		m.setViewName("nodeHealthHistoryReportView");
		return m;

	}
	
	@RequestMapping("/cpuThresholdHistoryReport")
	public ModelAndView cpuThresholdHistoryReport(HttpServletRequest req, HttpServletResponse res) {
		System.out.println("Controller cpuThresholdHistoryReport");
		String from_date = req.getParameter("from_date");
		String to_date = req.getParameter("to_date");
		String[] ip_address = req.getParameterValues("ipAddressCheck");
		List<String> list = Arrays.asList(ip_address);

		System.out.println("ip_list :" + list);
		System.out.println("to_date :" + to_date);
		System.out.println("from date:" + from_date);
  
		List<CpuThresholdHealthReportBean> historyData=service.cpuThresholdHistoryReport(from_date, to_date, list);
		System.out.println("Report Data:"+historyData);
		ModelAndView m = new ModelAndView();
		m.addObject("statusReportData", historyData);
		m.addObject("fdate",from_date);
		m.addObject("tdate",to_date);
		m.setViewName("CPUThresholdHistoryReportView");
		return m;

	}
	
	@RequestMapping("/memoryThresholdHistoryReport")
	public ModelAndView memoryThresholdHistoryReport(HttpServletRequest req, HttpServletResponse res) {
		System.out.println("Controller memoryThresholdHistoryReport");
		String from_date = req.getParameter("from_date");
		String to_date = req.getParameter("to_date");
		String[] ip_address = req.getParameterValues("ipAddressCheck");
		List<String> list = Arrays.asList(ip_address);

		System.out.println("ip_list :" + list);
		System.out.println("to_date :" + to_date);
		System.out.println("from date:" + from_date);
  
		List<MemoryThresholdReportBean> historyData=service.memoryThresholdHistoryReport(from_date, to_date, list);
		System.out.println("Report Data:"+historyData);
		ModelAndView m = new ModelAndView();
		m.addObject("statusReportData", historyData);
		m.addObject("fdate",from_date);
		m.addObject("tdate",to_date);
		m.setViewName("MemoryThresholdHistoryReportView");
		return m;

	}
	
	@RequestMapping(value = "/nodeHealthMonitoringView", method = RequestMethod.GET)
	public ModelAndView nodeHealthMonitoringView()
	{
		ModelAndView m = new ModelAndView();
		m.addObject("node", service.nodeHealthMonitoringView());
		m.setViewName("NodeHealthMonitoringView");
		return m;
	}
	
	@RequestMapping(value = "/nodeMonitoringView", method = RequestMethod.GET)
	public ModelAndView nodeMonitoringView()
	{
		ModelAndView m = new ModelAndView();
		m.addObject("node", service.nodeMonitoringView());
		m.setViewName("NodeMonitoringView");
		return m;
	}
	
	@RequestMapping(value = "/interfaceMonitoringView", method = RequestMethod.GET)
	public ModelAndView interfaceMonitoringView()
	{
		ModelAndView m = new ModelAndView();
		m.addObject("node", service.interfaceMonitoringView());
		m.setViewName("InterfaceMonitoringView");
		return m;
	}
	
	
	@RequestMapping("/latencyHisotryGraphForm")
	public String latencyHisotryGraphForm(HttpServletRequest req, HttpServletResponse res) {
		System.out.println("Controller Called latencyHisotryGraphForm");
		return "LatencyHistoryGraphForm";
	}
	
	@RequestMapping("/latencyHistoryGraph")
	public void latencyHistoryGraph(HttpServletRequest req, HttpServletResponse res) {
		System.out.println("Controller latencyHistoryGraph");
		String from_date = req.getParameter("from_date");
		String to_date = req.getParameter("to_date");
		String ip_address = req.getParameter("ip_address");
		System.out.println("ip_address :" + ip_address);
		System.out.println("to_date :" + to_date);
		System.out.println("from date:" + from_date);
		PrintWriter out = null;
		try {
			out = res.getWriter();
			out.print(service.latencyHistoricalData(from_date, to_date, ip_address));
		} catch (Exception e) {
			System.out.println("Exception:"+e);
		} finally {
			out.close();
		}
  

	}
	
	// Interface Bandwidth History graph
	
	@RequestMapping("/interfaceBandwidthHistoryGraphForm")
	public String interfaceBandwidthHistoryGraphForm(HttpServletRequest req, HttpServletResponse res) {
		System.out.println("Controller Called interfaceBandwidthHistoryGraphForm");
		return "InterfaceBandwidthHistoryGraphForm";
	}
	
	@RequestMapping("/interfaceBandwidthHistoryGraph")
	public void interfaceBandwidthHistoryGraph(HttpServletRequest req, HttpServletResponse res) {
		System.out.println("Controller interfaceBandwidthHistoryGraph");
		String from_date = req.getParameter("from_date");
		String to_date = req.getParameter("to_date");
		String ip_address = req.getParameter("ip_address");
		String interface_name = req.getParameter("interface_name");
		System.out.println("interface name = "+interface_name);
		System.out.println("ip_address :" + ip_address);
		System.out.println("to_date :" + to_date);
		System.out.println("from date:" + from_date);
		PrintWriter out = null;
		try {
			out = res.getWriter();
			out.print(service.interfaceBWHistoryData(from_date, to_date, ip_address, interface_name));
		} catch (Exception e) {
			System.out.println("Exception:"+e);
		} finally {
			out.close();
		}
  

	}
	
	// Node Health History Graph

	@RequestMapping("/nodeHealthHistoryGraphForm")
	public String nodeHealthHistoryGraphForm(HttpServletRequest req, HttpServletResponse res) {
		System.out.println("Controller Called nodeHealthHistoryGraphForm");
		return "NodeHealthHistoryGraphForm";
	}
	
	@RequestMapping("/nodeHealthHistoryGraph")
	public void nodeHealthHistoryGraph(HttpServletRequest req, HttpServletResponse res) {
		System.out.println("Controller nodeHealthHistoryGraph");
		String from_date = req.getParameter("from_date");
		String to_date = req.getParameter("to_date");
		String ip_address = req.getParameter("ip_address");
		System.out.println("ip_address :" + ip_address);
		System.out.println("to_date :" + to_date);
		System.out.println("from date:" + from_date);
		PrintWriter out = null;
		try {
			out = res.getWriter();
			out.print(service.nodeHealthHistoryData(from_date, to_date, ip_address));
		} catch (Exception e) {
			System.out.println("Exception:"+e);
		} finally {
			out.close();
		}
  

	}
	
	
	// Node Availability
	@RequestMapping("/nodeAvailabilityGraphForm")
	public String nodeAvailabilityGraphForm(HttpServletRequest req, HttpServletResponse res) {
		System.out.println("Controller Called nodeAvailabilityGraphForm");
		return "NodeAvailabilityGraphForm";
	}
	
	@RequestMapping("/nodeAvailabilityGraph")
	public void nodeAvailabilityGraph(HttpServletRequest req, HttpServletResponse res) {
		System.out.println("Controller nodeAvailabilityGraph");
		String from_date = req.getParameter("from_date");
		String to_date = req.getParameter("to_date");
		String ip_address = req.getParameter("ip_address");
		System.out.println("ip_address :" + ip_address);
		System.out.println("to_date :" + to_date);
		System.out.println("from date:" + from_date);
		PrintWriter out = null;
		try {
			out = res.getWriter();
			out.print(service.nodeAvailabilityData(from_date, to_date, ip_address));
		} catch (Exception e) {
			System.out.println("Exception:"+e);
		} finally {
			out.close();
		}
  

	}
	
	//Interface Availability Graph
	
	@RequestMapping("/interfaceAvailabilityGraphForm")
	public String 	interfaceAvailabilityGraphForm(HttpServletRequest req, HttpServletResponse res) {
		System.out.println("Controller Called 	interfaceAvailabilityGraphForm");
		return "InterfaceAvailabilityGraphForm";
	}
	
	@RequestMapping("/interfaceAvailabilityGraph")
	public String interfaceAvailabilityGraph(HttpServletRequest req, HttpServletResponse res) {
		System.out.println("Controller nodeAvailabilityGraph");
		String from_date = req.getParameter("from_date");
		String to_date = req.getParameter("to_date");
		String ip_address = req.getParameter("ip_address");
		String interface_name = req.getParameter("interface_name");
		System.out.println("interface name = "+interface_name);
		System.out.println("ip_address :" + ip_address);
		System.out.println("to_date :" + to_date);
		System.out.println("from date:" + from_date);
		PrintWriter out = null;
		try {
			out = res.getWriter();
			out.print(service.interfaceAvailabilityData(from_date, to_date, ip_address, interface_name));
		} catch (Exception e) {
			System.out.println("Exception:"+e);
		} finally {
			out.close();
		}
		
		return null;

	}
	
	
	// Device Alert Log
	
	@RequestMapping("/deviceAlertLog")
	public ModelAndView viewDeviceAlertLog()
	{
		
		ModelAndView m = new ModelAndView();
		m.addObject("deviceALertLog", service.viewDeviceAlertLog());
		m.setViewName("viewDeviceAlertlog");
		
		return m;
		
	}
	
	
	
	

}
