/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tambo.model.VO;

import java.io.Serializable;
import java.util.Collection;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author usuario
 */
@Entity
@Table(name = "public.\"Meeting\"")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Meeting.findAll", query = "SELECT m FROM Meeting m")
    , @NamedQuery(name = "Meeting.findByMeetingId", query = "SELECT m FROM Meeting m WHERE m.meetingId = :meetingId")
    , @NamedQuery(name = "Meeting.findByMeetingDate", query = "SELECT m FROM Meeting m WHERE m.meetingDate = :meetingDate")
    , @NamedQuery(name = "Meeting.findByPlace", query = "SELECT m FROM Meeting m WHERE m.place = :place")})
public class Meeting implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "meeting_id")
    private Integer meetingId;
    @Basic(optional = false)
    @NotNull
    @Column(name = "meeting_date")
    @Temporal(TemporalType.DATE)
    private Date meetingDate;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 255)
    @Column(name = "place")
    private String place;
 

    public Meeting() {
    }

    public Meeting(Integer meetingId) {
        this.meetingId = meetingId;
    }

    public Meeting(Integer meetingId, Date meetingDate, String place) {
        this.meetingId = meetingId;
        this.meetingDate = meetingDate;
        this.place = place;
    }

    public Integer getMeetingId() {
        return meetingId;
    }

    public void setMeetingId(Integer meetingId) {
        this.meetingId = meetingId;
    }

    public Date getMeetingDate() {
        return meetingDate;
    }

    public void setMeetingDate(Date meetingDate) {
        this.meetingDate = meetingDate;
    }

    public String getPlace() {
        return place;
    }

    public void setPlace(String place) {
        this.place = place;
    }

    public void setAll(Meeting meet){
        this.meetingDate=meet.getMeetingDate();
        this.place=meet.getPlace();
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (meetingId != null ? meetingId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Meeting)) {
            return false;
        }
        Meeting other = (Meeting) object;
        if ((this.meetingId == null && other.meetingId != null) || (this.meetingId != null && !this.meetingId.equals(other.meetingId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.tambo.model.VO.Meeting[ meetingId=" + meetingId + " ]";
    }
    
}
