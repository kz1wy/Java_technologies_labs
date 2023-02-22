import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import org.hibernate.query.Query;
import utils.HibernateUtils;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;


public class ManufactureDAO implements Repository<Manufacture> {
    private SessionFactory sessionFactory;

    public ManufactureDAO(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    public boolean add(Manufacture m) {
        Transaction transaction = null;
        try (Session session = HibernateUtils.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();
            session.persist(m);
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

    public Manufacture get(long id) {
        try (Session session = sessionFactory.openSession()) {
            return session.get(Manufacture.class, id);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public List<Manufacture> getAll() {
        try (Session session = sessionFactory.openSession()) {
            return session.createQuery("from Manufacture", Manufacture.class).list();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
    public boolean remove(long id) {
        Transaction transaction = null;
        try (Session session = HibernateUtils.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();
            Manufacture manufacture = session.get(Manufacture.class, id);
            if (manufacture != null) {
                session.delete(manufacture);
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
    public boolean remove(Manufacture m) {
        return remove(m.getId());
    }

    public boolean update(Manufacture m) {
        Transaction transaction = null;
        try (Session session = HibernateUtils.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();
            session.update(m);
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

    public boolean haveMoreThan100Emp() {
        try (Session session = sessionFactory.openSession()) {
            Query<Manufacture> query = session.createQuery("from Manufacture where employee > 100", Manufacture.class);
            return !query.list().isEmpty();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    public long getSumOfEmployees() {
        try (Session session = sessionFactory.openSession()) {
            Query<Long> query = session.createQuery("SELECT SUM(employee) FROM Manufacture", Long.class);
            Long sum = query.getSingleResult();
            return sum == null ? 0 : sum;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return 0;
    }
    public Manufacture getLastManufacture() {
        try (Session session = HibernateUtils.getSessionFactory().openSession()) {
            CriteriaBuilder builder = session.getCriteriaBuilder();
            CriteriaQuery<Manufacture> criteria = builder.createQuery(Manufacture.class);
            Root<Manufacture> root = criteria.from(Manufacture.class);
            criteria.select(root)
                    .where(builder.equal(root.get("location"), "USA"))
                    .orderBy(builder.desc(root.get("id")));
            List<Manufacture> m = session.createQuery(criteria).getResultList();
            if (m.isEmpty())
                throw new IllegalStateException("Not found any manufacture based in the US");
                //IllegalStateException equivalent to InvalidOperationException in C#.
            return m.get(0);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}