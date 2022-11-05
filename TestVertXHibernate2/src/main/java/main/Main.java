package main;

import io.vertx.core.Vertx;
import verticle.ServiceVerticle;

public class Main {
    public static void main(String[] args) {
        Vertx vertx = Vertx.vertx();
        vertx.deployVerticle(new ServiceVerticle());
    }
}
