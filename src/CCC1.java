import java.util.*;

public class CCC1 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int n = sc.nextInt();

        int[] rad = new int[n];

        for (int i = 0;i < n;i++) {
            rad[i] = sc.nextInt();
        }
        for (int i = 0;i < n;i++) {
            makeHex(rad[i]);
            System.out.println();
        }
    }
    public static void makeHex(int rad) {
        StringBuilder sb = new StringBuilder();

        // start line
        for (int i = 0;i < rad;i++) {
            sb.append('-');
        }
        for (int i = 0;i <= rad;i++) {
            if (i != rad) {
                sb.append('*').append('-');
            }
            else {
                sb.append('*');
            }
        }
        for (int i = 0;i < rad;i++) {
            sb.append('-');
        }
        sb.append("\n");

        // mid part
        int height = 2 * rad - 1;

        for (int i = 1;i <= rad;i++) {

            for (int j = 0;j < rad - i;j++) {
                sb.append('-');
            }

            sb.append('*').append('-');


            for (int j = 1;j < rad + i;j++) {
                sb.append('O').append('-');
            }

            sb.append('*');

            for (int j = 0;j < rad - i;j++) {
                sb.append('-');
            }
            sb.append('\n');
        }

        for (int i = rad - 1;i >= 1;i--) {

            for (int j = 0;j < rad - i;j++) {
                sb.append('-');
            }

            sb.append('*').append('-');


            for (int j = 1;j < rad + i;j++) {
                sb.append('O').append('-');
            }

            sb.append('*');

            for (int j = 0;j < rad - i;j++) {
                sb.append('-');
            }
            sb.append('\n');
        }

        // end line
        for (int i = 0;i < rad;i++) {
            sb.append('-');
        }
        for (int i = 0;i <= rad;i++) {
            if (i != rad) {
                sb.append('*').append('-');
            }
            else {
                sb.append('*');
            }
        }
        for (int i = 0;i < rad;i++) {
            sb.append('-');
        }


        System.out.println(sb.toString());

    }
}
