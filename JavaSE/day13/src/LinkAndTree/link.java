package LinkAndTree;

public class link {
    private Node head;
    private Node tail;
    private int size=0;

    public  void add(Object val){
        Node newNode = new Node();
        newNode.value=val;
        if (head ==null){
            head=newNode;
            tail=newNode;
            size++;
        }else {
            tail.next=newNode; //尾结点变更
            tail=newNode;  //尾指针偏移
            size++;
        }
    }

    public void travel(){
        Node tmp = head;
        while (tmp!=null){
            System.out.print(tmp.value+" ");
            tmp=tmp.next;
        }
        System.out.println();
    }
    public void travel2(){
        view(head);
    }

    private void view(Node node){
        if (node==null){
            System.out.println();
            return;
        }
        System.out.print(node.value+" ");
        view(node.next);
    }

    public boolean remove(Object val){
        Node tmp = head;
        // 删头部的时候需要修改头指针
        if (head.value.equals(val)){
            head=head.next;
            size--;
            return true;
        }

        while (tmp.next!=null){ //直接判断头节点的下一个，需要对头部进行判断
            if (tmp.next.value.equals(val)){
                // 删尾部的时候，需要修改尾指针
                if (tmp.next==tail){
                    tail=tmp; //修改尾部指针
                }
                tmp.next=tmp.next.next;
                size--;
                return true;
            }else{
                tmp = tmp.next;
            }
        }
        return false;
    }

    public int size(){
        return size;
    }
    /*
    public int size(){
        LinkAndTree.Node tmp = head;
        int count=0;
        while (tmp!=null){
            count++;
            tmp=tmp.next;

        }
        return count;
    }

     */
}
