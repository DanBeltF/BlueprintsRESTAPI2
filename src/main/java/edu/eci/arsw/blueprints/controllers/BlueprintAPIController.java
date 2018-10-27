/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.eci.arsw.blueprints.controllers;

import edu.eci.arsw.blueprints.model.Blueprint;
import edu.eci.arsw.blueprints.persistence.BlueprintNotFoundException;
import edu.eci.arsw.blueprints.persistence.BlueprintPersistenceException;
import edu.eci.arsw.blueprints.services.BlueprintsServices;
import java.util.LinkedHashSet;
import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author hcadavid
 */
@RestController
@RequestMapping(value = "/blueprints")
public class BlueprintAPIController {
    @Autowired
    BlueprintsServices bpp;

    @RequestMapping(method = RequestMethod.GET)
    public ResponseEntity<?> getAllBlueprint() {
        try {
            //obtener datos que se enviarán a través del API
            return new ResponseEntity<>(bpp.getAllBlueprints(), HttpStatus.ACCEPTED);
        } catch (BlueprintNotFoundException ex) {
            Logger.getLogger(BlueprintAPIController.class.getName()).log(Level.SEVERE, null, ex);
            return new ResponseEntity<>(ex.getMessage(), HttpStatus.NOT_FOUND);
        }
    }

    @RequestMapping(path = "/{author}", method = RequestMethod.GET)
    public ResponseEntity<?> getBlueprintForAuthor(@PathVariable String author) {
        try {
            //obtener datos que se enviarán a través del API
            return new ResponseEntity<>(bpp.getBlueprintsByAuthor(author), HttpStatus.ACCEPTED);
        } catch (BlueprintNotFoundException ex) {
            Logger.getLogger(BlueprintAPIController.class.getName()).log(Level.SEVERE, null, ex);
            return new ResponseEntity<>(ex.getMessage(), HttpStatus.NOT_FOUND);
        }
    }

    @RequestMapping(path = "/{author}/{name}", method = RequestMethod.GET)
    public ResponseEntity<?> getBlueprintForAuthorName(@PathVariable String author, @PathVariable String name) {
        try {
            //obtener datos que se enviarán a través del API
            return new ResponseEntity<>(bpp.getBlueprint(author, name), HttpStatus.ACCEPTED);
        } catch (BlueprintNotFoundException ex) {
            Logger.getLogger(BlueprintAPIController.class.getName()).log(Level.SEVERE, null, ex);
            return new ResponseEntity<>(ex.getMessage(), HttpStatus.NOT_FOUND);
        }
    }

    @RequestMapping(method = RequestMethod.POST)
    public ResponseEntity<?> addBlueprint(@RequestBody Blueprint b) {
        try {
            //registrar dato
            bpp.addNewBlueprint(b);
            return new ResponseEntity<>(HttpStatus.CREATED);
        } catch (BlueprintPersistenceException ex) {
            Logger.getLogger(Exception.class.getName()).log(Level.SEVERE, null, ex);
            return new ResponseEntity<>(ex.getMessage(), HttpStatus.FORBIDDEN);
        }

    }
    
    @RequestMapping(path = "/{author}/{name}",method = RequestMethod.PUT)
    public ResponseEntity<?> putBlueprint(@RequestBody Blueprint b,@PathVariable String author,@PathVariable String name) {
        try {
            //actualizar dato
            bpp.updateBlueprintPoints(b,author,name);
            return new ResponseEntity<>(HttpStatus.CREATED);
        } catch (BlueprintPersistenceException ex) {
            Logger.getLogger(Exception.class.getName()).log(Level.SEVERE, null, ex);
            return new ResponseEntity<>(ex.getMessage(), HttpStatus.FORBIDDEN);
        }

    }
    
    @RequestMapping(path = "/{author}/{name}",method = RequestMethod.DELETE)
    public ResponseEntity<?> deleteBlueprint(@PathVariable String author,@PathVariable String name) {
        try {
            //Borrar dato
            bpp.deleteNewBlueprint(author,name);
            return new ResponseEntity<>(HttpStatus.OK);
        } catch (BlueprintPersistenceException ex) {
            Logger.getLogger(Exception.class.getName()).log(Level.SEVERE, null, ex);
            return new ResponseEntity<>(ex.getMessage(), HttpStatus.FORBIDDEN);
        }

    }
    
    
    
    
}

