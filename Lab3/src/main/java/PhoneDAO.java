import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import org.hibernate.query.Query;
import utils.HibernateUtils;

public class PhoneDAO implements Repository<Phone>{
    private SessionFactory sessionFactory;

    public PhoneDAO(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    public boolean add(Phone p) {
        Transaction transaction = null;
        try (Session session = HibernateUtils.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();
            session.persist(p);
            transaction.commit();
            return true;
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
            return false;
        }
    }

    public Phone get(long id) {
        try (Session session = sessionFactory.openSession()) {
            return session.get(Phone.class, id);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public List<Phone> getAll() {
        try (Session session = sessionFactory.openSession()) {
            return session.createQuery("from Phone", Phone.class).list();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
    public boolean remove(long id) {
        Transaction transaction = null;
        try (Session session = HibernateUtils.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();
            Phone phone = session.get(Phone.class, id);
            if (phone != null) {
                session.delete(phone);
                transaction.commit();
                return true;
            } else {
                return false;
            }
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
            return false;
        }
    }
    public boolean remove(Phone p) {
        return remove(p.getId());
    }

    public boolean update(Phone p) {
        Transaction transaction = null;
        try (Session session = HibernateUtils.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();
            session.update(p);
            transaction.commit();
            return true;
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
            return false;
        }
    }

    public Phone getHighestSellingPrice() {
        try (Session session = sessionFactory.openSession()) {
            Query<Phone> query = session.createQuery("from Phone order by price desc", Phone.class);
            query.setMaxResults(1);
            return query.uniqueResult();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
    public List<Phone> getPhoneOrderByCountryName() {
        try (Session session = sessionFactory.openSession()) {
            Query<Phone> query = session.createQuery("from Phone order by country asc, price desc", Phone.class);
            return query.list();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public boolean existPriceAbove50m() {
        try (Session session = sessionFactory.openSession()) {
            Query<Phone> query = session.createQuery("from Phone where price > 50000000", Phone.class);
            if (!query.list().isEmpty()) return true;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    public Phone meetRequirementsPhone() {
        try (Session session = sessionFactory.openSession()) {
            Query<Phone> query = session.createQuery("from Phone where color = 'Pink' and price > 15000000", Phone.class);
            query.setMaxResults(1);
            return query.uniqueResult();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

}