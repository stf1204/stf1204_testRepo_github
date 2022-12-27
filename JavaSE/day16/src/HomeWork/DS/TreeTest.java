package HomeWork.DS;

class TreeNode {
    int value;
    // 2个指针, 一个是左子(比我小), 另一个是右子(比我大)
    TreeNode left;
    TreeNode right;
}

class Tree {

    private TreeNode root;

    /**
     * 向目标结点tmpRoot插入新结点
     * @param tmpRoot, 目标子树
     * @param newNode 新结点
     */
    private void insert(TreeNode tmpRoot, TreeNode newNode) {
        if (tmpRoot == null) {
            return;
        }
        if (newNode.value < tmpRoot.value) { // 向左走
            if (tmpRoot.left == null) { // 目标左子是空, 这是最好的情况
                tmpRoot.left = newNode;
            } else { // 左子非空, 把左子作为临时根再递归插入
                insert(tmpRoot.left, newNode);
            }
        } else { // 向右走
            if (tmpRoot.right == null) { // 目标右子是空, 也是最好的情况
                tmpRoot.right = newNode;
            } else { // 右子非空, 递归插入
                insert(tmpRoot.right, newNode);
            }
        }
    }

    public void add(int val) {
        TreeNode newNode = new TreeNode();
        newNode.value = val; // 携带数据
        if (root == null) { // 树为空
            root = newNode; // 第一个新结点直接作为根
        } else { // 树是非空的.
            insert(root, newNode);
        }

    }

    private void view(TreeNode tmpRoot) {
        // 左中右
        if (tmpRoot.left != null) {
            view(tmpRoot.left);
        }
        System.out.println(tmpRoot.value);
        if (tmpRoot.right != null) {
            view(tmpRoot.right);
        }
    }

    public void travel() {
        view(root);
    }
}

public class TreeTest {

    public static void main(String[] args) {
        Tree tree = new Tree();
        tree.add(10);
        tree.add(5);
        tree.add(30);
        tree.add(20);
        tree.add(7);
        tree.add(9);
        tree.add(1);
        tree.add(8);
        tree.add(40);
        tree.add(25);

        tree.travel();

    }
}
