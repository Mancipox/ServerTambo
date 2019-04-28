/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tambo.model.VO;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 *
 * @author usuario
 */
@Embeddable
public class ContactPK implements Serializable {

    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 255)
    @Column(name = "teacher_email")
    private String teacherEmail;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 255)
    @Column(name = "student_email")
    private String studentEmail;

    public ContactPK() {
    }

    public ContactPK(String teacherEmail, String studentEmail) {
        this.teacherEmail = teacherEmail;
        this.studentEmail = studentEmail;
    }

    public String getTeacherEmail() {
        return teacherEmail;
    }

    public void setTeacherEmail(String teacherEmail) {
        this.teacherEmail = teacherEmail;
    }

    public String getStudentEmail() {
        return studentEmail;
    }

    public void setStudentEmail(String studentEmail) {
        this.studentEmail = studentEmail;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (teacherEmail != null ? teacherEmail.hashCode() : 0);
        hash += (studentEmail != null ? studentEmail.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof ContactPK)) {
            return false;
        }
        ContactPK other = (ContactPK) object;
        if ((this.teacherEmail == null && other.teacherEmail != null) || (this.teacherEmail != null && !this.teacherEmail.equals(other.teacherEmail))) {
            return false;
        }
        if ((this.studentEmail == null && other.studentEmail != null) || (this.studentEmail != null && !this.studentEmail.equals(other.studentEmail))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.mycompany.tamboserver.exceptions.ContactPK[ teacherEmail=" + teacherEmail + ", studentEmail=" + studentEmail + " ]";
    }
    
}
