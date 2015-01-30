/**
 * 
 */
package com.prometheus.hoc.original.app.processor.aop;

/**
 * @author yyang
 *
 */
public interface ProcessorPointcuts {

  String VALIDATE = "@target(com.prometheus.hoc.original.processor.Validate)";
  String PRE_SAVE = "@target(com.prometheus.hoc.original.processor.PreSave)";
  String POST_SAVE = "@target(com.prometheus.hoc.original.processor.PostSave)";
  String AROUND_SAVE = "";
  String PRE_DELETE = "@target(com.prometheus.hoc.original.processor.PreDelete)";
  String POST_DELETE = "@target(com.prometheus.hoc.original.processor.PostDelete)";
  String AROUND_DELETE = "";
}
