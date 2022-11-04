package M.A101.entity;

import javax.persistence.*;
import java.sql.Date;

@Entity
public class CinemaRoomDetails {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int cinema_room_details_id;
    @OneToOne
    private CinemaRoom cinema_room_id;
    private int room_rate;
    private Date active_date;
    private String description;

    public CinemaRoomDetails() {
    }

    public int getCinema_room_details_id() {
        return cinema_room_details_id;
    }

    public void setCinema_room_details_id(int cinema_room_details_id) {
        this.cinema_room_details_id = cinema_room_details_id;
    }

    public CinemaRoom getCinema_room_id() {
        return cinema_room_id;
    }

    public void setCinema_room_id(CinemaRoom cinema_room_id) {
        cinema_room_id = cinema_room_id;
    }

    public int getRoom_rate() {
        return room_rate;
    }

    public void setRoom_rate(int room_rate) {
        this.room_rate = room_rate;
    }

    public Date getActive_date() {
        return active_date;
    }

    public void setActive_date(Date active_date) {
        this.active_date = active_date;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
