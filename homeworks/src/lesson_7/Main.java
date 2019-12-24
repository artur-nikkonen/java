package lesson_7;

import java.util.Scanner;

/**
 * Расширить задачу про котов и тарелки с едой.
 * Сделать так, чтобы в тарелке с едой не могло получиться отрицательного количества еды (например, в миске 10 еды, а кот пытается покушать 15-20).
 * Каждому коту нужно добавить поле сытость (когда создаем котов, они голодны). Если коту удалось покушать (хватило еды), сытость = true.
 * Считаем, что если коту мало еды в тарелке, то он её просто не трогает, то есть не может быть наполовину сыт (это сделано для упрощения логики программы).
 * Создать массив котов и тарелку с едой, попросить всех котов покушать из этой тарелки и потом вывести информацию о сытости котов в консоль.
 * Добавить в тарелку метод, с помощью которого можно было бы добавлять еду в тарелку.
 */
public class Main {

    static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        Plate plate = new Plate(25);
        Cat[] cats = {
                new Cat("Мурка", 15, true),
                new Cat("Барсик", 12, false),
                new Cat("Маркиза", 17, true),
                new Cat("Муся", 8, false),
        };

        while (true) {

            int notFullCatsCount = 0; // количество несытых кошек

            plate.info();

            for (Cat c : cats) {
                c.eat(plate);
                c.info();
                if (!c.isFull()) notFullCatsCount++;
            }

            if (notFullCatsCount == 0) {
                System.out.println("=============================");
                System.out.println("Поздравляем! Вы накормили всех кошек.");
                System.out.println("=============================");
                break;
            }

            plate.info();

            System.out.println("Сколько досыпать корма? 0 - выход");
            int food = scanner.nextInt();
            if (food == 0) break;
            plate.addFood(food);
        }
        scanner.close();
    }
}
