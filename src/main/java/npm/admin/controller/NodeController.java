package npm.admin.controller;

import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import npm.admin.beans.AddNodeBean;
import npm.admin.beans.AddNodeParameterBean;
import npm.admin.beans.DeviceTypeMasterBean;
import npm.admin.beans.GroupMasterBean;
import npm.admin.beans.LocationMasterBean;
import npm.admin.beans.ServiceProviderMasterBean;
import npm.admin.dao.MasterDao;
import npm.admin.beans.SNMPConfigMasterBean;
import npm.admin.service.NodeService;


@Controller
@RequestMapping("/node")
public class NodeController {
	
	@Autowired
	MasterDao dao;
	
	@Autowired
	NodeService service;
	
	
	//Add Node
	@RequestMapping("/addNodeForm")
	public String addUserForm(HttpServletRequest req, HttpServletResponse res, Model m) {
		System.out.println("Controller Called addNodeForm");

		m.addAttribute("addNode", new AddNodeBean());
		m.addAttribute("deviceType", new DeviceTypeMasterBean());
		m.addAttribute("deviceType", service.getdeviceType());
		
		m.addAttribute("groupName", new GroupMasterBean());
		m.addAttribute("groupName", service.getgroupName());
		
		m.addAttribute("serviceProvider", new ServiceProviderMasterBean());
		m.addAttribute("serviceProvider", service.getserviceProvider());
		
		m.addAttribute("snmp", new SNMPConfigMasterBean());
		m.addAttribute("snmp", service.getSnmp());

		return "addNode";
	}

	@RequestMapping(value = "/addNode", method = RequestMethod.POST)
	public void addIcmp(@RequestParam("deviceIp") String deviceIp, @RequestParam("deviceName") String deviceName,
			@RequestParam("deviceType") String deviceType, @RequestParam("groupName") String groupName,
			@RequestParam("snmp") String snmp, @RequestParam("serviceProvider") String serviceProvider,
			@RequestParam("company") String company, @RequestParam("state") String state,  
			@RequestParam("zone") String zone, @RequestParam("location") String location, @RequestParam("district") String district,HttpServletResponse res) {
		try {
			PrintWriter out = res.getWriter();
			System.out.println("Controller Called addNode");
			String result = service.addNode(deviceIp, deviceName, deviceType, groupName, snmp, serviceProvider, company, state, zone, location, district);
			System.out.println("Status:" + result);
			System.out.println("ip = " +deviceIp);
			out.print(result);
		} catch (Exception e) {
			System.out.println("Exception:" + e);
		}

	}
	
	@RequestMapping(value = "/viewAddNode", method = RequestMethod.GET)
	public ModelAndView viewAddNode() {
		ModelAndView m = new ModelAndView();
		m.addObject("viewAddNode", service.viewAddNode());
		m.setViewName("viewAddNode");
		return m;
	}
	
	@RequestMapping(value = "/deleteAddNode", method = RequestMethod.POST)
	// public String delete(@PathVariable long ID) {
	public void deleteAddNode(@RequestParam("id") long ID, HttpServletResponse res) {

		try {
			PrintWriter out = res.getWriter();
			System.out.println("Delete controller called:" + ID);
			String result = service.deleteAddNode(ID);
			System.out.println("delete Status:" + result);

			out.print(result);
		} catch (Exception e) {
			System.out.println("Exception:" + e);
		}

	}
	
	@RequestMapping(value = "/editAddNode/{ID}")
	public ModelAndView editNodeAdd(@PathVariable long ID, ModelAndView m) {

		System.out.println("editNodeAdd edit controller called");
		// public String editUser(@RequestParam("id") long ID, Model m) {
		AddNodeBean emp = service.editAddNode(ID);
		m.addObject("addNodeId", ID);
		m.addObject("addNode", emp);
		m.addObject("deviceType", service.getdeviceType());
		m.addObject("groupName", service.getgroupName());
		m.addObject("serviceProvider", service.getserviceProvider());
		m.addObject("snmp", service.getSnmp());
		m.setViewName("addNode");
		return m;
	}
	
	@RequestMapping(value = "/updateAddNode")
	public void updateCompanyMaster(@RequestParam("deviceIp") String deviceIp, @RequestParam("deviceName") String deviceName,
			@RequestParam("deviceType") String deviceType, @RequestParam("groupName") String groupName,
			@RequestParam("snmp") String snmp, @RequestParam("serviceProvider") String serviceProvider,
			@RequestParam("company") String company, @RequestParam("state") String state,  
			@RequestParam("zone") String zone, @RequestParam("location") String location, @RequestParam("district") String district,HttpServletResponse res,
			 @RequestParam("id") long id) {

		try {
			PrintWriter out = res.getWriter();

			System.out.println("Controller Called updateAddNode");
			String result = service.updateAddNode(deviceIp, deviceName, deviceType, groupName, snmp, serviceProvider, company, state, zone, location, district, id);
			System.out.println("update Status:" + result);

			out.print(result);
		} catch (Exception e) {
			System.out.println("Exception:" + e);
		}
	}

	
	
	//Add Node Parameter Form
	
	@RequestMapping("/addNodeParameterForm")
	public String addNodeParameterForm(HttpServletRequest req, HttpServletResponse res, Model m) {
		System.out.println("Controller Called addNodeParameterForm");

		m.addAttribute("addNodeParameter", new AddNodeParameterBean());

		return "nodeParameter";
	}
	
