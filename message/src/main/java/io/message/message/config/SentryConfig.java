package io.message.message.config;

import io.sentry.spring.jakarta.EnableSentry;
import org.springframework.context.annotation.Configuration;

@EnableSentry(dsn = "https://5a0b6eeea90825780b2a72a94f7d9b15@o4507900250357760.ingest.us.sentry.io/4507900274212864")
@Configuration
public class SentryConfig {
}
