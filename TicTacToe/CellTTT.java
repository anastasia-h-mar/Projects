public class CellTTT {
    private char myValue;

    public CellTTT(char value) {
        myValue = value;
    }

    public void updateValue(char value) {
        if (myValue == '-') {
            myValue = value;
        }
    }

    public char getValue() {
        return myValue;
    }

    public String toString() {
        return myValue + "";
    }
}