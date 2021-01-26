package org.itstack.interview.agent.asm;

import org.objectweb.asm.*;
import org.objectweb.asm.commons.AdviceAdapter;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.lang.reflect.Method;

import static org.objectweb.asm.Opcodes.ASM5;

public class ASMProxy extends ClassLoader {

    public static <T> T getProxy(Class clazz) throws Exception {

        ClassReader classReader = new ClassReader(clazz.getName());
        ClassWriter classWriter = new ClassWriter(classReader, ClassWriter.COMPUTE_MAXS);

        classReader.accept(new ClassVisitor(ASM5, classWriter) {
            @Override
            public MethodVisitor visitMethod(int access, final String name, String descriptor, String signature, String[] exceptions) {

                // 方法过滤
                if (!"queryUserInfo".equals(name))
                    return super.visitMethod(access, name, descriptor, signature, exceptions);

                final MethodVisitor methodVisitor = super.visitMethod(access, name, descriptor, signature, exceptions);

                return new AdviceAdapter(ASM5, methodVisitor, access, name, descriptor) {

                    @Override
                    protected void onMethodEnter() {
                        // 执行指令；获取静态属性
                        methodVisitor.visitFieldInsn(Opcodes.GETSTATIC, "java/lang/System", "out", "Ljava/io/PrintStream;");
                        // 加载常量 load constant
                        methodVisitor.visitLdcInsn(name + " 你被代理了，By ASM！");
                        // 调用方法
                        methodVisitor.visitMethodInsn(Opcodes.INVOKEVIRTUAL, "java/io/PrintStream", "println", "(Ljava/lang/String;)V", false);
                        super.onMethodEnter();
                    }
                };
            }
        }, ClassReader.EXPAND_FRAMES);

        byte[] bytes = classWriter.toByteArray();

        return (T) new ASMProxy().defineClass(clazz.getName(), bytes, 0, bytes.length).newInstance();
    }

}
