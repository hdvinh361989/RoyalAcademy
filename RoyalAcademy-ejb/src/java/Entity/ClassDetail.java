/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Entity;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author vinh
 */
@Entity
@Table(name = "ClassDetail")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "ClassDetail.findAll", query = "SELECT c FROM ClassDetail c"),
    @NamedQuery(name = "ClassDetail.findById", query = "SELECT c FROM ClassDetail c WHERE c.id = :id"),
    @NamedQuery(name = "ClassDetail.findByCreatedDate", query = "SELECT c FROM ClassDetail c WHERE c.createdDate = :createdDate")})
public class ClassDetail implements Serializable {
    @JoinColumn(name = "subject", referencedColumnName = "_id")
    @ManyToOne
    private Subject subject;
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 50)
    @Column(name = "_id")
    private String id;
    @Column(name = "createdDate")
    @Temporal(TemporalType.DATE)
    private Date createdDate;
    @JoinColumn(name = "student", referencedColumnName = "_id")
    @ManyToOne
    private User student;
    @JoinColumn(name = "class", referencedColumnName = "_id")
    @ManyToOne
    private Class class1;

    public ClassDetail() {
    }

    public ClassDetail(String id) {
        this.id = id;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Date getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(Date createdDate) {
        this.createdDate = createdDate;
    }

    public User getStudent() {
        return student;
    }

    public void setStudent(User student) {
        this.student = student;
    }

    public Class getClass1() {
        return class1;
    }

    public void setClass1(Class class1) {
        this.class1 = class1;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof ClassDetail)) {
            return false;
        }
        ClassDetail other = (ClassDetail) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Entity.ClassDetail[ id=" + id + " ]";
    }

    public Subject getSubject() {
        return subject;
    }

    public void setSubject(Subject subject) {
        this.subject = subject;
    }
    
}
