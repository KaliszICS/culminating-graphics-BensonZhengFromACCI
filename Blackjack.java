/**

        * File: Culminating activity: Blackjack

        * Author: Benson Zheng

        * Date Created: March 27, 2026

        * Date Last Modified: June 10, 2026

        */ 
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
import java.util.LinkedList;
import java.util.List;
import java.util.Collections;

public class Blackjack extends Application {
    private String value = "";
    private int dealerHandValue = 0;
    private int playerHandValue = 0;
    private int balance = 50;
    private int betAmount = 1;
    private boolean gameOver = false;
    private int highScore = 0;

    private Scene sceneRef;
    private Pane betPaneRef;
    private Text displayPotTxtRef;
    private Text displayBalTxtRef;

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

        Rectangle cardAce = new Rectangle(cardWidth, cardHeight);
        cardAce.setFill(Color.WHITE);
        cardAce.setStroke(Color.BLACK);
        cardAce.setArcHeight(10);
        cardAce.setArcWidth(10);

        Text topLetter = new Text("A");
        topLetter.setFont(Font.font("Arial", FontWeight.BOLD, 24));
        topLetter.setFill(Color.RED);
        topLetter.setStroke(Color.WHITE);
        topLetter.setStrokeWidth(1);
        topLetter.setTranslateX(-cardWidth / 2 + margin + 10);
        topLetter.setTranslateY(-cardHeight / 2 + margin + 10);

        Text bottomLetter = new Text("A");
        bottomLetter.setFont(Font.font("Arial", FontWeight.BOLD, 24));
        bottomLetter.setFill(Color.RED);
        bottomLetter.setStroke(Color.WHITE);
        bottomLetter.setStrokeWidth(1);
        bottomLetter.setTranslateX(cardWidth / 2 - margin - 10);
        bottomLetter.setTranslateY(cardHeight / 2 - margin - 10);
        bottomLetter.setRotate(180);

        StackPane aceCard = new StackPane(cardAce, topLetter, bottomLetter);
        aceCard.setTranslateX(20);
        aceCard.setTranslateY(-20);
        aceCard.setRotate(10);

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
        sceneRef = scene;

        stage.setTitle("Blackjack");
        stage.setScene(scene);
        stage.show();

