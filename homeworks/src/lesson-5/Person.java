public class Person {
    String name;
    String position;
    String email;
    String tel;
    float salary;
    int age;

    public Person(String name, String position, String email, String tel, float salary, int age) {
        this.name = name;
        this.position = position;
        this.email = email;
        this.tel = tel;
        this.salary = salary;
        this.age = age;
    }

    @Override
    public String toString() {
        return "Person{" +
                "name='" + name + '\'' +
                ", position='" + position + '\'' +
                ", email='" + email + '\'' +
                ", tel='" + tel + '\'' +
                ", salary=" + salary +
                ", age=" + age +
                '}';
    }

    public void print() {
        System.out.println(toString());
    }
}
