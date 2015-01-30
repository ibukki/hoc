/**
 * 
 */
package com.prometheus.hoc.original.app.support;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.prometheus.hoc.original.app.processor.PostDelete;
import com.prometheus.hoc.original.app.processor.PostSave;
import com.prometheus.hoc.original.app.processor.PreDelete;
import com.prometheus.hoc.original.app.processor.PreSave;
import com.prometheus.hoc.original.app.processor.Validate;
import com.prometheus.hoc.original.service.OOService;

/**
 * @author yyang
 *
 */
@Service
public class EntityService<Entity> {

  @Autowired
  private OOService originalObjectService;

  public Entity findById(Class<Entity> entityClass,
      Long id) {
    return null;
  }
  
  public Entity findByInternalId(Class<Entity> entityClass,
      Long internalId) {
    return null;
  }

  public Entity findByCode(Class<Entity> entityClass,
      String Code) {
    return null;
  }

  @Validate
  @PreSave
  @PostSave
  public void saveEntity(Class<Entity> entityClass, Entity entity) {
    
  }
  
  @PreDelete
  @PostDelete
  public void deleteEntity(Class<Entity> entityClass, long id) {
    
  }
}
