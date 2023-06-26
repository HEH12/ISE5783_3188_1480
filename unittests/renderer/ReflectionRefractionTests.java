
package renderer;

import static java.awt.Color.*;

import geometries.Plane;
import lighting.DirectionalLight;
import lighting.PointLight;
import org.junit.jupiter.api.Test;

import geometries.Sphere;
import geometries.Triangle;
import lighting.AmbientLight;
import lighting.SpotLight;
import primitives.*;
import renderer.*;
import scene.Scene;

/** Tests for reflection and transparency functionality, test for partial
 * shadows
 * (with transparency)
 * @author dzilb */
public class ReflectionRefractionTests {
    private Scene scene = new Scene("Test scene");

    /**
     * Produce a picture of a sphere lighted by a spot light
     */
    @Test
    public void twoSpheres() {
        Camera camera = new Camera(new Point(0, 0, 1000), new Vector(0, 0, -1), new Vector(0, 1, 0)) //
                .setVPSize(150, 150).setVPDistance(1000);

        scene.geometries.add(
                new Sphere(new Point(0, 0, -50), 50d).setEmission(new Color(BLUE))
                        .setMaterial(new Material().setKd(0.4).setKs(0.3).setShininess(100).setKt(0.3)),
                new Sphere(new Point(0, 0, -50), 25d).setEmission(new Color(RED))
                        .setMaterial(new Material().setKd(0.5).setKs(0.5).setShininess(100)));
        scene.lights.add( //
                new SpotLight(new Color(1000, 600, 0), new Point(-100, -100, 500), new Vector(-1, -1, -2)) //
                        .setKl(0.0004).setKq(0.0000006));

        camera.setImageWriter(new ImageWriter("refractionTwoSpheres", 500, 500)) //
                .setRayTracer(new RayTracerBasic(scene)) //
                .renderImage() //
                .writeToImage();
    }

    /**
     * Produce a picture of a sphere lighted by a spot light
     */
    @Test
    public void twoSpheresOnMirrors() {
        Camera camera = new Camera(new Point(0, 0, 10000), new Vector(0, 0, -1), new Vector(0, 1, 0)) //
                .setVPSize(2500, 2500).setVPDistance(10000); //

        scene.setAmbientLight(new AmbientLight(new Color(255, 255, 255), new Double3(0.1)));

        scene.geometries.add( //
                new Sphere(new Point(-950, -900, -1000), 400d).setEmission(new Color(0, 50, 100)) //
                        .setMaterial(new Material().setKd(0.25).setKs(0.25).setShininess(20)
                                .setKt(new Double3(0.5, 0, 0))),
                new Sphere(new Point(-950, -900, -1000), 200d).setEmission(new Color(100, 50, 20)) //
                        .setMaterial(new Material().setKd(0.25).setKs(0.25).setShininess(20)),
                new Triangle(new Point(1500, -1500, -1500), new Point(-1500, 1500, -1500),
                        new Point(670, 670, 3000)) //
                        .setEmission(new Color(20, 20, 20)) //
                        .setMaterial(new Material().setKr(1)),
                new Triangle(new Point(1500, -1500, -1500), new Point(-1500, 1500, -1500),
                        new Point(-1500, -1500, -2000)) //
                        .setEmission(new Color(20, 20, 20)) //
                        .setMaterial(new Material().setKr(new Double3(0.5, 0, 0.4))));

        scene.lights.add(new SpotLight(new Color(1020, 400, 400), new Point(-750, -750, -150), new Vector(-1, -1, -4)) //
                .setKl(0.00001).setKq(0.000005));

        ImageWriter imageWriter = new ImageWriter("reflectionTwoSpheresMirrored", 500, 500);
        camera.setImageWriter(imageWriter) //
                .setRayTracer(new RayTracerBasic(scene)) //
                .renderImage() //
                .writeToImage();
    }

