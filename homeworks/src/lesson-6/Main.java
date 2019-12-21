/**
 * Вариант с сайта:
 * 1. Создать классы Собака и Кот с наследованием от класса Животное.
 * 2. Животные могут выполнять действия: бежать, плыть, перепрыгивать препятствие. В качестве параметра каждому методу передается величина, означающая или длину препятствия (для бега и плавания), или высоту (для прыжков).
 * 3. У каждого животного есть ограничения на действия (бег: кот 200 м., собака 500 м.; прыжок: кот 2 м., собака 0.5 м.; плавание: кот не умеет плавать, собака 10 м.).
 * 4. При попытке животного выполнить одно из этих действий, оно должно сообщить результат в консоль. (Например, dog1.run(150); -> результат: run: true)
 * 5. * Добавить животным разброс в ограничениях. То есть у одной собаки ограничение на бег может быть 400 м., у другой 600 м.
 * Вариант из методички: * Добавить подсчет созданных котов, собак и животных.
 */

public class Main {
    public static void main(String[] args) {
        //создаем животных
        //и задаем здоровье, чтобы изменить возможности животного. По умолчанию здоровие = 1
        Animal[] animals = {
                new Dog("Шарик"),
                new Dog("Бобик", 0.5f),
                new Dog("Палкан", 2f),
                new Cat("Рыжик"),
                new Cat("Муся", 1.2f),
        };

        //испытываем животных

        System.out.println("Испытания животных:");
        for (Animal a : animals) {
            a.run(300);
            a.jump(1);
            a.swim(8);
            System.out.println();
        }

        //выводим статистику
        System.out.println("Статистика");
        System.out.println("Всего создано животных: " + Animal.getCounter() );
        System.out.println("Всего создано собак: " + Dog.getCounter());
        System.out.println("Всего создано кошек: " + Cat.getCounter());
   }
}