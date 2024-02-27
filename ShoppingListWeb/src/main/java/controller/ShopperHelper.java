package controller;

import java.util.List;

import javax.persistence.*;

import model.Shopper;

//package and import statements
public class ShopperHelper {
	static EntityManagerFactory emfactory = Persistence.createEntityManagerFactory("shopping2");
	
	public void insertShopper(Shopper s) {
		EntityManager em = emfactory.createEntityManager();
		em.getTransaction().begin();
		em.persist(s);
		em.getTransaction().commit();
		em.close();
		}
	public List<Shopper> showAllShoppers() {
		EntityManager em =
		emfactory.createEntityManager();
		List<Shopper> allShoppers = em.createQuery("SELECT s FROM Shopper s").getResultList();
		return allShoppers;
		}
	
	public Shopper findShopper(String nameToLookUp) {
			EntityManager em = emfactory.createEntityManager();
			em.getTransaction().begin();
			TypedQuery<Shopper> typedQuery = em.createQuery("select sh from Shopper sh where sh.shopperName = :selectedName", Shopper.class);
			typedQuery.setParameter("selectedName", nameToLookUp);
			typedQuery.setMaxResults(1);
			Shopper foundShopper;
			try {
				foundShopper = typedQuery.getSingleResult();
			} catch (NoResultException ex) {
				foundShopper = new Shopper(nameToLookUp);
			}
			em.close();
			return foundShopper;
		}
}

