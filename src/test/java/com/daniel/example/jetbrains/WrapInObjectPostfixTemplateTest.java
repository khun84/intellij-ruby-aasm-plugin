package com.daniel.example.jetbrains;

import com.intellij.json.JsonFileType;
import com.intellij.psi.PsiFile;
import com.intellij.testFramework.fixtures.BasePlatformTestCase;
import org.jetbrains.plugins.ruby.ruby.lang.RubyFileType;
import org.junit.Assert;

public class WrapInObjectPostfixTemplateTest extends BasePlatformTestCase {
    public void testWrapWithArray() throws Exception {
//        assertWrapping("{\n  123\n}", "123");
//        assertWrapping("{\n  null\n}", "null");
    }

    /**
     * Asserts that the expansion of {@code content} by .o equals {@code expected}.
     */
    private void assertWrapping(String expected, String content) {
        PsiFile file = myFixture.configureByText(JsonFileType.INSTANCE, content);
        myFixture.type(".o\t");

        Assert.assertEquals(expected, file.getText());
    }
}
