import java.awt.*;
import java.awt.geom.*;
import java.lang.Math;
 
/**
* A circle that can be manipulated and that draws itself on a canvas.
* 
* @author  Michael Kolling and David J. Barnes
* @version 1.0.  (15 July 2000) 
*/
 
public class Circle{
 
    public static final double PI=3.1416;
    private int diameter;
    private int xPosition;
    private int yPosition;
    private String color;
    private boolean isVisible;
    private double area;
    private int ticks;


    public Circle(){
        diameter = 30;
        xPosition = 20;
        yPosition = 15;
        color = "blue";
        isVisible = false;
    }
       
    public Circle(int area){
        if(area >= 0) {
            double radio = Math.sqrt(area / PI);
            diameter = (int)Math.round(2 * radio);
        } else {
            diameter = 30;
        }
        xPosition = 20;
        yPosition = 15;
        color = "blue";
        isVisible = false;
    }
    
    public void makeVisible(){
        isVisible = true;
        draw();
    }
 
    public void makeInvisible(){
        erase();
        isVisible = false;
    }
 
    private void draw(){
        if(isVisible) {
            Canvas canvas = Canvas.getCanvas();
            canvas.draw(this, color, 
                new Ellipse2D.Double(xPosition, yPosition, 
                diameter, diameter));
            canvas.wait(10);
        }
    }
 
    private void erase(){
        if(isVisible) {
            Canvas canvas = Canvas.getCanvas();
            canvas.erase(this);
        }
    }
    
    /**
     * Move the circle a few pixels to the right.
     */
    public void moveRight(){
        moveHorizontal(20);
    }
 
    /**
     * Move the circle a few pixels to the left.
     */
    public void moveLeft(){
        moveHorizontal(-20);
    }
 
    /**
     * Move the circle a few pixels up.
     */
    public void moveUp(){
        moveVertical(-20);
    }
 
    /**
     * Move the circle a few pixels down.
     */
    public void moveDown(){
        moveVertical(20);
    }
 
    /**
     * Move the circle horizontally.
     * @param distance the desired distance in pixels
     */
    public void moveHorizontal(int distance){
        erase();
        xPosition += distance;
        draw();
    }
 
    /**
     * Move the circle vertically.
     * @param distance the desired distance in pixels
     */
    public void moveVertical(int distance){
        erase();
        yPosition += distance;
        draw();
    }
 
    /**
     * Slowly move the circle horizontally.
     * @param distance the desired distance in pixels
     */
    public void slowMoveHorizontal(int distance){
        int delta;
 
        if(distance < 0) {
            delta = -1;
            distance = -distance;
        } else {
            delta = 1;
        }
 
        for(int i = 0; i < distance; i++){
            xPosition += delta;
            draw();
        }
    }
 
    /**
     * Slowly move the circle vertically
     * @param distance the desired distance in pixels
     */
    public void slowMoveVertical(int distance){
        int delta;
 
        if(distance < 0) {
            delta = -1;
            distance = -distance;
        }else {
            delta = 1;
        }
 
        for(int i = 0; i < distance; i++){
            yPosition += delta;
            draw();
        }
    }
 
    /**
     * Change the size.
     * @param newDiameter the new size (in pixels). Size must be >=0.
     */
    public void changeSize(int newDiameter){
        if (newDiameter >=0)
        erase();
        diameter = newDiameter;
        draw();
    }
 
    /**
     * Change the color. 
     * @param color the new color. Valid colors are "red", "yellow", "blue", "green",
     * "magenta" and "black".
     */
    public void changeColor(String newColor){
        color = newColor;
        draw();
    }
 
    /**
     * Return the area of the circle
     * @return the area of the circle
     */
    public double area(){
        double radio = diameter/2.0;
        return Math.PI * (radio * radio);
    }
    
    /**
     * Increases the area of ​​the circle by a percentage [0...100]).
     * @param percentage. Valid [0...100]
     */
    public void bigger(int percentage) {
        if(percentage >= 0 && percentage <= 100) {
            double areaActual = area();
            double nuevaArea = areaActual * (1 + (percentage / 100.0));
            double radio = Math.sqrt(nuevaArea / PI);
            double nuevoDiametro = 2 * radio;
            changeSize((int)Math.round(nuevoDiametro));
        }
    }
    
    /**
     * Decreases its size times times, until it reaches an area less than 
     * or equal to area
     * @param times. Valid > 1
     * @param area. Valid > 0
     */
    public void shrink(int times, double area) {
        if (times > 1 && area > 0) {
            for (int i = 0; i < times; i++) {
                double areaActual = area();
                double nuevoArea = areaActual / times;
                double radio = Math.sqrt(nuevoArea / PI);
                double nuevoDiametro = 2 * radio;
                changeSize((int)Math.round(nuevoDiametro));
            }
        }
    }
    
    /**
     * Increase its size times times, until it reaches an area more than 
     * or equal to actual area
     * @param times. Valid > 1
     * @param area. Valid > 0
     */
    public void enLarge(int times, double area) {
        if (times > 1 && area > 0) {
            for (int i = 0; i < times; i++) {
                double areaActual = area();
                double nuevaArea = areaActual * times;
                double radio = Math.sqrt(nuevaArea / PI);
                double nuevoDiametro = 2 * radio;
                changeSize((int)Math.round(nuevoDiametro));
            }
        }
    }
    
    /** 
     * Return the current color of the circle
     * @return the current color of the circle 
     */
    public String getColor() {
        return color;
    }  
}