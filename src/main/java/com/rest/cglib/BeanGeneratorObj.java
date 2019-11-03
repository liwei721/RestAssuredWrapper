package com.rest.cglib;

import net.sf.cglib.asm.$ClassVisitor;
import net.sf.cglib.asm.$Type;
import net.sf.cglib.beans.BeanGenerator;
import net.sf.cglib.core.ClassEmitter;
import net.sf.cglib.core.Constants;
import net.sf.cglib.core.EmitUtils;

import java.util.HashMap;
import java.util.Map;

/**
 * @author xuanke(zhou.liwei @ mydreamplus.com)
 * @createTime 2019-11-03-10:20
 * @description 生成实体类
 */
public class BeanGeneratorObj extends BeanGenerator {
    private Map<String, $Type> props = new HashMap<String, $Type>();

    public BeanGeneratorObj() {
        super();
    }

    public void addProperty(String name, Class type) {
        super.addProperty(name, type);
        if (props.containsKey(name)) {
            throw new IllegalArgumentException("Duplicate property name \"" + name + "\"");
        }
        props.put(name, $Type.getType(type));
    }

    /**
     * 生成class
     *
     * @param v
     * @throws Exception
     */
    public void generateClass($ClassVisitor v) throws Exception {
        int size = props.size();
        String[] names = props.keySet().toArray(new String[size]);
        $Type[] types = new $Type[size];
        for (int i = 0; i < size; i++) {
            types[i] = ($Type) props.get(names[i]);
        }

        ClassEmitter ce = new ClassEmitter(v);
        ce.begin_class(Constants.V1_2, Constants.ACC_PUBLIC, getClassName(),
                getDefaultClassLoader() != null ? $Type.getType(getDefaultClassLoader().getClass())
                        : Constants.TYPE_OBJECT, null, null);
        EmitUtils.null_constructor(ce);
        add_properties(ce, names, types);
        ce.end_class();
    }

    /**
     * 添加属性
     *
     * @param ce
     * @param names
     * @param types
     */
    private void add_properties(ClassEmitter ce, String[] names, $Type[] types) {
        for (int i = 0; i < names.length; i++) {
            String fieldName = names[i];
            ce.declare_field(Constants.ACC_PRIVATE, fieldName, types[i], null);
            EmitUtils.add_property(ce, names[i], types[i], fieldName);
        }
    }
}