/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.eci.arsw.blueprints.test.services;

import edu.eci.arsw.blueprints.model.Blueprint;
import edu.eci.arsw.blueprints.model.Point;
import edu.eci.arsw.blueprints.persistence.BlueprintNotFoundException;
import edu.eci.arsw.blueprints.persistence.BlueprintPersistenceException;
import edu.eci.arsw.blueprints.persistence.impl.InMemoryBlueprintPersistence;
import edu.eci.arsw.blueprints.services.BlueprintsServices;
import java.util.HashSet;
import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.junit.Test;
import static org.junit.Assert.*;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 *
 * @author hcadavid
 */
public class InMemoryPersistenceTest {
    
    @Test
    public void saveNewAndLoadTest() throws BlueprintPersistenceException, BlueprintNotFoundException{
        InMemoryBlueprintPersistence ibpp=new InMemoryBlueprintPersistence();

        Point[] pts0=new Point[]{new Point(40, 40),new Point(15, 15)};
        Blueprint bp0=new Blueprint("mack", "mypaint",pts0);
        
        ibpp.saveBlueprint(bp0);
        
        Point[] pts=new Point[]{new Point(0, 0),new Point(10, 10)};
        Blueprint bp=new Blueprint("john", "thepaint",pts);
        
        ibpp.saveBlueprint(bp);
        
        assertNotNull("Loading a previously stored blueprint returned null.",ibpp.getBlueprint(bp.getAuthor(), bp.getName()));
        
        assertEquals("Loading a previously stored blueprint returned a different blueprint.",ibpp.getBlueprint(bp.getAuthor(), bp.getName()), bp);
        
    }


    @Test
    public void saveExistingBpTest() {
        InMemoryBlueprintPersistence ibpp=new InMemoryBlueprintPersistence();
        
        Point[] pts=new Point[]{new Point(0, 0),new Point(10, 10)};
        Blueprint bp=new Blueprint("john", "thepaint",pts);
        
        try {
            ibpp.saveBlueprint(bp);
        } catch (BlueprintPersistenceException ex) {
            fail("Blueprint persistence failed inserting the first blueprint.");
        }
        
        Point[] pts2=new Point[]{new Point(10, 10),new Point(20, 20)};
        Blueprint bp2=new Blueprint("john", "thepaint",pts2);

        try{
            ibpp.saveBlueprint(bp2);
            fail("An exception was expected after saving a second blueprint with the same name and autor");
        }
        catch (BlueprintPersistenceException ex){
            
        }
                
        
    }
    
    @Test
    public void saveAndGetBlueprintTest() throws BlueprintPersistenceException {
        ApplicationContext ac = new ClassPathXmlApplicationContext("applicationContext.xml");
        BlueprintsServices bs = ac.getBean(BlueprintsServices.class);

        Point[] pts = new Point[]{new Point(2, 2), new Point(11, 11)};
        Blueprint bp = new Blueprint("Clark", "MyFirstDraw", pts);

        bs.addNewBlueprint(bp);

        Blueprint temp = null;
        try {
            temp = bs.getBlueprint("Clark", "MyFirstDraw");
        } catch (BlueprintNotFoundException ex) {
            fail("An exception was expected after saving a second blueprint with the same name and autor");
        }
        assertEquals(temp, bp);

    }

    @Test
    public void saveAndGetBlueprintTest2() throws BlueprintPersistenceException {
        ApplicationContext ac = new ClassPathXmlApplicationContext("applicationContext.xml");
        BlueprintsServices bs = ac.getBean(BlueprintsServices.class);

        Point[] pts = new Point[]{new Point(2, 2), new Point(11, 11)};
        Blueprint bp = new Blueprint("Josh", "Architec", pts);
        Point[] pts2 = new Point[]{new Point(12, 12), new Point(20, 20)};
        Blueprint bp2 = new Blueprint("Josh", "Architec v2", pts2);

        bs.addNewBlueprint(bp);
        bs.addNewBlueprint(bp2);

        Set<Blueprint> temp = new HashSet<>();
        try {
            temp = bs.getBlueprintsByAuthor("Josh");
        } catch (BlueprintNotFoundException ex) {
            fail("An exception was expected after saving a second blueprint with the same name and autor");
        }
        assertEquals(2, temp.size());

    }
    
    
    /**
     * Si se quiere probar este test asegurese que la inyeccion del filtro sea la correspondiente.
     */
    //@Test
    public void filtroRedundanciaBlueprintTest() throws BlueprintPersistenceException {
        ApplicationContext ac = new ClassPathXmlApplicationContext("applicationContext.xml");
        BlueprintsServices bs = ac.getBean(BlueprintsServices.class);

        Point[] pts = new Point[]{new Point(0, 0), new Point(0, 0), new Point(0, 0), new Point(0, 0)};
        Blueprint bp = new Blueprint("Josh", "Architec", pts);
        Point[] pts2 = new Point[]{new Point(12, 12), new Point(20, 20)};
        Blueprint bp2 = new Blueprint("Josh", "Architec v2", pts2);
        Point[] pts3 = new Point[]{new Point(0, 0), new Point(1, 1), new Point(2, 2), new Point(3, 3), new Point(4, 4)};
        Blueprint bp3 = new Blueprint("Clark", "MyFirstDraw", pts3);

        bs.addNewBlueprint(bp);
        bs.addNewBlueprint(bp2);
        bs.addNewBlueprint(bp3);
        
        try {
            assertEquals(bs.getBlueprint("Josh", "Architec").getPoints().size(), 1);
            assertEquals(bs.getBlueprint("Clark", "MyFirstDraw").getPoints().size(), 5);
        } catch (BlueprintNotFoundException ex) {
            Logger.getLogger(InMemoryPersistenceTest.class.getName()).log(Level.SEVERE, null, ex);
        }

    }
    
    /**
     * Si se quiere probar este test asegurese que la inyeccion del filtro sea la correspondiente.
     */
    @Test
    public void filtroSubmuestreoBlueprintTest() throws BlueprintPersistenceException {
        ApplicationContext ac = new ClassPathXmlApplicationContext("applicationContext.xml");
        BlueprintsServices bs = ac.getBean(BlueprintsServices.class);

        Point[] pts = new Point[]{new Point(0, 0), new Point(0, 0), new Point(0, 0), new Point(0, 0)};
        Blueprint bp = new Blueprint("Josh", "Architec", pts);
        Point[] pts2 = new Point[]{new Point(12, 12), new Point(20, 20)};
        Blueprint bp2 = new Blueprint("Josh", "Architec v2", pts2);
        Point[] pts3 = new Point[]{new Point(0, 0), new Point(1, 1), new Point(2, 2), new Point(3, 3), new Point(4, 4)};
        Blueprint bp3 = new Blueprint("Clark", "MyFirstDraw", pts3);

        bs.addNewBlueprint(bp);
        bs.addNewBlueprint(bp2);
        bs.addNewBlueprint(bp3);

        try {
            assertEquals(bs.getBlueprint("Josh", "Architec").getPoints().size(), 1);
            assertEquals(bs.getBlueprint("Clark", "MyFirstDraw").getPoints().size(), 5);
        } catch (BlueprintNotFoundException ex) {
            Logger.getLogger(InMemoryPersistenceTest.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
