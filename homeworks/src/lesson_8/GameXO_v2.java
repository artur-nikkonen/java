package lesson_8;

import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

public class GameXO_v2 {

    private final char DOT_X = 'X';
    private final char DOT_O = 'O';
    private final char DOT_EMPTY = ' ';
    private final int FIELD_SIZE = 5;
    private final int WIN_DOTS_COUNT = 4;

    private char[][] field = new char[FIELD_SIZE][FIELD_SIZE];

    private Scanner scanner = new Scanner(System.in);
    private Random random = new Random();

    private boolean showAiMessages = true;

    public GameXO_v2() {
       resetGame();
    }

    public int getFieldSize() {
        return FIELD_SIZE;
    }

    public void resetGame() {
        initField();
    }

    public boolean humanIsWin() {
        return checkWin(DOT_X);
    }

    public char[][] getField(){
        char [][] copy = new char[field.length][];
        for(int i = 0; i < field.length; i++)
            copy[i] = field[i].clone();
        return copy;
    }

    public boolean aiIsWin() {
        return checkWin(DOT_O);
    }


    private boolean playAgain() {
        int answer;
        System.out.println("Повторить игру еще раз? 1 - да, 0 - нет");
        answer = scanner.nextInt();
        return answer == 1;
    }

    private boolean checkWin(char dot) {
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
    private boolean checkWinSecondDiagonal(char dot) {
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
    private boolean checkWinMainDiagonal(char dot) {
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
    private boolean checkWinVertical(char dot) {
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
    private boolean checkWinHorizontal(char dot) {
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

    private void aiStep() {

        int row, col;

        do {
            row = random.nextInt(FIELD_SIZE);
            col = random.nextInt(FIELD_SIZE);
        } while (!coordinatesIsCorrect(row, col));

        field[row][col] = DOT_O;
    }

    private void humanStep() {

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

    public void humanStep(int row, int col){
        field[row][col] = DOT_X;
    }

    public boolean coordinatesIsCorrect(int row, int col) {
        if (row < 0 || row > FIELD_SIZE - 1) return false;
        if (col < 0 || col > FIELD_SIZE - 1) return false;
        if (field[row][col] != DOT_EMPTY) return false;
        return true;
    }

    private boolean coordinatesInCorrect(int row, int col, int dot) {
        if (row < 0 || row > FIELD_SIZE - 1) return false;
        if (col < 0 || col > FIELD_SIZE - 1) return false;
        if (field[row][col] != dot) return false;
        return true;
    }

    public boolean checkFieldIsFull() {
        for (int i = 0; i < FIELD_SIZE; i++) {
            for (int j = 0; j < FIELD_SIZE; j++) {
                if (field[i][j] == DOT_EMPTY) return false;
            }
        }
        return true;
    }

    private void printField() {
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

    private void initField() {
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
    public void aiSmartStep() {

        if (tryMakeBotWinStep()) return;
        if (tryCloseHumanWinStep()) return;
        if (tryMakeBotForkStep()) return;
        if (tryCloseHumanForkStep()) return;
        //пробуем сделать ход рядом с оппонентом
        if (tryNearOpponentStep()) return;

        //угроз нет. Ходим случайно.
        if (showAiMessages)
            System.out.println("Сообщение бота - Угроз и возможностей выйграть не найдено. Случайный ход.");
        aiStep();
    }

    /**
     * Выбирает случайное поле стреди тех, которые граничат с оппонентом.
     * Чем больше оппонентоав по соседству, тем вероятнее ход.
     */
    private boolean tryNearOpponentStep() {

        ArrayList<int[]> variants = new ArrayList<int[]>();
        for (int row = 0; row < FIELD_SIZE; row++) {
            for (int col = 0; col < FIELD_SIZE; col++) {
                if (coordinatesIsCorrect(row, col)) {
                    for (int x = row - 1; x <= row + 1; x++)
                        for (int y = col - 1; y <= col + 1; y++) {
                            if (coordinatesInCorrect(x, y, DOT_X)) {
                                //добавляем несколько раз, если поле граничит с несколькими X
                                variants.add(new int[]{row, col});
                            }
                        }
                }
            }
        }

        if(variants.size() == 0 ){
            return false;
        } else {
            if (showAiMessages) System.out.println("Сообщение бота - Найден ход рядом с " + DOT_X);
            //выбираем случайный ваниант среди найденных
            int randomI = random.nextInt(variants.size());
            int[] v = variants.get(randomI);
            field[v[0]][v[1]] = DOT_O;
            return true;
        }
    }

    /**
     * Попытка закрыть вилку человека
     */
    private boolean tryCloseHumanForkStep() {
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
    private boolean tryMakeBotForkStep() {
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
    private boolean tryCloseHumanWinStep() {
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
    private boolean tryMakeBotWinStep() {
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
    private boolean checkFork(char dot) {
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
    private boolean checkForkVertical(char dot) {
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
    private boolean checkForkHorizontal(char dot) {
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
    private boolean checkForkSecondDiagonal(char dot) {
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
    private boolean checkForkMainDiagonal(char dot) {
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
