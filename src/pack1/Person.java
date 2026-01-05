package pack1;

public class Person {
    // Common fields for all persons
    protected String name;
    protected String mobile;
    protected int age;
    
    // Constructor
    public Person(String name, String mobile, int age) {
        this.name = name;
        this.mobile = mobile;
        this.age = age;
    }
    
    // Getter methods
    public String getName() {
        return name;
    }
    
    public String getMobile() {
        return mobile;
    }
    
    public int getAge() {
        return age;
    }
    
    // Setter methods (if needed)
    public void setName(String name) {
        this.name = name;
    }
    
    public void setMobile(String mobile) {
        this.mobile = mobile;
    }
    
    public void setAge(int age) {
        this.age = age;
    }
}

