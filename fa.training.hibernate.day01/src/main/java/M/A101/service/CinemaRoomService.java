package M.A101.service;

import M.A101.entity.CinemaRoom;

public interface CinemaRoomService {
    boolean insertCinemaRoom(CinemaRoom cinemaRoom);
    CinemaRoom findById(int id);
}
