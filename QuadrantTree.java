
public class QuadrantTree {
	QTreeNode root;
	
	public QuadrantTree(int [][] thePixels) {
        root = treeMaker(thePixels, 0, 0, thePixels.length, root);
		root.setParent(null);
	
	}
	
// private helper method that creates the tree
	private QTreeNode treeMaker(int[][] thePixels, int x, int y, int size, QTreeNode parent) {
	    QTreeNode node = new QTreeNode(); // Create a new node
	    node.setParent(parent); //Sets parent

	    if (size == 1) {
	      int color = thePixels[x][y];
	        node.setColor(color); // Sets color
	        node.setx(x); // Sets x-coordinate
	        node.sety(y); // Sets y-coordinate
	        node.setSize(size); // Sets size
	    } else {
	        int newSize = size / 2;
	        node.setColor(Gui.averageColor(thePixels, x, y, size)); // Sets the color to the average color of that quadrant

	        // Set the coordinates and size for the node
	        node.setx(x);
	        node.sety(y);
	        node.setSize(size);

	        // Recursively creates the children and sets them
	        node.setChild(treeMaker(thePixels, x, y, newSize, node), 0); // Upper-left quadrant
	        node.setChild(treeMaker(thePixels, x, y + newSize, newSize, node), 1); // Upper-right quadrant
	        node.setChild(treeMaker(thePixels, x + newSize, y, newSize, node), 2); // Lower-left quadrant
	        node.setChild(treeMaker(thePixels, x + newSize, y + newSize, newSize, node), 3); // Lower-right quadrant
	    }

	    return node;
	}



	//Method to get the root
	public QTreeNode getRoot() {
		return this.root;
	}

	
	
	public ListNode<QTreeNode> getPixels(QTreeNode r, int theLevel) {
	    // Base case: If the node is null, return an empty list.
	    if (r == null) {
	        return null;
	    }

	    // If theLevel is 0 or the node is a leaf return this node.
	    if (theLevel == 0 || r.isLeaf()) {
	        return new ListNode<QTreeNode>(r);
	    }

	    // Recursive case: Collect pixels from children nodes at theLevel - 1.
	    ListNode<QTreeNode> pixelsList = null;
	    ListNode<QTreeNode> lastNode = null;
	    for (int i = 0; i < 4; i++) { 
	        ListNode<QTreeNode> childPixels = getPixels(r.getChild(i), theLevel - 1);

	        // Add to the main pixels list.
	        if (childPixels != null) {
	            if (pixelsList == null) {
	                pixelsList = childPixels;
	                lastNode = pixelsList;
	                // Find the last node in the list
	                while (lastNode.getNext() != null) {
	                    lastNode = lastNode.getNext();
	                }
	            } else {
	                lastNode.setNext(childPixels);
	                // Update the last node to the new last node
	                while (lastNode.getNext() != null) {
	                    lastNode = lastNode.getNext();
	                }
	            }
	        }
	    }

	    return pixelsList;
	}

	
	
	
	public Duple findMatching(QTreeNode root, int theColor, int theLevel) {
	    if (root == null) {
	        return new Duple(); // Return an empty Duple if the root node is null
	    }

	    Duple result = new Duple();

	    // Check if we are at the right level or if the root node is a leaf
	    if (theLevel == 0 || root.isLeaf()) {
	        // Ensure root is not null before calling getColor()	
	        if (root != null && Gui.similarColor(root.getColor(), theColor)) {
	            ListNode<QTreeNode> newNode = new ListNode<>(root);
	            newNode.setNext(result.getFront());
	            result.setFront(newNode);
	            result.setCount(result.getCount() + 1);
	        }
	    } else {
	        // If not at the right level, recursively search the children
	        for (int i = 0; i < 4; i++) { //loop for 4 times, assuming there is only 4 children
	            QTreeNode child = root.getChild(i);
	            if (child != null) { // Check if the child node is not null
	                Duple childResult = findMatching(child, theColor, theLevel - 1);
	                if (childResult.getFront() != null) {
	                    ListNode<QTreeNode> last = childResult.getFront();
	                    System.out.println(last);
	                    while (last.getNext() != null) {
	                        last = last.getNext();
	                    }
	                    last.setNext(result.getFront());
	                    result.setFront(childResult.getFront());
	                    result.setCount(result.getCount() + childResult.getCount());
	                    System.out.println(result.getCount());
	                }
	            }
	        }
	    }
	    return result;
	}



	
	public QTreeNode findNode(QTreeNode r, int theLevel, int x, int y) {
	    // Check if r is null at the start
	    if (r == null) {
	        return null;
	    }

	    // If r is a leaf or we've reached the desired level, check if it contains the point.
	    if ((r.isLeaf() || theLevel == 0) && r.contains(x, y)) {
	        return r;
	    }

	    // Continue searching in child nodes.
	    if (!r.isLeaf() && theLevel > 0) {
	        for (int i = 0; i < 4; i++) { 
	            QTreeNode child = r.getChild(i);

	            // Check if the child node is not null and if it contains the point (x, y).
	            if (child != null && child.contains(x, y)) {
	                return findNode(child, theLevel - 1, x, y); // Decrease the level to search subtrees
	            }
	        }
	    }

	    // If no child contains the point and we're not at a leaf, return null.
	    return null;
	}



	
}
