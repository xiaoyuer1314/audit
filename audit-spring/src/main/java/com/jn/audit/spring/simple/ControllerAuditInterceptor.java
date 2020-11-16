package com.jn.audit.spring.simple;

import com.jn.agileway.aop.adapter.aopalliance.MethodInvocationAdapter;
import com.jn.audit.core.auditing.aop.AuditMethodInterceptor;
import org.aopalliance.intercept.MethodInterceptor;
import org.aopalliance.intercept.MethodInvocation;

public class ControllerAuditInterceptor implements MethodInterceptor {

    private AuditMethodInterceptor delegate;

    @Override
    public Object invoke(MethodInvocation invocation) throws Throwable {
        return delegate.intercept(new MethodInvocationAdapter(invocation));
    }
}
