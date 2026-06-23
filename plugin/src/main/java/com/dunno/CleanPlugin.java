package com.dunno;

import com.dunno.extensions.CleanExtension;
import com.dunno.tasks.HelloArchitectureTask;
import org.gradle.api.Plugin;
import org.gradle.api.Project;
import org.gradle.api.tasks.TaskProvider;

public class CleanPlugin implements Plugin<Project> {
    @Override
    public void apply(Project project) {

        var extension = project.getExtensions().create("clean", CleanExtension.class);

        TaskProvider<HelloArchitectureTask> taskProvider =
                project.getTasks().register(
                        "helloArchitecture",
                        HelloArchitectureTask.class
                );

        taskProvider.configure(helloArchitectureTask -> {
            helloArchitectureTask.setGroup("Architecture");
            helloArchitectureTask.setDescription("Prints a greeting message to the console.");

            helloArchitectureTask.getPack().set(extension.getPack());
            helloArchitectureTask.getProjectName().set(extension.getProjectName());
            helloArchitectureTask.getJavaVersion().set(extension.getJavaVersion());
        });
    }
}
