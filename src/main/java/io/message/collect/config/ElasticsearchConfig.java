package io.message.collect.config;

import lombok.AllArgsConstructor;
import org.apache.http.conn.ssl.TrustAllStrategy;
import org.apache.http.ssl.SSLContextBuilder;
import org.springframework.boot.autoconfigure.elasticsearch.ElasticsearchProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.elasticsearch.client.ClientConfiguration;
import org.springframework.data.elasticsearch.client.elc.ElasticsearchConfiguration;
import org.springframework.data.elasticsearch.repository.config.EnableElasticsearchRepositories;

import javax.net.ssl.SSLContext;
import java.security.KeyManagementException;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.time.Duration;

@Configuration
@AllArgsConstructor
@EnableElasticsearchRepositories(basePackages = "io.message.collect")
public class ElasticsearchConfig extends ElasticsearchConfiguration {

    private final ElasticsearchProperties elasticsearchProperties;

    @Override
    public ClientConfiguration clientConfiguration() {
        return ClientConfiguration.builder()
                .connectedTo(elasticsearchProperties.getUris().toArray(new String[0]))
                .usingSsl(disableSslVerification())
                .withBasicAuth(elasticsearchProperties.getUsername(), elasticsearchProperties.getPassword())
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
