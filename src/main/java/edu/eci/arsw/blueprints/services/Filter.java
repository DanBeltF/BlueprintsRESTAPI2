/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.eci.arsw.blueprints.services;

import edu.eci.arsw.blueprints.model.Blueprint;
import java.util.Set;

/**
 *
 * @author dbeltran
 */
public interface Filter {
    /**
     *
     * @return the Blueprint filtered.
     */
    public Blueprint filtrarIndividual(Blueprint b);
    
      /**
     *
     * @param bs set of Blueprint that going to be filtered
     * @return set of filtered Blueprints.
     */
    public Set<Blueprint> filtrarTodos(Set<Blueprint> bs);
}
