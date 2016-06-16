package code;

//As part of http://practiceit.cs.washington.edu/problem.jsp?category=Building+Java+Programs%2C+3rd+edition%2FBJP3+Chapter+8&problem=bjp3-8-s21-constructorPoint
//BJP3 Self-Check 8.21: constructorPoint
//"Add a constructor to the Point class that accepts another Point as a parameter and initializes the new Point to have the same (x, y) values. Use the keyword this in your solution. "

public class Point {

	int x;
	int y;
	
	public Point(int x, int y){
		this.x = x;
		this.y = y;
	}
	
	public Point(Point pt){
	    this.x = pt.x;
	    this.y = pt.y;
	}
	
	//http://practiceit.cs.washington.edu/problem.jsp?category=Building+Java+Programs%2C+3rd+edition%2FBJP3+Chapter+8&problem=bjp3-8-s25-setXYPoint
	//BJP3 Self-Check 8.25: setXYPoint
	//"Add methods named setX and setY to the Point class. Each method accepts an integer parameter and changes the Point object's x- or y-coordinate to be the value passed, respectively. "
	public void setX(int x){
	    this.x = x;
	}

	public void setY(int y){
	    this.y = y;
	}
	
	//http://practiceit.cs.washington.edu/problem.jsp?category=Building+Java+Programs%2C+3rd+edition%2FBJP3+Chapter+8&problem=bjp3-8-e1-quadrantPoint
	//BJP3 Exercise 8.1: quadrantPoint
	//" Add the following method to the Point class:public int quadrant()
	// Returns which quadrant of the x/y plane this Point object falls in. Quadrant 1 contains all points whose x and y values are both positive. Quadrant 2 contains all points with negative x but positive y. Quadrant 3 contains all points with negative x and y values. Quadrant 4 contains all points with positive x but negative y. If the point lies directly on the x and/or y axis, return 0. "
	public int quadrant(){
	    if(this.x > 0 && this.y > 0)
	        return 1;
	    else if(this.x > 0 && this.y < 0)
	        return 4;
	    else if(this.x < 0 && this.y > 0)
	        return 2;
	    else if(this.x < 0 && this.y < 0)
	        return 3;
	    else
	        return 0;
	}
	
	//http://practiceit.cs.washington.edu/problem.jsp?category=Building+Java+Programs%2C+3rd+edition%2FBJP3+Chapter+8&problem=bjp3-8-e2-flipPoint
	//BJP3 Exercise 8.2: flipPoint
	//" Add the following method to the Point class:
	//public void flip()
	//Negates and swaps the x/y coordinates of the Point object. For example, if the object initially represents the point (5, -3), after a call to flip, the object should represent (3, -5). If the object initially represents the point (4, 17), after a call to flip, the object should represent (-17, -4)." 
	
	public void flip(){
	    int origx = this.x;
	    this.x = -1 * this.y;
	    this.y = -1 * origx;
	}
	
	//http://practiceit.cs.washington.edu/problem.jsp?category=Building+Java+Programs%2C+3rd+edition%2FBJP3+Chapter+8&problem=bjp3-8-e3-manhattanDistancePoint
	//BJP3 Exercise 8.3: manhattanDistancePoint
	//"Add the following method to the Point class:
	//public int manhattanDistance(Point other)
	//Returns the "Manhattan distance" between the current Point object and the given other Point object. The Manhattan distance refers to how far apart two places are if the person can only travel straight horizontally or vertically, as though driving on the streets of Manhattan. In our case, the Manhattan distance is the sum of the absolute values of the differences in their coordinates; in other words, the difference in x plus the difference in y between the points. "
	public int manhattanDistance(Point other){
	    return Math.abs(this.x - other.x) + Math.abs(this.y - other.y);
	}
	
	//http://practiceit.cs.washington.edu/problem.jsp?category=Building+Java+Programs%2C+3rd+edition%2FBJP3+Chapter+8&problem=bjp3-8-e4-isVerticalPoint
	//BJP3 Exercise 8.4: isVerticalPoint
	//"Add the following method to the Point class:
	//public boolean isVertical(Point other)
	//Returns true if the given Point lines up vertically with this Point; that is, if their x coordinates are the same. "
	public boolean isVertical(Point other){
	    return this.x == other.x;
	}
	
	//http://practiceit.cs.washington.edu/problem.jsp?category=Building+Java+Programs%2C+3rd+edition%2FBJP3+Chapter+8&problem=bjp3-8-e5-slopePoint
	//BJP3 Exercise 8.5: slopePoint
	//"Add the following method to the Point class: 
	//public double slope(Point other)
	//Returns the slope of the line drawn between this Point and the given other Point. Use the formula (y2 - y1) / (x2 - x1) to determine the slope between two points (x1, y1) and (x2, y2). Note that this formula fails for points with identical x-coordinates, so throw an IllegalArgumentException in this case."	
	public double slope(Point other){
	    if(this.x == other.x)
	        throw new IllegalArgumentException();
	    
	    return (double)(other.y-this.y)/(other.x-this.x);
	}
	
	//http://practiceit.cs.washington.edu/problem.jsp?category=Building+Java+Programs%2C+3rd+edition%2FBJP3+Chapter+8&problem=bjp3-8-e6-isCollinearPoint
	//BJP3 Exercise 8.6: isCollinearPoint
	//"Add the following method to the Point class:
	//public boolean isCollinear(Point p1, Point p2)
	//Returns whether this Point is collinear with the given two other points. Points are collinear if a straight line can be drawn that connects them. Two basic examples are three points that have the same x- or y-coordinate. The more general case can be determined by calculating the slope of the line between each pair of points and checking whether this slope is the same for all pairs of points. Use the formula (y2 - y1) / (x2 - x1) to determine the slope between two points (x1, y1) and (x2, y2). (Note that this formula fails for points with identical x-coordinates so this will have to be special-cased in your code.)"
	public boolean isCollinear(Point p1, Point p2){
	    if(p1.equals(this) && p2.equals(this))
	        return true;
	    
	    double slopePt1_Pt0 = (double)(p1.y-this.y)/(p1.x-this.x);
	    double slopePt1_Pt2 =(double)(p2.y-p1.y)/(p2.x-p1.x);                       
	    return slopePt1_Pt0 == slopePt1_Pt2;
	}
	
}
