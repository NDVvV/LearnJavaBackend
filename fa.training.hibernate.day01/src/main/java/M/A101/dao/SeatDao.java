package M.A101.dao;

import M.A101.entity.Seat;

import java.util.List;

public interface SeatDao {
    boolean insertSeat(Seat seat);
    List<Seat> getListSeatByStatus(String seat_status);
    boolean updateStatusById(int seat_id, String seat_status);
    void deleteById(int seat_id);
}
