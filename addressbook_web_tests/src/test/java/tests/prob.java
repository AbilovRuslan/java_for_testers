package tests;

import org.junit.jupiter.api.Test;

public class prob {
    public class Calculator {
        public int add(int a, int b) {
            return a + b;
        }
    }

        @Test
        public void main() {
            Calculator calculator = new Calculator();

            // Тестируем метод
            int result = calculator.add(2, 3);

            // Выводим результат на консоль
            System.out.println("Результат сложения 2 + 3: " + result);
        }
}


