package geometries;

import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import primitives.Ray;

public class Geometries extends Intersectable {

    protected List<Intersectable> intersectList;


    /**
     * Default constructor
     */
    public Geometries() {
        intersectList = new LinkedList<>();
    }

    /**
     * Initialize the geometries based on the geometries received
     */
    public Geometries(Intersectable... geometries) {
        intersectList = new LinkedList<>();
        add(geometries);
    }

    /**
     * Add new geometries
     */
    public void add(Intersectable... geometries) {
        Collections.addAll(intersectList, geometries);
    }

    @Override
    protected List<GeoPoint> findGeoIntersectionsHelper(Ray ray, double maxDistance) {
        List<GeoPoint> result = null;
        for (Intersectable item : intersectList) {
            List<GeoPoint> itemList = item.findGeoIntersectionsHelper(ray, maxDistance);
            if (itemList != null) {
                if (result == null) {
                    result = new LinkedList<>();
                }
                result.addAll(itemList);
            }
        }
        return result;

    }


}

