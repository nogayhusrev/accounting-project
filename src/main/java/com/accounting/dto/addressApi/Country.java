package com.accounting.dto.addressApi;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.annotation.Generated;
import javax.validation.Valid;
import java.util.List;

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

}