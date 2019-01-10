package edu.touro.mco152;

import com.google.inject.AbstractModule;
import com.google.inject.Scope;
import com.google.inject.Singleton;
import com.google.inject.name.Names;



public class CalculatorModule extends AbstractModule {

    @Override
    protected void configure() {
        bind(String.class)
                .annotatedWith(Names.named("JDBC URL"))
                .toInstance("jdbc:mysql://localhost/pizza");
        bind(CalculatorHistory.class).to(DbCalculatorHistory.class);
    }
}
