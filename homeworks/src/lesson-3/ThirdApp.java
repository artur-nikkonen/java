import java.util.Random;
import java.util.Scanner;

/**
 * Написать программу, которая загадывает случайное число от 0 до 9 и пользователю дается 3 попытки угадать это число.
 * При каждой попытке компьютер должен сообщить, больше ли указанное пользователем число, чем загаданное, или меньше.
 * После победы или проигрыша выводится запрос – «Повторить игру еще раз? 1 – да / 0 – нет»(1 – повторить, 0 – нет).
 */
public class ThirdApp {

    private static final Scanner sc = new Scanner(System.in);
    private static final int maxAttemptsNumber = 3; //максимальное число попыток

    public static void main(String[] args) {
        do {
            RunGame();
        } while (Rapeat());

        ExitGame();
    }

    private static void ExitGame() {
        System.out.println("Конец игры");
        sc.close();
    }

    /**
     * Игра по угадыванию числа
     */
    private static void RunGame() {
        Random random = new Random();
        int secretNumber = random.nextInt(10); // число, которое надо угадать
        int attempt = 1; //номер попытки
        int curAnswer; // текущий ответ пользователя

        System.out.println("Отгодайте число от 0 до 9");

        while (attempt <= maxAttemptsNumber) {
            System.out.println("Попытка №" + attempt + ". Введите число: ");
            curAnswer = tryParseScToIntWithCheck(0, 9);

            if (curAnswer == Integer.MIN_VALUE) {
                System.out.println("Попробуйте еще раз. Должно быть число от 0 до 9");
            } else if (curAnswer == secretNumber) {
                System.out.println("Поздравляем, вы угадали!!! Загаданное число = " + secretNumber);
                return;
            } else {
                System.out.println("Загаданное число" + (secretNumber < curAnswer ? " < " : " > ") + curAnswer);
                attempt++;
            }
        }

        System.out.println("Вы не угадали. Загаданное число = " + secretNumber);
    }

    /**
     * Спрашиваем у пользователя, нужно ли повторить игру.
     */

    private static boolean Rapeat() {
        int answer;
        do {
            System.out.println("Повторить игру еще раз? 1 – да / 0 – нет");
            answer = tryParseScToIntWithCheck(0, 1);
            if (answer == Integer.MIN_VALUE) System.out.print("Не понял. ");
        } while (answer == Integer.MIN_VALUE);

        return answer == 1;
    }

    /**
     * Преобразует строку из сканера в целое число. При ошибке возвращает MIN_VALUE.
     * Если число не в диапазоне между min и max, то тоже возвращает MIN_VALUE
     */
    private static int tryParseScToIntWithCheck(int min, int max) {
        String str = sc.nextLine();
        int i = tryParseInt(str);
        if (i == Integer.MIN_VALUE) return Integer.MIN_VALUE;
        if (i < min || i > max) return Integer.MIN_VALUE;
        return i;
    }

    /**
     * Преобразует строку в целое число. При ошибке возвращает MIN_VALUE
     */
    private static int tryParseInt(String value) {
        try {
            return Integer.parseInt(value);
        } catch (NumberFormatException e) {
            return Integer.MIN_VALUE;
        }
    }
}
