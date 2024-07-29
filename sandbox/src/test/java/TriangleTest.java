import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class TriangleTest {

    @Test
    void canCalculateArea(){
        double result = Triangle.triangleArea(5,5,8);
        Assertions. assertEquals(12, result);
    }

   
}
