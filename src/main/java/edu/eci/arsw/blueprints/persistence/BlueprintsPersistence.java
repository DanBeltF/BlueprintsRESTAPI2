/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.eci.arsw.blueprints.persistence;

import edu.eci.arsw.blueprints.model.Blueprint;
import java.util.Set;

/**
 *
 * @author hcadavid
 */
public interface BlueprintsPersistence {
    
    /**
     * 
     * @param bp the new blueprint
     * @throws BlueprintPersistenceException if a blueprint with the same name already exists,
     *    or any other low-level persistence error occurs.
     */
    public void saveBlueprint(Blueprint bp) throws BlueprintPersistenceException;
    
    /**
     * 
     * @param author blueprint's author
     * @param bprintname blueprint's author
     * @return the blueprint of the given name and author
     * @throws BlueprintNotFoundException if there is no such blueprint
     */
    public Blueprint getBlueprint(String author,String bprintname) throws BlueprintNotFoundException;
    
    /**
     * 
     * @param author blueprint's author
     * @return the blueprints of the given author
     * @throws BlueprintNotFoundException if there is no such blueprint
     */
    public Set<Blueprint> getBlueprintByAutor(String author) throws BlueprintNotFoundException;
    
    
    /**
     *
     * @return the set of all blueprints 
     * @throws BlueprintNotFoundException if there is no such blueprint
     */
    public Set<Blueprint> getAllBlueprint() throws BlueprintNotFoundException;
    
    
    /**
     * 
     * @param bp blueprint a actualizar
     * @param author blueprint's author
     * @param name blueprint's name
     * @throws edu.eci.arsw.blueprints.persistence.BlueprintPersistenceException
     */
    public void updateBlueprintPoints(Blueprint bp,String author,String name) throws BlueprintPersistenceException;
    
    /**
     * 
     * @param author
     * @param name
     * @throws edu.eci.arsw.blueprints.persistence.BlueprintPersistenceException
     */
    public void deleteBlueprint(String author,String name) throws BlueprintPersistenceException;
}
