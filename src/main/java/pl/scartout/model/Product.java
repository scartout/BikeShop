package pl.scartout.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.xml.bind.annotation.XmlRootElement;

import com.google.common.base.Preconditions;
import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;

	@XmlRootElement
	@Entity
	@Table(name = "products")
	public final class Product implements Serializable {
	    private static final long serialVersionUID = 1L;
	    
	    @Id
	    @GeneratedValue(strategy = GenerationType.IDENTITY)
	    @Column(name = "product_id")
	    private Long id;
	    @Column(name = "sku", unique = true)
	    private String sku;
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
	    @Column(name = "vat")
	    private Double vat;
	    @Column(name = "image_main")
	    private String mainImage;
	    @Column(name = "image_second")
	    private String imageSecond;
	    @Column(name = "image_third")
	    private String imageThird;
	    @Column(name = "date_added")
	    private Date dateAdded;
	    @ManyToOne
	    @JoinColumn(name = "category_id")
	    private Category category;
	    @ManyToOne
	    @JoinColumn(name = "manufacturer_id")
	    private Manufacturer manufacturer;
	    @LazyCollection(LazyCollectionOption.FALSE)
	    @OneToMany(mappedBy = "product",
	    		cascade = { CascadeType.PERSIST, CascadeType.REMOVE },
	    		orphanRemoval = true)
	    private List<Comment> comments = new ArrayList<>();
	    
	    public Product() {}

		public Product(String sku, String name, String descriptionShort, String descriptionLong, String descriptionSize,
				Double price, Double vat, String mainImage, String imageSecond, String imageThird) {
			Preconditions.checkArgument(price>=0, "Price cannot be negative");
			Preconditions.checkArgument(vat>=0, "Price cannot be negative");
			Preconditions.checkArgument(vat<=100, "Price cannot be greater than 99.99");
			double countPriceNet = price/(1+(vat/100.0));
			countPriceNet = Math.round(countPriceNet*100.0)/100.0;
			Preconditions.checkState(countPriceNet>0, "Price net cannot be negative");
			Preconditions.checkState(countPriceNet<=price, "Price net cannot be greater than price gross");
			this.sku = sku;
			this.name = name;
			this.descriptionShort = descriptionShort;
			this.descriptionLong = descriptionLong;
			this.descriptionSize = descriptionSize;
			this.price = price;
			this.priceNet = countPriceNet;
			this.vat = vat;
			this.mainImage = mainImage;
			this.imageSecond = imageSecond;
			this.imageThird = imageThird;
			this.dateAdded = new Date();
		}

		public Product(Long id, String sku, String name, String descriptionShort, String descriptionLong, String descriptionSize,
				Double price, Double vat, String mainImage, String imageSecond, String imageThird,
				Category category, Manufacturer manufacturer) {
			Preconditions.checkArgument(price>=0, "Price cannot be negative");
			Preconditions.checkArgument(vat>=0, "Price cannot be negative");
			Preconditions.checkArgument(vat<=100, "Price cannot be greater than 99.99");
			double countPriceNet = price/(1+(vat/100.0));
			countPriceNet = Math.round(countPriceNet*100.0)/100.0;
			Preconditions.checkState(countPriceNet>0, "Price net cannot be negative");
			Preconditions.checkState(countPriceNet<=price, "Price net cannot be greater than price gross");
			this.id = id;
			this.sku = sku;
			this.name = name;
			this.descriptionShort = descriptionShort;
			this.descriptionLong = descriptionLong;
			this.descriptionSize = descriptionSize;
			this.price = price;
			this.priceNet = countPriceNet;
			this.vat = vat;
			this.mainImage = mainImage;
			this.imageSecond = imageSecond;
			this.imageThird = imageThird;
			this.dateAdded = new Date();
			this.category = category;
			this.manufacturer = manufacturer;
		}

		public Long getId() {
			return id;
		}

		public void setId(Long id) {
			this.id = id;
		}

		public String getSku() {
			return sku;
		}

		public void setSku(String sku) {
			this.sku = sku;
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

		public String getMainImage() {
			return mainImage;
		}

		public void setMainImage(String mainImage) {
			this.mainImage = mainImage;
		}

		public String getImageSecond() {
			return imageSecond;
		}

		public void setImageSecond(String imageSecond) {
			this.imageSecond = imageSecond;
		}

		public String getImageThird() {
			return imageThird;
		}

		public void setImageThird(String imageThird) {
			this.imageThird = imageThird;
		}

		public Date getDateAdded() {
			return dateAdded;
		}

		public void setDateAdded(Date dateAdded) {
			this.dateAdded = dateAdded;
		}

		public Category getCategory() {
			return category;
		}

		public void setCategory(Category category) {
			this.category = category;
		}

		public Manufacturer getManufacturer() {
			return manufacturer;
		}

		public void setManufacturer(Manufacturer manufacturer) {
			this.manufacturer = manufacturer;
		}

		public List<Comment> getComments() {
			return comments;
		}

		public void setComments(List<Comment> comments) {
			this.comments = comments;
		}

		@Override
		public String toString() {
			return "Product - " + id + " - " + name;
		}

		@Override
		public int hashCode() {
			final int prime = 31;
			int result = 1;
			result = prime * result + ((category == null) ? 0 : category.hashCode());
			result = prime * result + ((comments == null) ? 0 : comments.hashCode());
			result = prime * result + ((dateAdded == null) ? 0 : dateAdded.hashCode());
			result = prime * result + ((descriptionLong == null) ? 0 : descriptionLong.hashCode());
			result = prime * result + ((descriptionShort == null) ? 0 : descriptionShort.hashCode());
			result = prime * result + ((descriptionSize == null) ? 0 : descriptionSize.hashCode());
			result = prime * result + ((id == null) ? 0 : id.hashCode());
			result = prime * result + ((imageSecond == null) ? 0 : imageSecond.hashCode());
			result = prime * result + ((imageThird == null) ? 0 : imageThird.hashCode());
			result = prime * result + ((mainImage == null) ? 0 : mainImage.hashCode());
			result = prime * result + ((manufacturer == null) ? 0 : manufacturer.hashCode());
			result = prime * result + ((name == null) ? 0 : name.hashCode());
			result = prime * result + ((price == null) ? 0 : price.hashCode());
			result = prime * result + ((priceNet == null) ? 0 : priceNet.hashCode());
			result = prime * result + ((sku == null) ? 0 : sku.hashCode());
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
			if (dateAdded == null) {
				if (other.dateAdded != null)
					return false;
			} else if (!dateAdded.equals(other.dateAdded))
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
			if (imageSecond == null) {
				if (other.imageSecond != null)
					return false;
			} else if (!imageSecond.equals(other.imageSecond))
				return false;
			if (imageThird == null) {
				if (other.imageThird != null)
					return false;
			} else if (!imageThird.equals(other.imageThird))
				return false;
			if (mainImage == null) {
				if (other.mainImage != null)
					return false;
			} else if (!mainImage.equals(other.mainImage))
				return false;
			if (manufacturer == null) {
				if (other.manufacturer != null)
					return false;
			} else if (!manufacturer.equals(other.manufacturer))
				return false;
			if (name == null) {
				if (other.name != null)
					return false;
			} else if (!name.equals(other.name))
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
			if (sku == null) {
				if (other.sku != null)
					return false;
			} else if (!sku.equals(other.sku))
				return false;
			if (vat == null) {
				if (other.vat != null)
					return false;
			} else if (!vat.equals(other.vat))
				return false;
			return true;
		}

}
