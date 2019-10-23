package quadTree;

/*
 * https://www.careercup.com/question?id=4786604149309440
 * 
 * 
It is a continuation of the previous question. 
Define a quad-tree for a black and white image. Count the number the of black pixels.

https://www.careercup.com/question?id=6491225129484288

https://www.careercup.com/question?id=4807138387951616

http://algs4.cs.princeton.edu/92search/QuadTree.java

http://algs4.cs.princeton.edu/92search/QuadTree.java.html

http://algs4.cs.princeton.edu/92search/

http://www.java-gaming.org/index.php?topic=26844.0

https://en.wikipedia.org/wiki/Quadtree


Assumption: While a pairwise intersection of corresponding pixels in the two images....Intersection is white iff both pixels of the pair are white. 

Then you can do the recursive intersection as follows: 
let A and B be to corresponding nodes in the two trees (or their subtrees)

if rootA.color == "black":
    return A
if rootA.color == "white":
    return B
if rootA.color == "gray" and rootB.color == "gray".
##Pairwise intersect the subtrees of A and B.
##If all four of the returned subtrees have a black root, return a single node tree with a black root.
Otherwise return a tree with a gray root and the four returned subtrees as its subtrees.

Complexity is O(min{|A|, |B|}).
514065477227646067
*/

/*
Quad Tree

-----------------------------------------------------------------          -----------------------------------------------------------------
|       |       |       |       |       |       |       |       |          |       |       |       |       |       |       |       |       |
|       |       |       |       |       |       |       |       |          |       |       |       |       |       |       |       |   B   |
|       |       |       |       |       |       |       |       |          |       |       |       |       |       |       |       |       |
-----------------------------------------------------------------          -----------------------------------------------------------------
|       |       |       |       |       |       |       |       |          |       |       |       |       |       |       |       |       |
|       |       |       |       |       |       |       |       |          |       |   B   |       |       |       |       |       |       |
|       |       |       |       |       |       |       |       |          |       |       |       |       |       |       |       |       |
-----------------------------------------------------------------          -----------------------------------------------------------------
|       |       |       |       |       |       |       |       |          |       |       |       |       |       |       |       |       |
|       |       |       |       |  'B'  |   B   |   B   |       |          |       |       |       |       |  'B'  |       |       |       |
|       |       |       |       |       |       |       |       |          |       |       |       |       |       |       |       |       |
-----------------------------------------------------------------          -----------------------------------------------------------------
|       |       |       |       |       |       |       |       |          |       |       |       |       |       |       |       |       |
|       |       |       |       |   B   |   B   |       |       |          |       |       |       |   B   |       |       |       |       |
|       |       |       |       |       |       |       |       |          |       |       |       |       |       |       |       |       |
-----------------------------------------------------------------          -----------------------------------------------------------------
|       |       |       |       |       |       |       |       |          |       |       |       |       |       |       |       |       |
|   B   |   B   |   B   |   B   |   B   |   B   |       |       |          |  'B'  |       |       |       |       |       |       |       |
|       |       |       |       |       |       |       |       |          |       |       |       |       |       |       |       |       |
-----------------------------------------------------------------          -----------------------------------------------------------------
|       |       |       |       |       |       |       |       |          |       |       |       |       |       |       |       |       |
|   B   |   B   |   B   |   B   |   B   |   B   |       |       |          |       |       |       |       |       |       |       |       |
|       |       |       |       |       |       |       |       |          |       |       |       |       |       |       |       |       |
-----------------------------------------------------------------          -----------------------------------------------------------------
|       |       |       |       |       |       |       |       |          |       |       |       |       |       |       |       |       |
|   B   |   B   |   B   |   B   |   1   |   2   |       |       |          |       |       |       |       |       |       |       |       |
|       |       |       |       |       |       |       |       |          |       |       |       |       |       |       |       |       |
-----------------------------------------------------------------          -----------------------------------------------------------------
|       |       |       |       |       |       |       |       |          |       |       |       |       |       |       |       |       |
|   B   |   B   |   B   |  'B'  |   4   |   3   |       |  'B'  |          |       |       |       |  'B'  |       |       |       |  'B'  |
|       |       |       |       |       |       |       |       |          |       |       |       |       |       |       |       |       |
-----------------------------------------------------------------          -----------------------------------------------------------------
                           qd1                                                                        qd2
B: Black
G: Grey
W: Whilte

                       G                   ...... 8 x 8                                              G                                          ...... 8 x 8
        /      /               \      \                                  /                /                   \                    \
       W      G                 G      B   ...... 4 x 4                 G                G                     G                    G           ...... 4 x 4
         /  /   \  \       /  /   \  \                           /    /   \   \     /   /   \    \       /   /   \   \        /   /   \   \
        W  W    G   B     B  W   G    W    ...... 2 x 2         G    W     G   W   W   G    W     G     W   W     G   W      G   W     G   W    ...... 2 x 2
             / / \ \          / / \ \                        / / \ \    / / \ \     / / \ \    / / \ \         / / \ \    / / \ \   / / \ \
            B W   W W        W W   B W     ...... 1 pixel   W W   B W  W W   B W   W B   W W  B W   W W       W W   B W  B W   W W W W   B W    ...... 1 pixel

 */
public class QuadTreePciture<Key extends Comparable<Key>, Value> {
	
	private QuadTreeNode root;
	
	public static void main(String[] args) {

	
        QuadTreePciture<Integer, String> qd1 = new QuadTreePciture<Integer, String>();
        for(int i = 0; i < 4; i++)
            for(int j = 0; j < 4; j++)
            	qd1.insert(i, j, "Black");
        for(int i = 4; i < 6; i++)
            for(int j = 2; j < 6; j++)
            	qd1.insert(i, j, "Black");
    	qd1.insert(7, 1, "Black");

        QuadTreePciture<Integer, String> qd2 = new QuadTreePciture<Integer, String>();
        
        
        
	
	}
	// helper node data type
	private class QuadTreeNode {
		Key x, y;
		QuadTreeNode NW, NE, SE, SW;
		Value value;
		
		QuadTreeNode(Key x, Key y, Value value){
			this.x = x;
			this.y = y;
			this.value = value;
		}
		
	}
	
  /***********************************************************************
    *  Insert (x, y) into appropriate quadrant
    ***************************************************************************/	
	public void insert(Key x, Key y, Value value) {
		root = insert(root, x, y, value);
	}
	
	private QuadTreeNode insert(QuadTreeNode h, Key x, Key y, Value value){
		if(h == null) return new QuadTreeNode(x, y, value);  // <== h object is created here
//		if(eq(x, h.x) && eq(y, h.y)) h.value = value;	// duplicate
		else if ( less(x, h.x) &&  less(y, h.y)) h.SW = insert(h.SW, x, y, value);
		else if ( less(x, h.x) && !less(y, h.y)) h.NW = insert(h.NW, x, y, value);
		else if (!less(x, h.x) &&  less(y, h.y)) h.SE = insert(h.SE, x, y, value);
		else if (!less(x, h.x) && !less(y, h.y)) h.NE = insert(h.NE, x, y, value);
		return h;
	}
	
   /***************************************************************************
    *  helper comparison functions
    ***************************************************************************/	
	private boolean less(Key k1, Key k2) { return k1.compareTo(k2) <  0; } 
	private boolean   eq(Key k1, Key k2) { return k1.compareTo(k2) == 0; }
	

	
}
