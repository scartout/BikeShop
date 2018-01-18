package pl.scartout.model;

import java.io.Serializable;

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
	@Table(name = "comment")
	public class Comment implements Serializable {
	    private static final long serialVersionUID = 1L;
	    
	    @Id
	    @GeneratedValue(strategy = GenerationType.IDENTITY)
	    @Column(name = "comment_id")
	    private Long id;
	    @NotNull
	    @Column(name = "comment")
	    private String comment;
	    @ManyToOne
	    @JoinColumn(name = "client_id")
	    private Client client;
	    @ManyToOne
	    @JoinColumn(name = "product_id")
	    private Product product;

	    
	    public Comment(){}

		
		
}
