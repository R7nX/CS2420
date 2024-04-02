package assign08;

public class BinaryNode<Type> {
    public Type element;
    public BinaryNode<Type> leftChild;
    public BinaryNode<Type> rightChild;

    public int dotLabel;

    public BinaryNode(Type element, BinaryNode<Type> lNode, BinaryNode<Type> rNode) {
        this.element = element;
        this.leftChild = lNode;
        this.rightChild = rNode;
        this.dotLabel++;
    }

    public BinaryNode(Type element) {
        this.element = element;
        this.leftChild = null;
        this.rightChild = null;
        this.dotLabel++;
    }

    public String generateDot() {
        String ret = "\tnode" + dotLabel + " [label = \"<f0> |<f1> " + element + "|<f2> \"]\n";
        if (leftChild != null) {
            ret += "\tnode" + dotLabel + ":f0 -> node" + leftChild.dotLabel + ":f1\n" + leftChild.generateDot();
        }
        if (rightChild != null) {
            ret += "\tnode" + dotLabel + ":f2 -> node" + rightChild.dotLabel + ":f1\n" + rightChild.generateDot();
        }

        return ret;
    }
}
