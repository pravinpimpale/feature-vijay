package npm.admin.controller;

import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.json.JSONArray;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import npm.admin.beans.ADConfigMasterBean;
import npm.admin.beans.CompanyMasterBean;
import npm.admin.beans.DeviceTypeMasterBean;
import npm.admin.beans.EmailConfigMasterBean;
import npm.admin.beans.GroupMasterBean;
import npm.admin.beans.IcmpMasterBean;
import npm.admin.beans.LocationMasterBean;
import npm.admin.beans.MakeModelMasterBean;
import npm.admin.beans.NwIpScanBean;
import npm.admin.beans.SNMPConfigMasterBean;
import npm.admin.beans.ServiceProviderMasterBean;
import npm.admin.beans.UserMasterBean;
import npm.admin.service.MasterService;

@Controller
@RequestMapping("/master")
public class MasterController {

	@Autowired
	MasterService service;

	@RequestMapping("/addUserForm")
	public String addUserForm(HttpServletRequest req, HttpServletResponse res, Model m) {
		System.out.println("Controller Called addUserForm");

		m.addAttribute("fetching", new UserMasterBean());

		return "addUser";
	}

	@RequestMapping(value = "/addUser", method = RequestMethod.POST)
//    public String submitForm( @Valid @ModelAttribute("emp") Employee e, BindingResult br)  
//    {  
	public void addUser(@RequestParam("name") String name, @RequestParam("username") String username,
			@RequestParam("password") String password, @RequestParam("confirmPassword") String confirmPassword,
			@RequestParam("email") String email, @RequestParam("role") String role,
			@RequestParam("department") String department, @RequestParam("mobileNo") String mobileNo,
			HttpServletResponse res) {
		try {
			PrintWriter out = res.getWriter();
			System.out.println("Controller Called addUser");
			String result = service.addUser(username, password, email, mobileNo, name, confirmPassword, role,
					department);
			System.out.println("Status:" + result);
			out.print(result);
		} catch (Exception e) {
			System.out.println("Exception:" + e);
		}

	}

//	/@RequestMapping(value = "/updateUser", method = RequestMethod.PUT)

	@RequestMapping(value = "/updateUser")
	public void updateUser(@RequestParam("name") String name, @RequestParam("username") String username,
			@RequestParam("id") long id, @RequestParam("password") String password,
			@RequestParam("confirmPassword") String confirmPassword, @RequestParam("email") String email,
			@RequestParam("role") String role, @RequestParam("department") String department,
			@RequestParam("mobileNo") String mobileNo, HttpServletResponse res) {

		try {
			PrintWriter out = res.getWriter();

			System.out.println("Controller Called UpdateUser");
			String result = service.updateUser(id, username, password, email, mobileNo, name, confirmPassword, role,
					department);
			System.out.println("update Status:" + result);

			out.print(result);
		} catch (Exception e) {
			System.out.println("Exception:" + e);
		}

	}

	@RequestMapping(value = "/viewUsers", method = RequestMethod.GET)
	public ModelAndView viewUsers() {
		ModelAndView m = new ModelAndView();
		m.addObject("userData", service.viewUsers());
		m.setViewName("viewUsers");
		return m;
	}

	@RequestMapping(value = "/deleteUser", method = RequestMethod.POST)
	// public String delete(@PathVariable long ID) {
	public void delete(@RequestParam("id") long ID, HttpServletResponse res) {

		try {
			PrintWriter out = res.getWriter();
			System.out.println("Delete controller calleda:" + ID);
			String result = service.deleteUser(ID);
			System.out.println("delete Status:" + result);

			out.print(result);
		} catch (Exception e) {
			System.out.println("Exception:" + e);
		}

	}

	@RequestMapping(value = "/editUser/{ID}")
	public ModelAndView editUser(@PathVariable long ID, ModelAndView m) {

		System.out.println("Edit controller called");
		// public String editUser(@RequestParam("id") long ID, Model m) {
		UserMasterBean emp = service.editUser(ID);
		m.addObject("userId", ID);
		m.addObject("fetching", emp);
		m.setViewName("addUser");
		return m;
	}

	// ICMP Configuration

	@RequestMapping(value = "/addIcmpform")
	public String icmpCall(Model m) {
		System.out.println("ICMP called");
		m.addAttribute("icmp", new IcmpMasterBean());
		return "ICMP";
	}

