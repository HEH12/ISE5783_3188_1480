package geometries;
/**class RadialGeometry
 @author Hadas Holtzberg 326133188 and Zehavi Perla 326381480**/
public abstract class RadialGeometry implements Geometry {
    protected double radius;
    public RadialGeometry(double radius) {
        this.radius = radius;
    }
}
