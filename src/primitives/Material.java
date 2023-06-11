package primitives;

public class Material {

    /**
     *  Kd - diffuse component, represents the scattering of light rays to all directions from the surface
     */
    private Double3 Kd = Double3.ZERO;

    /**
     *  Ks - specular component, represents the reflectance of the light source over the surface
     */
    private Double3 Ks = Double3.ZERO;

    /**
     *  Shininess - how shiny the material is
     */
    private int nShininess = 0;

    /**
     *  Kt - transparency component
     * 0.0 is opaque
     * 1.0 is clear
     */
    private Double3 Kt = Double3.ZERO;

    /**
     *  Kr - reflection component
     * 0.0 is matte
     * 1.0 is very reflexive
     */
    private Double3 Kr = Double3.ZERO;

    //*********Setters*********

    public Material setKs(double ks) {
        Ks = new Double3(ks);
        return this;
    }

    public Material setKd(double kd) {
        this.Kd = new Double3(kd);
        return this;
    }

    public Material setKs(Double3 ks) {
        Ks = ks;
        return this;
    }

    public Material setKd(Double3 kd) {
        this.Kd = kd;
        return this;
    }


    public Material setShininess(int nShininess) {
        this.nShininess = nShininess;
        return this;
    }

    public Material setKt(double kt) {
        this.Kt = new Double3(kt);
        return this;
    }

    public Material setKr(double kr) {
        this.Kr = new Double3(kr);
        return this;
    }

    public Material setKt(Double3 kt) {
        this.Kt = kt;
        return this;
    }

    public Material setKr(Double3 kr) {
        this.Kr = kr;
        return this;
    }

    public Double3 getKs() {
        return Ks;
    }

    public Double3 getKd() {
        return Kd;
    }

    //*********Getters*********

    public int getShininess() {
        return nShininess;
    }

    public Double3 getKt() {
        return Kt;
    }

    public Double3 getKr() {
        return Kr;
    }


}