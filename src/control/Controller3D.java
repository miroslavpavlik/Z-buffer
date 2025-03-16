package control;

import objects.Arrow;
import objects.Axis;
import objects.Cube;
import objects.Solid;
import raster.ImageBuffer;
import raster.ZBuffer;
import render.Renderer3D;
import transforms.*;
import view.Panel;

import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;
import java.util.List;

public class Controller3D implements Controller {

    private final Panel panel;
    private final ZBuffer buffer;
    private final List<Solid> solids;
    private Renderer3D renderer;

    private Mat4 modelMatrix;

    private Camera camera;
    private final double cameraSpeed = 1;
    private int lastMouseX, lastMouseY;
    private boolean isMousePressed = false;

    public Controller3D(Panel panel) {
        this.panel = panel;
        this.buffer = new ZBuffer(panel.getRaster());
        this.solids = new ArrayList<>();
        this.renderer = new Renderer3D(buffer);
        this.modelMatrix = new Mat4Identity();


        Cube cube = new Cube();
        Axis axis = new Axis();
        Arrow arrow = new Arrow();
        Vec3D e = new Vec3D(5, -10, 5);
        camera = new Camera()
                .withPosition(e)
                .withAzimuth(Math.toRadians(100))
                .withZenith(Math.toRadians(-15));




       // solids.add(cube);
        solids.add(axis);
        //solids.add(arrow);

        initObjects(panel.getRaster());
        initListeners(panel);
        redraw();
    }

    public void initObjects(ImageBuffer raster) {
        raster.setClearColor(new Col(0x16161D));
        buffer.clear();
        // TODO: vhodne dokoncit
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
                    case KeyEvent.VK_UP -> camera = camera.up(cameraSpeed);
                    case KeyEvent.VK_DOWN -> camera = camera.down(cameraSpeed);
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

                    double sensitivity = 0.01;
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
        double fov = Math.PI / 3;
        double aspect = (double) buffer.getImageBuffer().getWidth() / buffer.getImageBuffer().getHeight();
        double near = 0.5;
        double far = 50;
        Mat4 projectionMatrix = new Mat4PerspRH(fov, aspect, near, far);
        renderer.renderSolids(solids, modelMatrix, view, projectionMatrix);
        panel.repaint();
    }

    private void hardClear() {
        panel.clear();
    }

}
