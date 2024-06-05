package io.message.collect.application.web.repository;

import io.message.collect.application.data.Person;
import org.springframework.data.cassandra.repository.CassandraRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface ScyllaRepository extends CassandraRepository<Person, UUID> {

}
