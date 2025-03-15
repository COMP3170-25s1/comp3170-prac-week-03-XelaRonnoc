package comp3170.week3;

import static org.lwjgl.opengl.GL11.glViewport;
import static org.lwjgl.opengl.GL41.*;

import org.joml.Matrix4f;
import org.joml.Vector4f;

import java.io.File;
import java.io.IOException;

import comp3170.OpenGLException;
import comp3170.IWindowListener;
import comp3170.Shader;
import comp3170.ShaderLibrary;
import comp3170.Window;

public class Week3 implements IWindowListener {

	
	final private String VERTEX_SHADER = "vertex.glsl"; 
	final private String FRAGMENT_SHADER = "fragment.glsl";
	
	private Window window;
	private Shader shader;
	
	final private File DIRECTORY = new File("src/comp3170/week3"); 
	
	private int width = 1000;
	private int height = 1000;
	private Vector4f clearColour = new Vector4f(1.0f, 1.0f, 1.0f, 1.0f);
	
	private Scene scene;
	private long oldTime;
	
	Matrix4f modelMatrix;
	
	public Week3() throws OpenGLException  {
		
		// create window with title, size, and a listener (this)
		Window window = new Window("Week 3", width, height, this);
		
		// sets the window as resizable
		window.setResizable(true);
		// start running the window
		window.run();
	}
	
	@Override
	public void init() {
		
		new ShaderLibrary(DIRECTORY);
		// set the background colour to white
		glClearColor(clearColour.x, clearColour.y, clearColour.z, clearColour.w);
		
		scene = new Scene();
		
		oldTime = System.currentTimeMillis();
		
	}


	@Override
	public void draw() {
		update();
        // clear the colour buffer
		glClear(GL_COLOR_BUFFER_BIT);	
		scene.draw();

	    
	}
	
	public void update() {
		long time = System.currentTimeMillis();
		float deltaTime = (time - oldTime) / 1000f;
		oldTime = time;
		scene.update(deltaTime);
	}

	@Override
	public void resize(int width, int height) {
		this.width = width;
		this.height = height;
		glViewport(0,0,width,height);
		
	}
	
	@Override
	public void close() {
		// TODO Auto-generated method stub
		
	}

	public static void main(String[] args) throws IOException, OpenGLException{
		new Week3();
	}

	
	
}
