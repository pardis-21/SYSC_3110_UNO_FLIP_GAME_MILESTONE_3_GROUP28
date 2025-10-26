import  java.util.*;

public class PlayerOrder {
    private playerNode firstPlayer;
    private playerNode lastPlayer;
    private int numPlayers;

    private class playerNode {
        private String pName;
        private playerNode prev;
        private playerNode next;

        public playerNode(Player p) {
            this.pName = p.getName();
            this.prev = null;
            this.next = null;

        }
    }

    public PlayerOrder() {
        this.firstPlayer = null;
        this.lastPlayer = null;
        this.numPlayers = 0;
    }
    public boolean isEmpty() {
        return numPlayers == 0;
    }


    //adding players to the linkedList
    public void addPlayer(Player p) {
        playerNode tempPlayerNode = new playerNode(p);

        if (firstPlayer == null) {

            firstPlayer = tempPlayerNode;
            firstPlayer.next = tempPlayerNode;
            firstPlayer.prev = firstPlayer;

            lastPlayer = tempPlayerNode;
            lastPlayer.next = tempPlayerNode;
            lastPlayer.prev = lastPlayer;

        } else if (firstPlayer == lastPlayer) {
            firstPlayer.next = tempPlayerNode;
            lastPlayer = tempPlayerNode;
            lastPlayer.next = firstPlayer;
            lastPlayer.prev = firstPlayer.next;
            firstPlayer.prev = lastPlayer.next;

        } else if (firstPlayer != lastPlayer) {

            lastPlayer.next = tempPlayerNode;
            tempPlayerNode.prev = lastPlayer;
            lastPlayer = tempPlayerNode;
            tempPlayerNode.next = firstPlayer;
            firstPlayer.prev = tempPlayerNode;

        }
    }

    //traversing forward the doubly linked list
    public void nextPlayerClockwise(){
        playerNode currentPlayer = firstPlayer;
        while (currentPlayer != null) {
            System.out.println("Its "+ currentPlayer.pName + "'s turn!");
            currentPlayer = currentPlayer.next;
        }
    }

    public void nextPlayerCounterClockwise(){
        playerNode currentPlayer = lastPlayer;
        while (currentPlayer != null) {
            System.out.println("Its "+ currentPlayer.pName + "'s turn!");
            currentPlayer = currentPlayer.prev;
        }

    }
}