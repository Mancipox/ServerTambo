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
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author usuario
 */
@Entity
@Table(name = "\"Class_teacher\"", schema = "public")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Classteacher.findAll", query = "SELECT c FROM Classteacher c")
    , @NamedQuery(name = "Classteacher.findByCtId", query = "SELECT c FROM Classteacher c WHERE c.ctId = :ctId")})
public class Classteacher implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "ct_id")
    private Integer ctId;
    @JoinColumn(name = "class_id", referencedColumnName = "class_id")
    @ManyToOne(
    cascade = {CascadeType.PERSIST, CascadeType.REMOVE})
    private Class classId;
    @JoinColumn(name = "teacher_email", referencedColumnName = "email")
    @ManyToOne
    private User teacherEmail;

    public Classteacher() {
    }

    public Classteacher(Integer ctId) {
        this.ctId = ctId;
    }

    public Classteacher(Class classId, User teacherEmail) {
        this.classId = classId;
        this.teacherEmail = teacherEmail;
    }

    public Integer getCtId() {
        return ctId;
    }

    public void setCtId(Integer ctId) {
        this.ctId = ctId;
    }

    public Class getClassId() {
        return classId;
    }

    public void setClassId(Class classId) {
        this.classId = classId;
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
        hash += (ctId != null ? ctId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Classteacher)) {
            return false;
        }
        Classteacher other = (Classteacher) object;
        if ((this.ctId == null && other.ctId != null) || (this.ctId != null && !this.ctId.equals(other.ctId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.mycompany.tamboserver.exceptions.Classteacher[ ctId=" + ctId + " ]";
    }

    public void setAll(Contact contact) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
