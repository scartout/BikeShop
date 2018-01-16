package pl.scartout.model;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import javax.xml.bind.annotation.XmlRootElement;

	@XmlRootElement
	@Entity
	@Table(name = "orders")
	public class Order implements Serializable {
	    private static final long serialVersionUID = 1L;
	    
	    @Id
	    @GeneratedValue(strategy = GenerationType.IDENTITY)
	    @Column(name = "order_id")
	    private Long id;
	    @NotNull
	    @Column(name = "order_date")
	    private Date orderDate;
	    @Column(name = "approval_date")
	    private Date approvalDate;
	    @Column(name = "shipping_date")
	    private Date shippingDate;
	    @Column(name = "complete_date")
	    private Date completeDate;
	    @ManyToOne
	    @JoinColumn(name = "client_id")
	    private Client client;
	    @ManyToMany
	    @JoinTable(name = "order_products",
	            joinColumns = { @JoinColumn(name = "order_id", referencedColumnName = "order_id") },
	            inverseJoinColumns = { @JoinColumn(name = "product_id", referencedColumnName = "product_id") })
	    private List<Product> products;
	    
	    public Order(){}

		public Order(Date orderDate, Date approvalDate, Date shippingDate, Date completeDate, Client client,
				List<Product> products) {
			super();
			this.orderDate = orderDate;
			this.approvalDate = approvalDate;
			this.shippingDate = shippingDate;
			this.completeDate = completeDate;
			this.client = client;
			this.products = products;
		}

		@Override
		public String toString() {
			return "Order [id=" + id + ", orderDate=" + orderDate + ", approvalDate=" + approvalDate + ", shippingDate="
					+ shippingDate + ", completeDate=" + completeDate + ", client=" + client + ", products=" + products
					+ "]";
		}

		@Override
		public int hashCode() {
			final int prime = 31;
			int result = 1;
			result = prime * result + ((approvalDate == null) ? 0 : approvalDate.hashCode());
			result = prime * result + ((client == null) ? 0 : client.hashCode());
			result = prime * result + ((completeDate == null) ? 0 : completeDate.hashCode());
			result = prime * result + ((id == null) ? 0 : id.hashCode());
			result = prime * result + ((orderDate == null) ? 0 : orderDate.hashCode());
			result = prime * result + ((products == null) ? 0 : products.hashCode());
			result = prime * result + ((shippingDate == null) ? 0 : shippingDate.hashCode());
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
			Order other = (Order) obj;
			if (approvalDate == null) {
				if (other.approvalDate != null)
					return false;
			} else if (!approvalDate.equals(other.approvalDate))
				return false;
			if (client == null) {
				if (other.client != null)
					return false;
			} else if (!client.equals(other.client))
				return false;
			if (completeDate == null) {
				if (other.completeDate != null)
					return false;
			} else if (!completeDate.equals(other.completeDate))
				return false;
			if (id == null) {
				if (other.id != null)
					return false;
			} else if (!id.equals(other.id))
				return false;
			if (orderDate == null) {
				if (other.orderDate != null)
					return false;
			} else if (!orderDate.equals(other.orderDate))
				return false;
			if (products == null) {
				if (other.products != null)
					return false;
			} else if (!products.equals(other.products))
				return false;
			if (shippingDate == null) {
				if (other.shippingDate != null)
					return false;
			} else if (!shippingDate.equals(other.shippingDate))
				return false;
			return true;
		}
		
}
