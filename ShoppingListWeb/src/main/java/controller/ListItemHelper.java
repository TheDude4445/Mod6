package controller;

import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;

import model.ListItem;

public class ListItemHelper {

    static EntityManagerFactory emfactory = Persistence.createEntityManagerFactory("shopping2");

    public void addItem(String store, String item) {
        ListItem newItem = new ListItem(store, item);
        insertItem(newItem);
    }

    public void insertItem(ListItem listItem) {
        EntityManager entityManager = emfactory.createEntityManager();
        EntityTransaction transaction = entityManager.getTransaction();

        try {
            transaction.begin();
            entityManager.persist(listItem);
            transaction.commit();
        } catch (Exception e) {
            if (transaction.isActive()) {
                transaction.rollback();
            }
            e.printStackTrace();
        } finally {
            entityManager.close();
        }
    }

    public void deleteItem(ListItem listItem) {
        EntityManager entityManager = emfactory.createEntityManager();
        EntityTransaction transaction = entityManager.getTransaction();

        try {
            transaction.begin();
            ListItem itemToDelete = entityManager.find(ListItem.class, listItem.getId());
            if (itemToDelete != null) {
                entityManager.remove(itemToDelete);
                transaction.commit();
            } else {
                System.out.println("Item not found in the database.");
            }
        } catch (Exception e) {
            if (transaction.isActive()) {
                transaction.rollback();
            }
            e.printStackTrace();
        } finally {
            entityManager.close();
        }
    }

    public void updateItem(ListItem updatedItem) {
        EntityManager entityManager = emfactory.createEntityManager();
        EntityTransaction transaction = entityManager.getTransaction();

        try {
            transaction.begin();
            entityManager.merge(updatedItem);
            transaction.commit();
        } catch (Exception e) {
            if (transaction.isActive()) {
                transaction.rollback();
            }
            e.printStackTrace();
        } finally {
            entityManager.close();
        }
    }

    public ListItem searchForItemById(int id) {
        EntityManager entityManager = emfactory.createEntityManager();
        ListItem foundItem = entityManager.find(ListItem.class, id);
        entityManager.close();
        return foundItem;
    }

    public List<ListItem> getAllItems() {
        EntityManager entityManager = emfactory.createEntityManager();
        TypedQuery<ListItem> query = entityManager.createQuery("SELECT li FROM ListItem li", ListItem.class);
        List<ListItem> itemList = query.getResultList();
        entityManager.close();
        return itemList;
    }

    public List<ListItem> searchByStore(String storeName) {
        EntityManager entityManager = emfactory.createEntityManager();
        TypedQuery<ListItem> query = entityManager.createQuery("SELECT li FROM ListItem li WHERE li.store = :store", ListItem.class);
        query.setParameter("store", storeName);
        List<ListItem> itemList = query.getResultList();
        entityManager.close();
        return itemList;
    }

    public List<ListItem> searchByItem(String itemName) {
        EntityManager entityManager = emfactory.createEntityManager();
        TypedQuery<ListItem> query = entityManager.createQuery("SELECT li FROM ListItem li WHERE li.item = :item", ListItem.class);
        query.setParameter("item", itemName);
        List<ListItem> itemList = query.getResultList();
        entityManager.close();
        return itemList;
    }
}