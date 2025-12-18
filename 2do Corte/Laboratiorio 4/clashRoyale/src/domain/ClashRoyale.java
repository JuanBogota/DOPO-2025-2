package domain;


import java.util.ArrayList;
import java.util.TreeMap;
import javax.swing.JOptionPane;


/**
 * ClashRoyale
 * @author POOB  
 * @version ECI 2025
 */

public class ClashRoyale{
    private ArrayList<Thing> things;
    private TreeMap<String,Card> cards;

    /**
     * Create a ClashRoyale
     */
    public ClashRoyale(){
        things = new ArrayList<Thing>();
        cards = new TreeMap<String,Card>();
        addSome();
    }

    private void addSome(){
        try{
            String [][] cards= {{"P.E.K.K.A", "TROOP","7","4982"},
                                  {"Hog Rider", "TROOP","4","1696"},
                                  {"Baby Dragon", "TROOP","4","1526"},
                                  {"Cannon", "BUILDING","3","1176"},
                                  {"Fireball", "SPELL","4","0"}};
            for (String [] c: cards){
                addCard(c[0],c[1],c[2],c[3]);
            }
            
            String [][] decks = {{"Beatdown","AGRESSIVE", "P.E.K.K.A\nHog Rider\nBaby Dragon"},
                                 {"Control","DEFENSIVE", "Baby Dragon\nCannon\nFireball"}                                           };
            for (String [] c: decks){
                addDeck(c[0],c[1],c[2]);
            }
            
        } catch(ClashRoyaleException e){
            JOptionPane.showMessageDialog(null, "Error al cargar los datos:\n" + e.getMessage(), "Error de carga", JOptionPane.ERROR_MESSAGE);
            Log.record(e);
        }
    }


    /**
     * Consult a Thing
     */
    public Thing consult(String name){
        Thing c=null;
        for(int i=0;i<things.size() && c == null;i++){
            if (things.get(i).name().compareToIgnoreCase(name)==0) 
               c=things.get(i);
        }
        return c;
    }

    
    /**
     * Add a new card
    */
    public void addCard(String name, String type, String elixir, String hitPoints) throws ClashRoyaleException { 
        if (name == null || name.trim().isEmpty()) {
            throw new ClashRoyaleException(ClashRoyaleException.INVALID_TYPE);
        }
        if (type == null || type.trim().isEmpty()) {
            throw new ClashRoyaleException(ClashRoyaleException.INVALID_TYPE);
        }
        int elixirValue;
        int hitPointsValue;
        try {
            elixirValue = Integer.parseInt(elixir);
            hitPointsValue = Integer.parseInt(hitPoints);
        } catch (NumberFormatException e) {
            throw new ClashRoyaleException(ClashRoyaleException.INVALID_NUMBER);
        }
        if (elixirValue < 1 || elixirValue > 10) {
            throw new ClashRoyaleException(ClashRoyaleException.ELIXIR_BAD_ERROR);
        }
        if (cards.containsKey(name.toUpperCase())) {
        throw new ClashRoyaleException(ClashRoyaleException.DUPLICATE_CARD);
        }
    
        Card nc = new Card(name, type, elixirValue, hitPointsValue);
        things.add(nc);
        cards.put(name.toUpperCase(), nc);
    }
    
    
    /**
     * Add a new deck
    */
    public void addDeck(String name, String type, String theCards) throws ClashRoyaleException{ 
        Deck c = new Deck(name,type);
        String [] aCards= theCards.split("\n");
        for (String b : aCards){
            c.addCard(cards.get(b.toUpperCase()));
        }
        things.add(c);
    }

    /**
     * Consults the things that start with a prefix
     * @param  
     * @return 
     */
    public ArrayList<Thing> select(String prefix){
        ArrayList <Thing> answers=new ArrayList<Thing>();
        prefix=prefix.toUpperCase();
        for(int i=0;i<things.size();i++){
            if(things.get(i).name().toUpperCase().startsWith(prefix)){
                answers.add(things.get(i));
            }   
        }
        return answers;
    }
    
    /**
     * Consult selected things
     * @param selected
     * @return  
     */
    public String data(ArrayList<Thing> selected){
        StringBuffer answer=new StringBuffer();
        answer.append(things.size()+ " elementos\n");
        for(Thing p : selected) {
            try{
                answer.append('>' + p.data());
                answer.append("\n");
            }catch(ClashRoyaleException e){
                answer.append("**** "+e.getMessage());
            }
        }    
        return answer.toString();
    }
    
    
     /**
     * Return the data of things with a prefix
     * @param prefix
     * @return  
     */ 
    public String search(String prefix){
        return data(select(prefix));
    }
    
    
    /**
     * Return the data of all things
     * @return  
     */    
    public String toString(){
        return data(things);
    }
    
    /**
     * Consult the number of things
     * @return 
     */
    public int numberThings(){
        return things.size();
    }

}
