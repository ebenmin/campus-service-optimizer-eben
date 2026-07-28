package com.campus.optimizer.datastructures;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

/**
 * WORKED EXAMPLE test class — copy this shape for your own structure:
 * one normal-case test, one boundary-case test, one invalid-input test.
 */
class MyStackTest {

    @Test
    void normalCase_pushThenPopReturnsMostRecentValueFirst() {
        MyStack<Integer> stack = new MyStack<>();
        stack.push(1);
        stack.push(2);
        stack.push(3);
        assertEquals(3, stack.pop()); // trace: pushed 1,2,3 -> pop returns 3
        assertEquals(2, stack.pop());
        assertEquals(1, stack.pop());
    }

    @Test
    void boundaryCase_resizingBeyondInitialCapacityKeepsAllElements() {
        MyStack<Integer> stack = new MyStack<>();
        for (int i = 0; i < 100; i++) { // forces at least one internal resize
            stack.push(i);
        }
        assertEquals(100, stack.size());
        assertEquals(99, stack.pop());
    }

    @Test
    void invalidInput_popOnEmptyStackThrows() {
        MyStack<Integer> stack = new MyStack<>();
        assertTrue(stack.isEmpty());
        assertThrows(IllegalStateException.class, stack::pop);
    }
}
