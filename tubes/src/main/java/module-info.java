module org.example.tubes {
    requires javafx.controls;
    requires javafx.fxml;

    requires org.controlsfx.controls;
    requires com.dlsc.formsfx;
    requires org.kordamp.ikonli.javafx;
    requires com.fasterxml.jackson.databind;
    requires com.fasterxml.jackson.dataformat.yaml;
    requires java.desktop;

    opens org.example.tubes to javafx.fxml;
    exports org.example.tubes;
    exports org.example.tubes.plugin;
    opens org.example.tubes.plugin to javafx.fxml;
}