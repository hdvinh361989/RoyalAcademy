/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Entity;

import java.io.Serializable;
import java.util.Collection;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
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
 * @author vinh
 */
@Entity
@Table(name = "class")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Class.findAll", query = "SELECT c FROM Class c"),
    @NamedQuery(name = "Class.findById", query = "SELECT c FROM Class c WHERE c.id = :id"),
    @NamedQuery(name = "Class.findByName", query = "SELECT c FROM Class c WHERE c.name = :name"),
    @NamedQuery(name = "Class.findByEnrollKey", query = "SELECT c FROM Class c WHERE c.enrollKey = :enrollKey"),
    @NamedQuery(name = "Class.findByStartTime", query = "SELECT c FROM Class c WHERE c.startTime = :startTime"),
    @NamedQuery(name = "Class.findByEndTime", query = "SELECT c FROM Class c WHERE c.endTime = :endTime"),
    @NamedQuery(name = "Class.findByCreatedDate", query = "SELECT c FROM Class c WHERE c.createdDate = :createdDate")})
public class Class implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 50)
    @Column(name = "_id")
    private String id;
    @Size(max = 50)
    @Column(name = "enrollKey")
    private String enrollKey;
    @Column(name = "startTime")
    @Temporal(TemporalType.DATE)
    private Date startTime;
    @Column(name = "endTime")
    @Temporal(TemporalType.DATE)
    private Date endTime;
    @Column(name = "createdDate")
    @Temporal(TemporalType.DATE)
    private Date createdDate;
    @Size(max = 50)
    @Column(name = "name")
    private String name;
    @OneToMany(mappedBy = "class1")
    private Collection<ClassDetail> classDetailCollection;
    @OneToMany(mappedBy = "class1")
    private Collection<Marks> marksCollection;
    @JoinColumn(name = "staff", referencedColumnName = "_id")
    @ManyToOne
    private User staff;
    @JoinColumn(name = "subject", referencedColumnName = "_id")
    @ManyToOne
    private Subject subject;
    @JoinColumn(name = "course", referencedColumnName = "_id")
    @ManyToOne
    private Courses course;

    public Class() {
    }

    public Class(String id) {
        this.id = id;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getEnrollKey() {
        return enrollKey;
    }

    public void setEnrollKey(String enrollKey) {
        this.enrollKey = enrollKey;
    }

    public Date getStartTime() {
        return startTime;
    }

    public void setStartTime(Date startTime) {
        this.startTime = startTime;
    }

    public Date getEndTime() {
        return endTime;
    }

    public void setEndTime(Date endTime) {
        this.endTime = endTime;
    }

    public Date getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(Date createdDate) {
        this.createdDate = createdDate;
    }

    @XmlTransient
    public Collection<ClassDetail> getClassDetailCollection() {
        return classDetailCollection;
    }

    public void setClassDetailCollection(Collection<ClassDetail> classDetailCollection) {
        this.classDetailCollection = classDetailCollection;
    }

    @XmlTransient
    public Collection<Marks> getMarksCollection() {
        return marksCollection;
    }

    public void setMarksCollection(Collection<Marks> marksCollection) {
        this.marksCollection = marksCollection;
    }

    public User getStaff() {
        return staff;
    }

    public void setStaff(User staff) {
        this.staff = staff;
    }

    public Subject getSubject() {
        return subject;
    }

    public void setSubject(Subject subject) {
        this.subject = subject;
    }

    public Courses getCourse() {
        return course;
    }

    public void setCourse(Courses course) {
        this.course = course;
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
        if (!(object instanceof Class)) {
            return false;
        }
        Class other = (Class) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Entity.Class[ id=" + id + " ]";
    }

    /**
     * @return the name
     */
    public String getName() {
        return name;
    }

    /**
     * @param name the name to set
     */
    public void setName(String name) {
        this.name = name;
    }
    
}
