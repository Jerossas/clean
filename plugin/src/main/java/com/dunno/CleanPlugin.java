package com.dunno;

import org.gradle.api.Plugin;
import org.gradle.api.Project;

public class CleanPlugin implements Plugin<Project> {
    @Override
    public void apply(Project project) {
        System.out.println("Hello world from clean plugin!");
        System.out.printf("Project name: %s", project.getName());
    }
}
