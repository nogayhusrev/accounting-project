
package com.accounting.dto.addressApi;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import javax.annotation.Generated;
import javax.validation.Valid;
import com.fasterxml.jackson.annotation.JsonAnyGetter;
import com.fasterxml.jackson.annotation.JsonAnySetter;
import com.fasterxml.jackson.annotation.JsonIgnore;
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
public class AddressApiCountryResponse {

    @JsonProperty("error")
    private Boolean error;
    @JsonProperty("msg")
    private String msg;
    @JsonProperty("data")
    @Valid
    private List<Country> data;
    @JsonIgnore
    @Valid
    private Map<String, Object> additionalProperties = new LinkedHashMap<String, Object>();

    /**
     * No args constructor for use in serialization
     * 
     */
    public AddressApiCountryResponse() {
    }

    /**
     * 
     * @param msg
     * @param data
     * @param error
     */
    public AddressApiCountryResponse(Boolean error, String msg, List<Country> data) {
        super();
        this.error = error;
        this.msg = msg;
        this.data = data;
    }

    @JsonProperty("error")
    public Boolean getError() {
        return error;
    }

    @JsonProperty("error")
    public void setError(Boolean error) {
        this.error = error;
    }

    @JsonProperty("msg")
    public String getMsg() {
        return msg;
    }

    @JsonProperty("msg")
    public void setMsg(String msg) {
        this.msg = msg;
    }

    @JsonProperty("data")
    public List<Country> getData() {
        return data;
    }

    @JsonProperty("data")
    public void setData(List<Country> data) {
        this.data = data;
    }

    @JsonAnyGetter
    public Map<String, Object> getAdditionalProperties() {
        return this.additionalProperties;
    }

    @JsonAnySetter
    public void setAdditionalProperty(String name, Object value) {
        this.additionalProperties.put(name, value);
    }

}
