package geometries;

import primitives.Point;
import primitives.Ray;

import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

public class Geometries implements Intersectable  {

    protected List<Intersectable> _intersectList;


    /**
     * Default constructor
     */
    public Geometries(){
        _intersectList = new LinkedList<>();
    }

    /**
     * Initialize the geometries based on the geometries received
     * @param geometries
     */
    public Geometries(Intersectable... geometries) {
        _intersectList = new LinkedList<>();
        add(geometries);
    }

    /**
     * Add new geometries
     * @param geometries
     */
    public void add(Intersectable... geometries){
        Collections.addAll(_intersectList, geometries);
    }

    /**
     * findIntersections find intersections between the geometries to ray
     * @param ray The Ray to intersect
     * @return list of point that intersections between the geometries to ray
     */
    @Override
    public List<Point> findIntersections(Ray ray) {
        if (_intersectList.isEmpty()) return null; // if have no intersections
        List<Point> result = null;
        for (var item: _intersectList) { //for all geometries in the list
            List<Point> itemList = item.findIntersections(ray);
            if(itemList!=null) {
                if(result==null) {
                    result=new LinkedList<Point>();
                }
                result.addAll(itemList);
            }
        }
        return result;
    }
}

