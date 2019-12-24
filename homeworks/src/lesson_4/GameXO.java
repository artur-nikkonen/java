package lesson_4;

import java.util.Random;
import java.util.Scanner;

/**
 * 1. Полностью разобраться с кодом, попробовать переписать с нуля, стараясь не подглядывать в методичку;
 * 2. Переделать проверку победы, чтобы она не была реализована просто набором условий, например, с использованием циклов.
 * 3. * Попробовать переписать логику проверки победы, чтобы она работала для поля 5х5 и количества фишек 4.
 * Очень желательно не делать это просто набором условий для каждой из возможных ситуаций;
 * 4. *** Доработать искусственный интеллект, чтобы он мог блокировать ходы игрока.
 */

public class GameXO {

    private static final char DOT_X = 'X';
    private static final char DOT_O = 'O';
    private static final char DOT_EMPTY = '_';
    private static final int FIELD_SIZE = 5;
    private static final int WIN_DOTS_COUNT = 4;

    private static char[][] field = new char[FIELD_SIZE][FIELD_SIZE];

    private static Scanner scanner = new Scanner(System.in);
    private static Random random = new Random();

    private static boolean showAiMessages = true;

    public static void main(String[] args) {

        do {
            runGame();
        } while (playAgain());

        System.out.println("Игра закончена");
    }

    private static void runGame() {
        initField();
        printField();

        while (true) {

            humanStep();
            printField();

            if (checkWin(DOT_X)) {
                System.out.println("Поздравляем, вы выиграли!");
                break;
            }
            if (checkFieldIsFull()) {
                System.out.println("Ничья");
                break;
            }
            //aiStep();
            aiSmartStep();
            printField();

            if (checkWin(DOT_O)) {
                System.out.println("Выиграл ИИ!");
                break;
            }
            if (checkFieldIsFull()) {
                System.out.println("Ничья");
                break;
            }
        }
    }

    private static boolean playAgain() {
        int answer;
        System.out.println("Повторить игру еще раз? 1 - да, 0 - нет");
        answer = scanner.nextInt();
        return answer == 1;
    }

    private static boolean checkWin(char dot) {
        //В дальнейшем можно обиединить все эти проверки в один метод с дополнительными параметрами шага по вертикили и горизонтали
        //Не сделал объединение, так как это затрудняет понимание методов и тестирование
        if (checkWinHorizontal(dot)) return true;
        if (checkWinVertical(dot)) return true;
        if (checkWinMainDiagonal(dot)) return true;
        if (checkWinSecondDiagonal(dot)) return true;
        return false;
    }

    /**
     * Проверяем победу в напралении второстепенной диагонали. Проверяем только те диагонали, на которых количество ячеек
     * не меньше WIN_DOTS_COUNT
     */
    private static boolean checkWinSecondDiagonal(char dot) {
        int firstCol = -(FIELD_SIZE - WIN_DOTS_COUNT);
        int lastCol = (FIELD_SIZE - WIN_DOTS_COUNT);

        for (int startCol = firstCol; startCol <= lastCol; startCol++) {
            //по всем строкам диагонали и суммируем только те колонки, которые попадают в поле
            int count = 0;
            for (int row = FIELD_SIZE - 1, col = startCol; row >= 0; row--, col++) {
                if (col >= 0 && col < FIELD_SIZE) {
                    if (field[col][row] == dot) count++;
                    else count = 0;
                    if (count == WIN_DOTS_COUNT) return true;
                }
            }
        }
        return false;
    }

    /**
     * Проверяем победу в напралении главной диагонали. Проверяем только те диагонали, на которых количество ячеек
     * не меньше WIN_DOTS_COUNT
     */
    private static boolean checkWinMainDiagonal(char dot) {
        int firstCol = -(FIELD_SIZE - WIN_DOTS_COUNT);
        int lastCol = (FIELD_SIZE - WIN_DOTS_COUNT);

        for (int startCol = firstCol; startCol <= lastCol; startCol++) {
            //по всем строкам диагонали и суммируем только те колонки, которые попадают в поле
            int count = 0;
            for (int row = 0, col = startCol; row < FIELD_SIZE; row++, col++) {
                if (col >= 0 && col < FIELD_SIZE) {
                    if (field[col][row] == dot) count++;
                    else count = 0;
                    if (count == WIN_DOTS_COUNT) return true;
                }
            }
        }
        return false;
    }

