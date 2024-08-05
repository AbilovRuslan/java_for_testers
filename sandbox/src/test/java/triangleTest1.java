import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class triangleTest1 {

    @Test
    void testCalculatePerimeter() {
        Triangle1 triangle = new Triangle1(5.0, 5.0, 8.0);
        double result = triangle.calculatePerimeter();
        assertEquals(18.0, result, "Периметр треугольника должен быть 18.0");
    }

    @Test
    void testCalculateArea() {
        Triangle1 triangle = new Triangle1(5.0, 5.0, 8.0);
        double result = triangle.calculateArea();
        assertEquals(12.0, result, "Площадь треугольника должна быть 12.0");
    }
    @Test
    void testEquals_UnequalTriangles() {
        var triangle1 = new Triangle1(3, 4, 5);
        var triangle2 = new Triangle1(5, 4, 3);
        Assertions.assertTrue(triangle1.equals(triangle2), "Two triangles with the same sides in different orders should be equal.");
    }



    @Test
    public void testTriangleWithNegativeSide() {
        try {
            new Triangle1(-5.0, 5.0, 8.0);
            // Если исключение не было выброшено, данный код не должен выполняться
            Assertions.fail();
        } catch (IllegalArgumentException exception) {

        }
    }
}

