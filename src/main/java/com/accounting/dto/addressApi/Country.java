package com.accounting.dto.addressApi;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import javax.annotation.Generated;
import javax.validation.Valid;
import java.util.List;
import java.util.Objects;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
        "name",
        "iso3",
        "iso2",
        "states"
})
@Generated("jsonschema2pojo")
@Data
@AllArgsConstructor
@NoArgsConstructor

public class Country {

    @JsonProperty("name")
    public String name;
    @JsonProperty("iso3")
    public String iso3;
    @JsonProperty("iso2")
    public String iso2;
    @JsonProperty("states")
    @Valid
    public List<State> states;


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Country)) return false;
        Country country = (Country) o;
        return Objects.equals(getName(), country.getName()) && Objects.equals(getIso3(), country.getIso3()) && Objects.equals(getIso2(), country.getIso2());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getName(), getIso3(), getIso2());
    }
}
