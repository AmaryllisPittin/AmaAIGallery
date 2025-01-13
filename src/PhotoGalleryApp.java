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

        BorderPane root = new BorderPane();
        root.setStyle("-fx-background-color: #2E2E2E;");

        // Créer la galerie avec la méthode mise à jour
        GridPane gallery = createGallery();
        root.setCenter(gallery);

        // Créer la scène
        Scene scene = new Scene(root, 700, 600);
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    private GridPane createGallery() {
        GridPane gallery = new GridPane();
        gallery.setPadding(new Insets(20));
        gallery.setHgap(10);
        gallery.setVgap(10);
        gallery.setAlignment(Pos.CENTER);

        int column = 0;
        int row = 0;

        // Déterminer la taille des images en fonction de la taille de la fenêtre
        double imageWidth = 250; // Largeur initiale pour l'image
        double imageHeight = 200; // Hauteur initiale pour l'image

        // Ajouter les images dans la grille
        for (String imagePath : IMAGE_PATHS) {
            // Charger l'image
            Image image = new Image(imagePath);
            ImageView thumbnail = new ImageView(image);

            // Ajuster la taille de l'image en fonction de la fenêtre
            thumbnail.setFitWidth(imageWidth);
            thumbnail.setFitHeight(imageHeight);
            thumbnail.setPreserveRatio(true);

            // Ajouter un événement de clic pour agrandir l'image
            thumbnail.setOnMouseClicked(event -> openImageInFullScreen(image));

            // Ajouter l'image à la grille
            gallery.add(thumbnail, column, row);

            column++;
            if (column == 4) { // Changer de ligne après 4 images
                column = 0;
                row++;
            }
        }

        // Mettre à jour la taille des images en fonction de la taille de la fenêtre
        gallery.widthProperty().addListener((obs, oldVal, newVal) -> {
            // Calculer la taille des vignettes en fonction de la largeur de la fenêtre
            double widthPerImage = (newVal.doubleValue() - 30) / 4; // 30px pour les espacements
            double heightPerImage = widthPerImage * 2 / 3; // Conserver un ratio

            // Appliquer la nouvelle taille à chaque image
            for (int i = 0; i < gallery.getChildren().size(); i++) {
                ImageView imageView = (ImageView) gallery.getChildren().get(i);
                imageView.setFitWidth(widthPerImage);
                imageView.setFitHeight(heightPerImage);
            }
        });

        return gallery;
    }

    private void openImageInFullScreen(Image image) {

        Stage fullscreenStage = new Stage();
        fullscreenStage.setTitle("Image Agrandie");

        ImageView imageView = new ImageView(image);
        imageView.setPreserveRatio(true);
        imageView.setFitWidth(800);
        imageView.setFitHeight(600);

        StackPane root = new StackPane(imageView);
        root.setStyle("-fx-background-color: white;");

        Scene scene = new Scene(root, 800, 600);
        fullscreenStage.setScene(scene);
        fullscreenStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
