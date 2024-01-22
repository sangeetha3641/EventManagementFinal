package EventManagementDto;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
@Entity
public class Client {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int clientid;
	private String name;
	private long Contact;
	private String mail;
	private String password;
	@OneToMany(cascade=CascadeType.ALL)
	private List<ClientEvent>clientevent;
	@Override
	public String toString() {
		return "Client [clientid=" + clientid + ", name=" + name + ", Contact=" + Contact + ", mail=" + mail
				+ ", password=" + password + ", clientevent=" + clientevent + "]";
	}
	public int getClientid() {
		return clientid;
	}
	public void setClientid(int clientid) {
		this.clientid = clientid;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public long getContact() {
		return Contact;
	}
	public void setContact(long contact) {
		Contact = contact;
	}
	public String getMail() {
		return mail;
	}
	public void setMail(String mail) {
		this.mail = mail;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public List<ClientEvent> getClientevent() {
		return clientevent;
	}
	public void setClientevent(List<ClientEvent> clientevent) {
		this.clientevent = clientevent;
	}
	
}
