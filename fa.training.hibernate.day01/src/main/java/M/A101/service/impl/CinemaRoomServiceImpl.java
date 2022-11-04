package M.A101.service.impl;

import M.A101.dao.CinemaRoomDao;
import M.A101.dao.impl.CinemaRoomDaoImpl;
import M.A101.entity.CinemaRoom;
import M.A101.service.CinemaRoomService;

public class CinemaRoomServiceImpl implements CinemaRoomService {

    CinemaRoomDao dao = new CinemaRoomDaoImpl();

    @Override
    public boolean insertCinemaRoom(CinemaRoom cinemaRoom) {
        return dao.insertCinemaRoom(cinemaRoom);
    }

    @Override
    public CinemaRoom findById(int id) {
        return dao.findById(id);
    }
}
