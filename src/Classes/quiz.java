package Classes;

public class quiz {
    String question,  opA ,  opB,  opC,  opD;
    int ans;

    public int getAns() {
        return ans;
    }

    public String getQuestion() {
        return question;
    }

    public String getOpA() {
        return opA;
    }

    public String getOpB() {
        return opB;
    }

    public String getOpC() {
        return opC;
    }

    public String getOpD() {
        return opD;
    }

    public quiz() {
    }

    public quiz(String question, String opA, String opB, String opC, String opD, int ans) {
        this.question = question;
        this.opA = opA;
        this.opB = opB;
        this.opC = opC;
        this.opD = opD;
        this.ans = ans;
    }
    public void setter(String question, String opA, String opB, String opC, String opD, int ans) {
        this.question = question;
        this.opA = opA;
        this.opB = opB;
        this.opC = opC;
        this.opD = opD;
        this.ans = ans;
    }
}
