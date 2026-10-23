import java.util.Scanner;

public class CCC3 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        StringBuilder sb = new StringBuilder();

        int n = sc.nextInt();

        for (int i = 0;i < n;i++) {
            int rad = sc.nextInt();
            int row = sc.nextInt();
            int col = sc.nextInt();

            char[][] arr = new char[row][col];
            int[][] count = new int[row][col];

            for (int j = 0;j < row;j++) {
                arr[j] = sc.next().toCharArray();
            }

            updateArr(count, arr, row, col);
            createAndDestroy(count, arr, row, col);

            for (int j = 0;j < row;j++) {
                sb.append(arr[j]).append('\n');
            }
            sb.append('\n');
        }
        System.out.println(sb.toString());

    }

    public static void updateArr(int[][] count, char[][] arr, int row, int col) {

        for (int i = 0; i < row; i++) {
            for (int j = 0; j < col; j++) {
                switch (arr[i][j]) {
                    case '1' -> {
                        for (int k = j; k < col; k++) {
                            count[i][k]++;
                        }
                    }

                    case '2' -> {
                        int spc = 1;
                        for (int k = i + 1; k < row && j + spc < col; k++) {
                            count[k][j + spc]++;
                            spc++;
                        }
                    }

                    case '3' -> {
                        int spc = 1;
                        for (int k = i + 1; k < row && j - spc >= 0; k++) {
                            count[k][j - spc]++;
                            spc++;
                        }

                    }

                    case '4' -> {
                        for (int k = j; k >= 0; k--) {
                            count[i][k]++;
                        }
                    }

                    case '5' -> {
                        int spc = 1;
                        for (int k = i - 1; k >= 0 && j - spc >= 0; k--) {
                            count[k][j - spc]++;
                            spc++;
                        }
                    }

                    case '6' -> {
                        int spc = 1;
                        for (int k = i - 1; k >= 0 && j + spc < col; k--) {
                            count[k][j + spc]++;
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

    public static void createAndDestroy(int[][] count, char[][] arr, int row, int col) {

        for (int i = 0;i < row;i++) {
            for (int j = 0;j < col;j++) {
                if (arr[i][j] == 'O') {
                    if (count[i][j] == 2) arr[i][j] = 'M';
                }
                else if (arr[i][j] == 'M') {
                    if (count[i][j] > 2) arr[i][j] = 'O';
                }
            }
        }
    }
}
