import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
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

public class RadixGraphics extends JPanel {//24
    public final static Font FONT = new Font("Fira Code", Font.PLAIN, 15);
    public static double LERP_TIME = 1000;
    public static Point z = new Point(40, 10);

    private ArrayList<Integer> pieces;
    public ArrayList<Box> boxes;
    private ArrayList<Holder> holders;
    private ArrayList<Pointer> pointers;
    private ArrayList<Slicer> slicers;
    private ArrayList<Flexbox> flexers;
    
    public RadixGraphics(){
        pieces = new ArrayList<>();
        boxes = new ArrayList<>();
        holders = new ArrayList<>();
        pointers = new ArrayList<>();
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
        placeArray(z);
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
        for(Pointer pointer : pointers){
            pointer.draw(ctx);
        }
        for(Flexbox flexbox: flexers){
            flexbox.draw(ctx);
        }
        for(Holder holder : holders){
            holder.draw(ctx);
        }
    }

    public void clear(){
        pieces.clear();
        boxes.clear();
        flexers.clear();
        slicers.clear();
        pointers.clear();
        holders.clear();
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
        var max = Collections.max(pieces);
        int max_d = (int)Math.floor(Math.log10(max)) + 1;
        Flexbox main = new Flexbox(boxes, z);
        for(int d = 0; d < max_d; d++){
            holders.clear();

            ///We split apart into 10 cases for each digit.
            for(var i = 0; i < 10; i++){
                var holder = new Holder(new Point((i + 2) * 75, z.y + 900), Integer.toString(i).charAt(0));
                holders.add(holder);
            }
            for(var i = 0; i < main.elements.size(); i++){
                var value = check_digit(d, main.elements.get(i).content, max_d);
                holders.get(value).addBox(main.elements.get(i));
            }
            for (var holder : holders) {
                holder.spaceOut();
            }
            main.elements.clear();
            for (var holder : holders) {
                for(var elem : holder.elements){
                    main.addBox(elem);
                }
            }
            main.spaceOut();
        }
    }

    public void msd(){
        var max = Collections.max(pieces);
        int max_d = (int)Math.floor(Math.log10(max)) + 1;
        Flexbox main = new Flexbox(boxes, z);
        recursive_msd(max_d, main);
    }



    public void recursive_msd(int d, Flexbox main){
        if(d==0 || main.elements.size() <= 1){
            return;
        }
        flexers.clear();
        
        ///We split apart into 10 cases for each digit.
        for(var i = 0; i < 10; i++){
            var fbox = new Flexbox(new Point(z.x, (i + 10) * 40), Integer.toString(i).charAt(0));
            flexers.add(fbox);
        }
        ///For each element, we check the digit and place it into a flex box.
        for(var i = 0; i < main.elements.size(); i++){
            var value = check_digit(d-1, main.elements.get(i).content, d);
            flexers.get(value).addBox(main.elements.get(i));
        }
        ///We visualize all the flexers.
        for (var flexer : flexers) {
            flexer.spaceOut();
        }
        ///We clear all the elements from the main portion to put them back in.
        main.elements.clear();
        ///We add the elements into main.
        for (var flexer : flexers) {
            for(var elem : flexer.elements){
                main.addBox(elem);
            }
        }
        ///We visualize main.
        main.spaceOut();


        var local_slices = new ArrayList<Slicer>();

        ///We formulate the slices.
        Slicer slice = new Slicer(z.x-5, z.y+10, 30, '0');
        local_slices.add(slice);
        slicers.add(slice);
        ///We initialize `i` and `j`.
        int i = 0;
        int j = 1;
        while(true) {
            ///We stop if we reach the end.
            if(j >= flexers.size()) break;

            ///We get the current and next flexer.
            var currentFlexer = flexers.get(i);
            var nextFlexer = flexers.get(j);

            ///We attempt to find the end of the current, otherwise we continue.
            Box endOfCurrent;
            if(currentFlexer.elements.isEmpty()){
                i++;
                j++;
                continue;
            } else {
                // Get the "end" of the current flexer (last element)
                endOfCurrent = currentFlexer.elements.get(currentFlexer.elements.size() - 1);
            }
            
            ///We attempt to find the start of the next, otherwise we continue the j in search for another next.
            Box startOfNext;
            if(nextFlexer.elements.isEmpty()){
                j++;
                continue;
            } else {
                // Get the "beginning" of the next flexer (first element)
                startOfNext = nextFlexer.elements.get(0);
            }

            ///We can bring i back to us if our j move was successful.
            i = j - 1;

            ///We add the splice.
            Slicer splice = new Slicer(endOfCurrent, startOfNext, (char) ('0' + j));
            local_slices.add(splice);
            slicers.add(splice);

            ///We increment them per time.
            i+=1;
            j+=1;
        }

        var local_flexers = new ArrayList<Flexbox>();
        for(var k = 0; k<flexers.size(); k++){
            local_flexers.add(flexers.get(k));
        }
        flexers.clear();

        for(var s = 0; s<local_slices.size(); s++){
            var splice = local_slices.get(s);
            var next = new Flexbox(local_flexers.get(s).elements, new Point(splice.x+5, splice.y-10));
            recursive_msd(d-1, next);
        }
    }
    
    private static int check_digit(int digit, String content, int max_d){
        String padded = String.format("%0" + max_d + "d", Integer.parseInt(content));
        return Character.getNumericValue(padded.charAt(max_d - digit - 1));
    }

    // private static void draw_separator(Graphics2D ctx, Box one, Box two){
    //     int separatorX1 = (int)one.x + one.width; // Right edge of Box 1
    //     int separatorX2 = (int)two.x; // Left edge of Box 2

    //     int middle_of_edges = (separatorX1 + separatorX2) / 2;
    //     int y_middle = (int)one.y + one.height / 2;

    //     ctx.setColor(Color.BLACK);
    //     ctx.drawLine(middle_of_edges,  y_middle - one.height / 8 * 9, middle_of_edges, y_middle + one.height / 8 * 9);
    // }





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
    
    ///Here, we intend to get the maximum x value that the person may scale their screen up to. The input is mapped between this range.
    public static int scrn_x(double pct){
        return (int) (Toolkit.getDefaultToolkit().getScreenSize().getWidth() * pct / 100.0);
    }
    ///Here, we intend to get the maximum x value that the person may scale their screen up to. The input is mapped between this range.
    public static int scrn_y(double pct){
        return (int) (Toolkit.getDefaultToolkit().getScreenSize().getHeight() * pct / 100.0);
    }
}
