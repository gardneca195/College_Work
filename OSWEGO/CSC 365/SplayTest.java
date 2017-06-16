public class SplayTest {


    public SplayTest() {
    }

    @BeforeClass
    public static void setUpClass() {
    }

    @AfterClass
    public static void tearDownClass() {
    }

    @Before
    public void setUp() {

        tree = new Splay();
        tree.add(18);   //              188
        tree.add(25);   //             /
        tree.add(12);   //           -78
        tree.add(-66);  //              \
        tree.add(-7);   //              162
        tree.add(162);  //              /
        tree.add(0);    //             12
        tree.add(9);    //            /  \
        tree.add(12);   //           0    25
        tree.add(-78);  //          / \   /
        tree.add(188);  //       -66   9 18
                                //          \
    }                           //           -7

    @Test
    public void doInsertAndSearchWork() {

        Splay test = this.tree;

        assertTrue(test.search(18));
        assertTrue(test.search(0));
        assertTrue(test.search(-66));
        assertTrue(test.search(-78));
        assertTrue(test.search(12));

        assertFalse(test.search(100));
        assertFalse(test.search(-10));
        assertFalse(test.search(2));
    }

    @Test
    public void doesDeleteWork() {

        Splay test = this.tree;

        test.delete(188);
        test.delete(12);
        test.delete(-7);
        test.delete(25);
        test.delete(-33); 



    @Test
    public void doesSplayingWorkProperly() {

        Splay test = this.tree;
        assertEquals(188, tree.getRoot().getKey());
        test.delete(0);
        assertEquals(12, tree.getRoot().getKey());
        test.search(9);
        assertEquals(9, tree.getRoot().getKey());
    }

    @Test
    public void treeLooksRightAfterInserting() {
        
        String lvl = "188 -78 162 12 0 25 -66 9 18 -7 ";
        
        redirectSystemOut();
        this.tree.printLevelOrder(this.tree.getRoot());
        setSystemOutBack();
        String testResult = new String(newOut.toByteArray());
        assertEquals(lvl, testResult);
    }  
}