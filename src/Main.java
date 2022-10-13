import java.util.ArrayDeque;

public class Main {
    public static void main(String[] args) {
        Node tree = new Node(6);

        tree.addNode(4);
        tree.addNode(10);

        tree.addNode(8);
        tree.addNode(2);
        tree.addNode(5);
        tree.addNode(11);

        tree.addNode(1);
        tree.addNode(9);
        tree.addNode(3);
        tree.addNode(7);

        tree.addNode(15);
        tree.addNode(12);
        tree.addNode(13);
        tree.addNode(14);

        tree.addNode(16);

        tree.print();
        System.out.println(tree.sum());
    }

    public static class Node {
        public int value = 0;
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
            ArrayDeque<Node> queue = new ArrayDeque<Node>();
            queue.addLast(this);

            while (true) {
                Node first = queue.removeFirst();

                if (first.left == null) {
                    first.left = new Node(value, first);
                    return;
                }
                if (first.right == null) {
                    first.right = new Node(value, first);
                    return;
                }

                queue.addLast(first.left);
                queue.addLast(first.right);
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

        public void print() {
            ArrayDeque<Node> queue = new ArrayDeque<Node>();

            queue.addLast(this);
            this.print(queue);
            System.out.println("==========");
        }

        private void print(ArrayDeque<Node> queue) {
            int size = queue.size();

            if (size == 0) {
                return;
            }

            while (size > 0) {
                Node first = queue.removeFirst();
                System.out.printf("%d ", first.value);
                if (first.parent != null) {
                    System.out.printf("(%d%c) ", first.parent.value, first.getLeftOrRight());
                }

                if (first.left != null) {
                    queue.addLast(first.left);
                }
                if (first.right != null) {
                    queue.addLast(first.right);
                }

                size--;
            }
            System.out.println();

            this.print(queue);
        }
    }
}
