package primitives;

/**
 * Class represents the material of a Geometry
 */
public class Material {
    /**
     * Field represents the diffusive attenuation factor
     */
    public Double3 kD = Double3.ZERO;
    /**
     * Field represents the specular attenuation factor
     */
    public Double3 kS = Double3.ZERO;
    /**
     * Field represents the shininess of the material
     */
    public int nShininess = 0;
    /**
     * Field represents the transparency attenuation factor
     */
    public Double3 kT = Double3.ZERO;
    /**
     * Field represents the reflectivity attenuation factor
     */
    public Double3 kR = Double3.ZERO;
    /**
     * Field represents glossiness attenuation factor
     */
    public double kG = 1;

    /**
     * Builder patterns setter for field kD
     *
     * @param kD parameter for kD
     * @return Material object
     */
    public Material setKd(Double3 kD) {
        this.kD = kD;
        return this;
    }

    /**
     * Builder patterns setter for field kD
     *
     * @param value parameter for kD constructor
     * @return Material object
     */
    public Material setKd(double value) {
        this.kD = new Double3(value);
        return this;
    }

    /**
     * Builder patterns setter for field kS
     *
     * @param kS parameter for kS
     * @return Material object
     */
    public Material setKs(Double3 kS) {
        this.kS = kS;
        return this;
    }

    /**
     * Builder patterns setter for field kS
     *
     * @param value parameter for kS constructor
     * @return Material object
     */
    public Material setKs(double value) {
        this.kS = new Double3(value);
        return this;
    }

    /**
     * Builder patterns setter for field nShininess
     *
     * @param nShininess parameter for nShininess
     * @return Material object
     */
    public Material setShininess(int nShininess) {
        this.nShininess = nShininess;
        return this;
    }

    /**
     * Builder patterns setter for field kR
     *
     * @param kR parameter for kT constructor
     * @return Material object
     */
    public Material setKr(Double3 kR) {
        this.kR = kR;
        return this;
    }

    /**
     * Builder patterns setter for field kR
     *
     * @param value parameter for kR constructor
     * @return Material object
     */
    public Material setKr(double value) {
        this.kR = new Double3(value);
        return this;
    }

    /**
     * Builder patterns setter for field kT
     *
     * @param kT parameter for kT constructor
     * @return Material object
     */
    public Material setKt(Double3 kT) {
        this.kT = kT;
        return this;
    }

    /**
     * Builder patterns setter for field kT
     *
     * @param value parameter for kT constructor
     * @return Material object
     */
    public Material setKt(double value) {
        this.kT = new Double3(value);
        return this;
    }

    /**
     * Builder patterns setter for field kG
     *
     * @param value parameter for kG constructor
     * @return Material object
     */
    public Material setKg(double value) {
        this.kG = value;
        return this;
    }
}
