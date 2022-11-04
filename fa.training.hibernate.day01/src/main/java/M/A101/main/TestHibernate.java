package M.A101.main;

import M.A101.dao.CinemaRoomDao;
import M.A101.dao.SeatDao;
import M.A101.dao.impl.CinemaRoomDaoImpl;
import M.A101.dao.impl.SeatDaoImpl;
import M.A101.entity.CinemaRoom;
import M.A101.entity.Seat;
import M.A101.verticle.ServerVerticle;
import io.vertx.core.Vertx;

import java.util.List;
import java.util.Scanner;

public class TestHibernate {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.println("\n\n\n*********Nhập nội dung cần thực hiện**********\n1.Thêm cinema room\n" +
                    "2.Thêm seat vào cinema room\n3.Cập nhật trạng thái seat\n4.Xóa seat qua id\n5.Tìm seat theo trạng thái");
            int choice = scanner.nextInt();
            scanner.nextLine();
            switch (choice) {
                case 1:
                    System.out.println("\nNhập tên room: ");
                    String name = scanner.nextLine();
                    System.out.println("\nNhập số lượng ghế: ");
                    int quantities = scanner.nextInt();
                    CinemaRoom cinemaRoom = new CinemaRoom();
                    cinemaRoom.setCinema_room_name(name);
                    cinemaRoom.setSeat_quantity(quantities);

                    CinemaRoomDao cinemaRoomDao = new CinemaRoomDaoImpl();
                    if (cinemaRoomDao.insertCinemaRoom(cinemaRoom)) {
                        System.out.println("\n Thêm phòng thành công!");
                    } else {
                        System.out.println("\n Thêm phòng thất bại!");
                    }
                    break;
                case 2:
                    System.out.println("\nNhập id room: ");
                    int id_room = scanner.nextInt();
                    scanner.nextLine();
                    System.out.println("\n Nhập seat_column: ");
                    String seat_column = scanner.nextLine();
                    System.out.println("\n Nhập seat_row: ");
                    int seat_row = scanner.nextInt();
                    scanner.nextLine();
                    System.out.println("\n Nhập status: ");
                    String status = scanner.nextLine();
                    System.out.println("\n Nhập type: ");
                    String type = scanner.nextLine();

                    Seat seat = new Seat();
                    CinemaRoomDao cinemaRoomDao1 = new CinemaRoomDaoImpl();
                    CinemaRoom cinemaRoom1 = cinemaRoomDao1.findById(id_room);

                    seat.setCinema_room_id(cinemaRoom1);
                    seat.setSeat_column(seat_column);
                    seat.setSeat_row(seat_row);
                    seat.setSeat_status(status);
                    seat.setSeat_type(type);

                    SeatDao seatDao = new SeatDaoImpl();
                    if (seatDao.insertSeat(seat)) {
                        System.out.println("\n Thêm seat thành công!");
                    } else {
                        System.out.println("\n Thêm seat không thành công!");
                    }

                    break;
                case 3:
                    System.out.println("\n Nhập id_seat: ");
                    int id_seat = scanner.nextInt();
                    scanner.nextLine();
                    System.out.println("\n Nhập trạng thái muốn cập nhật: ");
                    String status1 = scanner.nextLine();

                    SeatDao seatDao1 = new SeatDaoImpl();
                    if (seatDao1.updateStatusById(id_seat, status1)) {
                        System.out.println("\n Cập nhật seat thành công!");
                    } else {
                        System.out.println("\n Cập nhật seat không thành công!");
                    }


                    break;
                case 4:
                    System.out.println("\n Nhập id seat muốn xóa: ");
                    int id_seat_delete = scanner.nextInt();
                    scanner.nextLine();
                    SeatDao seatDao2 = new SeatDaoImpl();
                    seatDao2.deleteById(id_seat_delete);

                    break;
                case 5:
                    System.out.println("\n Nhập trạng thái cần tìm: ");
                    String status_find = scanner.nextLine();
                    SeatDao seatDao3 = new SeatDaoImpl();
                    List<Seat> seats = seatDao3.getListSeatByStatus(status_find);
                    seats.forEach(seatEach -> {
                        System.out.println(seatEach.getSeat_id() + " - " +
                                seatEach.getCinema_room_id() + " - " +
                                seatEach.getSeat_column() + " - " +
                                seatEach.getSeat_row() + " - " +
                                seatEach.getSeat_status() + " - " +
                                seatEach.getSeat_type());
                    });

                    break;
            }
        }


    }


}
