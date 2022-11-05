package controller;

import entity.Candidate;
import io.vertx.core.http.HttpServerResponse;
import io.vertx.core.json.Json;
import io.vertx.ext.web.RoutingContext;
import service.CandidateService;
import service.impl.CandidateServiceImpl;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

public class Controller {
    CandidateService candidateService = new CandidateServiceImpl();

    public void insertCandidate(RoutingContext routingContext) {
        HttpServerResponse response = routingContext.response();
        response.putHeader("content-type","application/json;charset=UTF-8");

        try {
            Candidate candidate = Json.decodeValue(routingContext.getBody(), Candidate.class);
            if (candidateService.insertCandidate(candidate)) {
                response.end("true");
            } else {
                response.end("false");
            }

        } catch (Exception ex) {
            ex.printStackTrace();
        }

    }

    public void getBySkill(RoutingContext routingContext) {
        HttpServerResponse response = routingContext.response();
        response.putHeader("content-type","application/json;charset=UTF-8");

        String skill = routingContext.request().getParam("skill");

        try {
            List<Candidate> candidates = candidateService.getBySkill(skill);
            response.end(Json.encodePrettily(candidates));
        } catch (Exception ex) {
            ex.printStackTrace();
        }


    }

    public void getBySkillAndForeignLanguage(RoutingContext routingContext) {
        HttpServerResponse response = routingContext.response();
        response.putHeader("content-type","application/json;charset=UTF-8");

        String skill = routingContext.request().getParam("skill");
        String foreignLanguage = routingContext.request().getParam("foreignLanguage");

        try {
            List<Candidate> candidates = candidateService.getBySkillAndForeignLanguage(skill,foreignLanguage);
            response.end(Json.encodePrettily(candidates));
        } catch (Exception ex) {
            ex.printStackTrace();
        }


    }

    public void getBySkillAndETSkill(RoutingContext routingContext){

        try {
            HttpServerResponse response = routingContext.response();
            response.putHeader("content-type","application/json;charset=UTF-8");

            String skill = routingContext.request().getParam("skill");
            String etskill = routingContext.request().getParam("etskill");

            System.out.println("ok1");
            List<Candidate> candidates = candidateService.getBySkillAndETSkill(skill, etskill);
            System.out.println("ok2");
            response.end(Json.encodePrettily(candidates));
        } catch (Exception ex) {
            ex.printStackTrace();
        }



    }

    public void getByInterview(RoutingContext routingContext){
        HttpServerResponse response = routingContext.response();
        response.putHeader("content-type","application/json;charset=UTF-8");

        String interview = routingContext.request().getParam("interview");


        List<Candidate> candidates = candidateService.getByInterview(interview);
        response.end(Json.encodePrettily(candidates));

    }

    public void updateByPhoneEmailCv(RoutingContext routingContext) {
        HttpServerResponse response = routingContext.response();
        response.putHeader("content-type","application/json;charset=UTF-8");

        String phone = routingContext.request().getParam("phone");
        String email = routingContext.request().getParam("email");
        String cv = routingContext.request().getParam("cv");
        String remark = routingContext.request().getParam("remark");

        if (candidateService.updateByPhoneEmailCv(phone, email, cv, remark)) {
            response.end("true");
        } else {
            response.end("false");
        }

    }





}
