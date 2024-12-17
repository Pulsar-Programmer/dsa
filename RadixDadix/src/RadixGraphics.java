import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.swing.JPanel;

import java.awt.Color;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.Toolkit;
import java.awt.image.BufferedImage;

public class RadixGraphics extends JPanel {
    public final static Font FONT = new Font("Fira Code", Font.PLAIN, 24);

    private ArrayList<Integer> pieces;
    public ArrayList<Box> boxes;
    // private ArrayList<RadixHolder> holders;
    // private ArrayList<Pointer> pointers;
    private ArrayList<Slicer> slicers;
    private ArrayList<Flexbox> flexers;
    
    public RadixGraphics(){
        pieces = new ArrayList<>();
        boxes = new ArrayList<>();
        // holders = new ArrayList<>();
        // pointers = new ArrayList<>();
        slicers = new ArrayList<>();
        flexers = new ArrayList<>();
    }
    public RadixGraphics(ArrayList<Integer> pieces){
        this();
        this.pieces = pieces;
        start();
    }


    public ArrayList<Integer> getPieces() {
        return pieces;
    }
    public void setPieces(ArrayList<Integer> pieces) {
        this.pieces = pieces;
        start();
    }

    public void start(){
        placeArray(new Point(10, 10));
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        var ctx = (Graphics2D)g;

        for (Box box : boxes) {
            box.draw(ctx);
        }
        for (Slicer slicer : slicers) {
            slicer.draw(ctx);
        }
        for(Flexbox flexbox: flexers){
            flexbox.draw(ctx);
        }
    }

    public void clear(){
        pieces.clear();
        boxes.clear();
        flexers.clear();
        slicers.clear();
    }

    private void placeArray(Point p){
        var list = pieces;

        BufferedImage dummyImage = new BufferedImage(1, 1, BufferedImage.TYPE_INT_ARGB);
        Graphics2D actx = dummyImage.createGraphics();
        actx.setFont(FONT);

        FontMetrics fm = actx.getFontMetrics();
        int y_offset = fm.getAscent();
        int x = p.x;
        int y = p.y + y_offset;
        
        for(var i = 0; i < list.size(); i++){
            var to_draw = list.get(i).toString();
            var x_offset = fm.stringWidth(to_draw);

            var handle = new Box(x, y, x_offset + 10, y_offset + 5, to_draw);
            boxes.add(handle);

            x += x_offset + 20;
        }
    }


    

    

    public void lsd(){
        //TODO
    }

    public void msd(){
        //TODO
        boxes.get(0).move(new Point(300, 300), 2);
            
        // var flex = new Flexbox(new Point(10, 100));
        // for(var  i = 0; i < boxes.size(); i++){
        //     flex.addBox(boxes.get(boxes.size() - i - 1));
        // }
        // flexers.add(flex);
        


    }
    


    private static void draw_separator(Graphics2D ctx, Box one, Box two){
        int separatorX1 = one.x + one.width; // Right edge of Box 1
        int separatorX2 = two.x; // Left edge of Box 2

        int middle_of_edges = (separatorX1 + separatorX2) / 2;
        int y_middle = one.y + one.height / 2;

        ctx.setColor(Color.BLACK);
        ctx.drawLine(middle_of_edges,  y_middle - one.height / 8 * 9, middle_of_edges, y_middle + one.height / 8 * 9);
    }





    public static void msdRadixSort(int[] arr) {
        if (arr.length == 0) return;

        int max = Arrays.stream(arr).max().orElse(0);
        int maxDigits = Integer.toString(max).length();

        msdRadixSortHelper(arr, 0, arr.length - 1, maxDigits);
    }

    private static void msdRadixSortHelper(int[] arr, int low, int high, int digit) {
        if (low >= high || digit == 0) return;

        int radix = 10; // Base of the number system
        int divisor = (int) Math.pow(10, digit - 1);

        // Buckets for digits 0-9
        List<List<Integer>> buckets = new ArrayList<>();
        for (int i = 0; i < radix; i++) {
            buckets.add(new ArrayList<>());
        }

        // Place elements into buckets based on the current digit
        for (int i = low; i <= high; i++) {
            int digitValue = (arr[i] / divisor) % radix;
            buckets.get(digitValue).add(arr[i]);
        }

        // Reassemble array from buckets
        int index = low;
        for (List<Integer> bucket : buckets) {
            for (int num : bucket) {
                arr[index++] = num;
            }
        }

        // Recursively sort each bucket
        int start = low;
        for (List<Integer> bucket : buckets) {
            if (!bucket.isEmpty()) {
                msdRadixSortHelper(arr, start, start + bucket.size() - 1, digit - 1);
                start += bucket.size();
            }
        }
    }


    public static void lsdRadixSort(int[] arr) {
        if (arr.length == 0) return;

        // Find the maximum number to determine the number of digits
        int max = Arrays.stream(arr).max().orElse(0);
        int exp = 1; // Exponent to extract digits (1 for units, 10 for tens, etc.)
        int radix = 10; // Base of the number system (10 for decimal)

        while (max / exp > 0) {
            countingSortByDigit(arr, exp, radix);
            exp *= radix; // Move to the next significant digit
        }
    }

    private static void countingSortByDigit(int[] arr, int exp, int radix) {
        int[] output = new int[arr.length];
        int[] count = new int[radix];

        // Count occurrences of each digit
        for (int num : arr) {
            int digit = (num / exp) % radix;
            count[digit]++;
        }

        // Accumulate counts
        for (int i = 1; i < radix; i++) {
            count[i] += count[i - 1];
        }

        // Build output array (stable sort)
        for (int i = arr.length - 1; i >= 0; i--) {
            int digit = (arr[i] / exp) % radix;
            output[--count[digit]] = arr[i];
        }

        // Copy sorted array back to original
        System.arraycopy(output, 0, arr, 0, arr.length);
    }











    









   


    
    ///Here, we intend to get the maximum x value that the person may scale their screen up to. The input is mapped between this range.
    public static int scrn_x(double pct){
        return (int) (Toolkit.getDefaultToolkit().getScreenSize().getWidth() * pct / 100.0);
    }
    ///Here, we intend to get the maximum x value that the person may scale their screen up to. The input is mapped between this range.
    public static int scrn_y(double pct){
        return (int) (Toolkit.getDefaultToolkit().getScreenSize().getHeight() * pct / 100.0);
    }
}
