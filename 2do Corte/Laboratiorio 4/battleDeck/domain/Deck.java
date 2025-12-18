package domain;

import java.util.ArrayList;

public class Deck extends Thing {

    private ArrayList<Card> cards;

    /**
     * Constructs a new deck
     * 
     * @param name
     * @param type
     */
    public Deck(String name, String type) {
        super(name, type);
        cards = new ArrayList<Card>();
    }

    /**
     * Add a new Card
     * 
     * @param c
     */
    public void addCard(Card c) {
        cards.add(c);
    }

    /**
     * Calculate the average elixir of the cards in the deck considering only the
     * cards with valid values
     * 
     * @return the average
     * @throws ClashRoyaleException IMPOSSIBLE, If there are no valid values
     */
    @Override
    public int elixir() throws ClashRoyaleException {
        if (cards.isEmpty())
            throw new ClashRoyaleException(ClashRoyaleException.IMPOSSIBLE);
        int sum = 0;
        for (Card c : cards) {
            sum += c.elixir();
        }
        return Math.round((float) sum / cards.size());
    }

    /**
     * Calculate the average elixir of the cards in the deck considering only the
     * cards with valid values
     * 
     * @return the average
     * @throws ClashRoyaleException IMPOSSIBLE, If there are no valid values
     */
    public int knownElixir() throws ClashRoyaleException {
        if (cards.isEmpty())
            throw new ClashRoyaleException(ClashRoyaleException.IMPOSSIBLE);
        int sum = 0;
        int count = 0;
        for (Card c : cards) {
            try {
                int v = c.elixir();
                if (v >= 0) {
                    sum += v;
                    count++;
                }
            } catch (ClashRoyaleException e) {
                // Ignorar cartas con error
            }
        }
        if (count == 0)
            throw new ClashRoyaleException(ClashRoyaleException.IMPOSSIBLE);
        return sum / count;
    }

    /**
     * Estimate the average elixir of the cards in the deck replacing unknown or
     * erroneous values
     * 
     * @param unknown
     * @param error
     * @return the estimated average
     * @throws ClashRoyaleException
     */
    public int estimateElixir(String unknown, String error) throws ClashRoyaleException {
        if (cards.isEmpty()) throw new ClashRoyaleException(ClashRoyaleException.IMPOSSIBLE);
        int validSum = 0;
        int validCount = 0;
        int unknownCount = 0;
        int errorCount = 0;
        for (Card c : cards) {
            try {
                int value = c.elixir();
                if (value >= 1 && value <= 9) {
                    validSum += value;
                    validCount++;
                } else {
                    errorCount++;
                }
            } catch (ClashRoyaleException e) {
                unknownCount++;
            }
        }
        if (errorCount > 1 || unknownCount > 1)
            throw new ClashRoyaleException(ClashRoyaleException.IMPOSSIBLE);
        if (validCount == 0)
            throw new ClashRoyaleException(ClashRoyaleException.IMPOSSIBLE);
        int avg = 0;
        if ("AVG".equalsIgnoreCase(unknown) || "AVG".equalsIgnoreCase(error)) {
            avg = knownElixir();
        }
        int finalSum = validSum;
        if (unknownCount == 1) {
            finalSum += "MIN".equalsIgnoreCase(unknown) ? 1 : "MAX".equalsIgnoreCase(unknown) ? 9 : avg;
        }
        if (errorCount == 1) {
            finalSum += "MIN".equalsIgnoreCase(error) ? 1 : "MAX".equalsIgnoreCase(error) ? 9 : avg;
        }
        return finalSum / cards.size();
    }

    /**
     * Calculate the average resistance of the cards in the deck considering only
     * the cards with valid values
     * 
     * @return the average
     * @throws ClashRoyaleException IMPOSSIBLE, If there are no valid values
     */
    @Override
    public int resistance() throws ClashRoyaleException {
        if (cards.isEmpty())
            throw new ClashRoyaleException(ClashRoyaleException.IMPOSSIBLE);
        int sum = 0;
        for (Card c : cards) {
            sum += c.resistance();
        }
        return Math.round((float) sum / cards.size());
    }

    @Override
    public String data() throws ClashRoyaleException {
        StringBuffer answer = new StringBuffer();
        answer.append(name + ": " + type + ". Elixir:" + elixir() + "   Resistencia:" + resistance());
        for (Card c : cards) {
            answer.append("\n\t" + c.data());
        }
        return answer.toString();
    }

}
