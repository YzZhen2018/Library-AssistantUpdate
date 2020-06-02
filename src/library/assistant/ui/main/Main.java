package library.assistant.ui.main;

import javafx.application.Application;
import static javafx.application.Application.launch;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundImage;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import library.assistant.database.DatabaseHandler;
import library.assistant.exceptions.ExceptionUtil;
import library.assistant.util.LibraryAssistantUtil;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class Main extends Application {

    private final static Logger LOGGER = LogManager.getLogger(Main.class.getName());

    @Override
    public void start(Stage stage) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("/library/assistant/ui/login/login.fxml"));
        /*Text text = new Text("显示提示信息成功!");
        text.setFont(new Font(20));
        text.setFill(Color.GREEN);
        VBox box = new VBox();
        box.getChildren().add(text);
        box.setStyle("-fx-background:transparent;");
        final int width = 200;
        final int height = 50;
        final Scene scene = new Scene(box, width, height);*/
        root.setStyle("-fx-background:transparent;");
        Scene scene = new Scene(root);
        scene.setFill(null);
        stage.setScene(scene);
        stage.initStyle(StageStyle.TRANSPARENT);
        stage.show();
        stage.setTitle("Library Assistant Login");

        LibraryAssistantUtil.setStageIcon(stage);

        new Thread(() -> {
            ExceptionUtil.init();
            DatabaseHandler.getInstance();
        }).start();
    }

    public static void main(String[] args) {
        Long startTime = System.currentTimeMillis();
        LOGGER.log(Level.INFO, "Library Assistant launched on {}", LibraryAssistantUtil.formatDateTimeString(startTime));
        launch(args);
        Runtime.getRuntime().addShutdownHook(new Thread() {
            @Override
            public void run() {
                Long exitTime = System.currentTimeMillis();
                LOGGER.log(Level.INFO, "Library Assistant is closing on {}. Used for {} ms", LibraryAssistantUtil.formatDateTimeString(startTime), exitTime);
            }
        });
    }

}
