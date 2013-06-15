import java.lang.Math;
import java.util.Collection;
import java.util.BitSet;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Scanner;
import java.util.Vector;

class Ppath {

  private static Scanner scan = new Scanner(System.in);

  private static HashSet<Integer> primes = initPrimes();

  private static HashMap<Integer, Collection<Integer>> connectedCache = new HashMap<Integer, Collection<Integer>>();

  public static void main(String[] args) {
    outermost: for (int numTimes = scan.nextInt(); numTimes > 0; numTimes--) {

      int depth = 0;

      Integer start = new Integer(scan.nextInt());
      Integer stop  = new Integer(scan.nextInt());

      if (start.equals(stop)) {
        System.out.println("0");
        continue outermost;
      }

      Collection<Integer> visited  = new HashSet<Integer>();
      Collection<Integer> currents = new Vector<Integer>();
      currents.add(start);

      while (true) {

        if (currents.isEmpty()) {
          System.out.println("Impossible");
          continue outermost;
        }

        Collection<Integer> next = new Vector<Integer>();

        for (Integer current : currents) {

          visited.add(current);
          Collection<Integer> connected = getConnected(current);
          connected.removeAll(visited);
          next.addAll(connected);
        }

        if (next.contains(stop)) {
          System.out.println(depth + 1);
          continue outermost;
        } else {
          depth++;
        }

        currents = next;

      }
    }
  }


  /**
  * Return a collection of 'connected' primes.
  **/
  private static Collection<Integer> getConnected(Integer i) {
    if (!connectedCache.containsKey(i)) {
      Collection<Integer> result = new Vector<Integer>();

      for (Integer c : primes) {
        String is = i.toString();
        String cs = c.toString();
        int same  = 0;
        for (int k = 0; k < 4; k++) {
          if (is.charAt(k) == cs.charAt(k)) {
            same++;
          }
        }
        if (same == 3) {
          result.add(c);
        }

      }

      connectedCache.put(i, result);
    }
    return new Vector<Integer>(connectedCache.get(i));
  }

  private static HashSet<Integer> initPrimes() {
    PrimeNumberRange primesRange = new PrimeNumberRange(1000, 9999);
    int[] primesArray = primesRange.primes();
    HashSet<Integer> primesHash = new HashSet<Integer>();

    for (int p : primesArray) {
      primesHash.add(new Integer(p));
    }
    return primesHash;
  }

}

class PrimeNumberRange {

  private int start;
  private int length;

  private BitSet bits;

  public PrimeNumberRange(int start, int end) {
    this.start  = start;
    this.length = 1 + end - start;
    this.bits   = new BitSet(this.length);
    seed();
  }

  public boolean isPrime(int number) {
    return bits.get(indexOf(number));
  }

  /**
  * Returns the prime number after the one given.
  **/
  public int nextPrime(int from) {
    int real = bits.nextSetBit(from - start);
    if (real < 0) {
      return -1;
    } else {
      return start + real;
    }
  }

  public int length() {
    return this.length;
  }

  /**
  * Returns a int array containing all of the primes.
  **/
  public int[] primes() {
    int[] result = new int[bits.cardinality()];
    int index = 0;
    int i     = start;
    while ((i = nextPrime(i)) > 0) {
      result[index++] = i;
      i++; // if you do not increment i then nextPrime will return the first prime over and over
    }
    return result;
  }

  /**
  * Step through bits and set the bits which represent prime numbers true.
  *
  * Could be implemented much better.
  **/
  private void seed() {
    outer: for (int i = start; i < start + length(); i++) {
      if (i % 2 != 0) {
        for (int j = 3; j <= Math.sqrt(i); j += 2) {
          if (i % j == 0) {
            continue outer;
          }
        }
        setPrime(i);
      }
    }
  }

  private int indexOf(int number) {
    return number - start;
  }

  private void set(int number, boolean value) {
    bits.set(indexOf(number), value);
  }

  private void setPrime(int number) {
    set(number, true);
  }

}