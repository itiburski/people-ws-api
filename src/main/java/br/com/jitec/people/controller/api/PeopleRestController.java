package br.com.jitec.people.controller.api;

import java.util.List;

import javax.validation.Valid;

import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.jitec.people.controller.request.PersonRequest;
import br.com.jitec.people.controller.response.PersonResponse;
import br.com.jitec.people.service.PeopleService;
import br.com.jitec.people.service.dto.PersonDto;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@Api(tags = "People manipulation")
@RestController
@RequestMapping("/people")
public class PeopleRestController {

	@Autowired
	private PeopleService peopleService;

	@Autowired
	private ModelMapper modelMapper;

	private Logger logger = LoggerFactory.getLogger(PeopleRestController.class);

	@ApiOperation(value = "Get all the people in the database")
	@ApiResponses(value = { @ApiResponse(code = 200, message = "OK") })
	@GetMapping(produces = { MediaType.APPLICATION_JSON_VALUE })
	public List<PersonResponse> getPeople() {
		List<PersonDto> peopleDto = peopleService.getAll();

		java.lang.reflect.Type listType = new TypeToken<List<PersonResponse>>() {
		}.getType();

		return modelMapper.map(peopleDto, listType);
	}

	@ApiOperation(value = "Gets the Person with the specified publicId")
	@ApiResponses(value = { @ApiResponse(code = 200, message = "OK"), @ApiResponse(code = 204, message = "No Content"),
			@ApiResponse(code = 404, message = "Not Found") })
	@GetMapping(path = "/{publicId}", produces = { MediaType.APPLICATION_JSON_VALUE })
	public ResponseEntity<PersonResponse> getPerson(@PathVariable String publicId) {
		PersonDto personDto = peopleService.get(publicId);

		if (personDto != null) {
			PersonResponse response = modelMapper.map(personDto, PersonResponse.class);
			return new ResponseEntity<>(response, HttpStatus.OK);
		} else {
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		}
	}

	@ApiOperation(value = "Creates a new Person")
	@PostMapping(consumes = { MediaType.APPLICATION_JSON_VALUE }, produces = { MediaType.APPLICATION_JSON_VALUE })
	public ResponseEntity<PersonResponse> createPerson(@Valid @RequestBody PersonRequest request) {
		PersonDto personDto = modelMapper.map(request, PersonDto.class);
		PersonDto savedPerson = peopleService.create(personDto);

		if (savedPerson != null) {
			PersonResponse response = modelMapper.map(savedPerson, PersonResponse.class);
			return new ResponseEntity<>(response, HttpStatus.CREATED);
		} else {
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		}
	}

	@ApiOperation(value = "Updates the Person with the specified publicId")
	@ApiResponses(value = { @ApiResponse(code = 200, message = "OK"), @ApiResponse(code = 204, message = "No Content"),
			@ApiResponse(code = 404, message = "Not Found") })
	@PutMapping(path = "/{publicId}", consumes = { MediaType.APPLICATION_JSON_VALUE }, produces = {
			MediaType.APPLICATION_JSON_VALUE })
	public ResponseEntity<PersonResponse> updatePerson(@PathVariable String publicId,
			@Valid @RequestBody PersonRequest request) {
		PersonDto personDto = modelMapper.map(request, PersonDto.class);
		PersonDto updatedPerson = peopleService.update(publicId, personDto);

		if (updatedPerson != null) {
			PersonResponse response = modelMapper.map(updatedPerson, PersonResponse.class);
			return new ResponseEntity<>(response, HttpStatus.OK);
		} else {
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		}
	}

	@ApiOperation(value = "Deletes the Person with the specified publicId")
	@ApiResponses(value = { @ApiResponse(code = 204, message = "No Content"),
			@ApiResponse(code = 404, message = "Not Found") })
	@DeleteMapping(path = "/{publicId}")
	public ResponseEntity<PersonResponse> deletePerson(@PathVariable String publicId) {
		peopleService.delete(publicId);
		return new ResponseEntity<>(HttpStatus.NO_CONTENT);
	}

}
