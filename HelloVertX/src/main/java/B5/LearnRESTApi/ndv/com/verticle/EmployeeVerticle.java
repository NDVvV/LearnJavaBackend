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
     * Hàm này dùng để lấy danh sách dữ liệu Employee mà có sắp xếp
     * http://localhost:8080/api/employeessort?sort=desc ->giảm dần
     * http://localhost:8080/api/employeessort?sort=asc ->tăng dần
     *
     * @param routingContext
     */
    private void getSortEmployees(RoutingContext routingContext) {
        HttpServerResponse response = routingContext.response();
        response.putHeader("content-type", "application/json;charset=UTF-8");
        //parameter lấy từ người dùng
        String sort = routingContext.request().getParam("sort");
        if (sort == null) {
            //nếu không có thì cho lỗi luôn API
            routingContext.response().setStatusCode(400).end();
        } else {
            //dùng ArrayList để lưu trữ các Key của dữ liệu
            ArrayList sortedKeys =
                    new ArrayList(employees.keySet());
            //mặc định sắp xếp tăng dần các Key
            Collections.sort(sortedKeys);
            //nếu sort là desc (giảm dần)
            if (sort.equalsIgnoreCase("desc")) {
                //thì đảo ngược lại danh sách đang tăng dần -> nó tự thành giảm dần
                Collections.reverse(sortedKeys);
            }
            //khai báo danh sách Employee là ArrayList
            ArrayList sortEmployees = new ArrayList();
            //vòng lặp theo Key
            for (Object key : sortedKeys) {
                //mỗi lần lấy employees.get(key) là đã lấy tăng dần hoặc giảm dần (vì key đã sắp xếp)
                sortEmployees.add(employees.get(key));
            }
            //trả về danh sách đã sắp xêp
            response.end(Json.encodePrettily(sortEmployees));
        }
    }

    //Hàm trả về thông tin chi tiết của 1 Employee khi biết Id của họ
    private void getOneEmployee(RoutingContext routingContext) {
        HttpServerResponse response = routingContext.response();
        response.putHeader("content-type", "application/json;charset=utf-8");
        //lấy id nhập từ URL
        String sid = routingContext.request().getParam("id");
        if (sid == null) {//nếu không tồn tại thì báo lỗi
            routingContext.response().setStatusCode(400).end();
        } else {
            //đưa id đó về số (vì dữ liệu lấy từ URL là chuỗi
            int id = Integer.parseInt(sid);
            //trả về Empoyee có mã là id
            Employee empFound = employees.get(id);
            //xuất Json chi tiết Employee ra cho client
            response.end(Json.encodePrettily(empFound));
        }
    }

    //Hàm nhận Json Object Employee để lưu vào Server
    private void insertNewEmployee(RoutingContext routingContext) {
        HttpServerResponse response = routingContext.response();
        response.putHeader("content-type", "application/json;charset=UTF8");
        try {
            //routingContext.getBody() lấy dữ liệu từ client gửi lên, nó có định dạng Json
            //Json.decodeValue(routingContext.getBody(),Employee.class);->đưa Json đó về Employee
            Employee emp = Json.decodeValue(routingContext.getBody(), Employee.class);
            //đưa vào HashMap
            employees.put(emp.getId(), emp);
            //xuất kết quả là true xuống cho client nếu lưu thành công
            response.end("true");
        } catch (Exception ex) {
            response.end(ex.getMessage());//lưu thất bại (khác true)
        }
    }

    /**
     * Hàm cập nhật dữ liệu
     *
     * @param routingContext
     */
    private void updateOneEmpoyee(RoutingContext routingContext) {
        HttpServerResponse response = routingContext.response();
        response.putHeader("content-type", "application/json;charset=UTF8");
        //routingContext.getBody() lấy dữ liệu là cấu trúc Json từ client
        //Json.decodeValue(routingContext.getBody(),Employee.class); mô hình hóa là Java model
        Employee emp = Json.decodeValue(routingContext.getBody(), Employee.class);
        //kiểm tra id tồn tại không
        if (employees.containsKey(emp.getId())) {
            employees.put(emp.getId(), emp);//nếu tốn tại thì chỉnh sửa
            response.end("true");//trả về true khi chỉnh sửa thành công
        } else
            response.end("false");//trả về false khi chỉnh sửa thất bại
    }

    /**
     * Hàm này dùng để xóa một Employee khi biết Id
     *
     * @param routingContext
     */
    private void deleteOneEmployee(RoutingContext routingContext) {
        HttpServerResponse response = routingContext.response();
        response.putHeader("content-type", "application/json;charset=UTF-8");
        //lấy id từ client
        String sid = routingContext.request().getParam("id");
        //đưa về int
        int id = Integer.parseInt(sid);
        //kiểm tra id tồn tại hay không
        if (employees.containsKey(id)) {
            employees.remove(id);//có thì xóa
            response.end("true");//xóa thành công trả về true
        } else
            response.end("false");//không tìm thấy trả về false
    }
}
