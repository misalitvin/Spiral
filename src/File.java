import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.ByteBuffer;

public class File{
    static boolean isPrime(int value) {
        if (value == 1) return false;
        for (int i = 2; i < value; i++)
        {
            if (value % i == 0) {
                return false;
            }
        }
        return true;
    }

    static void writeline(FileOutputStream bin, int line){
        try{
        switch (line) {
            case 1:
                for (int i = 1; i <= 255; i++) {
                    if (isPrime(i)) {
                        bin.write((byte) i);
                        System.out.println(i);
                    }

                }break;
            case 2:
                for(int i = 256;i<=255*256;i++){
                    if(isPrime(i)){
                        byte[] bytes = new byte[4];
                        ByteBuffer.wrap(bytes).putInt(i);

                        byte[] twoBytes = new byte[2];
                        twoBytes[0] = bytes[2];
                        twoBytes[1] = bytes[3];
                        bin.write(twoBytes);
                        System.out.println(i);
                    }

                }break;
            case 3:
                for(int i = 256*255;i<=255*256*256;i++){
                    if(isPrime(i)){
                        byte[] bytes = new byte[4];
                        ByteBuffer.wrap(bytes).putInt(i);

                        byte[] threeBytes = new byte[3];
                        threeBytes[0] = bytes[1];
                        threeBytes[1] = bytes[2];
                        threeBytes[2] = bytes[3];
                        bin.write(threeBytes);
                    }

                }break;
        }
        }catch (IOException e){
            System.out.println("Mistake");
        }
    }
    public static void main(String[] args) {
        FileCreation();
    }
    public static void FileCreation() {
        try {
            FileOutputStream bin = new FileOutputStream("Bin.bin");
            int count = 0;
            int Byte = 255;
            for (int line = 1; line <= 3; line++) {
                for (int j = Byte / 255; j <= Byte; j++) {
                    if (isPrime(j)) {
                        count++;
                    }
                }
                bin.write(0);
                bin.write(count);//writing 8 bytes with amount of primes in line

                writeline(bin,line);//writing line of primes

                bin.write('\n');
                count = 0;
                Byte = Byte * 256;
            }
            System.out.println("File created");
            bin.close();
        } catch (Exception e) {
        }
    }
}
