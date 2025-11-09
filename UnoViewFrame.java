import javax.swing.*;
import javax.swing.JOptionPane;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class UnoViewFrame extends JFrame implements UnoView {


    private JPanel cardPanel; // panel for current player's cards
    private JPanel decksPanel; // panel for the discard pile, new card pile, and "UNO!" button
    private JButton drawPile;
    public JPanel scorePanel; // panel for the player's score

    private JButton unoButton;
    private static final int NUMBER_OF_CARDS_BEGGING_GAME = 7;
    private ArrayList<Player> playerNames;
    private UnoController controller;

    private GameLogicModel model;



    //PLAYER CARDS IN HAND AS BUTTONS
    private JButton[] playerCardButtons;
    //private JButton playerCard;
    private static final int SEVEN = 7;


    public JButton discardPile;
    public JButton newCard;
    public JButton UNOButton;

    public UnoViewFrame(){
        super("Uno");
        controller = new UnoController(model);

        //FRAME PROPERTIES
        getContentPane().setBackground(new Color(30, 120,60));
        setTitle("UNO FLIP - MILESTONE 2");
        setSize(800, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);

        this.controller = controller;

        //subscribing to the view
        model = new GameLogicModel();
        model.addUnoViewFrame(this);
        this.setVisible(true);


        cardPanel = new JPanel(); // display the players cards
        decksPanel = new JPanel();

        scorePanel = new JPanel(); // display score
        discardPile = new JButton(); //shows top card on discard pile
        drawPile = new JButton("DRAW"); //pile to take a card
        UNOButton = new JButton("UNO"); // when player has one card, button shows

        // SETTING UP NEWCARD BUTTON
        newCard = new JButton("NEW CARD");
        drawPile.setPreferredSize(new Dimension(150, 150));
        drawPile.setFont(new Font("Arial", Font.BOLD, 16));
        drawPile.setBackground(Color.GRAY);
        drawPile.setForeground(Color.BLACK);

        //SETTING UP SCOREPANEL
        JLabel scoreLabel = new JLabel("Score: 0");
        scoreLabel.setForeground(Color.BLACK);
        scorePanel.setBackground(new Color(30, 120,60));
        scorePanel.add(scoreLabel);

        //DECK PANEL
        decksPanel.add(drawPile);

        // TESTING RANDOM CARD FOR DISCARD PILE
        Card topCard = new Card();
        String displaNum;
        if(topCard.getCardType().ordinal() <= 9) {
            displaNum = String.valueOf(topCard.getCardType().ordinal());
        }
        else{
            displaNum = topCard.getCardType().toString();
        }
        discardPile.setText(displaNum);
        discardPile.setBackground(topCard.JavaCardColour(topCard.getCardColour()));
        discardPile.setForeground(Color.BLACK);
        discardPile.setFont(new Font("Arial", Font.BOLD, 40));
        discardPile.setPreferredSize(new Dimension(150, 150));


        // ADDING LAYOUT
        setLayout(new BorderLayout());
        decksPanel.setLayout(new FlowLayout(FlowLayout.CENTER, 100, 200));
        decksPanel.add(discardPile);
        decksPanel.add(newCard);

        add(decksPanel, BorderLayout.CENTER);
        add(cardPanel, BorderLayout.SOUTH);
        add(scorePanel, BorderLayout.NORTH);



// WHEN LAYER PRESSESS NEWCARD BUTTON, ONE RANDOM CARD IS ADDED TO THEIR HAND
        // WHEN PLAYER HAND == 1, UNO BUTTON SHOWS AND THEY HAVE TO CLICK IT, IF NOT THEY GET 2 RANDOM CARDS ADDED
        // IF PLAYER ONE PLAYS, POP UP SAY PLAYER 2? PRESS OK,PLAYER 2 CAN PLAY
        // WHEN INSIDE PLAYER 2, SHOWS PLAYER 2 HAND


        cardPanel.setLayout(new FlowLayout(FlowLayout.CENTER, 10, 10));




    }
    public void updateHand(List<Card> Hand){
        discardPile.removeAll();
        discardPile.repaint();
    }

    public void updateTopCard(Card card){
        discardPile.removeAll();
        discardPile.repaint();
    }

    public void showWildColourSelection(){

    }

    public void showMessage(String message){
        JOptionPane.showMessageDialog(this, message);
    }

    //STARTING THE GAME FOR THE FIRST 7 CARDS PER PLAYER
    public void startGamePlayerButtons(){

        //JButton playerCardButton = playerCardButtons[NUMBER_OF_CARDS_BEGGING_GAME];
        //CLEARING EVERYTHING AT THE START OF THE GAME
        cardPanel.removeAll();
        cardPanel.revalidate();
        cardPanel.repaint();

        //GET THE CURRENT PLAYER FROM THE PLAYERORDER CLASS
        Player currentPlayer = model.getCurrentPlayer();

        //DEBUGGING PURPOSES CAN DELETE LATER ENSURING THAT THE CORRECT PLAYER HAND IS SHOWN
        System.out.println("SHOWING CARDS FOR: " + currentPlayer.showHand());
        String displayText;


        //CREATING THE BUTTON ARRAY FOR THE BOTTOM PANEL CARDPANEL IN THE FRAME
        List<Card> hand = controller.getCurrentPlayerHand();

        for (Card card: hand){
            PlayerCardButton playerCardButtons = new PlayerCardButton(card);
            playerCardButtons.addActionListner(controller);
            /*
            for (int i = 0; i < SEVEN; i++) {
                PlayerCardButton playerButton = new PlayerCardButton(card);
                playerCardButtons[i] = playerButton;
                playerButton.addActionListener(controller);
                playerButton.setFocusable(true);
                playerCardButtons[i].setFocusable(false);
                cardPanel.add(playerButton);
            }


             */

        }
        cardPanel.revalidate();
        cardPanel.repaint();

    }

    //called by model.notifyObserver()
    private void update(){
        model.playerTurn();
        startGamePlayerButtons();

    }



}
