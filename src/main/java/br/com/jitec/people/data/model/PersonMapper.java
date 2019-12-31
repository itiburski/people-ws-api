package br.com.jitec.people.data.model;

import br.com.jitec.people.service.dto.PersonDto;

public class PersonMapper {

//	@Deprecated
//	public static PersonResponse toResponse(Person person) {
//		if (person == null) {
//			return null;
//		}
//		PersonResponse response = new PersonResponse();
//		response.setId(person.getId());
//		response.setName(person.getName());
//		response.setAge(DateUtil.calcularIdade(person.getBirthDate()));
//		response.setBirthDate(person.getBirthDate());
//		response.setEmail(person.getEmail());
//		return response;
//	}

//	@Deprecated
//	public static Person mergeWithRequest(Person person, PersonRequest request) {
//		person.setBirthDate(request.getBirthDate());
//		person.setName(request.getName());
//		person.setEmail(request.getEmail());
//		return person;
//	}

	public static Person merge(Person person, PersonDto dto) {
		person.setBirthDate(dto.getBirthDate());
		person.setName(dto.getName());
		person.setEmail(dto.getEmail());
		return person;
	}

//	@Deprecated
//	public static Person fromRequest(PersonRequest request) {
//		Person person = new Person();
//		person.setBirthDate(request.getBirthDate());
//		person.setName(request.getName());
//		person.setEmail(request.getEmail());
//		return person;
//	}
}
