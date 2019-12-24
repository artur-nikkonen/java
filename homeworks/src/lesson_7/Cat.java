package lesson_7;

public class Cat {
    private String name;
    private int appetite;
    private int alreadyEaten;
    private boolean canEatPart; // может ли кошка съесть порцию меньше, чем нужно, чтобы стать сытой

    public Cat(String name, int appetite, boolean canEatPart) {
        this.name = name;
        this.appetite = appetite;
        this.canEatPart = canEatPart;
        alreadyEaten = 0;
    }

    public void info() {
        System.out.printf("%s (аппетит: %d, всего съел: %d, ест частями: %b)", name, appetite, alreadyEaten, canEatPart);

        if (alreadyEaten == 0) System.out.println(" - голоден");
        else if (alreadyEaten >= appetite) System.out.println(" - сыт");
        else System.out.println(" - съел " + alreadyEaten + " из " + appetite);
    }

    public void eat(Plate plate) {
        int maxTake = appetite - alreadyEaten;
        if (maxTake <= 0) return;

        int curTake = plate.takeFood(maxTake, canEatPart);
        alreadyEaten += curTake;
    }

    /**
     * Показывает, сыла ли кошка
     */
    boolean isFull() {
        return alreadyEaten >= appetite;
    }
}
