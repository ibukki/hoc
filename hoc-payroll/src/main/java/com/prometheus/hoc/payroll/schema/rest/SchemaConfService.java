package com.prometheus.hoc.payroll.schema.rest;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.prometheus.hoc.common.bean.SimpleJsonResponse;
import com.prometheus.hoc.common.dao.BaseHibernateDao;
import com.prometheus.hoc.payroll.schema.eo.SchemaConfEO;
import com.prometheus.hoc.payroll.schema.eo.SchemaConfStepEO;
import com.prometheus.hoc.payroll.schema.util.SchemaStepParser;
import com.prometheus.hoc.payroll.schema.vo.FormulaItem;
import com.prometheus.hoc.payroll.schema.vo.FormulaItemEnum;
import com.prometheus.hoc.payroll.schema.vo.PayrollSchema;
import com.prometheus.hoc.payroll.schema.vo.PayrollSchemaParam;
import com.prometheus.hoc.payroll.schema.vo.PayrollSchemaStep;
import com.prometheus.hoc.payroll.schema.vo.PlainPayrollSchema;
import com.prometheus.hoc.payroll.schema.xml.SchemaXMLStep;
import com.prometheus.hoc.payroll.schema.xml.SchemaXMLStepInput;
import com.prometheus.hoc.payroll.schema.xml.SchemaXMLStepOutput;
import com.prometheus.hoc.payroll.schema.xml.SchemaXMLStepParam;

@Component
@Path("schema")
public class SchemaConfService {
	
	@Autowired
	private BaseHibernateDao baseHibernateDao;
	
	@GET
	@Path("{schemaid}")
	@Produces({ MediaType.APPLICATION_JSON })
	public PayrollSchema getSchema(@PathParam("schemaid") String configId){
		PayrollSchema schema = new PayrollSchema();
		SchemaConfEO conf = (SchemaConfEO) baseHibernateDao.findById(SchemaConfEO.class, configId);
		if(conf != null){
			schema.setConfigId(conf.getConfigId());
			schema.setDescription(conf.getDescription());
			List<SchemaConfStepEO> confStepList = conf.getConfStepList();
			List<PayrollSchemaStep> schemaStepList = new ArrayList<PayrollSchemaStep>();
			for (SchemaConfStepEO stepEO : confStepList) {
				PayrollSchemaStep step = new PayrollSchemaStep();
				step.setStepId(stepEO.getStepId());
				step.setStepDescr(stepEO.getStepDescr());
				step.setStepName(stepEO.getStepName());
				step.setStepSeq(stepEO.getStepSeq());
				
				//parse input stream
				byte[] stepContent = stepEO.getStepContent();
				if(stepContent != null){
					InputStream is = new ByteArrayInputStream(stepContent); 
					SchemaXMLStep stepXML = SchemaStepParser.parseSchemaStepXML(is);
					List<SchemaXMLStepParam> inputParams = stepXML.getInput().getParams();
					step.setStepInput(this.convertXMLParamToUI(inputParams));
					List<SchemaXMLStepParam> outputPrams = stepXML.getOutput().getParams();
					step.setStepOutput(this.convertXMLParamToUI(outputPrams));
				}
				schemaStepList.add(step);
			}
			schema.setStepList(schemaStepList);
		}
		return schema;
	}
	
	@GET
	@Path("fitemtype")
	@Produces({ MediaType.APPLICATION_JSON })
	public List<FormulaItem> getFormulaItemTypes(){
		List<FormulaItem> result = new ArrayList<FormulaItem>();
		for(FormulaItemEnum item : FormulaItemEnum.values()){
			FormulaItem uiItem = new FormulaItem();
			uiItem.setCode(item.getCode());
			uiItem.setNum(item.getNum());
			result.add(uiItem);
		}
		return result;
	}
	
	
	
