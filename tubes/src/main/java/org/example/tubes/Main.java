package org.example.tubes;
import java.io.File;
import java.net.URL;
import java.net.URLClassLoader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class Main{
    public static void main(String[] args) {
        File f = new File("Plugin");
        final List<Plugin> Plugins = new ArrayList<>();
        try{
            List<String> names = Arrays.asList(f.list());
            names.forEach(name -> {
                final Class c;
                try {
                    c = Class.forName(name);
                } catch (ClassNotFoundException e) {
                    throw new RuntimeException(e);
                }
                final List<Class> interfaces = Arrays.asList(c.getInterfaces());
                    interfaces.forEach(itf -> {
                        if (itf.getName().equals("Plugin")) {
                            Plugin fl = null;
                            try {
                                fl = (Plugin) c.newInstance();
                            } catch (InstantiationException e) {
                                throw new RuntimeException(e);
                            } catch (IllegalAccessException e) {
                                throw new RuntimeException(e);
                            }
                            Plugins.add(fl);
                        }
                    });
            });
        }catch(Exception e){

        }

    }


}

