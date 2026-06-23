package com.dunno.extensions;

import org.gradle.api.provider.Property;

public abstract class CleanExtension {

    public abstract Property<String> getPack();
    public abstract Property<String> getProjectName();
    public abstract Property<String> getJavaVersion();
}
