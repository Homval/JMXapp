package org.example.resource;

public class ResourceServerController implements ResourceServerControllerMBean{
    private TestResource testResource;

    public ResourceServerController(TestResource testResource) {
        this.testResource = testResource;
    }

    @Override
    public String getResourceName() {
        String name = testResource.getName();
        System.out.println("ResourceName: " + name);
        return name;
    }

    @Override
    public int getResourceAge() {
        int age = testResource.getAge();
        System.out.println("Resource age: " + age);
        return age;
    }
}
