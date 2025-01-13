import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
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
            "img/fire.png",
            "img/galaxy1.png",
            "img/knife1.png",
            "img/lamp.png",
            "img/lavalamp1.png",
            "img/lavalamp2.png",
            "img/lavalamp3.png",
            "img/lavalamp4.png",
            "img/throne1.png",
            "img/throne2.png"
    };

    @Override
    public void start(Stage primaryStage) {

        primaryStage.setTitle("AmaIAGallery");

        // Initialisation de root
        root = new BorderPane();
        root.setStyle("-fx-background-color: #2E2E2E;");

        // Créer la galerie
        GridPane gallery = createGallery();
        root.setCenter(gallery);

        // Ajouter un Listener pour ajuster la taille des images lorsque la fenêtre est
        // redimensionnée
        root.widthProperty().addListener((obs, oldVal, newVal) -> {
            double widthPerImage = (newVal.doubleValue() - 30) / 4; // 30 pour les marges et espaces
            double heightPerImage = widthPerImage * 3 / 4; // Ratio 4:3

            for (int i = 0; i < gallery.getChildren().size(); i++) {
                ImageView imageView = (ImageView) gallery.getChildren().get(i);
                imageView.setFitWidth(widthPerImage);
                imageView.setFitHeight(heightPerImage);
            }
        });

        // Créer la scène
        Scene scene = new Scene(root, 800, 600);
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

        // Ajouter les images au GridPane
        for (String imagePath : IMAGE_PATHS) {
            Image image = new Image(imagePath);
            ImageView thumbnail = new ImageView(image);

            // Ajuster les dimensions initiales
            thumbnail.setFitWidth(150); // Largeur initiale, ajustée dynamiquement
            thumbnail.setFitHeight(112.5); // Hauteur initiale pour un ratio 4:3
            thumbnail.setPreserveRatio(true);

            // Ajouter un événement de clic pour agrandir l'image
            thumbnail.setOnMouseClicked(event -> openImageInFullScreen(image));

            // Ajouter la vignette à la grille
            gallery.add(thumbnail, column, row);

            column++;
            if (column == 4) { // Passer à la ligne suivante après 4 images
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
