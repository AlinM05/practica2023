package magazin.hibernate;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity
public class Price implements Serializable {
//	@Id
//	@GeneratedValue(generator = "increment")
//	@GenericGenerator(name = "increment", strategy = "increment")
//	private long id;
	private int value;

	@Id
	@ManyToOne
	@JoinColumn(name = "store_id", nullable = false)
	private Store store;

	@Id
	@ManyToOne
	@JoinColumn(name = "article_id", nullable = false)
	private Article article;

	public Price(int value, Store store, Article article) {
		super();
		this.value = value;
		this.setStore(store);
		this.setArticle(article);
	}

	public Price() {
	}

	public int getValue() {
		return value;
	}

	public void setValue(int value) {
		this.value = value;
	}

	public Store getStore() {
		return store;
	}

	public void setStore(Store store) {
		this.store = store;
	}

	public Article getArticle() {
		return article;
	}

	public void setArticle(Article article) {
		this.article = article;
	}

}