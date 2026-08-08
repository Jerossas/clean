package com.dunno.tasks;

import org.gradle.api.DefaultTask;
import org.gradle.api.file.ProjectLayout;
import org.gradle.api.tasks.TaskAction;

import javax.inject.Inject;
import java.io.File;

public abstract class ScaffoldTask extends DefaultTask {

    @Inject
    protected abstract ProjectLayout getProjectLayout();

    @TaskAction
    void run() {

        File baseDirectory = getProjectLayout().getProjectDirectory()
                .dir(".")
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
    }
}
