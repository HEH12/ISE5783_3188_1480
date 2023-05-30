package geometries;
/**class RadialGeometry
 @author Hadas Holtzberg 326133188 and Zehavi Perla 326381480**/
public abstract class RadialGeometry extends Geometry {
    protected double radius;
    public RadialGeometry(double radius) {
        this.radius = radius;
    }
    public RadialGeometry(){this.radius = 0;}
}
