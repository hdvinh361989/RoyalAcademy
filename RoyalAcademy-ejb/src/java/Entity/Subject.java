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
@Table(name = "subject")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Subject.findAll", query = "SELECT s FROM Subject s"),
    @NamedQuery(name = "Subject.findById", query = "SELECT s FROM Subject s WHERE s.id = :id"),
    @NamedQuery(name = "Subject.findByName", query = "SELECT s FROM Subject s WHERE s.name like :name"),
    @NamedQuery(name = "Subject.findByArea_Name", query = "SELECT s FROM Subject s WHERE s.name like :name AND s.areaOfStudy = :area"),
    @NamedQuery(name = "Subject.FindNameGroupArea", 
            query = "SELECT s.areaOfStudy.id, s.areaOfStudy.name "
            + "FROM Subject s "
            + "WHERE s.name like :name "
            + "GROUP BY s.areaOfStudy.id, s.areaOfStudy.name"),
    @NamedQuery(name = "Subject.findByCreatedDate", query = "SELECT s FROM Subject s WHERE s.createdDate = :createdDate")})
public class Subject implements Serializable {
    @OneToMany(mappedBy = "subject")
    private Collection<ClassDetail> classDetailCollection;
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
    @Column(name = "createdDate")
    @Temporal(TemporalType.DATE)
    private Date createdDate;
    @JoinColumn(name = "areaOfStudy", referencedColumnName = "_id")
    @ManyToOne
    private AreaOfStudy areaOfStudy;
    @OneToMany(mappedBy = "subject")
    private Collection<Assignment> assignmentCollection;
    @OneToMany(mappedBy = "subject")
    private Collection<Class> classCollection;
    @OneToMany(mappedBy = "subject")
    private Collection<SubjectCourseDetail> subjectCourseDetailCollection;
    

    public Subject() {
    }

    public Subject(String id) {
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

    public Date getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(Date createdDate) {
        this.createdDate = createdDate;
    }

    @XmlTransient
    public Collection<Assignment> getAssignmentCollection() {
        return assignmentCollection;
    }

    public void setAssignmentCollection(Collection<Assignment> assignmentCollection) {
        this.assignmentCollection = assignmentCollection;
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

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Subject)) {
            return false;
        }
        Subject other = (Subject) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Entity.Subject[ id=" + id + " ]";
    }

    @XmlTransient
    public Collection<ClassDetail> getClassDetailCollection() {
        return classDetailCollection;
    }

    public void setClassDetailCollection(Collection<ClassDetail> classDetailCollection) {
        this.classDetailCollection = classDetailCollection;
    }

    /**
     * @return the areaOfStudy
     */
    public AreaOfStudy getAreaOfStudy() {
        return areaOfStudy;
    }

    /**
     * @param areaOfStudy the areaOfStudy to set
     */
    public void setAreaOfStudy(AreaOfStudy areaOfStudy) {
        this.areaOfStudy = areaOfStudy;
    }
    
}
