define i32 @fact(i32* nocapture %n) local_unnamed_addr #0 {
  %nVal.pr = load i32, i32* %n, align 4
  %gt1 = icmp sgt i32 %nVal.pr, 0
  br i1 %gt1, label %body.preheader, label %exit

body.preheader:                                   ; preds = %0
  br label %body

body:                                             ; preds = %body.preheader, %body
  %prod.03 = phi i32 [ %r3, %body ], [ 1, %body.preheader ]
  %nVal2 = phi i32 [ %r5, %body ], [ %nVal.pr, %body.preheader ]
  %r3 = mul i32 %prod.03, %nVal2
  %r5 = add nsw i32 %nVal2, -1
  %gt = icmp sgt i32 %nVal2, 1
  br i1 %gt, label %body, label %cond.exit_crit_edge

cond.exit_crit_edge:                              ; preds = %body
  store i32 0, i32* %n, align 4
  br label %exit

exit:                                             ; preds = %cond.exit_crit_edge, %0
  %prod.0.lcssa = phi i32 [ %r3, %cond.exit_crit_edge ], [ 1, %0 ]
  ret i32 %prod.0.lcssa
}

; Function Attrs: norecurse nounwind readnone
define i32 @main() local_unnamed_addr #1 {
fact.exit:
  ret i32 120
}

