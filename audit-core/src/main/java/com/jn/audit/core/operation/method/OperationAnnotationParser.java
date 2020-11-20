package com.jn.audit.core.operation.method;

import com.jn.audit.core.annotation.Operation;
import com.jn.audit.core.annotation.Resource;
import com.jn.audit.core.model.OperationDefinition;
import com.jn.audit.core.model.ResourceDefinition;
import com.jn.langx.util.reflect.Reflects;

import java.lang.reflect.Method;

/**
 * 提供内置的 com.jn.audit.core.annotation.Operation 注解解析器
 * 如果要支持自定义的注解，可以自定义
 */
public class OperationAnnotationParser implements OperationMethodAnnotationDefinitionParser<Operation> {

    private ResourceDefinitionParser resourceDefinitionParser = new ResourceDefinitionParser();

    @Override
    public Class<Operation> getAnnotation() {
        return Operation.class;
    }

    @Override
    public String getName() {
        return Reflects.getFQNClassName(Operation.class);
    }

    @Override
    public OperationDefinition parse(Method method) {
        Operation operation = Reflects.getAnnotation(method, getAnnotation());

        Resource resource = Reflects.getAnnotation(method, Resource.class);
        ResourceDefinition resourceDefinition = null;
        if (resource != null) {
            resourceDefinition = resourceDefinitionParser.parse(resource);
        } else {
            resourceDefinition = new ResourceDefinition();
        }

        if (operation != null) {
            OperationDefinition operationDefinition = new OperationDefinition();
            operationDefinition.setId(operation.code());
            operationDefinition.setCode(operation.code());
            operationDefinition.setName(operation.name());
            operationDefinition.setType(operation.type());
            operationDefinition.setDescription(operation.description());
            operationDefinition.setModule(operation.module());

            ResourceDefinition resourceDefinition2 = resourceDefinitionParser.parse(operation.resourceDefinition());
            resourceDefinition.putAll(resourceDefinition2);
            operationDefinition.setResourceDefinition(resourceDefinition);
            return operationDefinition;
        }
        return null;
    }

}
