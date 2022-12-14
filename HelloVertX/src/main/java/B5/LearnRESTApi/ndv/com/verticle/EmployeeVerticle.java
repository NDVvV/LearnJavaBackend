package B5.LearnRESTApi.ndv.com.verticle;

import B5.LearnRESTApi.ndv.com.model.Employee;
import io.vertx.core.AbstractVerticle;
import io.vertx.core.Promise;
import io.vertx.core.http.HttpServerResponse;
import io.vertx.core.json.Json;
import io.vertx.ext.web.Router;
import io.vertx.ext.web.RoutingContext;
import io.vertx.ext.web.handler.BodyHandler;

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

    private void getAllEmployees(RoutingContext routingContext) {
        HttpServerResponse response = routingContext.response();
        response.putHeader("content-type", "application/json;charset=UTF-8");
        response.end(Json.encodePrettily(employees.values()));
    }

    @Override
    public void start(Promise startPromise) throws Exception {
        createExampleData();
        Router router = Router.router(vertx);
        router.get("/api/employees").handler(this::getAllEmployees);
        router.get("/api/employeessort").handler(this::getSortEmployees);
        router.get("/api/employees/:id").handler(this::getOneEmployee);
        router.route("/api/employees*").handler(BodyHandler.create());
        router.post("/api/employees").handler(this::insertNewEmployee);
        router.put("/api/employees").handler(this::updateOneEmpoyee);
        router.delete("/api/employees/:id").handler(this::deleteOneEmployee);
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

    /**
     * H??m n??y d??ng ????? l???y danh s??ch d??? li???u Employee m?? c?? s???p x???p
     * http://localhost:8080/api/employeessort?sort=desc ->gi???m d???n
     * http://localhost:8080/api/employeessort?sort=asc ->t??ng d???n
     *
     * @param routingContext
     */
    private void getSortEmployees(RoutingContext routingContext) {
        HttpServerResponse response = routingContext.response();
        response.putHeader("content-type", "application/json;charset=UTF-8");
        //parameter l???y t??? ng?????i d??ng
        String sort = routingContext.request().getParam("sort");
        if (sort == null) {
            //n???u kh??ng c?? th?? cho l???i lu??n API
            routingContext.response().setStatusCode(400).end();
        } else {
            //d??ng ArrayList ????? l??u tr??? c??c Key c???a d??? li???u
            ArrayList sortedKeys =
                    new ArrayList(employees.keySet());
            //m???c ?????nh s???p x???p t??ng d???n c??c Key
            Collections.sort(sortedKeys);
            //n???u sort l?? desc (gi???m d???n)
            if (sort.equalsIgnoreCase("desc")) {
                //th?? ?????o ng?????c l???i danh s??ch ??ang t??ng d???n -> n?? t??? th??nh gi???m d???n
                Collections.reverse(sortedKeys);
            }
            //khai b??o danh s??ch Employee l?? ArrayList
            ArrayList sortEmployees = new ArrayList();
            //v??ng l???p theo Key
            for (Object key : sortedKeys) {
                //m???i l???n l???y employees.get(key) l?? ???? l???y t??ng d???n ho???c gi???m d???n (v?? key ???? s???p x???p)
                sortEmployees.add(employees.get(key));
            }
            //tr??? v??? danh s??ch ???? s???p x??p
            response.end(Json.encodePrettily(sortEmployees));
        }
    }

    //H??m tr??? v??? th??ng tin chi ti???t c???a 1 Employee khi bi???t Id c???a h???
    private void getOneEmployee(RoutingContext routingContext) {
        HttpServerResponse response = routingContext.response();
        response.putHeader("content-type", "application/json;charset=utf-8");
        //l???y id nh???p t??? URL
        String sid = routingContext.request().getParam("id");
        if (sid == null) {//n???u kh??ng t???n t???i th?? b??o l???i
            routingContext.response().setStatusCode(400).end();
        } else {
            //????a id ???? v??? s??? (v?? d??? li???u l???y t??? URL l?? chu???i
            int id = Integer.parseInt(sid);
            //tr??? v??? Empoyee c?? m?? l?? id
            Employee empFound = employees.get(id);
            //xu???t Json chi ti???t Employee ra cho client
            response.end(Json.encodePrettily(empFound));
        }
    }

    //H??m nh???n Json Object Employee ????? l??u v??o Server
    private void insertNewEmployee(RoutingContext routingContext) {
        HttpServerResponse response = routingContext.response();
        response.putHeader("content-type", "application/json;charset=UTF8");
        try {
            //routingContext.getBody() l???y d??? li???u t??? client g???i l??n, n?? c?? ?????nh d???ng Json
            //Json.decodeValue(routingContext.getBody(),Employee.class);->????a Json ???? v??? Employee
            Employee emp = Json.decodeValue(routingContext.getBody(), Employee.class);
            //????a v??o HashMap
            employees.put(emp.getId(), emp);
            //xu???t k???t qu??? l?? true xu???ng cho client n???u l??u th??nh c??ng
            response.end("true");
        } catch (Exception ex) {
            response.end(ex.getMessage());//l??u th???t b???i (kh??c true)
        }
    }

    /**
     * H??m c???p nh???t d??? li???u
     *
     * @param routingContext
     */
    private void updateOneEmpoyee(RoutingContext routingContext) {
        HttpServerResponse response = routingContext.response();
        response.putHeader("content-type", "application/json;charset=UTF8");
        //routingContext.getBody() l???y d??? li???u l?? c???u tr??c Json t??? client
        //Json.decodeValue(routingContext.getBody(),Employee.class); m?? h??nh h??a l?? Java model
        Employee emp = Json.decodeValue(routingContext.getBody(), Employee.class);
        //ki???m tra id t???n t???i kh??ng
        if (employees.containsKey(emp.getId())) {
            employees.put(emp.getId(), emp);//n???u t???n t???i th?? ch???nh s???a
            response.end("true");//tr??? v??? true khi ch???nh s???a th??nh c??ng
        } else
            response.end("false");//tr??? v??? false khi ch???nh s???a th???t b???i
    }

    /**
     * H??m n??y d??ng ????? x??a m???t Employee khi bi???t Id
     *
     * @param routingContext
     */
    private void deleteOneEmployee(RoutingContext routingContext) {
        HttpServerResponse response = routingContext.response();
        response.putHeader("content-type", "application/json;charset=UTF-8");
        //l???y id t??? client
        String sid = routingContext.request().getParam("id");
        //????a v??? int
        int id = Integer.parseInt(sid);
        //ki???m tra id t???n t???i hay kh??ng
        if (employees.containsKey(id)) {
            employees.remove(id);//c?? th?? x??a
            response.end("true");//x??a th??nh c??ng tr??? v??? true
        } else
            response.end("false");//kh??ng t??m th???y tr??? v??? false
    }
}
