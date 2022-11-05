package entity;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@Entity
@Table(name = "entry_test")
public class EntryTest implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "test_id")
    private int testId;
    private String time;
    private Date date;
    @Column(name = "langduage_valuator")
    private String languageValuator;
    @Column(name = "language_result")
    private Float languageResult;
    @Column(name = "technical_valuator")
    private String technicalValuator;
    @Column(name = "technical_result")
    private Float technicalResult;
    private String result;
    private String remark;
    @Column(name = "entry_test_skill")
    private String entryTestSkill;
    @ManyToOne
    @JoinColumn(name = "candidate_id")
    private Candidate candidateId;

    public EntryTest() {
    }

    public int getTestId() {
        return testId;
    }

    public void setTestId(int testId) {
        this.testId = testId;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String getLanguageValuator() {
        return languageValuator;
    }

    public void setLanguageValuator(String languageValuator) {
        this.languageValuator = languageValuator;
    }

    public Float getLanguageResult() {
        return languageResult;
    }

    public void setLanguageResult(Float languageResult) {
        this.languageResult = languageResult;
    }

    public String getTechnicalValuator() {
        return technicalValuator;
    }

    public void setTechnicalValuator(String technicalValuator) {
        this.technicalValuator = technicalValuator;
    }

    public Float getTechnicalResult() {
        return technicalResult;
    }

    public void setTechnicalResult(Float technicalResult) {
        this.technicalResult = technicalResult;
    }

    public String getResult() {
        return result;
    }

    public void setResult(String result) {
        this.result = result;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public String getEntryTestSkill() {
        return entryTestSkill;
    }

    public void setEntryTestSkill(String entryTestSkill) {
        this.entryTestSkill = entryTestSkill;
    }

    public Candidate getCandidateId() {
        return candidateId;
    }

    public void setCandidateId(Candidate candidateId) {
        this.candidateId = candidateId;
    }
}
