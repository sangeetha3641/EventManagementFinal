package EventManagementDto;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class ClientService {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int Clientserviceid;
	private String clientServicename;
	private double ClientServiceCost;
	private int ClientServicenoofdays;
	private double ClientServiceDuration;
	private double ClientServicecostperperson;
	public double getClientServicecostperperson() {
		return ClientServicecostperperson;
	}
	public void setClientServicecostperperson(double clientServicecostperperson) {
		ClientServicecostperperson = clientServicecostperperson;
	}
	public int getClientserviceid() {
		return Clientserviceid;
	}
	public void setClientserviceid(int clientserviceid) {
		Clientserviceid = clientserviceid;
		System.out.println(clientserviceid);
	}

	public String getClientServicename() {
		return clientServicename;
	}
	public void setClientServicename(String clientServicename) {
		this.clientServicename = clientServicename;
	}
	public double getClientServiceCost() {
		return ClientServiceCost;
	}
	public void setClientServiceCost(double clientServiceCost) {
		ClientServiceCost = clientServiceCost;
	}
	public int getClientServicenoofdays() {
		return ClientServicenoofdays;
	}
	public void setClientServicenoofdays(int clientServicenoofdays) {
		ClientServicenoofdays = clientServicenoofdays;
	}
	public double getClientServiceDuration() {
		return ClientServiceDuration;
	}
	public void setClientServiceDuration(double clientServiceDuration) {
		ClientServiceDuration = clientServiceDuration;
	}
	@Override
	public String toString() {
		return "ClientService [Clientserviceid=" + Clientserviceid + ", clientServicename=" + clientServicename
				+ ", ClientServiceCost=" + ClientServiceCost + ", ClientServicenoofdays=" + ClientServicenoofdays
				+ ", ClientServiceDuration=" + ClientServiceDuration + ", ClientServicecostperperson="
				+ ClientServicecostperperson + "]";
	}
	
}
