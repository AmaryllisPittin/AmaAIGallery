import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.ScrollPane;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

public class PhotoGalleryApp extends Application {

    private BorderPane root; // Déclarez root comme variable d'instance

    private static final String[] IMAGE_PATHS = {
            "img/biblic1.png",
            "img/biblic2.png",
            "img/bijoux1.png",
            "img/casc1.png",
            "img/casc2.png",
            // "img/cavern1.png",
            // "img/chesterfield1.png",
            // "img/chesterfield2.png",
            // "img/chesterfield3.png",
            // "img/chesterfield4.png",
            "img/colomb1.png",
            "img/colomb2.png",
            "img/couronne1.png",
            "img/cross1.png",
            "img/cross2.png",
            "img/cross3.png",
            "img/fire.png",
            "img/fire2.png",
            "img/fire3.png",
            // "img/fire4.png",
            "img/galaxy1.png",
            "img/indian1.png",
            // "img/jurassicroom1.png",
            // "img/key1.png",
            // "img/key2.png",
            // "img/key3.png",
            "img/kingdom1.png",
            "img/knife1.png",
            // "img/knight1.png",
            // "img/knight2.png",
            "img/knight3.png",
            "img/lamp.png",
            "img/landscape1.png",
            "img/lavalamp1.png",
            "img/lavalamp2.png",
            "img/lavalamp3.png",
            "img/lavalamp4.png",
            // "img/livingroom1.png",
            // "img/magic1.png",
            "img/miror.png",
            // "img/pharaon.png",
            "img/portrait1.png",
            "img/portrait2.png",
            // "img/precious1.png",
            "img/space1.png",
            "img/space2.png",
            "img/space3.png",
            // "img/space4.png",
            // "img/space5.png",
            "img/space6.png",
            "img/space7.png",
            // "img/sword1.png",
            "img/temple1.png",
            "img/temple2.png",
            "img/temple3.png",
            "img/temple4.png",
            "img/temple5.png",
            "img/temple6.png",
            "img/temple7.png",
            "img/throne1.png",
            "img/throne2.png",
            // "img/throne3.png",
            "img/throne4.png",
            "img/throne5.png",
            "img/tree1.png",
            // "img/woodroom1.png",
    };

    @Override
    public void start(Stage primaryStage) {
        primaryStage.setTitle("AmaIAGallery");

        root = new BorderPane();
        root.setStyle("-fx-background-color: white");

        GridPane gallery = createGallery();

        ScrollPane scrollPane = new ScrollPane(gallery);
        scrollPane.setFitToWidth(true);
        scrollPane.setPannable(true);
        scrollPane.setStyle("-fx-background: transparent;");
        scrollPane.setHbarPolicy(ScrollPane.ScrollBarPolicy.NEVER);

        root.setCenter(scrollPane);

        root.widthProperty().addListener((obs, oldVal, newVal) -> {
            double widthPerImage = (newVal.doubleValue() - 30) / 2;
            double heightPerImage = widthPerImage * 2 / 4;

            for (int i = 0; i < gallery.getChildren().size(); i++) {
                ImageView imageView = (ImageView) gallery.getChildren().get(i);
                imageView.setFitWidth(widthPerImage);
                imageView.setFitHeight(heightPerImage);
            }
        });

        Scene scene = new Scene(root, 500, 600);
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    private GridPane createGallery() {
        GridPane gallery = new GridPane();
        gallery.setPadding(new Insets(10));
        gallery.setHgap(5);
        gallery.setVgap(5);
        gallery.setAlignment(Pos.CENTER);

        int column = 0;
        int row = 0;

        for (String imagePath : IMAGE_PATHS) {
            Image image = new Image(imagePath);
            ImageView thumbnail = new ImageView(image);

            thumbnail.setFitWidth(450);
            thumbnail.setFitHeight(337.5);
            thumbnail.setPreserveRatio(true);

            thumbnail.setOnMouseClicked(event -> openImageInFullScreen(image));

            gallery.add(thumbnail, column, row);

            column++;
            if (column == 4) {
                column = 0;
                row++;
            }
        }

        return gallery;
    }

    private void openImageInFullScreen(Image image) {
        Stage fullscreenStage = new Stage();
        fullscreenStage.setTitle("Image Agrandie");

        ImageView imageView = new ImageView(image);
        imageView.setPreserveRatio(true);
        imageView.setFitWidth(600);
        imageView.setFitHeight(600);

        StackPane root = new StackPane(imageView);
        root.setStyle("-fx-background-color: black;");

        Scene scene = new Scene(root, 600, 600);
        fullscreenStage.setScene(scene);
        fullscreenStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
