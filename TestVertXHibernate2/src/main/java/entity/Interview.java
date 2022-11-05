package entity;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@Entity
public class Interview implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "interview_id")
    private int interviewId;
    private String time;
    private Date date;
    private String interviewer;
    private String comments;
    @Column(name = "interview_result")
    private String interviewResult;
    private String remark;
    @ManyToOne
    @JoinColumn(name = "candidate_id")
    private Candidate candidateId;

    public Interview() {
    }

    public int getInterviewId() {
        return interviewId;
    }

    public void setInterviewId(int interviewId) {
        this.interviewId = interviewId;
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

    public String getInterviewer() {
        return interviewer;
    }

    public void setInterviewer(String interviewer) {
        this.interviewer = interviewer;
    }

    public String getComments() {
        return comments;
    }

    public void setComments(String comments) {
        this.comments = comments;
    }

    public String getInterviewResult() {
        return interviewResult;
    }

    public void setInterviewResult(String interviewResult) {
        this.interviewResult = interviewResult;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public Candidate getCandidateId() {
        return candidateId;
    }

    public void setCandidateId(Candidate candidateId) {
        this.candidateId = candidateId;
    }
}
