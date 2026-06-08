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

    private int balance = 50;
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
        Rectangle cardAce = new Rectangle(cardWidth, cardHeight);
        cardAce.setFill(Color.WHITE);
        cardAce.setStroke(Color.BLACK);
        cardAce.setArcHeight(10);
        cardAce.setArcWidth(10);
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
        StackPane aceCard = new StackPane(cardAce, topLetter, bottomLetter);
        aceCard.setTranslateX(20);
        aceCard.setTranslateY(-20);
        aceCard.setRotate(10);

        /// king card loading screen
        Rectangle cardKing = new Rectangle(cardWidth, cardHeight);
        cardKing.setFill(Color.WHITE);
        cardKing.setStroke(Color.BLACK);
        cardKing.setArcHeight(10);
        cardKing.setArcWidth(10);
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

        StackPane kingCard = new StackPane(cardKing, topLetter2, bottomLetter2);
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
            /// Place Your Bet text
            Text placeYourBet = new Text("Place your bet and confirm");
            placeYourBet.setStroke(Color.BLACK);
            placeYourBet.setStrokeWidth(3);
            placeYourBet.setFill(Color.WHITE);
            placeYourBet.setFont(Font.font("Arial", FontWeight.BOLD, 34));
            placeYourBet.setLayoutX(scene.getWidth()/2 - placeYourBet.getBoundsInLocal().getWidth()/2);
            placeYourBet.setLayoutY(scene.getHeight()/2);
            /// confirm bet button
            Rectangle confirmRect = new Rectangle(150, 55);
            confirmRect.setArcHeight(40);
            confirmRect.setArcWidth(40);
            confirmRect.setFill(Color.WHITESMOKE);
            confirmRect.setStroke(Color.BLACK);
            confirmRect.setStrokeWidth(3);
            Text confirmTxt = new Text("Confirm");
            confirmTxt.setFont(Font.font("Times New Roman", FontWeight.BOLD, 20));
            confirmTxt.setFill(Color.BLACK);
            Pane confirmBtn = new Pane();
            confirmTxt.setLayoutX(30);
            confirmTxt.setLayoutY(35);
            confirmBtn.getChildren().addAll(confirmRect, confirmTxt);
            /// directly above bet display
            confirmBtn.setLayoutX(245);
            confirmBtn.setLayoutY(370);
            
            /// Balance display panel
            Rectangle displayBalRect = new Rectangle (150, 50);
            displayBalRect.setArcHeight(40);
            displayBalRect.setArcWidth(40);
            displayBalRect.setStroke(Color.BLACK);
            displayBalRect.setStrokeWidth(3);
            displayBalRect.setFill(Color.WHITE);
            Text displayBalTxt = new Text("$" + balance);
            displayBalTxt.setFont(Font.font("Times New Roman", FontWeight.BOLD, 12));
            displayBalTxt.setTranslateX(10);
            displayBalTxt.setTranslateY(22);
            Pane displayBal = new Pane(displayBalRect, displayBalTxt);
            displayBal.setLayoutX(scene.getWidth()/2 - displayBalRect.getWidth()/2);
            displayBal.setLayoutY(scene.getHeight()/2 - 90);
            ///double bet button visual
            Rectangle times2Rect = new Rectangle(70, 55);
            times2Rect.setArcHeight(40);
            times2Rect.setArcWidth(40);
            times2Rect.setFill(Color.WHITE);
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
            times2Btn.setLayoutY(270);
            ///half bet button visual
            Rectangle divide2Rect = new Rectangle(70, 55);
            divide2Rect.setArcHeight(40);
            divide2Rect.setArcWidth(40);
            divide2Rect.setFill(Color.WHITE);
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
            divide2Btn.setLayoutY(270);
            ///pot display
            Rectangle displayPot = new Rectangle(150, 55);
            displayPot.setArcHeight(40);
            displayPot.setArcWidth(40);
            displayPot.setFill(Color.WHITE);
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
            displayPotA.setLayoutY(270);
            
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
            Pane gamePane = new Pane(placeYourBet, confirmBtn, times2Btn, 
                                     divide2Btn, displayPotA, displayBal);
            scene.setRoot(gamePane);
        
            confirmBtn.setOnMouseClicked( event -> {
            Pane blackjackPane = new Pane();
            double buttonWidth = 120;
            double buttonHeight = 50;
            double margin2 = 30;
            double  sceneBottomY = scene.getHeight() - buttonHeight - margin2;

            Deck deck = new Deck();

            String initialCard1 = deck.drawCard();
            String initialCard2 = deck.drawCard();

            /// draw initial cards with variables
            /// get the cards and separate value from suit
            int comma1 = initialCard1.indexOf(",");
            String value1 = initialCard1.substring(0, comma1);
            String suit1 = initialCard1.substring(comma1 + 2);
            int comma2 = initialCard2.indexOf(",");
            String value2 = initialCard2.substring(0, comma2);
            String suit2 = initialCard2.substring(comma2 + 2);
            /// suits to symbols on card visual
            String suitSymbol1 = "";
            if (suit1.equals("Hearts")) suitSymbol1 = "♥";
            else if (suit1.equals("Diamonds")) suitSymbol1 = "♦";
            else if (suit1.equals("Clubs")) suitSymbol1 = "♣";
            else if (suit1.equals("Spades")) suitSymbol1 = "♠";
            String suitSymbol2 = "";
            if (suit2.equals("Hearts")) suitSymbol2 = "♥";
            else if (suit2.equals("Diamonds")) suitSymbol2 = "♦";
            else if (suit2.equals("Clubs")) suitSymbol2 = "♣";
            else if (suit2.equals("Spades")) suitSymbol2 = "♠";
            /// first card
            Rectangle card1 = new Rectangle(100, 140);
            card1.setFill(Color.WHITE);
            card1.setStroke(Color.BLACK);
            card1.setArcWidth(10);
            card1.setArcHeight(10);
            Text card1Value = new Text(value1);
            card1Value.setFont(Font.font("Arial", FontWeight.BOLD, 24));
            card1Value.setTranslateX(-30);
            card1Value.setTranslateY(-45);
            Text card1Suit = new Text(suitSymbol1);
            card1Suit.setFont(Font.font("Arial", 30));
            StackPane playerCard1 = new StackPane(card1, card1Value, card1Suit);
            playerCard1.setLayoutX(250);
            playerCard1.setLayoutY(150);
            /// second card
            Rectangle card2 = new Rectangle(100, 140);
            card2.setFill(Color.WHITE);
            card2.setStroke(Color.BLACK);
            card2.setArcWidth(10);
            card2.setArcHeight(10);
            Text card2Value = new Text(value2);
            card2Value.setFont(Font.font("Arial", FontWeight.BOLD, 24));
            card2Value.setTranslateX(-30);
            card2Value.setTranslateY(-45);
            Text card2Suit = new Text(suitSymbol2);
            card2Suit.setFont(Font.font("Arial", 30));
            StackPane playerCard2 = new StackPane(card2, card2Value, card2Suit);
            playerCard2.setLayoutX(370);
            playerCard2.setLayoutY(150);
            /// Stand Button
            Rectangle standRect = new Rectangle(buttonWidth, buttonHeight);
            standRect.setArcWidth(20);
            standRect.setArcHeight(20);
            standRect.setFill(Color.WHITE);
            standRect.setStroke(Color.BLACK);
            standRect.setStrokeWidth(3);
            Text standTxt = new Text("Stand");
            standTxt.setFont(Font.font("Times New Roman", FontWeight.BOLD, 18));
            StackPane standBtn = new StackPane(standRect, standTxt);
            standBtn.setLayoutX(margin2);
            standBtn.setLayoutY(sceneBottomY);
            /// Hit Button
            Rectangle hitRect = new Rectangle(buttonWidth, buttonHeight);
            hitRect.setArcWidth(20);
            hitRect.setArcHeight(20);
            hitRect.setFill(Color.WHITE);
            hitRect.setStroke(Color.BLACK);
            hitRect.setStrokeWidth(3);
            Text hitTxt = new Text("Hit");
            hitTxt.setFont(Font.font("Times New Roman", FontWeight.BOLD, 18));
            StackPane hitBtn = new StackPane(hitRect, hitTxt);
            hitBtn.setLayoutX(margin2 + (buttonWidth + margin2));
            hitBtn.setLayoutY(sceneBottomY);
            /// Split Button
            Rectangle splitRect = new Rectangle(buttonWidth, buttonHeight);
            splitRect.setArcWidth(20);
            splitRect.setArcHeight(20);
            splitRect.setFill(Color.WHITE);
            splitRect.setStroke(Color.BLACK);
            splitRect.setStrokeWidth(3);
            Text splitTxt = new Text("Split");
            splitTxt.setFont(Font.font("Times New Roman", FontWeight.BOLD, 18));
            StackPane splitBtn = new StackPane(splitRect, splitTxt);
            splitBtn.setLayoutX(margin2 + 2 * (buttonWidth + margin2));
            splitBtn.setLayoutY(sceneBottomY);
            /// Double Button
            Rectangle doubleRect = new Rectangle(buttonWidth, buttonHeight);
            doubleRect.setArcWidth(20);
            doubleRect.setArcHeight(20);
            doubleRect.setFill(Color.WHITE);
            doubleRect.setStroke(Color.BLACK);
            doubleRect.setStrokeWidth(3);
            Text doubleTxt = new Text("Double");
            doubleTxt.setFont(Font.font("Times New Roman", FontWeight.BOLD, 18));
            StackPane doubleBtn = new StackPane(doubleRect, doubleTxt);
            doubleBtn.setLayoutX(margin2 + 3 * (buttonWidth + margin2));
            doubleBtn.setLayoutY(sceneBottomY);
            
            blackjackPane.getChildren().addAll(standBtn, hitBtn, card1, 
                                               card2, splitBtn, doubleBtn);
            scene.setRoot(blackjackPane);
            standBtn.setOnMouseClicked(standClicked -> {
                
            });
        });

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
            /// draw card from deck
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

