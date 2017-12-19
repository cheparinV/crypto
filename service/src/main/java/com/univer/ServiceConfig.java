package com.univer;

import com.univer.repo.DaoConfig;
import com.univer.service.impl.MessageServiceImpl;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

/**
 * @author Vladislav Cheparin (vladislav.cheparin.gdc@ts.fujitsu.com)
 */
@Configuration
@Import({
        DaoConfig.class
})
@ComponentScan(basePackageClasses = {MessageServiceImpl.class})
public class ServiceConfig {
}
