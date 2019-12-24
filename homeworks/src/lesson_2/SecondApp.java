package lesson_2;

public class SecondApp {

    /**
     * Вспромогательный метод для вывода одномерного массива
     */
    private static void printArray(int[] array, boolean withEmptyLineAfter) {
        for (int value : array) {
            System.out.print(value + "\t");
        }
        System.out.println();
        if (withEmptyLineAfter) System.out.println();
    }

    /**
     * Вспромогательный метод для вывода двумерного массива
     */
    private static void print2dArray(int[][] array) {
        for (int[] line : array) {
            for (int value : line) {
                System.out.print(value + "\t");
            }
            System.out.println();
        }

        System.out.println();
    }

    /**
     * Задать целочисленный массив, состоящий из элементов 0 и 1. Например: [ 1, 1, 0, 0, 1, 0, 1, 1, 0, 0 ].
     * С помощью цикла и условия заменить 0 на 1, 1 на 0
     */
    private static void convertArray() {
        int[] array = {1, 1, 0, 0, 1, 0, 1, 1, 0, 0};

        printArray(array, false);

        for (int i = 0; i < array.length; i++) {
            array[i] = array[i] == 1 ? 0 : 1;
        }

        printArray(array, true);
    }

    /**
     * Задать пустой целочисленный массив размером 8. С помощью цикла заполнить его значениями 0 3 6 9 12 15 18 21;
     */
    private static void fillArray() {
        int[] array = new int[8];

        for (int i = 0; i < array.length; i++) {
            array[i] = i * 3;
        }

        printArray(array, true);
    }

    /**
     * Задать массив [ 1, 5, 3, 2, 11, 4, 5, 2, 4, 8, 9, 1 ] пройти по нему циклом, и числа меньшие 6 умножить на 2;
     */
    private static void multiplySomeArrayElements() {
        int[] array = {1, 5, 3, 2, 11, 4, 5, 2, 4, 8, 9, 1};

        printArray(array, false);

        for (int i = 0; i < array.length; i++) {
            if (array[i] < 6) {
                array[i] *= 2;
            }
        }

        printArray(array, true);
    }

    /**
     * Создать квадратный двумерный целочисленный массив (количество строк и столбцов одинаковое),
     * и с помощью цикла(-ов) заполнить его диагональные элементы единицами;
     */
    private static void fill2dArray() {
        int size = 5;
        int[][] array = new int[size][size];

        for (int i = 0; i < size; i++) {
            array[i][i] = 1;
            array[size - i - 1][i] = 1;
        }

        print2dArray(array);
    }

    /**
     * Задать одномерный массив и найти в нем минимальный и максимальный элементы (без помощи интернета);
     */
    private static void findMaxMinElements() {
        int[] array = {1, 5, -3, 2, 11, 4, 5, -2, 4, 18, 9, 1};

        int min = array[0];
        int max = array[0];

        for (int i = 1; i < array.length; i++) {
            if (array[i] < min) {
                min = array[i];
            } else if (array[i] > max) {
                max = array[i];
            }
        }

        printArray(array, false);
        System.out.println("max = " + max + "; min = " + min);
        System.out.println();
    }

    /**
     * Написать метод, в который передается не пустой одномерный целочисленный массив, метод должен вернуть true,
     * если в массиве есть место, в котором сумма левой и правой части массива равны.
     */
    private static boolean checkBalance(int[] array) {

        int fullSum = 0;

        for (int value : array) {
            fullSum += value;
        }

        int leftSum = 0;

        for (int i = 0; i < array.length; i++) {
            leftSum += array[i];
            if (leftSum * 2 == fullSum) {
                System.out.println("сумма = " + leftSum + " граница между элементами " + (i + 1) + " и " + (i + 2));
                return true;
            }
        }
        return false;
    }

    /**
     * метод для вывода результатов checkBalance
     */
    private static void testCheckBalance(int[] array) {
        printArray(array, false);
        if (checkBalance(array)) {
            System.out.println("Место есть");
        } else {
            System.out.println("Места нет");
        }
        System.out.println();
    }

    /**
     * Написать метод, которому на вход подается одномерный массив и число n (может быть положительным,
     * или отрицательным), при этом метод должен сместить все элементымассива на n позиций.
     * Для усложнения задачи нельзя пользоваться вспомогательными массивами.
     */
    private static void offsetElements(int[] array, int offset) {

        if (offset > 0) {
            for (int i = array.length - 1; i >= 0; i--) {
                array[i] = (i - offset >= 0) ? array[i - offset] : 0;
            }
        } else if (offset < 0) {
            offset = -offset; //меняем знак чтобы выражение в цикле было нагляднее
            for (int i = 0; i < array.length; i++) {
                array[i] = (i + offset < array.length) ? array[i + offset] : 0;
            }
        }
    }

    /**
     * метод для вывода результатов offsetElements
     */
    private static void testOffsetElements(int[] array, int offset) {
        printArray(array, false);
        System.out.println("сдвиг = " + offset);
        offsetElements(array, offset);
        printArray(array, true);
    }

    public static void main(String[] args) {

        convertArray();
        fillArray();
        multiplySomeArrayElements();
        fill2dArray();
        findMaxMinElements();

        testCheckBalance(new int[]{2, 2, 2, 1, 2, 2, 10, 1});
        testCheckBalance(new int[]{1, 1, 1, 2, 1});
        testCheckBalance(new int[]{1, 2, 3, 4, 5});
        testCheckBalance(new int[]{10, -2, 2, 3, 3});

        testOffsetElements(new int[]{1, 2, 3, 4, 5, 6, 7, 8, 9}, 3);
        testOffsetElements(new int[]{1, 2, 3, 4, 5, 6, 7, 8, 9}, 8);
        testOffsetElements(new int[]{1, 2, 3, 4, 5, 6, 7, 8, 9}, -4);
        testOffsetElements(new int[]{1, 2, 3, 4, 5, 6, 7, 8, 9}, -10);
    }
}
