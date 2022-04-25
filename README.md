# project1
package pra;
import java.util.*;
class CHILD {
    CHILD l_child=null, r_child=null;
    String name;
    int count;
    CHILD(String word, int frequency) {
        this.name = word;
        this.count = frequency;
    }
}

class INSERT_IN2_TREE {
    CHILD root;
    INSERT_IN2_TREE() {
        root = null;
    }
    void insert_val(String word) {
        root = inserting_NODES(root, word);
    }

    CHILD inserting_NODES(CHILD KEY, String VALUE) {
        if (KEY == null) {
            KEY = new CHILD(VALUE, 1);
            return KEY;
        }
        if (KEY.name.compareTo(VALUE) > 0) {
            KEY.l_child = inserting_NODES(KEY.l_child, VALUE);
        }
        if (KEY.name.compareTo(VALUE) == 0) {
            KEY.count += 1;
        } else if (KEY.name.compareTo(VALUE) < 0) {
            KEY.r_child = inserting_NODES(KEY.r_child, VALUE);
        }
        return KEY;
    }
    static void INORDER(CHILD values) {
        if(values==null)
            return;
        INORDER(values.l_child);
        System.out.print(" "+values.name +", "+values.count +";");
        INORDER(values.r_child);
    }

    void deleteing_NODES(String var) { root = deleteing_NODES(root, var); }
    CHILD deleteing_NODES(CHILD root, String node_del)
    {
        if (root == null)
            return root;
        if (root.name.compareTo(node_del)<0) {
            root.r_child = deleteing_NODES(root.r_child, node_del);
        }
        else if (root.name.compareTo(node_del)>0) {
            root.l_child = deleteing_NODES(root.l_child, node_del);
        }

        else {
            if (root.l_child == null)
                return root.r_child;
            else if (root.r_child == null)
                return root.l_child;
            root.name = LESFT_FAR_child(root.r_child);
            root.r_child = deleteing_NODES(root.r_child, root.name);
        }
        return root;
    }
    String LESFT_FAR_child(CHILD root)
    {
        String min = root.name;
        while (root.l_child != null) {
            min = root.l_child.name;
            root = root.l_child;
        }
        return min;
    }

    CHILD SEARCHING_NODES(CHILD RootNode, String srch_KEY) {
        if (RootNode == null) {
            System.out.println("The word is not found.");
        } else if (RootNode.name.compareTo(srch_KEY) == 0) {
            if (RootNode.count > 1) {
                RootNode.count -= 1;
                System.out.println("The frequency of " + RootNode.name + " is now " + RootNode.count + ".");
            }
            if (RootNode.count == 1) {
                System.out.println("The word is removed from the BST.");
                deleteing_NODES(srch_KEY);
            }
        } else if (RootNode.name.compareTo(srch_KEY) > 0) {
            RootNode.l_child = SEARCHING_NODES(RootNode.l_child, srch_KEY);
        } else {
            RootNode.r_child = SEARCHING_NODES(RootNode.r_child, srch_KEY);
        }
        return RootNode;
    }
}
class BinaryTree {
    public static void main(String[] args) {
        Scanner Scanner=new Scanner(System.in);
        System.out.print("Enter a sentence: ");
        String[] INPUT=Scanner.nextLine().replaceAll(",","").split(" ");

        INSERT_IN2_TREE OBJECT=new INSERT_IN2_TREE();

        for(String s:INPUT)
        {
            OBJECT.insert_val(s);
        }
        System.out.print("The words and their frequencies in the BST:");
        INSERT_IN2_TREE.INORDER(OBJECT.root);

        System.out.print("\nEnter the word to be searched: ");

        String Find=Scanner.nextLine();
        OBJECT.SEARCHING_NODES(OBJECT.root,Find);
    }
}
