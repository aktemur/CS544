#include "llvm/IR/Function.h"
#include "llvm/IR/Dominators.h"
#include "llvm/Analysis/LoopInfo.h"
#include "llvm/Analysis/LoopPass.h"
#include "llvm/Transforms/Scalar.h"
#include "llvm/Analysis/LoopInfo.h"
#include "llvm/Analysis/ValueTracking.h"
#include <iostream>

using namespace llvm;

namespace {
  class LICM : public LoopPass {
  public:
    static char ID; // Pass identification, value is not significant

    LICM() : LoopPass(ID) {}

    /// This transformation requires natural loop information & requires that
    /// loop preheaders be inserted into the CFG...
    virtual void getAnalysisUsage(AnalysisUsage &AU) const {
      AU.setPreservesCFG();
      AU.addRequiredID(LoopSimplifyID);
      AU.addRequired<LoopInfoWrapperPass>();
      AU.addRequired<DominatorTreeWrapperPass>();
    }

    virtual bool runOnLoop(Loop *L, LPPassManager &LPM) {
      bool changed = false;
      BasicBlock *preHeaderBlock = L->getLoopPreheader();
      Instruction *preHeaderTerminator = preHeaderBlock->getTerminator();
      DominatorTreeWrapperPass &DTWP = getAnalysis<DominatorTreeWrapperPass>();
      DominatorTree &DT = DTWP.getDomTree();
      LoopInfo &LI = getAnalysis<LoopInfoWrapperPass>().getLoopInfo();
      std::vector<Instruction*> instructionsToMove;
      
      // TODO: Implement LICM

      return changed;
    }
  };
}

char LICM::ID = 0;
RegisterPass<LICM> X("cs544licm", "Loop Invariant Code Motion (CS544)");
