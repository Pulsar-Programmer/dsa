//Double comments are for the developer and not intended to be read for documentation.
///Triple comments are supplemental comments written throughout code intended to be read.
/** Doc comments are attached to entities explicating their function and are intended to be read, also. */
public class Driver {
    public static void main(String[] args) throws Exception {
        Snake glider = new Snake();
        glider.update("FMLFMUMMMMMM");
        System.out.println(glider.debug());
        System.out.println(glider);
        
        Snake egg = new Snake();
        egg.update("FMFMFMFMFMEEE");
        System.out.println(egg.debug());
        System.out.println(egg);

        Snake sanke = new Snake();
        sanke.update("RMUMLMFDM");
        System.out.println(sanke.debug());
        System.out.println(sanke);
    }
}
