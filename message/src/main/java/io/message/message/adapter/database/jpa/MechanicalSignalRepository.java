package io.message.message.adapter.database.jpa;

import io.message.message.domain.model.MechanicalSignal;
import org.springframework.data.cassandra.repository.ReactiveCassandraRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MechanicalSignalRepository
        extends ReactiveCassandraRepository<MechanicalSignal, String> {}
