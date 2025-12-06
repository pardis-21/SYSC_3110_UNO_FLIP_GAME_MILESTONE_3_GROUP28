import org.junit.*;

import java.util.ArrayList;

import static org.junit.Assert.*;
/**
 * This test class tests the different methods in the Card Class.
 *
 * @Author Anvita Ala 101301514
 * @Author Charis Nobossi 101297742
 * @Author Pulcherie Mbaye 101302394
 * @Author Pardis Ehsani 101300400
 */


public class UNOCommandManagerTest {

    // Dummy command used ONLY for testing
    static class DummyCommand implements UNOCommand {
        boolean undone = false;
        boolean redone = false;

        @Override
        public void undoAction() {
            undone = true;
        }

        @Override
        public void redoAction() {
            redone = true;
        }

    }

    @Test
    public void testPushMakesUndoable() {
        UNOCommandManager mgr = new UNOCommandManager();
        assertFalse(mgr.isUndoable());

        mgr.push(new DummyCommand());

        assertTrue(mgr.isUndoable());
        assertFalse(mgr.isRedoable());
    }

    @Test
    public void testUndoCallsUndoActionAndMovesCommandToRedoStack() {
        UNOCommandManager mgr = new UNOCommandManager();
        DummyCommand cmd = new DummyCommand();
        mgr.push(cmd);

        mgr.undo();

        assertTrue(cmd.undone);   // undo action was triggered
        assertTrue(mgr.isRedoable());  // moved to redo stack
        assertFalse(mgr.isUndoable()); // undo stack empty
    }


    @Test
    public void testRedoCallsRedoActionAndMovesBackToUndoStack() {
        UNOCommandManager mgr = new UNOCommandManager();
        DummyCommand cmd = new DummyCommand();

        mgr.push(cmd);
        mgr.undo(); // move it to redo stack

        mgr.redo();

        assertTrue(cmd.redone);
        assertTrue(mgr.isUndoable()); // back on undo stack
        assertFalse(mgr.isRedoable());
    }

    @Test
    public void testMultiLevelUndoRedo() {
        UNOCommandManager mgr = new UNOCommandManager();

        DummyCommand c1 = new DummyCommand();
        DummyCommand c2 = new DummyCommand();
        DummyCommand c3 = new DummyCommand();

        mgr.push(c1);
        mgr.push(c2);
        mgr.push(c3);

        // undo 3 commands
        mgr.undo(); // c3
        mgr.undo(); // c2
        mgr.undo(); // c1

        assertTrue(c1.undone);
        assertTrue(c2.undone);
        assertTrue(c3.undone);

        // redo all 3
        mgr.redo(); // c1
        mgr.redo(); // c2
        mgr.redo(); // c3

        assertTrue(c1.redone);
        assertTrue(c2.redone);
        assertTrue(c3.redone);
    }

    @Test
    public void testStackMaxSizeIsEnforced() {
        UNOCommandManager mgr = new UNOCommandManager();

        // push 35 commands
        for (int i = 0; i < 35; i++) {
            mgr.push(new DummyCommand());
        }

        // undo stack must not exceed 30
        // (we can't access stack directly, but undoing 30 times must empty it)
        int undoCount = 0;
        while (mgr.isUndoable()) {
            mgr.undo();
            undoCount++;
        }

        assertEquals(30, undoCount);
    }



}
