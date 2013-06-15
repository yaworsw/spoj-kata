import java.util.Scanner;

class Ny10a {

  private static String[] seqs = {
    "TTT", "TTH", "THT", "THH", "HTT", "HTH", "HHT", "HHH"
  };

  private static Scanner scan = new Scanner(System.in);

  private static int[] tallies;

  public static void main(String[] args) {

    for (int numTimes = scan.nextInt(); numTimes > 0; numTimes--) {

      resetTallies();

      int trial = scan.nextInt();
      scan.nextLine();
      String tosses = scan.nextLine();

      char[] a = new char[3];
      char[] b = new char[3];
      char[] c = new char[3];

      a[0] = tosses.charAt(0);
      a[1] = tosses.charAt(1);
      int ai = 2;

      b[0] = tosses.charAt(1);
      int bi = 1;

      int ci = 0;

      for (int i = 2; i < tosses.length(); i++) {

        a[ai++] = b[bi++] = c[ci++] = tosses.charAt(i);

        if (ai == 3) {
          tally(a);
          ai = 0;
        } else if (bi == 3) {
          tally(b);
          bi = 0;
        } else if (ci == 3) {
          tally(c);
          ci = 0;
        }

      }

      System.out.print(trial);
      for (int i = 0; i < seqs.length; i++) {
        System.out.print(" ");
        System.out.print(tallies[i]);
      }
      System.out.println();


    }

  }

  private static void resetTallies() {
    tallies = new int[8];
  }

  private static int tally(String in) {
    for (int i = 0; i < seqs.length; i++) {
      if (seqs[i].equals(in)) {
        return tallies[i]++;
      }
    }
    return -1;
  }

  private static int tally(char[] in) {
    return tally(String.valueOf(in));
  }

}