    /**
     * Проверяем победу по вертикали
     */
    private static boolean checkWinVertical(char dot) {
        for (int col = 0; col < FIELD_SIZE; col++) {
            int count = 0;
            for (int row = 0; row < FIELD_SIZE; row++) {
                if (field[row][col] == dot) count++;
                else count = 0;
                if (count == WIN_DOTS_COUNT) return true;
            }
        }
        return false;
    }

    /**
     * Проверяем победу по горизонтали
     */
    private static boolean checkWinHorizontal(char dot) {
        for (int row = 0; row < FIELD_SIZE; row++) {
            int count = 0;
            for (int col = 0; col < FIELD_SIZE; col++) {
                if (field[row][col] == dot) count++;
                else count = 0;
                if (count == WIN_DOTS_COUNT) return true;
            }
        }
        return false;
    }

    private static void aiStep() {

        int row, col;

        do {
            row = random.nextInt(FIELD_SIZE);
            col = random.nextInt(FIELD_SIZE);
        } while (!coordinatesIsCorrect(row, col));

        field[row][col] = DOT_O;
    }

    private static void humanStep() {

        int row, col;
        boolean correct;

        do {
            System.out.println("Ввведите координаты (номер строки, номер столбца)");
            row = scanner.nextInt() - 1;
            col = scanner.nextInt() - 1;
            correct = coordinatesIsCorrect(row, col);
            if (!correct) System.out.print("Неправильные координаты. ");
        } while (!correct);

        field[row][col] = DOT_X;
    }

    private static boolean coordinatesIsCorrect(int row, int col) {
        if (row < 0 || row > FIELD_SIZE - 1) return false;
        if (col < 0 || col > FIELD_SIZE - 1) return false;
        if (field[row][col] != DOT_EMPTY) return false;
        return true;
    }

    private static boolean checkFieldIsFull() {
        for (int i = 0; i < FIELD_SIZE; i++) {
            for (int j = 0; j < FIELD_SIZE; j++) {
                if (field[i][j] == DOT_EMPTY) return false;
            }
        }
        return true;
    }

    private static void printField() {
        //шапка
        System.out.print(" ");
        for (int i = 0; i < FIELD_SIZE; i++) {
            System.out.printf(" %s", i + 1);
        }
        System.out.println();

        //клетки
        for (int i = 0; i < FIELD_SIZE; i++) {
            System.out.printf("%s|", i + 1);
            for (int j = 0; j < FIELD_SIZE; j++) {
                System.out.printf("%s|", field[i][j]);
            }
            System.out.println();
        }
    }

    private static void initField() {
        for (int i = 0; i < FIELD_SIZE; i++) {
            for (int j = 0; j < FIELD_SIZE; j++) {
                field[i][j] = DOT_EMPTY;
            }
        }
    }

    /**
     * Метод для Бота.
     * Проверяет следующий ход. Если он выигрышный для бота, то ходит туда.
     * Проверяет возможность выиграть человеку. Если возможность есть, то закрывает её.
     * Проверяет возможность сделать вилку для бота. Если возможность есть, то ходит туда
     * Проверяет возможность сделать вилку для человека. Если возносзность есть, то закрывает вилку.
     * Если ничего не найдено, то ходит случайный ход.
     */
    private static void aiSmartStep() {

        if (tryMakeBotWinStep()) return;
        if (tryCloseHumanWinStep()) return;
        if (tryMakeBotForkStep()) return;
        if (tryCloseHumanForkStep()) return;

        //угроз нет. Ходим случайно.
        if (showAiMessages)
            System.out.println("Сообщение бота - Угроз и возможностей выйграть не найдено. Случайный ход.");
        aiStep();
    }

