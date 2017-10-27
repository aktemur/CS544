define i32 @main() {
  %r = alloca i32
  %i = alloca i64
  %a = alloca i64
  store i64 0, i64* %i
  store i64 5, i64* %a
  br label %le

le:                                               ; preds = %lc, %0
  %i1 = load i64, i64* %i
  %cond1 = icmp slt i64 %i1, 100
  br i1 %cond1, label %lb1, label %lo

lb1:                                              ; preds = %le
  br label %ifc

ifc:                                              ; preds = %lb1
  %i3 = load i64, i64* %i
  %cond2 = icmp slt i64 %i1, 10
  br i1 %cond2, label %iftrue, label %iffalse

iffalse:                                          ; preds = %ifc
  br label %lb2

iftrue:                                           ; preds = %ifc
  %div = sdiv i64 5, 0
  br label %lb2

lb2:                                              ; preds = %iftrue, %iffalse
  br label %lc

lc:                                               ; preds = %lb2
  %i2 = load i64, i64* %i
  %ni = add nsw i64 %i2, 1
  store i64 %ni, i64* %i
  br label %le

lo:                                               ; preds = %le
  %o = load i32, i32* %r
  ret i32 %o
}
