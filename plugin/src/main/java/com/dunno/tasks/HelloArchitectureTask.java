package com.dunno.tasks;

import org.gradle.api.DefaultTask;
import org.gradle.api.provider.Property;
import org.gradle.api.tasks.Input;
import org.gradle.api.tasks.TaskAction;
import org.gradle.api.tasks.options.Option;

public abstract class HelloArchitectureTask extends DefaultTask {

    @Input
    @Option(option = "developer-name", description = "The developer name to greet")
    public abstract Property<String> getDeveloperName();

    @Input
    public abstract Property<String> getPack();

    @Input
    public abstract Property<String> getProjectName();

    @Input
    public abstract Property<String> getJavaVersion();

    @TaskAction
    void run() {
        System.out.println("Hello from Spring Architecture plugin!");

        System.out.println("Hope you doing well, " + getDeveloperName().get());

        System.out.printf(
                "Package name: %s\nProject name: %s\nJava version: %s\n",
                getPack().get(),
                getProjectName().get(),
                getJavaVersion().get()
        );
    }
}
