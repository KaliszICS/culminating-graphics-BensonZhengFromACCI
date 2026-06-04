import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import java.util.Queue;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Collections;
import java.util.Random;

public class Blackjack extends Application {

    private int balance = 100;
    private int betAmount = 1;

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
        bottomLetter2.setRotate(180);

        Rectangle startRect = new Rectangle(200, 55);
        startRect.setArcHeight(55);
        startRect.setArcWidth(40);
        startRect.setFill(Color.GREEN);
        startRect.setStroke(Color.BLACK);
        startRect.setStrokeWidth(3);
        Text startTxt = new Text("Start Game");
        startTxt.setFont(Font.font("Times New Roman", FontWeight.BOLD, 24));
        startTxt.setFill(Color.BLACK);
        StackPane startBtn = new StackPane(startRect, startTxt);
        startBtn.setTranslateY(140);
        StackPane currentScene = new StackPane(aceCard, kingCard, welcome, startBtn);
        Scene scene = new Scene(currentScene, 640, 480);

        stage.setTitle("Blackjack");
        stage.setScene(scene);
        stage.show();

        startBtn.setOnMouseClicked(startClicked -> {
            ///double bet button visual
            Rectangle times2Rect = new Rectangle(70, 55);
            times2Rect.setArcHeight(40);
            times2Rect.setArcWidth(40);
            times2Rect.setFill(Color.GREEN);
            times2Rect.setStroke(Color.BLACK);
            times2Rect.setStrokeWidth(3);
            Text times2Txt = new Text("2x");
            times2Txt.setFont(Font.font("Times New Roman", FontWeight.BOLD, 24));
            times2Txt.setFill(Color.BLACK);
            Pane times2Btn = new Pane();
            /// edits position on button pane
            times2Txt.setLayoutX(22);
            times2Txt.setLayoutY(35);
            times2Btn.getChildren().addAll(times2Rect, times2Txt);
            /// edits position on display pane
            times2Btn.setLayoutX(450);
            times2Btn.setLayoutY(350);
            ///half bet button visual
            Rectangle divide2Rect = new Rectangle(70, 55);
            divide2Rect.setArcHeight(40);
            divide2Rect.setArcWidth(40);
            divide2Rect.setFill(Color.GREEN);
            divide2Rect.setStroke(Color.BLACK);
            divide2Rect.setStrokeWidth(3);
            Text divide2Txt = new Text("1/2");
            divide2Txt.setFont(Font.font("Times New Roman", FontWeight.BOLD, 24));
            divide2Txt.setFill(Color.BLACK);
            Pane divide2Btn = new Pane();
            /// edits position on button pane
            divide2Txt.setLayoutX(15);
            divide2Txt.setLayoutY(35);
            divide2Btn.getChildren().addAll(divide2Rect, divide2Txt);
            /// edits position on entire screen
            divide2Btn.setLayoutX(120);
            divide2Btn.setLayoutY(350);
            ///pot display
            Rectangle displayPot = new Rectangle(150, 55);
            displayPot.setArcHeight(40);
            displayPot.setArcWidth(40);
            displayPot.setFill(Color.GREEN);
            displayPot.setStroke(Color.BLACK);
            displayPot.setStrokeWidth(3);
            Text displayPotTxt = new Text("$" + betAmount + ".0");
            displayPotTxt.setFont(Font.font("Times New Roman", FontWeight.BOLD, 24));
            displayPotTxt.setFill(Color.BLACK);
            Pane displayPotA = new Pane();
            /// edits position on display pane
            displayPotTxt.setLayoutX(65);
            displayPotTxt.setLayoutY(35);
            displayPotA.getChildren().addAll(displayPot, displayPotTxt);
            /// edits position on entire screen
            displayPotA.setLayoutX(245);
            displayPotA.setLayoutY(350);
            
            ///pot display and half/double button code
            /// checks if value after is valid
            divide2Btn.setOnMouseClicked(event -> {
                if (betAmount/2 >= 1) {
                    betAmount = betAmount / 2;
                    displayPotTxt.setText(String.valueOf("$" + betAmount + ".0"));
                    System.out.println("23230");
                }
            });
            times2Btn.setOnMouseClicked(event -> {
                /// checks if value after is valid
                if (betAmount*2 <= balance) {
                    betAmount = betAmount*2;
                    displayPotTxt.setText(String.valueOf("$" + betAmount + ".0"));
                    System.out.println("1000");
                }
            });
            Pane gamePane = new Pane(times2Btn, divide2Btn, displayPotA);
            scene.setRoot(gamePane);

            
        });
    }
    public class Deck {
        private Queue<String> deck = new LinkedList<>();
            public Deck() {
                buildDeck();
                shuffleDeck();
            }
            /// creates deck of 52 cards
            private void buildDeck() {
                /// place value and suits into array
                String[] value = {"A","2","3","4","5","6","7","8","9","10","J","Q","K"};
                String[] suits = {"Hearts","Diamonds","Clubs","Spades"};
                /// creates each card by taking each value and making one of each suit
                /// adds into queue for shuffling later
                for (int start = 0; start < value.length; start++) {
                    for (int start2 = 0; start2 < suits.length; start2++) {
                        deck.add(value[start] + ", " + suits[start2]);
                    }
                }
            }
            /// shuffle deck
            public void shuffleDeck() {
                List<String> shuffled = new ArrayList<>(deck);
                Collections.shuffle(shuffled);
                ///clears old organised deck adds temporary shuffled deck
                deck.clear();
                deck.addAll(shuffled);
            }
            /// draw card
            public String drawCard() {
                if (deck.isEmpty()) {
                    return "Deck is empty";
                }
                /// removes top most card
                return deck.remove();
            }
            /// remaining cards
            public int cardsLeft() {
                /// *(!@U)(*!*(@*!*(!@ make display for this later ********!*!*@!_(@(*@**(!@)!@*)!@*!@)(!@*)!@*!
                return deck.size();
            }
        }

    public static void main(String[] args) {
        launch(args);
    }

}