	@RequestMapping(value = "/addIcmp", method = RequestMethod.POST)
//  public String submitForm( @Valid @ModelAttribute("emp") Employee e, BindingResult br)  
//  {  
	public void addIcmp(@RequestParam("packetSize") String packetSize, @RequestParam("packetCount") String packetCount,
			@RequestParam("poolingTime") String poolingTime, @RequestParam("timeout") String timeout,
			@RequestParam("ttl") String ttl, @RequestParam("customerName") String customerName,
			@RequestParam("location") String location, HttpServletResponse res) {
		try {
			PrintWriter out = res.getWriter();
			System.out.println("Controller Called icmpUser");
			String result = service.addIcmp(packetSize, packetCount, poolingTime, timeout, ttl, customerName, location);
			System.out.println("Status:" + result);
			out.print(result);
		} catch (Exception e) {
			System.out.println("Exception:" + e);
		}

	}

	@RequestMapping(value = "/viewIcmp", method = RequestMethod.GET)
	public ModelAndView viewIcmp() {
		ModelAndView m = new ModelAndView();
		m.addObject("icmpConfig", service.viewIcmp());
		m.setViewName("viewIcmp");
		return m;
	}

	@RequestMapping(value = "/deleteIcmp", method = RequestMethod.POST)
	// public String delete(@PathVariable long ID) {
	public void deleteIcmp(@RequestParam("id") long ID, HttpServletResponse res) {

		try {
			PrintWriter out = res.getWriter();
			System.out.println("Delete controller calleda:" + ID);
			String result = service.deleteIcmp(ID);
			System.out.println("delete Status:" + result);

			out.print(result);
		} catch (Exception e) {
			System.out.println("Exception:" + e);
		}

	}

	@RequestMapping(value = "/editIcmp/{ID}")
	public ModelAndView editIcmp(@PathVariable long ID, ModelAndView m) {

		System.out.println("Icmp edit controller called");
		// public String editUser(@RequestParam("id") long ID, Model m) {
		IcmpMasterBean emp = service.editIcmp(ID);
		m.addObject("icmpId", ID);
		m.addObject("icmp", emp);
		m.setViewName("ICMP");
		return m;
	}

	@RequestMapping(value = "/updateIcmp")
	public void updateIcmp(@RequestParam("packetSize") String packetSize,
			@RequestParam("packetCount") String packetCount, @RequestParam("id") long id,
			@RequestParam("poolingTime") String poolingTime, @RequestParam("timeout") String timeout,
			@RequestParam("ttl") String ttl, @RequestParam("customerName") String customerName,
			@RequestParam("location") String location, HttpServletResponse res) {

		try {
			PrintWriter out = res.getWriter();

			System.out.println("Controller Called UpdateIcmp");
			String result = service.updateIcmp(id, packetSize, packetCount, poolingTime, timeout, ttl, customerName,
					location);
			System.out.println("update Status:" + result);

			out.print(result);
		} catch (Exception e) {
			System.out.println("Exception:" + e);
		}

	}

	// Company Master
	@RequestMapping(value = "/companyMasterform")
	public String companyMasterForm(Model m) {
		System.out.println("Company master  called");
		m.addAttribute("companyMaster", new CompanyMasterBean());
		return "companyMaster";
	}
	
	@RequestMapping(value = "/addCompany", method = RequestMethod.POST)
//  public String submitForm( @Valid @ModelAttribute("emp") Employee e, BindingResult br)  
//  {  
	public void addCompany(@RequestParam("companyName") String companyName, @RequestParam("address") String address,
			@RequestParam("email") String email, @RequestParam("number") String number,
			 HttpServletResponse res) {
		try {
			PrintWriter out = res.getWriter();
			System.out.println("Controller Called Company Master");
			String result = service.addCompany(companyName, address, email, number);
			System.out.println("Status:" + result);
			out.print(result);
		} catch (Exception e) {
			System.out.println("Exception:" + e);
		}

	}
	
	@RequestMapping(value = "/viewCompanyMaster", method = RequestMethod.GET)
	public ModelAndView viewCompanyMaster() {
		ModelAndView m = new ModelAndView();
		m.addObject("viewCompanyMaster", service.viewCompanyMaster());
		m.setViewName("viewCompanyMaster");
		return m;
	}
	
