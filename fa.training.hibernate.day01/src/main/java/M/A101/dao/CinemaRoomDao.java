package M.A101.dao;

import M.A101.entity.CinemaRoom;
import org.hibernate.Session;
import pojo.HibernateUtils;

public interface CinemaRoomDao {

    boolean insertCinemaRoom(CinemaRoom cinemaRoom);
    CinemaRoom findById(int id);


}
