import java.util.Arrays;
import java.util.Scanner;

public class CCC4 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        StringBuilder sb = new StringBuilder();

        int n = sc.nextInt();

        for (int i = 0;i < n;i++) {
            int rad = sc.nextInt();
            int l = sc.nextInt();

            sc.nextLine();

            char[][] instructions = new char[l][6];

            for (int j = 0;j < l;j++) {
                for (int k = 0; k < 6; k++) {
                    instructions[j][k] = sc.next().charAt(0);
                }
                sc.nextLine();
            }

            char[][] arr = makeHex(rad);

            int row = arr.length;
            int col = arr[0].length;

//            System.out.println(row + " " + col);

            int[][] emittersPosition = placeEmitters(arr, row, col);
//            for (int j = 0;j < emittersPosition.length;j++) {
//                System.out.println(Arrays.toString(emittersPosition[j]));
//            }

            for (int j = 0;j < l;j++) {
                executeInstruction(emittersPosition, instructions[j], row, col, rad);
//                for (int k = 0;k < emittersPosition.length;k++) {
//                    System.out.println(Arrays.toString(emittersPosition[k]));
//                }
//                System.out.println();

                int[][] count = new int[row][col];
                updateArr(count, arr, row, col, emittersPosition);
                createAndDestroy(count, arr, row, col);
            }

            for (int j = 0;j < row;j++) {
                sb.append(arr[j]).append('\n');
            }
            sb.append('\n');
        }
        System.out.println(sb.toString());
    }

    public static void updateArr(int[][] count, char[][] arr, int row, int col, int[][] emittersPosition) {
        for (int em = 1;em < 7;em++) {
            int i = emittersPosition[em][0];
            int j = emittersPosition[em][1];
            boolean isActive = emittersPosition[em][2] == 1;

            if (!isActive) continue;

            switch (em) {
                case 1 -> {
                    for (int k = j; k < col; k++) {
                        count[i][k]++;
                    }
                }
                case 2 -> {
                    int spc = 1;
                    for (int k = i + 1; k < row && j + spc < col; k++) {
                        count[k][j + spc]++;
                        spc++;
                    }
                }
                case 3 -> {
                    int spc = 1;
                    for (int k = i + 1; k < row && j - spc >= 0; k++) {
                        count[k][j - spc]++;
                        spc++;
                    }
                }
                case 4 -> {
                    for (int k = j; k >= 0; k--) {
                        count[i][k]++;
                    }
                }
                case 5 -> {
                    int spc = 1;
                    for (int k = i - 1; k >= 0 && j - spc >= 0; k--) {
                        count[k][j - spc]++;
                        spc++;
                    }
                }
                case 6 -> {
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

    public static void executeInstruction(int[][] emittersPosition, char[] instructions, int row, int col, int rad) {

        for (int k = 1;k < 7;k++) {
            int i = emittersPosition[k][0];
            int j = emittersPosition[k][1];
            boolean isActive = emittersPosition[k][2] == 1;

            char instruction = instructions[k - 1];

            switch (instruction) {
                case 'F' -> {
                    if (i == 0 && j < col - 1 - rad) {
                        emittersPosition[k][0] = i;
                        emittersPosition[k][1] = j + 2;
                    }
                    else if (i < row / 2 && j >= col - 1 - rad + i) {
                        emittersPosition[k][0] = i + 1;
                        emittersPosition[k][1] = j + 1;
                    }
                    else if (i >= row / 2 && i < row - 1 && j >= col - 1 - (i - row / 2)) {
                        emittersPosition[k][0] = i + 1;
                        emittersPosition[k][1] = j - 1;
                    }
                    else if (i == row - 1 && j > rad) {
                        emittersPosition[k][0] = i;
                        emittersPosition[k][1] = j - 2;
                    }
                    else if (i > row / 2 && j <= rad - (row - 1 - i)) {
                        emittersPosition[k][0] = i - 1;
                        emittersPosition[k][1] = j - 1;
                    }
                    else {
                        emittersPosition[k][0] = i - 1;
                        emittersPosition[k][1] = j + 1;
                    }

                }
                case 'B' -> {
                    if (i == 0 && j > rad) {
                        emittersPosition[k][0] = i;
                        emittersPosition[k][1] = j - 2;
                    }
                    else if (i < row / 2 && j <= rad - i) {
                        emittersPosition[k][0] = i + 1;
                        emittersPosition[k][1] = j - 1;
                    }
                    else if (i >= row / 2 && i < row - 1 && j <= i - row / 2) {
                        emittersPosition[k][0] = i + 1;
                        emittersPosition[k][1] = j + 1;
                    }
                    else if (i == row - 1 && j < col - 1 - rad) {
                        emittersPosition[k][0] = i;
                        emittersPosition[k][1] = j + 2;
                    }
                    else if (i > row / 2 && j >= col - 1 - (i - row / 2)) {
                        emittersPosition[k][0] = i - 1;
                        emittersPosition[k][1] = j + 1;
                    }
                    else {
                        emittersPosition[k][0] = i - 1;
                        emittersPosition[k][1] = j - 1;
                    }
                }
                case 'T' -> {
                    emittersPosition[k][2] = isActive ? 0 : 1;
                }
                default -> {
                    continue;
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

    public static int[][] placeEmitters(char[][] arr, int row, int col) {
        int[][] emittersPosition = new int[7][3];

        for (int i = 0;i < col;i++) {
            if (arr[0][i] == '*') {
                emittersPosition[2][0] = 0;
                emittersPosition[2][1] = i;
                emittersPosition[2][2] = 0;
                break;
            }
        }

        for (int i = col - 1;i >= 0;i--) {
            if (arr[0][i] == '*') {
                emittersPosition[3][0] = 0;
                emittersPosition[3][1] = i;
                emittersPosition[3][2] = 0;
                break;
            }
        }

        emittersPosition[1][0] = row / 2;
        emittersPosition[1][1] = 0;
        emittersPosition[1][2] = 0;

        emittersPosition[4][0] = row / 2;
        emittersPosition[4][1] = col - 1;
        emittersPosition[4][2] = 0;

        for (int i = 0;i < col;i++) {
            if (arr[row - 1][i] == '*') {
                emittersPosition[6][0] = row - 1;
                emittersPosition[6][1] = i;
                emittersPosition[6][2] = 0;
                break;
            }
        }

        for (int i = col - 1;i >= 0;i--) {
            if (arr[row - 1][i] == '*') {
                emittersPosition[5][0] = row - 1;
                emittersPosition[5][1] = i;
                emittersPosition[5][2] = 0;
                break;
            }
        }

        return emittersPosition;
    }

    public static char[][] makeHex(int rad) {
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

//        System.out.println(sb.toString());

        String[] lines = sb.toString().split("\\r?\\n");

        char[][] arr = new char[lines.length][];

        for (int i = 0;i < lines.length;i++) {
            arr[i] = lines[i].toCharArray();
        }
        return arr;
    }
}
