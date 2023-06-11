package scene;
import geometries.Geometries;
import lighting.AmbientLight;
import lighting.LightSource;
import primitives.Color;

import java.util.LinkedList;
import java.util.List;

public class Scene {

    public String name;
    public Color background;
    public AmbientLight ambientLight = AmbientLight.NONE;

    public Geometries geometries;
    public List<LightSource> lights = new LinkedList<>();

    public Scene(String name) {
        this.name = name;
        geometries = new Geometries();
        background = Color.BLACK;
        lights = new LinkedList<>();
        ambientLight = new AmbientLight();
    }
    /**
     * getLights
     * @return Lights
     */
    public List<LightSource> getLights() {
        return lights;
    }

    public Scene setBackground(Color background) {
        this.background = background;
        return this;
    }

    public Scene setAmbientLight(AmbientLight ambientLight) {
        this.ambientLight = ambientLight;
        return this;
    }

    public Scene setGeometries(Geometries geometries) {
        this.geometries = geometries;
        return this;
    }

    public AmbientLight getAmbientLight() {
        return ambientLight;
    }

    public Geometries getGeometries() {
        return geometries;
    }

    public Color getBackground() {
        return background;
    }

    public Scene setLight(List<LightSource> lights) {
        this.lights = lights;
        return this;
    }

}