    /**
     * Produce a picture of a two triangles lighted by a spot light with a
     * partially
     * transparent Sphere producing partial shadow
     */
    @Test
    public void trianglesTransparentSphere() {
        Camera camera = new Camera(new Point(0, 0, 1000), new Vector(0, 0, -1), new Vector(0, 1, 0)) //
                .setVPSize(200, 200).setVPDistance(1000);

        scene.setAmbientLight(new AmbientLight(new Color(WHITE), new Double3(0.15)));

        scene.geometries.add( //
                new Triangle(new Point(-150, -150, -115), new Point(150, -150, -135),
                        new Point(75, 75, -150)) //
                        .setMaterial(new Material().setKd(0.5).setKs(0.5).setShininess(60)), //
                new Triangle(new Point(-150, -150, -115), new Point(-70, 70, -140), new Point(75, 75, -150)) //
                        .setMaterial(new Material().setKd(0.5).setKs(0.5).setShininess(60)), //
                new Sphere(new Point(60, 50, -50), 30d).setEmission(new Color(BLUE)) //
                        .setMaterial(new Material().setKd(0.2).setKs(0.2).setShininess(30).setKt(0.6)));

        scene.lights.add(new SpotLight(new Color(700, 400, 400), new Point(60, 50, 0), new Vector(0, 0, -1)) //
                .setKl(4E-5).setKq(2E-7));

        ImageWriter imageWriter = new ImageWriter("refractionShadow", 600, 600);
        camera.setImageWriter(imageWriter) //
                .setRayTracer(new RayTracerBasic(scene)) //
                .renderImage() //
                .writeToImage();
    }

    @Test
    public void final_test() {
        Camera camera = new Camera(new Point(0, 0, -1000), new Vector(0, 0, 1), new Vector(0, 1, 0));
        camera.setVPDistance(1000).setVPSize(200, 200);

        Scene scene = new Scene("Test scene");
        scene.setBackground(new Color(java.awt.Color.LIGHT_GRAY));
        scene.setAmbientLight(new AmbientLight(new Color(java.awt.Color.WHITE), new Double3(0.15)));
        scene.geometries.add(
                new Sphere(new Point(0, 0, 50), 50) //
                        .setEmission(new Color(java.awt.Color.BLUE)) //
                        .setMaterial(new Material().setKd(0.5).setKs(0.5).setShininess(100).setKt(0.3)), //
                new Sphere(new Point(0, 0, 50), 25) //
                        .setEmission(new Color(java.awt.Color.RED)) //
                        .setMaterial(new Material().setKd(0.4).setKs(0.3).setShininess(100)), //
                new Triangle(new Point(1500, 1500, 1500), new Point(-1500, -1500, 1500), new Point(670, -670, -3000)) //
                        .setEmission(new Color(java.awt.Color.GRAY)) //
                        .setMaterial(new Material().setKr(1)) //
        );
        scene.lights.add(new SpotLight(new Color(700, 400, 400), new Point(60, -50, 0), new Vector(0, 0, 1)) //
                .setKl(4E-5).setKq(2E-7));
        scene.lights.add(new DirectionalLight(new Color(500, 300, 0), new Vector(1, -1, 1)));


        ImageWriter imageWriter = new ImageWriter("pictureLevel7", 600, 600);
        camera.setImageWriter(imageWriter) //
                .setRayTracer(new RayTracerBasic(scene)) //
                .renderImage() //
                .writeToImage();
    }

