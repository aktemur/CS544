#include <stdio.h>

typedef struct Point {
  int x;
  int y;
} Point;

void init(Point *p, int x, int y) {
  p->x = x;
  p->y = y;
}

void move(Point *p, int dx, int dy) {
  p->x += dx;
  p->y += dy;
}

int main() {
  Point p;
  init(&p, 5, 7);
  move(&p, 3, 8);
  return p.x + p.y;
}
