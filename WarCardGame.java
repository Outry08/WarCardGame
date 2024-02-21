package warcardgame;

/*******************************************************************
 * Project: War Card Game
 * Programmer: Ryan McKinnon
 * Date: Mar 31st - Apr 19th, 2021
 * Description: Culminating Project; Re-creating the card game war
*******************************************************************/

import java.util.Scanner;
import java.text.DecimalFormat;
import java.util.ArrayList;

public class WarCardGame {

    public static void main(String[] args) throws InterruptedException {

        // Objects
        Scanner scanS = new Scanner(System.in);
        // Variables
        String p1Name, p2Name;
        // Arrays
        ArrayList<Integer> cardDeck = new ArrayList<>();
        ArrayList<Integer> p1Deck = new ArrayList<>();
        ArrayList<Integer> p2Deck = new ArrayList<>();
        ArrayList<Integer> p1WarCards = new ArrayList<>();
        ArrayList<Integer> p2WarCards = new ArrayList<>();

        // Program introduction and rules
        introduction();

        // User names
        System.out.print("\nPlayer 1, what is your name?: ");
        p1Name = scanS.nextLine();
        System.out.print("Player 2, what is your name?: ");
        p2Name = scanS.nextLine();

        // Creating a deck of cards in a random order
        createDeck(cardDeck);

        // Dividing the cards among the two players' individual decks
        dealCards(cardDeck, p1Deck, p2Deck);

        // Playing the game
        playGame(p1Name, p1Deck, p1WarCards, p2Name, p2Deck, p2WarCards);

        // Determining and declaring the winner
        if (p1Deck.size() > p2Deck.size())
            gameEnd(p1Name);
        else if (p2Deck.size() > p1Deck.size())
            gameEnd(p2Name);
        else
            System.out.println("\nIt's a tie!");

    }

    /**
     * Method: introduction
     * Description: Prints out the title, welcoming, and rules of the game
     **/
    public static void introduction() {

        // Objects
        Scanner scanS = new Scanner(System.in);
        // Variables
        String viewRules;

        // Title
        System.out.println("\t\t\t| WAR |\n");

        // User chooses to view the rules or not
        System.out.print("Welcome! This is a program that simulates the card"
                + " game war.\nWhen asked for an input, type it next to the request."
                + "\nWould you like to hear the rules of the game?"
                + "(yes/no): ");
        // Error handling
        do {
            viewRules = scanS.nextLine();
            if (!(viewRules.equalsIgnoreCase("yes")
                    || viewRules.equalsIgnoreCase("no")))
                System.out.print("Please input yes or no: ");
        } while (!(viewRules.equalsIgnoreCase("yes")
                || viewRules.equalsIgnoreCase("no")));

        // Rules
        if (viewRules.equalsIgnoreCase("yes"))
            System.out.println("\nThe rules of this game is that two people are"
                    + " each\ngiven a half of the deck of cards and, without"
                    + " looking at\ntheir cards, they draw the top card at the same"
                    + " time, and\nthe larger card (2 is low, Ace is high, Joker"
                    + " is highest)\ntakes the smaller card, and the aim of the"
                    + " game is to take every\none of your opponent’s cards. There"
                    + " are the usual 4 cards\nof each type, and 2 Jokers. If there"
                    + " is a tie, a war\ninitiates where each player places"
                    + " down a set number of cards\nfacing down, and then places a"
                    + " card facing up and whichever\ncard is larger, takes every"
                    + " card that was placed.\nThere are a few house rules in place"
                    + " as well: A gift is when\nyou beat your opponent’s card by"
                    + " 1, then they give you their\nnext card for free. Another"
                    + " house rule is that every 30 turns,\nthe number of cards"
                    + " in special events increases: wars start at\n2 and gifts start"
                    + " at 1, then, after 30 turns, wars are 3 and\ngifts are 2 and"
                    + " so on. (The symbol ':P' represents a Joker)");
        System.out.println("\nGood luck, have fun!");

    }

