package geometries;

import primitives.Point;
import primitives.Ray;

import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

public class Geometries implements Intersectable  {

    protected List<Intersectable> intersectables;


    /**
     * Default constructor
     */
    public Geometries(){
        intersectables = new LinkedList<>();
    }
    /**
     * Initialize the geometries based on the geometries received
     * @param geometries
     */
    public Geometries(Intersectable... geometries) {
       intersectables = new LinkedList<>();
       add(geometries);
   }

    /**
     * Add new geometries
     * @param geometries
     */
    public void add(Intersectable... geometries){
        Collections.addAll(intersectables, geometries);
    }

    /**
     * findIntersections find intersections between the geometries to ray
     * @param ray The Ray to intersect
     * @return list of point that intersections between the geometries to ray
     */
    @Override
    public List<Point> findIntersections(Ray ray) {
        if (intersectables.isEmpty()) return null; // if have no intersections
        List<Point> result = null;
        for (var item: intersectables) { //for all geometries in the list
            List<Point> intersections = item.findIntersections(ray);
            if(intersections!=null) {
                if(result==null) {
                    result=new LinkedList<Point>();
                }
                result.addAll(intersections);
            }
        }
        return result;
    }
}

