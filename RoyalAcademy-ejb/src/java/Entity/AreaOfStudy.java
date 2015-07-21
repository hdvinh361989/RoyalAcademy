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
@Table(name = "AreaOfStudy")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "AreaOfStudy.findAll", query = "SELECT a FROM AreaOfStudy a"),
    @NamedQuery(name = "AreaOfStudy.findById", query = "SELECT a FROM AreaOfStudy a WHERE a.id = :id"),
    @NamedQuery(name = "AreaOfStudy.findByName", query = "SELECT a FROM AreaOfStudy a WHERE a.name like :name"),
    @NamedQuery(name = "AreaOfStudy.findByCreatedDate", query = "SELECT a FROM AreaOfStudy a WHERE a.createdDate = :createdDate")})
public class AreaOfStudy implements Serializable {
    @OneToMany(mappedBy = "areaOfStudy")
    private Collection<Subject> subjectCollection;
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 50)
    @Column(name = "_id")
    private String id;
    @Size(max = 100)
    @Column(name = "name")
    private String name;
    @Column(name = "createdDate")
    @Temporal(TemporalType.DATE)
    private Date createdDate;
    @OneToMany(mappedBy = "areaOfStudy")
    private Collection<Courses> coursesCollection;

    public AreaOfStudy() {
    }

    public AreaOfStudy(String id) {
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
    public Collection<Courses> getCoursesCollection() {
        return coursesCollection;
    }

    public void setCoursesCollection(Collection<Courses> coursesCollection) {
        this.coursesCollection = coursesCollection;
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
        if (!(object instanceof AreaOfStudy)) {
            return false;
        }
        AreaOfStudy other = (AreaOfStudy) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Entity.AreaOfStudy[ id=" + id + " ]";
    }

    @XmlTransient
    public Collection<Subject> getSubjectCollection() {
        return subjectCollection;
    }

    public void setSubjectCollection(Collection<Subject> subjectCollection) {
        this.subjectCollection = subjectCollection;
    }
    
}
