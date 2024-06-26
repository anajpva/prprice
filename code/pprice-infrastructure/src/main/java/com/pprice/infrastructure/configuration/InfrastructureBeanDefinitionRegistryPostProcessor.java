package com.pprice.infrastructure.configuration;

import java.util.Collection;

import com.pprice.application.usecases.ApplicationUseCase;
import com.pprice.domain.services.DomainService;
import org.reflections.Reflections;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.beans.factory.support.BeanDefinitionRegistry;
import org.springframework.beans.factory.support.BeanDefinitionRegistryPostProcessor;
import org.springframework.beans.factory.support.RootBeanDefinition;
import org.springframework.context.annotation.Configuration;

@Configuration
class InfrastructureBeanDefinitionRegistryPostProcessor implements BeanDefinitionRegistryPostProcessor {

  @Override
  public void postProcessBeanDefinitionRegistry(BeanDefinitionRegistry beanDefinitionRegistry) throws BeansException {
    Reflections reflections = new Reflections("com.pprice");
    Collection<Class<?>> annotatedClasses = reflections.getTypesAnnotatedWith(DomainService.class);
    annotatedClasses.addAll(reflections.getTypesAnnotatedWith(ApplicationUseCase.class));

    annotatedClasses.forEach(clazz -> {
      RootBeanDefinition beanDefinition = new RootBeanDefinition(clazz);
      beanDefinitionRegistry.registerBeanDefinition(clazz.getName(), beanDefinition);
    });
  }

  @Override
  public void postProcessBeanFactory(ConfigurableListableBeanFactory configurableListableBeanFactory) throws BeansException {
    // Do nothing
  }

}
