public class Triangle1 {

    public static void main(String[] args) {

        printTrianglePerimetr(3.0, 5.0, 6.0);
        TriangleArea(5,5,8);
        printTrianglePerimetr(4,7,8);
        TriangleArea(4,6,9);
    }

    private static void TriangleArea(double a, double b, double c) {
        System.out.println("Площадь треугольника равна =" + printTriangleArea(a,b,c));
    }
    public static double printTriangleArea(double a, double b, double c) {
        double s = (a + b + c) / 2.0;
        return Math.sqrt(s * (s - a) * (s - b) * (s - c));
    }

    public static double printTrianglePerimetr(double a, double b, double c) {
        System.out.println("Периметр треугольника равен =" + TrianglePerimetr(a, b, c));
        return a;
    }

    private static double TrianglePerimetr(double a, double b, double c) {
        return a + b + c;
    }
}
