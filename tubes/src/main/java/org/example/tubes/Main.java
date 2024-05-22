package org.example.tubes;

import java.io.File;
import java.net.URL;
import java.net.URLClassLoader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import java.io.IOException;
import java.util.Enumeration;
import java.util.jar.JarEntry;
import java.util.jar.JarFile;

public class Main {
    public static void main(String[] args) {
        File pluginDir = new File("plugin");
        final List<Plugin> plugins = new ArrayList<>();

        try {
            List<String> jarFiles = Arrays.asList(pluginDir.list((dir, name) -> name.endsWith(".jar")));
            for (String jarFile : jarFiles) {
                File file = new File(pluginDir, jarFile);
                URL jarURL = file.toURI().toURL();
                URLClassLoader classLoader = new URLClassLoader(new URL[]{jarURL}, Main.class.getClassLoader());

                // Iterate over classes in the JAR and check if they implement the Plugin interface
                // Placeholder function to get class names from JAR
                List<String> classNames = getClassNamesFromJar(jarFile); // Implement this method

                for (String className : classNames) {
                    try {
                        Class<?> clazz = classLoader.loadClass(className);
                        if (Plugin.class.isAssignableFrom(clazz)) {
                            Plugin plugin = (Plugin) clazz.getDeclaredConstructor().newInstance();
                            plugins.add(plugin);
                        }
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        // Example usage
        GameState gameState = new GameState(); // Initialize with actual data
        for (Plugin plugin : plugins) {
            plugin.save("example_save.json", gameState);
            GameState loadedState = plugin.load("example_save.json");
            System.out.println("Loaded state: " + loadedState);
        }
    }



    private static List<String> getClassNamesFromJar(String jarFilePath) {
        List<String> classNames = new ArrayList<>();
        try (JarFile jarFile = new JarFile(jarFilePath)) {
            Enumeration<JarEntry> entries = jarFile.entries();
            while (entries.hasMoreElements()) {
                JarEntry entry = entries.nextElement();
                if (entry.getName().endsWith(".class")) {
                    String className = entry.getName().replace("/", ".").replace(".class", "");
                    classNames.add(className);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return classNames;
    }

}
