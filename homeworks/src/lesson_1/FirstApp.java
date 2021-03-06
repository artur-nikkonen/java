package lesson_1;
public class FirstApp {
    public static void main(String[] args) {

        //2. Создать переменные всех пройденных типов данных и инициализировать их значения.
        exampleOfVarTypes();
        System.out.println("2. Переменные созданы");

        //3. Написать метод вычисляющий выражение a * (b + (c / d)) и возвращающий результат
        float a = 10F, b = 2F, c = 3F, d = 4F;
        float result = calculateABCD(a, b, c, d);
        System.out.println("3. " + a + " * (" + b + " + (" + c + " / " + d + ")) = " + result);

        //4. Написать метод, принимающий на вход два целых числа и проверяющий, что их сумма
        // лежит в пределах от 10 до 20 (включительно), если да – вернуть true, в противном случае – false
        int i1 = 15, i2 = 3;
        boolean checkSumResult = checkSum(i1, i2);
        System.out.println("4. Числа: " + i1 + ", " + i2 + ". Сумма в диапазоне [10;20]: " + checkSumResult);

        //5. Написать метод, которому в качестве параметра передается целое число, метод должен напечатать в консоль,
        // положительное ли число передали или отрицательное.
        printNumberSign(-127);

        //6. Написать метод, которому в качестве параметра передается целое число. Метод должен вернуть true,
        // если число отрицательное
        int i3 = 90;
        boolean checkSignResult = checkSign(i3);
        System.out.println("6. Число: " + i3 + ". Число отрицательное: " + checkSignResult);

        //7. Написать метод, которому в качестве параметра передается строка, обозначающая имя, метод должен вывести
        // в консоль сообщение «Привет, указанное_имя!»
        String name = "Артур";
        printHello(name);

        //8. Написать метод, который определяет является ли год високосным, и выводит сообщение в консоль.
        // Каждый 4-й год является високосным, кроме каждого 100-го, при этом каждый 400-й – високосный.
        printYearIsLeap(1985); //!4
        printYearIsLeap(2004); //4 && !100 && !400
        printYearIsLeap(1900); //4 && 100 && !400
        printYearIsLeap(2000); //4 && 100 && 400
    }

    //8. Написать метод, который определяет является ли год високосным, и выводит сообщение в консоль.
    // Каждый 4-й год является високосным, кроме каждого 100-го, при этом каждый 400-й – високосный.
    private static void printYearIsLeap(int year) {
        if (year % 4 == 0 && (year % 100 != 0 || year % 400 == 0)) {
            System.out.println(year + " - високосный год");
        } else {
            System.out.println(year + " - невисокосный год");
        }
    }

    //7. Написать метод, которому в качестве параметра передается строка, обозначающая имя, метод должен вывести
    // в консоль сообщение «Привет, указанное_имя!»
    private static void printHello(String name) {
        System.out.println("Привет, " + name + "!");
    }

    //6. Написать метод, которому в качестве параметра передается целое число. Метод должен вернуть true,
    // если число отрицательное
    private static boolean checkSign(int i) {
        return i < 0;
    }

    //5. Написать метод, которому в качестве параметра передается целое число, метод должен напечатать в консоль,
    // положительное ли число передали или отрицательное.
    private static void printNumberSign(int i) {
        if (i >= 0) {
            System.out.println(i + " - положительное число");
        } else {
            System.out.println(i + " - отрицательное число");
        }

    }

    //4. Написать метод, принимающий на вход два целых числа и проверяющий, что их сумма
    // лежит в пределах от 10 до 20 (включительно), если да – вернуть true, в противном случае – false
    private static boolean checkSum(int i1, int i2) {
        //проверки на переполнение нет
        int sum = i1 + i2;
        return sum >= 10 && sum <= 20;
    }

    //3. Написать метод вычисляющий выражение a * (b + (c / d)) и возвращающий результат
    private static float calculateABCD(float a, float b, float c, float d) {
        //проверки на d = 0 нет
        return a * (b + c / d);
    }

    //2. Создать переменные всех пройденных типов данных и инициализировать их значения.
    private static void exampleOfVarTypes() {
        byte b = 127;
        short sh = 32767;
        int i = 2147483647;
        long l = 9223372036854775807L;

        float f = 1.6F;
        double d = -2.6;

        char ch = 'A';
        boolean bool = true;

        String str = "Это строка!";
    }

}
