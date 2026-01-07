package pack1;

public abstract class Person {
    protected String name;
    protected String email;
    protected String mobile;
    protected int age;
    
    public Person(String name, String email, String mobile, int age) {
        this.name = name;
        this.email = email;
        this.mobile = mobile;
        this.age = age;
    }
    
    // Getter mtds
    public String getName() {
        return name;
    }
    
    public String getEmail() {
        return email;
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
    
    public void setEmail(String email) {
        this.email = email;
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


