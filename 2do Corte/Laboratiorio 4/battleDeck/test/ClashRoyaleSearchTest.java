package test;
import domain.*;


import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;


public class ClashRoyaleSearchTest
{
    @Test
    public void testBusquedaConNombre_FallaPorBugEnSelect() throws ClashRoyaleException {
        ClashRoyale c = new ClashRoyale();
        assertDoesNotThrow(() -> {
            c.search("Cannon");
        });
    }
    
    @Test
    public void testBusquedaConNombreVacio_FallaPorBugEnSelect() throws ClashRoyaleException {
        ClashRoyale c = new ClashRoyale();
        assertDoesNotThrow(() -> {
            c.search("");
        });
    }
    
    @Test
    public void testBusquedaConNombreQueNoExiste_FallaPorBugEnSelect() throws ClashRoyaleException {
        ClashRoyale c = new ClashRoyale();
        assertDoesNotThrow(() -> {
            c.search("Messi");
        });
    }
}
