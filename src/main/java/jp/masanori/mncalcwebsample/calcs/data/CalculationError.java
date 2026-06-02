package jp.masanori.mncalcwebsample.calcs.data;
import com.fasterxml.jackson.annotation.JsonProperty;

import io.micronaut.core.annotation.Introspected;
import io.micronaut.serde.annotation.Serdeable;

@Serdeable
@Introspected
public class CalculationError {
    @JsonProperty("Message")
    public String message;
    @JsonProperty("Line")
    public int line;
    @JsonProperty("SheetName")
    public String sheetName;
    
    @JsonProperty("Message")
    public String getMessage() {
        return message;
    }

    @JsonProperty("Message")
    public void setMessage(String message) {
        this.message = message;
    }
    
    @JsonProperty("Line")
    public int getLine() {
        return line;
    }

    @JsonProperty("Line")
    public void setLine(int line) {
        this.line = line;
    }

    @JsonProperty("SheetName")
    public String getSheetName() {
        return sheetName;
    }

    @JsonProperty("SheetName")
    public void setSheetName(String sheetName) {
        this.sheetName = sheetName;
    }
}
