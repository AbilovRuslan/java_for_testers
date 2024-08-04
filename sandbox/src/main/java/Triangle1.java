public record Triangle1(double a, double b, double c)  {

    public Triangle1 {
        // Условие с отрицательной стороной
        if (a < 0 || b < 0 || c < 0) {
            throw new IllegalArgumentException("The length of a triangle's side cannot be negative or zero.");
        }
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