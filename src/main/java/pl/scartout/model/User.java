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
import javax.validation.constraints.Size;

import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;
import org.hibernate.validator.constraints.Email;

import javax.xml.bind.annotation.XmlRootElement;

	@XmlRootElement
	@Entity
	@Table(name = "users")
	public class User implements Serializable {
	    private static final long serialVersionUID = 1L;
	    
	    @Id
	    @GeneratedValue(strategy = GenerationType.IDENTITY)
	    @Column(name = "user_id")
	    private Long id;
	    @Size(min = 3)
	    private String username;
	    @Size(min = 6)
	    private String password;
	    @Email
	    @Column(nullable = false)
	    private String email;
	    @NotNull
	    @Column(name = "first_name")
	    private String firstName;
	    @NotNull
	    @Column(name = "last_name")
	    private String lastName;
	    private String companyName;
	    @OneToOne
	    @JoinColumn(name = "role_id")
	    private Role role;
	    @LazyCollection(LazyCollectionOption.FALSE)
	    @OneToMany(mappedBy = "user", 
	            cascade = { CascadeType.PERSIST, CascadeType.REMOVE },
	            orphanRemoval = true)
	    private List<Order> orders = new ArrayList<>();
	    @OneToOne
	    @JoinColumn(name = "contact_id")
	    private Contact contact;
	    @OneToOne
	    @JoinColumn(name = "address_id")
	    private Address address;
	    
	    public User(){}

		public User(Long id, String username, String password, String email, String firstName, String lastName,
				String companyName, Role role, Contact contact, List<Order> orders, Address address) {
			super();
			this.id = id;
			this.username = username;
			this.password = password;
			this.email = email;
			this.firstName = firstName;
			this.lastName = lastName;
			this.companyName = companyName;
			this.role = role;
			this.contact = contact;
			this.orders = orders;
			this.address = address;
		}

		public Long getId() {
			return id;
		}

		public void setId(Long id) {
			this.id = id;
		}

		public String getUsername() {
			return username;
		}

		public void setUsername(String username) {
			this.username = username;
		}

		public String getPassword() {
			return password;
		}

		public void setPassword(String password) {
			this.password = password;
		}

		public String getEmail() {
			return email;
		}

		public void setEmail(String email) {
			this.email = email;
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

		public String getCompanyName() {
			return companyName;
		}

		public void setCompanyName(String companyName) {
			this.companyName = companyName;
		}

		public Role getRole() {
			return role;
		}

		public void setRole(Role role) {
			this.role = role;
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

		@Override
		public String toString() {
			return "User [id=" + id + ", username=" + username + ", password=" + password + ", email=" + email
					+ ", firstName=" + firstName + ", lastName=" + lastName + ", companyName=" + companyName + ", role="
					+ role + ", contact=" + contact + ", orders=" + orders + ", address=" + address + "]";
		}

		@Override
		public int hashCode() {
			final int prime = 31;
			int result = 1;
			result = prime * result + ((address == null) ? 0 : address.hashCode());
			result = prime * result + ((companyName == null) ? 0 : companyName.hashCode());
			result = prime * result + ((contact == null) ? 0 : contact.hashCode());
			result = prime * result + ((email == null) ? 0 : email.hashCode());
			result = prime * result + ((firstName == null) ? 0 : firstName.hashCode());
			result = prime * result + ((id == null) ? 0 : id.hashCode());
			result = prime * result + ((lastName == null) ? 0 : lastName.hashCode());
			result = prime * result + ((orders == null) ? 0 : orders.hashCode());
			result = prime * result + ((password == null) ? 0 : password.hashCode());
			result = prime * result + ((role == null) ? 0 : role.hashCode());
			result = prime * result + ((username == null) ? 0 : username.hashCode());
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
			User other = (User) obj;
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
			if (email == null) {
				if (other.email != null)
					return false;
			} else if (!email.equals(other.email))
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
			if (orders == null) {
				if (other.orders != null)
					return false;
			} else if (!orders.equals(other.orders))
				return false;
			if (password == null) {
				if (other.password != null)
					return false;
			} else if (!password.equals(other.password))
				return false;
			if (role == null) {
				if (other.role != null)
					return false;
			} else if (!role.equals(other.role))
				return false;
			if (username == null) {
				if (other.username != null)
					return false;
			} else if (!username.equals(other.username))
				return false;
			return true;
		}
		
}
