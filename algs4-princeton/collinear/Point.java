/******************************************************************************
 *  Compilation:  javac Point.java
 *  Execution:    java Point
 *  Dependencies: none
 *  
 *  An immutable data type for points in the plane.
 *  For use on Coursera, Algorithms Part I programming assignment.
 *
 ******************************************************************************/

import java.util.Comparator;
import edu.princeton.cs.algs4.StdDraw;
import edu.princeton.cs.algs4.StdOut;

public class Point implements Comparable<Point> {

    private final int x;     // x-coordinate of this point
    private final int y;     // y-coordinate of this point
    public final Comparator<Point> SLOPE_ORDER = new SlopeOrder();

    /**
     * Initializes a new point.
     *
     * @param  x the <em>x</em>-coordinate of the point
     * @param  y the <em>y</em>-coordinate of the point
     */
    public Point(int x, int y) {
        /* DO NOT MODIFY */
        this.x = x;
        this.y = y;
    }

    /**
     * Draws this point to standard draw.
     */
    public void draw() {
        /* DO NOT MODIFY */
        StdDraw.point(x, y);
    }

    /**
     * Draws the line segment between this point and the specified point
     * to standard draw.
     *
     * @param that the other point
     */
    public void drawTo(Point that) {
        /* DO NOT MODIFY */
        StdDraw.line(this.x, this.y, that.x, that.y);
    }

    /**
     * Returns the slope between this point and the specified point.
     * Formally, if the two points are (x0, y0) and (x1, y1), then the slope
     * is (y1 - y0) / (x1 - x0). For completeness, the slope is defined to be
     * +0.0 if the line segment connecting the two points is horizontal;
     * Double.POSITIVE_INFINITY if the line segment is vertical;
     * and Double.NEGATIVE_INFINITY if (x0, y0) and (x1, y1) are equal.
     *
     * @param  that the other point
     * @return the slope between this point and the specified point
     */
    public double slopeTo(Point that) {
        /* YOUR CODE HERE */
        double dy = this.y - that.y;
        double dx = this.x - that.x;
        if (dy == 0.0 && dx == 0.0) return Double.NEGATIVE_INFINITY;
        if (dx == 0.0) return Double.POSITIVE_INFINITY;
        if (dy == 0.0) return +0.0;
        return dy / dx;
    }

    /**
     * Compares two points by y-coordinate, breaking ties by x-coordinate.
     * Formally, the invoking point (x0, y0) is less than the argument point
     * (x1, y1) if and only if either y0 < y1 or if y0 = y1 and x0 < x1.
     *
     * @param  that the other point
     * @return the value <tt>0</tt> if this point is equal to the argument
     *         point (x0 = x1 and y0 = y1);
     *         a negative integer if this point is less than the argument
     *         point; and a positive integer if this point is greater than the
     *         argument point
     */
    public int compareTo(Point that) {
        /* YOUR CODE HERE */
        int dy = this.y - that.y;
        int dx = this.x - that.x;
        
        if (dy > 0) return +1;
        if (dy < 0) return -1;
        if (dx > 0) return +1;
        if (dx < 0) return -1;
        return 0;
    }

    private class SlopeOrder implements Comparator<Point>
    {
        @Override
        public int compare(Point p1, Point p2) {
            double slope1 = slopeTo(p1);
            double slope2 = slopeTo(p2);
            if (slope1 < slope2) return -1;
            if (slope1 > slope2) return +1;
            return 0;
        }
    }

    /**
     * Compares two points by the slope they make with this point.
     * The slope is defined as in the slopeTo() method.
     *
     * @return the Comparator that defines this ordering on points
     */
    public Comparator<Point> slopeOrder() {
        /* YOUR CODE HERE */
        return SLOPE_ORDER;
    }

    /**
     * Returns a string representation of this point.
     * This method is provide for debugging;
     * your program should not rely on the format of the string representation.
     *
     * @return a string representation of this point
     */
    public String toString() {
        /* DO NOT MODIFY */
        return "(" + x + ", " + y + ")";
    }

    /**
     * Unit tests the Point data type.
     */
    public static void main(String[] args) {
        /* YOUR CODE HERE */
        {
            Point p0 = new Point(0, 0);
            Point p1 = new Point(0, 0);
            assert p0.slopeTo(p1) == Double.NEGATIVE_INFINITY;
            StdOut.println(p0.slopeTo(p1));
        }
        {
            Point p0 = new Point(0, 0);
            Point p1 = new Point(0, 1);
            assert p0.slopeTo(p1) == Double.POSITIVE_INFINITY;
            StdOut.println(p0.slopeTo(p1));
        }
        {
            Point p0 = new Point(0, 0);
            Point p1 = new Point(0, -1);
            assert p0.slopeTo(p1) == Double.POSITIVE_INFINITY;
            StdOut.println(p0.slopeTo(p1));
        }
        {
            Point p0 = new Point(0, 0);
            Point p1 = new Point(1, 1);
            assert p0.slopeTo(p1) == 1.0;
            StdOut.println(p0.slopeTo(p1));
        }
        {
            Point p0 = new Point(0, 0);
            Point p1 = new Point(1, -1);
            assert p0.slopeTo(p1) == -1.0;
            StdOut.println(p0.slopeTo(p1));
        }
        {
            Point p0 = new Point(0, 0);
            Point p1 = new Point(1, -1);
            Point p2 = new Point(1, -1);
            assert p0.slopeOrder().compare(p1, p2) == 0;
            StdOut.println(p0.slopeOrder().compare(p1, p2));
        }
        {
            Point p0 = new Point(0, 0);
            Point p1 = new Point(1, 1);
            Point p2 = new Point(1, -1);
            assert p0.slopeOrder().compare(p1, p2) == 1;
            StdOut.println(p0.slopeOrder().compare(p1, p2));
        }
        {
            Point p0 = new Point(0, 0);
            Point p1 = new Point(1, -1);
            Point p2 = new Point(1, 1);
            assert p0.slopeOrder().compare(p1, p2) == -1;
            StdOut.println(p0.slopeOrder().compare(p1, p2));
        }
        {
            Point p0 = new Point(0, 0);
            Point p1 = new Point(0, 1);
            assert p0.compareTo(p1) == -1;
            StdOut.println(p0.compareTo(p1));
        }
        {
            Point p0 = new Point(0, 0);
            Point p1 = new Point(-1, 0);
            assert p0.compareTo(p1) == 1;
            StdOut.println(p0.compareTo(p1));
        }
        {
            Point p0 = new Point(0, 0);
            Point p1 = new Point(0, 0);
            assert p0.compareTo(p1) == 0;
            StdOut.println(p0.compareTo(p1));
        }
    }
}