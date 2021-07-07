package com.daniel.example.jetbrains.ruby.codeInsight.callTypes;

import com.intellij.codeInsight.completion.CompletionType;
import com.intellij.psi.PsiFile;
import com.intellij.testFramework.fixtures.BasePlatformTestCase;

import java.util.List;

public class AasmCodeCompletionTest extends BasePlatformTestCase {
    @Override
    protected String getTestDataPath() {
        return ("src/test/resources");
    }

    public void testCompletion(){
        PsiFile file = myFixture.configureByFile("foo.rb");
        myFixture.type("t.b");
        myFixture.complete(CompletionType.BASIC);
        List<String> lookupStrings = myFixture.getLookupElementStrings();
        System.out.println(lookupStrings);
        assertNotNull(lookupStrings);
        assertNotEmpty(lookupStrings);
        assertContainsElements(lookupStrings, "bar");
    }
}
