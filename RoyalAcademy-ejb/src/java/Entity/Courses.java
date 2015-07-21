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
@Table(name = "courses")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Courses.findAll", query = "SELECT c FROM Courses c"),
    @NamedQuery(name = "Courses.findById", query = "SELECT c FROM Courses c WHERE c.id = :id"),
    @NamedQuery(name = "Courses.findByName", query = "SELECT c FROM Courses c WHERE c.name like :name"),
    @NamedQuery(name = "Courses.findByStartTime", query = "SELECT c FROM Courses c WHERE c.startTime = :startTime"),
    @NamedQuery(name = "Courses.findByEndTime", query = "SELECT c FROM Courses c WHERE c.endTime = :endTime"),
    @NamedQuery(name = "Courses.findByCreatedDate", query = "SELECT c FROM Courses c WHERE c.createdDate = :createdDate")})
public class Courses implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 50)
    @Column(name = "_id")
    private String id;
    @Size(max = 60)
    @Column(name = "name")
    private String name;
    @Column(name = "startTime")
    @Temporal(TemporalType.DATE)
    private Date startTime;
    @Column(name = "endTime")
    @Temporal(TemporalType.DATE)
    private Date endTime;
    @Column(name = "createdDate")
    @Temporal(TemporalType.DATE)
    private Date createdDate;
    @JoinColumn(name = "areaOfStudy", referencedColumnName = "_id")
    @ManyToOne
    private AreaOfStudy areaOfStudy;
    @OneToMany(mappedBy = "course")
    private Collection<Class> classCollection;
    @OneToMany(mappedBy = "course")
    private Collection<SubjectCourseDetail> subjectCourseDetailCollection;
    @OneToMany(mappedBy = "course")
    private Collection<User> userCollection;

    public Courses() {
    }

    public Courses(String id) {
        this.id = id;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
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

    public AreaOfStudy getAreaOfStudy() {
        return areaOfStudy;
    }

    public void setAreaOfStudy(AreaOfStudy areaOfStudy) {
        this.areaOfStudy = areaOfStudy;
    }

    @XmlTransient
    public Collection<Class> getClassCollection() {
        return classCollection;
    }

    public void setClassCollection(Collection<Class> classCollection) {
        this.classCollection = classCollection;
    }

    @XmlTransient
    public Collection<SubjectCourseDetail> getSubjectCourseDetailCollection() {
        return subjectCourseDetailCollection;
    }

    public void setSubjectCourseDetailCollection(Collection<SubjectCourseDetail> subjectCourseDetailCollection) {
        this.subjectCourseDetailCollection = subjectCourseDetailCollection;
    }

    @XmlTransient
    public Collection<User> getUserCollection() {
        return userCollection;
    }

    public void setUserCollection(Collection<User> userCollection) {
        this.userCollection = userCollection;
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
        if (!(object instanceof Courses)) {
            return false;
        }
        Courses other = (Courses) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Entity.Courses[ id=" + id + " ]";
    }

    
}
