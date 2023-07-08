
package com.accounting.dto.addressApi;

import javax.annotation.Generated;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
    "country"
})
@Generated("jsonschema2pojo")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class CountryAndItsStatesPOSTRequest {

    @JsonProperty("country")
    public String country;

}
