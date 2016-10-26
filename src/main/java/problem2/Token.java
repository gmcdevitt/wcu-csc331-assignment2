package main.java.problem2;

class Token {

    private int token;

    Token() {
        this.token = 0;
    }

    int getValue() {
        return token;
    }

    void update() {
        token = ++token % 3;
    }
}
