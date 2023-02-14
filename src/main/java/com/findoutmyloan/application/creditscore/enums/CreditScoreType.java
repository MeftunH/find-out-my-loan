package com.findoutmyloan.application.creditscore.enums;
/* @author - Maftun Hashimli (maftunhashimli@gmail.com)) */

public enum CreditScoreType {
    LOW_CREDIT_SCORE(0,500),
    MEDIUM_CREDIT_SCORE(500,1000),
    HIGH_CREDIT_SCORE(1000,2000),
    ;
    private int minimumCreditScore;
    private int maximumCreditScore;
    CreditScoreType(int minimumCreditScore,int maximumCreditScore) {
        this.minimumCreditScore = minimumCreditScore;
        this.maximumCreditScore = maximumCreditScore;
    }
    private static boolean isCreditScoreInLowRange(int creditScore){
        return creditScore>=LOW_CREDIT_SCORE.minimumCreditScore&&creditScore<=LOW_CREDIT_SCORE.maximumCreditScore;
    }
    private static boolean isCreditScoreInMediumRange(int creditScore){
        return creditScore>=MEDIUM_CREDIT_SCORE.minimumCreditScore&&creditScore<=MEDIUM_CREDIT_SCORE.maximumCreditScore;
    }
    private static boolean isCreditScoreInHighRange(int creditScore){
        return creditScore>=HIGH_CREDIT_SCORE.minimumCreditScore&&creditScore<=HIGH_CREDIT_SCORE.maximumCreditScore;
    }

    public int getMinimumCreditScore() {
        return minimumCreditScore;
    }

    public int getMaximumCreditScore() {
        return maximumCreditScore;
    }

    public static CreditScoreType getCreditScoreType(int creditScore){
        if(isCreditScoreInLowRange(creditScore)){
            return LOW_CREDIT_SCORE;
        }else if(isCreditScoreInMediumRange(creditScore)){
            return MEDIUM_CREDIT_SCORE;
        }else if(isCreditScoreInHighRange(creditScore)){
            return HIGH_CREDIT_SCORE;
        }
        return null;
    }
}
