package M.A101.verticle;

import M.A101.controller.Controller;
import io.vertx.core.AbstractVerticle;
import io.vertx.core.http.HttpServerOptions;
import io.vertx.ext.web.Router;
import io.vertx.ext.web.handler.BodyHandler;

public class ServerVerticle extends AbstractVerticle {
    Controller controller = new Controller();

    @Override
    public void start() throws Exception {
        Router router = Router.router(vertx);
        router.route("/api/cinemaRoom*").handler(BodyHandler.create());
        router.post("/api/cinemaRoom").handler(controller::insertCinemaRoom);
        router.post("/api/cinemaRoom/:id/seat").handler(controller::insertSeat);
        router.put("/api/cinemaRoom/seat/:id").handler(controller::updateStatusById);
        router.delete("/api/cinemaRoom/seat/:id").handler(controller::deleteById);
        router.get("/api/cinemaRoom/seat").handler(controller::getByStatus);

        HttpServerOptions httpServerOptions = new HttpServerOptions();
        httpServerOptions.setCompressionSupported(true);
        vertx
                .createHttpServer(httpServerOptions)
                .requestHandler(router)
                .listen( 8080);
    }
}
