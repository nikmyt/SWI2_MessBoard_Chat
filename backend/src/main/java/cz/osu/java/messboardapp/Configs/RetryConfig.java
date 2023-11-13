package cz.osu.java.messboardapp.Configs;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.retry.backoff.ExponentialBackOffPolicy;
import org.springframework.retry.policy.SimpleRetryPolicy;
import org.springframework.retry.support.RetryTemplate;

@Configuration
public class RetryConfig {
    @Bean
    public RetryTemplate retryTemplate() {
        RetryTemplate retryTemplate = new RetryTemplate();

        // Configure retry policy with exponential backoff
        ExponentialBackOffPolicy backOffPolicy = new ExponentialBackOffPolicy();
        backOffPolicy.setInitialInterval(1000); // 1 second initial delay
        backOffPolicy.setMaxInterval(30000); // 30 seconds maximum delay
        backOffPolicy.setMultiplier(2); // Exponential factor
        retryTemplate.setBackOffPolicy(backOffPolicy);

        // Retry up to 5 times
        retryTemplate.setRetryPolicy(new SimpleRetryPolicy(5));

        return retryTemplate;
    }
}
