package com.souleimen.avions.restcontrollers;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.souleimen.avions.entities.TypeAv;
import com.souleimen.avions.repos.TypeAvRepository;

@RestController
@RequestMapping("/api/typ")
@CrossOrigin("*")
public class TypeAvRESTController {

    @Autowired
    private TypeAvRepository typeAvRepository;

    @RequestMapping(method = RequestMethod.GET)
    public List<TypeAv> getAllTypes() {
        return typeAvRepository.findAll();
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public ResponseEntity<TypeAv> getTypeAvById(@PathVariable("id") Long id) {
        Optional<TypeAv> typeAv = typeAvRepository.findById(id);
        return typeAv.map(ResponseEntity::ok)
                     .orElseGet(() -> ResponseEntity.status(HttpStatus.NOT_FOUND).build());
    }

    @RequestMapping(method = RequestMethod.POST)
    public ResponseEntity<TypeAv> createTypeAv(@RequestBody TypeAv typeAv) {
        TypeAv savedTypeAv = typeAvRepository.save(typeAv);
        return ResponseEntity.status(HttpStatus.CREATED).body(savedTypeAv);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    public ResponseEntity<Void> deleteTypeAv(@PathVariable("id") Long id) {
        if (typeAvRepository.existsById(id)) {
            typeAvRepository.deleteById(id);
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.PUT)
    public ResponseEntity<TypeAv> updateTypeAv(@PathVariable("id") Long id, @RequestBody TypeAv typeAv) {
        if (!typeAvRepository.existsById(id)) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
        typeAv.setIdAv(id); // Make sure to set the ID to ensure the correct entity is updated
        TypeAv updatedTypeAv = typeAvRepository.save(typeAv);
        return ResponseEntity.ok(updatedTypeAv);
    }
}
