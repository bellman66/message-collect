package io.message.message.framework.database.jpa;

import io.message.message.domain.model.MechanicalSignal;
import org.springframework.data.cassandra.repository.CassandraRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MechanicalSignalRepository extends CassandraRepository<MechanicalSignal, String> {
}
