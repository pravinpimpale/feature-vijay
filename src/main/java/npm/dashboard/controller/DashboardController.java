package npm.dashboard.controller;

import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.json.JSONArray;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import npm.dashboard.service.DashboardService;

@Controller
@RequestMapping("/dashboard")
public class DashboardController {

	@Autowired
	DashboardService service;

	/* Summary Dashboard Page */

	@RequestMapping("/summaryPage")
	public String summaryDashboardPage(HttpServletRequest req, HttpServletResponse res) {
		System.out.println("Controller Called summaryPage");

		return "summary";
	}

	/* Get Device Summary Count */
	@RequestMapping(value = "/deviceSummaryCount", method = RequestMethod.GET)
	public JSONArray deviceSummaryCount(ModelMap model, HttpServletRequest request, HttpServletResponse response,
			HttpSession session) {

		PrintWriter out = null;
		try {
			out = response.getWriter();
			JSONArray jsonresponse = service.deviceSummaryCount();
			out.print(jsonresponse);

		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		} finally {
			out.close();
		}

		return null;
	}

	/* Get Link Summary Count */
	@RequestMapping(value = "/linkSummaryCount", method = RequestMethod.GET)
	public JSONArray linkSummaryCount(ModelMap model, HttpServletRequest request, HttpServletResponse response,
			HttpSession session) {

		PrintWriter out = null;
		try {
			out = response.getWriter();
			JSONArray jsonresponse = service.linkSummaryCount();
			out.print(jsonresponse);

		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		} finally {
			out.close();
		}

		return null;
	}

	/* Get Down Devices Summary Listing */

	@RequestMapping(value = "/deviceSummaryList", method = RequestMethod.GET)
	public JSONArray deviceSummaryList(ModelMap model, HttpServletRequest request, HttpServletResponse response,
			HttpSession session) {

		PrintWriter out = null;
		try {
			out = response.getWriter();
			JSONArray jsonresponse = service.deviceSummaryList();
			out.print(jsonresponse);

		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		} finally {
			out.close();
		}

		return null;
	}

	/* Get Down Link Summary Listing */

	@RequestMapping(value = "/linkSummaryList", method = RequestMethod.GET)
	public JSONArray linkSummaryList(ModelMap model, HttpServletRequest request, HttpServletResponse response,
			HttpSession session) {

		PrintWriter out = null;
		try {
			out = response.getWriter();
			JSONArray jsonresponse = service.linkSummaryList();
			out.print(jsonresponse);

		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		} finally {
			out.close();
		}

		return null;
	}


	/* Get Device Info */
	@RequestMapping(value = "/getDeviceInfo", method = RequestMethod.GET)
	public JSONArray getDeviceInfo(@RequestParam("deviceInfo") String deviceInfo,
			HttpServletResponse response) {

		PrintWriter out = null;
		try {
			out = response.getWriter();
			JSONArray jsonresponse = service.getDeviceInfo(deviceInfo);
			out.print(jsonresponse);

		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		} finally {
			out.close();
		}

		return null;
	}
	
	/* Get Link Info */
	@RequestMapping(value = "/getLinkInfo", method = RequestMethod.GET)
	public JSONArray getLinkInfo(@RequestParam("linkInfo") String linkInfo,
			HttpServletResponse response) {

		PrintWriter out = null;
		try {
			out = response.getWriter();
			JSONArray jsonresponse = service.getLinkInfo(linkInfo);
			out.print(jsonresponse);

		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		} finally {
			out.close();
		}

		return null;
	}
	
	/* Top 10 Dashboard Page */

	@RequestMapping("/topUtilization")
	public String topUtilizationPage(HttpServletRequest req, HttpServletResponse res) {
		System.out.println("Controller Called topUtilization");

		return "topUtilization";
	}

	/* Top CPU Summary Listing */

	@RequestMapping(value = "/topCPUSummary", method = RequestMethod.GET)
	public JSONArray topCPUSummary(ModelMap model, HttpServletRequest request, HttpServletResponse response,
			HttpSession session) {

		PrintWriter out = null;
		try {
			out = response.getWriter();
			JSONArray jsonresponse = service.topCPUSummary();
			out.print(jsonresponse);

		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		} finally {
			out.close();
		}

		return null;
	}

	/* Interface Details Page */

	@RequestMapping("/interfaceDetailsPage")
	public String interfaceDetailsPage(HttpServletRequest req, HttpServletResponse res) {
		System.out.println("Controller Called interfaceDetailsPage");

		return "interfaceDetails";
	}

	/* Interface Details Page */

	@RequestMapping("/interfaceInfoPage")
	public String interfaceInfoPage(HttpServletRequest req, HttpServletResponse res) {
		System.out.println("Controller Called interfaceInfoPage");

		return "interfaceInfo";
	}

