package com.dunno;

import com.dunno.tasks.HelloArchitectureTask;
import org.gradle.api.Plugin;
import org.gradle.api.Project;
import org.gradle.api.tasks.TaskProvider;

public class CleanPlugin implements Plugin<Project> {
    @Override
    public void apply(Project project) {
        TaskProvider<HelloArchitectureTask> taskProvider =
                project.getTasks().register(
                        "helloArchitecture",
                        HelloArchitectureTask.class
                );

        taskProvider.configure(helloArchitectureTask -> {
            helloArchitectureTask.setGroup("Architecture");
            helloArchitectureTask.setDescription("Prints a greeting message to the console.");
        });
    }
}
