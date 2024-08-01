import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class triangleTest1 {

    @Test
    void testCalculatePerimeter() {
        Triangle1 triangle = new Triangle1(5.0, 5.0, 8.0);
        double result = triangle.calculatePerimeter();
        Assertions.assertEquals(18.0, result, "Периметр треугольника должен быть 18.0");
    }

    @Test
    void testCalculateArea() {
        Triangle1 triangle = new Triangle1(5.0, 5.0, 8.0);
        double result = triangle.calculateArea();
        Assertions.assertEquals(12.0, result, "Площадь треугольника должна быть 12.0");
    }

}