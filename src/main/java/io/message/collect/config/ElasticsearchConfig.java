package io.message.collect.config;

import lombok.AllArgsConstructor;
import org.springframework.boot.autoconfigure.elasticsearch.ElasticsearchProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.elasticsearch.client.ClientConfiguration;
import org.springframework.data.elasticsearch.client.elc.ElasticsearchClients;
import org.springframework.data.elasticsearch.client.elc.ElasticsearchConfiguration;

import java.util.List;

@Configuration
@AllArgsConstructor
public class ElasticsearchConfig extends ElasticsearchConfiguration {

    private final ElasticsearchProperties elasticsearchProperties;

    @Override
    public ClientConfiguration clientConfiguration() {
        List<String> uris = elasticsearchProperties.getUris();
        return ClientConfiguration.builder()
                .connectedTo(uris.getFirst())
                .withClientConfigurer(ElasticsearchClients.ElasticsearchRestClientConfigurationCallback.from(restClientBuilder -> {
                    // configure the Elasticsearch RestClient
                    return restClientBuilder;
                }))
                .build();
    }

}
