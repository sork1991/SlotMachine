import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class SlotMachineContestTest
{
    /**
     * Default constructor for test class SlotMachineContestTest
     */
    public SlotMachineContestTest()
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
    }
    
    @Test
    public void shouldGenerateSameSizeInWheelsandSymbols(){
        SlotMachine sM = new SlotMachine(4);
        int sizeW = sM.configuration().length;
        String[] s = sM.symbols();
        int sizeS = s.length;
        assertEquals(4, sizeW);
        assertEquals(4, sizeS);
    }
    
    @Test
    public void shouldNotGenereteWheelsAndSymbolsMajorthanColors(){
        SlotMachine sM = new SlotMachine(8);
        int sizeW = sM.configuration().length;
        String[] s = sM.symbols();
        int sizeS = s.length;
        assertEquals(0, sizeW);
        assertEquals(0, sizeS);
    }
    
    @Test
    public void accordingDrRmShouldNEqualInWheelAndSymbol(){
        int n = 3;
        SlotMachine sM = new SlotMachine(n);
        int sizeW = sM.configuration().length;
        String[] s = sM.symbols();
        int sizeS = s.length;
        assertEquals(n, sizeW);
        assertEquals(n, sizeS);
    }
    
    @Test
    public void accordingDrRmShouldHaveNDifferentColors()
    {
        int n = 3;
        SlotMachine sM = new SlotMachine(n);
        int res = sM.distinctSymbols();
        assertEquals(n, res);
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