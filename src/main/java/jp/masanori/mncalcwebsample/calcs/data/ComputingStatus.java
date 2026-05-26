package jp.masanori.mncalcwebsample.calcs.data;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;

import io.micronaut.core.annotation.Introspected;
import io.micronaut.serde.annotation.Serdeable;
@Serdeable
@Introspected
public class ComputingStatus {
    @JsonProperty("RequestID")
    private String requestID;
    @JsonProperty("Status")
    private String status;
    @JsonProperty("Errors")
    private List<CalculationError> errors;

    public ComputingStatus() {}
    public ComputingStatus(String status) { 
        this.status = status;
    }
    @JsonProperty("RequestID")
    public String getRequestID() { 
        return requestID; 
    }

    @JsonProperty("RequestID")
    public void setRequestID(String requestID) { 
        this.requestID = requestID; 
    }
    @JsonProperty("Status")
    public String getStatus() {
        return status;
    }
    @JsonProperty("Status")
    public void setStatus(String status) {
        this.status = status;
    }
    
    @JsonProperty("Errors")
    public List<CalculationError> getErrors() {
        return errors;
    }
    @JsonProperty("Errors")
    public void setErrors(List<CalculationError> errors) {
        this.errors = errors;
    }
}
