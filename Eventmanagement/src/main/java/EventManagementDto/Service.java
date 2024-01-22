package EventManagementDto;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Service {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int Serviceid;
	private String Servicename;
	private double ServiceCostperday;
	private double ServiceCostperperson;
	private int Servicecostnoofdays;
	public int getServicecostnoofdays() {
		return Servicecostnoofdays;
	}
	public void setServicecostnoofdays(int servicecostnoofdays) {
		Servicecostnoofdays = servicecostnoofdays;
	}
	public int getServiceid() {
		return Serviceid;
	}
	public void setServiceid(int serviceid) {
		Serviceid = serviceid;
	}
	public String getServicename() {
		return Servicename;
	}
	public void setServicename(String servicename) {
		Servicename = servicename;
	}
	public double getServiceCostperday() {
		return ServiceCostperday;
	}
	public void setServiceCostperday(double serviceCostperday) {
		ServiceCostperday = serviceCostperday;
	}
	public double getServiceCostperperson() {
		return ServiceCostperperson;
	}
	public void setServiceCostperperson(double serviceCostperperson) {
		ServiceCostperperson = serviceCostperperson;
	}
	@Override
	public String toString() {
		return "Service [Serviceid=" + Serviceid + ", Servicename=" + Servicename + ", ServiceCostperday="
				+ ServiceCostperday + ", ServiceCostperperson=" + ServiceCostperperson + ", Servicecostnoofdays="
				+ Servicecostnoofdays + "]";
	}
	
}

	