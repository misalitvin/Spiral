import java.awt.*;
import java.io.FileInputStream;
import java.util.Arrays;


public class Main extends Frame {

    public static void main(String[] args) {
        new Main();

    }

    int x, y;
    int stepSize = 5;
    int num = 1;
    int cond = 0;
    int numOfTurns = 1, numOfCh = 1;

    public Main() {
        super();
        setSize(500, 400);
        setVisible(true);
        x = getWidth() / 2;
        y = getHeight() / 2;
    }

    static int[] add(int[] A, int value, int length) {
        int[] B;
        B = Arrays.copyOf(A, length + 1);
        B[length] = value;
        return B;
    }

    public void paint(Graphics g) {
        try {
            FileInputStream bin = new FileInputStream("Bin.bin");
            super.paint(g);
            int val;
            int line = 1;
            int length = 0;
            int byte1, byte2 = 0;


            int[] Nums = new int[10000000];
            bin.skip(2);
            while ((val = bin.read()) != -1) {
                if (val == 10 ) {
                    bin.skip(2);
                    line++;

                }

                switch (line) {
                    case 2:
                        byte1 = bin.read() << 8;
                        val = val | byte1;
                        break;
                    case 3:
                        byte1 = bin.read() << 8;
                        byte2 = bin.read() << 16;
                        val = val | byte1 | byte2;
                        break;
                }
                if (val != 266 && val != 255 * 256 + 11) {
                    Nums = add(Nums, val, length);
                    length++;

                }

            }
            bin.close();
            System.out.println(Arrays.toString(Nums));
            do {
                for (int i = 0; i < Nums.length; i++) {
                    if (num == Nums[i]) {
                        System.out.println(Nums[i]);
                        g.fillOval(x, y, 5, 5);
                    }
                }


            /*if (isPrime(num)) {
                g.fillOval(x, y, 10, 10);
                }*/

                switch (cond) {
                    case 0:
                        x += stepSize;
                        break;
                    case 1:
                        y -= stepSize;
                        break;
                    case 2:
                        x -= stepSize;
                        break;
                    case 3:
                        y += stepSize;
                        break;
                }
                if (num % numOfCh == 0) {
                    cond = (cond + 1) % 4;
                    numOfTurns++;
                    if (numOfTurns % 2 == 0) {
                        numOfCh++;
                    }
                }
                num++;

            } while (num <= 256 * 256 * 256);
        } catch (Exception exception) {
            System.out.println("Mistake");
        }
    }
}