	@GET
	@Path("list")
	@Produces({ MediaType.APPLICATION_JSON })
	public List<PlainPayrollSchema> getSchemaList(){
		List<SchemaConfEO> schemaList = baseHibernateDao.findAll(SchemaConfEO.class);
		PlainPayrollSchema p = new PlainPayrollSchema();
		List<PlainPayrollSchema> pList = new ArrayList<PlainPayrollSchema>();
		for (SchemaConfEO payrollSchema : schemaList) {
			p.setConfigId(payrollSchema.getConfigId());
			p.setDescription(payrollSchema.getDescription());
			pList.add(p);
		}
		return pList;
	}
	
	@POST
	@Path("save")
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public SimpleJsonResponse save(PayrollSchema schema){
		int res = -1;
		SchemaConfEO confEO = new SchemaConfEO();
		confEO.setConfigId(schema.getConfigId());
		confEO.setDescription(schema.getDescription());
		
		List<PayrollSchemaStep> schemaStepList = schema.getStepList();
		List<SchemaConfStepEO> stepEOList = new ArrayList<SchemaConfStepEO>();
		for (PayrollSchemaStep step : schemaStepList) {
			SchemaConfStepEO stepEO = new SchemaConfStepEO();
			stepEO.setStepId(step.getStepId());
			stepEO.setSchemaConf(confEO);
			stepEO.setStepName(step.getStepName());
			stepEO.setStepDescr(step.getStepDescr());
			stepEO.setStepSeq(step.getStepSeq());
			byte[] content;
			try {
				content = this.convertFromUISteptoBytes(step);
				stepEO.setStepContent(content);
			} catch (UnsupportedEncodingException e) {
				//DO NOTHING
			}
			stepEOList.add(stepEO);
		}
		confEO.setConfStepList(stepEOList);
		
		res = baseHibernateDao.saveorUpdate(confEO);
		
		SimpleJsonResponse rsp = new SimpleJsonResponse();
		if(res > 0){
			rsp.setCode(0);
			rsp.setMsg("success");
		}else{
			rsp.setCode(99);
			rsp.setMsg("failed");
		}
		return rsp;
	}
	
	
	private byte[] convertFromUISteptoBytes(PayrollSchemaStep step) throws UnsupportedEncodingException{
		List<PayrollSchemaParam> input = step.getStepInput();
		List<PayrollSchemaParam> output = step.getStepOutput();
		
		SchemaXMLStep xmlStep = new SchemaXMLStep();
		SchemaXMLStepInput xmlInput = new SchemaXMLStepInput();
		xmlInput.setParams(this.convertUIParamToXML(step.getStepInput()));
		xmlStep.setInput(xmlInput);
		
		SchemaXMLStepOutput xmlOutput = new SchemaXMLStepOutput();
		xmlOutput.setParams(this.convertUIParamToXML(step.getStepOutput()));
		xmlStep.setOutput(xmlOutput);
		
		String xmlString = SchemaStepParser.convertSchemaStepXMLToString(xmlStep);
		return xmlString.getBytes("UTF-8");
	}
	
	private List<PayrollSchemaParam> convertXMLParamToUI(List<SchemaXMLStepParam> params){
		List<PayrollSchemaParam> paramList = new ArrayList<PayrollSchemaParam>();
		if(params != null){
			for (SchemaXMLStepParam param : params) {
				PayrollSchemaParam uiparam = new PayrollSchemaParam();
				uiparam.setParamName(param.getName());
				uiparam.setParamDescr(param.getDescription());
				uiparam.setParamType(param.getType());
				uiparam.setSequence(param.getSequence());
				uiparam.setFormulaLines(param.getFormulaLines());
				paramList.add(uiparam);
			}
		}
		return paramList;
	}
	
	private List<SchemaXMLStepParam> convertUIParamToXML(List<PayrollSchemaParam> params){
		List<SchemaXMLStepParam> paramList = new ArrayList<SchemaXMLStepParam>();
		if(params != null){
			for (PayrollSchemaParam param : params) {
				SchemaXMLStepParam xmlparam = new SchemaXMLStepParam();
				xmlparam.setName(param.getParamName());
				xmlparam.setType(param.getParamType());
				xmlparam.setDescription(param.getParamDescr());
				xmlparam.setSequence(param.getSequence());
				xmlparam.setFormulaLines(param.getFormulaLines());
				paramList.add(xmlparam);
			}
		}
		return paramList;
	}
}
