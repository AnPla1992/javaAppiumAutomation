import org.junit.Assert;
import org.junit.Test;

public class MainClassTest {

    @Test
    public void testGetLocalNumber(){
        Assert.assertTrue("The method getLocalNumber() doesn't return 14!", new MainClass().getLocalNumber() == 14);
    }
}
