/**
 * A person's info with first name, last name, and age.
 */
public class Person {
    private String firstName;
    private String lastName;
    private int age;

    /**
     * Constructor to initialize a Person object.
     *
     * @param firstName First name of the person.
     * @param lastName  Last name of the person.
     * @param age       Age of the person.
     */
    public Person(String firstName, String lastName, int age) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.age = age;
    }

    /**
     * Gets the last name of the person.
     *
     * @return Last name.
     */
    public String getLastName() {
        return lastName;
    }

    /**
     * Gets the age of the person.
     *
     * @return Age.
     */
    public int getAge() {
        return age;
    }

    @Override
    public String toString() {
        return lastName + ", " + firstName + " (Age: " + age + ")";
    }
}
