package br.com.jitec.people.data.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.jitec.people.data.model.Person;

@Repository
public interface PeopleRepository extends JpaRepository<Person, Long> {

	Person findByPublicId(String publicId);

}