        startBtn.setOnMouseClicked(startClicked -> {
            /// after start clicked
            Text placeYourBet = new Text("Place your bet and confirm");
            placeYourBet.setStroke(Color.BLACK);
            placeYourBet.setStrokeWidth(3);
            placeYourBet.setFill(Color.WHITE);
            placeYourBet.setFont(Font.font("Arial", FontWeight.BOLD, 34));
            placeYourBet.setLayoutX(scene.getWidth() / 2 - placeYourBet.getBoundsInLocal().getWidth() / 2);
            placeYourBet.setLayoutY(scene.getHeight() / 2);
            /// draws confirm button
            Rectangle confirmRect = new Rectangle(150, 55);
            confirmRect.setArcHeight(40);
            confirmRect.setArcWidth(40);
            confirmRect.setFill(Color.WHITESMOKE);
            confirmRect.setStroke(Color.BLACK);
            confirmRect.setStrokeWidth(3);
            /// confirm text
            Text confirmTxt = new Text("Confirm");
            confirmTxt.setFont(Font.font("Times New Roman", FontWeight.BOLD, 20));
            confirmTxt.setFill(Color.BLACK);
            /// adds rectangle with text
            Pane confirmBtn = new Pane();
            confirmTxt.setLayoutX(30);
            confirmTxt.setLayoutY(35);
            confirmBtn.getChildren().addAll(confirmRect, confirmTxt);
            confirmBtn.setLayoutX(245);
            confirmBtn.setLayoutY(370);
            /// display balance box
            Rectangle displayBalRect = new Rectangle(150, 50);
            displayBalRect.setArcHeight(40);
            displayBalRect.setArcWidth(40);
            displayBalRect.setStroke(Color.BLACK);
            displayBalRect.setStrokeWidth(3);
            displayBalRect.setFill(Color.WHITE);
            /// text for balance
            Text displayBalTxt = new Text("$" + balance + ".0");
            displayBalTxt.setFont(Font.font("Times New Roman", FontWeight.BOLD, 20));
            displayBalTxt.setLayoutX((displayBalRect.getWidth() / 2) - (displayBalTxt.getLayoutBounds().getWidth() / 2));
            displayBalTxt.setLayoutY((displayBalRect.getHeight() / 2) + (displayBalTxt.getLayoutBounds().getHeight() / 4));
            /// adds box with text
            Pane displayBal = new Pane(displayBalRect, displayBalTxt);
            displayBal.setLayoutX(scene.getWidth() / 2 - displayBalRect.getWidth() / 2);
            displayBal.setLayoutY(scene.getHeight() / 2 - 90);
            /// times 2 bet box
            Rectangle times2Rect = new Rectangle(70, 55);
            times2Rect.setArcHeight(40);
            times2Rect.setArcWidth(40);
            times2Rect.setFill(Color.WHITE);
            times2Rect.setStroke(Color.BLACK);
            times2Rect.setStrokeWidth(3);
            /// times 2 bet text
            Text times2Txt = new Text("2x");
            times2Txt.setFont(Font.font("Times New Roman", FontWeight.BOLD, 24));
            times2Txt.setFill(Color.BLACK);
            /// adds together
            Pane times2Btn = new Pane();
            times2Txt.setLayoutX(22);
            times2Txt.setLayoutY(35);
            times2Btn.getChildren().addAll(times2Rect, times2Txt);
            times2Btn.setLayoutX(450);
            times2Btn.setLayoutY(270);
            /// same as multiply
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
            divide2Txt.setLayoutX(15);
            divide2Txt.setLayoutY(35);
            divide2Btn.getChildren().addAll(divide2Rect, divide2Txt);
            divide2Btn.setLayoutX(120);
            divide2Btn.setLayoutY(270);

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
            displayPotTxt.setLayoutX(displayPot.getWidth()/2 - displayBalTxt.getLayoutBounds().getWidth()/2);
            displayPotTxt.setLayoutY(35);
            displayPotA.getChildren().addAll(displayPot, displayPotTxt);
            displayPotA.setLayoutX(245);
            displayPotA.setLayoutY(270);

            /// balance hits 0
            Text gameOverTxt = new Text("GAME OVER");
            gameOverTxt.setFont(Font.font("Arial", FontWeight.BOLD, 48));
            gameOverTxt.setFill(Color.RED);
            gameOverTxt.setStroke(Color.DARKRED);
            gameOverTxt.setStrokeWidth(4);

            Text finalScoreTxt = new Text("Best Balance: $" + highScore + ".0");
            finalScoreTxt.setFont(Font.font("Arial", FontWeight.BOLD, 24));
            finalScoreTxt.setFill(Color.GOLDENROD);

            Rectangle restartRect = new Rectangle(200, 55);
            restartRect.setArcHeight(40);
            restartRect.setArcWidth(40);
            restartRect.setFill(Color.WHITE);
            restartRect.setStroke(Color.BLACK);
            restartRect.setStrokeWidth(3);

            Text restartTxt = new Text("Play Again");
            restartTxt.setFont(Font.font("Times New Roman", FontWeight.BOLD, 22));
            restartTxt.setFill(Color.BLACK);

            StackPane restartBtn = new StackPane(restartRect, restartTxt);

            Pane gameOverPane = new Pane();

            // Position elements after pane is known size
            gameOverTxt.setLayoutY(160);
            finalScoreTxt.setLayoutY(220);
            restartBtn.setLayoutX(220);
            restartBtn.setLayoutY(280);

            gameOverPane.getChildren().addAll(gameOverTxt, finalScoreTxt, restartBtn);

            // Center text
            gameOverTxt.setLayoutX((scene.getWidth() - gameOverTxt.getLayoutBounds().getWidth())/2);
            finalScoreTxt.setLayoutX((scene.getWidth() - finalScoreTxt.getLayoutBounds().getWidth())/2);

            restartBtn.setOnMouseClicked(e -> {
                balance = 50;
                betAmount = 1;
                playerHandValue = 0;
                dealerHandValue = 0;
                
                gameOver = false;

                displayPotTxtRef.setText("$" + betAmount + ".0");
                displayBalTxtRef.setText("$" + balance + ".0");

                sceneRef.setRoot(betPaneRef);
            });
            /// you lose screen

            Text bustTxt = new Text("You lost this hand!");
            bustTxt.setFont(Font.font("Arial", FontWeight.BOLD, 24));
            bustTxt.setFill(Color.RED);

            Text winTxt = new Text("You won this hand!");
            winTxt.setFont(Font.font("Arial", FontWeight.BOLD, 24));
            winTxt.setFill(Color.GREEN);

            Text pushTxt = new Text("Equal value hands, push!");
            pushTxt.setFont(Font.font("Arial", FontWeight.BOLD, 24));
            pushTxt.setFill(Color.GRAY);

            Rectangle retryRect = new Rectangle(150, 55);
            retryRect.setArcHeight(40);
            retryRect.setArcWidth(40);
            retryRect.setFill(Color.WHITE);
            retryRect.setStroke(Color.BLACK);

            Text retryTxt = new Text("Retry");
            retryTxt.setFont(Font.font("Arial", FontWeight.BOLD, 20));

            StackPane retryBtn = new StackPane(retryRect, retryTxt);
            retryBtn.setLayoutX(245);
            retryBtn.setLayoutY(300);

            bustTxt.setLayoutX((scene.getWidth() - bustTxt.getLayoutBounds().getWidth()) / 2);
            bustTxt.setLayoutY(120);
            winTxt.setLayoutX((scene.getWidth() - winTxt.getLayoutBounds().getWidth()) / 2);
            winTxt.setLayoutY(120);
            pushTxt.setLayoutX((scene.getWidth() - pushTxt.getLayoutBounds().getWidth()) / 2);
            pushTxt.setLayoutY(120);

            Pane roundEndPhase = new Pane(retryBtn, bustTxt, winTxt, pushTxt);

            retryBtn.setOnMouseClicked(e -> {
                playerHandValue = 0;
                dealerHandValue = 0;
                gameOver = false;

                displayPotTxtRef.setText("$" + betAmount + ".0");
                displayBalTxtRef.setText("$" + balance + ".0");

                sceneRef.setRoot(betPaneRef);
            });

            divide2Btn.setOnMouseClicked(halfpot -> {
                if (betAmount / 2 >= 1) {
                    betAmount = betAmount / 2;
                }
                else {
                    betAmount = 1;
                }
                displayPotTxt.setText("$" + betAmount + ".0");
            });

            times2Btn.setOnMouseClicked(doublepot -> {
                if (betAmount * 2 <= balance) {
                    betAmount = betAmount * 2;
                }
                else {
                    betAmount = balance;
                }
                displayPotTxt.setText("$" + betAmount + ".0");
            });

            Pane gamePane = new Pane(placeYourBet, confirmBtn, times2Btn, divide2Btn, displayPotA, displayBal);

            betPaneRef = gamePane;
            displayPotTxtRef = displayPotTxt;
            displayBalTxtRef = displayBalTxt;
            scene.setRoot(gamePane);

            confirmBtn.setOnMouseClicked(event -> {
                roundEndPhase.setVisible(false);
                dealerHandValue = 0;
                playerHandValue = 0;
                gameOver = false;

                Pane blackjackPane = new Pane();

                double buttonWidth = 120;
                double buttonHeight = 50;
                double margin2 = 30;
                double sceneBottomY = scene.getHeight() - buttonHeight - margin2;

                Deck deck = new Deck();

                String initialCard1 = deck.drawCard();
                String initialCard2 = deck.drawCard();
                String initialCard3 = deck.drawCard();
                String initialCard4 = deck.drawCard();

                String[] cards = { initialCard1, initialCard2, initialCard3, initialCard4 };

                StackPane[] playerCards = new StackPane[10];
                StackPane[] dealerCards = new StackPane[10];

                double card2Width = 100;
                double gap = 20;
                double startX = 90;

                for (int start = 0; start < 4; start++) {
                    int comma = cards[start].indexOf(",");
                    value = cards[start].substring(0, comma);
                    String suit = cards[start].substring(comma + 2);

                    String suitSymbol = "";
                    if (suit.equals("Hearts")) suitSymbol = "♥";
                    else if (suit.equals("Diamonds")) suitSymbol = "♦";
                    else if (suit.equals("Clubs")) suitSymbol = "♣";
                    else if (suit.equals("Spades")) suitSymbol = "♠";

                    Rectangle dealerCardRect = new Rectangle(100, 140);
                    dealerCardRect.setFill(Color.WHITE);
                    dealerCardRect.setStroke(Color.BLACK);
                    dealerCardRect.setArcWidth(10);
                    dealerCardRect.setArcHeight(10);

                    Text dealerCardValue = new Text(value);
                    dealerCardValue.setFont(Font.font("Arial", FontWeight.BOLD, 24));
                    dealerCardValue.setTranslateX(-30);
                    dealerCardValue.setTranslateY(-45);

                    Text dealerCardSuit = new Text(suitSymbol);
                    dealerCardSuit.setFont(Font.font("Arial", 30));

                    StackPane dealerCard = new StackPane(dealerCardRect, dealerCardValue, dealerCardSuit);
                    dealerCard.setLayoutX(startX + start * (card2Width + gap));
                    dealerCard.setLayoutY(100);
                    dealerCards[start] = dealerCard;

                    Rectangle card = new Rectangle(100, 140);
                    card.setFill(Color.WHITE);
                    card.setStroke(Color.BLACK);
                    card.setArcWidth(10);
                    card.setArcHeight(10);

                    Text cardValue = new Text(value);
                    cardValue.setFont(Font.font("Arial", FontWeight.BOLD, 24));
                    cardValue.setTranslateX(-30);
                    cardValue.setTranslateY(-45);

                    Text cardSuit = new Text(suitSymbol);
                    cardSuit.setFont(Font.font("Arial", 30));

                    StackPane playerCard = new StackPane(card, cardValue, cardSuit);
                    playerCard.setLayoutX(startX + start * (card2Width + gap));
                    playerCard.setLayoutY(250);
                    playerCards[start] = playerCard;
                }

                Rectangle standRect = new Rectangle(buttonWidth, buttonHeight);
                standRect.setArcWidth(20);
                standRect.setArcHeight(20);
                standRect.setFill(Color.WHITE);
                standRect.setStroke(Color.BLACK);

                Text standTxt = new Text("Stand");
                standTxt.setFont(Font.font("Times New Roman", FontWeight.BOLD, 18));

                StackPane standBtn = new StackPane(standRect, standTxt);
                standBtn.setLayoutX(margin2);
                standBtn.setLayoutY(sceneBottomY);

                Rectangle hitRect = new Rectangle(buttonWidth, buttonHeight);
                hitRect.setArcWidth(20);
                hitRect.setArcHeight(20);
                hitRect.setFill(Color.WHITE);
                hitRect.setStroke(Color.BLACK);

                Text hitTxt = new Text("Hit");
                hitTxt.setFont(Font.font("Times New Roman", FontWeight.BOLD, 18));

                StackPane hitBtn = new StackPane(hitRect, hitTxt);
                hitBtn.setLayoutX(margin2 + (buttonWidth + margin2));
                hitBtn.setLayoutY(sceneBottomY);

                Rectangle doubleRect = new Rectangle(buttonWidth, buttonHeight);
                doubleRect.setArcWidth(20);
                doubleRect.setArcHeight(20);
                doubleRect.setFill(Color.WHITE);
                doubleRect.setStroke(Color.BLACK);

                Text doubleTxt = new Text("Double");
                doubleTxt.setFont(Font.font("Times New Roman", FontWeight.BOLD, 18));

                StackPane doubleBtn = new StackPane(doubleRect, doubleTxt);
                doubleBtn.setLayoutX(margin2 + 2 * (buttonWidth + margin2));
                doubleBtn.setLayoutY(sceneBottomY);

                Rectangle backRect = new Rectangle(100, 140);
                backRect.setFill(Color.RED);
                backRect.setStroke(Color.BLACK);
                backRect.setArcHeight(10);
                backRect.setArcWidth(10);

                Text backText = new Text("🂠");
                backText.setFont(Font.font("Arial", FontWeight.BOLD, 120));
                backText.setFill(Color.WHITE);
            
                StackPane dealerBackCard = new StackPane(backRect, backText);
                dealerBackCard.setLayoutX(440);
                dealerBackCard.setLayoutY(100);

                blackjackPane.getChildren().addAll(
                        playerCards[0], playerCards[1],
                        dealerCards[2], dealerCards[3],
                        standBtn, hitBtn, doubleBtn, 
                        roundEndPhase, dealerBackCard);
                scene.setRoot(blackjackPane);

                playerHandValue += getCardValue(initialCard1, true);
                playerHandValue += getCardValue(initialCard2, true);
                dealerHandValue += getCardValue(initialCard3, false);
                dealerHandValue += getCardValue(initialCard4, false);

                int[] counter = {0};
                int[] dealerCounter = {0};

                standBtn.setOnMouseClicked(stand -> {
                    dealerBackCard.setVisible(false);
                    while (dealerHandValue <= 16) {
                        String newCard = deck.drawCard();
                        dealerHandValue += getCardValue(newCard, false);

                        int comma = newCard.indexOf(",");
                        String val = newCard.substring(0, comma);
                        String suit = newCard.substring(comma + 2);

                        String suitSymbol = "";
                        if (suit.equals("Hearts")) suitSymbol = "♥";
                        else if (suit.equals("Diamonds")) suitSymbol = "♦";
                        else if (suit.equals("Clubs")) suitSymbol = "♣";
                        else if (suit.equals("Spades")) suitSymbol = "♠";

                        Rectangle cardRect = new Rectangle(100, 140);
                        cardRect.setFill(Color.WHITE);
                        cardRect.setStroke(Color.BLACK);
                        cardRect.setArcWidth(10);
                        cardRect.setArcHeight(10);

                        Text valueText = new Text(val);
                        valueText.setFont(Font.font("Arial", FontWeight.BOLD, 24));
                        valueText.setTranslateX(-30);
                        valueText.setTranslateY(-45);

                        Text suitText = new Text(suitSymbol);
                        suitText.setFont(Font.font("Arial", 30));

                        StackPane dealerCard = new StackPane(cardRect, valueText, suitText);
                        dealerCard.setLayoutX(210 - dealerCounter[0] * 120);
                        dealerCounter[0]++;
                        dealerCard.setLayoutY(100);
                        blackjackPane.getChildren().add(dealerCard);
                    }

                    standBtn.setVisible(false);
                    hitBtn.setVisible(false);
                    doubleBtn.setVisible(false);

                    roundEndPhase.setVisible(true);
                    roundEndPhase.toFront();

                    if (dealerHandValue > 21 || playerHandValue > dealerHandValue) {
                        winTxt.setVisible(true);
                        bustTxt.setVisible(false);
                        pushTxt.setVisible(false);
                        balance = balance + betAmount;
                        if (balance > highScore) highScore = balance;
                    } else if (dealerHandValue > playerHandValue) {
                        bustTxt.setVisible(true);
                        winTxt.setVisible(false);
                        pushTxt.setVisible(false);
                        balance = balance - betAmount;

                        // Check game over after stand loss
                        if (balance <= 0) {
                            balance = 0;
                            finalScoreTxt.setText("Best Balance: $" + highScore + ".0");
                            scene.setRoot(gameOverPane);
                        }
                    } else {
                        pushTxt.setVisible(true);
                        bustTxt.setVisible(false);
                        winTxt.setVisible(false);
                    }
                });

                hitBtn.setOnMouseClicked(hit -> {
                    counter[0]++;

                    String newCard = deck.drawCard();
                    playerHandValue += getCardValue(newCard, true);

                    int comma = newCard.indexOf(",");
                    String val = newCard.substring(0, comma);
                    String suit = newCard.substring(comma + 2);

                    String suitSymbol = "";
                    if (suit.equals("Hearts")) suitSymbol = "♥";
                    else if (suit.equals("Diamonds")) suitSymbol = "♦";
                    else if (suit.equals("Clubs")) suitSymbol = "♣";
                    else if (suit.equals("Spades")) suitSymbol = "♠";

                    Rectangle cardRect = new Rectangle(100, 140);
                    cardRect.setFill(Color.WHITE);
                    cardRect.setStroke(Color.BLACK);
                    cardRect.setArcWidth(10);
                    cardRect.setArcHeight(10);

                    Text valueText = new Text(val);
                    valueText.setFont(Font.font("Arial", FontWeight.BOLD, 24));
                    valueText.setTranslateX(-30);
                    valueText.setTranslateY(-45);

                    Text suitText = new Text(suitSymbol);
                    suitText.setFont(Font.font("Arial", 30));

                    StackPane hitCard = new StackPane(cardRect, valueText, suitText);
                    hitCard.setLayoutY(250);
                    hitCard.setLayoutX(startX + (1 + counter[0]) * (card2Width + gap));
                    blackjackPane.getChildren().add(hitCard);
                    roundEndPhase.toFront();

                    if (playerHandValue > 21) {
                        gameOver = true;
                        balance = balance - betAmount;

                        standBtn.setVisible(false);
                        hitBtn.setVisible(false);
                        doubleBtn.setVisible(false);
                        dealerBackCard.setVisible(false);

                        // Check game over after hit bust
                        if (balance <= 0) {
                            balance = 0;
                            finalScoreTxt.setText("Best Balance: $" + highScore + ".0");
                            scene.setRoot(gameOverPane);
                        } else {
                            roundEndPhase.setVisible(true);
                            bustTxt.setVisible(true);
                            winTxt.setVisible(false);
                            pushTxt.setVisible(false);
                            dealerBackCard.setVisible(false);
                        }
                    }
                });
                doubleBtn.setOnMouseClicked(doubleStand -> {
                dealerBackCard.setVisible(false);
                /// draw 1 more and stand double current pot
                if (betAmount * 2 > balance) {
                    return;
                }
                else {
                String newCard = deck.drawCard();
                playerHandValue += getCardValue(newCard, true);

                int comma = newCard.indexOf(",");
                String val = newCard.substring(0, comma);
                String suit = newCard.substring(comma + 2);

                String suitSymbol = "";
                if (suit.equals("Hearts")) suitSymbol = "♥";
                else if (suit.equals("Diamonds")) suitSymbol = "♦";
                else if (suit.equals("Clubs")) suitSymbol = "♣";
                else if (suit.equals("Spades")) suitSymbol = "♠";

                Rectangle cardRect = new Rectangle(100, 140);
                cardRect.setFill(Color.WHITE);
                cardRect.setStroke(Color.BLACK);
                cardRect.setArcWidth(10);
                cardRect.setArcHeight(10);

                Text valueText = new Text(val);
                valueText.setFont(Font.font("Arial", FontWeight.BOLD, 24));

                Text suitText = new Text(suitSymbol);
                suitText.setFont(Font.font("Arial", 30));

                StackPane playerCard = new StackPane(cardRect, valueText, suitText);
                playerCard.setLayoutY(250);
                counter[0]++;
                playerCard.setLayoutX(startX + (1 + counter[0]) * (card2Width + gap));

                blackjackPane.getChildren().add(playerCard);

                while (dealerHandValue <= 16) {
                    String dealerCard = deck.drawCard();
                    dealerHandValue += getCardValue(dealerCard, false);

                    int c = dealerCard.indexOf(",");
                    String v = dealerCard.substring(0, c);
                    String s = dealerCard.substring(c + 2);

                    String symbol = "";
                    if (s.equals("Hearts")) symbol = "♥";
                    else if (s.equals("Diamonds")) symbol = "♦";
                    else if (s.equals("Clubs")) symbol = "♣";
                    else if (s.equals("Spades")) symbol = "♠";

                    Rectangle cardRect2 = new Rectangle(100, 140);
                    cardRect2.setFill(Color.WHITE);
                    cardRect2.setStroke(Color.BLACK);
                    cardRect2.setArcWidth(10);
                    cardRect2.setArcHeight(10);

                    Text valueText2 = new Text(v);
                    valueText2.setFont(Font.font("Arial", FontWeight.BOLD, 24));

                    Text suitText2 = new Text(symbol);
                    suitText2.setFont(Font.font("Arial", 30));

                    StackPane dealerCardPane = new StackPane(cardRect2, valueText2, suitText2);
                    dealerCardPane.setLayoutX(210 - dealerCounter[0] * 120);
                    dealerCounter[0]++;
                    dealerCardPane.setLayoutY(100);

                    blackjackPane.getChildren().add(dealerCardPane);
                }

                standBtn.setVisible(false);
                hitBtn.setVisible(false);
                doubleBtn.setVisible(false);

                roundEndPhase.setVisible(true);
                roundEndPhase.toFront();

                if (dealerHandValue > 21 || playerHandValue > dealerHandValue) {
                    winTxt.setVisible(true);
                    bustTxt.setVisible(false);
                    pushTxt.setVisible(false);

                    balance += betAmount*2;
                    if (balance > highScore) highScore = balance;

                } else if (dealerHandValue > playerHandValue) {
                    bustTxt.setVisible(true);
                    winTxt.setVisible(false);
                    pushTxt.setVisible(false);

                    balance -= betAmount;

                    if (balance <= 0) {
                        balance = 0;
                        finalScoreTxt.setText("Best Balance: $" + highScore + ".0");
                        scene.setRoot(gameOverPane);
                    }

                } else {
                    pushTxt.setVisible(true);
                    bustTxt.setVisible(false);
                    winTxt.setVisible(false);
                }
            }
        });
            });
        });
    }

    private int getCardValue(String card, boolean isPlayer) {
        int comma = card.indexOf(",");
        String value = card.substring(0, comma).trim();

        if (value.equals("J") || value.equals("Q") || value.equals("K")) {
            return 10;
        }

        if (value.equals("A")) {
            if (isPlayer) {
                if (playerHandValue + 11 > 21) {
                    return 1;
                } else {
                    return 11;
                }
            } else {
                if (dealerHandValue + 11 > 21) {
                    return 1;
                } else {
                    return 11;
                }
            }
        }

        return Integer.parseInt(value);
    }
    public class Deck {
        private Queue<String> deck = new LinkedList<>();

        public Deck() {
            buildDeck();
            shuffleDeck();
        }

        private void buildDeck() {
            String[] value = { "A", "2", "3", "4", "5", "6", "7", "8", "9", "10", "J", "Q", "K" };
            String[] suits = { "Hearts", "Diamonds", "Clubs", "Spades" };

            for (int start = 0; start < value.length; start++) {
                for (int start2 = 0; start2 < suits.length; start2++) {
                    deck.add(value[start] + ", " + suits[start2]);
                }
            }
        }

        public void shuffleDeck() {
            List<String> shuffled = new ArrayList<>(deck);
            Collections.shuffle(shuffled);
            deck.clear();
            deck.addAll(shuffled);
        }

        public String drawCard() {
            if (deck.isEmpty()) return "Deck is empty";
            return deck.remove();
        }

        public int cardsLeft() {
            return deck.size();
        }
    }

    public static void main(String[] args) {
        launch(args);
    }
}