package resourceService;

import resources.TestResource;

public class ResourceService implements ResourceServiceMBean {
    private TestResource testResource;

    public ResourceService(TestResource testResource) {
        this.testResource = testResource;
    }

    @Override
    public String getname() {
        String name = testResource.getName();
        System.out.println("ResourceName: " + name);
        return name;
    }

    @Override
    public int getage() {
        int age = testResource.getAge();
        System.out.println("Resource age: " + age);
        return age;
    }
}
