package com.souleimen.avions.restcontrollers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.souleimen.avions.entities.Avion;
import com.souleimen.avions.service.AvionService;

@RestController
@RequestMapping("/api")
@CrossOrigin(origins = "*") // Allow requests from any origin
public class AvionRESTController {

    @Autowired
    private AvionService avionService;

    @RequestMapping(path = "/all", method = RequestMethod.GET)
    public List<Avion> getAllAvions() {
        return avionService.getAllAvions();
    }

    @RequestMapping(value = "/getbyid/{id}", method = RequestMethod.GET)
    public Avion getAvionById(@PathVariable("id") Long id) {
        return avionService.getAvion(id);
    }

    @RequestMapping(path = "/addavio", method = RequestMethod.POST)
    @PreAuthorize("hasAuthority('ADMIN')")
    public Avion createAvion(@RequestBody Avion avion) {
        return avionService.saveAvion(avion);
    }

    @RequestMapping(path = "/updateavio", method = RequestMethod.PUT)
    public Avion updateAvion(@RequestBody Avion avion) {
        return avionService.updateAvion(avion);
    }

    @RequestMapping(value = "/delavio/{id}", method = RequestMethod.DELETE)
    public void deleteAvion(@PathVariable("id") Long id) {
        avionService.deleteAvionById(id);
    }

    @RequestMapping(value = "/aviostyp/{idAv}", method = RequestMethod.GET)
    public List<Avion> findByTypeAvIdAv(@PathVariable("idAv") Long idAv) {
        return avionService.findByTypeAvIdA(idAv);
    }

    @RequestMapping(value = "/aviosByMatricule/{nom}", method = RequestMethod.GET)
    public List<Avion> findByNomAvionContains(@PathVariable("nom") String nom) {
        return avionService.findByMatriculeAvion(nom);
    }
}
