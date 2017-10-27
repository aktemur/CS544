#define DEBUG_TYPE "cs544mba"

#include "llvm/Support/Debug.h"
#include "llvm/ADT/Statistic.h"
STATISTIC(MBACount, "The # of modified instructions");

#include "llvm/Pass.h"
#include "llvm/IR/IRBuilder.h"
#include "llvm/IR/Module.h"
#include "llvm/IR/Function.h"
#include "llvm/Support/raw_ostream.h"
#include "llvm/IR/InstrTypes.h"
#include "llvm/Transforms/Utils/BasicBlockUtils.h"

/*****************************************
 * Pass implementation
 *****************************************/

using namespace llvm;

// anonymous namespace -> avoid exporting unneeded symbols
namespace {

// A pass that perform a simple instruction substitution
// see http://llvm.org/docs/WritingAnLLVMPass.html#the-basicblockpass-class
class MBA : public BasicBlockPass {

public:
  // The address of this static is used to uniquely identify this pass in the
  // pass registry. The PassManager relies on this address to find instance of
  // analyses passes and build dependencies on demand.
  // The value does not matter.
  static char ID;

  MBA() : BasicBlockPass(ID) { }

  // Called once for each module, before the calls on the basic blocks.
  bool doInitialization(Module &M) override {
    // do nothing
    return false;
  }
  
  // Called for each basic block of the module
  // Rely on the equality: a + b == (a ^ b) + 2 * (a & b)
  bool runOnBasicBlock(BasicBlock &BB) override {
    bool modified = false;

    // Can't use a for-range loop because we want to delete the instruction from
    // the list we're iterating when replacing it.
    for (Instruction &Inst : BB) {
      // not a regular C++ dynamic_cast!
      // see http://llvm.org/docs/ProgrammersManual.html#the-isa-cast-and-dyn-cast-templates
      auto *BinOp = dyn_cast<BinaryOperator>(&Inst);
      if (!BinOp)
        // The instruction is not a binary operator, we don't handle it.
        continue;

      unsigned Opcode = BinOp->getOpcode();
      if (Opcode != Instruction::Add || !BinOp->getType()->isIntegerTy())
        // Only handle integer add.
        // Note instead of doing a dyn_cast to a BinaryOperator above, we could
        // have done directly a dyn_cast to AddOperator, but this way seems more
        // effective to later add other operators.
        continue;

      // The IRBuilder helps you inserting instructions in a clean and fast way
      // see http://llvm.org/docs/ProgrammersManual.html#creating-and-inserting-new-instructions
      IRBuilder<> Builder(BinOp);

      Value *NewValue = Builder.CreateAdd(Builder.CreateXor(BinOp->getOperand(0),
                                                            BinOp->getOperand(1)),
                                          Builder.CreateMul(ConstantInt::get(BinOp->getType(), 2),
                                                            Builder.CreateAnd(BinOp->getOperand(0),
                                                                              BinOp->getOperand(1)))
                                          );

      // ReplaceInstWithValue basically does this (`IIT' is passed by reference):
      // IIT->replaceAllUsesWith(NewValue);
      // IIT = BB.getInstList.erase(IIT);
      //
      // `erase' returns a valid iterator of the instruction before the
      // one that has been erased. This keeps iterators valid.
      //
      // see also
      // http://llvm.org/docs/ProgrammersManual.html#replacing-an-instruction-with-another-value
      BasicBlock::iterator it(Inst);
      ReplaceInstWithValue(BB.getInstList(), it, NewValue);
      modified = true;

      // update statistics!
      // They are printed out with -stats on the opt command line
      ++MBACount;
    }
    return modified;
  }
};
}

// pass registration is done through the constructor of static objects...

/* opt pass registration */
char MBA::ID = 0;
static RegisterPass<MBA> X("cs544mba",  // the option name -> -cs544mba
                           "Mixed Boolean Arithmetic Substitution (CS544)", // option description
                           true, // true as we don't modify the CFG
                           false // true if we're writing an analysis
                           );
