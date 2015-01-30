package com.prometheus.hoc.original;

public @interface OORelation {

  OORelationModel model() default OORelationModel.COMPOSITE;

  OORelationMultiplicity multi() default OORelationMultiplicity.ONE_TO_ONE;
}
