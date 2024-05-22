package org.example.tubes;

import java.io.File;
import java.lang.reflect.Method;
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
//                List<String> classNames = getClassNamesFromJar(jarFile); // Implement this method
                List<String> classNames = getClassNamesFromJar(jarFile);

                for (String className : classNames) {
                    try {
                        Class<?> clazz = classLoader.loadClass(className);

                        // Periksa apakah kelas mengimplementasikan interface Plugin
                        if (Plugin.class.isAssignableFrom(clazz)) {
                            // Dapatkan metode 'load' dan 'save' dari kelas
                            Method loadMethod = clazz.getDeclaredMethod("load", String.class);
                            Method saveMethod = clazz.getDeclaredMethod("save", String.class, GameState.class);

                            // Jika kelas memiliki kedua metode 'load' dan 'save', tambahkan ke list plugin
                            if (loadMethod != null && saveMethod != null) {
                                Plugin plugin = (Plugin) clazz.getDeclaredConstructor().newInstance();
                                plugins.add(plugin);
                            }
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
                if (!entry.isDirectory() && entry.getName().endsWith(".class")) {
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