    /**
     * Попытка закрыть вилку человека
     */
    private static boolean tryCloseHumanForkStep() {
        for (int row = 0; row < FIELD_SIZE; row++) {
            for (int col = 0; col < FIELD_SIZE; col++) {
                if (coordinatesIsCorrect(row, col)) {
                    field[row][col] = DOT_X;
                    if (checkFork(DOT_X)) {
                        field[row][col] = DOT_O; // человек может сделать вилку, ходим сюда
                        return true;
                    } else {
                        field[row][col] = DOT_EMPTY;
                    }
                }
            }
        }
        return false;
    }

    /**
     * Попытка сделать вилку для бота
     */
    private static boolean tryMakeBotForkStep() {
        for (int row = 0; row < FIELD_SIZE; row++) {
            for (int col = 0; col < FIELD_SIZE; col++) {
                if (coordinatesIsCorrect(row, col)) {
                    field[row][col] = DOT_O;
                    if (checkFork(DOT_O)) {
                        return true; // ии сделал силку
                    } else {
                        field[row][col] = DOT_EMPTY;
                    }
                }
            }
        }
        return false;
    }

    /**
     * Попытка закрыть победный ход человека
     */
    private static boolean tryCloseHumanWinStep() {
        for (int row = 0; row < FIELD_SIZE; row++) {
            for (int col = 0; col < FIELD_SIZE; col++) {
                if (coordinatesIsCorrect(row, col)) {
                    field[row][col] = DOT_X;
                    if (checkWin(DOT_X)) {
                        field[row][col] = DOT_O; // человек может победить, ходим сюда
                        if (showAiMessages) System.out.println("Сообщение бота - Найден победный ход для " + DOT_X);
                        return true;
                    } else {
                        field[row][col] = DOT_EMPTY;
                    }
                }
            }
        }
        return false;
    }

    /**
     * Попытка сделать победный ход бота
     */
    private static boolean tryMakeBotWinStep() {
        for (int row = 0; row < FIELD_SIZE; row++) {
            for (int col = 0; col < FIELD_SIZE; col++) {
                if (coordinatesIsCorrect(row, col)) {
                    field[row][col] = DOT_O;
                    if (checkWin(DOT_O)) {
                        if (showAiMessages) System.out.println("Сообщение бота - Найден победный ход для " + DOT_O);
                        return true; // ии победил
                    } else {
                        field[row][col] = DOT_EMPTY;
                    }
                }
            }
        }
        return false;
    }

    /**
     * Проверяем наличие вилки - ситуации когда для победы есть 2 варианта хода с двух сторон. Например: _XXX_
     */
    private static boolean checkFork(char dot) {
        //Методы дублируют методы в checkWin с другими условиями успеха
        //В дальнейшем можно обиединить все эти проверки в один метод с дополнительными параметрами шага по вертикили и горизонтали
        //Не сделал объединение, так как это затрудняет понимание методов и тестирование
        if (checkForkHorizontal(dot)) return true;
        if (checkForkVertical(dot)) return true;
        if (checkForkMainDiagonal(dot)) return true;
        if (checkForkSecondDiagonal(dot)) return true;
        return false;
    }

    /**
     * Проверяем вилку по вертикали
     */
    private static boolean checkForkVertical(char dot) {
        for (int col = 0; col < FIELD_SIZE; col++) {
            boolean emptyDotBefore = false;
            int count = 0;
            for (int row = 0; row < FIELD_SIZE; row++) {
                if (field[row][col] == DOT_EMPTY) {
                    if (emptyDotBefore && count == WIN_DOTS_COUNT - 1) {
                        //мы нашли вилку: есть пробел до, есть N-1 точек, есть пробел после
                        if (showAiMessages)
                            System.out.println("Сообщение бота - Найдена вилка по вертикали для " + dot);
                        return true;
                    } else {
                        //мы не нашли вилкку. обнуляем полсчет.
                        emptyDotBefore = true;
                        count = 0;
                    }
                } else if (!emptyDotBefore || field[row][col] != dot) {
                    //мы не нашли вилкку. обнуляем полсчет и пробел до
                    emptyDotBefore = false;
                    count = 0;
                } else {
                    //emptyBefore == true && field[row][col] == dot
                    count++;
                }
            }
        }
        return false;
    }

