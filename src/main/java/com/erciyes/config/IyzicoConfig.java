package com.erciyes.config;

import com.iyzipay.Options;
import org.springframework.context.annotation.Configuration;

@Configuration
public class IyzicoConfig {
    public static Options getOptions() {
        Options options = new Options();
        options.setApiKey("sandbox-9urQVgZxuqcdTTf4Dt0yM5ooOW92w7m0");
        options.setSecretKey("sandbox-AJ8v7rZb5j7ZOV1MVU8BjPWNg5tZxKOq");
        options.setBaseUrl("https://sandbox-api.iyzipay.com");
        return options;
    }
}
