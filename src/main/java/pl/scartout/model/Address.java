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
	@Table(name = "addresses")
	public class Address implements Serializable {
	    private static final long serialVersionUID = 1L;
	    
	    @Id
	    @GeneratedValue(strategy = GenerationType.IDENTITY)
	    @Column(name = "address_id")
	    private Long id;
	    @NotNull
	    private String city;
	    @NotNull
	    private String voivodeship;
	    @NotNull
	    private String county;
	    @NotNull
	    private String street;
	    @NotNull
	    private String postcode;
	    @NotNull
	    @Column(name = "street_number")
	    private String streetNumber;
	    @NotNull
	    @Column(name = "local_number")
	    private String localNumber;
	    
	    public Address(){}

		public Address(String city, String voivodeship, String county, String street, String postcode,
				String streetNumber, String localNumber) {
			super();
			this.city = city;
			this.voivodeship = voivodeship;
			this.county = county;
			this.street = street;
			this.postcode = postcode;
			this.streetNumber = streetNumber;
			this.localNumber = localNumber;
		}

		@Override
		public String toString() {
			return "Address [id=" + id + ", city=" + city + ", voivodeship=" + voivodeship + ", county=" + county
					+ ", street=" + street + ", postcode=" + postcode + ", streetNumber=" + streetNumber
					+ ", localNumber=" + localNumber + "]";
		}

		@Override
		public int hashCode() {
			final int prime = 31;
			int result = 1;
			result = prime * result + ((city == null) ? 0 : city.hashCode());
			result = prime * result + ((county == null) ? 0 : county.hashCode());
			result = prime * result + ((id == null) ? 0 : id.hashCode());
			result = prime * result + ((localNumber == null) ? 0 : localNumber.hashCode());
			result = prime * result + ((postcode == null) ? 0 : postcode.hashCode());
			result = prime * result + ((street == null) ? 0 : street.hashCode());
			result = prime * result + ((streetNumber == null) ? 0 : streetNumber.hashCode());
			result = prime * result + ((voivodeship == null) ? 0 : voivodeship.hashCode());
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
			Address other = (Address) obj;
			if (city == null) {
				if (other.city != null)
					return false;
			} else if (!city.equals(other.city))
				return false;
			if (county == null) {
				if (other.county != null)
					return false;
			} else if (!county.equals(other.county))
				return false;
			if (id == null) {
				if (other.id != null)
					return false;
			} else if (!id.equals(other.id))
				return false;
			if (localNumber == null) {
				if (other.localNumber != null)
					return false;
			} else if (!localNumber.equals(other.localNumber))
				return false;
			if (postcode == null) {
				if (other.postcode != null)
					return false;
			} else if (!postcode.equals(other.postcode))
				return false;
			if (street == null) {
				if (other.street != null)
					return false;
			} else if (!street.equals(other.street))
				return false;
			if (streetNumber == null) {
				if (other.streetNumber != null)
					return false;
			} else if (!streetNumber.equals(other.streetNumber))
				return false;
			if (voivodeship == null) {
				if (other.voivodeship != null)
					return false;
			} else if (!voivodeship.equals(other.voivodeship))
				return false;
			return true;
		}
	   
}
