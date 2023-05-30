package lighting;

import primitives.Color;
import primitives.Double3;

public class AmbientLight extends Light {


    public static AmbientLight NONE = new AmbientLight(Color.BLACK, Double3.ZERO);


    /**
     * primary constructor
     * calls super constructor
     *
     * @param Ia Color intensity
     * @param Ka intensity factor
     */
    public AmbientLight(Color Ia, Double3 Ka) {
        super(Ia.scale(Ka));
    }


    /**
     * default constructor sets the intensity to black
     * calls super constructor
     */
    public AmbientLight() {
        super(Color.BLACK);
    }
}


