package domain;


/**
 * Write a description of class ClashRoyaleException here.
 *
 * @author (your name)
 * @version (a version number or a date)
 */
public class ClashRoyaleException extends Exception{
    
    public static final String ELIXIR_UNKNOWN = "No se conoce el elixir.";
    public static final String RESISTANCE_UNKNOWN = "No se conoce la resistencia";
    public static final String ELIXIR_ERROR = "El elixir no es suficiente";
    public static final String RESISTANCE_ERROR = "La resistencia no es suficiente";
    public static final String IMPOSSIBLE = "La resistencia no es suficiente";
    
    public static final String DUPLICATE_CARD = "El nombre de la carta o mazo ya existe.";
    public static final String INVALID_NUMBER = "Los valores deben ser numéricos.";
    public static final String ELIXIR_BAD_ERROR = "El elixir no tiene los valores esperados.";
    public static final String INVALID_TYPE = "El tipo de carta no es válido.";
    
    /**
     * Constructor for objects of class ClashRoyaleException
     */
    public ClashRoyaleException(String message){
        super(message);
    }
}
