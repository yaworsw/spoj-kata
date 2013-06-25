import java.util.HashMap;
import java.util.Scanner;

class Nsystem {

  private static Scanner scan = new Scanner(System.in);

  public static void main(String[] args)
  {
    int numTimes = scan.nextInt();
    scan.nextLine();
    while (numTimes-- > 0) {
      String[] in = scan.nextLine().split(" ");
      MCXINumber a = new MCXINumber(in[0]);
      MCXINumber b = new MCXINumber(in[1]);
      MCXINumber r = a.add(b);
      System.out.println(r);
    }
  }

}

class MCXINumber {

  private static HashMap<Character, Integer> magnitudes = makeMagnitudesMap();

  private int val;

  public MCXINumber(int val) {
    this.val = val;
  }

  public MCXINumber(String str) {
    this.val = MCXINumber.strToVal(str);
  }

  private static HashMap<Character, Integer> makeMagnitudesMap() {
    HashMap<Character, Integer> map = new HashMap<Character, Integer>(4);
    map.put('m', 1000);
    map.put('c', 100);
    map.put('x', 10);
    map.put('i', 1);
    return map;
  }

  private static int strToVal(String str) {
    int v = 0;
    for (int i = 0; i < str.length(); i++) {
      char cur = str.charAt(i);
      if (!Character.isDigit(cur)) {
        v += MCXINumber.magnitudes.get(cur);
      } else {
        int pow = Integer.parseInt(new Character(cur).toString());
        char next = str.charAt(i+1);
        int mag = MCXINumber.magnitudes.get(next);
        i++;
        v += pow * mag;
      }
    }
    return v;
  }

  public MCXINumber add(MCXINumber other) {
    return new MCXINumber(this.val + other.val);
  }

  public int getVal() {
    return this.val;
  }

  public String getStr() {

    int m = (int)Math.floor(this.val / 1000);
    int thou = m * 1000;
    int c = (int)Math.floor((this.val - thou) / 100);
    int hun  = c * 100;
    int x = (int)Math.floor((this.val - (thou + hun)) / 10);
    int tens = x * 10;
    int i = (int)Math.floor((this.val - (thou + hun + tens)));
    String result = ""; // should likely be StringBuilder

    if (m > 0) {
      if (m > 1) {
        result += m;
      }
      result += "m";
    }

    if (c > 0) {
      if (c > 1) {
        result += c;
      }
      result += "c";
    }

    if (x > 0) {
      if (x > 1) {
        result += x;
      }
      result += "x";
    }

    if (i > 0) {
      if (i > 1) {
        result += i;
      }
      result += "i";
    }

    return result;

  }

  public String toString() {
    return this.getStr();
  }

}