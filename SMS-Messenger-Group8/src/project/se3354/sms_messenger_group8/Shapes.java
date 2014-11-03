
public class Shapes {
	public class Triangle {

	    private FloatBuffer vertexBuffer;

	    // number of coordinates per vertex in this array
	    static final int COORDS_PER_VERTEX = 3;
	    static float triangleCoords[] = {   // in counterclockwise order:
	             0.0f,  0.622008459f, 0.0f, // top
	            -0.5f, -0.311004243f, 0.0f, // bottom left
	             0.5f, -0.311004243f, 0.0f  // bottom right
	    };

	    // Set color with red, green, blue and alpha (opacity) values
	    float color[] = { 0.63671875f, 0.76953125f, 0.22265625f, 1.0f };

	    public Triangle() {
	        // initialize vertex byte buffer for shape coordinates
	        ByteBuffer bb = ByteBuffer.allocateDirect(
	                // (number of coordinate values * 4 bytes per float)
	                triangleCoords.length * 4);
	        // use the device hardware's native byte order
	        bb.order(ByteOrder.nativeOrder());

	        // create a floating point buffer from the ByteBuffer
	        vertexBuffer = bb.asFloatBuffer();
	        // add the coordinates to the FloatBuffer
	        vertexBuffer.put(triangleCoords);
	        // set the buffer to read the first coordinate
	        vertexBuffer.position(0);
	    }
	}
	
	public class Square extends Shapes{

	    private FloatBuffer vertexBuffer;
	    private ShortBuffer drawListBuffer;

	    // number of coordinates per vertex in this array
	    static final int COORDS_PER_VERTEX = 3;
	    static float squareCoords[] = {
	            -0.5f,  0.5f, 0.0f,   // top left
	            -0.5f, -0.5f, 0.0f,   // bottom left
	             0.5f, -0.5f, 0.0f,   // bottom right
	             0.5f,  0.5f, 0.0f }; // top right

	    private short drawOrder[] = { 0, 1, 2, 0, 2, 3 }; // order to draw vertices

	    public Square() {
	        // initialize vertex byte buffer for shape coordinates
	        ByteBuffer bb = ByteBuffer.allocateDirect(
	        // (# of coordinate values * 4 bytes per float)
	                squareCoords.length * 4);
	        bb.order(ByteOrder.nativeOrder());
	        vertexBuffer = bb.asFloatBuffer();
	        vertexBuffer.put(squareCoords);
	        vertexBuffer.position(0);

	        // initialize byte buffer for the draw list
	        ByteBuffer dlb = ByteBuffer.allocateDirect(
	        // (# of coordinate values * 2 bytes per short)
	                drawOrder.length * 2);
	        dlb.order(ByteOrder.nativeOrder());
	        drawListBuffer = dlb.asShortBuffer();
	        drawListBuffer.put(drawOrder);
	        drawListBuffer.position(0);
	    }
	}
}
