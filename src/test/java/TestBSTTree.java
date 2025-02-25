import org.example.BinarySearchTree.BSTNodeCom;
import org.example.BinarySearchTree.BSTTree1Com;

public class TestBSTTree {
    public static void main(String[] args) {
                      /*
                              4
                            /   \
                           2     6
                          / \   / \ bv
                         1   3  5  7
         */
        BSTNodeCom<String> root = new BSTNodeCom<>("4","4",new BSTNodeCom<>("2","2",new BSTNodeCom<>("1"),new BSTNodeCom<>("3")),new BSTNodeCom<>("6","6",new BSTNodeCom<>("5"),new BSTNodeCom<>("7")));
        BSTTree1Com<String> bstTree1Com = new BSTTree1Com<>(root);
        System.out.println(bstTree1Com.get("4"));
        System.out.println(bstTree1Com.min());
        System.out.println(bstTree1Com.max());

        bstTree1Com.put("9","9");
        System.out.println(bstTree1Com.get("9"));

        bstTree1Com.put("10","10");
        System.out.println(bstTree1Com.get("10"));

        System.out.println(bstTree1Com.predecessor("4"));
        System.out.println(bstTree1Com.successor("4"));
    }
}
