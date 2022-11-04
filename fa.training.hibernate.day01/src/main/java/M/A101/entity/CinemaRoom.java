package M.A101.entity;


import javax.persistence.*;
import java.util.List;
import java.util.Set;

@Entity
public class CinemaRoom {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int cinema_room_id;
    private String cinema_room_name;
    private int seat_quantity;

    @OneToMany(mappedBy = "cinema_room_id", cascade = CascadeType.ALL)
    private Set<Seat> seats;

    @OneToOne(mappedBy = "cinema_room_id", cascade = CascadeType.ALL)
    private CinemaRoomDetails cinemaRoomDetail;

    public CinemaRoom() {
    }

    public int getCinema_room_id() {
        return cinema_room_id;
    }

    public void setCinema_room_id(int cinema_room_id) {
        this.cinema_room_id = cinema_room_id;
    }

    public String getCinema_room_name() {
        return cinema_room_name;
    }

    public void setCinema_room_name(String cinema_room_name) {
        this.cinema_room_name = cinema_room_name;
    }

    public int getSeat_quantity() {
        return seat_quantity;
    }

    public void setSeat_quantity(int seat_quantity) {
        this.seat_quantity = seat_quantity;
    }
}
