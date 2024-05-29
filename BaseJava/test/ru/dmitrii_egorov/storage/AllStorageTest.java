package ru.dmitrii_egorov.storage;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

@RunWith(Suite.class)
@SuiteClasses({
    ArrayStorageTest.class,
    ListStorageTest.class,
    SortedArrayStorageTest.class
})
public class AllStorageTest {
}
