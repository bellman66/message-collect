package io.message.message.config;

import java.security.KeyManagementException;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.time.Duration;
import javax.net.ssl.SSLContext;
import lombok.AllArgsConstructor;
import org.apache.http.conn.ssl.TrustAllStrategy;
import org.apache.http.ssl.SSLContextBuilder;
import org.springframework.boot.autoconfigure.elasticsearch.ElasticsearchProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.elasticsearch.client.ClientConfiguration;
import org.springframework.data.elasticsearch.client.elc.ReactiveElasticsearchConfiguration;
import org.springframework.data.elasticsearch.repository.config.EnableReactiveElasticsearchRepositories;

@Configuration
@AllArgsConstructor
@EnableReactiveElasticsearchRepositories(basePackages = "io.message.message")
public class ElasticsearchConfig extends ReactiveElasticsearchConfiguration {

    private final ElasticsearchProperties elasticsearchProperties;

    @Override
    public ClientConfiguration clientConfiguration() {
        return ClientConfiguration.builder()
                .connectedTo(elasticsearchProperties.getUris().toArray(new String[0]))
                .usingSsl(disableSslVerification())
                .withBasicAuth(
                        elasticsearchProperties.getUsername(),
                        elasticsearchProperties.getPassword())
                .withConnectTimeout(Duration.ofSeconds(5))
                .withSocketTimeout(Duration.ofSeconds(3))
                .build();
    }

    public static SSLContext disableSslVerification() {
        try {
            return SSLContextBuilder.create()
                    .setProtocol("SSL")
                    .loadTrustMaterial(TrustAllStrategy.INSTANCE)
                    .build();
        } catch (NoSuchAlgorithmException | KeyStoreException | KeyManagementException e) {
            e.printStackTrace();
        }
        return null;
    }
}