	@RequestMapping(value = "/deleteCompanyMaster", method = RequestMethod.POST)
	// public String delete(@PathVariable long ID) {
	public void deleteCompanyMaster(@RequestParam("id") long ID, HttpServletResponse res) {

		try {
			PrintWriter out = res.getWriter();
			System.out.println("Delete controller called:" + ID);
			String result = service.deleteCompanyMaster(ID);
			System.out.println("delete Status:" + result);

			out.print(result);
		} catch (Exception e) {
			System.out.println("Exception:" + e);
		}

	}
	

	@RequestMapping(value = "/editCompanyMaster/{ID}")
	public ModelAndView editCompanyMaster(@PathVariable long ID, ModelAndView m) {

		System.out.println("Company Master edit controller called");
		// public String editUser(@RequestParam("id") long ID, Model m) {
		CompanyMasterBean emp = service.editCompanyMaster(ID);
		m.addObject("companyMasterId", ID);
		m.addObject("companyMaster", emp);
		m.setViewName("companyMaster");
		return m;
	}
	
	@RequestMapping(value = "/updateCompanyMaster")
	public void updateCompanyMaster(@RequestParam("companyName") String companyName, @RequestParam("address") String address,
			@RequestParam("email") String email, @RequestParam("number") String number, @RequestParam("id") long id,
			 HttpServletResponse res) {

		try {
			PrintWriter out = res.getWriter();

			System.out.println("Controller Called updateCompanyMaster");
			String result = service.updateCompanyMaster(id, companyName, address, email, number);
			System.out.println("update Status:" + result);

			out.print(result);
		} catch (Exception e) {
			System.out.println("Exception:" + e);
		}

	}
	
	
	
	//location master 
	@RequestMapping(value = "/locationMasterform")
	public ModelAndView localtionMasterForm(ModelAndView modelview, Model m) {
//		  Map<String, String> countryMap = new HashMap();
//		    countryMap.put("AR", "Argentina");
//		    countryMap.put("IN", "India");
//		    countryMap.put("JP", "Japan");
//		    countryMap.put("US", "United States");
//		    countryMap.put("SG", "Singapore");
		System.out.println("Location master  called");

		modelview.addObject("locationMaster", new LocationMasterBean());
		modelview.addObject("companyOptions", service.getCompanyMap());
		modelview.setViewName("locationMaster");
		return modelview;
	}

	@RequestMapping(value = "/addlocationMaster", method = RequestMethod.POST)
//  public String submitForm( @Valid @ModelAttribute("emp") Employee e, BindingResult br)  
//  {  
	public void addlocationMaster(@RequestParam("companyName") String companyName, @RequestParam("location") String location,
			 HttpServletResponse res) {
		try {
			PrintWriter out = res.getWriter();
			System.out.println("Controller Called location Master");
			String result = service.addlocationMaster(companyName, location);
			System.out.println("Status:" + result);
			out.print(result);
		} catch (Exception e) {
			System.out.println("Exception:" + e);
		}

	}
	
	@RequestMapping(value = "/viewLocationMaster", method = RequestMethod.GET)
	public ModelAndView viewLocationMaster() {
		ModelAndView m = new ModelAndView();
		m.addObject("viewLocationMaster", service.viewLocationMaster());
		m.setViewName("viewLocationMaster");
		return m;
	}
	

	@RequestMapping(value = "/deleteLocationMaster", method = RequestMethod.POST)
	// public String delete(@PathVariable long ID) {
	public void deleteLocationMaster(@RequestParam("id") long ID, HttpServletResponse res) {

		try {
			PrintWriter out = res.getWriter();
			System.out.println("Delete controller called:" + ID);
			String result = service.deleteLocationMaster(ID);
			System.out.println("delete Status:" + result);

			out.print(result);
		} catch (Exception e) {
			System.out.println("Exception:" + e);
		}

	}
	
	@RequestMapping(value = "/editLocationMaster/{ID}")
	public ModelAndView editLocationMaster(@PathVariable long ID, ModelAndView m) {

		System.out.println("Location Master edit controller called");
		// public String editUser(@RequestParam("id") long ID, Model m) {
		LocationMasterBean emp = service.editLocationMaster(ID);
		m.addObject("locationMasterId", ID);
		m.addObject("locationMaster", emp);
		m.addObject("companyOptions", service.getCompanyMap());
		m.setViewName("locationMaster");
		return m;
	}
	
