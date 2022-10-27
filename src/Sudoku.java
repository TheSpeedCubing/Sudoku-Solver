

public class Sudoku {
    public static void main(String[] arg) {
        int[][] q = {
                {9, 0, 0, 0, 5, 0, 4, 0, 8},
                {2, 8, 0, 0, 6, 0, 0, 0, 0},
                {0, 0, 0, 0, 0, 0, 0, 7, 0},
                {8, 0, 1, 0, 0, 3, 0, 0, 0},
                {0, 9, 0, 7, 0, 6, 0, 3, 0},
                {0, 0, 0, 5, 0, 0, 8, 0, 9},
                {0, 1, 0, 0, 0, 0, 0, 0, 0},
                {0, 0, 0, 0, 2, 0, 0, 8, 6},
                {6, 0, 4, 0, 3, 0, 0, 0, 7}
        };
        boolean b = solve(q, 0, 0);
        if (b)
            print(q);
        else System.out.println("unsolvable");
    }

    public static int[][] clone(int[][] q) {
        int[][] v = new int[9][9];
        for (int x = 0; x < 9; x++) {
            System.arraycopy(q[x], 0, v[x], 0, 9);
        }
        return v;
    }

    public static void print(int[][] q) {
        int r = 0;
        System.out.print("+---+---+---+");
        for (int x = 0; x < 9; x++) {
            System.out.print("\n|");
            for (int y = 0; y < 9; y++) {
                r++;
                System.out.print(q[x][y]);
                if (r % 3 == 0)
                    System.out.print("|");
            }
            r = 0;
            if ((x + 1) % 3 == 0)
                System.out.print("\n+---+---+---+");
        }
    }

    public static boolean solve(int[][] q, int r, int c) {
        if (c == 9) { //if at the last column
            if (r == 8) //if at the last row
                return true;
            r++; //go to next row
            c = 0;
        }
        if (q[r][c] != 0) //if this is not empty, solve the next column
            return solve(q, r, c + 1);
        loop:
        for (int i = 1; i <= 9; i++) { //from 1 - 9
            for (int a = 0; a < 9; a++) { //check if r conflicts
                if (q[r][a] == i) {
                    q[r][c] = 0;
                    continue loop;
                }
            }
            for (int a = 0; a < 9; a++) {//check if c conflicts
                if (q[a][c] == i) {
                    q[r][c] = 0;
                    continue loop;
                }
            }
            int sR = r - r % 3;
            int sC = c - c % 3;
            for (int a = 0; a < 3; a++) {//check if block conflicts
                for (int b = 0; b < 3; b++) {
                    if (q[a + sR][b + sC] == i) {
                        q[r][c] = 0;
                        continue loop;
                    }
                }
            }
            q[r][c] = i; //not conflict
            if (solve(q, r, c + 1)) //solve the next column
                return true;
            q[r][c] = 0; //next column not solvable
        }
        return false;
    }
}