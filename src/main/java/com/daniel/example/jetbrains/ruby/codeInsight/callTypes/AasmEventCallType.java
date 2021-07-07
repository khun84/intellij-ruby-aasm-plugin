package com.daniel.example.jetbrains.ruby.codeInsight.callTypes;

import com.intellij.icons.AllIcons;
import com.intellij.navigation.ItemPresentation;
import com.intellij.openapi.util.text.StringUtil;
import com.intellij.psi.PsiElement;
import com.intellij.psi.PsiNamedElement;
import com.intellij.psi.stubs.IndexSink;
import com.intellij.util.IncorrectOperationException;
import com.intellij.util.Processor;
import com.intellij.util.containers.ContainerUtil;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.jetbrains.plugins.ruby.RBundle;
import org.jetbrains.plugins.ruby.ruby.codeInsight.symbols.Type;
import org.jetbrains.plugins.ruby.ruby.codeInsight.symbols.structure.ArgumentFakePsiElement;
import org.jetbrains.plugins.ruby.ruby.codeInsight.symbols.structure.PsiElementSymbol;
import org.jetbrains.plugins.ruby.ruby.codeInsight.symbols.structure.Symbol;
import org.jetbrains.plugins.ruby.ruby.lang.TextUtil;
import org.jetbrains.plugins.ruby.ruby.lang.psi.RPossibleCall;
import org.jetbrains.plugins.ruby.ruby.lang.psi.RPsiElement;
import org.jetbrains.plugins.ruby.ruby.lang.psi.controlStructures.methods.Visibility;
import org.jetbrains.plugins.ruby.ruby.lang.psi.impl.methodCall.RCallNavigator;
import org.jetbrains.plugins.ruby.ruby.lang.psi.indexes.RubyResolutionIndex;
import org.jetbrains.plugins.ruby.ruby.lang.psi.iterators.RBlockCall;
import org.jetbrains.plugins.ruby.ruby.lang.psi.methodCall.*;
import org.jetbrains.plugins.ruby.ruby.lang.psi.stubs.RStubNamespaceElement;
import org.jetbrains.plugins.ruby.ruby.lang.psi.stubs.calls.RPossibleCallStub;
import org.jetbrains.plugins.ruby.ruby.presentation.RContainerPresentationUtil;
import org.jetbrains.plugins.ruby.ruby.presentation.RMethodPresentationUtil;

import javax.swing.*;
import java.util.List;

public final class AasmEventCallType extends RubyEvalCallType implements CallTypeWithStructurePresentation {
    public static final RubyCallType<List<String>> INSTANCE = new AasmEventCallType();
    private static final Icon ICON;

    private AasmEventCallType() {
        super("event");
        this.withSkipHashArguments();
    }

    protected void indexArgument(RPossibleCallStub<?> stub, IndexSink sink, @NotNull String arg) {
        if (arg == null) {
//            $$$reportNull$$$0(1);
        }
        RubyResolutionIndex.sink(RStubNamespaceElement.getFQNForStub(stub, arg + "!"), sink);
        RubyResolutionIndex.sink(RStubNamespaceElement.getFQNForStub(stub, "may_" + arg + "?"), sink);
    }

    protected boolean processArgument(Symbol parent, RCall call, Processor<? super Symbol> processor, String argument, PsiElement invocationPoint) {
        if (!processor.process(new PsiElementSymbol<RCall>(call, argument + "!", Type.INSTANCE_METHOD, parent))) {
            return false;
        } else {
            return processor.process(new PsiElementSymbol<RCall>(call, "may_" + argument + "?", Type.INSTANCE_METHOD, parent));
        }
    }

    @NotNull
    public RPsiElement getStructureViewElement(@NotNull RPossibleCall possibleCall) {
        if (possibleCall == null) {
//            $$$reportNull$$$0(0);
        }

        RPossibleCall call = possibleCall instanceof RBlockCall ? ((RBlockCall)possibleCall).getCall() : possibleCall;
        String name = (String)ContainerUtil.getFirstItem((List)call.getData(new RubyCallType[]{this}));
        Object var10000 = call instanceof RCall && name != null ? new AasmEventCallType.MyPsiElement((RCall)call, name) : possibleCall;
        if (var10000 == null) {
//            $$$reportNull$$$0(1);
        }

        return (RPsiElement)var10000;
    }

    @NotNull
    public ItemPresentation getPresentation(@NotNull final RPossibleCall call) {
        if (call == null) {
//            $$$reportNull$$$0(2);
        }

        return new ItemPresentation() {
            @NotNull
            public String getPresentableText() {
                String name = (String)ContainerUtil.getFirstItem((List)call.getData(new RubyCallType[]{AasmEventCallType.this}));
                if (name != null && !name.isEmpty()) {
                    name = "aasm :" + name;
                }
                String var10000 = StringUtil.notNullize(name, RBundle.message("ruby.presentation.unknown"));
                if (var10000 == null) {
//                    $$$reportNull$$$0(0);
                }

                return var10000;
            }

            @NotNull
            public String getLocationString() {
                String var10000 = TextUtil.wrapInParens(RContainerPresentationUtil.getLocation(call));
                if (var10000 == null) {
//                    $$$reportNull$$$0(1);
                }

                return var10000;
            }

            @Nullable
            public Icon getIcon(boolean unused) {
                return null;
            }
        };
    }


    static {
        ICON = RContainerPresentationUtil.createIconWithAccessSign(RMethodPresentationUtil.createStaticIcon(AllIcons.Nodes.Method), Visibility.PUBLIC);
    }

    private static final class MyPsiElement extends ArgumentFakePsiElement {
        private MyPsiElement(@NotNull RCall call, @NotNull String name) {
//            if (call == null) {
//                $$$reportNull$$$0(0);
//            }

//            if (name == null) {
//                $$$reportNull$$$0(1);
//            }

            super(call, name);
        }

        @NotNull
        public String getTypeName() {
            return "event";
        }

        @NotNull
        public Icon getIcon() {
            Icon var10000 = AasmEventCallType.ICON;
            if (var10000 == null) {
//                $$$reportNull$$$0(3);
            }

            return var10000;
        }



        @Nullable
        public PsiElement setName(@NotNull String name) throws IncorrectOperationException {
            if (name == null) {
//                $$$reportNull$$$0(4);
            }

            PsiElement identifier = this.getNameIdentifier();
            if (!(identifier instanceof PsiNamedElement)) {
                return null;
            } else {
                PsiElement newIdentifier = ((PsiNamedElement)identifier).setName(name);
                RCall newCall = RCallNavigator.getByRArgument(newIdentifier);
                return newCall != null ? new AasmEventCallType.MyPsiElement(newCall, name) : null;
            }
        }
    }
}
