package com.themusic.backend.resources;

import java.net.URI;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.themusic.backend.domain.Instrument;
import com.themusic.backend.dto.InstrumentDTO;
import com.themusic.backend.services.InstrumentService;

@RestController
@RequestMapping(value = "/instruments")
public class InstrumentResource {

	@Autowired
	private InstrumentService instrumentService;
	
	@RequestMapping(method = RequestMethod.GET)
	//@CrossOrigin(origins = "http://localhost:8081")
	@CrossOrigin(origins = "http://localhost:4200")
	public ResponseEntity<List<InstrumentDTO>> findAll() {
		List<Instrument> list = instrumentService.findAll();
		List<InstrumentDTO> listDto = list.stream().map(x -> new InstrumentDTO(x)).collect(Collectors.toList());
		return ResponseEntity.ok().body(listDto);
	}
	
	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	//@CrossOrigin(origins = "http://localhost:8081")
	@CrossOrigin(origins = "http://localhost:4200")
	public ResponseEntity<InstrumentDTO> findById(@PathVariable String id) {
		Optional<Instrument> instrument = instrumentService.findById(id);
		return ResponseEntity.ok().body(new InstrumentDTO(instrument.get()));
	}
	
	@RequestMapping(value="/page", method = RequestMethod.GET)
	@CrossOrigin(origins = "http://localhost:8081")
	public ResponseEntity<Page<InstrumentDTO>> findPage(
			@RequestParam(value="page", defaultValue="0") Integer page, 
			@RequestParam(value="linesPerPage", defaultValue="2") Integer linesPerPage, 
			@RequestParam(value="orderBy", defaultValue="name") String orderBy, 
			@RequestParam(value="direction", defaultValue="ASC") String direction) {
		Page<Instrument> list = instrumentService.findPage(page, linesPerPage, orderBy, direction);
		Page<InstrumentDTO> listDto = list.map(x -> new InstrumentDTO(x));
		return ResponseEntity.ok().body(listDto);
	}
	
	@RequestMapping(method = RequestMethod.POST)
	//@CrossOrigin(origins = "http://localhost:8081")
	@CrossOrigin(origins = "http://localhost:4200")
	public ResponseEntity<Void> save(@RequestBody InstrumentDTO instrumentDto) {
		Instrument instrument = instrumentService.fromDTO(instrumentDto);
		instrument = instrumentService.save(instrument);
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(instrument.getId()).toUri();
		return ResponseEntity.created(uri).build();
	}
	
	@RequestMapping(value = "/{id}", method = RequestMethod.PUT)
	@CrossOrigin(origins = "http://localhost:8081")
	public ResponseEntity<Void> update(@RequestBody InstrumentDTO instrumentDto, @PathVariable String id) {
		Instrument instrument = instrumentService.fromDTO(instrumentDto);
		instrument.setId(id);
		instrument = instrumentService.update(instrument);
		return ResponseEntity.noContent().build();
	}
	
	@RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
	@CrossOrigin(origins = "http://localhost:8081")
	public ResponseEntity<Void> delete(@PathVariable String id) {
		instrumentService.delete(id);
		return ResponseEntity.noContent().build();
	}
}