	@RequestMapping(value = "/updateLocationMaster")
	public void updateCompanyMaster(@RequestParam("companyName") String companyName, @RequestParam("location") String location,
			 @RequestParam("id") long id,
			 HttpServletResponse res) {

		try {
			PrintWriter out = res.getWriter();

			System.out.println("Controller Called updateCompanyMaster");
			String result = service.updateLocationMaster(companyName, location, id);
			System.out.println("update Status:" + result);

			out.print(result);
		} catch (Exception e) {
			System.out.println("Exception:" + e);
		}
	}
	
	
	// NW IP SCAN
	@RequestMapping("/nwIpScanForm")
	public String nwIpScanForm(HttpServletRequest req, HttpServletResponse res, Model m) {
		System.out.println("Controller Called nwIpScanForm");

		m.addAttribute("nwIpScan", new NwIpScanBean());
		m.addAttribute("group", service.getGroupMap());

		return "nwIpScan";
	}
	
	@RequestMapping(value = "/addIpScan", method = RequestMethod.POST)
//  public String submitForm( @Valid @ModelAttribute("emp") Employee e, BindingResult br)  
//  {  
	public void addIpScan(@RequestParam("groupName") String groupName,@RequestParam("ipScan") String ipScan, @RequestParam("endIpScan") String endIpScan,
			HttpServletResponse res, HttpServletRequest req) {
		try {
			PrintWriter out = res.getWriter();
			System.out.println("Controller Called IPSCAN Master");
//			String ipScan = req.getParameter("ipScan");
//			String endIpScan = req.getParameter("endIpScan");
			String result = service.addIpScan(groupName, ipScan, endIpScan);
			System.out.println("ipscan  = " +ipScan);
			System.out.println("endIP = "+endIpScan);
			System.out.println("Status:" + result);
			out.print(result);
		} catch (Exception e) {
			System.out.println("Exception:" + e);
		}

	}
	
	
	@RequestMapping(value = "/viewIpScan", method = RequestMethod.GET)
	public ModelAndView viewIpScan() {
		ModelAndView m = new ModelAndView();
		m.addObject("viewIpScan", service.viewIpScan());
		m.setViewName("viewNwIpScan");
		return m;
	}
	

	@RequestMapping(value = "/deleteIpScan", method = RequestMethod.POST)
	// public String delete(@PathVariable long ID) {
	public void deleteIpScan(@RequestParam("id") long ID, HttpServletResponse res) {

		try {
			PrintWriter out = res.getWriter();
			System.out.println("Delete controller called:" + ID);
			String result = service.deleteIpScan(ID);
			System.out.println("delete Status:" + result);

			out.print(result);
		} catch (Exception e) {
			System.out.println("Exception:" + e);
		}

	}
	
	@RequestMapping(value = "/editIpScan/{ID}")
	public ModelAndView editIpScan(@PathVariable long ID, ModelAndView m) {

		System.out.println("editIpScan Master edit controller called");
		// public String editUser(@RequestParam("id") long ID, Model m) {
		NwIpScanBean emp = service.editIpScan(ID);
		m.addObject("addIpScanId", ID);
		m.addObject("nwIpScan", emp);
		m.addObject("group", service.getGroupMap());
		m.setViewName("nwIpScan");
		return m;
	}
	
	@RequestMapping(value = "/updateIpScan")
	public void updateIpScan(@RequestParam("groupName") String groupName, @RequestParam("ipScan") String ipScan,
			@RequestParam("endIpScan") String endIpScan,
			 @RequestParam("id") long id,
			 HttpServletResponse res) {

		try {
			PrintWriter out = res.getWriter();

			System.out.println("Controller Called updateIpScan");
			String result = service.updateIpScan(groupName, ipScan,  endIpScan, id);
			System.out.println("update Status:" + result);

			out.print(result);
		} catch (Exception e) {
			System.out.println("Exception:" + e);
		}
	}
	
	
	//Schedule Scan
	@RequestMapping("/ScheduleScan")
	public String ScheduleScanForm()
	{
		return "ScheduleScan";
	}
//	@RequestMapping(value = { "/UpdateCompany/{name}" }, method = RequestMethod.GET)
//	public ModelAndView UpdateCompany(HttpServletRequest req, HttpServletResponse res, @PathVariable String name) {
//		ModelAndView m = new ModelAndView("UpdateCompanyMaster");
//
//		m.addObject("company", service.CompanyList(name));
//
//		return m;
//
//	}

/* Add Group Master Form */

