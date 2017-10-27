int main() {
  int x = 8;
  int y = 4;
  while (y!=0 && x/y < 5) { // Note: Short-cut evaluation!!!
    x = x + 2;
    if (y == 2) break;
    y = y - 1;
  }
  int z = x + y;
  return z;
}
