package lighting;

import primitives.Color;
import primitives.Point;
import primitives.Vector;

public class PointLight extends Light implements LightSource {

    public Point position;
    public double kC;
    public double kL;
    public double kQ;

    /**
     * constructor
     */
    public PointLight(Color intensity, Point position) {
        super(intensity);
        this.position = position;
        this.kC = 1d;
        this.kL = 0d;
        this.kQ = 0d;
    }

    /**
     * get Intensity
     * @param p point
     * @return Intensity of the light
     */
    public Color getIntensity(Point p) {
        double d = p.distance(position);
        double d2 = d * d;

        double calc = (kC + d * kL + d2 * kQ);
        return getIntensity().reduce(calc);
    }

    /**
     * get light
     * @param p
     * @return point's super
     */
    @Override
    public Vector getL(Point p) {
        return p.subtract(position).normalize();
    }

    /**
     * set Kc
     * @param kC one of factors for attenuation with distance (d)
     * @return Kc
     */
    public PointLight setKc(double kC) {
        this.kC = kC;
        return this;
    }

    /**
     * set Kl
     * @param kL one of factors for attenuation with distance (d)
     * @return Kl
     */
    public PointLight setKl(double kL) {
        this.kL = kL;
        return this;
    }

    /**
     * set Kq
     * @param kQ one of factors for attenuation with distance (d)
     * @return Kq
     */
    public PointLight setKq(double kQ) {
        this.kQ = kQ;
        return this;
    }
}