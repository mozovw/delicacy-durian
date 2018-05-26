package com.delicacy.durian.state.pay;

import org.reflections.Reflections;

import java.util.HashMap;
import java.util.Set;

/**
 * @author
 * @create 2018-05-26 17:51
 **/
public class StrategyFactory {

    private  static StrategyFactory factory = new StrategyFactory();

    public  static HashMap<Integer,Strategy> sourceMap = new HashMap<>();


    static {
        Reflections reflections = new Reflections("com.delicacy.durian.state.pay.impl");
        Set<Class<?>> typesAnnotatedWith = reflections.getTypesAnnotatedWith(Pay.class);
        typesAnnotatedWith.stream().forEach(e->{
            Pay annotation = e.getAnnotation(Pay.class);
            try {
                Class<?> clazz =Class.forName(e.getCanonicalName());
                Strategy strategy = (Strategy) clazz.newInstance();
                sourceMap.put(annotation.value(),strategy);
            } catch (ClassNotFoundException e1) {
                e1.printStackTrace();
            } catch (IllegalAccessException e1) {
                e1.printStackTrace();
            } catch (InstantiationException e1) {
                e1.printStackTrace();
            }
        });
    }

    public Strategy create(int type){
        return sourceMap.get(type);
    }

    public static StrategyFactory getInstance() {
        return factory;
    }
}
