package jp.masanori.mncalcwebsample.calcs;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import jakarta.inject.Singleton;

@Singleton
public class WebProgramCalculator {
    private final Logger logger;
    public WebProgramCalculator() {
        this.logger = LoggerFactory.getLogger(WebProgramCalculator.class);
    }
}
