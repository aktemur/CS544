define i32 @main() {
  %r = alloca i32
  %i = alloca i64
  %a = alloca i64
  store i64 0, i64* %i
  store i64 5, i64* %a
  %div = sdiv i64 5, 0
  br label %le

le:                                               ; preds = %lc, %0
  %i1 = load i64, i64* %i
  %cond = icmp slt i64 %i1, 100
  br label %lb

lb:                                               ; preds = %le
  br label %lc

lc:                                               ; preds = %lb
  %i2 = load i64, i64* %i
  %ni = add nsw i64 %i2, %div
  store i64 %ni, i64* %i
  br i1 %cond, label %le, label %lo

lo:                                               ; preds = %lc
  %o = load i32, i32* %r
  ret i32 %o
}
