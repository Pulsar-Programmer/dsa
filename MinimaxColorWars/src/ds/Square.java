package ds;

/**Squares within the board. */
public class Square implements Selectable {
    GraphicsObject object;



    @Override
    public boolean cmp(int mouse_x, int mouse_y) {
        // return mouse_x - object.position.x
        return false;
    }

    
}
