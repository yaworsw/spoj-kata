#include <stdio.h>

int main () {

  while (1) {

    float goal;
    scanf("%f", &goal);

    if (goal == 0) {
      return 0;
    }

    int   i = 1;
    float t = 0.5;
    while (t < goal) {
      t += ((float)1 / (i + 2));
      i++;
    }

    printf("%d card(s)\n", i);

  }

}
