package jp.masanori.mncalcwebsample.calcs;

import io.micronaut.http.HttpResponse;
import io.micronaut.http.MediaType;
import io.micronaut.http.annotation.Controller;
import io.micronaut.http.annotation.Get;
import io.micronaut.http.annotation.Post;
import io.micronaut.http.server.types.files.SystemFile;
import io.micronaut.scheduling.TaskExecutors;
import io.micronaut.scheduling.annotation.ExecuteOn;
import reactor.core.publisher.Mono;

import java.io.IOException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Controller("/calc")
public class CalcController {
    private final Logger logger;
    private final WebProgramCalculator calc;
    public CalcController(WebProgramCalculator calc) {
        this.logger = LoggerFactory.getLogger(CalcController.class);
        this.calc = calc;
    }
   // @Post(uri = "/webprograms", consumes = MediaType.MULTIPART_FORM_DATA, produces = "text/plain;charset=utf-8")
   @Get("/webprograms")
   @ExecuteOn(TaskExecutors.BLOCKING)
    public Mono<String> calcWebProgram() throws IOException, InterruptedException{
        return calc.startCalculating();
        //return Mono.just(HttpResponse.badRequest());
    }
}