	@RequestMapping(value = "/addNodeParameter", method = RequestMethod.POST)
	public void addIcmp(@RequestParam("ip") String ip, @RequestParam("memoryThreshold") int memoryThreshold,
			@RequestParam("cpuThreshold") int cpuThreshold, @RequestParam("latencyThreshold") int latencyThreshold,
			@RequestParam("monitoring") String monitoring, @RequestParam("cpuHistory") String cpuHistory,
			@RequestParam("memoryHistory") String memoryHistory, @RequestParam("latencyHistory") String latencyHistory, 
			@RequestParam("snmpDiscovery") String snmpDiscovery, HttpServletResponse res) {
		try {
			PrintWriter out = res.getWriter();
			System.out.println("Controller Called addNodeParameter");
			String result = service.addNodeParameter(ip, memoryThreshold, cpuThreshold, latencyThreshold, monitoring, cpuHistory, memoryHistory, latencyHistory,snmpDiscovery);
			System.out.println("Status:" + result);
			out.print(result);
		} catch (Exception e) {
			System.out.println("Exception:" + e);
		}

	}
	
	@RequestMapping(value = "/viewNodeParameter", method = RequestMethod.GET)
	public ModelAndView viewNodeParameter() {
		ModelAndView m = new ModelAndView();
		m.addObject("viewNodeParameter", service.viewNodeParameter());
		m.setViewName("viewNodeParameter");
		return m;
	}
	
	@RequestMapping(value = "/deleteNodeParameter", method = RequestMethod.POST)
	// public String delete(@PathVariable long ID) {
	public void deleteNodeParameter(@RequestParam("id") long ID, HttpServletResponse res) {

		try {
			PrintWriter out = res.getWriter();
			System.out.println("Delete controller called:" + ID);
			String result = service.deleteNodeParameter(ID);
			System.out.println("delete Status:" + result);

			out.print(result);
		} catch (Exception e) {
			System.out.println("Exception:" + e);
		}

	}
	
	@RequestMapping(value = "/editNodeParameter/{ID}")
	public ModelAndView editNodeParameter(@PathVariable long ID, ModelAndView m) {

		System.out.println("node parameter edit controller called");
		// public String editUser(@RequestParam("id") long ID, Model m) {
		AddNodeParameterBean emp = service.editNodeParameter(ID);
		m.addObject("addNodeParameterId", ID);
		m.addObject("addNodeParameter", emp);
		m.setViewName("nodeParameter");
		return m;
	}
	
	@RequestMapping(value = "/updateNodeParameter")
	public void updateCompanyMaster(@RequestParam("ip") String ip, @RequestParam("memoryThreshold") int memoryThreshold,
			@RequestParam("cpuThreshold") int cpuThreshold, @RequestParam("latencyThreshold") int latencyThreshold,
			@RequestParam("monitoring") String monitoring, @RequestParam("cpuHistory") String cpuHistory,
			@RequestParam("memoryHistory") String memoryHistory, @RequestParam("latencyHistory") String latencyHistory, HttpServletResponse res,
			 @RequestParam("id") long id) {

		try {
			PrintWriter out = res.getWriter();

			System.out.println("Controller Called updateNodeParameter");
			String result = service.updateNodeParameter(ip, memoryThreshold, cpuThreshold, latencyThreshold, monitoring, cpuHistory, memoryHistory, latencyHistory, id);
			System.out.println("update Status:" + result);

			out.print(result);
		} catch (Exception e) {
			System.out.println("Exception:" + e);
		}
	}
	
	//Config Backup

	@RequestMapping("/configBackup")
	public ModelAndView DeviceReportForm(HttpServletRequest req, HttpServletResponse res) {
		System.out.println("Controller configBackup");

		ModelAndView m = new ModelAndView();
		m.addObject("groupName", dao.getGroupMap());
		m.setViewName("ConfigBackup");
		return m;
	}
	
	@RequestMapping("/addConfigBackup")
	public void addConfigBackup(HttpServletRequest req, HttpServletResponse res) {
		System.out.println("Controller addConfigBackup");
		
		PrintWriter out = null;
		
		try {
			
			String backupPath = req.getParameter("backuppath");
			String runConfig = req.getParameter("runConfig");
			String startupConfig = req.getParameter("startupConfig");
			String backup = req.getParameter("backup");
			String timepicker = req.getParameter("timepicker");
			String group_name = req.getParameter("group_name");
			String ip_address = req.getParameter("ipAddressCheck");
			
	  
			String result = service.addConfigBackup(backupPath, runConfig, startupConfig, backup, timepicker, group_name, ip_address);
			System.out.println("final response = "+result);
			out = res.getWriter();
			out.print(result);
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		finally {
			out.close();
		}

	}

	@RequestMapping(value = "/viewconfigBackupdata", method = RequestMethod.GET)
	public ModelAndView viewconfigBackup() {
		ModelAndView m = new ModelAndView();
		m.addObject("configBackup", service.viewconfigBackup());
		m.setViewName("viewConfigBackup");
		return m;
	}
	
	@RequestMapping(value = "/deleteConfigBackup", method = RequestMethod.POST)
	// public String delete(@PathVariable long ID) {
	public void deleteConfigBackup(@RequestParam("id") long ID, HttpServletResponse res) {

		try {
			PrintWriter out = res.getWriter();
			System.out.println("Delete controller called:" + ID);
			String result = service.deleteConfigBackup(ID);
			System.out.println("delete Status:" + result);

			out.print(result);
		} catch (Exception e) {
			System.out.println("Exception:" + e);
		}

	}
	
	//19-12-2022
	@RequestMapping(value = "/importNodeIP", method = RequestMethod.POST)
	public void importNodeIP(@RequestParam("nodeIP") String nodeIP,HttpServletResponse res) {

		try {
			PrintWriter out = res.getWriter();

			System.out.println("Controller Called nodeIP = "+nodeIP);
			String result = service.importNodeIP(nodeIP);
			//System.out.println("update Status:" + result);

			out.print(result);
		} catch (Exception e) {
			System.out.println("Exception:" + e);
		}
	}
	

}