    /**
     * Проверяем вилку по горизонтали
     */
    private static boolean checkForkHorizontal(char dot) {
        for (int row = 0; row < FIELD_SIZE; row++) {
            boolean emptyDotBefore = false;
            int count = 0;
            for (int col = 0; col < FIELD_SIZE; col++) {
                if (field[row][col] == DOT_EMPTY) {
                    if (emptyDotBefore && count == WIN_DOTS_COUNT - 1) {
                        //мы нашли вилку: есть пробел до, есть N-1 точек, есть пробел после
                        if (showAiMessages)
                            System.out.println("Сообщение бота - Найдена вилка по горизонтали для " + dot);
                        return true;
                    } else {
                        //мы не нашли вилкку. обнуляем полсчет.
                        emptyDotBefore = true;
                        count = 0;
                    }
                } else if (!emptyDotBefore || field[row][col] != dot) {
                    //мы не нашли вилкку. обнуляем полсчет и пробел до
                    emptyDotBefore = false;
                    count = 0;
                } else {
                    //emptyBefore == true && field[row][col] == dot
                    count++;
                }
            }
        }
        return false;
    }

    /**
     * Проверяем килку в напралении второстепенной диагонали.
     */
    private static boolean checkForkSecondDiagonal(char dot) {
        int firstCol = -(FIELD_SIZE - WIN_DOTS_COUNT);
        int lastCol = (FIELD_SIZE - WIN_DOTS_COUNT);

        for (int startCol = firstCol; startCol <= lastCol; startCol++) {
            boolean emptyDotBefore = false;
            int count = 0;
            for (int row = FIELD_SIZE - 1, col = startCol; row >= 0; row--, col++) {
                if (col < 0 || col >= FIELD_SIZE) continue;
                if (field[row][col] == DOT_EMPTY) {
                    if (emptyDotBefore && count == WIN_DOTS_COUNT - 1) {
                        //мы нашли вилку: есть пробел до, есть N-1 точек, есть пробел после
                        if (showAiMessages)
                            System.out.println("Сообщение бота - Найдена вилка по второй диагонали для " + dot);
                        return true;
                    } else {
                        //мы не нашли вилкку. обнуляем полсчет.
                        emptyDotBefore = true;
                        count = 0;
                    }
                } else if (!emptyDotBefore || field[row][col] != dot) {
                    //мы не нашли вилкку. обнуляем полсчет и пробел до
                    emptyDotBefore = false;
                    count = 0;
                } else {
                    //emptyBefore == true && field[row][col] == dot
                    count++;
                }
            }
        }
        return false;
    }

    /**
     * Проверяем вилку в напралении главной диагонали.
     */
    private static boolean checkForkMainDiagonal(char dot) {
        int firstCol = -(FIELD_SIZE - WIN_DOTS_COUNT);
        int lastCol = (FIELD_SIZE - WIN_DOTS_COUNT);

        for (int startCol = firstCol; startCol <= lastCol; startCol++) {
            boolean emptyDotBefore = false;
            int count = 0;
            for (int row = 0, col = startCol; row < FIELD_SIZE; row++, col++) {
                if (col < 0 || col >= FIELD_SIZE) continue;
                if (field[row][col] == DOT_EMPTY) {
                    if (emptyDotBefore && count == WIN_DOTS_COUNT - 1) {
                        //мы нашли вилку: есть пробел до, есть N-1 точек, есть пробел после
                        if (showAiMessages)
                            System.out.println("Сообщение бота - Найдена вилка по главной диагонали для " + dot);
                        return true;
                    } else {
                        //мы не нашли вилкку. обнуляем полсчет.
                        emptyDotBefore = true;
                        count = 0;
                    }
                } else if (!emptyDotBefore || field[row][col] != dot) {
                    //мы не нашли вилкку. обнуляем полсчет и пробел до
                    emptyDotBefore = false;
                    count = 0;
                } else {
                    //emptyBefore == true && field[row][col] == dot
                    count++;
                }
            }
        }
        return false;
    }
}
