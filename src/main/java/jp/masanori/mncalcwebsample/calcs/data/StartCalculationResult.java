package jp.masanori.mncalcwebsample.calcs.data;

import java.util.ArrayList;
import java.util.List;
import com.fasterxml.jackson.annotation.JsonProperty;

import io.micronaut.core.annotation.Introspected;
import io.micronaut.serde.annotation.Serdeable;

@Serdeable
@Introspected
public class StartCalculationResult {
    @JsonProperty("RequestID")
    private String requestID;
    @JsonProperty("Errors")
    private List<CalculationError> errors;

    public StartCalculationResult() {}
    public StartCalculationResult(String requestID) { this.requestID = requestID; }
    @JsonProperty("RequestID")
    public String getRequestID() { 
        return requestID; 
    }

    @JsonProperty("RequestID")
    public void setRequestID(String requestID) { 
        this.requestID = requestID; 
    }
    @JsonProperty("Errors")
    public List<CalculationError> getErrors() {
        return errors;
    }
    @JsonProperty("Errors")
    public void setErrors(List<CalculationError> errors) {
        this.errors = errors;
    }
    public static StartCalculationResult getFailed(String errorMessage) {
        StartCalculationResult result = new StartCalculationResult();
        result.errors = new ArrayList<>();
        CalculationError error = new CalculationError();
        error.setMessage(errorMessage);
        result.errors.add(error);
        return result;
    }
}