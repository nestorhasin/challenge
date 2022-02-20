package org.alkemy.challenge.java.controllers;

import javax.validation.Valid;

import org.alkemy.challenge.java.DTOs.CharacterDTO;
import org.alkemy.challenge.java.services.interfaces.ICharacterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/characters")
public class CharacterController {
    
    @Autowired
    private ICharacterService iCharacterService;

    @GetMapping
    public ResponseEntity<?> getCharacters(
        @RequestParam(value = "name", required = false) String name,
        @RequestParam(value = "age", required = false) Integer age,
        @RequestParam(value = "movie", required = false) Long id,
        @RequestParam(value = "weight", required = false) Double weight){
        if(name != null) return new ResponseEntity<>(iCharacterService.readAllByName(name), HttpStatus.OK);
        if(age != null) return new ResponseEntity<>(iCharacterService.readAllByAge(age), HttpStatus.OK);
        if(id != null) return new ResponseEntity<>(iCharacterService.readAllByMovie(id), HttpStatus.OK);
        if(weight != null) return new ResponseEntity<>(iCharacterService.readAllByWeight(weight), HttpStatus.OK);
        return new ResponseEntity<>(iCharacterService.read(), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getCharacterById(@PathVariable Long id){
        return new ResponseEntity<>(iCharacterService.readById(id), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<?> createCharacter(@Valid @RequestBody CharacterDTO characterDTO){
        return new ResponseEntity<>(iCharacterService.create(characterDTO), HttpStatus.CREATED);
    }

    @PutMapping
    public ResponseEntity<?> updateCharacter(@Valid @RequestBody CharacterDTO characterDTO){
        return new ResponseEntity<>(iCharacterService.update(characterDTO), HttpStatus.NO_CONTENT);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteCharacter(@PathVariable Long id){
        iCharacterService.delete(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

}
