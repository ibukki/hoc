package com.prometheus.hoc.original;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target({ ElementType.METHOD, ElementType.FIELD })
@Retention(RetentionPolicy.RUNTIME)
public @interface OOProperty {

  OOColumn mappedTo();

  boolean required() default false;

  int length() default 0;

  OOPropertyType dateType() default OOPropertyType.STRING;

  OORelation relation() default @OORelation(model = OORelationModel.COMPOSITE, multi = OORelationMultiplicity.ONE_TO_ONE);
}
