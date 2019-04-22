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
@Table(name = "public.\"Class\"")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Class.findAll", query = "SELECT c FROM Class c")
    , @NamedQuery(name = "Class.findByClassId", query = "SELECT c FROM Class c WHERE c.classId = :classId")
    , @NamedQuery(name = "Class.findByDescription", query = "SELECT c FROM Class c WHERE c.description = :description")
    , @NamedQuery(name = "Class.findByKarma", query = "SELECT c FROM Class c WHERE c.karma = :karma")
    , @NamedQuery(name = "Class.findByState", query = "SELECT c FROM Class c WHERE c.state = :state")})
public class Class implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "class_id")
    private Integer classId;
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
    @JoinColumn(name = "topic_id", referencedColumnName = "topic_id")
    @ManyToOne(optional = false)
    private Topic topicId;

    public Class() {
    }

    public Class(Integer classId) {
        this.classId = classId;
    }

    public Class(Integer classId, String description, int karma) {
        this.classId = classId;
        this.description = description;
        this.karma = karma;
    }

    public void setAll(Class classx){
        this.description=classx.getDescription();
        this.karma=classx.karma;
        this.state=classx.state;
        this.teacherEmail=classx.teacherEmail;
        this.topicId=classx.topicId;
    }
    public Integer getClassId() {
        return classId;
    }

    public void setClassId(Integer classId) {
        this.classId = classId;
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

    public Topic getTopicId() {
        return topicId;
    }

    public void setTopicId(Topic topicId) {
        this.topicId = topicId;
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

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (classId != null ? classId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Class)) {
            return false;
        }
        Class other = (Class) object;
        if ((this.classId == null && other.classId != null) || (this.classId != null && !this.classId.equals(other.classId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.mycompany.tamboserver.exceptions.Class[ classId=" + classId + " ]";
    }
    
}
