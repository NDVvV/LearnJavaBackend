package dao.impl;

import dao.CandidateDao;
import entity.Candidate;
import entity.EntryTest;
import entity.Interview;
import org.hibernate.Session;
import org.hibernate.Transaction;
import pojo.HibernateUtils;

import javax.persistence.criteria.*;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

public class CandidateDaoImpl implements CandidateDao {
    @Override
    public boolean insertCandidate(Candidate candidate) {
        int id = 0;
        Session session = HibernateUtils.getFactory().openSession();
        Transaction transaction = session.getTransaction();

        try {
            transaction.begin();
            id = (int) session.save(candidate);
        } catch (Exception ex) {
            transaction.rollback();
            ex.printStackTrace();
        } finally {
            transaction.commit();
            session.close();
        }
        return id != 0;
    }

    @Override
    public List<Candidate> getBySkill(String skill) {
        Session session = HibernateUtils.getFactory().openSession();

        CriteriaBuilder builder = session.getCriteriaBuilder();
        CriteriaQuery<Candidate> query = builder.createQuery(Candidate.class);
        Root<Candidate> cRoot = query.from(Candidate.class);

        List<Candidate> candidates = null;

        if (!skill.isEmpty()) {
            Predicate p1 = builder.like(cRoot.get("skill").as(String.class),"%" + skill + "%");
            query = query.where(p1);
            candidates = session.createQuery(query).getResultList();
        }
        session.close();
        return candidates;
    }

    @Override
    public List<Candidate> getBySkillAndForeignLanguage(String skill, String foreignLanguage) {
        Session session = HibernateUtils.getFactory().openSession();

        CriteriaBuilder builder = session.getCriteriaBuilder();
        CriteriaQuery<Candidate> query = builder.createQuery(Candidate.class);
        Root<Candidate> cRoot = query.from(Candidate.class);

        List<Candidate> candidates = null;
        query.select(cRoot);

        if (!skill.isEmpty() && !foreignLanguage.isEmpty()) {
            String skillFind = String.format("%%%s%%",skill);
            String foreignLanguageFind = String.format("%%%s%%",foreignLanguage);
            Predicate p1 = builder.like(cRoot.get("skill").as(String.class),skillFind);
            Predicate p2 = builder.like(cRoot.get("foreignLanguage").as(String.class),foreignLanguageFind);
            query = query.where(builder.and(p1,p2));
            candidates = session.createQuery(query).getResultList();
        }
        session.close();
        return candidates;
    }

    @Override
    public List<Candidate> getBySkillAndETSkill(String skill, String eTSkill) {
        Session session = HibernateUtils.getFactory().openSession();

        CriteriaBuilder builder = session.getCriteriaBuilder();
        CriteriaQuery<Candidate> query = builder.createQuery(Candidate.class);
        Root<Candidate> cRoot = query.from(Candidate.class);
        Join<Candidate, EntryTest> candidateEntryTestJoin = cRoot.join("entryTests");

        List<Candidate> candidatee = null;
        try {

            query.select(cRoot);
            Predicate p1 = builder.equal(cRoot.get("skill"), skill);
            Predicate p2 = builder.equal(candidateEntryTestJoin.get("entryTestSkill"), eTSkill);
            query = query.where(builder.and(p1, p2));

            candidatee= session.createQuery(query).getResultList();

            session.close();

        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return candidatee;

    }

    @Override
    public List<Candidate> getByInterview(String interview) {
        Session session = HibernateUtils.getFactory().openSession();

        CriteriaBuilder builder = session.getCriteriaBuilder();
        CriteriaQuery<Candidate> query = builder.createQuery(Candidate.class);
        Root<Candidate> cRoot = query.from(Candidate.class);

        Join<Candidate, Interview> iRoot = cRoot.join("interviews");
        query.select(cRoot);
        List<Candidate> candidates = null;
        try {
            Predicate p1 = builder.equal(iRoot.get("interviewer"), interview);
            query = query.where(p1);

            candidates = session.createQuery(query).getResultList();
        } catch (Exception ex) {
            ex.printStackTrace();
        }



        session.close();
        return candidates;
    }

    @Override
    public boolean updateByPhoneEmailCv(String phone, String email, String cv, String remark) {
        AtomicInteger result = new AtomicInteger(0);
        Session session = HibernateUtils.getFactory().openSession();
        Transaction transaction = session.getTransaction();

        CriteriaBuilder builder = session.getCriteriaBuilder();
        CriteriaQuery<Candidate> query = builder.createQuery(Candidate.class);
        Root<Candidate> cRoot = query.from(Candidate.class);

        Predicate p1 = builder.equal(cRoot.get("phone"), phone);
        Predicate p2 = builder.equal(cRoot.get("email"), email);
        Predicate p3 = builder.equal(cRoot.get("cv"), cv);

        query.where(builder.and(p1, p2, p3));
        query.select(cRoot);
        try {
            List<Candidate> candidates = session.createQuery(query).getResultList();
            candidates.forEach(candidate -> {
                result.set(1);
                candidate.setRemark(remark);
                transaction.begin();
                session.save(candidate);
                transaction.commit();
            });



        } catch (Exception ex) {
            transaction.rollback();
            ex.printStackTrace();
        }


        session.close();

        return result.get() != 0;
    }
}
