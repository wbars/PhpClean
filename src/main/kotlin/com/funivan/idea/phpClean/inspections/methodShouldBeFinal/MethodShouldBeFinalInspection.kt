package com.funivan.idea.phpClean.inspections.methodShouldBeFinal

import com.intellij.codeInspection.ProblemsHolder
import com.intellij.psi.PsiElementVisitor
import com.jetbrains.php.lang.inspections.PhpInspection
import com.jetbrains.php.lang.psi.elements.PhpClass
import com.jetbrains.php.lang.psi.visitors.PhpElementVisitor

class MethodShouldBeFinalInspection : PhpInspection() {
    override fun getShortName() = "MethodShouldBeFinalInspection"
    override fun buildVisitor(holder: ProblemsHolder, isOnTheFly: Boolean): PsiElementVisitor {
        return object : PhpElementVisitor() {
            override fun visitPhpClass(clazz: PhpClass) {
                if (!clazz.isFinal && !clazz.isInterface) {
                    for (method in clazz.ownMethods.filter { !it.modifier.isFinal && !it.modifier.isAbstract }) {
                        holder.registerProblem(
                                method.nameIdentifier ?: method,
                                "Method should be final"
                        )
                    }
                }
            }
        }
    }
}