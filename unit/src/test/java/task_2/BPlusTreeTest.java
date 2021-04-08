package task_2;

import org.junit.Before;
import org.junit.Test;

import java.util.List;
import java.util.stream.Collectors;

import static org.junit.Assert.*;

public class BPlusTreeTest {

    private BPlusTree tree;


    @Before
    public void setup() {
        tree = new BPlusTree(6);
    }

    @Test
    public void emptyTreeCheckRootClass() {
        var expected = BPlusTree.LeafNode.class;
        var actual = tree.getRoot().getClass();
        assertEquals(expected, actual);
    }

    @Test
    public void insertFiveCheckNoSplit() {
        for (int i = 0; i < 5; i++) {
            tree.insert(5);
        }
        var expected = BPlusTree.LeafNode.class;
        var actual = tree.getRoot().getClass();
        assertEquals(expected, actual);
    }

    @Test
    public void insertSix_checkRootIsNode_checkLeafNodeSplit() {
        for (int i = 0; i < 6; i++) {
            tree.insert(i);
        }

        var root = tree.getRoot();
        assertEquals(root.getClass(), BPlusTree.InnerNode.class);

        var expectedValue = Integer.valueOf(3);
        var actualValue = root.getEntries().first().getValue();
        assertEquals(expectedValue, actualValue);

        BPlusTree.InnerNode rootNode = (BPlusTree.InnerNode) root;
        var link = (BPlusTree.InnerNode.NodeLink) rootNode.getEntries().first();
        var containsSmaller
                = link.getLeft()
                .getEntries()
                .stream()
                .map(BPlusTree.Node.Entry::getValue)
                .collect(Collectors.toList())
                .equals(List.of(0, 1, 2));
        assertTrue(containsSmaller);

        var containsGreater
                = link.getRight()
                .getEntries()
                .stream()
                .map(BPlusTree.Node.Entry::getValue)
                .collect(Collectors.toList())
                .equals(List.of(3, 4, 5));
        assertTrue(containsGreater);
    }


    @Test
    public void insertFrom0to20_checkInnerNodesSplit_checkLeavesListIntegrity() {
        for (int i = 0; i < 21; i++) {
            tree.insert(i);
        }

        var rootLink = (BPlusTree.InnerNode.NodeLink) tree.getRoot().getEntries().first();
        var rootValue = rootLink.getValue();
        assertEquals(Integer.valueOf(12), rootValue);

        var leftContains = rootLink.getLeft().getEntries()
                .stream()
                .map(BPlusTree.Node.Entry::getValue)
                .collect(Collectors.toList())
                .equals(List.of(3, 6, 9));
        assertTrue(leftContains);

        var rightContains = rootLink.getRight().getEntries()
                .stream()
                .map(BPlusTree.Node.Entry::getValue)
                .collect(Collectors.toList())
                .equals(List.of(15, 18));
        assertTrue(rightContains);


        var leaf = (BPlusTree.LeafNode) ((BPlusTree.InnerNode.NodeLink) rootLink.getLeft().getEntries().first()).getLeft();
        for (int i = 0; i < 19; i += 3) {
            assertEquals(leaf.getEntries().stream()
                    .map(BPlusTree.Node.Entry::getValue)
                    .collect(Collectors.toList()), List.of(i, i + 1, i + 2));
            if (i != 18) leaf = leaf.getNext();
        }

        var lastValueEquals20 = leaf.getEntries().last().getValue().equals(20);
        assertTrue(lastValueEquals20);

    }


}
