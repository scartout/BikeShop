package pl.scartout.model;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;

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
	    private int quantity;
	    private double total;
	    @Column(name = "invoice_number")
	    private String invoiceNumber;
	    @Column(name = "order_date")
	    private Date orderDate;
	    @Column(name = "approval_date")
	    private Date approvalDate;
	    @Column(name = "shipping_date")
	    private Date shippingDate;
	    @Column(name = "complete_date")
	    private Date completeDate;
	    @ManyToOne
	    @JoinColumn(name = "user_id")
	    private User user;
	    @OneToOne
	    @JoinColumn(name = "product_id")
	    private Product product;
	    
	    public Order(){}

		public Order(int quantity) {
			super();
			this.quantity = quantity;
		}

		public Long getId() {
			return id;
		}

		public void setId(Long id) {
			this.id = id;
		}

		public int getQuantity() {
			return quantity;
		}

		public void setQuantity(int quantity) {
			this.quantity = quantity;
		}

		public double getTotal() {
			return total;
		}

		public void setTotal(double total) {
			this.total = total;
		}

		public String getInvoiceNumber() {
			return invoiceNumber;
		}

		public void setInvoiceNumber(String invoiceNumber) {
			this.invoiceNumber = invoiceNumber;
		}

		public Date getOrderDate() {
			return orderDate;
		}

		public void setOrderDate(Date orderDate) {
			this.orderDate = orderDate;
		}

		public Date getApprovalDate() {
			return approvalDate;
		}

		public void setApprovalDate(Date approvalDate) {
			this.approvalDate = approvalDate;
		}

		public Date getShippingDate() {
			return shippingDate;
		}

		public void setShippingDate(Date shippingDate) {
			this.shippingDate = shippingDate;
		}

		public Date getCompleteDate() {
			return completeDate;
		}

		public void setCompleteDate(Date completeDate) {
			this.completeDate = completeDate;
		}

		public User getUser() {
			return user;
		}

		public void setUser(User user) {
			this.user = user;
		}

		public Product getProduct() {
			return product;
		}

		public void setProduct(Product product) {
			this.product = product;
		}

		@Override
		public String toString() {
			return "Order [id=" + id + ", quantity=" + quantity + ", total=" + total + ", invoiceNumber="
					+ invoiceNumber + ", orderDate=" + orderDate + ", approvalDate=" + approvalDate + ", shippingDate="
					+ shippingDate + ", completeDate=" + completeDate + ", user=" + user + ", product=" + product + "]";
		}

		@Override
		public int hashCode() {
			final int prime = 31;
			int result = 1;
			result = prime * result + ((approvalDate == null) ? 0 : approvalDate.hashCode());
			result = prime * result + ((completeDate == null) ? 0 : completeDate.hashCode());
			result = prime * result + ((id == null) ? 0 : id.hashCode());
			result = prime * result + ((invoiceNumber == null) ? 0 : invoiceNumber.hashCode());
			result = prime * result + ((orderDate == null) ? 0 : orderDate.hashCode());
			result = prime * result + ((product == null) ? 0 : product.hashCode());
			result = prime * result + quantity;
			result = prime * result + ((shippingDate == null) ? 0 : shippingDate.hashCode());
			long temp;
			temp = Double.doubleToLongBits(total);
			result = prime * result + (int) (temp ^ (temp >>> 32));
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
			Order other = (Order) obj;
			if (approvalDate == null) {
				if (other.approvalDate != null)
					return false;
			} else if (!approvalDate.equals(other.approvalDate))
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
			if (invoiceNumber == null) {
				if (other.invoiceNumber != null)
					return false;
			} else if (!invoiceNumber.equals(other.invoiceNumber))
				return false;
			if (orderDate == null) {
				if (other.orderDate != null)
					return false;
			} else if (!orderDate.equals(other.orderDate))
				return false;
			if (product == null) {
				if (other.product != null)
					return false;
			} else if (!product.equals(other.product))
				return false;
			if (quantity != other.quantity)
				return false;
			if (shippingDate == null) {
				if (other.shippingDate != null)
					return false;
			} else if (!shippingDate.equals(other.shippingDate))
				return false;
			if (Double.doubleToLongBits(total) != Double.doubleToLongBits(other.total))
				return false;
			if (user == null) {
				if (other.user != null)
					return false;
			} else if (!user.equals(other.user))
				return false;
			return true;
		}

}
