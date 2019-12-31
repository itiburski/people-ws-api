package br.com.jitec.people.service;

import java.util.List;

import br.com.jitec.people.service.dto.PersonDto;

public interface PeopleService {

	List<PersonDto> getAll();

	PersonDto get(String publicId);

	PersonDto create(PersonDto request);

	PersonDto update(String publicId, PersonDto request);

	void delete(String publicId);

}
