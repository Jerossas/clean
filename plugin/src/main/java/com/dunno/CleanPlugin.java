package com.dunno;

import org.gradle.api.Plugin;
import org.gradle.api.Project;

public class CleanPlugin implements Plugin<Project> {
    @Override
    public void apply(Project project) {
        project.getTasks().register("helloArchitecture", task -> {

            task.setDescription("Displays a greeting from the architecture plugin");
            task.setGroup("greeting");
            task.doFirst(t -> {
                System.out.println("Hello from Spring Architecture plugin!");
            });
        });
    }
}