    /**
     * Method: createDeck
     * Description: Makes a deck of cards with the cards in a random order
     * 
     * @param cardDeck - The array for the deck of cards
     **/
    public static void createDeck(ArrayList<Integer> cardDeck) {

        // Variables
        int rndNum;
        boolean isPastLimit;

        // An array of 14, a counter for each type of card
        int[] cardCount = new int[14];

        // Loops until the deck has 54 cards
        while (cardDeck.size() < 54) {
            // Setting the boolean to false at the beginning of each loop
            isPastLimit = false;

            /*
             * Getting the random number. The random number that is chosen is 2
             * less than the card type that it corresponds to.
             */
            rndNum = (int) (Math.random() * 14);

            /*
             * Incrementing the counter accordingly and checking if the counter
             * has exceeded it's limit.
             */
            cardCount[rndNum]++;
            // The cards from 2 - Ace all need 4 cards
            if (rndNum != 13 && cardCount[rndNum] > 4) {
                cardCount[rndNum]--;// The loop will not run indefinitely
                isPastLimit = true;
            }
            // The Joker only needs 2 cards
            else if (rndNum == 13 && cardCount[rndNum] > 2) {
                cardCount[rndNum]--;// The loop will not run indefinitely
                isPastLimit = true;
            }
            // Adding the card value if the limit was not surpassed
            if (!isPastLimit)
                cardDeck.add(rndNum);
        }

    }

    /**
     * Method: getCardInfo
     * Description: Getting the correct symbol and name for the card
     * 
     * @param card   - The card that is getting it's symbol and name
     * @param symbol - Checks if the desired information is the symbol or the name
     * @return cardInfo - The desired piece of information, either the name or the
     *         symbol.
     **/
    public static String getCardInfo(int card, boolean symbol) {

        // Created to change ints into strings
        DecimalFormat toString = new DecimalFormat("0");
        // Variables
        String cardSymbol = "", cardName = "", cardInfo;

        // If the card value is < 9, the symbol is the card value + 2
        if (card < 9)
            cardSymbol = toString.format(card + 2);

        // Getting the name of the card, and the symbol if the card's value is >= 9
        switch (card) {
            case 0:
                cardName = " Two ";
                break;
            case 1:
                cardName = "Three";
                break;
            case 2:
                cardName = " Four";
                break;
            case 3:
                cardName = " Five";
                break;
            case 4:
                cardName = " Six ";
                break;
            case 5:
                cardName = "Seven";
                break;
            case 6:
                cardName = "Eight";
                break;
            case 7:
                cardName = " Nine";
                break;
            case 8:
                cardName = " Ten ";
                break;
            case 9:
                cardSymbol = "J";
                cardName = " Jack";
                break;
            case 10:
                cardSymbol = "Q";
                cardName = "Queen";
                break;
            case 11:
                cardSymbol = "K";
                cardName = " King";
                break;
            case 12:
                cardSymbol = "A";
                cardName = " Ace ";
                break;
            case 13:
                cardSymbol = ":P";
                cardName = "Joker";
                break;
        }

        // Returning the desired piece of information
        if (symbol)
            cardInfo = cardSymbol;
        else
            cardInfo = cardName;
        return cardInfo;

    }

    /**
     * Method: printCard
     * Description: Prints out lines to look like a card
     * 
     * @param card          - The value of the current card in play
     * @param faceDownCards - The number of cards to be printed face down in a war
     * @param isSmall       - If a card should be printed in miniature size or not
     * @throws InterruptedException - Allows the use of Thread.sleep
     **/
    public static void printCard(int card, int faceDownCards, boolean isSmall)
            throws InterruptedException {

        // Variables
        String cardName, cardSymbol, extraSpace = "";

        // Getting the appropriate name and symbol from the methods
        cardSymbol = getCardInfo(card, true);
        cardName = getCardInfo(card, false);

        /*
         * Compensating to keep the spacing constant depending on if the cardSymbol
         * is 1 or 2 characters long.
         */
        if (cardSymbol.length() == 1)
            extraSpace = " ";

        // Printing the larger type of card when the boolean isSmall is false
        if (!isSmall) {
            /*
             * Printing face down cards the number of times as the value of
             * faceDownCards, if it's 0, no face down cards will be printed.
             */
            for (int i = 0; i < faceDownCards; i++) {
                System.out.println("—————————");
                System.out.println("|*******|");
                wait(500);
            }
            // Printing the large card
            System.out.println("—————————");
            System.out.println("|     " + extraSpace + cardSymbol + "|");
            System.out.println("|       |");
            System.out.println("| " + cardName + " |");
            System.out.println("|       |");
            System.out.println("|" + cardSymbol + "     " + extraSpace + "|");
            System.out.println("—————————");
        }
        /*
         * Printing the smaller card when the boolean is true. The smaller card is
         * used for gifts.
         */
        else {
            System.out.println("——————");
            System.out.println("|    |");
            System.out.println("| " + extraSpace + cardSymbol + " |");
            System.out.println("|    |");
            System.out.println("——————");
        }

    }

