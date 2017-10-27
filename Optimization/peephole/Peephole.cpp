#define DEBUG_TYPE "cs544peephole"

#include "llvm/Support/Debug.h"
#include "llvm/ADT/Statistic.h"
STATISTIC(PeepholeCount, "The # of modified instructions");

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
class Peephole : public BasicBlockPass {

public:
  // The address of this static is used to uniquely identify this pass in the
  // pass registry. The PassManager relies on this address to find instance of
  // analyses passes and build dependencies on demand.
  // The value does not matter.
  static char ID;

  Peephole() : BasicBlockPass(ID) { }

  // Called once for each module, before the calls on the basic blocks.
  bool doInitialization(Module &M) override {
    // do nothing
    return false;
  }
  
  // Called for each basic block of the module
  // Turn an integer multiplication by a power of 2 into a shift operation
  bool runOnBasicBlock(BasicBlock &BB) override {
    bool modified = false;

    // TODO
    
    return modified;
  }
};
}

// pass registration is done through the constructor of static objects...

/* opt pass registration */
char Peephole::ID = 0;
static RegisterPass<Peephole> X("cs544peephole",  // the option name -> -cs544peephole
                           "Peephole optimization (CS544)", // option description
                           true, // true as we don't modify the CFG
                           false // true if we're writing an analysis
                           );
