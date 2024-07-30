public class Triangle {


    public static void main(String[] args) {
        {
            //периметр
            double w = 2.0;
            double r = 3.0;
            double t = 5.0;
            double perimeter = (w + r + t);

            System.out.println("периметр треугольника равен = " + perimeter);

        }

        /// площадь по Герону
        double a;
        a = triangleArea(5,5,8);
        System.out.println("Треугольник со сторонами 5,5,8 имеет площадь:" + a);

    }

    public static double triangleArea(int a, int b, int c) {
        double s = (a + b + c) / 2.0;  // полупериметр
        return Math.sqrt(s * (s - a) * (s - b) * (s - c)); // Герон
    }

}