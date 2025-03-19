package control;

import objects.*;
import raster.ImageBuffer;
import raster.ZBuffer;
import render.Renderer3D;
import shade.FlatColor;
import shade.SmoothColor;
import transforms.*;
import view.Panel;

import javax.swing.*;
import java.awt.event.*;
import java.util.ArrayList;
import java.util.List;

public class Controller3D implements Controller {

    private final Panel panel;
    private ZBuffer buffer;
    private final List<Solid> solids = new ArrayList<>();
    private Renderer3D renderer;
    private boolean isPerspective = true;
    private boolean wiredModel = false;
    private Solid selectedSolid;
    private Camera camera;
    private final double cameraSpeed = 0.5;
    private int lastMouseX, lastMouseY;
    private boolean isMousePressed = false;

    private Solid animatedCube;
    private Timer animationTimer;
    private boolean isAnimating = false;

    public Controller3D(Panel panel) {
        this.panel = panel;
        initObjects(panel.getRaster());
        initListeners(panel);
        startAnimation();
        redraw();
    }

    public void initObjects(ImageBuffer raster) {
        raster.setClearColor(new Col(0x16161D));

        Axis axis = new Axis();
        Cuboid cuboid = new Cuboid();
        Cube cube = new Cube();
        Pyramid pyramid = new Pyramid();
        BezierSurface surface = new BezierSurface();
        animatedCube = new Cube();

        Vec3D e = new Vec3D(10, 10, 5);
        camera = new Camera()
                .withPosition(e)
                .withAzimuth(Math.toRadians(-120))
                .withZenith(Math.toRadians(-20));


        animatedCube.translate(new Vec3D(0, 0, 0));

        solids.add(axis);
        solids.add(cube);
        solids.add(cuboid);
        solids.add(pyramid);
        solids.add(surface);
        solids.add(animatedCube);
        buffer = new ZBuffer(panel.getRaster());

        buffer.clear();
        selectedSolid = cube;
        renderer = new Renderer3D(buffer);
    }

    private void startAnimation() {
        animationTimer = new Timer(50, e -> animateObject()); //50 ms = 20 FPS
        animationTimer.start();
        isAnimating = true;
    }

    private void toggleAnimation() {
        if (isAnimating) {
            animationTimer.stop();
        } else {
            animationTimer.start();
        }
        isAnimating = !isAnimating;
    }

    private void animateObject() {
        animatedCube.rotate(0, 0, Math.toRadians(5));
        redraw();
    }

    @Override
    public void initListeners(Panel panel) {
        panel.addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                switch (e.getKeyCode()) {
                    case KeyEvent.VK_W -> camera = camera.forward(cameraSpeed);
                    case KeyEvent.VK_S -> camera = camera.backward(cameraSpeed);
                    case KeyEvent.VK_A -> camera = camera.left(cameraSpeed);
                    case KeyEvent.VK_D -> camera = camera.right(cameraSpeed);
                    case KeyEvent.VK_C -> hardClear();
                    case KeyEvent.VK_P -> isPerspective = !isPerspective;
                    case KeyEvent.VK_F -> wiredModel = !wiredModel;
                    case KeyEvent.VK_1 -> selectedSolid = solids.get(1);
                    case KeyEvent.VK_2 -> selectedSolid = solids.get(2);
                    case KeyEvent.VK_3 -> selectedSolid = solids.get(3);
                    case KeyEvent.VK_4 -> selectedSolid = solids.get(4);
                    case KeyEvent.VK_X -> selectedSolid.rotate(Math.toRadians(10), 0, 0);
                    case KeyEvent.VK_Y -> selectedSolid.rotate(0, Math.toRadians(10), 0);
                    case KeyEvent.VK_Z -> selectedSolid.rotate(0, 0, Math.toRadians(10));
                    case KeyEvent.VK_UP -> selectedSolid.translate(new Vec3D(0, 0, 1));
                    case KeyEvent.VK_DOWN -> selectedSolid.translate(new Vec3D(0, 0, -1));
                    case KeyEvent.VK_LEFT -> selectedSolid.translate(new Vec3D(1, 0, 0));
                    case KeyEvent.VK_RIGHT -> selectedSolid.translate(new Vec3D(-1, 0, 0));
                    case KeyEvent.VK_M -> selectedSolid.scale(1.1, 1.1, 1.1);
                    case KeyEvent.VK_N -> selectedSolid.scale(0.9, 0.9, 0.9);
                    case KeyEvent.VK_B -> renderer.setShader(new SmoothColor());
                    case KeyEvent.VK_V -> renderer.setShader(new FlatColor());
                    case KeyEvent.VK_R -> toggleAnimation();
                }
                redraw();
            }
        });

        panel.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                if (e.getButton() == MouseEvent.BUTTON1) {
                    isMousePressed = true;
                    lastMouseX = e.getX();
                    lastMouseY = e.getY();
                }
            }

            @Override
            public void mouseReleased(MouseEvent e) {
                if (e.getButton() == MouseEvent.BUTTON1) {
                    isMousePressed = false;
                }
            }
        });

        panel.addMouseMotionListener(new MouseMotionAdapter() {
            @Override
            public void mouseDragged(MouseEvent e) {
                if (isMousePressed) {
                    int deltaX = e.getX() - lastMouseX;
                    int deltaY = e.getY() - lastMouseY;
                    lastMouseX = e.getX();
                    lastMouseY = e.getY();

                    double sensitivity = 0.005;
                    camera = camera.addAzimuth(deltaX * sensitivity);
                    camera = camera.addZenith(deltaY * sensitivity);

                    redraw();
                }
            }
        });

        panel.addComponentListener(new ComponentAdapter() {
            @Override
            public void componentResized(ComponentEvent e) {
                panel.resize();
                initObjects(panel.getRaster());
            }
        });
    }

    private void redraw() {
        buffer.clear();
        Mat4 view = camera.getViewMatrix();
        Mat4 projectionMatrix;
        if (isPerspective) {
            double fov = Math.PI / 3;
            double aspect = buffer.getImageBuffer().getHeight() / (float) buffer.getImageBuffer().getWidth();
            double near = 0.5;
            double far = 50;
            projectionMatrix = new Mat4PerspRH(fov, aspect, near, far);
        } else {
            double width = 20;
            double height = width * buffer.getImageBuffer().getHeight() / (float) buffer.getImageBuffer().getWidth();
            double near = 0.5;
            double far = 50;
            projectionMatrix = new Mat4OrthoRH(width, height, near, far);
        }

        renderer.renderSolids(solids, view, projectionMatrix, wiredModel);
        panel.repaint();
    }

    private void hardClear() {
        buffer.clear();
        panel.clear();
    }
}
