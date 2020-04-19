package com.jn.audit.core;

import com.jn.audit.core.model.*;
import com.jn.langx.util.function.Supplier;

public interface AuditEventExtractor<AuditedRequest, AuditedRequestContext> extends Supplier<AuditRequest<AuditedRequest, AuditedRequestContext>, AuditEvent> {

    Service extractService(AuditRequest<AuditedRequest, AuditedRequestContext> wrappedRequest);

    Principal extractPrincipal(AuditRequest<AuditedRequest, AuditedRequestContext> wrappedRequest);

    Resource extractResource(AuditRequest<AuditedRequest, AuditedRequestContext> wrappedRequest);

    Operation extractOperation(AuditRequest<AuditedRequest, AuditedRequestContext> wrappedRequest);

}
