package com.dunno.tasks;

import org.gradle.api.DefaultTask;
import org.gradle.api.tasks.TaskAction;

public class HelloArchitectureTask extends DefaultTask {

    @TaskAction
    void run() {
        System.out.println("Hello from Spring Architecture plugin!");
    }
}
