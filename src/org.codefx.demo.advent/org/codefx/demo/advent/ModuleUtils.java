package org.codefx.demo.advent;

import java.lang.StackWalker.StackFrame;

import static java.lang.StackWalker.Option.RETAIN_CLASS_REFERENCE;

public class ModuleUtils {

	public static boolean isModulePresent(String moduleName) {
		return StackWalker
				.getInstance(RETAIN_CLASS_REFERENCE)
				.walk(frames -> frames
						.map(StackFrame::getDeclaringClass)
						.filter(declaringClass ->
								declaringClass != ModuleUtils.class)
						.findFirst()
						.orElse((Class) ModuleUtils.class))
				.getModule()
				.getLayer()
				.findModule(moduleName)
				.isPresent();
		// chain all the methods!
	}

}
