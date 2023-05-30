package geometries;
/**class geometries is the class representing to be an interface to our geometric shapes
 @author Hadas Holtzberg 326133188 and Zehavi Perla 326381480**/

import primitives.Color;
import primitives.Material;
import primitives.Point;
import primitives.Vector;


 public abstract class Geometry extends Intersectable {
     private Material material;
     public Geometry() {
         this.emission = Color.BLACK;
         this.material = new Material();
     }

     /**
      *get Material
      */
     public Material getMaterial() {
         return material;
     }

     /**
      *set Material
      */
     public Geometry setMaterial(Material material) {
         this.material = material;
         return this;
     }

     /**
      * get Emission
      * @return light emission of the geometry
      */
     public Color getEmission() {
         return emission;
     }
     /**
      *set Emission
      */
     public Geometry setEmission(Color emission) {
         this.emission = emission;
         return this;
     }
     protected Color emission;

     /**
      * @param point
      * @return normal from the point to geometrries
      * @return normal from the point to geometries
      */
    public abstract Vector getNormal(Point point);

}
