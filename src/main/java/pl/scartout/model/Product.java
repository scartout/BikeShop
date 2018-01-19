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
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.xml.bind.annotation.XmlRootElement;

import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;

	@XmlRootElement
	@Entity
	@Table(name = "products")
	public class Product implements Serializable {
	    private static final long serialVersionUID = 1L;
	    
	    @Id
	    @GeneratedValue(strategy = GenerationType.IDENTITY)
	    @Column(name = "product_id")
	    private Long id;
	    @NotNull
	    @Column(name = "name", nullable = false)
	    private String name;
	    @Column(name = "description_short")
	    private String descriptionShort;
	    @Column(name = "description_long")
	    private String descriptionLong;
	    @Column(name = "description_size")
	    private String descriptionSize;
	    @NotNull
	    @Column(name = "price")
	    private Double price;
	    @Column(name = "price_net")
	    private Double priceNet;
	    private Double vat;
	    @ManyToMany(mappedBy = "products")
	    private List<Order> orders;
	    @LazyCollection(LazyCollectionOption.FALSE)
	    @OneToMany(mappedBy = "product",
	    		cascade = { CascadeType.PERSIST, CascadeType.REMOVE },
	    		orphanRemoval = true)
	    private List<Image> images = new ArrayList<>();
	    @ManyToOne
	    @JoinColumn(name = "category_id")
	    private Category category;
	    @ManyToOne
	    @JoinColumn(name = "producer_id")
	    private Producer producer;
	    @LazyCollection(LazyCollectionOption.FALSE)
	    @OneToMany(mappedBy = "product",
	    		cascade = { CascadeType.PERSIST, CascadeType.REMOVE },
	    		orphanRemoval = true)
	    private List<Comment> comments = new ArrayList<>();
	    
	    public Product() {}

		public Product(Long id, String name, String descriptionShort, String descriptionLong, String descriptionSize,
				Double price, Double priceNet, Double vat, List<Order> orders, List<Image> images, Category category,
				Producer producer, List<Comment> comments) {
			super();
			this.name = name;
			this.descriptionShort = descriptionShort;
			this.descriptionLong = descriptionLong;
			this.descriptionSize = descriptionSize;
			this.price = price;
			this.priceNet = priceNet;
			this.vat = vat;
		}

		public Long getId() {
			return id;
		}

		public void setId(Long id) {
			this.id = id;
		}

		public String getName() {
			return name;
		}

		public void setName(String name) {
			this.name = name;
		}

		public String getDescriptionShort() {
			return descriptionShort;
		}

		public void setDescriptionShort(String descriptionShort) {
			this.descriptionShort = descriptionShort;
		}

		public String getDescriptionLong() {
			return descriptionLong;
		}

		public void setDescriptionLong(String descriptionLong) {
			this.descriptionLong = descriptionLong;
		}

		public String getDescriptionSize() {
			return descriptionSize;
		}

		public void setDescriptionSize(String descriptionSize) {
			this.descriptionSize = descriptionSize;
		}

		public Double getPrice() {
			return price;
		}

		public void setPrice(Double price) {
			this.price = price;
		}

		public Double getPriceNet() {
			return priceNet;
		}

		public void setPriceNet(Double priceNet) {
			this.priceNet = priceNet;
		}

		public Double getVat() {
			return vat;
		}

		public void setVat(Double vat) {
			this.vat = vat;
		}

		public List<Order> getOrders() {
			return orders;
		}

		public void setOrders(List<Order> orders) {
			this.orders = orders;
		}

		public List<Image> getImages() {
			return images;
		}

		public void setImages(List<Image> images) {
			this.images = images;
		}

		public Category getCategory() {
			return category;
		}

		public void setCategory(Category category) {
			this.category = category;
		}

		public Producer getProducer() {
			return producer;
		}

		public void setProducer(Producer producer) {
			this.producer = producer;
		}

		public List<Comment> getComments() {
			return comments;
		}

		public void setComments(List<Comment> comments) {
			this.comments = comments;
		}

		@Override
		public String toString() {
			return "Product [id=" + id + ", name=" + name + ", descriptionShort=" + descriptionShort
					+ ", descriptionLong=" + descriptionLong + ", descriptionSize=" + descriptionSize + ", price="
					+ price + ", priceNet=" + priceNet + ", vat=" + vat + ", orders=" + orders + ", images=" + images
					+ ", category=" + category + ", producer=" + producer + ", comments=" + comments + "]";
		}

		@Override
		public int hashCode() {
			final int prime = 31;
			int result = 1;
			result = prime * result + ((category == null) ? 0 : category.hashCode());
			result = prime * result + ((comments == null) ? 0 : comments.hashCode());
			result = prime * result + ((descriptionLong == null) ? 0 : descriptionLong.hashCode());
			result = prime * result + ((descriptionShort == null) ? 0 : descriptionShort.hashCode());
			result = prime * result + ((descriptionSize == null) ? 0 : descriptionSize.hashCode());
			result = prime * result + ((id == null) ? 0 : id.hashCode());
			result = prime * result + ((images == null) ? 0 : images.hashCode());
			result = prime * result + ((name == null) ? 0 : name.hashCode());
			result = prime * result + ((orders == null) ? 0 : orders.hashCode());
			result = prime * result + ((price == null) ? 0 : price.hashCode());
			result = prime * result + ((priceNet == null) ? 0 : priceNet.hashCode());
			result = prime * result + ((producer == null) ? 0 : producer.hashCode());
			result = prime * result + ((vat == null) ? 0 : vat.hashCode());
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
			Product other = (Product) obj;
			if (category == null) {
				if (other.category != null)
					return false;
			} else if (!category.equals(other.category))
				return false;
			if (comments == null) {
				if (other.comments != null)
					return false;
			} else if (!comments.equals(other.comments))
				return false;
			if (descriptionLong == null) {
				if (other.descriptionLong != null)
					return false;
			} else if (!descriptionLong.equals(other.descriptionLong))
				return false;
			if (descriptionShort == null) {
				if (other.descriptionShort != null)
					return false;
			} else if (!descriptionShort.equals(other.descriptionShort))
				return false;
			if (descriptionSize == null) {
				if (other.descriptionSize != null)
					return false;
			} else if (!descriptionSize.equals(other.descriptionSize))
				return false;
			if (id == null) {
				if (other.id != null)
					return false;
			} else if (!id.equals(other.id))
				return false;
			if (images == null) {
				if (other.images != null)
					return false;
			} else if (!images.equals(other.images))
				return false;
			if (name == null) {
				if (other.name != null)
					return false;
			} else if (!name.equals(other.name))
				return false;
			if (orders == null) {
				if (other.orders != null)
					return false;
			} else if (!orders.equals(other.orders))
				return false;
			if (price == null) {
				if (other.price != null)
					return false;
			} else if (!price.equals(other.price))
				return false;
			if (priceNet == null) {
				if (other.priceNet != null)
					return false;
			} else if (!priceNet.equals(other.priceNet))
				return false;
			if (producer == null) {
				if (other.producer != null)
					return false;
			} else if (!producer.equals(other.producer))
				return false;
			if (vat == null) {
				if (other.vat != null)
					return false;
			} else if (!vat.equals(other.vat))
				return false;
			return true;
		}

}