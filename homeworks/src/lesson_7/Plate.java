package lesson_7;

public class Plate {
    private int food;

    public Plate(int food) {
        this.food = food;
    }

    /**
     * Добавляет еду в тарелку
     */
    public void addFood(int food) {
        if (food <= 0) return;
        this.food += food;
    }

    public void info() {
        System.out.println("-----------------------------");
        System.out.println("В таредке " + food + " еды");
        System.out.println("-----------------------------");
    }

    /**
     * Уменьшает еду в тарелка на количество, которое съела кошка
     *
     * @param maxTake - сколько еды максимум может съесть кошка
     * @param canEatPart - может ли кошка съесть меньше, чем может
     * @return  - сколько еды в реальность съела
     */
    public int takeFood(int maxTake, boolean canEatPart) {
        if (maxTake <= 0) return 0;
        if(!canEatPart && food < maxTake) return 0;

        int curTake = Math.min(food, maxTake);
        food -= curTake;
        return curTake;
    }
}
