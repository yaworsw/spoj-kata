#include <stdio.h>

int main () {

  while (1) {

    int i;
    scanf("%d", &i);

    if (i == 0) {
      return;
    }

    int j = 0;
    while (i > 0) {
      j += i * i--;
    }

    printf("%d\n", j);

  }

}
