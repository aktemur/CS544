#include "llvm/Pass.h"
#include "llvm/IR/Function.h"
#include "llvm/Analysis/PostDominators.h"
#include "llvm/Transforms/Utils/UnifyFunctionExitNodes.h"
#include <iostream>

using namespace llvm;

namespace {
  class ADCE : public FunctionPass {
  private:
    std::vector<Instruction*> workList;   // Instructions that just became live
    std::set<Instruction*>    liveSet;    // The set of live instructions
    
  public:
    static char ID; // Pass identification, value not significant

    ADCE() : FunctionPass(ID) {}
    
    virtual void getAnalysisUsage(AnalysisUsage &AU) const {
      // We require that all function nodes are unified, because otherwise code
      // can be marked live that wouldn't necessarily be otherwise.
      AU.addRequired<UnifyFunctionExitNodes>();
      AU.addRequired<PostDominatorTreeWrapperPass>();
      AU.setPreservesCFG();
    }
    
    virtual bool runOnFunction(Function &F) {
      bool changed = false;
      // Implement the algorithm here

      
      assert(workList.empty());
      liveSet.clear();
      // return true if the function was modified.
      return changed;
    }
  };
}
  
char ADCE::ID = 0;
static RegisterPass<ADCE> X("cs544adce", "Aggressive Dead Code Elimination (CS544)");
