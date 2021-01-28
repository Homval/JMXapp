package org.example.resource;

public interface ResourceServerControllerMBean {
    String getResourceName(TestResource testResource);

    int getResourceAge(TestResource testResource);
}
