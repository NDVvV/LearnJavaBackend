package verticle;

import controller.Controller;
import io.vertx.core.AbstractVerticle;
import io.vertx.core.http.HttpServerOptions;
import io.vertx.ext.web.Router;
import io.vertx.ext.web.handler.BodyHandler;

public class ServiceVerticle extends AbstractVerticle {
    Controller controller = new Controller();

    @Override
    public void start() throws Exception {
        Router router = Router.router(vertx);
        router.route("/api/candidate*").handler(BodyHandler.create());
        router.post("/api/candidate").handler(controller::insertCandidate);
        router.get("/api/candidate/skill").handler(controller::getBySkill);
        router.get("/api/candidate/skillandfl").handler(controller::getBySkillAndForeignLanguage);
        router.get("/api/candidate/skillandetskill").handler(controller::getBySkillAndETSkill);
        router.get("/api/candidate/interview").handler(controller::getByInterview);
        router.put("/api/candidate").handler(controller::updateByPhoneEmailCv);

        HttpServerOptions httpServerOptions = new HttpServerOptions();
        httpServerOptions.setCompressionSupported(true);
        vertx
                .createHttpServer(httpServerOptions)
                .requestHandler(router)
                .listen( 8080);
    }
}
