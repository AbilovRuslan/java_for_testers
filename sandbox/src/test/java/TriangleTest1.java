import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class TriangleTest1 {

    @Test
    void canCalculateArea(){
        double result = Triangle1.printTriangleArea(5,5,8);
        Assertions. assertEquals(12, result);
    }

    void  canCalculatePerimetr(){
        double result = Triangle1.printTrianglePerimetr(4,7,8);
        Assertions.assertEquals(19,result);
    }


}
