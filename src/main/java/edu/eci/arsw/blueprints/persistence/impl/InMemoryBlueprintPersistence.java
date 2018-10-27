/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.eci.arsw.blueprints.persistence.impl;

import edu.eci.arsw.blueprints.model.Blueprint;
import edu.eci.arsw.blueprints.model.Point;
import edu.eci.arsw.blueprints.persistence.BlueprintNotFoundException;
import edu.eci.arsw.blueprints.persistence.BlueprintPersistenceException;
import edu.eci.arsw.blueprints.persistence.BlueprintsPersistence;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import org.springframework.stereotype.Service;

/**
 *
 * @author hcadavid
 */
@Service
public class InMemoryBlueprintPersistence implements BlueprintsPersistence{

    private final Map<Tuple<String,String>,Blueprint> blueprints=new HashMap<>();

    public InMemoryBlueprintPersistence() {
        //load stub data
        Point[] pts1 = new Point[]{new Point(140, 140), new Point(115, 115)};
        Point[] pts2 = new Point[]{new Point(64, 321), new Point(32, 87)};
        Point[] pts3 = new Point[]{new Point(142, 142), new Point(117, 117)};
        Point[] pts4 = new Point[]{new Point(143, 143), new Point(118, 118)};
        Blueprint bp1 = new Blueprint("_authorname_", "_bpname_1", pts1);
        Blueprint bp2 = new Blueprint("_authorname_", "_bpname_2", pts2);
        Blueprint bp3 = new Blueprint("_authorname_2", "_bpname_3", pts3);
        Blueprint bp4 = new Blueprint("_authorname_3", "_bpname_4", pts4);
        blueprints.put(new Tuple<>(bp1.getAuthor(), bp1.getName()), bp1);
        blueprints.put(new Tuple<>(bp2.getAuthor(), bp2.getName()), bp2);
        blueprints.put(new Tuple<>(bp3.getAuthor(), bp3.getName()), bp3);
        blueprints.put(new Tuple<>(bp4.getAuthor(), bp4.getName()), bp4);
        
    }    
    
    @Override
    public void saveBlueprint(Blueprint bp) throws BlueprintPersistenceException {
        if (blueprints.containsKey(new Tuple<>(bp.getAuthor(),bp.getName()))){
            throw new BlueprintPersistenceException("The given blueprint already exists: "+bp);
        }
        else{
            blueprints.putIfAbsent(new Tuple<>(bp.getAuthor(),bp.getName()), bp);
        }        
    }

    @Override
    public Blueprint getBlueprint(String author, String bprintname) throws BlueprintNotFoundException {
        Blueprint b = null;
        b = blueprints.get(new Tuple<>(author, bprintname));
        if (b == null) {
            throw new BlueprintNotFoundException("Error HTTP - 404");
        }
        return b;
    }

    @Override
    public Set<Blueprint> getBlueprintByAutor(String author) throws BlueprintNotFoundException {
        Set<Blueprint> ans = new HashSet<>();
        for (Blueprint b : blueprints.values()) {
            if (b.getAuthor().equals(author)) {
                ans.add(b);
            }
        }
        if (ans.isEmpty()) {
            throw new BlueprintNotFoundException("error HTTP 404");
        }
        return ans;
    }

    @Override
    public Set<Blueprint> getAllBlueprint() throws BlueprintNotFoundException {
        Set<Blueprint> ans = new HashSet<>();
        if (!blueprints.values().isEmpty()) {
            ans = new HashSet<>(blueprints.values());
        } else {
            throw new BlueprintNotFoundException("error HTTP 404");
        }
        return ans;
    }
    
    @Override
    public void updateBlueprintPoints(Blueprint bp, String author, String name) throws BlueprintPersistenceException {
        Blueprint b = blueprints.get(new Tuple<>(author, name));
        if (b == null) {
            blueprints.putIfAbsent(new Tuple<>(author, name), bp);
        } else {
            blueprints.get(new Tuple<>(author, name)).setPoints(bp.getPoints());
        }
    }

    @Override
    public void deleteBlueprint(String author, String name) throws BlueprintPersistenceException {
        blueprints.remove(new Tuple<>(author, name));
    }
    
}
