package test;
import domain.*;

import static org.junit.Assert.*;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;



/**
 * The test class ClashRoyaleTest.
 *
 * @author  (your name)
 * @version (a version number or a date)
 */
public class ClashRoyaleTest{
    
    @Test
    public void shouldAddCardSuccessfully() throws ClashRoyaleException {
        ClashRoyale c = new ClashRoyale();
        String name = "Giant";
        String type = "TROOP";
        String elixir = "5";
        String hitPoints = "3000";
        c.addCard(name, type, elixir, hitPoints);
        Thing card = c.consult("Giant");
        assertNotNull("La carta debería existir después de añadirla", card);
        assertEquals("El nombre de la carta debería ser Giant", "Giant", card.name());
        assertTrue("La carta debería ser una instancia de Card", card instanceof Card);
    }
    
    @Test
    public void shouldAddDeckSuccessfully() throws ClashRoyaleException {
        ClashRoyale c = new ClashRoyale();
        String deckName = "Test Deck";
        String deckType = "AGRESSIVE";
        String cards = "P.E.K.K.A\nHog Rider\nBaby Dragon";
        c.addDeck(deckName, deckType, cards);
        Thing deck = c.consult("Test Deck");
        assertNotNull("El mazo debería existir después de añadirlo", deck);
        assertEquals("El nombre del mazo debería ser Test Deck", "Test Deck", deck.name());
        assertTrue("El mazo debería ser una instancia de Deck", deck instanceof Deck);
    }
    
    @Test
    public void shouldAddCardWithSpecificAttributes() throws ClashRoyaleException {
        ClashRoyale c = new ClashRoyale();
        String name = "Wizard";
        String type = "TROOP";
        String elixir = "5";
        String hitPoints = "2000";
        c.addCard(name, type, elixir, hitPoints);
        Thing card = c.consult("Wizard");
        assertNotNull("La carta debería existir", card);
        Card wizardCard = (Card) card;
        assertEquals("El tipo de carta debería ser TROOP", "TROOP", wizardCard.type());
        assertEquals("El costo de elixir debería ser 5", 5, wizardCard.elixir());
        assertEquals("Los puntos de vida deberían ser 2000", 2000, wizardCard.resistance());
    }

    @Test
    public void toStringShouldUpdateAfterAddingCardAndDeck() throws ClashRoyaleException {
        ClashRoyale c = new ClashRoyale();
        c.addCard("Giant", "TROOP", "5", "3000");
        c.addDeck("Test Deck", "AGRESSIVE", "P.E.K.K.A\nHog Rider\nBaby Dragon");
        String s = c.toString();
        assertTrue("Después de añadir una carta y un mazo el total debe incrementarse", s.startsWith("9 elementos"));
        assertTrue("Debe contener la carta añadida", s.contains("Giant"));
        assertTrue("Debe contener el mazo añadido", s.contains("Test Deck"));
    }

    @Test
    public void shouldHandleCompleteGameScenario() throws ClashRoyaleException {
        ClashRoyale c = new ClashRoyale();

        c.addCard("Mega Knight", "TROOP", "7", "5000");
        c.addCard("Tesla", "BUILDING", "4", "1200");
        
        c.addDeck("Mixed Deck", "BALANCED", "Mega Knight\nTesla\nFireball");
    
        String gameState = c.toString();
        
        assertTrue("El juego debe tener las nuevas cartas y el mazo", 
            gameState.contains("Mega Knight") && 
            gameState.contains("Tesla") && 
            gameState.contains("Mixed Deck"));
        
        Thing megaKnight = c.consult("Mega Knight");
        Thing mixedDeck = c.consult("Mixed Deck");
        
        assertNotNull("Debe existir la carta Mega Knight", megaKnight);
        assertNotNull("Debe existir el mazo Mixed Deck", mixedDeck);
        assertTrue("El número total de elementos debe ser correcto", 
            gameState.startsWith("10 elementos"));
    }
    
    //a
    /**
     * Si el nombre de la carta o el mazo ya existe, debe fallar.
     */
    @Test
    public void shouldFailWhenCardNameOrDeckAlreadyExists() {
        ClashRoyale c = new ClashRoyale();
        try {
            c.addCard("C001", "Gigante", "5", "800");
            c.addCard("C001", "Gigante", "5", "800");
            fail("Debería lanzar una excepción por carta duplicada.");
        } catch (Exception e) {
            assertEquals(ClashRoyaleException.DUPLICATE_CARD, e.getMessage());
        }
    }

    
    // b
    /**
     * Si los valores numéricos no son válidos, debe fallar.
     */
    @Test
    public void shouldFailWhenNumericValuesAreInvalid() {
        ClashRoyale c = new ClashRoyale();
    
        try {
            c.addCard("C010", "Mago", "cinco", "ochocientos");
            fail("Debería lanzar una excepción por valores no numéricos.");
        } catch (Exception e) {
            assertEquals(ClashRoyaleException.INVALID_NUMBER, e.getMessage());
        }
    }
    
    // c
        /**
     * Si el elixir está fuera del rango permitido, debe fallar.
     */
    @Test
    public void shouldFailWhenElixirOutOfRange() {
        ClashRoyale c = new ClashRoyale();
    
        try {
            c.addCard("C020", "Mini P.E.K.K.A", "20", "600");
            fail("Debería lanzar una excepción por elixir fuera de rango.");
        } catch (Exception e) {
            assertEquals(ClashRoyaleException.ELIXIR_BAD_ERROR, e.getMessage());
        }
    }

    // d
    /**
     * Si el código de la carta está vacío, debe fallar.
     */
    @Test
    public void shouldFailWhenCardCodeIsEmpty() {
        ClashRoyale c = new ClashRoyale();
        try {
            c.addCard("", "Arqueras", "3", "200");
            fail("Debería lanzar una excepción por código vacío.");
        } catch (Exception e) {
            assertEquals(ClashRoyaleException.INVALID_TYPE, e.getMessage());
           }
    }
    }
