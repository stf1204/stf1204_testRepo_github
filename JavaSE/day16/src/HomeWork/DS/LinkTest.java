package HomeWork.DS;

class Node {
    // 数据域
    Object value;
    // 指针域
    Node next;

    public Node(Object value, Node next) {
        this.value = value;
        this.next = next;
    }
}

class Link {

    private Node head;
    private Node tail;
    private int size;

    public void add(Object val) {
        Node newNode = new Node(val, null);
        if (head == null) { // 链表为空
            tail = head = newNode;
        } else { // 链表非空
            // 尾部链入
            tail.next = newNode;
            // 刷新尾引用, 让它再指向最新的结点
            tail = newNode;
        }
        size++;
    }

    public void travel() {
        Node tmp = head;
        while (tmp != null) {
            System.out.println(tmp.value);
            tmp = tmp.next;
        }
    }

    /**
     * 递归方法, 遍历链表, 分解成只打印头的数据和 把头的下一个又作为新头的子链表递归处理,
     * 直到链表为空为止
     * @param tmpHead , 以它为头的链表
     */
    private void view(Node tmpHead) {
        if (tmpHead == null) {
            return;
        }
        System.out.println(tmpHead.value);
        view(tmpHead.next);
    }

    public void travel2() {
        view(head);
    }

    /**
     * 获取链表中的元素个数
     * @return
     */
    public int size() {
        /*
        int size = 0;
        Node tmp = head;
        while (tmp != null) {
            size++;
            tmp = tmp.next;
        }
        */
        return size;
    }

    /**
     * 从链表中删除指定值为元素
     * @param val 要删除的元素
     * @return 删除的成功或失败
     */
    public boolean remove(Object val) {
        if (head == null) {
            return false;
        }
        if (head.value.equals(val)) { // 单独判断头的情况
            head = head.next;
            size--;
            return true;
        }
        Node prev = head;
        while (prev.next != null) {
            // 如果prev.next的值等于参数值, 定位成功, 目标就是prev.next
            if (prev.next.value.equals(val)) {
                if (prev.next == tail) { // 如果删除的目标结点是尾
                    tail = prev; // 刷新尾引用.
                }
                prev.next = prev.next.next; // 把目标的下一个地址赋值给 上一个的next
                size--; // 调整计数器
                return true;
            }
            prev = prev.next; // 让prev向后移动
        }
        return false;
    }

}

public class LinkTest {

    public static void main(String[] args) {
        Link link = new Link();
        link.add("yyy");
        link.add("abc");
        link.add("qq");
        link.add("zz");
        link.add("xx");
        link.add("111");
        link.add("zz");
        link.add("777");

        link.travel();

        System.out.println("link.size() = " + link.size());

        while (link.remove("zz")); // 把所有相同的值的元素删除了.

        link.travel();
        System.out.println("**********************");
        link.add("999");
        link.travel();

        System.out.println("*******************");
        link.travel2();
    }
}
