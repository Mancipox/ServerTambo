/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tambo.model.VO;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author usuario
 */
@Entity
@Table(name = "public.\"Question\"")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Question.findAll", query = "SELECT q FROM Question q")
    , @NamedQuery(name = "Question.findByQuestionId", query = "SELECT q FROM Question q WHERE q.questionId = :questionId")
    , @NamedQuery(name = "Question.findByDescription", query = "SELECT q FROM Question q WHERE q.description = :description")
    , @NamedQuery(name = "Question.findByKarma", query = "SELECT q FROM Question q WHERE q.karma = :karma")
    , @NamedQuery(name = "Question.findByState", query = "SELECT q FROM Question q WHERE q.state = :state")})
public class Question implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "question_id")
    private Integer questionId;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 255)
    @Column(name = "description")
    private String description;
    @Basic(optional = false)
    @NotNull
    @Column(name = "karma")
    private int karma;
    @Column(name = "state")
    private Boolean state;
    @JoinColumn(name = "meeting_id", referencedColumnName = "meeting_id")
    @OneToOne(cascade=CascadeType.PERSIST)
    private Meeting meetingId;
    @JoinColumn(name = "student_email", referencedColumnName = "email")
    @ManyToOne(optional = false)
    private User studentEmail;
    @JoinColumn(name = "teacher_email", referencedColumnName = "email")
    @ManyToOne(optional = false)
    private User teacherEmail;

    public Question() {
    }

    public Question(Integer questionId) {
        this.questionId = questionId;
    }

    public Question(Integer questionId, String description, int karma) {
        this.questionId = questionId;
        this.description = description;
        this.karma = karma;
    }

    public Integer getQuestionId() {
        return questionId;
    }

    public void setQuestionId(Integer questionId) {
        this.questionId = questionId;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getKarma() {
        return karma;
    }

    public void setKarma(int karma) {
        this.karma = karma;
    }

    public Boolean getState() {
        return state;
    }

    public void setState(Boolean state) {
        this.state = state;
    }

    public Meeting getMeetingId() {
        return meetingId;
    }

    public void setMeetingId(Meeting meetingId) {
        this.meetingId = meetingId;
    }

    public User getStudentEmail() {
        return studentEmail;
    }

    public void setStudentEmail(User studentEmail) {
        this.studentEmail = studentEmail;
    }

    public User getTeacherEmail() {
        return teacherEmail;
    }

    public void setTeacherEmail(User teacherEmail) {
        this.teacherEmail = teacherEmail;
    }
    public void setAll(Question question){
        this.description=question.getDescription();
        this.karma=question.karma;
        this.state=question.state;
        this.teacherEmail=question.teacherEmail;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (questionId != null ? questionId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Question)) {
            return false;
        }
        Question other = (Question) object;
        if ((this.questionId == null && other.questionId != null) || (this.questionId != null && !this.questionId.equals(other.questionId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.tambo.model.VO.Question[ questionId=" + questionId + " ]";
    }
    
}