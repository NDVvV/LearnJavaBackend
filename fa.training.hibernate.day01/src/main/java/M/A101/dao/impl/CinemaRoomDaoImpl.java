package M.A101.dao.impl;

import M.A101.dao.CinemaRoomDao;
import M.A101.entity.CinemaRoom;
import org.hibernate.Session;
import org.hibernate.Transaction;
import pojo.HibernateUtils;

public class CinemaRoomDaoImpl implements CinemaRoomDao {
    @Override
    public boolean insertCinemaRoom(CinemaRoom cinemaRoom) {
        Session session = HibernateUtils.getFactory().openSession();
        Transaction transaction = session.getTransaction();
        int id = 0;

        try {
            transaction.begin();
            id = (int) session.save(cinemaRoom);

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
    public CinemaRoom findById(int id) {
        Session session = HibernateUtils.getFactory().openSession();
        CinemaRoom cinemaRoom = null;

        try {
            cinemaRoom = session.get(CinemaRoom.class, id);

        }catch (Exception ex) {
            ex.printStackTrace();
        }finally {

            session.close();
        }

        return cinemaRoom;

    }
}
