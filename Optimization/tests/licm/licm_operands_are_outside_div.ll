define i32 @main() {
  %r = alloca i32
  %i = alloca i64
  %a = alloca i64
  store i64 0, i64* %i
  store i64 5, i64* %a
  %v1 = load i64, i64* %i
  %v2 = load i64, i64* %a
  br label %le

le:                                               ; preds = %lc, %0
  %i1 = load i64, i64* %i
  %cond = icmp slt i64 %i1, 100
  br i1 %cond, label %lb, label %lo

lb:                                               ; preds = %le
  %div = sdiv i64 %v1, %v2
  br label %lc

lc:                                               ; preds = %lb
  %i2 = load i64, i64* %i
  %ni = add nsw i64 %v1, %div
  store i64 %ni, i64* %i
  br label %le

lo:                                               ; preds = %le
  %o = load i32, i32* %r
  ret i32 %o
}
