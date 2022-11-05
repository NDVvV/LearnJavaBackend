package service.impl;

import dao.CandidateDao;
import dao.impl.CandidateDaoImpl;
import entity.Candidate;
import service.CandidateService;

import java.util.Date;
import java.util.List;

public class CandidateServiceImpl implements CandidateService {
    CandidateDao candidateDao = new CandidateDaoImpl();

    @Override
    public boolean insertCandidate(Candidate candidate) {
        return candidateDao.insertCandidate(candidate);
    }

    @Override
    public List<Candidate> getBySkill(String skill) {
        return candidateDao.getBySkill(skill);
    }

    @Override
    public List<Candidate> getBySkillAndForeignLanguage(String skill, String foreignLanguage) {
        return candidateDao.getBySkillAndForeignLanguage(skill,foreignLanguage);
    }

    @Override
    public List<Candidate> getBySkillAndETSkill(String skill, String eTSkill) {
        return candidateDao.getBySkillAndETSkill(skill, eTSkill);
    }

    @Override
    public List<Candidate> getByInterview(String interview) {
        return candidateDao.getByInterview(interview);
    }

    @Override
    public boolean updateByPhoneEmailCv(String phone, String email, String cv, String remark) {
        return candidateDao.updateByPhoneEmailCv(phone, email, cv, remark);
    }
}
