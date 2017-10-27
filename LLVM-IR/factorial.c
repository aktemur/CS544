int fact(int n) {
  int prod = 1;
  while(n > 0) {
    prod = prod * n;
    n = n - 1;
  }
  return prod;
}

int main() {
  return fact(5);
}
