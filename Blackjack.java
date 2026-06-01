import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class Blackjack extends Application {

    @Override
    public void start(Stage stage) {

        Text welcome = new Text("Welcome to BlackJack");
        welcome.setFont(Font.font("Times New Roman", FontWeight.BOLD, 24));
        welcome.setFill(Color.BLACK);
        welcome.setStroke(Color.WHITE);
        welcome.setStrokeWidth(1);

        double cardWidth = 175;
        double cardHeight = 250;
        double margin = 10;

        /// Card rectangle
        Rectangle card = new Rectangle(cardWidth, cardHeight);
        card.setFill(Color.WHITE);
        card.setStroke(Color.BLACK);
        card.setArcHeight(10);
        card.setArcWidth(10);
        /// Top A
        Text topLetter = new Text("A");
        topLetter.setFont(Font.font("Arial", FontWeight.BOLD, 24));
        topLetter.setFill(Color.RED);
        topLetter.setStroke(Color.WHITE);
        topLetter.setStrokeWidth(1);
        topLetter.setTranslateX(-cardWidth / 2 + margin + 10);
        topLetter.setTranslateY(-cardHeight / 2 + margin + 10);
        /// Bottom A
        Text bottomLetter = new Text("A");
        bottomLetter.setFont(Font.font("Arial", FontWeight.BOLD, 24));
        bottomLetter.setFill(Color.RED);
        bottomLetter.setStroke(Color.WHITE);
        bottomLetter.setStrokeWidth(1);
        bottomLetter.setTranslateX(cardWidth / 2 - margin - 10);
        bottomLetter.setTranslateY(cardHeight / 2 - margin - 10);
        bottomLetter.setRotate(180);
        /// Group together
        StackPane aceCard = new StackPane(card, topLetter, bottomLetter);
        aceCard.setTranslateX(20);
        aceCard.setTranslateY(-20);
        aceCard.setRotate(10);

        ///
        Rectangle card2 = new Rectangle(cardWidth, cardHeight);
        card2.setFill(Color.WHITE);
        card2.setStroke(Color.BLACK);
        card2.setArcHeight(10);
        card2.setArcWidth(10);
        Text topLetter2 = new Text("K");
        topLetter2.setFont(Font.font("Arial", FontWeight.BOLD, 24));
        topLetter2.setFill(Color.BLACK);
        topLetter2.setStroke(Color.WHITE);
        topLetter2.setStrokeWidth(1);
        topLetter2.setTranslateX(-cardWidth / 2 + margin + 10);
        topLetter2.setTranslateY(-cardHeight / 2 + margin + 10);
        Text bottomLetter2 = new Text("K");
        bottomLetter2.setFont(Font.font("Arial", FontWeight.BOLD, 24));
        bottomLetter2.setFill(Color.BLACK);
        bottomLetter2.setStroke(Color.WHITE);
        bottomLetter2.setStrokeWidth(1);
        bottomLetter2.setRotate(180);
        bottomLetter2.setTranslateX(cardWidth / 2 - margin - 10);
        bottomLetter2.setTranslateY(cardHeight / 2 - margin - 10);

        StackPane kingCard = new StackPane(card2, topLetter2, bottomLetter2);
        kingCard.setTranslateX(-40);
        kingCard.setRotate(-10);

        bottomLetter.setRotate(180);
        StackPane finalLoading = new StackPane(aceCard, kingCard, welcome);
        Scene scene = new Scene(finalLoading, 640, 480);

        stage.setTitle("Blackjack");
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}