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
@Table(name = "Subject_Course_Detail")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "SubjectCourseDetail.findAll", query = "SELECT s FROM SubjectCourseDetail s"),
    @NamedQuery(name = "SubjectCourseDetail.findById", query = "SELECT s FROM SubjectCourseDetail s WHERE s.id = :id"),
    @NamedQuery(name = "SubjectCourseDetail.findByCourse", query = "SELECT s FROM SubjectCourseDetail s WHERE s.course = :course"),
    @NamedQuery(name = "SubjectCourseDetail.findByCreatedDate", query = "SELECT s FROM SubjectCourseDetail s WHERE s.createdDate = :createdDate")})
public class SubjectCourseDetail implements Serializable {
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
    @JoinColumn(name = "subject", referencedColumnName = "_id")
    @ManyToOne
    private Subject subject;
    @JoinColumn(name = "course", referencedColumnName = "_id")
    @ManyToOne
    private Courses course;

    public SubjectCourseDetail() {
    }

    public SubjectCourseDetail(String id) {
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
        if (!(object instanceof SubjectCourseDetail)) {
            return false;
        }
        SubjectCourseDetail other = (SubjectCourseDetail) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Entity.SubjectCourseDetail[ id=" + id + " ]";
    }
    
}