    /**
     * Method: dealCards
     * Description: Distributes the randomized deck into two separate decks, one
     * for each player.
     * 
     * @param cardDeck - The randomized deck of cards
     * @param p1Deck   - The 1st player's deck of cards
     * @param p2Deck   - The 2nd player's deck of cards
     **/
    public static void dealCards(ArrayList<Integer> cardDeck,
            ArrayList<Integer> p1Deck, ArrayList<Integer> p2Deck) {

        // Dealing the even indexes to player 1, and the odd indexes to player 2
        for (int i = 0; i < 54; i++) {
            if (i % 2 == 0)
                p1Deck.add(cardDeck.get(i));
            else
                p2Deck.add(cardDeck.get(i));
        }

    }

    /**
     * Method: playGame
     * Description: Plays a round of the game, determines the winner, and loops
     * until a player runs out of cards or desires to quit the game.
     * 
     * @param p1Name     - Player 1's name
     * @param p1Deck     - Player 1's cards
     * @param p1WarCards - The cards from player 1's deck that are placed in a war
     * @param p2Name     - Player 2's name
     * @param p2Deck     - Player 2's cards
     * @param p2WarCards - The cards from player 2's deck that are placed in a war
     * @throws InterruptedException - Allows the use of Thread.sleep
     **/
    public static void playGame(String p1Name, ArrayList<Integer> p1Deck,
            ArrayList<Integer> p1WarCards, String p2Name, ArrayList<Integer> p2Deck,
            ArrayList<Integer> p2WarCards) throws InterruptedException {

        // Objects
        Scanner scanS = new Scanner(System.in);
        // Variables
        String contn;
        boolean isWar = false;
        int faceDownCards = 0, maxWarCards = 2, numRounds = 0;

        // Sentinel do while
        do {
            // Incrementing the number or rounds after each round
            numRounds++;

            // Increasing the size of wars every 30 turns
            if (numRounds % 30 == 0) {
                maxWarCards++;
                System.out.println("\n" + (maxWarCards - 1) + " CARD GIFTS & "
                        + maxWarCards + " CARD WARS START NOW"
                        + "\n(" + numRounds + " turns in)");
            }

            // User input to place cards
            System.out.print("\nPlease type 'go' to play your cards, type 'quit'"
                    + "\nif you would like to end the game. In which case the player"
                    + "\nwith the most cards will win: ");
            // Error handling
            do {
                contn = scanS.nextLine();
                if (!(contn.equalsIgnoreCase("go")
                        || contn.equalsIgnoreCase("quit")))
                    System.out.print("Plese type 'go' or 'quit': ");
            } while (!(contn.equalsIgnoreCase("go")
                    || contn.equalsIgnoreCase("quit")));

            // Playing the cards
            if (contn.equalsIgnoreCase("quit"))
                break;
            // The round number
            System.out.println("\nRound #" + numRounds + ":");

            // Loops if a war is happening
            do {
                // Player 1's card
                playCard(p1Name, p1Deck.get(0), faceDownCards);

                // Adding a delay if a war is happening for better output flow
                if (isWar)
                    wait(1000);

                // Player 2's card
                playCard(p2Name, p2Deck.get(0), faceDownCards);

                // 2 second delay
                wait(2000);

                // If player 1 wins the round
                if (p1Deck.get(0) > p2Deck.get(0))
                    isWar = distributeWinnings(isWar, maxWarCards - 1, p1Name,
                            p1Deck, p1WarCards, p2Name, p2Deck, p2WarCards);

                // If player 2 wins the round
                else if (p2Deck.get(0) > p1Deck.get(0))
                    isWar = distributeWinnings(isWar, maxWarCards - 1, p2Name,
                            p2Deck, p2WarCards, p1Name, p1Deck, p1WarCards);

                // If a tie happens
                else {
                    // User input to place the war cards
                    System.out.print("A war! Please type 'go' to continue: ");
                    // Error handling
                    do {
                        contn = scanS.nextLine();
                        if (!(contn.equalsIgnoreCase("go")))
                            System.out.print("Please type 'go' to continue: ");
                    } while (!(contn.equalsIgnoreCase("go")));

                    // Setting the boolean to true
                    isWar = true;

                    /*
                     * Initiate a war and determine how many cards are to be
                     * face down at the same time. Methods with a return type
                     * still alter arrays.
                     */
                    faceDownCards = war(maxWarCards, p1Deck, p1WarCards, p2Deck,
                            p2WarCards) - 1;
                }
            } while (isWar);

            // 1 second delay
            wait(1000);

            // Telling the players how many cards they have after each round
            System.out.println("\n" + p1Name + " has " + p1Deck.size() + " cards");
            System.out.println(p2Name + " has " + p2Deck.size() + " cards");

            // 1 second delay
            wait(1000);

            // Resetting the faceDownCards value to 0 once the war is over
            faceDownCards = 0;
        } while (!(p1Deck.isEmpty() || p2Deck.isEmpty()));

    }

