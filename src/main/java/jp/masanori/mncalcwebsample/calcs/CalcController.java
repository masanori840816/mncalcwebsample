package jp.masanori.mncalcwebsample.calcs;

import io.micronaut.http.HttpResponse;
import io.micronaut.http.MediaType;
import io.micronaut.http.annotation.Controller;
import io.micronaut.http.annotation.Post;
import io.micronaut.http.server.types.files.SystemFile;
import reactor.core.publisher.Mono;

@Controller("/calc")
public class CalcController {
    @Post(uri = "/start", consumes = MediaType.MULTIPART_FORM_DATA, produces = "text/plain;charset=utf-8")
    public Mono<HttpResponse<SystemFile>> startCaluculation()
    {
        return Mono.just(HttpResponse.badRequest());
    }
}
