package lighting;

import primitives.Color;

/**
 * Abstract class represents light
 */
abstract class Light {
    // Field represents intensity in color
    private Color intensity;

    /**
     * Constructor for Light
     * @param intensity parameter for field intensity
     */
    protected Light(Color intensity) {
        this.intensity = intensity;
    }

    /**
     * Getter for intensity
     * @return intensity field
     */
    public Color getIntensity() {
        return intensity;
    }
}

