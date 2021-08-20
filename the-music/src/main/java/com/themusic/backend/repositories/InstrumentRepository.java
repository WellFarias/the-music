package com.themusic.backend.repositories;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;
import com.themusic.backend.domain.Instrument;

@Repository
public interface InstrumentRepository extends MongoRepository<Instrument, String>{

}
