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
import javafx.scene.control.ComboBox;
import java.io.File;
import java.io.IOException;
import java.util.Objects;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Enumeration;
import java.util.jar.JarEntry;
import java.util.jar.JarFile;

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
        System.out.println("test");
        System.out.println(gamestate.getJumlahTurn());
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
        File destDir = new File("target/classes/org/example/tubes/");
        try {
            extractJar(pluginDir, destDir);
        } catch (IOException e) {
            e.printStackTrace();
        }
        final List<Plugin> plugins = loadPluginsFromJar(pluginDir);
        if (plugins.size() == 0) {
            return false;
        }
        this.gamestate.availPlugin.add(plugins.get(0));
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
        FXMLLoader loader = new FXMLLoader(getClass().getResource("main.fxml"));
        Parent root = loader.load();
        MainController mainController = loader.getController();
        mainController.setPlayerAndCards(this.gamestate, false);

        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        Scene scene  = new Scene(root);
        scene.getStylesheets().add(Objects.requireNonNull(getClass().getResource("main.css")).toExternalForm());
        stage.setScene(scene);
        stage.show();
    }
    public static void extractJar(File jarFile, File destDir) throws IOException {
        if (!destDir.exists()) {
            destDir.mkdirs();
        }
        try (JarFile jar = new JarFile(jarFile)) {
            Enumeration<JarEntry> entries = jar.entries();
            while (entries.hasMoreElements()) {
                JarEntry entry = entries.nextElement();
                File entryDestination = new File(destDir, entry.getName());
                if (entry.isDirectory()) {
                    entryDestination.mkdirs();
                } else {
                    entryDestination.getParentFile().mkdirs();
                    try (InputStream in = jar.getInputStream(entry);
                         FileOutputStream out = new FileOutputStream(entryDestination)) {
                        byte[] buffer = new byte[1024];
                        int len;
                        while ((len = in.read(buffer)) != -1) {
                            out.write(buffer, 0, len);
                        }
                    }
                }
            }
        }
    }


    private static List<Plugin> loadPluginsFromJar(File pluginDir) {
        List<Plugin> plugins = new ArrayList<>();
        try {
            if (pluginDir.isFile() && pluginDir.getName().endsWith(".jar")) {
                URL jarURL = pluginDir.toURI().toURL();
                URLClassLoader classLoader = new URLClassLoader(new URL[]{jarURL}, Main.class.getClassLoader());
                List<String> classNames = getClassNamesFromJar(pluginDir.getPath());

                for (String className : classNames) {
                    try {

                        String fullClassName = "org.example.tubes." + className;
                        Class<?> clazz = classLoader.loadClass(fullClassName);
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
            } else {
                System.err.println("The specified path is not a valid .jar file: " + pluginDir.getAbsolutePath());
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
