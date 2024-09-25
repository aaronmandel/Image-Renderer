
public class QTreeNode {
	
	private int x;
	private int y;
	private int size;
	private int color;
	private QTreeNode parent;
	private QTreeNode[] children;
	
	
	public QTreeNode () {
		this.parent = null;
		this.children = new QTreeNode[4];
		for(int i = 0; i < 4; i++) {
			this.children[i] = null;
			
		}
		

		
	}
	
	public QTreeNode (QTreeNode[] theChildren, int xcoord, int ycoord, int theSize, int theColor) {
		this.x = xcoord;
		this.y = ycoord;
		this.size = theSize;
		this.color = theColor;
		this.children = theChildren;
	}
	
	//method to check if the node has the desired xcoordinate and  ycooridanted
	public boolean contains(int xcoord, int ycoord) {
	    return xcoord >= x && xcoord < x + size && ycoord >= y && ycoord < y + size;
	}

	
	//gets x
	public int getx() {
		return x;
	}
	// gets y
	public int gety() {
		return y;
	}
	//gets size
	public int getSize() {
		return size;
	}
	//method to get color
	public int getColor() {
		return color;
	}
	
	//method to get parent
	QTreeNode getParent() {
		return parent;
	}
	
	
	//method to get the child
	public QTreeNode getChild(int index) throws QTreeException {
	    if (index < 0 || index > 3 || children == null) {
	        throw new QTreeException("not valid index or null");
	    }
	    return children[index];
	}

	
	//Method to set x
	public void setx(int newx) {
		x = newx;
	}
	
	//Method to set y
	public void sety(int newy) {
		y = newy;
	}
	
	
	//Method to set size
	public void setSize(int newSize) {
		size = newSize;
	}
	
	//method to set Color
	public void setColor(int newColor) {
		color = newColor;
	}
	
	//method to set Parent
	public void setParent(QTreeNode newParent) {
		this.parent = newParent;
	}
	
	
	//method to set Child
	public void setChild(QTreeNode newChild, int index) {
	    if (index < 0 || index > 3) {
	        throw new QTreeException("Index out of bounds: " + index);
	    }
	    if(isLeaf() == true) {
	        

	    }
	    
	    this.children[index] = newChild;
	   setParent(newChild);
	}

	
	//method to check if the node is a leaf
	public boolean isLeaf() {
		for(int i = 0; i < 4; i++) {
		if(getChild(i) != null) {
			return false;
		}
		}
		return true;
	}

	
	
}
