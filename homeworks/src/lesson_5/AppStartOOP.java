package lesson_5;

/**
 * Создать класс "Сотрудник" с полями: ФИО, должность, email, телефон, зарплата, возраст.
 * Конструктор класса должен заполнять эти поля при создании объекта.
 * Внутри класса «Сотрудник» написать метод, который выводит информацию об объекте в консоль.
 * Создать массив из 5 сотрудников.
 * С помощью цикла вывести информацию только о сотрудниках старше 40 лет.
 */

public class AppStartOOP {
    public static void main(String[] args) {

        Person[] persons = new Person[5];

        persons[0] = new Person("Василий", "Рабочий", "email-1@gmail.com", "5551315", 15000, 25);
        persons[2] = new Person("Евгений", "Технолог", "email-4@gmail.com", "5551318", 55000, 53);
        persons[1] = new Person("Елена", "Директор", "email-5@gmail.com", "5551319", 85000, 67);
        persons[3] = new Person("Петр", "Рабочий", "email-2@gmail.com", "5551316", 15000, 30);
        persons[4] = new Person("Николай", "Прораб", "email-3@gmail.com", "5551317", 55000, 40);

        System.out.println("Информация о сотрудниках старше 40 лет:");

        for (Person p : persons) {
            if (p.age >= 40) p.print();
        }
    }
}
