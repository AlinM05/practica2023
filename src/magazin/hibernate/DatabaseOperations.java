package magazin.hibernate;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;

import utils.WithSessionAndTransaction;

public class DatabaseOperations {
	private SessionFactory sessionFactory;

	public DatabaseOperations(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}

	public DatabaseOperations() {

	}

	public void addArticle(String name) {
		new WithSessionAndTransaction() {
			@Override
			public void doAction(Session session) {
				Article a = new Article(name);
				session.save(a);
			}
		}.run();

	}

	public void addStore(String name) {
		new WithSessionAndTransaction() {
			@Override
			public void doAction(Session session) {
				Store store = getStoreByName(name, session);

				if (store != null) {
					throw new RuntimeException("Magazinul exista deja: " + name);
				}

				Store a = new Store(name);
				session.save(a);
			}
		}.run();

	}

	public void addPrice(String storeName, String articleName, int price) {
		new WithSessionAndTransaction() {
			@Override
			public void doAction(Session session) {
				Store store = getStoreByName(storeName, session);

				if (store == null) {
					throw new RuntimeException("Magazin inexistent: " + storeName);
				}

				Article article = getArticleByName(articleName, session);
				if (article == null) {
					throw new RuntimeException("Articol inexistent: " + articleName);
				}

				Price p = new Price(price, store, article);
				session.save(p);

			}
		}.run();
	}

	private Store getStoreByName(String name, Session session) {
		Query<Store> query = session.createQuery("from Store where name = :name", Store.class);
		query.setParameter("name", name);
		Store store = query.uniqueResult();
		return store;
	}

	private Article getArticleByName(String articleName, Session session) {
		Query<Article> query = session.createQuery("from Article where name = :name", Article.class);
		query.setParameter("name", articleName);
		Article article = query.uniqueResult();
		return article;
	}

	private List<Article> getArticles(Session session) {
		Query<Article> query = session.createQuery("from Article", Article.class);
		return query.list();
	}

	public List<Article> getAllArticles() {
		try (Session session = sessionFactory.openSession()) {
			List<Article> articles = session.createQuery("FROM Article", Article.class).getResultList();
			return articles;
		}
	}

	public void removeStore(String name) {
		new WithSessionAndTransaction() {
			@Override
			public void doAction(Session session) {
				Store store = getStoreByName(name, session);
				if (store == null) {
					throw new RuntimeException("Magazin inexistent: " + name);
				}
				session.delete(store);
			}
		}.run();
	}

	public void removeArticle(Long articleId) {
		new WithSessionAndTransaction() {
			@Override
			public void doAction(Session session) {
				Article article = session.get(Article.class, articleId);
				if (article == null) {
					throw new RuntimeException("Articol inexistent cu ID-ul: " + articleId);
				}
				session.delete(article);
			}
		}.run();
	}

	public void removePrice(Long priceId) {
		new WithSessionAndTransaction() {
			@Override
			public void doAction(Session session) {
				Price price = session.get(Price.class, priceId);
				if (price == null) {
					throw new RuntimeException("Pret inexistent cu ID-ul: " + priceId);
				}
				session.delete(price);
			}
		}.run();
	}

	public void modifyStore(Long storeId, String newName) {
		new WithSessionAndTransaction() {
			@Override
			public void doAction(Session session) {
				Store store = session.get(Store.class, storeId);
				if (store == null) {
					throw new RuntimeException("Magazin inexistent cu ID-ul: " + storeId);
				}
				store.setName(newName);
				session.update(store);
			}
		}.run();
	}

	public void modifyArticle(Long articleId, String newArticleName) {
		new WithSessionAndTransaction() {
			@Override
			public void doAction(Session session) {
				Article article = session.get(Article.class, articleId);
				if (article == null) {
					throw new RuntimeException("Articol inexistent cu ID-ul: " + articleId);
				}
				article.setName(newArticleName);
				session.update(article);
			}
		}.run();
	}

	public void modifyPrice(Long priceId, int newPriceValue) {
		new WithSessionAndTransaction() {
			@Override
			public void doAction(Session session) {
				Price price = session.get(Price.class, priceId);
				if (price == null) {
					throw new RuntimeException("Pret inexistent cu ID-ul: " + priceId);
				}
				price.setValue(newPriceValue);
				session.update(price);
			}
		}.run();
	}
}