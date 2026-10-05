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
    public void shouldNotAddSymbolWhenTypeIsUnknown() throws SlotMachineException{
        slotMachine.addWheel(1, "normal");
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
    
    @Test
    public void shouldNotLockRebelWheel() {
        Rebel rebel = new Rebel();
        rebel.lock();
        assertFalse(rebel.isLocked());
    }
    
    @Test
    public void shouldNotSwapRebelWheel() throws SlotMachineException {
        slotMachine.addWheel(1, "rebel");
        slotMachine.addWheel(2, "normal");
        slotMachine.addSymbol("normal",1,"red");
        slotMachine.addSymbol("normal",2,"blue");
        slotMachine.placeSymbol(1, "red");
        slotMachine.placeSymbol(2, "blue");
        slotMachine.swap(1, 2);
        String[] esperado = new String[]{"red", "blue"};
        String[] resultado = slotMachine.configuration();
        assertArrayEquals(esperado, resultado);
    }
    
    @Test
    public void shouldOnlySpinHalfSteps() throws SlotMachineException{
        Lazy lazy = new Lazy();
        lazy.addSymbol(0,"circle","normal","red");
        lazy.addSymbol(1,"circle","normal","blue");
        lazy.addSymbol(2,"circle","normal","green");
        lazy.addSymbol(3,"circle","normal","yellow");
        lazy.addSymbol(4,"circle","normal","magenta");
        lazy.placeSymbol("red");
        lazy.spin(6);
        assertEquals("yellow", lazy.getCurrentColor());
    }
    
    @Test
    public void shouldCopyLeftColor() throws SlotMachineException
    {
        Wheel izquierda = new Wheel();
        izquierda.addSymbol(0, "circle", "normal", "red");
        izquierda.placeSymbol("red");
    
        Lefty lefty = new Lefty();
        lefty.addSymbol(0, "circle", "normal", "red");
        lefty.addSymbol(1, "circle", "normal", "blue");
        lefty.placeSymbol("blue");
    
        lefty.spin(izquierda);
    
        assertEquals("red", lefty.getCurrentColor());
    }
    
    @Test
    public void shouldLockIfLeftIsLocked() throws SlotMachineException
    {
        Wheel izquierda = new Wheel();
        izquierda.addSymbol(0, "circle", "normal", "green");
        izquierda.placeSymbol("green");
        izquierda.lock();
    
        Lefty lefty = new Lefty();
        lefty.addSymbol(0, "circle", "normal", "green");
    
        lefty.spin(izquierda);
    
        assertTrue(lefty.isLocked());
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