package pl.scartout.model;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import javax.xml.bind.annotation.XmlRootElement;

	@XmlRootElement
	@Entity
	@Table(name = "employees")
	public class Employee implements Serializable {
	    private static final long serialVersionUID = 1L;
	    
	    @Id
	    @GeneratedValue(strategy = GenerationType.IDENTITY)
	    @Column(name = "employee_id")
	    private Long id;
	    @NotNull
	    @Column(name = "first_name")
	    private String firstName;
	    @NotNull
	    @Column(name = "last_name")
	    private String lastName;
	    @NotNull
	    private String position;
	    @Column(name = "hire_date")
	    private Date hireDate;
	    @Column(name = "layoff_date")
	    private Date layoffDate;
	    @OneToOne
	    @JoinColumn(name = "contact_id")
	    private Contact contact;
	    @OneToOne
	    @JoinColumn(name = "address_id")
	    private Address address;
	    @OneToOne
	    @JoinColumn(name = "user_id")
	    private User user;
	    
	    public Employee(){}

		public Employee(String firstName, String lastName, String position, Date hireDate, Date layoffDate,
				Contact contact, Address address, User user) {
			super();
			this.firstName = firstName;
			this.lastName = lastName;
			this.position = position;
			this.hireDate = hireDate;
			this.layoffDate = layoffDate;
			this.contact = contact;
			this.address = address;
			this.user = user;
		}

		@Override
		public String toString() {
			return "Employee [id=" + id + ", firstName=" + firstName + ", lastName=" + lastName + ", position="
					+ position + ", hireDate=" + hireDate + ", layoffDate=" + layoffDate + ", contact=" + contact
					+ ", address=" + address + ", user=" + user + "]";
		}

		@Override
		public int hashCode() {
			final int prime = 31;
			int result = 1;
			result = prime * result + ((address == null) ? 0 : address.hashCode());
			result = prime * result + ((contact == null) ? 0 : contact.hashCode());
			result = prime * result + ((firstName == null) ? 0 : firstName.hashCode());
			result = prime * result + ((hireDate == null) ? 0 : hireDate.hashCode());
			result = prime * result + ((id == null) ? 0 : id.hashCode());
			result = prime * result + ((lastName == null) ? 0 : lastName.hashCode());
			result = prime * result + ((layoffDate == null) ? 0 : layoffDate.hashCode());
			result = prime * result + ((position == null) ? 0 : position.hashCode());
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
			Employee other = (Employee) obj;
			if (address == null) {
				if (other.address != null)
					return false;
			} else if (!address.equals(other.address))
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
			if (hireDate == null) {
				if (other.hireDate != null)
					return false;
			} else if (!hireDate.equals(other.hireDate))
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
			if (layoffDate == null) {
				if (other.layoffDate != null)
					return false;
			} else if (!layoffDate.equals(other.layoffDate))
				return false;
			if (position == null) {
				if (other.position != null)
					return false;
			} else if (!position.equals(other.position))
				return false;
			if (user == null) {
				if (other.user != null)
					return false;
			} else if (!user.equals(other.user))
				return false;
			return true;
		}

}
