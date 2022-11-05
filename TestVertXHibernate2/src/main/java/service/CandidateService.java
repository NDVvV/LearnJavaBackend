package service;

import entity.Candidate;

import java.util.Date;
import java.util.List;

public interface CandidateService {
    boolean insertCandidate(Candidate candidate);
    List<Candidate> getBySkill (String skill);
    List<Candidate> getBySkillAndForeignLanguage (String skill, String foreignLanguage);
    List<Candidate> getBySkillAndETSkill (String skill, String eTSkill);
    List<Candidate> getByInterview (String interview);
    boolean updateByPhoneEmailCv (String phone, String email, String cv, String remark);
}
