package org.example.resourceService;

import org.example.resources.TestResource;

public class ResourceService implements ResourceServiceMBean {
    private TestResource testResource;

    public ResourceService(TestResource testResource) {
        this.testResource = testResource;
    }

    @Override
    public String getName() {
        String name = testResource.getName();
        System.out.println("ResourceName: " + name);
        return name;
    }

    @Override
    public int getAge() {
        int age = testResource.getAge();
        System.out.println("Resource age: " + age);
        return age;
    }
}
