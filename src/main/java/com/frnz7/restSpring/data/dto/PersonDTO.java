package com.frnz7.restSpring.data.dto;


import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.frnz7.restSpring.serializer.GenderSerializer;
import org.springframework.hateoas.RepresentationModel;


import java.io.Serial;
import java.io.Serializable;
import java.util.Date;
import java.util.Objects;

//@JsonPropertyOrder({"id", "first_name", "last_name", "address", "gender" })
//@JsonFilter("PersonFilter")
public class PersonDTO extends RepresentationModel<PersonDTO> implements Serializable {


    @Serial
    private static final long serialVersionUID = 1L;


    private Long id;
    //@JsonProperty("first_name")
    private String firstName;
    //@JsonProperty("last_name")
    //@JsonInclude(JsonInclude.Include.NON_NULL)
    private String lastName;
    //@JsonInclude(JsonInclude.Include.NON_EMPTY)
    //private String phoneNumber;
    //@JsonFormat(pattern = "dd/MM/yyyy")
    //private Date birthDay;
    private String address;
   // @JsonIgnore
    //@JsonSerialize(using = GenderSerializer.class)
    private String gender;
   //private String sensitiveData;
    private Boolean enabled;

    public PersonDTO(){}

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public Boolean getEnabled() {
        return enabled;
    }

    public void setEnabled(Boolean enabled) {
        this.enabled = enabled;
    }

    @Override
    public boolean equals(Object object) {
        if (object == null || getClass() != object.getClass()) return false;
        if (!super.equals(object)) return false;
        PersonDTO personDTO = (PersonDTO) object;
        return Objects.equals(id, personDTO.id) && Objects.equals(firstName, personDTO.firstName) && Objects.equals(lastName, personDTO.lastName) && Objects.equals(address, personDTO.address) && Objects.equals(gender, personDTO.gender) && Objects.equals(enabled, personDTO.enabled);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), id, firstName, lastName, address, gender, enabled);
    }
}