	@RequestMapping("/addGroupForm")
	public String addGroupForm(HttpServletRequest req, HttpServletResponse res, Model m) {
		System.out.println("Controller Called addGroupForm");

		m.addAttribute("fetchGroupInfo", new GroupMasterBean());

		return "addGroup";
	}

	/* Add Group Master Data */

	@RequestMapping(value = "/addGroup", method = RequestMethod.POST)
	public void addGroup(@RequestParam("groupName") String groupName, HttpServletResponse res) {
		try {
			PrintWriter out = res.getWriter();
			System.out.println("Controller Called addGroup");
			String result = service.addGroup(groupName);
			System.out.println("Status:" + result);
			out.print(result);
		} catch (Exception e) {
			System.out.println("Exception:" + e);
		}
	}

	/* Add Group Listing */
	@RequestMapping(value = "/groupListing", method = RequestMethod.GET)
	public JSONArray groupListing(ModelMap model, HttpServletRequest request, HttpServletResponse response,
			HttpSession session) {

		PrintWriter out = null;
		try {
			out = response.getWriter();
			JSONArray jsonresponse = service.groupListing();
			out.print(jsonresponse);

		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		} finally {
			out.close();
		}

		return null;
	}

	/* Delete Group */

	@RequestMapping(value = "/deleteGroup", method = RequestMethod.POST)
	public void deleteGroup(@RequestParam("id") long ID, HttpServletResponse res) {

		try {
			PrintWriter out = res.getWriter();
			System.out.println("Delete controller calleda:" + ID);
			String result = service.deleteGroup(ID);
			System.out.println("delete Status:" + result);

			out.print(result);
		} catch (Exception e) {
			System.out.println("Exception:" + e);
		}

	}

	/* Add Service Provider Master Form */

	@RequestMapping("/addServiceProviderForm")
	public String addServiceProviderForm(HttpServletRequest req, HttpServletResponse res, Model m) {
		System.out.println("Controller Called addServiceProviderForm");

		m.addAttribute("fetchSPInfo", new ServiceProviderMasterBean());

		return "addServiceProvider";
	}

	/* Add Service Provider Master Data */

	@RequestMapping(value = "/addServiceProvider", method = RequestMethod.POST)
	public void addServiceProvider(@RequestParam("serviceProviderName") String serviceProviderName,
			HttpServletResponse res) {
		try {
			PrintWriter out = res.getWriter();
			System.out.println("Controller Called addServiceProvider");
			String result = service.addServiceProvider(serviceProviderName);
			System.out.println("Status:" + result);
			out.print(result);
		} catch (Exception e) {
			System.out.println("Exception:" + e);
		}
	}

	/* Add Service Provider Listing */
	@RequestMapping(value = "/serviceProviderListing", method = RequestMethod.GET)
	public JSONArray serviceProviderListing(ModelMap model, HttpServletRequest request, HttpServletResponse response,
			HttpSession session) {

		PrintWriter out = null;
		try {
			out = response.getWriter();
			JSONArray jsonresponse = service.serviceProviderListing();
			out.print(jsonresponse);

		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		} finally {
			out.close();
		}

		return null;
	}

	/* Delete Service Provider */

	@RequestMapping(value = "/deleteServiceProvider", method = RequestMethod.POST)
	public void deleteServiceProvider(@RequestParam("id") long ID, HttpServletResponse res) {

		try {
			PrintWriter out = res.getWriter();
			System.out.println("Delete controller calleda:" + ID);
			String result = service.deleteServiceProvider(ID);
			System.out.println("delete Status:" + result);

			out.print(result);
		} catch (Exception e) {
			System.out.println("Exception:" + e);
		}

	}

	/* Add Make & Model Master Form */

	@RequestMapping("/addMakeModelForm")
	public String addMakeModelForm(HttpServletRequest req, HttpServletResponse res, Model m) {
		System.out.println("Controller Called addMakeModelForm");

		m.addAttribute("fetchMakeModelInfo", new MakeModelMasterBean());

		return "addMakeModel";
	}

