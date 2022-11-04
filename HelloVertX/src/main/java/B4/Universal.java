package B4;

import B5.LearnRESTApi.ndv.com.verticle.EmployeeVerticle;
import io.vertx.core.Vertx;

public class Universal {
    public static void main(String[] args) {
        Vertx vertx = Vertx.vertx();
        vertx.deployVerticle(new EmployeeVerticle());
    }
}
