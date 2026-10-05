import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

/**
 * The test class slotMachineC2Test.
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
    public void shouldSwapTwoWheels() throws SlotMachineException
    {
        slotMachine.addWheel(1, "normal");
        slotMachine.addWheel(2, "normal");
        slotMachine.addWheel(3, "normal");
        slotMachine.addSymbol("normal",1,"red");
        slotMachine.addSymbol("normal",2,"blue");
        slotMachine.addSymbol("normal",3,"yellow");
        slotMachine.placeSymbol(1, "red");
        slotMachine.placeSymbol(2, "blue");
        slotMachine.placeSymbol(3, "yellow");
        slotMachine.swap(1, 3);
        String[] esperado = new String[]{"yellow", "blue", "red"};
        String[] resultado = slotMachine.configuration();
        assertArrayEquals(esperado, resultado);
    }
    
    @Test
    public void shouldNotChangeWhenSwappingSamePosition() throws SlotMachineException
    {
        slotMachine.addWheel(1, "normal");
        slotMachine.addWheel(2, "normal");
        slotMachine.addSymbol("normal",1,"red");
        slotMachine.addSymbol("normal",2,"blue");
        slotMachine.placeSymbol(1, "red");
        slotMachine.placeSymbol(2, "blue");
        slotMachine.swap(1, 1);
        String[] esperado = new String[]{"red", "blue"};
        String[] resultado = slotMachine.configuration();
        assertArrayEquals(esperado, resultado);
    }
    
    @Test
    public void shouldChangeSymbolWhenUnlocked() throws SlotMachineException
    {
        slotMachine.addWheel(1, "normal");
        slotMachine.addSymbol("normal",1,"red");
        slotMachine.addSymbol("normal",2,"blue");
        slotMachine.addSymbol("normal",3,"yellow");
        slotMachine.addSymbol("normal",4,"purple");
        slotMachine.addSymbol("normal",5,"black");
        slotMachine.placeSymbol(1, "red");
        slotMachine.lock(1);
        slotMachine.unlock(1);
        String colorAntes = slotMachine.configuration()[0];
        slotMachine.spin(1);
        String colorAhora = slotMachine.configuration()[0];
        assertNotEquals(colorAntes, colorAhora);
    }
    
    @Test
    public void shouldNotChangeSymbolInWheelLock() throws SlotMachineException
    {
        slotMachine.addWheel(1, "normal");
        slotMachine.addSymbol("normal",1,"red");
        slotMachine.addSymbol("normal",2,"blue");
        slotMachine.addSymbol("normal",3,"yellow");
        slotMachine.addSymbol("normal",4,"purple");
        slotMachine.addSymbol("normal",5,"black");
        slotMachine.placeSymbol(1, "red");
        slotMachine.lock(1);
        String[] colorAntes = slotMachine.configuration();
        slotMachine.spin(1);
        String[] colorAhora = slotMachine.configuration();
        assertArrayEquals(colorAntes, colorAhora);
    }
    
    @Test
    public void shouldMoveToCorrectSymbolAfterSteps() throws SlotMachineException
    {
        slotMachine.addWheel(1, "normal");
        slotMachine.addSymbol("normal",1,"red");
        slotMachine.addSymbol("normal",2,"blue");
        slotMachine.addSymbol("normal",3,"yellow");
        slotMachine.addSymbol("normal",4,"purple");
        slotMachine.placeSymbol(1, "red");
        slotMachine.spin(1, 2);
        String resultado = slotMachine.configuration()[0];
        assertEquals("yellow", resultado);
    }
    
    @Test
    public void shouldNotMoveWhenWheelIsLocked() throws SlotMachineException
    {
        slotMachine.addWheel(1, "normal");
        slotMachine.addSymbol("normal",1,"red");
        slotMachine.addSymbol("normal",2,"blue");
        slotMachine.addSymbol("normal",3,"yellow");
        slotMachine.addSymbol("normal",4,"purple");
        slotMachine.placeSymbol(1, "red");
        slotMachine.lock(1);
        String colorAntes = slotMachine.configuration()[0];
        slotMachine.spin(1, 3);
        String resultado = slotMachine.configuration()[0];
        assertEquals(colorAntes, resultado);
    }
    
    @Test
    public void shouldSetExactConfiguration() throws SlotMachineException
    {
        slotMachine.addWheel(1, "normal");
        slotMachine.addWheel(2, "normal");
        slotMachine.addSymbol("normal",1,"red");
        slotMachine.addSymbol("normal",2,"blue");
        slotMachine.addSymbol("normal",3,"yellow");
        slotMachine.addSymbol("normal",4,"purple");
        slotMachine.spin(new String[]{"red", "blue"});
        String[] resultado = slotMachine.configuration();
        assertArrayEquals(new String[]{"red", "blue"}, resultado);
    }
    
    @Test
    public void shouldNotChangeWhenTooManySymbolsGiven() throws SlotMachineException
    {
        slotMachine.addWheel(1, "normal");
        slotMachine.addWheel(2, "normal");
        slotMachine.addSymbol("normal",1,"red");
        slotMachine.addSymbol("normal",2,"blue");
        slotMachine.addSymbol("normal",3,"yellow");
        slotMachine.addSymbol("normal",4,"purple");
        slotMachine.placeSymbol(1, "red");
        slotMachine.placeSymbol(2, "blue");
        String[] Antes = slotMachine.configuration();
        slotMachine.spin(new String[]{"red", "blue","yellow"});
        String[] resultado = slotMachine.configuration();
        assertArrayEquals(Antes, resultado);
    }

    @Test
    public void accordingDrRmShouldBeJackpotWhenAllWheelsMatch() throws SlotMachineException
    {
        slotMachine.addWheel(1,"normal");
        slotMachine.addWheel(2,"normal");
        slotMachine.addWheel(3,"normal");
        slotMachine.addSymbol("normal", 1, "red");
        slotMachine.addSymbol("normal", 2, "blue");
        slotMachine.spin(new String[]{"red", "red", "red"});
        assertTrue(slotMachine.isJackpot());
    }
    
    @Test
    public void accordingDrRmShouldKeepCorrectWheelCountAfterAddAndDelete() throws SlotMachineException
    {
        slotMachine.addWheel(1,"normal");
        slotMachine.addWheel(2, "normal");
        slotMachine.addWheel(3,"normal");
        slotMachine.delWheel(2);
        slotMachine.addSymbol("normal", 1, "red");
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