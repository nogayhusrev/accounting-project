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
        "error",
        "msg",
        "data"
})
@Generated("jsonschema2pojo")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class CountriesAndStatesGETResponse {

    @JsonProperty("error")
    public Boolean error;
    @JsonProperty("msg")
    public String msg;
    @JsonProperty("data")
    @Valid
    public List<Country> countries;

}
