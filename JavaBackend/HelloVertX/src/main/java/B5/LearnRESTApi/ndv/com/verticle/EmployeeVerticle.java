package B5.LearnRESTApi.ndv.com.verticle;

import B5.LearnRESTApi.ndv.com.model.Employee;
import io.vertx.core.AbstractVerticle;
import io.vertx.core.Promise;
import io.vertx.core.http.HttpServerResponse;
import io.vertx.core.json.Json;
import io.vertx.ext.web.Router;
import io.vertx.ext.web.RoutingContext;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;

public class EmployeeVerticle extends AbstractVerticle {
    private HashMap<Integer, Employee> employees = new HashMap<>();

    public void createExampleData() {
        employees.put(1, new Employee(1, "Viet1", "viet1@gmail.com"));
        employees.put(2, new Employee(2, "Viet2", "viet2@gmail.com"));
        employees.put(3, new Employee(3, "Viet3", "viet3@gmail.com"));
    }

    private void getSortEmployees(RoutingContext routingContext) {
        HttpServerResponse response = routingContext.response();
        response.putHeader("content-type", "application/json;charset=UTF-8");

        String sort = routingContext.request().getParam("sort");
        if (sort == null) {
            routingContext.response().setStatusCode(400).end();
        } else {
            ArrayList sortKey = new ArrayList(employees.keySet());
            Collections.sort(sortKey);

            if (sort.equalsIgnoreCase("desc")) {
                Collections.reverse(sortKey);
            }
            ArrayList sortEmloyees = new ArrayList();

            for (Object key : sortKey) {
                sortEmloyees.add(employees.get(key));
            }
            response.end(Json.encodePrettily(sortEmloyees));
        }
    }

    private void getAllEmployees(RoutingContext routingContext) {
        HttpServerResponse response = routingContext.response();
        response.putHeader("content-type", "application/json;charset=UTF-8");
        response.end(Json.encodePrettily(employees.values()));
    }

    @Override
    public void start(Promise<Void> startPromise) throws Exception {
        createExampleData();
        Router router = Router.router(vertx);
        router.get("/api/employees").handler(this::getAllEmployees);
        router.get("/api/employeessort").handler(this::getSortEmployees);
        vertx
                .createHttpServer()
                .requestHandler(router::accept)
                .listen(config().getInteger("http.port", 8080),
                        result -> {
                            if (result.succeeded()) {
                                startPromise.complete();
                            } else {
                                startPromise.fail(result.cause());
                            }
                        }
                );
    }
}
