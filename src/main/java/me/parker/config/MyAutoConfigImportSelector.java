package me.parker.config;

import org.springframework.context.annotation.DeferredImportSelector;
import org.springframework.core.type.AnnotationMetadata;

public class MyAutoConfigImportSelector implements DeferredImportSelector {

    @Override
    public String[] selectImports(AnnotationMetadata importingClassMetadata) {
        return new String[]{
                "me.parker.config.autoconfig.DispatcherServletConfig",
                "me.parker.config.autoconfig.TomcatWebServerConfig"
        };
    }
}
