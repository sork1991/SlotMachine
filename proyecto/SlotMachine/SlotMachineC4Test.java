import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

/**
 * The test class SlotMachineC4Test.
 *
 * @author  (your name)
 * @version (a version number or a date)
 */
public class SlotMachineC4Test
{
    private SlotMachine slotMachine;
    
    /**
     * Default constructor for test class SlotMachineC4Test
     */
    public SlotMachineC4Test()
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
    public void shouldNotAddSymbolWhenTypeIsUnknown(){
        slotMachine.addWheel(1);
        slotMachine.addSymbol("shyy",1,"red");
        assertFalse(slotMachine.ok());
        int sy = slotMachine.symbols().length;
        assertEquals(0 ,sy);
    }
    
    @Test
    public void shouldReduceEphemeralAfterOneNotifySpin(){
        Symbol ephem = new Ephemeral("red", "triangle");
        ephem.changeSize(24,16);
        ephem.notifySpin();
        int[] tamaño = ephem.shape.getSize();
        int[] esperado = new int[2];
        esperado[0] = 14;
        esperado[1] = 21;
        assertArrayEquals(esperado, tamaño);
    }
    
    @Test
    public void shouldNotAfterSpin8Reduce(){
        Symbol ephem = new Ephemeral("red", "triangle");
        ephem.changeSize(24,16);
        for(int i=1; i<=9;i++){
            ephem.notifySpin();
        }
        int[] tamaño = ephem.shape.getSize();
        int[] esperado = new int[2];
        esperado[0] = 2;
        esperado[1] = 2;
        assertArrayEquals(esperado, tamaño);
    }
    
    @Test
    public void shouldReturnCorrectTypes() {
        assertEquals("normal", new Symbol("red", "circle").getType());
        assertEquals("shy", new Shy("blue", "rectangle").getType());
        assertEquals("ephemeral", new Ephemeral("green", "triangle").getType());
    }
    
    @Test
    public void shouldReduceEphemeralCorrectlyAfterSeveralSpins() {
        Symbol ephem = new Ephemeral("red", "triangle");
        ephem.changeSize(24, 16);
        ephem.notifySpin();
        int[] size1 = ephem.shape.getSize();
        int[] esp1 =new int[2];
        esp1[0] =14;
        esp1[1] =21;
        assertArrayEquals(esp1, size1);
        ephem.notifySpin();
        int[] size2 = ephem.shape.getSize();
        int[] esp2 =new int[2];
        esp2[0] =12;
        esp2[1] =18;
        assertArrayEquals(esp2, size2);
        ephem.notifySpin();
        int[] size3 = ephem.shape.getSize();
        int[] esp3 =new int[2];
        esp3[0] =10;
        esp3[1] =15;
        assertArrayEquals(esp3, size3);
        ephem.notifySpin();
        int[] size4 = ephem.shape.getSize();
        int[] esp4 =new int[2];
        esp4[0] =8;
        esp4[1] =12;
        assertArrayEquals(esp4, size4);
    }
    
    @Test
    public void shouldUpdateLocalHeightAndWidthInEphemeral() {
        Symbol ephem = new Ephemeral("blue", "rectangle");
        ephem.changeSize(40, 30);
        int[] size1 = ephem.shape.getSize();
        int[] esp1 =new int[2];
        esp1[0] =30;
        esp1[1] =40;
        assertArrayEquals(esp1, size1);
        ephem.notifySpin();
        int[] size2 = ephem.shape.getSize();
        int[] esp2 =new int[2];
        esp2[0] =28;
        esp2[1] =37;
        assertArrayEquals(esp2, size2);
    }
    
    @Test
    public void shouldBecomeInvisibleWhenSelected() {
        Shy shy = new Shy("green", "circle");
        shy.makeVisible();
        shy.notifySelection();
        assertFalse(shy.getVisibility());
    }
    
    @Test
    public void shouldRespectInternalVisibilityFlag() {
        Shy shy = new Shy("yellow", "triangle");
        assertTrue(shy.getVisibility());
        shy.notifySelection();
        assertFalse(shy.getVisibility());
        shy.makeVisible();
        assertFalse(shy.getVisibility());
        shy.notifySelection();
        assertTrue(shy.getVisibility());
        shy.makeVisible();
        assertTrue(shy.getVisibility());
    }
    
    /**
     * Tears down the test fixture.
     *
     * Called after every test case method.
     */
    @AfterEach
    public void tearDown()
    {
    }
}