	/* Add Make and Model Master Data */

	@RequestMapping(value = "/addMakeModel", method = RequestMethod.POST)
	public void addMakeModel(@RequestParam("makeModelName") String makeModelName, HttpServletResponse res) {
		try {
			PrintWriter out = res.getWriter();
			System.out.println("Controller Called addMakeModel");
			String result = service.addMakeModel(makeModelName);
			System.out.println("Status:" + result);
			out.print(result);
		} catch (Exception e) {
			System.out.println("Exception:" + e);
		}
	}

	/* Add Make & Model Listing */
	@RequestMapping(value = "/makeModelListing", method = RequestMethod.GET)
	public JSONArray makeModelListing(ModelMap model, HttpServletRequest request, HttpServletResponse response,
			HttpSession session) {

		PrintWriter out = null;
		try {
			out = response.getWriter();
			JSONArray jsonresponse = service.makeModelListing();
			out.print(jsonresponse);

		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		} finally {
			out.close();
		}

		return null;
	}

	/* Delete Make & Model Provider */

	@RequestMapping(value = "/deleteMakeModel", method = RequestMethod.POST)
	public void deleteMakeModel(@RequestParam("id") long ID, HttpServletResponse res) {

		try {
			PrintWriter out = res.getWriter();
			System.out.println("Delete controller calleda:" + ID);
			String result = service.deleteMakeModel(ID);
			System.out.println("delete Status:" + result);

			out.print(result);
		} catch (Exception e) {
			System.out.println("Exception:" + e);
		}

	}

	/* Add Device Type Form */

	@RequestMapping("/addDeviceTypeForm")
	public String addDeviceTypeForm(HttpServletRequest req, HttpServletResponse res, Model m) {
		System.out.println("Controller Called addDeviceTypeForm");

		m.addAttribute("fetchDeviceTypeInfo", new DeviceTypeMasterBean());

		return "addDeviceType";
	}

	/* Add Device Type Master Data */

	@RequestMapping(value = "/addDeviceType", method = RequestMethod.POST)
	public void addDeviceType(@RequestParam("deviceTypeName") String deviceTypeName, HttpServletResponse res) {
		try {
			PrintWriter out = res.getWriter();
			System.out.println("Controller Called addDeviceType");
			String result = service.addDeviceType(deviceTypeName);
			System.out.println("Status:" + result);
			out.print(result);
		} catch (Exception e) {
			System.out.println("Exception:" + e);
		}
	}

	/* Add Device Type Listing */
	@RequestMapping(value = "/deviceTypeListing", method = RequestMethod.GET)
	public JSONArray deviceTypeListing(ModelMap model, HttpServletRequest request, HttpServletResponse response,
			HttpSession session) {

		PrintWriter out = null;
		try {
			out = response.getWriter();
			JSONArray jsonresponse = service.deviceTypeListing();
			out.print(jsonresponse);

		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		} finally {
			out.close();
		}

		return null;
	}

	/* Delete Device Type Provider */

	@RequestMapping(value = "/deleteDeviceType", method = RequestMethod.POST)
	public void deleteDeviceType(@RequestParam("id") long ID, HttpServletResponse res) {

		try {
			PrintWriter out = res.getWriter();
			System.out.println("Delete controller calleda:" + ID);
			String result = service.deleteDeviceType(ID);
			System.out.println("delete Status:" + result);

			out.print(result);
		} catch (Exception e) {
			System.out.println("Exception:" + e);
		}

	}

	/* Add AD Configuration Form */

	@RequestMapping("/addADConfigForm")
	public String addADConfigForm(HttpServletRequest req, HttpServletResponse res, Model m) {
		System.out.println("Controller Called addADConfigForm");

		m.addAttribute("fetchADConfigInfo", new ADConfigMasterBean());

		return "addADConfig";
	}

	/* Add AD Configuration */

