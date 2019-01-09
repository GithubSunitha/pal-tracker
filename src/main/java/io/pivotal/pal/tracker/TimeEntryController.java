package io.pivotal.pal.tracker;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static org.springframework.http.ResponseEntity.ok;

@RestController
public class TimeEntryController {

    private TimeEntryRepository timeEntryRepository;

    public TimeEntryController(TimeEntryRepository timeEntryRepository){
        this.timeEntryRepository = timeEntryRepository;
    }

    @DeleteMapping("/time-entries/{timeEntryId}")
    public ResponseEntity<TimeEntry> delete(@PathVariable long timeEntryId){
        timeEntryRepository.delete(timeEntryId);
       return new ResponseEntity(HttpStatus.NO_CONTENT);
    }

    @GetMapping("/time-entries/{timeEntryId}")
    public ResponseEntity<TimeEntry> read(@PathVariable long timeEntryId){
        TimeEntry entry = timeEntryRepository.find(timeEntryId);;
        if(entry != null)
            return new ResponseEntity<>(entry, HttpStatus.OK);
        return new ResponseEntity(HttpStatus.NOT_FOUND);
    }

    @GetMapping("/time-entries")
    public ResponseEntity<List<TimeEntry>> list(){
        return  new ResponseEntity<>(timeEntryRepository.list(), HttpStatus.OK);
    }

    @PutMapping("/time-entries/{timeEntryId}")
    public ResponseEntity<TimeEntry> update(@PathVariable long timeEntryId,  @RequestBody TimeEntry timeEntry){
        TimeEntry entry = timeEntryRepository.update(timeEntryId, timeEntry);
        if(entry != null)
            return new ResponseEntity<>(entry, HttpStatus.OK);
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @PostMapping("/time-entries")
    public ResponseEntity<TimeEntry> create( @RequestBody TimeEntry timeEntry) {
        TimeEntry entry = timeEntryRepository.create(timeEntry);
        if(entry != null)
            return new ResponseEntity<>(entry, HttpStatus.CREATED);
        return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
     }

}
