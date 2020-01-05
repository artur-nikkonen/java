package lesson_8;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class MyForm extends JFrame {

    JButton[][] field; // поле кнопок
    GameXO_v2 game = new GameXO_v2();
    boolean gameEnd = false; //индикатор окончания игры

    public MyForm() throws HeadlessException {

        setTitle("Ингра крестики-нолики");
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setBounds(300, 300, 400, 400);
        setResizable(false);

        createMenu();
        createButtonsField();
        updateField();

        setVisible(true);

    }

    /**
     * Копирует поле из игры и обновляет кнопки
     */
    private void updateField() {
        char[][] data = game.getField();
        for (int i = 0; i < data.length; i++) {
            for (int j = 0; j < data[i].length; j++) {
                field[i][j].setText(Character.toString(data[i][j]));
            }
        }
    }

    /**
     * Создает кнопки поля и вставляет их в окно
     */
    private void createButtonsField() {
        int size = game.getFieldSize();

        field = new JButton[size][];

        setLayout(new GridLayout(size, size));

        for (int x = 0; x < size; x++) {
            field[x] = new JButton[size];
            for (int y = 0; y < size; y++) {
                JButton button = new JButton("");
                button.setFont(new Font("Arial", Font.PLAIN, 30));
                button.putClientProperty("x", x);
                button.putClientProperty("y", y);
                button.addActionListener(e -> {
                    int x1 = (Integer) ((JButton) e.getSource()).getClientProperty("x");
                    int y1 = (Integer) ((JButton) e.getSource()).getClientProperty("y");
                    System.out.println("Нажата кнопка " + x1 + " " + y1);
                    makeStep(x1, y1);
                });
                field[x][y] = button;

                add(button);
            }
        }
    }

    /**
     * Создает меню
     */
    private void createMenu() {
        JMenuBar menuBar = new JMenuBar();
        JMenu mainMenu = new JMenu("Меню");
        JMenuItem newGame = new JMenuItem("Новая игра");
        newGame.addActionListener(e -> startNewGame());

        JMenuItem exit = new JMenuItem("Выход");
        exit.addActionListener(e -> System.exit(0));
        mainMenu.add(newGame);
        mainMenu.add(exit);
        menuBar.add(mainMenu);

        MouseListener ml = new MouseListener() {
            public void mouseClicked(MouseEvent e) {
            }

            public void mousePressed(MouseEvent e) {
            }

            public void mouseReleased(MouseEvent e) {
            }

            public void mouseExited(MouseEvent e) {
            }

            public void mouseEntered(MouseEvent e) {
                ((JMenu) e.getSource()).doClick();
            }
        };
        mainMenu.addMouseListener(ml);

        setJMenuBar(menuBar);

    }

    private void makeStep(int row, int col) {

        if (gameEnd) return; //текущая игра закончена
        if (!game.coordinatesIsCorrect(row, col)) return; //ячейка занята

        game.humanStep(row, col);//делаем ход человека

        if (game.humanIsWin()) {
            gameEnd = true;
            updateField();
            JOptionPane.showMessageDialog(this, "Поздравляем!!! Вы выиграли");
            return;
        }

        if(game.checkFieldIsFull()){
            gameEnd = true;
            updateField();
            JOptionPane.showMessageDialog(this, "Ничья");
            return;
        }

        game.aiSmartStep();
        if (game.aiIsWin()) {
            gameEnd = true;
            updateField();
            JOptionPane.showMessageDialog(this, "Вы проиграли(((");
            return;
        }
        updateField();
    }

    private void startNewGame() {
        game.resetGame();
        gameEnd = false;
        updateField();
        System.out.println("Старт новой игры");
    }
}