	@RequestMapping(value = "/addADConfig", method = RequestMethod.POST)
	public void addADConfig(@RequestParam("ldapServerName") String ldapServerName,
			@RequestParam("username") String username, @RequestParam("logonDomain") String logonDomain,
			@RequestParam("password") String password, @RequestParam("serverPort") long serverPort,
			HttpServletResponse res) {
		try {
			PrintWriter out = res.getWriter();
			System.out.println("Controller Called addADConfig");
			String result = service.addADConfig(ldapServerName, username, logonDomain, password, serverPort);
			System.out.println("Status:" + result);
			out.print(result);
		} catch (Exception e) {
			System.out.println("Exception:" + e);
		}

	}

	/* Add AD Configuration Listing */
	@RequestMapping(value = "/adConfigListing", method = RequestMethod.GET)
	public JSONArray adConfigListing(ModelMap model, HttpServletRequest request, HttpServletResponse response,
			HttpSession session) {

		PrintWriter out = null;
		try {
			out = response.getWriter();
			JSONArray jsonresponse = service.adConfigListing();
			out.print(jsonresponse);

		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		} finally {
			out.close();
		}

		return null;
	}

	/* Delete AD Configuration */

	@RequestMapping(value = "/deleteADConfig", method = RequestMethod.POST)
	public void deleteADConfig(@RequestParam("id") long ID, HttpServletResponse res) {

		try {
			PrintWriter out = res.getWriter();
			System.out.println("Delete controller calleda:" + ID);
			String result = service.deleteADConfig(ID);
			System.out.println("delete Status:" + result);

			out.print(result);
		} catch (Exception e) {
			System.out.println("Exception:" + e);
		}

	}

	/* Edit AD Configuration */

	@RequestMapping(value = "/editADConfig/{ID}")
	public ModelAndView editADConfig(@PathVariable long ID, ModelAndView m) {

		System.out.println("Edit controller called");
		ADConfigMasterBean adconfig = service.editADConfig(ID);
		System.out.println("response:" + adconfig.getId());
		m.addObject("adConfigId", ID);
		m.addObject("fetchADConfigInfo", adconfig);
		m.setViewName("addADConfig");
		return m;
	}

	/* Update AD Configuration */

	@RequestMapping(value = "/updateADConfig", method = RequestMethod.POST)
	public void updateADConfig(@RequestParam("ldapServerName") String ldapServerName,
			@RequestParam("username") String username, @RequestParam("logonDomain") String logonDomain,
			@RequestParam("password") String password, @RequestParam("serverPort") long serverPort,
			@RequestParam("id") long id, HttpServletResponse res) {
		try {
			PrintWriter out = res.getWriter();
			System.out.println("Controller Called updateADConfig");
			String result = service.updateADConfig(ldapServerName, username, logonDomain, password, serverPort, id);
			System.out.println("Status:" + result);
			out.print(result);
		} catch (Exception e) {
			System.out.println("Exception:" + e);
		}

	}

	/* Add Email Configuration Form */

	@RequestMapping("/addEmailConfigForm")
	public String addEmailConfigForm(HttpServletRequest req, HttpServletResponse res, Model m) {
		System.out.println("Controller Called addEmailConfigForm");

		m.addAttribute("fetchEmailConfigInfo", new EmailConfigMasterBean());

		return "addEmailConfig";
	}

	/* Add Email Configuration */

	@RequestMapping(value = "/addEmailConfig", method = RequestMethod.POST)
	public void addEmailConfig(@RequestParam("emailId") String emailId, @RequestParam("port") long port,
			@RequestParam("smtpServer") String smtpServer, @RequestParam("timeout") long timeout,
			@RequestParam("isSslTls") String isSslTls, @RequestParam("username") String username,
			@RequestParam("isSmtpAuth") String isSmtpAuth, @RequestParam("password") String password,
			HttpServletResponse res) {
		try {
			PrintWriter out = res.getWriter();
			System.out.println("Controller Called addEmailConfig" + isSmtpAuth + "//" + isSslTls);
			String result = service.addEmailConfig(emailId, port, smtpServer, timeout, isSslTls, username, isSmtpAuth,
					password);
			System.out.println("Status:" + result);
			out.print(result);
		} catch (Exception e) {
			System.out.println("Exception:" + e);
		}

	}

	/* Add Email Configuration Listing */
	@RequestMapping(value = "/emailConfigListing", method = RequestMethod.GET)
	public JSONArray emailConfigListing(ModelMap model, HttpServletRequest request, HttpServletResponse response,
			HttpSession session) {

		PrintWriter out = null;
		try {
			out = response.getWriter();
			JSONArray jsonresponse = service.emailConfigListing();
			out.print(jsonresponse);

		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		} finally {
			out.close();
		}

		return null;
	}

