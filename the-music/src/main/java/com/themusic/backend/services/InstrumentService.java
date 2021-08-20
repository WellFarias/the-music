package com.themusic.backend.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;

import com.themusic.backend.domain.Instrument;
import com.themusic.backend.dto.InstrumentDTO;
import com.themusic.backend.repositories.InstrumentRepository;
import com.themusic.backend.services.exception.ObjectNotFoundException;

@Service
public class InstrumentService {

	@Autowired
	private InstrumentRepository instrumentRepository;
	
	public List<Instrument> findAll() {
		return instrumentRepository.findAll();
	}
	
	public Optional<Instrument> findById(String id) {
		Optional<Instrument> instrument = instrumentRepository.findById(id);
		
		if (instrument == null) {
			throw new ObjectNotFoundException("Instrumento não encontrado");
		}
		
		return instrument;
	}
	
	public Instrument save(Instrument instrument) {
		return instrumentRepository.insert(instrument);
	}
	
	public Instrument update(Instrument instrument) {
		Optional<Instrument> newInstrument = instrumentRepository.findById(instrument.getId());
		updateData(newInstrument, instrument);
		return instrumentRepository.save(newInstrument.get());
	}

	private void updateData(Optional<Instrument> newInstrument, Instrument instrument) {
		newInstrument.get().setName(instrument.getName());
		newInstrument.get().setBrand(instrument.getBrand());
		newInstrument.get().setCategory(instrument.getCategory());
		newInstrument.get().setYear(instrument.getYear());
		newInstrument.get().setDescription(instrument.getDescription());
		newInstrument.get().setIsUsed(instrument.getIsUsed());
		newInstrument.get().setColor(instrument.getColor());
		newInstrument.get().setFrom(instrument.getFrom());
		newInstrument.get().setDelivery(instrument.getDelivery());
		newInstrument.get().setPrice(instrument.getPrice());
		newInstrument.get().setPlots(instrument.getPlots());
		newInstrument.get().setPicture(instrument.getPicture());
		
	}

	public void delete(String id) {
		findById(id);
		instrumentRepository.deleteById(id);
	}
	
	public Instrument fromDTO(InstrumentDTO instrumentDto) {
		return new Instrument(
				instrumentDto.getId(), 
				instrumentDto.getName(), 
				instrumentDto.getBrand(), 
				instrumentDto.getCategory(),
				instrumentDto.getYear(),
				instrumentDto.getDescription(),
				instrumentDto.getIsUsed(),
				instrumentDto.getColor(),
				instrumentDto.getFrom(),
				instrumentDto.getDelivery(),
				instrumentDto.getPrice(),
				instrumentDto.getPlots(),
				instrumentDto.getPicture()
		);
	}
	
	public Page<Instrument> findPage(Integer page, Integer linesPerPage, String orderBy, String direction) {
		PageRequest pageRequest = PageRequest.of(page, linesPerPage, Direction.valueOf(direction), orderBy);
		return instrumentRepository.findAll(pageRequest);
	}
}
