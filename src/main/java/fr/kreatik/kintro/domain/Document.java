package fr.kreatik.kintro.domain;


import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Field;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.DBRef;

import java.io.Serializable;
import java.util.Objects;

/**
 * A Document.
 */
@Document(collection = "jhi_document")
public class Document implements Serializable {

    private static final long serialVersionUID = 1L;
    
    @Id
    private String id;

    @Field("doc_name")
    private String docName;

    @Field("description")
    private String description;

    @Field("docpath")
    private String docpath;

    @DBRef
    @Field("employee")
    @JsonIgnoreProperties("documents")
    private Employee employee;

    @DBRef
    @Field("job")
    @JsonIgnoreProperties("documents")
    private Job job;

    // jhipster-needle-entity-add-field - JHipster will add fields here, do not remove
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getDocName() {
        return docName;
    }

    public Document docName(String docName) {
        this.docName = docName;
        return this;
    }

    public void setDocName(String docName) {
        this.docName = docName;
    }

    public String getDescription() {
        return description;
    }

    public Document description(String description) {
        this.description = description;
        return this;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getDocpath() {
        return docpath;
    }

    public Document docpath(String docpath) {
        this.docpath = docpath;
        return this;
    }

    public void setDocpath(String docpath) {
        this.docpath = docpath;
    }

    public Employee getEmployee() {
        return employee;
    }

    public Document employee(Employee employee) {
        this.employee = employee;
        return this;
    }

    public void setEmployee(Employee employee) {
        this.employee = employee;
    }

    public Job getJob() {
        return job;
    }

    public Document job(Job job) {
        this.job = job;
        return this;
    }

    public void setJob(Job job) {
        this.job = job;
    }
    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here, do not remove

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Document document = (Document) o;
        if (document.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), document.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "Document{" +
            "id=" + getId() +
            ", docName='" + getDocName() + "'" +
            ", description='" + getDescription() + "'" +
            ", docpath='" + getDocpath() + "'" +
            "}";
    }
}
