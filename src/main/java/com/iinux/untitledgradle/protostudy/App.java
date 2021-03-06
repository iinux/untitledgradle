import java.io.*;

/*
 * This Java source file was generated by the Gradle 'init' task.
 */
public class App {
    static String file = "/dev/mmcblk0p1";

    public static void main(String[] args) throws IOException {
        // System.out.println(calc());
        long pos = 536871944191L;
        pos = 1000000;
        System.out.println(read(pos));
        write(pos, 49);
        System.out.println(read(pos));
    }

    protected static long calc() throws IOException {
        long max = 549755813888L;
        long bottom = 0;
        long p = max;

        while (true) {
            System.out.println("p=" + p);
            int a = read(p);
            if (a == -1) {
                if (p == bottom) {
                    System.out.println("reach the bottom and not found");
                    return p;
                }
                long np = p - (p - bottom) / 2;
                if (np == p) {
                    p = bottom;
                } else {
                    p = np;
                }
                continue;
            }

            if (p == max) {
                System.out.println("reach the max and not found");
                return p;
            }

            int b = read(p + 1);
            if (b == -1) {
                return p;
            }

            bottom = p;

            long np = (max - p) / 2 + p;
            if (np == p) {
                p = max;
            } else {
                p = np;
            }
        }

    }

    protected static void write(long skip, int c) throws IOException {
        String cmd = String.format("sudo dd of=%s bs=1 count=1 skip=%d", file, skip);
        System.out.println(cmd);
        Process p = Runtime.getRuntime().exec(cmd);
        OutputStream os = p.getOutputStream();
        os.write(c);
        os.close();
    }

    protected static int read(long skip) throws IOException {
        String cmd = String.format("sudo dd if=%s bs=1 count=1 skip=%d", file, skip);
        System.out.println(cmd);
        Process p = Runtime.getRuntime().exec(cmd);

        return p.getInputStream().read();
        /*
        PrintStream print = new PrintStream(p.getOutputStream());
        print.println("This is line 1");
        print.println("This is line 2");
        print.close(); // send EOF

         */

        /*
        BufferedReader br = new BufferedReader(new InputStreamReader(p.getInputStream()));
        String str = br.readLine();
        while (str != null) {
            System.out.println(str);
            str = br.readLine();
        }

         */
    }
}
