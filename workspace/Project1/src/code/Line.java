package code;

//http://practiceit.cs.washington.edu/problem.jsp?category=Building+Java+Programs%2C+3rd+edition%2FBJP3+Chapter+8&problem=bjp3-8-e14-classLine
//BJP3 Exercise 8.14: classLine
/*Write a class called Line that represents a line segment between two Points. Your Line objects should have the following methods:
public Line(Point p1, Point p2)
Constructs a new line that contains the given two points.

public Point getP1()
Returns this line's first endpoint.

public Point getP2()
Returns this line's second endpoint.

public String toString()
Returns a string representation of this line, such as "[(22, 3), (4, 7)]".
*/

public class Line {
    Point p1;
    Point p2;
    
    
    /* http://practiceit.cs.washington.edu/problem.jsp?category=Building+Java+Programs%2C+3rd+edition%2FBJP3+Chapter+8&problem=bjp3-8-e16-constructorLine
     * BJP Exercise 8.16: constructorLine
     * " Add the following constructor to your Line class from the preceding exercises:
		public Line(int x1, int y1, int x2, int y2)
		Constructs a new line that contains the given two points. "
     */
    public Line(int x1, int y1, int x2, int y2){
        Point p1 = new Point(x1,y1);
        Point p2 = new Point(x2,y2);
        
        this.p1 = p1;
        this.p2 = p2;
    }
    
    public Line(Point p1, Point p2){
        this.p1 = p1;
        this.p2 = p2;
    }
    
    public Point getP1(){
        return this.p1;
    }
    
    public Point getP2(){
        return this.p2;
    }

    //http://practiceit.cs.washington.edu/problem.jsp?category=Building+Java+Programs%2C+3rd+edition%2FBJP3+Chapter+8&problem=bjp3-8-e15-getSlopeLine
    //BJP3 Exercise 8.15: getSlopeLine
    /*"Add the following method to your Line class from the previous exercise: 
     * public double getSlope()
	   Returns the slope of this line. The slope of a line between points (x1, y1) and (x2, y2) is equal to (y2 - y1) / (x2 - x1). If the two points have the same x-coordinates, the denominator is zero and the slope is undefined, so you should throw an IllegalStateException in this case." 
     */
    public double getSlope(){
        if(p1.x == p2.x)
            throw new IllegalStateException();
        
        return (double)(p2.y-p1.y)/(p2.x-p1.x);
    }
    
    public String toString(){
        return "[(" + this.p1.x + ", " + this.p1.y + "), (" + this.p2.x + ", " + this.p2.y + ")]";
    }
}