    /**
     * Method: playCard
     * Description: Output's whose card it is, and calls the printCard method
     * 
     * @param name          - The name of the player whose card is being placed
     * @param card          - The card that is to be outputted
     * @param faceDownCards - The number of cards to be placed face down when a
     *                      war is happening.
     * @throws InterruptedException - Allows the use of Thread.sleep
     **/
    public static void playCard(String name, int card, int faceDownCards)
            throws InterruptedException {

        System.out.println("\n" + name + "'s placement" + ":");
        printCard(card, faceDownCards, false);

    }

    /**
     * Method: distributeWinnings
     * Description: Distributes the cards from the loser to the winner
     * 
     * @param isWar        - If a war is happening or not
     * @param numGiftCards - The current number of gift cards to be given
     * @param winner       - The winner of the round
     * @param wDeck        - The winner's deck
     * @param wWarCards    - The winner's cards that were in a war
     * @param loser        - The loser of the round
     * @param lDeck        - The loser's deck
     * @param lWarCards    - The loser's cards that were in a war
     * @throws InterruptedException - Allows the use of Thread.sleep
     * @return isWar - Sending the isWar boolean back as it is updated in this
     *         method.
     **/
    public static boolean distributeWinnings(boolean isWar, int numGiftCards,
            String winner, ArrayList<Integer> wDeck, ArrayList<Integer> wWarCards,
            String loser, ArrayList<Integer> lDeck, ArrayList<Integer> lWarCards)
            throws InterruptedException {

        // Initializing the boolean to false every time the method is called
        boolean gift = false;

        // Outputting the winner of the round
        System.out.println(winner + " wins the round!");

        // Checking if a gift should be initiated
        if (wDeck.get(0) - lDeck.get(0) == 1)
            gift = true;

        /*
         * The winner takes the opponents card and has their own
         * card moved to the back of the deck.
         */
        wDeck.add(wDeck.get(0));
        wDeck.remove(0);
        wDeck.add(lDeck.get(0));
        lDeck.remove(0);

        /*
         * If a war happened, the winner additionally wins the cards
         * in both war card arrays.
         */
        if (isWar) {
            if (!wWarCards.isEmpty()) {
                /*
                 * wDeck.size - 2 because the card that the winner played is already
                 * at the back of the deck, just below the loser's card which is at
                 * the very back, same for wDeck.size - 1. The subtraction is there
                 * because the .size command starts counting at 1, but the indexes
                 * start at 0.
                 */
                warContents(winner, wWarCards, wDeck.get(wDeck.size() - 2),
                        loser, lWarCards, wDeck.get(wDeck.size() - 1));
            }
            /*
             * Adding all the war cards to the winner's deck and clearing the war
             * card arrays.
             */
            wDeck.addAll(wWarCards);
            wWarCards.clear();
            wDeck.addAll(lWarCards);
            lWarCards.clear();
            isWar = false;
        }

        /*
         * If a gift is required, print out small cards for each
         * card in the gift, and give the winner the gifted cards.
         */
        if (gift) {
            wait(1000);
            if (!lDeck.isEmpty()) {
                System.out.println("\nThe gift from " + loser + ":");
                for (int i = 0; i < numGiftCards; i++) {
                    wait(500);
                    if (lDeck.isEmpty())
                        break;
                    printCard(lDeck.get(0), 0, true);
                    wDeck.add(lDeck.get(0));
                    lDeck.remove(0);
                }
            }
        }

        return isWar;

    }

