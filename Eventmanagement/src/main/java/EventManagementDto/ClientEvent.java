package EventManagementDto;

import java.time.LocalDate;
import java.util.List;
import java.util.Locale;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
@Entity

public class ClientEvent {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int ClientEventid;
	private int ClientEventnoofpeople;
	private LocalDate StartDate;
	private double clientEventcost;
	private int ClientEventnoofdays;
	private String ClientEventLocation;
	@ManyToOne(cascade=CascadeType.ALL)
	private Client Client;
	@OneToMany(cascade=CascadeType.ALL)
	private List<ClientService>ClientService;
	private EventType eventtype;
	
	public int getClientEventid() {
		return ClientEventid;
	}
	public void setClientEventid(int clientEventid) {
		ClientEventid = clientEventid;
	}
	public int getClientEventnoofpeople() {
		return ClientEventnoofpeople;
	}
	public void setClientEventnoofpeople(int clientEventnoofpeople) {
		ClientEventnoofpeople = clientEventnoofpeople;
	}
	public LocalDate getStartDate() {
		return StartDate;
	}
	public void setStartDate(LocalDate locale) {
		StartDate = locale;
	}
	public double getClientEventcost() {
		return clientEventcost;
	}
	public void setClientEventcost(double clientEventcost) {
		this.clientEventcost = clientEventcost;
	}
	public int getClientEventnoofdays() {
		return ClientEventnoofdays;
	}
	public void setClientEventnoofdays(int clientEventnoofdays) {
		ClientEventnoofdays = clientEventnoofdays;
	}
	public String getClientEventLocation() {
		return ClientEventLocation;
	}
	public void setClientEventLocation(String clientEventLocation) {
		ClientEventLocation = clientEventLocation;
	}
	public Client getClient() {
		return Client;
	}
	public void setClient(Client client) {
		Client = client;
	}
	public List<ClientService> getClientService() {
		return ClientService;
	}
	public void setClientService(List<ClientService> clientService) {
		ClientService = clientService;
	}
	public EventType getEventtype() {
		return eventtype;
	}
	public void setEventtype(EventType eventtype) {
		this.eventtype = eventtype;
	}
	@Override
	public String toString() {
		return "ClientEvent [ClientEventid=" + ClientEventid + ", ClientEventnoofpeople=" + ClientEventnoofpeople
				+ ", StartDate=" + StartDate + ", clientEventcost=" + clientEventcost + ", ClientEventnoofdays="
				+ ClientEventnoofdays + ", ClientEventLocation=" + ClientEventLocation + ", ClientService="
				+ ClientService + ", eventtype=" + eventtype + "]";
	}
}
	
	
	
	