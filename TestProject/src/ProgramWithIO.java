import components.simplereader.SimpleReader;
import components.simplereader.SimpleReader1L;
import components.simplewriter.SimpleWriter;
import components.simplewriter.SimpleWriter1L;

public final class ProgramWithIO {

    private ProgramWithIO() {
    }

    public static void main(String[] args) {
        SimpleReader in = new SimpleReader1L();
        SimpleWriter out = new SimpleWriter1L();

        int x = 10;
        out.println(x);
        x += 5;
        out.println(x);
        String name = "Morgan";

        in.close();
        out.close();
    }

}
