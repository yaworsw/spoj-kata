#include <stdio.h>

int main () {

  int i;
  scanf("%d", &i);

  while (i-- > 0) {

    float sides;
    scanf("%f", &sides);

    float tot = 0;
    for (int j = sides; j > 0; j--) {
      tot += (float)1 / j;
    }

    printf("%.2f\n", tot / (1 / sides));

  }

}
