public class Dog extends Animal {

    private static int counter = 0;

    public Dog(String name) {
        super(name, 500, 10, 0.5f);
        counter++;
    }

    public Dog(String name, float health) {
        super(name, 500, 10, 0.5f, health);
        counter++;
    }

    public Dog(String name, float runDistance, float swimDistance, float jumpDistance) {
        super(name, runDistance, swimDistance, jumpDistance, 1);
        counter++;
    }

    public static int getCounter() {
        return counter;
    }

    private String getPrefix() {
        return "Собака " + getName() + ": ";
    }

    @Override
    public void run(float distance) {
        System.out.println(getPrefix() +
                (distance <= getRunDistance() ? "" : "НЕ ")
                + "пробежала " + distance
                + ". Максимальная дистанция: " + getRunDistance());
    }

    @Override
    public void swim(int distance) {
        System.out.println(getPrefix() +
                (distance <= getSwimDistance() ? "" : "НЕ ")
                + "проплыла " + distance
                + ". Максимальная дистанция: " + getSwimDistance());
    }

    @Override
    public void jump(int distance) {
        System.out.println(getPrefix() +
                (distance <= getJumpDistance() ? "" : "НЕ ")
                + "перепныгнула " + distance
                + ". Максимальная высота: " + getJumpDistance());
    }
}
