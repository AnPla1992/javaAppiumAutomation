import org.junit.Assert;
import org.junit.Test;

public class MainClassTest {

    MainClass mainClass = new MainClass();

    @Test
    public void testGetLocalNumber(){
        Assert.assertTrue("The method getLocalNumber() doesn't return 14!", mainClass.getLocalNumber() == 14);
    }

    @Test
    public void testGetClassNumber(){
        Assert.assertTrue("The method getClassNumber() returns a number that is less than or equal to 45.", mainClass.getClassNumber() > 45);
    }
}
