/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package aorta.kr;

import gov.nasa.jpf.annotation.MJI;
import gov.nasa.jpf.vm.MJIEnv;
import gov.nasa.jpf.vm.NativePeer;

/**
 *
 * @author asj
 */
public class JPF_aorta_kr_MentalState extends NativePeer {

  @MJI
  public int addAgentOwnName__Ljava_lang_String_2__Lalice_tuprolog_Struct_2 (MJIEnv env, int objRef, int rString0) {
    int rStruct = MJIEnv.NULL;
    return rStruct;
  }

  @MJI
  public int getProlog____Lalice_tuprolog_Prolog_2 (MJIEnv env, int objRef) {
    int rProlog = MJIEnv.NULL;
    return rProlog;
  }

  @MJI
  public int solve__Lalice_tuprolog_Term_2__Lalice_tuprolog_SolveInfo_2 (MJIEnv env, int objRef, int rTerm0) {
    int rSolveInfo = MJIEnv.NULL;
    return rSolveInfo;
  }

  @MJI
  public int solve__Laorta_reasoning_fml_Formula_2__Lalice_tuprolog_SolveInfo_2 (MJIEnv env, int objRef, int rFormula0) {
    int rSolveInfo = MJIEnv.NULL;
    return rSolveInfo;
  }

  @MJI
  public int solve__Ljava_lang_String_2__Lalice_tuprolog_SolveInfo_2 (MJIEnv env, int objRef, int rString0) {
    int rSolveInfo = MJIEnv.NULL;
    return rSolveInfo;
  }

  @MJI
  public int toStructs__Laorta_kr_KBType_2__Ljava_util_List_2 (MJIEnv env, int objRef, int rKBType0) {
    int rList = MJIEnv.NULL;
    return rList;
  }

  @MJI
  public int mergeKBs__Laorta_kr_KBType_2Ljava_util_List_2___3Ljava_util_List_2 (MJIEnv env, int objRef, int rKBType0, int rList1) {
    int rList = MJIEnv.NULL;
    return rList;
  }

  @MJI
  public boolean hasMoreSolutions____Z (MJIEnv env, int objRef) {
    boolean v = false;//(boolean)0;
    return v;
  }

  @MJI
  public int solveNext____Lalice_tuprolog_SolveInfo_2 (MJIEnv env, int objRef) {
    int rSolveInfo = MJIEnv.NULL;
    return rSolveInfo;
  }

  @MJI
  public int findAll__Laorta_reasoning_fml_Formula_2__Ljava_util_List_2 (MJIEnv env, int objRef, int rFormula0) {
    int rList = MJIEnv.NULL;
    return rList;
  }

  @MJI
  public int findAll__Lalice_tuprolog_Term_2__Ljava_util_List_2 (MJIEnv env, int objRef, int rTerm0) {
    int rList = MJIEnv.NULL;
    return rList;
  }

  @MJI
  public int findAll__Ljava_lang_String_2__Ljava_util_List_2 (MJIEnv env, int objRef, int rString0) {
    int rList = MJIEnv.NULL;
    return rList;
  }

  @MJI
  public void unify__Lalice_tuprolog_Term_2Ljava_util_List_2__V (MJIEnv env, int objRef, int rTerm0, int rList1) {
  }

  @MJI
  public void unify__Lalice_tuprolog_Term_2Lalice_tuprolog_SolveInfo_2__V (MJIEnv env, int objRef, int rTerm0, int rSolveInfo1) {
  }

  @MJI
  public int getVars__Lalice_tuprolog_Term_2__Ljava_util_List_2 (MJIEnv env, int objRef, int rTerm0) {
    int rList = MJIEnv.NULL;
    return rList;
  }

  @MJI
  public boolean remove__Lalice_tuprolog_Struct_2__Z (MJIEnv env, int objRef, int rStruct0) {
    boolean v = false;//(boolean)0;
    return v;
  }

  @MJI
  public int toString____Ljava_lang_String_2 (MJIEnv env, int objRef) {
    int rString = MJIEnv.NULL;
    return rString;
  }

  @MJI
  public void insert__Lalice_tuprolog_Struct_2__V (MJIEnv env, int objRef, int rStruct0) {
  }

  @MJI
  public boolean exists__Lalice_tuprolog_Term_2__Z (MJIEnv env, int objRef, int rTerm0) {
    boolean v = false;//(boolean)0;
    return v;
  }
}
