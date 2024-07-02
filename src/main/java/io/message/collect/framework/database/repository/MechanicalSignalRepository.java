package io.message.collect.framework.database.repository;

import io.message.collect.domain.model.MechanicalSignal;
import org.springframework.data.cassandra.repository.CassandraRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MechanicalSignalRepository extends CassandraRepository<MechanicalSignal, String> {
}
