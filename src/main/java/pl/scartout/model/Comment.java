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
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import javax.xml.bind.annotation.XmlRootElement;

	@XmlRootElement
	@Entity
	@Table(name = "comments")
	final public class Comment implements Serializable {
	    private static final long serialVersionUID = 1L;
	    
	    @Id
	    @GeneratedValue(strategy = GenerationType.IDENTITY)
	    @Column(name = "comment_id")
	    private Long id;
	    @NotNull
	    @Column(name = "comment")
	    private String description;
	    @NotNull
	    private Date date;
	    @NotNull
	    private double vote;
	    @ManyToOne
	    @JoinColumn(name = "user_id")
	    private User user;
	    @ManyToOne
	    @JoinColumn(name = "product_id")
	    private Product product;
	    
	    public Comment(){}

		public Comment(String description, Date date, double vote) {
			super();
			this.description = description;
			this.date = date;
			this.vote = vote;
		}

		public Long getId() {
			return id;
		}

		public void setId(Long id) {
			this.id = id;
		}

		public String getDescription() {
			return description;
		}

		public void setDescription(String description) {
			this.description = description;
		}

		public Date getDate() {
			return date;
		}

		public void setDate(Date date) {
			this.date = date;
		}

		public double getVote() {
			return vote;
		}

		public void setVote(double vote) {
			this.vote = vote;
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
			return "Comment [id=" + id + ", description=" + description + ", date=" + date + ", vote=" + vote
					+ ", user=" + user + ", product=" + product + "]";
		}

		@Override
		public int hashCode() {
			final int prime = 31;
			int result = 1;
			result = prime * result + ((date == null) ? 0 : date.hashCode());
			result = prime * result + ((description == null) ? 0 : description.hashCode());
			result = prime * result + ((id == null) ? 0 : id.hashCode());
			result = prime * result + ((product == null) ? 0 : product.hashCode());
			result = prime * result + ((user == null) ? 0 : user.hashCode());
			long temp;
			temp = Double.doubleToLongBits(vote);
			result = prime * result + (int) (temp ^ (temp >>> 32));
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
			Comment other = (Comment) obj;
			if (date == null) {
				if (other.date != null)
					return false;
			} else if (!date.equals(other.date))
				return false;
			if (description == null) {
				if (other.description != null)
					return false;
			} else if (!description.equals(other.description))
				return false;
			if (id == null) {
				if (other.id != null)
					return false;
			} else if (!id.equals(other.id))
				return false;
			if (product == null) {
				if (other.product != null)
					return false;
			} else if (!product.equals(other.product))
				return false;
			if (user == null) {
				if (other.user != null)
					return false;
			} else if (!user.equals(other.user))
				return false;
			if (Double.doubleToLongBits(vote) != Double.doubleToLongBits(other.vote))
				return false;
			return true;
		}
		
}