	/* Get Interface Info */
	@RequestMapping(value = "/interfaceInfo", method = RequestMethod.POST)
	public JSONArray interfaceInfo(@RequestParam("deviceIP") String deviceIP, @RequestParam("intName") String intName,
			HttpServletResponse response) {

		PrintWriter out = null;
		try {
			out = response.getWriter();
			JSONArray jsonresponse = service.interfaceInfo(deviceIP, intName);
			out.print(jsonresponse);

		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		} finally {
			out.close();
		}

		return null;
	}

	/* Get Interface Status History */
	@RequestMapping(value = "/interfaceStatusHistory", method = RequestMethod.POST)
	public JSONArray interfaceStatusHistory(@RequestParam("deviceIP") String deviceIP,
			@RequestParam("intName") String intName, HttpServletResponse response) {

		PrintWriter out = null;
		try {
			out = response.getWriter();
			JSONArray jsonresponse = service.interfaceStatusHistory(deviceIP, intName);
			out.print(jsonresponse);

		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		} finally {
			out.close();
		}

		return null;
	}

	/* Get Interface CRC Log */
	@RequestMapping(value = "/interfaceCRCLog", method = RequestMethod.POST)
	public JSONArray interfaceCRCLog(@RequestParam("deviceIP") String deviceIP, @RequestParam("intName") String intName,
			HttpServletResponse response) {

		PrintWriter out = null;
		try {
			out = response.getWriter();
			JSONArray jsonresponse = service.interfaceCRCLog(deviceIP, intName);
			out.print(jsonresponse);

		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		} finally {
			out.close();
		}

		return null;
	}

	
	/* Get Interface Uptime Log */
	@RequestMapping(value = "/interfaceUptimeLog", method = RequestMethod.POST)
	public JSONArray interfaceUptimeLog(@RequestParam("deviceIP") String deviceIP, @RequestParam("intName") String intName,
			HttpServletResponse response) {

		PrintWriter out = null;
		try {
			out = response.getWriter();
			JSONArray jsonresponse = service.interfaceUptimeLog(deviceIP, intName);
			out.print(jsonresponse);

		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		} finally {
			out.close();
		}

		return null;
	}
	
	/* Get Interface Bandwidth Utilization */
	
	@RequestMapping(value = "/interfaceBandwidthUtil", method = RequestMethod.POST)
	public JSONArray interfaceBandwidthUtil(@RequestParam("deviceIP") String deviceIP, @RequestParam("intName") String intName,
			HttpServletResponse response) {

		PrintWriter out = null;
		try {
			out = response.getWriter();
			JSONArray jsonresponse = service.interfaceBandwidthUtil(deviceIP, intName);
			out.print(jsonresponse);

		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		} finally {
			out.close();
		}

		return null;
	}
	
	
	/* Node Details Page */

	@RequestMapping("/nodeDetailsPage")
	public String nodeDetailsPage(HttpServletRequest req, HttpServletResponse res) {
		System.out.println("Controller Called nodeDetailsPage");

		return "nodeDetails";
	}
	
	//Export Node Latency
		@RequestMapping("/exportNodeLatency")
		public ModelAndView exportNodeLatency(HttpServletRequest req, HttpServletResponse res) {
			
			String from_date = req.getParameter("fromDate");
			String to_date = req.getParameter("toDate");
			String ip_address = req.getParameter("ipAddress");
			
			ModelAndView m = new ModelAndView();
			m.addObject("data", service.exportNodeLatency(from_date, to_date, ip_address));
			m.setViewName("exportNodeLatencyReport");
			
			return m;
			
			

		}
//	@RequestMapping(value = { "/nodeDetailsPage/{id}" }, method = RequestMethod.GET)
//	public String machineInfo(ModelMap model, @PathVariable String id) {
//		System.out.println("Controller Called nodeDetailsPage:"+id);
//
//		return "nodeDetails";
//	}
	
	/* TopTalker Dashboard Page */

	@RequestMapping("/toptalker")
	public String toptalkerDashboardPage(HttpServletRequest req, HttpServletResponse res) {
		System.out.println("Controller Called ToptalkerPage");

		return "toptalker";
	}
	
	/* Get Toptalker Link Summary Listing */

	@RequestMapping(value = "/topTalkerLinkSummaryList", method = RequestMethod.GET)
	public JSONArray topTalkerLinkSummaryList(ModelMap model, HttpServletRequest request, HttpServletResponse response,
			HttpSession session) {

		PrintWriter out = null;
		try {
			out = response.getWriter();
			JSONArray jsonresponse = service.topTalkerLinkSummaryList();
			out.print(jsonresponse);

		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		} finally {
			out.close();
		}

		return null;
	}
	
	/* TopTalker Dashboard Page */

	@RequestMapping("/toptalkerinfo")
	public String toptalkerInfoPage(HttpServletRequest req, HttpServletResponse res) {
		System.out.println("Controller Called ToptalkerInfoPage");

		return "topTalkerInfo";
	}
}
