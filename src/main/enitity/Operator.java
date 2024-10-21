package entity;

public enum Operator {
    MTS("MTS"),
    LIFE("Life"),
    A1("A1"),
    BEELINE("Beeline"),
    MEGAFON("Megafon"),
    TELE2("Tele2");

    private String name;

    Operator(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public static Operator getOperatorByName(String name) throws CustomException {
        for (Operator operator : Operator.values()) {
            if (operator.name.equalsIgnoreCase(name)) {
                return operator;
            }
        }
        throw new CustomException("Unknown operator name: " + name);
    }
}
