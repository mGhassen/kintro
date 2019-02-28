package fr.kreatik.kintro.domain;


import com.fasterxml.jackson.annotation.JsonIgnore;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Field;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.DBRef;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;
import java.util.Objects;

/**
 * The Employee entity.
 */
@ApiModel(description = "The Employee entity.")
@Document(collection = "employee")
public class Employee implements Serializable {

    private static final long serialVersionUID = 1L;
    
    @Id
    private String id;

    /**
     * The firstname attribute.
     */
    @ApiModelProperty(value = "The firstname attribute.")
    @Field("first_name")
    private String firstName;

    @Field("last_name")
    private String lastName;

    @Field("email")
    private String email;

    @Field("phone_number")
    private String phoneNumber;

    @Field("adresse")
    private String adresse;

    @Field("nationalite")
    private Long nationalite;

    @DBRef
    @Field("job")
    private Set<Job> jobs = new HashSet<>();
    @DBRef
    @Field("documentEmployee")
    private Set<DocumentEmployee> documentEmployees = new HashSet<>();
    // jhipster-needle-entity-add-field - JHipster will add fields here, do not remove
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public Employee firstName(String firstName) {
        this.firstName = firstName;
        return this;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public Employee lastName(String lastName) {
        this.lastName = lastName;
        return this;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmail() {
        return email;
    }

    public Employee email(String email) {
        this.email = email;
        return this;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public Employee phoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
        return this;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getAdresse() {
        return adresse;
    }

    public Employee adresse(String adresse) {
        this.adresse = adresse;
        return this;
    }

    public void setAdresse(String adresse) {
        this.adresse = adresse;
    }

    public Long getNationalite() {
        return nationalite;
    }

    public Employee nationalite(Long nationalite) {
        this.nationalite = nationalite;
        return this;
    }

    public void setNationalite(Long nationalite) {
        this.nationalite = nationalite;
    }

    public Set<Job> getJobs() {
        return jobs;
    }

    public Employee jobs(Set<Job> jobs) {
        this.jobs = jobs;
        return this;
    }

    public Employee addJob(Job job) {
        this.jobs.add(job);
        job.setEmployee(this);
        return this;
    }

    public Employee removeJob(Job job) {
        this.jobs.remove(job);
        job.setEmployee(null);
        return this;
    }

    public void setJobs(Set<Job> jobs) {
        this.jobs = jobs;
    }

    public Set<DocumentEmployee> getDocumentEmployees() {
        return documentEmployees;
    }

    public Employee documentEmployees(Set<DocumentEmployee> documentEmployees) {
        this.documentEmployees = documentEmployees;
        return this;
    }

    public Employee addDocumentEmployee(DocumentEmployee documentEmployee) {
        this.documentEmployees.add(documentEmployee);
        documentEmployee.setEmployee(this);
        return this;
    }

    public Employee removeDocumentEmployee(DocumentEmployee documentEmployee) {
        this.documentEmployees.remove(documentEmployee);
        documentEmployee.setEmployee(null);
        return this;
    }

    public void setDocumentEmployees(Set<DocumentEmployee> documentEmployees) {
        this.documentEmployees = documentEmployees;
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
        Employee employee = (Employee) o;
        if (employee.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), employee.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "Employee{" +
            "id=" + getId() +
            ", firstName='" + getFirstName() + "'" +
            ", lastName='" + getLastName() + "'" +
            ", email='" + getEmail() + "'" +
            ", phoneNumber='" + getPhoneNumber() + "'" +
            ", adresse='" + getAdresse() + "'" +
            ", nationalite=" + getNationalite() +
            "}";
    }
}
