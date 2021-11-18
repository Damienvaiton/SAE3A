package fr.dut.ptut2021.models;

import java.util.ArrayList;


public class Symbol {

    private ArrayList<Point> points;
    private int tolerance;

    public Symbol(){
        this.points = new ArrayList<>();
        this.tolerance = 10;
    }

    public Symbol(int tolerance){
        this.points = new ArrayList<>();
        this.tolerance = tolerance;
    }

    private int findIdOfNearestPoint(Point point){
        int idNearestPoint;
        if(points.size() == 0){
            return -1;
        }else {
            int dist = (int) Math.sqrt(Math.pow(point.getX() - points.get(0).getX(), 2) + Math.pow(point.getY() - points.get(0).getY(), 2));
            idNearestPoint = 0;
            for (int i = 0; i < points.size(); i++) {
                if ((int) Math.sqrt(Math.pow(point.getX() - points.get(i).getX(), 2) + Math.pow(point.getY() - points.get(i).getY(), 2)) <= dist) {
                    idNearestPoint = i;
                }
            }
        }
        return idNearestPoint;
    }

    
    public int findIdOfSecondNearestPoint(Point point){
        int idNearestPoint = findIdOfNearestPoint(point);
        if(points.size() <= 1){
            return -1;
        }else {
            if(Math.sqrt(Math.pow(point.getX() - points.get(idNearestPoint + 1).getX(), 2) + Math.pow(point.getY() - points.get(idNearestPoint + 1).getY(), 2)) < Math.sqrt(Math.pow(point.getX() - points.get(idNearestPoint - 1).getX(), 2) + Math.pow(point.getY() - points.get(idNearestPoint - 1).getY(), 2))){
                return idNearestPoint + 1;
            }else{
                return idNearestPoint - 1;
            }
        }
    }

    //p1 et p2 sont les limites de la zone
    private boolean isInArea(Point point, Point p1, Point p2){
        Point test;
        test = new Point(p1);
        int coef = (p1.getX() - p2.getX()) / (p1.getY() - p2.getY());

        if(p1.getY() - p2.getY() < 0){
            for(int i = p1.getY() - p2.getY(); i <= 0; i--){
                if(Math.sqrt(Math.pow((test.getX() + i * (coef)) - point.getX(), 2)+Math.pow(test.getY() - point.getY(), 2)) <= tolerance){
                    return true;
                }
            }
        }
        else{
            for(int i = 0; i <= p1.getY() - p2.getY(); i++){
                if(Math.sqrt(Math.pow((test.getX() + i * (coef)) - point.getX(), 2)+Math.pow(test.getY() - point.getY(), 2)) <= tolerance){
                    return true;
                }
            }
        }
        return false;
    }

    public boolean isInSymbol(Point point){

        int idNearestPoint = findIdOfNearestPoint(point);
        if(isInArea(point, points.get(idNearestPoint - 1), points.get(idNearestPoint))){
            return true;
        }
        else if(isInArea(point, points.get(idNearestPoint), points.get(idNearestPoint + 1))){
            return true;
        }
        else{
            return false;
        }
    }
}
