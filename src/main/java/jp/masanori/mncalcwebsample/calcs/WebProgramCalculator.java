package jp.masanori.mncalcwebsample.calcs;

import io.micronaut.http.HttpRequest;
import io.micronaut.http.MediaType;
import io.micronaut.http.client.HttpClient;
import io.micronaut.http.client.annotation.Client;
import io.micronaut.http.client.multipart.MultipartBody;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.fasterxml.jackson.core.util.RequestPayload;

import jakarta.inject.Singleton;
import jp.masanori.mncalcwebsample.calcs.data.ComputingStatePayload;
import jp.masanori.mncalcwebsample.calcs.data.ComputingStatus;
import jp.masanori.mncalcwebsample.calcs.data.CalculationError;
import jp.masanori.mncalcwebsample.calcs.data.StartCalculationResult;
import jp.masanori.mncalcwebsample.texts.TextFunctions;
import reactor.core.publisher.Mono;

@Singleton
public class WebProgramCalculator {
    private final Logger logger;
    private final HttpClient httpClient;

    public WebProgramCalculator(@Client("https://api.lowenergy.jp") HttpClient httpClient) {
        this.logger = LoggerFactory.getLogger(WebProgramCalculator.class);
        this.httpClient = httpClient;
    }
    public Mono<String> startCalculating() throws IOException, InterruptedException {
        Path samplePath = Paths.get("C:\\Files\\sample01_WEBPRO_inputSheet_for_Ver3.10.xlsx");
        byte[] excelBytes = Files.readAllBytes(samplePath);

        HttpRequest<byte[]> sendExcelRequest = HttpRequest.POST("/building/3/v3100/compute", excelBytes)
                    .contentType(MediaType.of("application/vnd.openxmlformats-officedocument.spreadsheetml.sheet"))
                    .accept(MediaType.APPLICATION_JSON);

        StartCalculationResult initResponse = httpClient.toBlocking()
                    .retrieve(sendExcelRequest, StartCalculationResult.class);
            
        String requestID = initResponse.getRequestID();
        if(TextFunctions.isNullOrEmpty(requestID) || (initResponse.getErrors() != null && initResponse.getErrors().size() > 0)) {
            String errorMessages = "";
            for(CalculationError err: initResponse.getErrors()) {
                errorMessages += err.getMessage();
                errorMessages += "\n";
            }
            return Mono.just("Failed starting calculation: " + errorMessages);
        }
        ComputingStatePayload payload = new ComputingStatePayload();
        payload.setRequestID(requestID);
        String status = "";
        int intervalTime = 2000;

        do {
            HttpRequest<ComputingStatePayload> checkStatusRequest = HttpRequest.POST("/building/3/v3100/computingState", payload)
                    .contentType(MediaType.APPLICATION_JSON)
                    .accept(MediaType.APPLICATION_JSON);

            ComputingStatus statusResponse = httpClient.toBlocking()
                    .retrieve(checkStatusRequest, ComputingStatus.class);

            status = statusResponse.getStatus();
            if ("Completed".equals(status) || "Error".equals(status)) {
                break;
            }

            Thread.sleep(intervalTime);
        } while (true);
        return Mono.just("status を取得しました: statuds:" + status + " requestID:" + requestID);

    }
}
