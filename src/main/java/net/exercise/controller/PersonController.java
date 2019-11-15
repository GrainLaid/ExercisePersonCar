package net.exercise.controller;

import net.exercise.entity.PersonEntity;
import net.exercise.service.PersonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class PersonController {

    private PersonService personService;

    @Autowired
    public void setPersonService(PersonService personService) {
        this.personService = personService;
    }
    @PostMapping("/person")
    public ResponseEntity<?> save(@RequestBody PersonEntity personEntity) {
        long id = personService.save(personEntity);
        return ResponseEntity.ok().body("New person has been saved with ID:" + id);
    }

    @GetMapping("/person/{id}")
    public ResponseEntity<PersonEntity> get(@PathVariable("id") long id) {
        PersonEntity personEntity = personService.get(id);
        return ResponseEntity.ok().body(personEntity);
    }

    @GetMapping("/person")
    public ResponseEntity<List<PersonEntity>> list() {
        List<PersonEntity> personEntityList = personService.list();
        return ResponseEntity.ok().body(personEntityList);
    }

    @DeleteMapping("/person/{id}")
    public ResponseEntity<?> delete(@PathVariable("id") long id) {
        personService.delete(id);
        return ResponseEntity.ok().body("Person has been deleted successfully.");
    }

}
