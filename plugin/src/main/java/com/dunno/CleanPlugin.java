package com.dunno;

import com.dunno.extensions.CleanExtension;
import com.dunno.tasks.HelloArchitectureTask;
import com.dunno.tasks.ScaffoldTask;
import org.gradle.api.Plugin;
import org.gradle.api.Project;
import org.gradle.api.tasks.TaskProvider;
import org.gradle.api.tasks.wrapper.Wrapper;

public class CleanPlugin implements Plugin<Project> {

    @Override
    public void apply(Project project) {

        project.getTasks().named("wrapper", Wrapper.class, wrapper -> {

            wrapper.setGradleVersion("9.5.1");
        });
        var helloArchitectureProvider = registerHelloArchitectureTask(project);
        var scaffoldProvider = registerScaffoldTask(project);
    }

    private TaskProvider<HelloArchitectureTask> registerHelloArchitectureTask(Project project) {

        var extension = project.getExtensions().create("clean", CleanExtension.class);
        var provider = project.getTasks().register("helloArchitecture", HelloArchitectureTask.class);

        provider.configure(helloArchitectureTask -> {
            helloArchitectureTask.setGroup("Architecture");
            helloArchitectureTask.setDescription("Prints a greeting message to the console.");

            helloArchitectureTask.getPack().set(extension.getPack());
            helloArchitectureTask.getProjectName().set(extension.getProjectName());
            helloArchitectureTask.getJavaVersion().set(extension.getJavaVersion());
        });

        return provider;
    }

    private TaskProvider<ScaffoldTask> registerScaffoldTask(Project project) {

        var provider = project.getTasks().register("scaffold", ScaffoldTask.class);

        provider.configure(scaffoldTask -> {
            scaffoldTask.setGroup("Architecture");
            scaffoldTask.dependsOn("wrapper");
        });

        return provider;
    }
}
