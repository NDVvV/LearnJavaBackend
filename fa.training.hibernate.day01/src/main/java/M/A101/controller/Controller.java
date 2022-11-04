package M.A101.controller;

import M.A101.entity.CinemaRoom;
import M.A101.entity.Seat;
import M.A101.service.CinemaRoomService;
import M.A101.service.SeatService;
import M.A101.service.impl.CinemaRoomServiceImpl;
import M.A101.service.impl.SeatServiceImpl;
import io.vertx.core.http.HttpServerResponse;
import io.vertx.core.json.Json;
import io.vertx.ext.web.RoutingContext;

import java.security.cert.Extension;
import java.util.ArrayList;
import java.util.List;

public class Controller {
    CinemaRoomService cinemaRoomService = new CinemaRoomServiceImpl();
    SeatService seatService = new SeatServiceImpl();

    public void insertCinemaRoom(RoutingContext routingContext) {
        HttpServerResponse response = routingContext.response();
        response.putHeader("content-type", "application/json;charset=UTF8");

        try {
            CinemaRoom cinemaRoom = Json.decodeValue(routingContext.getBody(), CinemaRoom.class);
            if (cinemaRoomService.insertCinemaRoom(cinemaRoom)) {
                response.end("true");
            } else {
                response.end("false");
            }

        } catch (Exception ex) {
            response.end(ex.getMessage());

        }
    }

    public void insertSeat(RoutingContext routingContext) {
        HttpServerResponse response = routingContext.response();
        response.putHeader("content-type", "application/json;charset=UTF-8");

        String idCinemaRoom = routingContext.request().getParam("id");
        try {
            Seat seat = Json.decodeValue(routingContext.getBody(), Seat.class);
            seat.setCinema_room_id(cinemaRoomService.findById(Integer.parseInt(idCinemaRoom)));
            if (seatService.insertSeat(seat)) {
                response.end("true");
            } else {
                response.end("false");
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    public void updateStatusById(RoutingContext routingContext) {
        HttpServerResponse response = routingContext.response();
        response.putHeader("content-type", "application/json;charset=UTF-8");

        String idSeatUpdate = routingContext.request().getParam("id");
        String status = routingContext.request().getParam("status");
        if (seatService.updateStatusById(Integer.parseInt(idSeatUpdate), status)) {
            response.end("true");
        } else {
            response.end("false");
        }
    }

    public void deleteById(RoutingContext routingContext) {
        HttpServerResponse response = routingContext.response();
        response.putHeader("content-type", "application/json;charset=UTF-8");

        String idSeat = routingContext.request().getParam("id");
        seatService.deleteById(Integer.parseInt(idSeat));
        response.end("true");
    }

    public void getByStatus(RoutingContext routingContext) {
        HttpServerResponse response = routingContext.response();
        response.putHeader("content-type", "application/json;charset=UTF-8");

        String status = routingContext.request().getParam("status");
        System.out.println("status: " + status);
        List<Seat> seats = seatService.getListSeatByStatus(status);
//        seats.forEach(s ->{
//            System.out.println("Seats: " + s.getSeat_id());
//        });
//        ArrayList listSeat = new ArrayList<>();
//        seats.forEach(s ->{
//            listSeat.add(s);
//        });

        response.end(Json.encodePrettily(seats));

    }


}
