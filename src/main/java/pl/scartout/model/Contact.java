package pl.scartout.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import javax.xml.bind.annotation.XmlRootElement;

	@XmlRootElement
	@Entity
	@Table(name = "contacts")
	public final class Contact implements Serializable {
	    private static final long serialVersionUID = 1L;
	    
	    @Id
	    @GeneratedValue(strategy = GenerationType.IDENTITY)
	    @Column(name = "contact_id")
	    private Long id;
	    @NotNull
	    @Column(name = "phone_number_first")
	    private String phoneNumberFirst;
	    @Column(name = "phone_number_second")
	    private String phoneNumberSecond;
	    private String fax;
	    
	    public Contact(){}

		public Contact(String phoneNumberFirst, String phoneNumberSecond, String fax) {
			super();
			this.phoneNumberFirst = phoneNumberFirst;
			this.phoneNumberSecond = phoneNumberSecond;
			this.fax = fax;
		}

		public Long getId() {
			return id;
		}

		public void setId(Long id) {
			this.id = id;
		}

		public String getPhoneNumberFirst() {
			return phoneNumberFirst;
		}

		public void setPhoneNumberFirst(String phoneNumberFirst) {
			this.phoneNumberFirst = phoneNumberFirst;
		}

		public String getPhoneNumberSecond() {
			return phoneNumberSecond;
		}

		public void setPhoneNumberSecond(String phoneNumberSecond) {
			this.phoneNumberSecond = phoneNumberSecond;
		}

		public String getFax() {
			return fax;
		}

		public void setFax(String fax) {
			this.fax = fax;
		}

		@Override
		public String toString() {
			return "Contact [id=" + id + ", phoneNumberFirst=" + phoneNumberFirst + ", phoneNumberSecond="
					+ phoneNumberSecond + ", fax=" + fax + "]";
		}

		@Override
		public int hashCode() {
			final int prime = 31;
			int result = 1;
			result = prime * result + ((fax == null) ? 0 : fax.hashCode());
			result = prime * result + ((id == null) ? 0 : id.hashCode());
			result = prime * result + ((phoneNumberFirst == null) ? 0 : phoneNumberFirst.hashCode());
			result = prime * result + ((phoneNumberSecond == null) ? 0 : phoneNumberSecond.hashCode());
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
			Contact other = (Contact) obj;
			if (fax == null) {
				if (other.fax != null)
					return false;
			} else if (!fax.equals(other.fax))
				return false;
			if (id == null) {
				if (other.id != null)
					return false;
			} else if (!id.equals(other.id))
				return false;
			if (phoneNumberFirst == null) {
				if (other.phoneNumberFirst != null)
					return false;
			} else if (!phoneNumberFirst.equals(other.phoneNumberFirst))
				return false;
			if (phoneNumberSecond == null) {
				if (other.phoneNumberSecond != null)
					return false;
			} else if (!phoneNumberSecond.equals(other.phoneNumberSecond))
				return false;
			return true;
		}

}
