package M.A101.dao.impl;

import M.A101.dao.SeatDao;
import M.A101.entity.Seat;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import pojo.HibernateUtils;

import java.util.List;

public class SeatDaoImpl implements SeatDao {
    @Override
    public boolean insertSeat(Seat seat) {
        int id = 0;
        Session session = HibernateUtils.getFactory().openSession();
        Transaction transaction = session.getTransaction();


        try {
            transaction.begin();
            id = (int) session.save(seat);



        } catch (Exception ex) {
            ex.printStackTrace();
            transaction.rollback();
        } finally {
            transaction.commit();
            session.close();
        }


        return id != 0;
    }

    @Override
    public List<Seat> getListSeatByStatus(String seat_status) {
        Session session = HibernateUtils.getFactory().openSession();

        try {
            Query query = session.createNativeQuery("SELECT * FROM Seat WHERE seat_status = :status", Seat.class);
            query.setParameter("status", seat_status);
            return query.getResultList();


        } catch (Exception ex) {
            ex.printStackTrace();
        } finally {
            session.close();
        }

        return null;

    }

    @Override
    public boolean updateStatusById(int seat_id, String seat_status) {
        Session session = HibernateUtils.getFactory().openSession();
        Transaction transaction = session.getTransaction();
        int id = 0;

        try {
            Seat seatBefore = session.get(Seat.class, seat_id);
            seatBefore.setSeat_status(seat_status);
            transaction.begin();
            id = (int) session.save(seatBefore);

        } catch (Exception ex) {
            transaction.rollback();
            ex.printStackTrace();
        } finally {
            transaction.commit();
            session.close();
        }
        return id != 0;
    }

    @Override
    public void deleteById(int seat_id) {
        Session session = HibernateUtils.getFactory().openSession();
        Transaction transaction = session.getTransaction();

        try {
            Seat seat = session.get(Seat.class, seat_id);
            transaction.begin();
            session.delete(seat);

        } catch (Exception ex) {
            transaction.rollback();
            ex.printStackTrace();
        } finally {
            transaction.commit();
            session.close();
        }
    }
}
