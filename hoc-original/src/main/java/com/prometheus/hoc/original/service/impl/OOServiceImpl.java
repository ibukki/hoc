/**
 * 
 */
package com.prometheus.hoc.original.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.prometheus.hoc.original.dao.impl.OriginalObjectDAO;
import com.prometheus.hoc.original.service.OOService;

/**
 * @author yyang
 *
 */
@Service
public class OOServiceImpl implements OOService {

  @Autowired
  private OriginalObjectDAO originalObjectDAO;
}
