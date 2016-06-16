package code;

//http://practiceit.cs.washington.edu/problem.jsp?category=Building+Java+Programs%2C+3rd+edition%2FBJP3+Chapter+8&problem=bjp3-8-s26-encapsulatedName
///BJP3 Self-Check 8.26: encapsulatedName
//"Encapsulate the Name class. Modify the existing code shown below to make its fields private, and add appropriate accessor methods to the class named getFirstName, getMiddleInitial, and getLastName. "

//http://practiceit.cs.washington.edu/problem.jsp?category=Building+Java+Programs%2C+3rd+edition%2FBJP3+Chapter+8&problem=bjp3-8-s27-mutatorsName
//BJP3 Self-Check 8.27: mutatorsName
//"Add mutator methods called setFirstName, setMiddleInitial, and setLastName to your encapsulated version of the Name class from the last exercise. Give the parameters the same names as your fields, and use the keyword this in your solution. "

public class Name {
    private String firstName;
    private char middleInitial;
    private String lastName;
    
    public Name(String first, char middle, String last) {
        firstName = first;
        middleInitial = middle;
        lastName = last;
    }
    
    public String getFirstName(){
        return this.firstName;
    }

    public char getMiddleInitial(){
        return this.middleInitial;
    }

    public String getLastName(){
        return this.lastName;
    }
    
    public void setFirstName(String firstName){
        this.firstName = firstName;
    }

    public void setMiddleInitial(char middleInitial){
        this.middleInitial = middleInitial;
    }

    public void setLastName(String lastName){
        this.lastName = lastName;
    }
}