    @Test
    public void test() {
        Scene scene = new Scene("Mini Project Final");
        Camera camera = new Camera(new Point(0, 0, 1000), new Vector(0, 0, -1), new Vector(0, 1, 0)) //
                .setVPSize(200, 200).setVPDistance(1000);
        scene.geometries.add(
                //train pass-Rails

                new Triangle(new Point(-150, -70, -130), new Point(150, -70, -130), new Point(150, -75, -130)) //
                        .setMaterial(new Material().setKd(0.5).setKs(0.5).setShininess(60).setKt(0.24)).setEmission(new Color(150, 75, 0)),
                new Triangle(new Point(-150, -70, -130), new Point(-150, -75, -130), new Point(150, -75, -130)) //
                        .setMaterial(new Material().setKd(0.5).setKs(0.5).setShininess(60).setKt(0.24)).setEmission(new Color(150, 75, 0)),
                new Triangle(new Point(-150, -50, -100), new Point(150, -50, -100), new Point(150, -45, -100)) //
                        .setMaterial(new Material().setKd(0.5).setKs(0.5).setShininess(60).setKt(0.24)).setEmission(new Color(150, 75, 0)),
                new Triangle(new Point(-150, -50, -100), new Point(-150, -45, -100), new Point(150, -45, -100)) //
                        .setMaterial(new Material().setKd(0.5).setKs(0.5).setShininess(60).setKt(0.24)).setEmission(new Color(150, 75, 0)),
                new Triangle(new Point(-90, -70, -130), new Point(-85, -70, -130), new Point(-75, -50, -100)) //
                        .setMaterial(new Material().setKd(0.5).setKs(0.5).setShininess(60).setKt(0.24)).setEmission(new Color(150, 75, 0)),
                new Triangle(new Point(-90, -70, -130), new Point(-80, -50, -100), new Point(-75, -50, -100)) //
                        .setMaterial(new Material().setKd(0.5).setKs(0.5).setShininess(60).setKt(0.24)).setEmission(new Color(150, 75, 0)),
                new Triangle(new Point(-40, -70, -130), new Point(-35, -70, -130), new Point(-26, -50, -100)) //
                        .setMaterial(new Material().setKd(0.5).setKs(0.5).setShininess(60).setKt(0.24)).setEmission(new Color(150, 75, 0)),
                new Triangle(new Point(-40, -70, -130), new Point(-31, -50, -100), new Point(-26, -50, -100)) //
                        .setMaterial(new Material().setKd(0.5).setKs(0.5).setShininess(60).setKt(0.24)).setEmission(new Color(150, 75, 0)),
                new Triangle(new Point(10, -70, -130), new Point(15, -70, -130), new Point(21, -50, -100)) //
                        .setMaterial(new Material().setKd(0.5).setKs(0.5).setShininess(60).setKt(0.24)).setEmission(new Color(150, 75, 0)),
                new Triangle(new Point(10, -70, -130), new Point(16, -50, -100), new Point(21, -50, -100)) //
                        .setMaterial(new Material().setKd(0.5).setKs(0.5).setShininess(60).setKt(0.24)).setEmission(new Color(150, 75, 0)),
                new Triangle(new Point(60, -70, -130), new Point(65, -70, -130), new Point(71, -50, -100)) //
                        .setMaterial(new Material().setKd(0.5).setKs(0.5).setShininess(60).setKt(0.24)).setEmission(new Color(150, 75, 0)),
                new Triangle(new Point(60, -70, -130), new Point(66, -50, -100), new Point(71, -50, -100)) //
                        .setMaterial(new Material().setKd(0.5).setKs(0.5).setShininess(60).setKt(0.24)).setEmission(new Color(150, 75, 0)),
                //Wheels
                new Sphere(new Point(-60, -44, -80), 20) //
                        .setEmission(new Color(GRAY)) //
                        .setMaterial(new Material().setKd(0.2).setKs(0.2).setShininess(50).setKt(0)),
                new Sphere(new Point(-59, -44, -60), 10) //
                        .setEmission(new Color(BLACK)) //
                        .setMaterial(new Material().setKd(0.2).setKs(0.2).setShininess(50).setKt(0)),
                new Sphere(new Point(40, -40, -80), 25) //
                        .setEmission(new Color(GRAY)) //
                        .setMaterial(new Material().setKd(0.2).setKs(0.2).setShininess(50).setKt(0)),
                new Sphere(new Point(39, -40, -60), 12.5) //
                        .setEmission(new Color(BLACK)) //
                        .setMaterial(new Material().setKd(0.2).setKs(0.2).setShininess(50).setKt(0)),


                //red box
                new Triangle(new Point(-90, -44, -90), new Point(70, -44, -90), new Point(70, 10, -90)) //
                        .setMaterial(new Material().setKd(0.5).setKs(0.5).setShininess(60).setKt(0)).setEmission(new Color(RED)),
                new Triangle(new Point(-90, -44, -90), new Point(-90, 10, -90), new Point(70, 10, -90)) //
                        .setMaterial(new Material().setKd(0.5).setKs(0.5).setShininess(60).setKt(0)).setEmission(new Color(RED)),
                new Triangle(new Point(70, -44, -90), new Point(80, -34, -110), new Point(70, 10, -100)) //
                        .setMaterial(new Material().setKd(0.5).setKs(0.5).setShininess(60).setKt(0)).setEmission(new Color(RED)),
                new Triangle(new Point(70, 10, -100), new Point(80, -34, -110), new Point(80, 20, -110)) //
                        .setMaterial(new Material().setKd(0.5).setKs(0.5).setShininess(60).setKt(0)).setEmission(new Color(RED)),
                new Triangle(new Point(-90, 10, -90), new Point(70, 10, -100), new Point(80, 20, -110)) //
                        .setMaterial(new Material().setKd(0.5).setKs(0.5).setShininess(60).setKt(0)).setEmission(new Color(RED)),
                new Triangle(new Point(-90, 10, -90), new Point(-80, 20, -110), new Point(80, 20, -110)) //
                        .setMaterial(new Material().setKd(0.5).setKs(0.5).setShininess(60).setKt(0)).setEmission(new Color(RED)),

                // blue box
                new Triangle(new Point(70, 10, -100), new Point(80, 20, -110), new Point(70, 50, -100)) //
                        .setMaterial(new Material().setKd(0.5).setKs(0.5).setShininess(60).setKt(0)).setEmission(new Color(50, 0, 100)),
                new Triangle(new Point(80, 60, -110), new Point(80, 20, -110), new Point(70, 50, -100)) //
                        .setMaterial(new Material().setKd(0.5).setKs(0.5).setShininess(60).setKt(0)).setEmission(new Color(50, 0, 100)),
                new Triangle(new Point(80, 60, -110), new Point(0 - 20, 50, -100), new Point(70, 50, -100)) //
                        .setMaterial(new Material().setKd(0.5).setKs(0.5).setShininess(60).setKt(0)).setEmission(new Color(50, 0, 100)),
                new Triangle(new Point(80, 60, -110), new Point(-20, 50, -100), new Point(-10, 60, -110)) //
                        .setMaterial(new Material().setKd(0.5).setKs(0.5).setShininess(60).setKt(0)).setEmission(new Color(50, 0, 100)),
                new Triangle(new Point(70, 10, -100), new Point(70, 50, -100), new Point(-20, 10, -90)) //
                        .setMaterial(new Material().setKd(0.5).setKs(0.5).setShininess(60).setKt(0)).setEmission(new Color(50, 0, 100)),
                new Triangle(new Point(-20, 10, -90), new Point(70, 50, -100), new Point(-20, 50, -90)) //
                        .setMaterial(new Material().setKd(0.5).setKs(0.5).setShininess(60).setKt(0)).setEmission(new Color(50, 0, 100)),

                //steam
                new Triangle(new Point(-70, -5, -100), new Point(-50, -5, -100), new Point(-70, 50, -100)) //
                        .setMaterial(new Material().setKd(0.5).setKs(0.5).setShininess(60).setKt(0)).setEmission(new Color(PINK)),
                new Triangle(new Point(-70, 50, -100), new Point(-50, -5, -100), new Point(-50, 50, -100)) //
                        .setMaterial(new Material().setKd(0.5).setKs(0.5).setShininess(60).setKt(0)).setEmission(new Color(PINK)),
                new Triangle(new Point(-50, 0, -100), new Point(-50, 50, -100), new Point(-45, 55, -105)) //
                        .setMaterial(new Material().setKd(0.5).setKs(0.5).setShininess(60).setKt(0)).setEmission(new Color(PINK)),
                new Triangle(new Point(-45, 55, -105), new Point(-50, 0, -100), new Point(-45, 15, -110)) //
                        .setMaterial(new Material().setKd(0.5).setKs(0.5).setShininess(60).setKt(0)).setEmission(new Color(PINK)),
                new Triangle(new Point(-70, 50, -100), new Point(-50, 50, -100), new Point(-45, 55, -105)) //
                        .setMaterial(new Material().setKd(0.5).setKs(0.5).setShininess(60).setKt(0)).setEmission(new Color(PINK)),
                new Triangle(new Point(-70, 50, -100), new Point(-65, 55, -105), new Point(-45, 55, -105)) //
                        .setMaterial(new Material().setKd(0.5).setKs(0.5).setShininess(60).setKt(0)).setEmission(new Color(PINK)),
                //Smoke cloud
                new Sphere(new Point(-55, 57, -106), 9) //
                        .setEmission(new Color(GRAY)) //
                        .setMaterial(new Material().setShininess(50).setKt(0.4)),
                new Sphere(new Point(-45, 65, -110), 8) //
                        .setEmission(new Color(GRAY)) //
                        .setMaterial(new Material().setKd(0.2).setKs(0.2).setShininess(50).setKt(0.4)),
                new Sphere(new Point(-33, 73, -110), 13) //
                        .setEmission(new Color(GRAY)) //
                        .setMaterial(new Material().setKd(0.2).setKs(0.2).setShininess(50).setKt(0.4)),


                //windows
                new Triangle(new Point(-5, 40, -80), new Point(17.5, 40, -80), new Point(17.5, 20, -80)) //
                        .setMaterial(new Material().setKd(0.5).setKs(0.5).setShininess(60).setKt(0.24)).setEmission(new Color(0, 200, 200)),
                new Triangle(new Point(-5, 40, -80), new Point(-5, 20, -80), new Point(17.5, 20, -80)) //
                        .setMaterial(new Material().setKd(0.5).setKs(0.5).setShininess(60).setKt(0.24)).setEmission(new Color(0, 200, 200)),
                new Triangle(new Point(32.5, 40, -80), new Point(55, 40, -80), new Point(55, 20, -80)) //
                        .setMaterial(new Material().setKd(0.5).setKs(0.5).setShininess(60).setKt(0.24)).setEmission(new Color(0, 200, 200)),
                new Triangle(new Point(32.5, 40, -80), new Point(32.5, 20, -80), new Point(55, 20, -80)) //
                        .setMaterial(new Material().setKd(0.5).setKs(0.5).setShininess(60).setKt(0.24)).setEmission(new Color(0, 200, 200)),


                //Stick
                new Triangle(new Point(-59, -45, -50), new Point(-59, -39, -50), new Point(45, -42, -50)) //
                        .setMaterial(new Material().setKd(0.5).setKs(0.5).setShininess(60).setKt(0)).setEmission(new Color(YELLOW)),
                new Triangle(new Point(-59, -39, -50), new Point(45, -36, -50), new Point(45, -42, -50)) //
                        .setMaterial(new Material().setKd(0.5).setKs(0.5).setShininess(60).setKt(0)).setEmission(new Color(YELLOW)),
                new Plane(new Point(-150, -150, -250), new Point(-100, 100, -250), new Point(75, 75, -250))
                        .setMaterial(new Material().setKd(0.5).setKs(0.5).setShininess(20).setKt(0.5)).setEmission(new Color(gray)),
                new Plane(new Point(-100, -100, -100), new Point(100, -150, -10), new Point(0, -50, -200))
                        .setMaterial(new Material().setKd(0.5).setKs(0.5).setShininess(20).setKr(0.1)).setEmission(new Color(BLACK))
        );

        scene.lights.add(new PointLight(new Color(WHITE), new Point(-91, 0, -80)) //
                .setKl(0.001).setKq(0.0005));
        scene.lights.add(new PointLight(new Color(100, 40, 80), new Point(-100, 150, 0)) //
                .setKl(4E-5).setKq(2E-8));


        ImageWriter imageWriter = new ImageWriter("Mini2", 1000, 1000);
                camera.setImageWriter(imageWriter) //
                .setRayTracer(new RayTracerBasic(scene))//.useSoftShadow(true).setNumOfSSRays(50).setRadiusBeamSS(10.0D))//.setMultithreading(10)

        .renderImage()
        .writeToImage();



    }
        @Test
        public void test2()
        {
            Scene scene = new Scene("test909");
            Camera camera = new Camera(new Point(0, 0, 1000), new Vector(0, 0, -1), new Vector(0, 1, 0));
            camera.setVPDistance(1500);

            camera.setVPSize(200, 250);//.useAntiAliasing(true)
                   // .setNumOfAARays(15).useAdaptive(true)
                   // .setMultithreading(10);
            scene.setAmbientLight(new AmbientLight(new Color(255, 255, 255), new Double3(0)));
            scene.setBackground(Color.BLACK);


            Plane plane = new Plane(new Point(0, 0, -300), new Vector(0, 0, 1));

            double i = 15, j = 3;
            for (int x = -90, y = 140; x < 0; x += j, y -= i, i += 1.5, j += 0.2) {
                scene.lights.add(new SpotLight(new Color(400, 300, 300), new Point(x, y, 150), camera.getvTo()).setKc(1).setKl(0.005).setKq(0.0005));
            }
            i = 15;
            j = -3;
            for (int x = 90, y = 140; x > 0; x += j, y -= i, i += 1.5, j -= 0.2) {
                scene.lights.add(new SpotLight(new Color(400, 300, 300), new Point(x, y, 150), camera.getvTo()).setKc(1).setKl(0.005).setKq(0.0005));
            }

            //DUBI
            scene.geometries.add(

                    new Sphere(new Point(4, 8, 18), 25).setEmission(new Color(77, 38, 0)).setMaterial(new Material().setKd(0.3).setKs(0.7).setShininess(100).setKt(0.5).setKr(0)),

                    new Sphere(new Point(26, -11, 18), 8).setEmission(new Color(77, 38, 0)).setMaterial(new Material().setKd(0.3).setKs(0.7).setShininess(100).setKt(0.5).setKr(0)),

                    new Sphere(new Point(-14, -12, 18), 8).setEmission(new Color(77, 38, 0)).setMaterial(new Material().setKd(0.3).setKs(0.7).setShininess(100).setKt(0.5).setKr(0)),

                    new Sphere(new Point(-21, 16, 18), 8).setEmission(new Color(77, 38, 0)).setMaterial(new Material().setKd(0.3).setKs(0.7).setShininess(100).setKt(0.5).setKr(0)),

                    new Sphere(new Point(27, 16, 18), 8).setEmission(new Color(77, 38, 0)).setMaterial(new Material().setKd(0.3).setKs(0.7).setShininess(100).setKt(0.5).setKr(0)),
                    new Sphere(new Point(3, 39, 18), 18).setEmission(new Color(77, 38, 0)).setMaterial(new Material().setKd(0.3).setKs(0.7).setShininess(100).setKt(0.5).setKr(0)),
                    new Sphere(new Point(11, 43, 35.71), 2.2).setEmission(new Color(BLACK)).setMaterial(new Material().setKd(0.3).setKs(0.7).setShininess(100).setKt(0).setKr(0)),
                    new Sphere(new Point(-4, 43, 35.94), 2.2).setEmission(new Color(BLACK)).setMaterial(new Material().setKd(0.3).setKs(0.7).setShininess(100).setKt(0).setKr(0)),
                    //nose
                    new Sphere(new Point(2.699, 37.57, 37.94), 2.5).setEmission(new Color(gray)).setMaterial(new Material().setKd(0.3).setKs(0.7).setShininess(100).setKt(0).setKr(0)),

                    new Sphere(new Point(-8, 58, 18), 7).setEmission(new Color(77, 38, 0)).setMaterial(new Material().setKd(0.3).setKs(0.7).setShininess(100).setKt(0.5).setKr(0)),
                    new Sphere(new Point(14, 58, 18), 7).setEmission(new Color(77, 38, 0)).setMaterial(new Material().setKd(0.3).setKs(0.7).setShininess(100).setKt(0.5).setKr(0))

            );

            scene.geometries.add(plane.setEmission(new Color(255, 153, 153)).setMaterial(new Material().setKd(0.5).setKs(0.5).setShininess(1200).setKt(0).setKr(0)));
            /////////////mirror
            scene.geometries.add(new Plane(new Point(0, -20, 0), new Vector(0, -40, 0)).setEmission(new Color(0, 40, 60)).setMaterial(new Material().setKr(1)));


            DirectionalLight direction_light = new DirectionalLight(new Color(0, 0, 1), new Vector(1, -1, 1));
            SpotLight spot_light = new SpotLight(new Color(0, 1, 0), new Point(4, 8, 18), new Vector(0, 0, 1));//1, 4E-4, 2E-5,;
            PointLight point_light = new PointLight(new Color(400, 300, 300), new Point(0, 145, 50));
            //on mirror

            ImageWriter imageWriter = new ImageWriter("minip1", 1000, 1000);

            scene.lights.add(point_light.setKc(1).setKl(0.05).setKq(0.00005));
            scene.lights.add(direction_light);
            scene.lights.add(spot_light.setKc(1).setKl(4E-4).setKq(2E-5));


            camera.setImageWriter(imageWriter) //
                    .setRayTracer(new RayTracerBasic(scene))
            .renderImage()
            .writeToImage();


        }
    }