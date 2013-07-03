import sys

for _ in range(0, int(sys.stdin.readline())):
  a, b = sys.stdin.readline().split(' ')
  print int(str(int(a[::-1]) + int(b[::-1]))[::-1])