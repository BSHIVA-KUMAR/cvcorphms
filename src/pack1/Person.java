package pack1;

public abstract class Person {
    protected String name;
    protected String mobile;
    protected int age;
    
    public Person(String name, String mobile, int age) {
        this.name = name;
        this.mobile = mobile;
        this.age = age;
    }
    
    // Getter mtds
    public String getName() {
        return name;
    }
    
    public String getMobile() {
        return mobile;
    }
    
    public int getAge() {
        return age;
    }
    
    // Setter methods 
    public void setName(String name) {
        this.name = name;
    }
    
    public void setMobile(String mobile) {
        this.mobile = mobile;
    }
    
    public void setAge(int age) {
        this.age = age;
    }
    
    // ABSTRACT mtd 
    public abstract String displayInfo();
}


