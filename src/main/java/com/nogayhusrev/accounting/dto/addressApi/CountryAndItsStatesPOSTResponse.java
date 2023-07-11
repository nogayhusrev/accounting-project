
package com.nogayhusrev.accounting.dto.addressApi;

import javax.annotation.Generated;
import javax.validation.Valid;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
    "error",
    "msg",
    "data"
})
@Generated("jsonschema2pojo")
public class CountryAndItsStatesPOSTResponse {

    @JsonProperty("error")
    public Boolean error;
    @JsonProperty("msg")
    public String msg;
    @JsonProperty("data")
    @Valid
    public Country country;

}
