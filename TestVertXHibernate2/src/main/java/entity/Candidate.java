package entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;
import java.util.Set;

@Entity
public class Candidate implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @JoinColumn(name = "candidate_id")
    private int candidateId;
    @Column(name = "full_name")
    private String fullName;
    @Column(name = "date_of_bỉth")
    private Date dateOfBirth;
    private int gender;
    @Column(name = "graduation_year")
    private Date graduationYear;
    private String phone;
    private String email;
    private String skill;
    @Column(name = "foreign_language")
    private String foreignLanguage;
    private int level;
    private String cv;
    @Column(name = "allocation_status")
    private int allocationStatus;
    private String remark;

    @OneToMany(mappedBy = "candidateId", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JsonIgnoreProperties("candidateId")
    private Set<Interview> interviews;

    @OneToMany(mappedBy = "candidateId", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JsonIgnoreProperties("candidateId")
    private Set<EntryTest> entryTests;

    public Candidate() {
    }

    public int getCandidateId() {
        return candidateId;
    }

    public void setCandidateId(int candidateId) {
        this.candidateId = candidateId;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public Date getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(Date dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    public int getGender() {
        return gender;
    }

    public void setGender(int gender) {
        this.gender = gender;
    }

    public Date getGraduationYear() {
        return graduationYear;
    }

    public void setGraduationYear(Date graduationYear) {
        this.graduationYear = graduationYear;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getSkill() {
        return skill;
    }

    public void setSkill(String skill) {
        this.skill = skill;
    }

    public String getForeignLanguage() {
        return foreignLanguage;
    }

    public void setForeignLanguage(String foreignLanguage) {
        this.foreignLanguage = foreignLanguage;
    }

    public int getLevel() {
        return level;
    }

    public void setLevel(int level) {
        this.level = level;
    }

    public String getCv() {
        return cv;
    }

    public void setCv(String cv) {
        this.cv = cv;
    }

    public int getAllocationStatus() {
        return allocationStatus;
    }

    public void setAllocationStatus(int allocationStatus) {
        this.allocationStatus = allocationStatus;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public Set<Interview> getInterviews() {
        return interviews;
    }

    public void setInterviews(Set<Interview> interviews) {
        this.interviews = interviews;
    }

    public Set<EntryTest> getEntryTests() {
        return entryTests;
    }

    public void setEntryTests(Set<EntryTest> entryTests) {
        this.entryTests = entryTests;
    }
}
