import java.util.Scanner;

public class CCC2 {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        StringBuilder sb = new StringBuilder();

        int n = sc.nextInt();

        for (int i = 0;i < n;i++) {
            int rad = sc.nextInt();
            int row = sc.nextInt();
            int col = sc.nextInt();

            char[][] arr = new char[row][col];

            for (int j = 0;j < row;j++) {
                arr[j] = sc.next().toCharArray();
            }

            updateArr(arr, row, col);

            for (int j = 0;j < row;j++) {
                sb.append(arr[j]).append('\n');
            }
            sb.append('\n');
        }
        System.out.println(sb.toString());
    }

    public static void updateArr(char[][] arr, int row, int col) {

        for (int i = 0;i < row;i++) {
            for (int j = 0;j < col;j++) {
                switch (arr[i][j]) {
                    case '1' -> {
                        for (int k = j;k < col;k++) {
                            if (arr[i][k] == 'O') arr[i][k] = 'M';
                        }
                    }

                    case '2' -> {
                        int spc = 1;
                        for (int k = i + 1;k < row && j + spc < col;k++) {
                            if (arr[k][j + spc] == 'O') arr[k][j + spc] = 'M';
                            spc++;
                        }
                    }

                    case '3' -> {
                        int spc = 1;
                        for (int k = i + 1;k < row && j - spc >= 0 ;k++) {
                            if (arr[k][j - spc] == 'O') arr[k][j - spc] = 'M';
                            spc++;
                        }

                    }

                    case '4' -> {
                        for (int k = j;k >= 0;k--) {
                            if (arr[i][k] == 'O') arr[i][k] = 'M';
                        }
                    }

                    case '5' -> {
                        int spc = 1;
                        for (int k = i - 1;k >= 0 && j - spc >= 0;k--) {
                            if (arr[k][j - spc] == 'O') arr[k][j - spc] = 'M';
                            spc++;
                        }
                    }

                    case '6' -> {
                        int spc = 1;
                        for (int k = i - 1;k >= 0 && j + spc < col;k--) {
                            if (arr[k][j + spc] == 'O') arr[k][j + spc] = 'M';
                            spc++;
                        }
                    }

                    default -> {
                        continue;
                    }

                }
            }
        }
    }
}


