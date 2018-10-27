/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.eci.arsw.blueprints.services;

import edu.eci.arsw.blueprints.model.Blueprint;
import edu.eci.arsw.blueprints.model.Point;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import org.springframework.stereotype.Service;

/**
 *
 * @author dbeltran
 */

public class FiltroSubmuestreo implements Filter{
    
    @Override
    public Blueprint filtrarIndividual(Blueprint b) {
        List<Point> temp = new ArrayList<>();
        List<Point> temp2 =b.getPoints();
        for(int i=0;i<temp2.size();i++){
            if(i%2==0){
                temp.add(temp2.get(i));
            } 
        }
        b.setPoints(temp);
        return b;
    }

    @Override
    public Set<Blueprint> filtrarTodos(Set<Blueprint> bs) {
        Set<Blueprint>  ans=new HashSet<>();
        for(Blueprint b:bs){
            ans.add(filtrarIndividual(b));
        }
        return ans;
    }
}
