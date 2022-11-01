package B4;

import io.vertx.core.AbstractVerticle;
import io.vertx.core.Promise;
import io.vertx.core.json.Json;
import io.vertx.ext.web.Router;
import io.vertx.ext.web.RoutingContext;

public class MonHocVerticle extends AbstractVerticle {
    @Override
    public void start(Promise<Void> startPromise) throws Exception {
        Router router=Router.router(vertx);
        router.get("/api/monhoc").handler(this::danhSachMonHoc);
        vertx.createHttpServer()
                .requestHandler(router::accept)
                .listen(config().getInteger("http.port",8080),
                        result->{
                            if(result.succeeded())
                            {
                                startPromise.complete();
                            }
                            else
                            {
                                startPromise.fail(result.cause());
                            }
                        }
                );
    }

    private void danhSachMonHoc(RoutingContext routingContext) {
        String[] arrMonHoc = {"C# cơ bản",
                            "Java cơ bản",
                            "Java nâng cao",
                            "Java Backend"};
        routingContext.response().putHeader("content-type",
                "application/json:charset=utf-8").end(Json.encodePrettily(arrMonHoc));
    }
}
