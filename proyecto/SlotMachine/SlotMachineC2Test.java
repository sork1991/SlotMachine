import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

/**
 * The test class slotMachineTest.
 *
 * @author  (your name)
 * @version (a version number or a date)
 */
public class SlotMachineC2Test
{
    private SlotMachine slotMachine;
    
    /**
     * Default constructor for test class slotMachineTest
     */
    public SlotMachineC2Test()
    {
    }

    /**
     * Sets up the test fixture.
     *
     * Called before every test case method.
     */
    @BeforeEach
    public void setUp()
    {
        slotMachine = new SlotMachine();
    }
    
    @Test
    public void shouldSwapTwoWheels()
    {
        slotMachine.addWheel(1);
        slotMachine.addWheel(2);
        slotMachine.addWheel(3);
        slotMachine.addSymbol(1,"red","triangle");
        slotMachine.addSymbol(2,"blue","rectangle");
        slotMachine.addSymbol(3,"yellow","circle");
        slotMachine.placeSymbol(1, "red");
        slotMachine.placeSymbol(2, "blue");
        slotMachine.placeSymbol(3, "yellow");
        slotMachine.swap(1, 3);
        String[] esperado = new String[]{"yellow", "blue", "red"};
        String[] resultado = slotMachine.configuration();
        assertArrayEquals(esperado, resultado);
    }
    
    @Test
    public void shouldNotChangeWhenSwappingSamePosition()
    {
        slotMachine.addWheel(1);
        slotMachine.addWheel(2);
        slotMachine.addSymbol(1,"red","triangle");
        slotMachine.addSymbol(2,"blue","rectangle");
        slotMachine.placeSymbol(1, "red");
        slotMachine.placeSymbol(2, "blue");
        slotMachine.swap(1, 1);
        String[] esperado = new String[]{"red", "blue"};
        String[] resultado = slotMachine.configuration();
        assertArrayEquals(esperado, resultado);
    }
    
    @Test
    public void shouldChangeSymbolWhenUnlocked()
    {
        slotMachine.addWheel(1);
        slotMachine.addSymbol(1,"red","triangle");
        slotMachine.addSymbol(2,"blue","rectangle");
        slotMachine.addSymbol(3,"yellow","circle");
        slotMachine.addSymbol(4,"purple","circle");
        slotMachine.addSymbol(5,"black","triangle");
        slotMachine.placeSymbol(1, "red");
        slotMachine.lock(1);
        slotMachine.unlock(1);
        String colorAntes = slotMachine.configuration()[0];
        slotMachine.spin(1);
        String colorAhora = slotMachine.configuration()[0];
        assertNotEquals(colorAntes, colorAhora);
    }
    
    @Test
    public void shouldNotChangeSymbolInWheelLock()
    {
        slotMachine.addWheel(1);
        slotMachine.addSymbol(1,"red","triangle");
        slotMachine.addSymbol(2,"blue","rectangle");
        slotMachine.addSymbol(3,"yellow","circle");
        slotMachine.addSymbol(4,"purple","circle");
        slotMachine.addSymbol(5,"black","triangle");
        slotMachine.placeSymbol(1, "red");
        slotMachine.lock(1);
        String[] colorAntes = slotMachine.configuration();
        slotMachine.spin(1);
        String[] colorAhora = slotMachine.configuration();
        assertArrayEquals(colorAntes, colorAhora);
    }
    
    @Test
    public void shouldMoveToCorrectSymbolAfterSteps()
    {
        slotMachine.addWheel(1);
        slotMachine.addSymbol(1,"red","triangle");
        slotMachine.addSymbol(2,"blue","rectangle");
        slotMachine.addSymbol(3,"yellow","circle");
        slotMachine.addSymbol(4,"purple","circle");
        slotMachine.placeSymbol(1, "red");
        slotMachine.spin(1, 2);
        String resultado = slotMachine.configuration()[0];
        assertEquals("yellow", resultado);
    }
    
    @Test
    public void shouldNotMoveWhenWheelIsLocked()
    {
        slotMachine.addWheel(1);
        slotMachine.addSymbol(1,"red","triangle");
        slotMachine.addSymbol(2,"blue","rectangle");
        slotMachine.addSymbol(3,"yellow","circle");
        slotMachine.addSymbol(4,"purple","circle");
        slotMachine.placeSymbol(1, "red");
        slotMachine.lock(1);
        String colorAntes = slotMachine.configuration()[0];
        slotMachine.spin(1, 3);
        String resultado = slotMachine.configuration()[0];
        assertEquals(colorAntes, resultado);
    }
    
    @Test
    public void shouldSetExactConfiguration()
    {
        slotMachine.addWheel(1);
        slotMachine.addWheel(2);
        slotMachine.addSymbol(1,"red","triangle");
        slotMachine.addSymbol(2,"blue","rectangle");
        slotMachine.addSymbol(3,"yellow","circle");
        slotMachine.addSymbol(4,"purple","circle");
        slotMachine.spin(new String[]{"red", "blue"});
        String[] resultado = slotMachine.configuration();
        assertArrayEquals(new String[]{"red", "blue"}, resultado);
    }
    
    @Test
    public void shouldNotChangeWhenTooManySymbolsGiven()
    {
        slotMachine.addWheel(1);
        slotMachine.addWheel(2);
        slotMachine.addSymbol(1,"red","triangle");
        slotMachine.addSymbol(2,"blue","rectangle");
        slotMachine.addSymbol(3,"yellow","circle");
        slotMachine.addSymbol(4,"purple","circle");
        slotMachine.placeSymbol(1, "red");
        slotMachine.placeSymbol(2, "blue");
        String[] Antes = slotMachine.configuration();
        slotMachine.spin(new String[]{"red", "blue","yellow"});
        String[] resultado = slotMachine.configuration();
        assertArrayEquals(Antes, resultado);
    }

    @Test
    public void accordingDrRmShouldBeJackpotWhenAllWheelsMatch()
    {
        slotMachine.addWheel(1);
        slotMachine.addWheel(2);
        slotMachine.addWheel(3);
        slotMachine.addSymbol(1, "red", "triangle");
        slotMachine.addSymbol(2, "blue", "rectangle");
        slotMachine.spin(new String[]{"red", "red", "red"});
        assertTrue(slotMachine.isJackpot());
    }
    
    @Test
    public void accordingDrRmShouldKeepCorrectWheelCountAfterAddAndDelete()
    {
        slotMachine.addWheel(1);
        slotMachine.addWheel(2);
        slotMachine.addWheel(3);
        slotMachine.delWheel(2);
        slotMachine.addSymbol(1, "red", "triangle");
        slotMachine.spin(new String[]{"red", "red"});
        assertEquals(2, slotMachine.configuration().length);
    }
        
    /**
     * Tears down the test fixture.
     *
     * Called after every test case method.
     */
    @AfterEach
    public void tearDown()
    {
        slotMachine.makeInvisible();
    }
}