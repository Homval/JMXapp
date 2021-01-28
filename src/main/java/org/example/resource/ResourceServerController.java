package org.example.resource;

public class ResourceServerController implements ResourceServerControllerMBean{
    private TestResource testResource;

    public ResourceServerController(TestResource testResource) {
        this.testResource = testResource;
    }

    public TestResource getTestResource() {
        return testResource;
    }

    @Override
    public String getResourceName(TestResource testResource) {
        String name = testResource.getName();
        System.out.println("ResourceName: " + name);
        return name;
    }

    @Override
    public int getResourceAge(TestResource testResource) {
        int age = testResource.getAge();
        System.out.println("Resource age: " + age);
        return age;
    }
}
