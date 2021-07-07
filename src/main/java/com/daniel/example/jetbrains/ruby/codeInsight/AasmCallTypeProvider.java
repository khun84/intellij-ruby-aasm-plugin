package com.daniel.example.jetbrains.ruby.codeInsight;

import com.daniel.example.jetbrains.ruby.codeInsight.callTypes.AasmEventCallType;
import org.jetbrains.plugins.ruby.ruby.lang.psi.methodCall.RubyCallType;
import org.jetbrains.plugins.ruby.ruby.lang.psi.methodCall.RubyCallTypeProvider;
import org.jetbrains.plugins.ruby.ruby.lang.psi.methodCall.RubyCallTypeRegistry;

import java.util.List;

public class AasmCallTypeProvider implements RubyCallTypeProvider {
    public static final RubyCallType<List<String>> EVENT_CALL;
    public AasmCallTypeProvider() {
    }

    @Override
    public void registerCallTypes(RubyCallTypeRegistry registry) {
        registry.registerCallTypes(EVENT_CALL);
    }

    static {
     EVENT_CALL = AasmEventCallType.INSTANCE;
    }
}