	/* Delete Email Configuration */

	@RequestMapping(value = "/deleteEmailConfig", method = RequestMethod.POST)
	public void deleteEmailConfig(@RequestParam("id") long ID, HttpServletResponse res) {

		try {
			PrintWriter out = res.getWriter();
			System.out.println("Delete controller calleda:" + ID);
			String result = service.deleteEmailConfig(ID);
			System.out.println("delete Status:" + result);

			out.print(result);
		} catch (Exception e) {
			System.out.println("Exception:" + e);
		}

	}

	/* Edit Email Configuration */

	@RequestMapping(value = "/editEmailConfig/{ID}")
	public ModelAndView editEmailConfig(@PathVariable long ID, ModelAndView m) {

		System.out.println("Edit controller called");
		EmailConfigMasterBean emailconfig = service.editEmailConfig(ID);
		System.out.println("response:" + emailconfig.getId());
		m.addObject("emailConfigId", ID);
		m.addObject("emailsslcheckbox", emailconfig.getIsSslTls());
		m.addObject("emailsmtpcheckbox", emailconfig.getIsSmtpAuth());
		m.addObject("fetchEmailConfigInfo", emailconfig);
		m.setViewName("addEmailConfig");
		return m;
	}

	/* Add SNMP Configuration Form */

	@RequestMapping("/addSNMPConfigForm")
	public String addSNMPConfigForm(HttpServletRequest req, HttpServletResponse res, Model m) {
		System.out.println("Controller Called addSNMPConfigForm");

		m.addAttribute("fetchSNMPConfigInfo", new SNMPConfigMasterBean());

		return "addSnmpConfig";
	}

	/* Add SNMP Configuration */

	@RequestMapping(value = "/addSNMPConfig", method = RequestMethod.POST)
	public void addSNMPConfig(@RequestParam("name") String name, @RequestParam("description") String description,
			@RequestParam("version") String version, @RequestParam("community") String community,
			@RequestParam("username") String username, @RequestParam("authProtocol") String authProtocol,
			@RequestParam("context") String context, @RequestParam("authPassword") String authPassword,
			@RequestParam("encryptProtocol") String encryptProtocol, @RequestParam("port") long port,
			@RequestParam("encryptPassword") String encryptPassword, @RequestParam("timeout") long timeout,
			@RequestParam("retries") long retries, HttpServletResponse res) {
		try {
			PrintWriter out = res.getWriter();
			System.out.println("Controller Called addSNMPConfig");
			String result = service.addSNMPConfig(name, description, version, community, username, authProtocol,
					context, authPassword, encryptProtocol, port, encryptPassword, timeout, retries);
			System.out.println("Status:" + result);
			out.print(result);
		} catch (Exception e) {
			System.out.println("Exception:" + e);
		}

	}
	
	/* Add Snmp Configuration Listing */
	@RequestMapping(value = "/snmpConfigListing", method = RequestMethod.GET)
	public JSONArray snmpConfigListing(ModelMap model, HttpServletRequest request, HttpServletResponse response,
			HttpSession session) {

		PrintWriter out = null;
		try {
			out = response.getWriter();
			JSONArray jsonresponse = service.snmpConfigListing();
			out.print(jsonresponse);

		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		} finally {
			out.close();
		}

		return null;
	}

	/* Delete SNMP Configuration */

	@RequestMapping(value = "/deleteSNMPConfig", method = RequestMethod.POST)
	public void deleteSNMPConfig(@RequestParam("id") long ID, HttpServletResponse res) {

		try {
			PrintWriter out = res.getWriter();
			System.out.println("Delete controller calleda:" + ID);
			String result = service.deleteSNMPConfig(ID);
			System.out.println("delete Status:" + result);

			out.print(result);
		} catch (Exception e) {
			System.out.println("Exception:" + e);
		}

	}
	
	@RequestMapping(value = "/discoveredNetwork", method = RequestMethod.GET)
	public ModelAndView viewAddNode() {
		ModelAndView m = new ModelAndView();
		m.addObject("discoveredNetwork", service.discoveredNetwork());
		m.setViewName("viewDiscoveredNetwork");
		return m;
	}
}
