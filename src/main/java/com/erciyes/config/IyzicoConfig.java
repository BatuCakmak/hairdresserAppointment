package com.erciyes.config;

import com.iyzipay.Options;
import org.springframework.context.annotation.Configuration;

@Configuration
public class IyzicoConfig {
    public static Options getOptions() {
        Options options = new Options();
        options.setApiKey("sandbox-XCDX6HRqzqj9iJLK43cSuc5Z0i7AdPts");
        options.setSecretKey("sandbox-K9s21660B9UOE5GxwxQ7IIk5d0VxKQu0");
        options.setBaseUrl("https://sandbox-api.iyzipay.com");
        return options;
    }
}
