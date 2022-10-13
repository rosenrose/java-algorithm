import java.util.ArrayDeque;

public class Main {
    public static void main(String[] args) {
        Node tree = new Node(4);

        tree.addNode(6);
        tree.addNode(10);

        tree.addNode(8);
        tree.addNode(2);
        tree.addNode(5);
        tree.addNode(11);

        tree.addNode(1);
        tree.addNode(9);
        tree.addNode(3);
        tree.addNode(7);

        tree.addNode(7);
        tree.addNode(12);
        tree.addNode(13);
        tree.addNode(14);

        tree.addNode(16);

        tree.printTree();
        //  tree.printSorted();
        Node found = tree.search(13);
        found.printNode();
        found = tree.search(20);
        System.out.println(found == null);
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

        public boolean isEmpty() {
            return this.left == null && this.right == null;
        }

        public boolean isFull() {
            return this.left != null && this.right != null;
        }

        public char getLeftOrRight() {
            if (this.parent == null) {
                return ' ';
            }

            return this.parent.left == this ? 'L' : 'R';
        }

        public Node getSibling() {
            Node parent = this.parent;

            if (parent == null) {
                return null;
            }

            return parent.left == this ? parent.right : parent.left;
        }

        public void addNode(int value) {
            if (value < this.value) {
                if (this.left == null) {
                    this.left = new Node(value, this);
                    return;
                }

                this.left.addNode(value);
            } else {
                if (this.right == null) {
                    this.right = new Node(value, this);
                    return;
                }

                this.right.addNode(value);
            }
        }

        public void addNode() {
            this.addNode(0);
        }

        public int sum() {
            int leftSum = this.left == null ? 0 : this.left.sum();
            int rightSum = this.right == null ? 0 : this.right.sum();

            return this.value + leftSum + rightSum;
        }

        public Node search(int value) {
            if (this.value == value) {
                return this;
            }

            if (value < this.value) {
                if (this.left == null) {
                    return null;
                }

                return this.left.search(value);
            }

            if (this.right == null) {
                return null;
            }

            return this.right.search(value);
        }

        public void printTree() {
            ArrayDeque<Node> queue = new ArrayDeque<>();

            queue.addLast(this);
            this.printTree(queue);
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

            this.printTree(queue);
        }

        public void printNode() {
            System.out.printf("%d ", this.value);
            if (this.parent != null) {
                System.out.printf("(%d%c) ", this.parent.value, this.getLeftOrRight());
            }
        }

        public void printSorted() {
            if (this.left != null) {
                this.left.printSorted();
            }

            System.out.printf("%d ", this.value);

            if (this.right != null) {
                this.right.printSorted();
            }
        }
    }
}
