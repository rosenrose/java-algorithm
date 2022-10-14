import java.util.ArrayDeque;
import java.util.Stack;

public class Main {
    public static void main(String[] args) {
        Node tree = new Node(6);

        tree.addNode(4);
        tree.addNode(10);
        tree.addNode(8);
        tree.addNode(2);
        tree.addNode(5);
        tree.addNode(15);

        tree.addNode(1);
        tree.addNode(9);
        tree.addNode(3);
        tree.addNode(7);
        tree.addNode(16);

        Node newTree = Node.copyTree(tree);

        tree.getLeftMost().value = 100;
        tree.printTree();
        newTree.printTree();

        tree.addNode(200);
        tree.printTree();
        newTree.printTree();
    }

    public static class Node {
        public int value;
        public Node parent;
        public Node left;
        public Node right;
        public int depth;

        public Node(int value, Node parent) {
            this.value = value;
            this.parent = parent;
            this.left = null;
            this.right = null;
            this.depth = parent == null ? 0 : parent.depth + 1;
        }

        public Node(int value) {
            this(value, null);
        }

        public static Node copyTree(final Node node) {
            if (node == null) {
                return null;
            }

            Node newNode = new Node(node.value, node.parent);

            newNode.left = Node.copyTree(node.left);
            newNode.right = Node.copyTree(node.right);

            return newNode;
        }

        public boolean hasChild() {
            return !(left == null && right == null);
        }

        private void removeChild(Node child) {
            if (left == child) {
                left = null;
            }
            if (right == child) {
                right = null;
            }
        }

        private void linkChildToParent(Node child) {
            if (getLeftOrRight() == 'L') {
                parent.left = child;
            } else {
                parent.right = child;
            }

            child.parent = parent;
            child.depth = child.parent.depth + 1;
        }

        public char getLeftOrRight() {
            if (parent == null) {
                return 'C';
            }

            return parent.left == this ? 'L' : 'R';
        }

        public Node getSibling() {
            Node parent = this.parent;

            if (parent == null) {
                return null;
            }

            return parent.left == this ? parent.right : parent.left;
        }

        public Node addNode(int value) {
            if (value < this.value) {
                if (left == null) {
                    left = new Node(value, this);
                    return left;
                }

                return left.addNode(value);
            }

            if (right == null) {
                right = new Node(value, this);
                return right;
            }

            return right.addNode(value);
        }

        public void addNode() {
            addNode(0);
        }

        public int sum() {
            int leftSum = left == null ? 0 : left.sum();
            int rightSum = right == null ? 0 : right.sum();

            return value + leftSum + rightSum;
        }

        public Node search(int value) {
            if (this.value == value) {
                return this;
            }

            if (value < this.value) {
                if (left == null) {
                    return null;
                }

                return left.search(value);
            }

            if (right == null) {
                return null;
            }

            return right.search(value);
        }

        public void addTree(Node tree) {
            addNode(tree.value);

            if (tree.left != null) {
                addTree(tree.left);
            }
            if (tree.right != null) {
                addTree(tree.right);
            }
        }

        public Node getLeftMost() {
            Node leftMost = this;

            while (leftMost.left != null) {
                leftMost = leftMost.left;
            }

            return leftMost;
        }

        public Node getRightMost() {
            Node rightMost = this;

            while (rightMost.right != null) {
                rightMost = rightMost.right;
            }

            return rightMost;
        }

        public void delete(Node node) {
            if (node == null || node.parent == null) {
                return;
            }

            if (!node.hasChild()) {
                node.parent.removeChild(node);
                return;
            }

            if (node.left == null) {
                node.linkChildToParent(node.right);
                return;
            }
            if (node.right == null) {
                node.linkChildToParent(node.left);
                return;
            }

            Node predecessor = node.left.getRightMost();
            // Node successor = node.right.getLeftMost();

            node.value = predecessor.value;
            delete(predecessor);
        }

        public void printTree() {
            ArrayDeque<Node> queue = new ArrayDeque<>();

            queue.addLast(this);
            printTree(queue);
            System.out.println("==========");
        }

        private void printTree(ArrayDeque<Node> queue) {
            int size = queue.size();

            if (size == 0) {
                return;
            }

            while (size > 0) {
                Node first = queue.removeFirst();
                first.printNode();

                if (first.left != null) {
                    queue.addLast(first.left);
                }
                if (first.right != null) {
                    queue.addLast(first.right);
                }

                size--;
            }
            System.out.println();

            printTree(queue);
        }

        public void printNode() {
            System.out.printf("%d ", value);
            if (parent != null) {
                System.out.printf("(%d%c) ", parent.value, getLeftOrRight());
            }
        }

        public void printInOrder() {
            if (left != null) {
                left.printInOrder();
            }

            System.out.printf("%d ", value);

            if (right != null) {
                right.printInOrder();
            }
        }

        public void printPreOrderRecursive() {
            System.out.printf("%d ", value);

            if (left != null) {
                left.printPreOrder();
            }
            if (right != null) {
                right.printPreOrder();
            }
        }

        public void printPreOrder() {
            Stack<Node> stack = new Stack<>();
            stack.push(this);

            while (!stack.isEmpty()) {
                Node node = stack.pop();

                System.out.printf("%d ", node.value);

                if (node.right != null) {
                    stack.push(node.right);
                }
                if (node.left != null) {
                    stack.push(node.left);
                }
            }
        }

        public void printPostOrder() {
            if (left != null) {
                left.printPostOrder();
            }
            if (right != null) {
                right.printPostOrder();
            }

            System.out.printf("%d ", value);
        }
    }
}
