package npm.admin.beans;

public class NodeStatusReportBean {
	
	private long ID;
	private String NODE_IP;
	private String NODE_STATUS;
	private String EVENT_TIMESTAMP;
	private String DEVICE_NAME;
	private String DEVICE_TYPE;
	private String GROUP_NAME;
	private String SNMP;
	private String SERVICE_PROVIDER;
	private String COMPANY;
	private String STATE;
	private String ZONE;
	private String DISTRICT;
	private String LOCATION;
	public long getID() {
		return ID;
	}
	public void setID(long iD) {
		ID = iD;
	}
	public String getNODE_IP() {
		return NODE_IP;
	}
	public void setNODE_IP(String nODE_IP) {
		NODE_IP = nODE_IP;
	}
	public String getNODE_STATUS() {
		return NODE_STATUS;
	}
	public void setNODE_STATUS(String nODE_STATUS) {
		NODE_STATUS = nODE_STATUS;
	}
	public String getEVENT_TIMESTAMP() {
		return EVENT_TIMESTAMP;
	}
	public void setEVENT_TIMESTAMP(String eVENT_TIMESTAMP) {
		EVENT_TIMESTAMP = eVENT_TIMESTAMP;
	}
	public String getDEVICE_NAME() {
		return DEVICE_NAME;
	}
	public void setDEVICE_NAME(String dEVICE_NAME) {
		DEVICE_NAME = dEVICE_NAME;
	}
	public String getDEVICE_TYPE() {
		return DEVICE_TYPE;
	}
	public void setDEVICE_TYPE(String dEVICE_TYPE) {
		DEVICE_TYPE = dEVICE_TYPE;
	}
	public String getGROUP_NAME() {
		return GROUP_NAME;
	}
	public void setGROUP_NAME(String gROUP_NAME) {
		GROUP_NAME = gROUP_NAME;
	}
	public String getSNMP() {
		return SNMP;
	}
	public void setSNMP(String sNMP) {
		SNMP = sNMP;
	}
	public String getSERVICE_PROVIDER() {
		return SERVICE_PROVIDER;
	}
	public void setSERVICE_PROVIDER(String sERVICE_PROVIDER) {
		SERVICE_PROVIDER = sERVICE_PROVIDER;
	}
	public String getCOMPANY() {
		return COMPANY;
	}
	public void setCOMPANY(String cOMPANY) {
		COMPANY = cOMPANY;
	}
	public String getSTATE() {
		return STATE;
	}
	public void setSTATE(String sTATE) {
		STATE = sTATE;
	}
	public String getZONE() {
		return ZONE;
	}
	public void setZONE(String zONE) {
		ZONE = zONE;
	}
	public String getDISTRICT() {
		return DISTRICT;
	}
	public void setDISTRICT(String dISTRICT) {
		DISTRICT = dISTRICT;
	}
	public String getLOCATION() {
		return LOCATION;
	}
	public void setLOCATION(String lOCATION) {
		LOCATION = lOCATION;
	}
	@Override
	public String toString() {
		return "NodeStatusReportBean [ID=" + ID + ", NODE_IP=" + NODE_IP + ", NODE_STATUS=" + NODE_STATUS
				+ ", EVENT_TIMESTAMP=" + EVENT_TIMESTAMP + ", DEVICE_NAME=" + DEVICE_NAME + ", DEVICE_TYPE="
				+ DEVICE_TYPE + ", GROUP_NAME=" + GROUP_NAME + ", SNMP=" + SNMP + ", SERVICE_PROVIDER="
				+ SERVICE_PROVIDER + ", COMPANY=" + COMPANY + ", STATE=" + STATE + ", ZONE=" + ZONE + ", DISTRICT="
				+ DISTRICT + ", LOCATION=" + LOCATION + "]";
	}
	
	

}
