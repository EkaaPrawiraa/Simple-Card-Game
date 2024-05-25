package org.example.tubes;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.paint.Color;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import java.io.File;
import java.io.IOException;
import java.util.Objects;


import org.example.tubes.Plugin;

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

public class PluginController {
    private GameState gamestate;
    public void setGamestate(GameState gamestate) {
        this.gamestate = gamestate;
    }
    @FXML
    private Label messageLabel;
    @FXML
    private Label filename;
    @FXML
    private void handlePlugin() {
        boolean addsuccess = performPlugin();

        if (addsuccess) {
            messageLabel.setText("Plugin Loaded Successfully");
            messageLabel.setTextFill(Color.GREEN);
        } else {
            messageLabel.setText("Error: File is not a valid JAR");
            messageLabel.setTextFill(Color.RED);
        }
    }
    private boolean performPlugin(){
        File pluginDir = new File(this.filename.getText());
        final List<Plugin> plugins = loadPluginsFromJar(pluginDir);
        if (plugins.size() == 0) {
            return false;
        }
        this.gamestate.availPlugin=plugins;
        return true;
    }

    @FXML
    private void handleChooseFile(){
        FileChooser filechooser = new FileChooser();
        filechooser.setTitle("Chooser JAR File");
        filechooser.getExtensionFilters().add(new FileChooser.ExtensionFilter("JAR Files", "*.jar"));
        Stage stage = (Stage) filename.getScene().getWindow();

        File selectedFile = filechooser.showOpenDialog(stage);

        if (selectedFile != null) {
            filename.setText(selectedFile.getPath());
        } else {
            filename.setText("No file chosen");
        }
    }
    @FXML
    private void switchMain(ActionEvent event) throws IOException {
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("main.fxml")));
        Scene scene = new Scene(root);
        scene.getStylesheets().add(Objects.requireNonNull(getClass().getResource("main.css")).toExternalForm());
        stage.setScene(scene);
        stage.show();
    }

    private static List<Plugin> loadPluginsFromJar(File pluginDir) {
        List<Plugin> plugins = new ArrayList<>();

        try {
            List<String> jarFiles = Arrays.asList(pluginDir.list((dir, name) -> name.endsWith(".jar")));
            for (String jarFile : jarFiles) {
                File file = new File(pluginDir, jarFile);
                URL jarURL = file.toURI().toURL();
                URLClassLoader classLoader = new URLClassLoader(new URL[]{jarURL}, Main.class.getClassLoader());
                List<String> classNames = getClassNamesFromJar(jarFile);

                for (String className : classNames) {
                    try {
                        Class<?> clazz = classLoader.loadClass(className);
                        if (Plugin.class.isAssignableFrom(clazz)) {
                            Method loadMethod = clazz.getDeclaredMethod("load", String.class);
                            Method saveMethod = clazz.getDeclaredMethod("save", String.class, GameState.class);
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

        return plugins;
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
