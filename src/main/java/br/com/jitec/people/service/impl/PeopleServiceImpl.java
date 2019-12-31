package br.com.jitec.people.service.impl;

import java.util.List;
import java.util.UUID;

import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.jitec.people.data.model.Person;
import br.com.jitec.people.data.model.PersonMapper;
import br.com.jitec.people.data.repository.PeopleRepository;
import br.com.jitec.people.service.PeopleService;
import br.com.jitec.people.service.dto.PersonDto;
import br.com.jitec.people.util.BusinessPreconditions;
import br.com.jitec.people.util.DateUtil;

@Service
public class PeopleServiceImpl implements PeopleService {

	@Autowired
	private PeopleRepository peopleRepository;

	@Autowired
	private ModelMapper modelMapper;

	@Override
	public List<PersonDto> getAll() {
		List<Person> people = peopleRepository.findAll();

		java.lang.reflect.Type listType = new TypeToken<List<PersonDto>>() {
		}.getType();
		List<PersonDto> peopleDto = modelMapper.map(people, listType);

		peopleDto.stream().forEach(person -> person.setAge(DateUtil.calcularIdade(person.getBirthDate())));

		return peopleDto;
	}

	@Override
	public PersonDto get(String publicId) {
		Person person = BusinessPreconditions.checkFound(peopleRepository.findByPublicId(publicId));

		PersonDto result = modelMapper.map(person, PersonDto.class);
		result.setAge(DateUtil.calcularIdade(result.getBirthDate()));
		return result;
	}

	@Override
	public PersonDto create(PersonDto personDto) {
		personDto.setPublicId(null);

		Person personToSave = modelMapper.map(personDto, Person.class);
		personToSave.setPublicId(UUID.randomUUID().toString());
		Person savedPerson = peopleRepository.save(personToSave);

		PersonDto result = modelMapper.map(savedPerson, PersonDto.class);
		result.setAge(DateUtil.calcularIdade(result.getBirthDate()));

		return result;
	}

	@Override
	public PersonDto update(String publicId, PersonDto personDto) {
		Person personToUpdate = BusinessPreconditions.checkFound(peopleRepository.findByPublicId(publicId));
		PersonMapper.merge(personToUpdate, personDto);
		Person updatedPerson = peopleRepository.save(personToUpdate);

		PersonDto result = modelMapper.map(updatedPerson, PersonDto.class);
		result.setAge(DateUtil.calcularIdade(result.getBirthDate()));

		return result;
	}

	@Override
	public void delete(String publicId) {
		Person personToDelete = BusinessPreconditions.checkFound(peopleRepository.findByPublicId(publicId));

		peopleRepository.delete(personToDelete);
	}

}
