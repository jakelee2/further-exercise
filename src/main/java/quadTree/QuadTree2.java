package quadTree;

import java.util.LinkedList;
import java.util.List;


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

left to right sequence:  NW, NE, SE, SW
  d
  
  0                     G                   ..... 8 x 8                                              G                                          ..... 8 x 8
         /      /               \      \                                 /                 /                  \                    \
  1     W      G                 G      B   ..... 4 x 4                 G                 G                    G                    G           ..... 4 x 4
          /  /   \  \       /  /   \  \                          /    /   \   \     /   /   \    \       /   /   \   \        /   /   \   \
  2      W  W    G   B     B  W   G    W    ..... 2 x 2         G    W     G   W   W   G    W     G     W   W     G   W      G   W     G   W    ..... 2 x 2
              / / \ \          / / \ \                       / / \ \    / / \ \     / / \ \    / / \ \         / / \ \    / / \ \   / / \ \
  3          B W   W W        W W   B W     ..... 1 pixel   W W   B W  W W   B W   W B   W W  B W   W W       W W   B W  B W   W W W W   B W    ..... 1 pixel

 */
public class QuadTree2<Key extends Comparable<Key>, Value> {
	
	private QuadTreeNode root;
	
	/* Given 2 quad-trees, find the intersection of black-pixels
	 * 
	 * Assumption: While a pairwise intersection of corresponding pixels in the two images....
	 * Intersection is white iff both pixels of the pair are white. 

		Then you can do the recursive intersection as follows: 
		let A and B be to corresponding nodes in the two trees (or their subtrees)
		
	if rootA.color == "black":
		return A
	if rootA.color == "white":
		return B
	if rootA.color == "gray" and rootB.color == "gray".
	
	##	Pairwise intersect the subtrees of A and B.
	##	If all four of the returned subtrees have a black root, return a single node tree with a black root.
		Otherwise return a tree with a gray root and the four returned subtrees as its subtrees.
	 * 
	 */

	List<QuadTreeNode> childrenNodes = new LinkedList<QuadTreeNode>();
	
	public List<QuadTreeNode> findInterSectionOfBlackPixels(QuadTreeNode root1, QuadTreeNode root2, int size) {
		if(root1.value == "Black" && root2.value == "Black"){
			return getAllTheChildenNodes(root1, 0, size);
		}
		if(root1.value == "White" && root2.value == "White"){
			return getAllTheChildenNodes(root1, 0, size);
		}
		
		return childrenNodes;
	}
	
	
	public List<QuadTreeNode> getAllTheChildenNodes(QuadTreeNode root, int depth, int size){
		
		return childrenNodes;
	}
	
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

	
	/*  Define a quad-tree for a black and white image. Count the number of black pixels.
	 * 
	 *	The crux is that at each level(depth) if the pixel color is not gray then it represents 4^(k - d) pixels of color same as itself, 
		where k represents the overall size of image of size (2^k X 2^k) and d represents the current depth of node in the tree 

		Assuming each node has color field: "black"/"white"/"gray" 
		and 4 field for the 4 child nodes of type node: ne , nw, se, sw 
	 */
	public int quadTreeCountBlackPixels(QuadTreeNode root, int depth, int size){
		if(root == null || root.value == "White")
			return 0;
		else if(root.value == "Black")
			return (int)Math.pow(4, size - depth);
		else {
			return	quadTreeCountBlackPixels(root.NW, depth+1, size) +
					quadTreeCountBlackPixels(root.NE, depth+1, size) +
					quadTreeCountBlackPixels(root.SE, depth+1, size) +
					quadTreeCountBlackPixels(root.SW, depth+1, size);
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
//			if(eq(x, h.x) && eq(y, h.y)) h.value = value;	// duplicate
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
