package jp.masanori.mncalcwebsample.calcs.data;

import com.fasterxml.jackson.annotation.JsonProperty;

import io.micronaut.core.annotation.Introspected;
import io.micronaut.serde.annotation.Serdeable;
@Serdeable
@Introspected
public class ComputingStatePayload {
    @JsonProperty("RequestID")
    private String requestID;
    @JsonProperty("RequestID")
    public String getRequestID() { 
        return requestID; 
    }

    @JsonProperty("RequestID")
    public void setRequestID(String requestID) { 
        this.requestID = requestID; 
    }
}
