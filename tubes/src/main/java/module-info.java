module org.example.tubes {
    requires javafx.controls;
    requires javafx.fxml;

    requires org.controlsfx.controls;
    requires com.dlsc.formsfx;
    requires org.kordamp.ikonli.javafx;

    opens org.example.tubes to javafx.fxml;
    exports org.example.tubes;
    exports org.example.tubes.plugin;
    opens org.example.tubes.plugin to javafx.fxml;
}