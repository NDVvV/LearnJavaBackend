package M.A101.service.impl;

import M.A101.dao.SeatDao;
import M.A101.dao.impl.SeatDaoImpl;
import M.A101.entity.Seat;
import M.A101.service.SeatService;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import pojo.HibernateUtils;

import java.util.List;

public class SeatServiceImpl implements SeatService {
    SeatDao seatDao = new SeatDaoImpl();
    @Override
    public boolean insertSeat(Seat seat) {
        return seatDao.insertSeat(seat);
    }

    @Override
    public List<Seat> getListSeatByStatus(String seat_status) {
        return seatDao.getListSeatByStatus(seat_status);

    }

    @Override
    public boolean updateStatusById(int seat_id, String seat_status) {
        return seatDao.updateStatusById(seat_id, seat_status);
    }

    @Override
    public void deleteById(int seat_id) {
        seatDao.deleteById(seat_id);
    }
}