    /**
     * Method: war
     * Description: Plays out a war
     * 
     * @param maxWarCards - The maximum number of cards that can be in the war
     * @param p1Deck      - Player 1's cards
     * @param p1WarCards  - The cards from player 1's deck that are placed in a war
     * @param p2Deck      - Player 2's cards
     * @param p2WarCards  - The cards from player 2's deck that are placed in a war
     * @return i - The number of times the loop runs
     * @throws InterruptedException - Allows the use of Thread.sleep
     **/
    public static int war(int maxWarCards, ArrayList<Integer> p1Deck,
            ArrayList<Integer> p1WarCards, ArrayList<Integer> p2Deck,
            ArrayList<Integer> p2WarCards) throws InterruptedException {

        // The variable is needed outside of the for loop
        int i;

        /*
         * Adds the appropriate number of cards into the war, and the initial card
         * that started the war (Hence the + 1 in the loop condition).
         */
        for (i = 0; i < maxWarCards + 1; i++) {
            /*
             * Checking if the normal amount of war cards can be acheived without
             * emptying a player's deck.
             */
            if (p1Deck.size() == 1 || p2Deck.size() == 1) {
                // Checking if the war cannot be fulfilled right from the start
                if (i == 0) {
                    System.out.println("A war cannot happen, the cards have been"
                            + " put back in each\nplayer's deck and a normal round"
                            + " will now play: ");
                    /*
                     * Reverting any past wars that may have happened in a row
                     * before this one, and recycling the current card to the back
                     * of each player's deck; creating a brand new round.
                     */
                    p1Deck.addAll(p1WarCards);
                    p1Deck.add(p1Deck.get(0));
                    p1Deck.remove(0);
                    p1WarCards.clear();
                    p2Deck.addAll(p2WarCards);
                    p2Deck.add(p2Deck.get(0));
                    p2Deck.remove(0);
                    p2WarCards.clear();

                    // 1 second delay
                    wait(1000);
                }
                /*
                 * Informing the user that a full war cannot happen, and how many
                 * cards will be placed instead.
                 */
                else {
                    System.out.println("A full war cannot happen. " + (i - 1)
                            + " card(s) will be placed face down\ninstead of "
                            + maxWarCards + ".");
                    // 1 second delay
                    wait(1000);
                }
                break;
            } else {
                /*
                 * Adding each player's cards to the war card arrays where the
                 * winner of the war will take from.
                 */
                p1WarCards.add(p1Deck.get(0));
                p1Deck.remove(0);
                p2WarCards.add(p2Deck.get(0));
                p2Deck.remove(0);
            }
        }

        /*
         * Returning the i vairable to set the faceDownCards variable in playGame to
         * the correct value, even if the loop was exited early.
         */
        return i;

    }

    /**
     * Method: warContents
     * Description: Outputting all of the cards that were in the war
     * 
     * @param winner     - The name of the winner of the war
     * @param wWarCards  - The winner's war cards
     * @param wExtraCard - The winner's final card in the war (It is not in the war
     *                   card array)
     * @param loser      - The name of the loser of the war
     * @param lWarCards  - The loser's war cards
     * @param lExtraCard - The loser's final card in the war (It is not in the war
     *                   card array)
     * @throws InterruptedException - Allows the use of Thread.sleep
     **/
    public static void warContents(String winner, ArrayList<Integer> wWarCards,
            int wExtraCard, String loser, ArrayList<Integer> lWarCards,
            int lExtraCard) throws InterruptedException {

        // 1 second delay
        wait(1000);

        // Informing the user of what their war cards were
        System.out.println("\nThe cards that " + winner + " had in the war"
                + "\n(face down and face up cards):");
        for (int i = 0; i < wWarCards.size(); i++)
            System.out.print(getCardInfo(wWarCards.get(i), true) + ", ");
        System.out.println(getCardInfo(wExtraCard, true));

        // 2 second delay
        wait(2000);

        // Informing the winner of what they won from the opponent
        System.out.println("\n" + winner + "'s war winnings from " + loser
                + "\n(face down and face up cards): ");
        for (int i = 0; i < lWarCards.size(); i++)
            System.out.print(getCardInfo(lWarCards.get(i), true) + ", ");
        System.out.println(getCardInfo(lExtraCard, true));

        // 1 second delay
        wait(1000);

    }

    /**
     * Method: gameEnd
     * Description: Outputting a message declaring the winner and thanking the
     * users for playing.
     * 
     * @param winner - The winner of the game
     **/
    public static void gameEnd(String winner) {

        // Declaring the winner
        System.out.println("\n" + winner + " wins the game!");
        // Thanking the users for playing
        System.out.println("Thanks for playing! I hope you enjoyed!");

    }

    /**
     * Method: wait
     * Description: Waits the desired amount of time. An alternative to writing
     * Thread.sleep every time.
     * 
     * @param milliseconds - The desired amount of time
     * @throws InterruptedException - Allows the use of Thread.sleep
     **/
    public static void wait(int milliseconds) throws InterruptedException {

        Thread.sleep(milliseconds);

    }

}
