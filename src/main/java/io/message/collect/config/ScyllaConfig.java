package io.message.collect.config;

import java.net.InetSocketAddress;
import java.util.List;
import lombok.AllArgsConstructor;
import org.springframework.boot.autoconfigure.cassandra.CassandraProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.cassandra.config.AbstractCassandraConfiguration;
import org.springframework.data.cassandra.config.SessionBuilderConfigurer;
import org.springframework.data.cassandra.core.cql.keyspace.CreateKeyspaceSpecification;
import org.springframework.data.cassandra.core.cql.keyspace.DropKeyspaceSpecification;
import org.springframework.data.cassandra.repository.config.EnableCassandraRepositories;

@Configuration
@AllArgsConstructor
@EnableCassandraRepositories
public class ScyllaConfig extends AbstractCassandraConfiguration {

    private final CassandraProperties properties;

    @Override
    protected List<CreateKeyspaceSpecification> getKeyspaceCreations() {
        return List.of(CreateKeyspaceSpecification.createKeyspace(getKeyspaceName()));
    }

    @Override
    protected List<DropKeyspaceSpecification> getKeyspaceDrops() {
        return List.of(DropKeyspaceSpecification.dropKeyspace(getKeyspaceName()));
    }

    @Override
    protected SessionBuilderConfigurer getSessionBuilderConfigurer() {
        return builder -> builder
                .withKeyspace(getKeyspaceName())
                .addContactPoint(InetSocketAddress.createUnresolved(String.valueOf(properties.getContactPoints()), properties.getPort()));
    }

    @Override
    protected String getKeyspaceName() {
        return properties.getKeyspaceName();
    }

}
