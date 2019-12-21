public class Cat extends Animal {

    private static int counter = 0;

    public Cat(String name) {
        super(name, 200, 0, 2);
        counter++;
    }

    public Cat(String name, float health) {
        super(name, 500, 10, 0.5f, health);
        counter++;
    }

    public Cat(String name, float runDistance, float swimDistance, float jumpDistance) {
        super(name, runDistance, swimDistance, jumpDistance);
        counter++;
    }

    public static int getCounter() {
        return counter;
    }

    private String getPrefix() {
        return "Кошка " + getName() + ": ";
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
        System.out.println(getPrefix()
                + "НЕ проплыла " + distance
                + ". Не умеет плавать");
    }

    @Override
    public void jump(int distance) {
        System.out.println(getPrefix() +
                (distance <= getJumpDistance() ? "" : "НЕ ")
                + "перепныгнула " + distance
                + ". Максимальная высота: " + getJumpDistance());
    }
}
