package defenseSystem;

enum Strength {
    
    LOW(20),
    MEDIUM(30),
    HIGH(40),
    STRONG(50),
    CLOSED(60);
    
    int value;
    Strength(int value){
        this.value = value;
    }
}
