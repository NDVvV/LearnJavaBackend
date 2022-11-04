package M.A101.main;

import M.A101.verticle.ServerVerticle;
import io.vertx.core.Vertx;

public class VertX {
    public static void main(String[] args) {

        Vertx vertx = Vertx.vertx();
        vertx.deployVerticle(new ServerVerticle());

    }
}
