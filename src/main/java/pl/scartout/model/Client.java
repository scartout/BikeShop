package pl.scartout.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import javax.xml.bind.annotation.XmlRootElement;

import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;

	@XmlRootElement
	@Entity
	@Table(name = "clients")
	public class Client implements Serializable {
	    private static final long serialVersionUID = 1L;
	    
	    @Id
	    @GeneratedValue(strategy = GenerationType.IDENTITY)
	    @Column(name = "client_id")
	    private Long id;
	    @NotNull
	    @Column(name = "first_name")
	    private String firstName;
	    @Column(name = "last_name")
	    private String lastName;
	    @NotNull
	    private String name;
	    @Column(name = "company_name")
	    private String companyName;
	    private String regon;
	    private String nip;
	    @OneToOne
	    @JoinColumn(name = "contact_id")
	    private Contact contact;
	    @LazyCollection(LazyCollectionOption.FALSE)
	    @OneToMany(mappedBy = "client", 
	            cascade = { CascadeType.PERSIST, CascadeType.REMOVE },
	            orphanRemoval = true)
	    private List<Order> orders = new ArrayList<>();
	    @OneToOne
	    @JoinColumn(name = "address_id")
	    private Address address;
	    @OneToOne
	    @JoinColumn(name = "user_id")
	    private User user;
	    @LazyCollection(LazyCollectionOption.FALSE)
	    @OneToMany(mappedBy = "client", 
	    		cascade = { CascadeType.PERSIST, CascadeType.REMOVE },
	    		orphanRemoval = true)
	    private List<Comment> comments = new ArrayList<>();
	    
	    public Client(){}

		public Client(String firstName, String lastName, String name, String companyName, String regon, String nip,
				Contact contact, List<Order> orders, Address address) {
			super();
			this.firstName = firstName;
			this.lastName = lastName;
			this.name = name;
			this.companyName = companyName;
			this.regon = regon;
			this.nip = nip;
		}

		public Long getId() {
			return id;
		}

		public void setId(Long id) {
			this.id = id;
		}

		public String getFirstName() {
			return firstName;
		}

		public void setFirstName(String firstName) {
			this.firstName = firstName;
		}

		public String getLastName() {
			return lastName;
		}

		public void setLastName(String lastName) {
			this.lastName = lastName;
		}

		public String getName() {
			return name;
		}

		public void setName(String name) {
			this.name = name;
		}

		public String getCompanyName() {
			return companyName;
		}

		public void setCompanyName(String companyName) {
			this.companyName = companyName;
		}

		public String getRegon() {
			return regon;
		}

		public void setRegon(String regon) {
			this.regon = regon;
		}

		public String getNip() {
			return nip;
		}

		public void setNip(String nip) {
			this.nip = nip;
		}

		public Contact getContact() {
			return contact;
		}

		public void setContact(Contact contact) {
			this.contact = contact;
		}

		public List<Order> getOrders() {
			return orders;
		}

		public void setOrders(List<Order> orders) {
			this.orders = orders;
		}

		public Address getAddress() {
			return address;
		}

		public void setAddress(Address address) {
			this.address = address;
		}

		public User getUser() {
			return user;
		}

		public void setUser(User user) {
			this.user = user;
		}

		@Override
		public String toString() {
			return "Client [id=" + id + ", firstName=" + firstName + ", lastName=" + lastName + ", name=" + name
					+ ", companyName=" + companyName + ", regon=" + regon + ", nip=" + nip + ", contact=" + contact
					+ ", orders=" + orders + ", address=" + address + ", user=" + user + "]";
		}

		@Override
		public int hashCode() {
			final int prime = 31;
			int result = 1;
			result = prime * result + ((address == null) ? 0 : address.hashCode());
			result = prime * result + ((companyName == null) ? 0 : companyName.hashCode());
			result = prime * result + ((contact == null) ? 0 : contact.hashCode());
			result = prime * result + ((firstName == null) ? 0 : firstName.hashCode());
			result = prime * result + ((id == null) ? 0 : id.hashCode());
			result = prime * result + ((lastName == null) ? 0 : lastName.hashCode());
			result = prime * result + ((name == null) ? 0 : name.hashCode());
			result = prime * result + ((nip == null) ? 0 : nip.hashCode());
			result = prime * result + ((orders == null) ? 0 : orders.hashCode());
			result = prime * result + ((regon == null) ? 0 : regon.hashCode());
			result = prime * result + ((user == null) ? 0 : user.hashCode());
			return result;
		}

		@Override
		public boolean equals(Object obj) {
			if (this == obj)
				return true;
			if (obj == null)
				return false;
			if (getClass() != obj.getClass())
				return false;
			Client other = (Client) obj;
			if (address == null) {
				if (other.address != null)
					return false;
			} else if (!address.equals(other.address))
				return false;
			if (companyName == null) {
				if (other.companyName != null)
					return false;
			} else if (!companyName.equals(other.companyName))
				return false;
			if (contact == null) {
				if (other.contact != null)
					return false;
			} else if (!contact.equals(other.contact))
				return false;
			if (firstName == null) {
				if (other.firstName != null)
					return false;
			} else if (!firstName.equals(other.firstName))
				return false;
			if (id == null) {
				if (other.id != null)
					return false;
			} else if (!id.equals(other.id))
				return false;
			if (lastName == null) {
				if (other.lastName != null)
					return false;
			} else if (!lastName.equals(other.lastName))
				return false;
			if (name == null) {
				if (other.name != null)
					return false;
			} else if (!name.equals(other.name))
				return false;
			if (nip == null) {
				if (other.nip != null)
					return false;
			} else if (!nip.equals(other.nip))
				return false;
			if (orders == null) {
				if (other.orders != null)
					return false;
			} else if (!orders.equals(other.orders))
				return false;
			if (regon == null) {
				if (other.regon != null)
					return false;
			} else if (!regon.equals(other.regon))
				return false;
			if (user == null) {
				if (other.user != null)
					return false;
			} else if (!user.equals(other.user))
				return false;
			return true;
		}

}
