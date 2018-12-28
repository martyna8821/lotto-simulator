import org.junit.*;

import pl.martyna.lotto.exceptions.IllegalValueException;
import pl.martyna.lotto.services.Draw;

import java.util.Collections;
import java.util.Set;
import static org.junit.Assert.*;

/**
 * This class contains JUnit tests for class Draw
 * @author Martyna Drabinska
 * @version 1.0
 */
public class DrawTest {

    /** Draw class object for testing */
    private Draw draw;

    /** Default constructor */
    public DrawTest(){}

    /** Before each test creates new instance od Draw class  */
    @Before
    public void setUp(){
        draw = new Draw();
    }

    /**
     * Tests if setting incorrect range values throws an IllegalValueException
     * @throws IllegalValueException exception that should be thrown for the incorrect range
     */
    @Test(expected = IllegalValueException.class)
    public void testIncorrectRange() throws IllegalValueException{
        draw.changeSettings(10,2,2);
    }

    /**
     * Tests if trying to set quantity bigger then the range of drawn numbers throws a IllegalValueException
     * @throws IllegalValueException exception that should be thrown for too big quantity
     */
    @Test(expected = IllegalValueException.class)
    public void testQuantityOutOfRange() throws IllegalValueException{
        draw.changeSettings(1,10,20);
    }

    /** Tests if setting correct settings values will succeeded */
    @Test
    public void testCorrectSettings(){
        try{
            draw.changeSettings(1,30, 5);
            assertEquals("Min not set",1, draw.getMin());
            assertEquals("Max not set", 30, draw.getMax());
            assertEquals("Quantity not set",5, draw.getQuantity());
        }
        catch (IllegalValueException ex){
            fail("Should not throw IllegalValueException for correct settings values");
        }
    }

    /** Tests if drawn results set is not null */
    @Test
    public void testIfDrawnSetNotNull(){
        assertNotNull("Drawn result set is null",draw.randomResults());
    }

    /** Tests if quantity of drawn results matches the settings quantity value */
    @Test
    public void testQuantityOfDrawnNumbers(){
        assertEquals("Incorrect quantity of drawn results",draw.randomResults().size(), draw.getQuantity());
    }

    /** Tests if min value of result in drawn set is not smaller then settings min value */
    @Test
    public void testMinDrawnNumber(){
        Set<Integer> results = draw.randomResults();
        if(Collections.min(results) < draw.getMin())
                fail("Drawn minimum result smaller then min");

    }
    /** Tests if max value of result in drawn set is not bigger then settings max value */
    @Test
    public void testMaxDrawnNumber(){
        Set<Integer> results = draw.randomResults();
        if(   Collections.max(results) > draw.getMax())
            fail("Drawn maximum result bigger then max");
    }

    /** Tests if each draw simulation returns different results  */
    @Test
    public void testIfEachDrawIsDifferent(){
        Set<Integer> results1 = draw.randomResults();
        Set<Integer> results2 = draw.randomResults();
        assertFalse(results1.containsAll(results2));
    }

}

