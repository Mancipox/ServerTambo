/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tambo.model.VO;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
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
@Table(name = "\"Contact\"", schema = "public")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Contact.findAll", query = "SELECT c FROM Contact c")
    , @NamedQuery(name = "Contact.findByTeacherEmail", query = "SELECT c FROM Contact c WHERE c.contactPK.teacherEmail = :teacherEmail")
    , @NamedQuery(name = "Contact.findByStudentEmail", query = "SELECT c FROM Contact c WHERE c.contactPK.studentEmail = :studentEmail")
    , @NamedQuery(name = "Contact.findByNumber", query = "SELECT c FROM Contact c WHERE c.number = :number")})
public class Contact implements Serializable {

    private static final long serialVersionUID = 1L;
    @EmbeddedId
    protected ContactPK contactPK;
    @Basic(optional = false)
    @Column(name = "number")
    private int number;
    @JoinColumn(name = "student_email", referencedColumnName = "email", insertable = false, updatable = false)
    @ManyToOne(optional = false)
    private User user;
    @JoinColumn(name = "teacher_email", referencedColumnName = "email", insertable = false, updatable = false)
    @ManyToOne(optional = false)
    private User user1;

    public Contact() {
    }

    public Contact(ContactPK contactPK) {
        this.contactPK = contactPK;
    }

    public Contact(ContactPK contactPK, int number) {
        this.contactPK = contactPK;
        this.number = number;
    }

    public Contact(String teacherEmail, String studentEmail) {
        this.contactPK = new ContactPK(teacherEmail, studentEmail);
    }

    public Contact(ContactPK contactPK, User user, User user1) {
        this.contactPK = contactPK;
        this.user = user;
        this.user1 = user1;
    }

    public ContactPK getContactPK() {
        return contactPK;
    }

    public void setContactPK(ContactPK contactPK) {
        this.contactPK = contactPK;
    }

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public User getUser1() {
        return user1;
    }

    public void setUser1(User user1) {
        this.user1 = user1;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (contactPK != null ? contactPK.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Contact)) {
            return false;
        }
        Contact other = (Contact) object;
        if ((this.contactPK == null && other.contactPK != null) || (this.contactPK != null && !this.contactPK.equals(other.contactPK))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.mycompany.tamboserver.exceptions.Contact[ contactPK=" + contactPK + " ]";
    }

    public void setAll(Contact contact) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
