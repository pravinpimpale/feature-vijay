package npm.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="NODE_PARAMETER" )
public class NodeParameterModel implements Serializable{
	
	private static final long serialVersionUID = -2264642949863409860L;
	
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID", updatable = false, nullable = false)
    private Long ID;
	
	@Column
	private String DEVICE_IP;
	
	@Column
	private int MEMORY_THRESHOLD;
	
	@Column
	private int LATENCY_THRESHOLD;
	
	@Column
	private int CPU_THRESHOLD;
	
	@Column
	private String MONITORING;
	
	@Column
	private String MEMORY_HISTORY;
	
	@Column
	private String LATENCY_HISTORY;
	
	@Column
	private String CPU_HISTORY;
	
	@Column
	private String SNMP_DISCOVERY_PARAM;
	
	

	public Long getID() {
		return ID;
	}

	public void setID(Long iD) {
		ID = iD;
	}


	public String getDEVICE_IP() {
		return DEVICE_IP;
	}

	public void setDEVICE_IP(String dEVICE_IP) {
		DEVICE_IP = dEVICE_IP;
	}

	public int getMEMORY_THRESHOLD() {
		return MEMORY_THRESHOLD;
	}

	public void setMEMORY_THRESHOLD(int mEMORY_THRESHOLD) {
		MEMORY_THRESHOLD = mEMORY_THRESHOLD;
	}

	public int getLATENCY_THRESHOLD() {
		return LATENCY_THRESHOLD;
	}

	public void setLATENCY_THRESHOLD(int lATENCY_THRESHOLD) {
		LATENCY_THRESHOLD = lATENCY_THRESHOLD;
	}

	public int getCPU_THRESHOLD() {
		return CPU_THRESHOLD;
	}

	public void setCPU_THRESHOLD(int cPU_THRESHOLD) {
		CPU_THRESHOLD = cPU_THRESHOLD;
	}

	public String getMONITORING() {
		return MONITORING;
	}

	public void setMONITORING(String mONITORING) {
		MONITORING = mONITORING;
	}

	public String getMEMORY_HISTORY() {
		return MEMORY_HISTORY;
	}

	public void setMEMORY_HISTORY(String mEMORY_HISTORY) {
		MEMORY_HISTORY = mEMORY_HISTORY;
	}

	public String getLATENCY_HISTORY() {
		return LATENCY_HISTORY;
	}

	public void setLATENCY_HISTORY(String lATENCY_HISTORY) {
		LATENCY_HISTORY = lATENCY_HISTORY;
	}

	public String getCPU_HISTORY() {
		return CPU_HISTORY;
	}

	public void setCPU_HISTORY(String cPU_HISTORY) {
		CPU_HISTORY = cPU_HISTORY;
	}
	
	

	public String getSNMP_DISCOVERY_PARAM() {
		return SNMP_DISCOVERY_PARAM;
	}

	public void setSNMP_DISCOVERY_PARAM(String sNMP_DISCOVERY_PARAM) {
		SNMP_DISCOVERY_PARAM = sNMP_DISCOVERY_PARAM;
	}

	@Override
	public String toString() {
		return "NodeParameterModel [ID=" + ID + ", DEVICE_IP=" + DEVICE_IP + ", MEMORY_THRESHOLD=" + MEMORY_THRESHOLD
				+ ", LATENCY_THRESHOLD=" + LATENCY_THRESHOLD + ", CPU_THRESHOLD=" + CPU_THRESHOLD + ", MONITORING="
				+ MONITORING + ", MEMORY_HISTORY=" + MEMORY_HISTORY + ", LATENCY_HISTORY=" + LATENCY_HISTORY
				+ ", CPU_HISTORY=" + CPU_HISTORY + "]";
	}
	
	
	


}
