import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

public class UnoController implements ActionListener {

    private UnoViewFrame viewFrame;
    private GameLogicModel model;


    public UnoController(GameLogicModel model) {

        this.model = new GameLogicModel();



    }

    public void setView(UnoViewFrame viewFrame) {
        this.viewFrame = viewFrame;
        viewFrame.newCard.addActionListener(this);
        // frame.UNOButton.addActionListener(this);
        model.startGame();
        viewFrame.updateTopCard(model.getTopCard());
        viewFrame.discardPile.addActionListener(this);

        model.startGame();
    }




    public void onCardClicked(Card heldCard) {
        Card top = model.getTopCard();

        if (heldCard.playCardOnAnother(top)) {
            model.playGame();
            updateView();
        }
    }

    public void onDrawClicked(){
        model.drawCardCurrentPlayer();
        updateView();
    }



    @Override
    public void actionPerformed(ActionEvent e) {
        Object source = e.getSource();

        if (source instanceof JButton){
            source = (JButton)source;
        }
        if(source == viewFrame.newCard){
            JOptionPane.showMessageDialog(viewFrame, "new card drawn");
            // IMPLEMENT ACTIONS

        }
        else if (source == viewFrame.discardPile){
            JOptionPane.showMessageDialog(viewFrame,"top card");
        }


    }

    public void updateView() {
        if (viewFrame == null) {
            return;
        }
        else {
            PlayerOrder playerOrder = new PlayerOrder();
            List<Card> hand = model.getPlayerHand();
            viewFrame.updateHand(hand);
            viewFrame.updateTopCard(model.getTopCard());
        }

    }

    public List<Card> getCurrentPlayerHand() {
        return model.getCurrentPlayer().getHand();
    }
}
