// General Tree Implementation for UNION/FIND

class ParPtrTree<E> {
 
    protected ArrayNode<E> [] array;
    protected int count;

    public ParPtrTree (int size) {
        array =  (ArrayNode<E>[]) new ArrayNode [size];
        count = 0;
    }

    public void addNode(E data, Integer pI) {
        if (count == array.length)
	    return;
        array[count++] = new ArrayNode<E>(data, pI);
    }

    public String toString() {
        String result = "";
        for (int i = 0; i < count; i++)
	    result +=  array[i] + " ";
        return result;
    }

    public int FIND (int current) {
        ArrayNode<E> node =  array[current];
        // we are at the root
	if (node.getParentIndex() == null) 
	    return current;
        while (node.getParentIndex() != null) {
            current = node.getParentIndex();
            node =  array[current];
	}
        return current;
   }

    public void UNION (int a, int b) {
	int rootIndex1 = FIND(a);
	int rootIndex2 = FIND(b);
        if (rootIndex1 == rootIndex2) 
	    return;
        // rootIndex1 != rootIndex2
        ArrayNode<E> node1 = array[rootIndex1];            
        ArrayNode<E> node2 = array[rootIndex2];
        node2.setParentIndex(rootIndex1);
    }

    public int getIndex(E data) {
	for (int i = 0; i < count; i++) {
	    ArrayNode node =  array[i];
            if (data.equals(node.getData()))
		return i;
	}
        return -1;
    }

    public int partitions() {
        int partitions = 0;
        for (int i = 0; i < count; i++) {
            ArrayNode<E> node = array[i];
	    if (node.getParentIndex() == null)
		partitions++;
	}
	return partitions;
    }
}


class ArrayNode<E> {
    private E data;
    private Integer parentIndex;
    private int children;

    public ArrayNode (E data, Integer parentIndex) {
	this.data = data;
        this.parentIndex = parentIndex;
        children = 0;
    }

    public String toString() {
        return parentIndex + "<-" + data + "c:" + children ;
    }

    public E getData() {
	return data;
    }

    public Integer getParentIndex() {
	return parentIndex;
    }

    public void setParentIndex(int parentIndex) {
        this.parentIndex = parentIndex;
    }

    public int children() {
	return children;
    }

    public void setChildren(int n) {
        children = n;
    }
}
