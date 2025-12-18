package test;

import domain.*;

import static org.junit.Assert.*;

import java.beans.Transient;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class DeckTest {

    @Test
    public void shouldCalculateTheElixirOfADeck() {
        Deck c = new Deck("Beatdown", "AGRESSIVE");
        c.addCard(new Card("P.E.K.K.A", "TROOP", 7, 4982));
        c.addCard(new Card("Hog Rider", "TROOP", 4, 1696));
        c.addCard(new Card("Baby Dragon", "TROOP", 4, 1526));
        try {
            assertEquals(5, c.elixir());
        } catch (ClashRoyaleException e) {
            fail("Threw a exception");
        }
    }

    @Test
    public void shouldThrowExceptionIfDeckHasNoCard() {
        Deck c = new Deck("Beatdown", "AGRESSIVE");
        try {
            int elixir = c.elixir();
            fail("Did not throw exception");
        } catch (ClashRoyaleException e) {
            assertEquals(ClashRoyaleException.IMPOSSIBLE, e.getMessage());
        }
    }

    @Test
    public void shouldThrowExceptionIfThereIsErrorInElixir() {
        Deck c = new Deck("Beatdown", "AGRESSIVE");
        c.addCard(new Card("P.E.K.K.A", "TROOP", 7, 4982));
        c.addCard(new Card("Hog Rider", "TROOP", -4, 1696));
        c.addCard(new Card("Baby Dragon", "TROOP", 4, 1526));
        try {
            int elixir = c.elixir();
            fail("Did not throw exception");
        } catch (ClashRoyaleException e) {
            assertEquals(ClashRoyaleException.ELIXIR_ERROR, e.getMessage());
        }
    }

    @Test
    public void shouldThrowExceptionIfElixirIsNotKnown() {
        Deck c = new Deck("Beatdown", "AGRESSIVE");
        c.addCard(new Card("P.E.K.K.A", "TROOP", 7, 4982));
        c.addCard(new Card("Hog Rider", "TROOP", 4, 1696));
        c.addCard(new Card("Baby Dragon", "TROOP", null, 1526));
        try {
            int elixir = c.elixir();
            fail("Did not throw exception");
        } catch (ClashRoyaleException e) {
            assertEquals(ClashRoyaleException.ELIXIR_UNKNOWN, e.getMessage());
        }
    }

    // ----------------------------------------------------------

    @Test
    public void shouldCalculateTheElixirOfADeckKnowElixir() {
        Deck c = new Deck("Beatdown", "AGRESSIVE");
        c.addCard(new Card("P.E.K.K.A", "TROOP", 7, 4982));
        c.addCard(new Card("Hog Rider", "TROOP", 4, 1696));
        c.addCard(new Card("Baby Dragon", "TROOP", 4, 1526));
        try {
            assertEquals(5, c.knownElixir());
        } catch (ClashRoyaleException e) {
            fail("Threw a exception");
        }
    }

    @Test
    public void shouldThrowExceptionIfDeckHasNoCardKnowElixir() {
        Deck c = new Deck("Beatdown", "AGRESSIVE");
        try {
            int elixir = c.knownElixir();
            fail("Did not throw exception");
        } catch (ClashRoyaleException e) {
            assertEquals(ClashRoyaleException.IMPOSSIBLE, e.getMessage());
        }
    }

    @Test
    public void shouldThrowExceptionIfThereIsErrorInElixirKnowElixir() {
        Deck c = new Deck("Beatdown", "AGRESSIVE");
        c.addCard(new Card("P.E.K.K.A", "TROOP", 7, 4982));
        c.addCard(new Card("Hog Rider", "TROOP", -4, 1696));
        c.addCard(new Card("Baby Dragon", "TROOP", 4, 1526));
        try {
            assertEquals(5, c.knownElixir());
        } catch (ClashRoyaleException e) {
            fail("Threw a exception");
        }
    }

    @Test
    public void shouldThrowExceptionIfElixirIsNotKnownKnowElixir() {
        Deck c = new Deck("Beatdown", "AGRESSIVE");
        c.addCard(new Card("P.E.K.K.A", "TROOP", 7, 4982));
        c.addCard(new Card("Hog Rider", "TROOP", 4, 1696));
        c.addCard(new Card("Baby Dragon", "TROOP", null, 1526));
        try {
            assertEquals(5, c.knownElixir());
        } catch (ClashRoyaleException e) {
            fail("Threw a exception");
        }
    }

    // ---------------------------------------

    @Test
    public void shouldEstimateElixirWithValidValues() {
        Deck deck = new Deck("Test", "NORMAL");
        deck.addCard(new Card("Card1", "TROOP", 7, 1000));
        deck.addCard(new Card("Card2", "TROOP", 4, 1000));
        deck.addCard(new Card("Card3", "TROOP", 4, 1000));
        try {
            assertEquals(5, deck.estimateElixir("MIN", "MIN"));
        } catch (ClashRoyaleException e) {
            fail("No debería lanzar excepción");
        }
    }

    @Test
    public void shouldEstimateElixirWithOneUnknownUsingMin() {
        Deck deck = new Deck("Test", "NORMAL");
        deck.addCard(new Card("Card1", "TROOP", 6, 1000));
        deck.addCard(new Card("Card2", "TROOP", null, 1000));
        try {
            assertEquals(3, deck.estimateElixir("MIN", "MIN")); // (6 + 1) / 2 = 3
        } catch (ClashRoyaleException e) {
            fail("No debería lanzar excepción");
        }
    }

    @Test
    public void shouldEstimateElixirWithOneErrorUsingMax() {
        Deck deck = new Deck("Test", "NORMAL");
        deck.addCard(new Card("Card1", "TROOP", 6, 1000));
        deck.addCard(new Card("Card2", "TROOP", -1, 1000));
        try {
            assertEquals(7, deck.estimateElixir("MAX", "MAX")); // (6 + 9) / 2 = 7
        } catch (ClashRoyaleException e) {
            fail("No debería lanzar excepción");
        }
    }

    @Test
    public void shouldThrowExceptionWithEmptyDeck() {
        Deck deck = new Deck("Test", "NORMAL");
        try {
            deck.estimateElixir("MIN", "MIN");
            fail("Debería lanzar excepción");
        } catch (ClashRoyaleException e) {
            assertEquals(ClashRoyaleException.IMPOSSIBLE, e.getMessage());
        }
    }

    @Test
    public void shouldThrowExceptionWithMultipleUnknowns() {
        Deck deck = new Deck("Test", "NORMAL");
        deck.addCard(new Card("Card1", "TROOP", null, 1000));
        deck.addCard(new Card("Card2", "TROOP", null, 1000));
        try {
            deck.estimateElixir("MIN", "MIN");
            fail("Debería lanzar excepción");
        } catch (ClashRoyaleException e) {
            assertEquals(ClashRoyaleException.IMPOSSIBLE, e.getMessage());
        }
    }

    @Test
    public void shouldThrowExceptionWithOneUnknownUsingAVG() {
        Deck deck = new Deck("Test", "NORMAL");
        deck.addCard(new Card("Card1", "TROOP", 6, 1000));
        deck.addCard(new Card("Card2", "TROOP", null, 1000));
        try {
            assertEquals(6, deck.estimateElixir("AVG", "MAX")); // (6 + 6) / 2 = 6
        } catch (ClashRoyaleException e) {
            fail("No debería lanzar excepción");
        }
    }

    // ---------------------------------------

    @Test
    public void shouldCalculateTheResistanceOfADeck() {
        Deck c = new Deck("Beatdown", "AGRESSIVE");
        c.addCard(new Card("P.E.K.K.A", "TROOP", 7, 4982));
        c.addCard(new Card("Hog Rider", "TROOP", 4, 1696));
        c.addCard(new Card("Baby Dragon", "TROOP", 4, 1526));
        try {
            assertEquals(2735, c.resistance());
        } catch (ClashRoyaleException e) {
            fail("Threw a exception");
        }
    }

    @Test
    public void shouldThrowExceptionIfDeckHasNoCardResistance() {
        Deck c = new Deck("Beatdown", "AGRESSIVE");
        try {
            int elixir = c.resistance();
            fail("Did not throw exception");
        } catch (ClashRoyaleException e) {
            assertEquals(ClashRoyaleException.IMPOSSIBLE, e.getMessage());
        }
    }

    @Test
    public void shouldThrowExceptionIfThereIsErrorInResistance() {
        Deck c = new Deck("Beatdown", "AGRESSIVE");
        c.addCard(new Card("P.E.K.K.A", "TROOP", 7, 4982));
        c.addCard(new Card("Hog Rider", "TROOP", -4, -1696));
        c.addCard(new Card("Baby Dragon", "TROOP", 4, 1526));
        try {
            int elixir = c.resistance();
            fail("Did not throw exception");
        } catch (ClashRoyaleException e) {
            assertEquals(ClashRoyaleException.RESISTANCE_ERROR, e.getMessage());
        }
    }

    @Test
    public void shouldThrowExceptionIfResistanceIsNotKnown() {
        Deck c = new Deck("Beatdown", "AGRESSIVE");
        c.addCard(new Card("P.E.K.K.A", "TROOP", 7, 4982));
        c.addCard(new Card("Hog Rider", "TROOP", 4, 1696));
        c.addCard(new Card("Baby Dragon", "TROOP", null, null));
        try {
            int elixir = c.resistance();
            fail("Did not throw exception");
        } catch (ClashRoyaleException e) {
            assertEquals(ClashRoyaleException.RESISTANCE_UNKNOWN, e.getMessage());
        }
    }
}