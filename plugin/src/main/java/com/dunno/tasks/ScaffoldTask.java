package com.dunno.tasks;

import org.gradle.api.DefaultTask;
import org.gradle.api.file.ProjectLayout;
import org.gradle.api.provider.Property;
import org.gradle.api.tasks.Input;
import org.gradle.api.tasks.TaskAction;

import javax.inject.Inject;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;
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

        for(var pkg : packages) {
            try {
                String content;
                try (InputStream stream = getClass().getResourceAsStream(String.format("/templates/%s.build.gradle", pkg))) {

                    if (stream == null) {
                        throw new IllegalStateException("No template found for module: " + pkg);
                    }
                    content = new String(stream.readAllBytes(), StandardCharsets.UTF_8);
                }

                Files.writeString(baseDirectory.toPath().resolve(pkg).resolve("build.gradle"), content);
            } catch (IOException e) {
                System.out.println(e.getMessage());
            }
        }
    }

}
