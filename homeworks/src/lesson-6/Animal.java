public abstract class Animal {

    private static int counter = 0;

    private String name;

    private float runDistance;
    private float swimDistance;
    private float jumpDistance;
    private float health = 1; //уровень здоровья, который увеличивает или уменьшает возможности животного


    public String getName() {
        return name;
    }

    public static int getCounter() {
        return counter;
    }

    public float getHealth() {
        return health;
    }

    public void setHealth(float health) {
        if (health >= 0 && health <= 2) {
            this.health = health;
        }
    }

    public float getRunDistance() {
        return runDistance * health;
    }

    public float getSwimDistance() {
        return swimDistance * health;
    }

    public float getJumpDistance() {
        return jumpDistance * health;
    }

    public Animal(String name, float runDistance, float swimDistance, float jumpDistance, float health) {
        this.name = name;
        this.runDistance = runDistance;
        this.swimDistance = swimDistance;
        this.jumpDistance = jumpDistance;
        setHealth(health);
        counter++;
    }

    public Animal(String name, float runDistance, float swimDistance, float jumpDistance) {
        this.name = name;
        this.runDistance = runDistance;
        this.swimDistance = swimDistance;
        this.jumpDistance = jumpDistance;
        counter++;
    }

    public abstract void run(float distance);
    public abstract void swim(int distance);
    public abstract void jump(int distance);
}
