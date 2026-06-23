package com.dunno.tasks;

import org.gradle.api.DefaultTask;
import org.gradle.api.provider.Property;
import org.gradle.api.tasks.Input;
import org.gradle.api.tasks.TaskAction;

public abstract class HelloArchitectureTask extends DefaultTask {

    @Input
    public abstract Property<String> getPack();

    @Input
    public abstract Property<String> getProjectName();

    @Input
    public abstract Property<String> getJavaVersion();

    @TaskAction
    void run() {
        System.out.println("Hello from Spring Architecture plugin!");

        System.out.printf(
                "Package name: %s\nProject name: %s\nJava version: %s\n",
                getPack().get(),
                getProjectName().get(),
                getJavaVersion().get()
        );
    }
}
