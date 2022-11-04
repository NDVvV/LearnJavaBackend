package M.A101.entity;

import javax.persistence.*;

@Entity
public class Seat {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int seat_id;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "cinema_room_id")
    private CinemaRoom cinema_room_id;
    private String seat_column;
    private int seat_row;
    private String seat_status;
    private String seat_type;

    public Seat() {
    }

    public int getSeat_id() {
        return seat_id;
    }

    public void setSeat_id(int seat_id) {
        this.seat_id = seat_id;
    }

    public CinemaRoom getCinema_room_id() {
        return cinema_room_id;
    }

    public void setCinema_room_id(CinemaRoom cinema_room_id) {
        this.cinema_room_id = cinema_room_id;
    }

    public String getSeat_column() {
        return seat_column;
    }

    public void setSeat_column(String seat_column) {
        this.seat_column = seat_column;
    }

    public int getSeat_row() {
        return seat_row;
    }

    public void setSeat_row(int seat_row) {
        this.seat_row = seat_row;
    }

    public String getSeat_status() {
        return seat_status;
    }

    public void setSeat_status(String seat_status) {
        this.seat_status = seat_status;
    }

    public String getSeat_type() {
        return seat_type;
    }

    public void setSeat_type(String seat_type) {
        this.seat_type = seat_type;
    }
}
