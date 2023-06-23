package lighting;

import primitives.Color;
import primitives.Point;
import primitives.Vector;

import java.util.LinkedList;
import java.util.List;
import java.util.Random;

import static java.lang.Math.sqrt;
import static primitives.Util.isZero;

public class PointLight extends Light implements LightSource {

    public Point position;
    public double kC;
    public double kL;
    public double kQ;
    private static final Random RND = new Random();

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
    /**
     * Creates a list of vectors from the given point to random points around the light within radius r
     *
     * @param p      the given point
     * @param r      the radius
     * @param amount the amount of vectors to create
     * @return list of vectors
     */
    @Override
    public List<Vector> getLCircle(Point p, double r, int amount) {
        if (p.equals(position))
            return null;

        List<Vector> result = new LinkedList<>();

        Vector l = getL(p); //vector to the center of the point light
        result.add(l);

        if (amount < 2) {
            return result;
        }

        Vector vAcross;
        if (isZero(l.getX()) && isZero(l.getY())) { //if l is parallel to z axis, then the normal is across z on x axis
            vAcross = new Vector(-1 * l.getZ(), 0, 0).normalize();
        } else { //otherwise get the normal using x and y
            vAcross = new Vector(-1 * l.getY(), l.getX(), 0).normalize();
        }
        Vector vForward = vAcross.crossProduct(l).normalize(); //the vector to the other direction

        double cosAngle, sinAngle, moveX, moveY, d;

        for (int i = 0; i < amount; i++) {
            Point movedPoint = this.position;

            cosAngle = 2 * RND.nextDouble() - 1; //random cosine of angle between (-1,1)
            sinAngle = sqrt(1 - cosAngle * cosAngle); //sin(angle)=1-cos^2(angle)

            d = r * (2 * RND.nextDouble() - 1); //d is between (-r,r)
            if (isZero(d)) { //if we got 0 then try again, because it will just be the same as the center
                i--;
                continue;
            }

            //says how much to move across and down
            moveX = d * cosAngle;
            moveY = d * sinAngle;

            //moving the point according to the value
            if (!isZero(moveX)) {
                movedPoint = movedPoint.add(vAcross.scale(moveX));
            }
            if (!isZero(moveY)) {
                movedPoint = movedPoint.add(vForward.scale(moveY));
            }

            result.add(p.subtract(movedPoint).normalize()); //adding the vector from the new point to the light position
        }
        return result;
    }
    @Override
    public double getDistance(Point point) {
        return position.distance(point);
    }

}