;;;;;;;;;;;;;;;;;;;;;
;;; CONSTANT POOL ;;;
;;;;;;;;;;;;;;;;;;;;;
@bool_false = constant %Bool {
  %Bool_vtable* @Bool_vtable_prototype,
  i1 0
}
@bool_true = constant %Bool {
  %Bool_vtable* @Bool_vtable_prototype,
  i1 1
}
@int_0 = constant %Int {
  %Int_vtable* @Int_vtable_prototype,
  i32 0
}
@int_13 = constant %Int {
  %Int_vtable* @Int_vtable_prototype,
  i32 13
}
@int_35 = constant %Int {
  %Int_vtable* @Int_vtable_prototype,
  i32 35
}
@int_42 = constant %Int {
  %Int_vtable* @Int_vtable_prototype,
  i32 42
}

@str_0 = constant [1 x i8] c"\00";
@string_0 = constant %String {
  %String_vtable* @String_vtable_prototype,
  i32 0,
  i8* getelementptr ([1 x i8], [1 x i8]* @str_0, i32 0, i32 0)
}

@str_1 = constant [5 x i8] c"Main\00";
@string_1 = constant %String {
  %String_vtable* @String_vtable_prototype,
  i32 4,
  i8* getelementptr ([5 x i8], [5 x i8]* @str_1, i32 0, i32 0)
}
;;;;;;;;;;;;;;;;;;;;;
;;;  DATA LAYOUT  ;;;
;;;;;;;;;;;;;;;;;;;;;
%Main = type {
  %Main_vtable*
}
%Main_vtable = type {
  i32, ;; class tag
  %String*, ;; type name
  %Main* () *,
  %String* (%Main*)*,
  %Object* (%Main*)*,
  %Int* (%Main*)*
}
@Main_vtable_prototype = constant %Main_vtable {
  i32 5,
  %String* @string_1,
  %Main* () * @Main_new,
  %String* (%Main*)* bitcast (%String* (%Object*)* @Object_type_name to %String* (%Main*)*),
  %Object* (%Main*)* bitcast (%Object* (%Object*)* @Object_abort to %Object* (%Main*)*),
  %Int* (%Main*)* @Main_main
}
;;;;;;;;;;;;;;;;;;;;;
;;;    METHODS    ;;;
;;;;;;;;;;;;;;;;;;;;;
define %Main* @Main_new() {
  %r0 = call i8* @malloc(i64 8)
  %r1 = bitcast i8* %r0 to %Main*
  ;; setting vtable
  %r2 = getelementptr %Main, %Main* %r1, i32 0, i32 0
  store %Main_vtable* @Main_vtable_prototype, %Main_vtable** %r2
  ret %Main* %r1
}

define %Int* @Main_main(%Main* %self) {
entry_0:
  ;; unboxing %Int* @int_42
  %r0 = getelementptr %Int, %Int* @int_42, i32 0, i32 1
  %r1 = load i32, i32* %r0
  ;; unboxing %Int* @int_35
  %r2 = getelementptr %Int, %Int* @int_35, i32 0, i32 1
  %r3 = load i32, i32* %r2
  %r4 = icmp eq i32 %r1, %r3
  ;; boxing i1 %r4
  %r5 = call %Bool* @Bool_new()
  %r6 = getelementptr %Bool, %Bool* %r5, i32 0, i32 1
  store i1 %r4, i1* %r6
  ;; emitting an 'if'
  %r7 = alloca %Int*
  ;; unboxing %Bool* %r5
  %r8 = getelementptr %Bool, %Bool* %r5, i32 0, i32 1
  %r9 = load i1, i1* %r8
  br i1 %r9, label %then_1, label %else_2
then_1:
  store %Int* @int_13, %Int** %r7
  br label %ifend_3
else_2:
  store %Int* @int_42, %Int** %r7
  br label %ifend_3
ifend_3:
  %r10 = load %Int*, %Int** %r7
  ret %Int* %r10
}

define i32 @main() {
  %mainobj = call %Main* @Main_new()
  %r0 = call %Int* @Main_main(%Main* %mainobj)
  ;; unboxing %Int* %r0
  %r1 = getelementptr %Int, %Int* %r0, i32 0, i32 1
  %r2 = load i32, i32* %r1
  ret i32 %r2
}

