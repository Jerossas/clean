package com.dunno.tasks;

import org.gradle.api.DefaultTask;
import org.gradle.api.file.ProjectLayout;
import org.gradle.api.provider.Property;
import org.gradle.api.tasks.Input;
import org.gradle.api.tasks.TaskAction;

import javax.inject.Inject;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;

public abstract class ScaffoldTask extends DefaultTask {

    @Inject
    protected abstract ProjectLayout getProjectLayout();

    @Input
    public abstract Property<String> getProjectName();

    @TaskAction
    void run() {

        File baseDirectory = getProjectLayout().getProjectDirectory()
                .getAsFile();

        String[] packages = {"domain", "application", "infrastructure", "web"};

        for(String pkg : packages){
            File dir = new File(baseDirectory, pkg);

            try {
                if (dir.mkdirs())
                    System.out.println("'" + pkg + "'" + " module was created successfully");
            } catch (SecurityException e) {
                System.out.println(e.getMessage());
            }
        }

        StringBuilder includedPackages = new StringBuilder();

        for(var pkg : packages) {
            includedPackages.append(String.format("include '%s'%n", pkg));
        }

        String settingGradleContent = String.format(
                """
                rootProject.name = '%s'
                
                %s
                """,
                getProjectName().get(),
                includedPackages
        );

        try {
            Files.writeString(baseDirectory.toPath().resolve("settings-example.gradle"), settingGradleContent);
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }
}
