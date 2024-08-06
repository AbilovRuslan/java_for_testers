import java.util.Objects;

public record Triangle1(double a, double b, double c)  {

    public Triangle1 {
        // Условие с отрицательной стороной
        if (a < 0 || b < 0 || c < 0) {
            throw new IllegalArgumentException("The length of a triangle's side cannot be negative or zero.");
        }
        // Условие неравенства треугольника
        if (a + b <= c || a + c <= b || b + c <= a) {
            throw new IllegalArgumentException("The sum of any two sides must be greater than the third side.");
        }
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }

        Triangle1 other = (Triangle1) obj;
        return areSidesEqual(this.a, this.b, this.c, other.a, other.b, other.c); //  равенство сторон
    }

    private boolean areSidesEqual(double a1, double b1, double c1, double a2, double b2, double c2) {
        //  равенство сторон в любом порядке
        return (Double.compare(a1, a2) == 0 && Double.compare(b1, b2) == 0 && Double.compare(c1, c2) == 0) ||
                (Double.compare(a1, a2) == 0 && Double.compare(b1, c2) == 0 && Double.compare(c1, b2) == 0) ||
                (Double.compare(a1, b2) == 0 && Double.compare(b1, a2) == 0 && Double.compare(c1, c2) == 0) ||
                (Double.compare(a1, b2) == 0 && Double.compare(b1, c2) == 0 && Double.compare(c1, a2) == 0) ||
                (Double.compare(a1, c2) == 0 && Double.compare(b1, a2) == 0 && Double.compare(c1, b2) == 0) ||
                (Double.compare(a1, c2) == 0 && Double.compare(b1, b2) == 0 && Double.compare(c1, a2) == 0);
    }
    @Override
    public int hashCode() {
        return Objects.hash(a, b, c);
    }

    public static void main(String[] args) {
        Triangle1 triangle1 = new Triangle1(5.0, 5.0, 8.0);
        triangle1.calculatePerimeter();
        triangle1.calculateArea();

    }



    // вычисления периметра
    public double calculatePerimeter() {
        double perimeter = a + b + c; // Периметр
        System.out.println("Периметр треугольника: " + perimeter);
        return perimeter;
    }

    //площадь по формуле Герона
    public double calculateArea() {
        double s = (a + b + c) / 2.0; // Полупериметр
        double area = Math.sqrt(s * (s - a) * (s - b) * (s - c)); // Площадь
        System.out.println("Площадь треугольника: " + area);
        return area;
    }
}