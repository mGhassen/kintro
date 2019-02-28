package fr.kreatik.kintro.domain;


import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Field;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.DBRef;

import java.io.Serializable;
import java.util.Objects;

/**
 * A DocumentEmployee.
 */
@Document(collection = "document_employee")
public class DocumentEmployee implements Serializable {

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
    @JsonIgnoreProperties("documentEmployees")
    private Employee employee;

    @DBRef
    @Field("job")
    @JsonIgnoreProperties("documentEmployees")
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

    public DocumentEmployee docName(String docName) {
        this.docName = docName;
        return this;
    }

    public void setDocName(String docName) {
        this.docName = docName;
    }

    public String getDescription() {
        return description;
    }

    public DocumentEmployee description(String description) {
        this.description = description;
        return this;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getDocpath() {
        return docpath;
    }

    public DocumentEmployee docpath(String docpath) {
        this.docpath = docpath;
        return this;
    }

    public void setDocpath(String docpath) {
        this.docpath = docpath;
    }

    public Employee getEmployee() {
        return employee;
    }

    public DocumentEmployee employee(Employee employee) {
        this.employee = employee;
        return this;
    }

    public void setEmployee(Employee employee) {
        this.employee = employee;
    }

    public Job getJob() {
        return job;
    }

    public DocumentEmployee job(Job job) {
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
        DocumentEmployee documentEmployee = (DocumentEmployee) o;
        if (documentEmployee.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), documentEmployee.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "DocumentEmployee{" +
            "id=" + getId() +
            ", docName='" + getDocName() + "'" +
            ", description='" + getDescription() + "'" +
            ", docpath='" + getDocpath() + "'" +
            "}";
    }
}
