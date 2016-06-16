package LeetCode.Easy;

/**
 * Find the total area covered by two rectilinear rectangles in a 2D plane.
 * 
 * Each rectangle is defined by its bottom left corner and top right corner as shown in the figure.
 * Assume that the total area is never beyond the maximum possible value of int.
 * 
 * 
 * @author ASUS-PC
 *
 */
public class RectangleArea {

    public int computeArea(int A, int B, int C, int D, int E, int F, int G, int H) {
    	int result = (C - A) * (D - B) + (G - E) * (H - F); //Sum of areas of two rectangles
        if(C <= E || G <= A || H <= B || D <= F) return result; //If no overlap at all.
        int dx = Math.min(C, G) - Math.max(A, E); //Overlap length along x
        int dy = Math.min(D, H) - Math.max(B, F); //Overlap length along y
        return result - dx * dy;
    }
}
