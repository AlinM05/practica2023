package project2;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity
public class Price implements Serializable {

	private long id;
	private int value;

	@Id
	@ManyToOne
	@JoinColumn(name = "store_id", nullable = false)
	private Store store;

	@Id
	@ManyToOne
	@JoinColumn(name = "article_id", nullable = false)
	private Article article;

	public Price(int price, Store store, Article article) {
		this.value = price;
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