package io.message.account.config;

import lombok.AllArgsConstructor;
import org.springframework.boot.autoconfigure.cassandra.CassandraProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.cassandra.config.AbstractCassandraConfiguration;
import org.springframework.data.cassandra.config.SchemaAction;
import org.springframework.data.cassandra.core.cql.keyspace.CreateKeyspaceSpecification;
import org.springframework.data.cassandra.core.cql.keyspace.DropKeyspaceSpecification;
import org.springframework.data.cassandra.core.cql.keyspace.KeyspaceOption;

import java.util.List;

@Configuration
@AllArgsConstructor
public class ScyllaConfig extends AbstractCassandraConfiguration {

    private static final String BASE_PACKAGE = "io.message.account";

    private CassandraProperties properties;

    @Override
    protected List<CreateKeyspaceSpecification> getKeyspaceCreations() {
        return List.of(CreateKeyspaceSpecification.createKeyspace(getKeyspaceName()).with(KeyspaceOption.DURABLE_WRITES, true));
    }

    @Override
    protected List<DropKeyspaceSpecification> getKeyspaceDrops() {
        return List.of(DropKeyspaceSpecification.dropKeyspace(getKeyspaceName()));
    }

    @Override
    protected String getKeyspaceName() {
        return properties.getKeyspaceName();
    }

    @Override
    public String getContactPoints() {
        return properties.getContactPoints().getFirst();
    }

    @Override
    protected int getPort() {
        return properties.getPort();
    }

    @Override
    public SchemaAction getSchemaAction() {
        return SchemaAction.valueOf(properties.getSchemaAction().toUpperCase());
    }

    @Override
    protected String getLocalDataCenter() {
        return properties.getLocalDatacenter();
    }

    @Override
    public String[] getEntityBasePackages() {
        return new String[]{BASE_PACKAGE};
    }

}
