import org.junit.*;

import pl.martyna.lotto.exceptions.IllegalValueException;
import pl.martyna.lotto.service.SimulationServiceImp;

import java.util.Collections;
import java.util.Set;
import static org.junit.Assert.*;

/**
 * This class contains JUnit tests for class Draw
 * @author Martyna Drabinska
 * @version 1.0
 */
public class SimulationTest {

    /** Draw class object for testing */
    private SimulationServiceImp simulationService;

    /** Default constructor */
    public SimulationTest(){}

    /** Before each test creates new instance od Draw class  */
    @Before
    public void setUp(){
        this.simulationService = new SimulationServiceImp();
    }

    /**
     * Tests if setting incorrect range values throws an IllegalValueException
     * @throws IllegalValueException exception that should be thrown for the incorrect range
     */
    @Test(expected = IllegalValueException.class)
    public void testIncorrectRange() throws IllegalValueException{
        simulationService.changeSettings(10,2,2);
    }

    /**
     * Tests if trying to set quantity bigger then the range of drawn numbers throws a IllegalValueException
     * @throws IllegalValueException exception that should be thrown for too big quantity
     */
    @Test(expected = IllegalValueException.class)
    public void testQuantityOutOfRange() throws IllegalValueException{
        simulationService.changeSettings(1,10,20);
    }

    /** Tests if setting correct settings values will succeeded */
    @Test
    public void testCorrectSettings(){
        try{
            simulationService.changeSettings(1,30, 5);
            assertEquals("Min not set",1, simulationService.getMin());
            assertEquals("Max not set", 30, simulationService.getMax());
            assertEquals("Quantity not set",5, simulationService.getQuantity());
        }
        catch (IllegalValueException ex){
            fail("Should not throw IllegalValueException for correct settings values");
        }
    }

    /** Tests if drawn results set is not null */
    @Test
    public void testIfDrawnSetNotNull(){
        assertNotNull("Drawn result set is null",simulationService.randomResults());
    }

    /** Tests if quantity of drawn results matches the settings quantity value */
    @Test
    public void testQuantityOfDrawnNumbers(){
        assertEquals("Incorrect quantity of drawn results",
                simulationService.randomResults().size(), simulationService.getQuantity());
    }

    /** Tests if min value of result in drawn set is not smaller then settings min value */
    @Test
    public void testMinDrawnNumber(){
        Set<Integer> results = simulationService.randomResults();
        if(Collections.min(results) < simulationService.getMin())
                fail("Drawn minimum result smaller then min");

    }
    /** Tests if max value of result in drawn set is not bigger then settings max value */
    @Test
    public void testMaxDrawnNumber(){
        Set<Integer> results = simulationService.randomResults();
        if(   Collections.max(results) > simulationService.getMax())
            fail("Drawn maximum result bigger then max");
    }

    /** Tests if each draw simulation returns different results  */
    @Test
    public void testIfEachDrawIsDifferent(){
        Set<Integer> results1 = simulationService.randomResults();
        Set<Integer> results2 = simulationService.randomResults();
        assertFalse(results1.containsAll(results2));
    }

}

