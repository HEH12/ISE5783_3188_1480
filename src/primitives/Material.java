package primitives;

public class Material {

    public Double3 kD;
    public Double3 kS;
    public int nShininess; //the strong of the shininess

    /**
     * constructor
     */
    public Material() {
        kS = Double3.ZERO;
        kD = Double3.ZERO;
        nShininess = 0;
    }

    /**
     * set Kd that get Double3
     * @param kD
     * @return Kd
     */
    public Material setKd(Double3 kD) {
        this.kD = kD;
        return this;
    }

    /**
     * set Kd that get double
     * @param kD
     * @return kd
     */
    public Material setKd(double kD) {
        this.kD = new Double3(kD);
        return this;
    }


    /**
     * set Ks that get Double3
     * @param kS
     * @return Ks
     */
    public Material setKs(Double3 kS) {
        this.kS = kS;
        return this;
    }

    /**
     * set Ks that get double
     * @param kS
     * @return
     */
    public Material setKs(double kS) {
        this.kS = new Double3(kS);
        return this;
    }


    /**
     * set Shininess
     * @param nShininess
     * @return Shininess
     */
    public Material setShininess(int nShininess) {
        this.nShininess = nShininess;
        return this;
    }

}