package com.prometheus.hoc.payroll.schema.util;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.apache.commons.lang3.StringUtils;

import com.prometheus.hoc.payroll.schema.vo.FormulaItemEnum;
import com.prometheus.hoc.payroll.schema.xml.SchemaXMLStep;
import com.prometheus.hoc.payroll.schema.xml.SchemaXMLStepFormulaItem;
import com.prometheus.hoc.payroll.schema.xml.SchemaXMLStepFormulaLine;
import com.prometheus.hoc.payroll.schema.xml.SchemaXMLStepInput;
import com.prometheus.hoc.payroll.schema.xml.SchemaXMLStepOutput;
import com.prometheus.hoc.payroll.schema.xml.SchemaXMLStepParam;
import com.thoughtworks.xstream.XStream;

public class SchemaStepParser {
	
	private final static String SAMPLE_STEP_XML = "com/prometheus/hoc/payroll/schema/util/step_sample.xml"; 
	
	public static final SchemaXMLStep parseSchemaStepXML(String filePath){
		InputStream is = SchemaStepParser.class.getClassLoader().getResourceAsStream(filePath);
		return parseSchemaStepXML(is);
	}
	
	/**
	 * 
	 * @param is
	 * @return
	 */
	public static final SchemaXMLStep parseSchemaStepXML(InputStream is){
		XStream xstream = new XStream();
		xstream.alias("step",SchemaXMLStep.class);
		xstream.alias("input-param", SchemaXMLStepInput.class);
		xstream.alias("output-param",SchemaXMLStepOutput.class);
		xstream.alias("param",SchemaXMLStepParam.class);
		xstream.autodetectAnnotations(true);
		
		SchemaXMLStep step = (SchemaXMLStep) xstream.fromXML(is);
		if(step != null){
			List<SchemaXMLStepFormulaLine> lines = ((SchemaXMLStepParam)step.getOutput().getParams().get(0)).getFormulaLines();
			System.out.println(convertFromLineItemsToString(lines));
		}
		
		return step;
	}
	
	/**
	 * 
	 * @return
	 */
	public static final SchemaXMLStep parseSchemaStepXML(){
		return parseSchemaStepXML(SAMPLE_STEP_XML);
	}
	
	/**
	 * 
	 * @param step
	 * @return
	 */
	public static final String convertSchemaStepXMLToString(SchemaXMLStep step){
//		XStream xstream = new XStream(
//				
//			new XppDriver(){
//	            public HierarchicalStreamWriter createWriter(Writer out) {
//	                return new PrettyPrintWriter(out) {  
//	                    boolean cdata = false;  
//	                    
//	                    @Override  
//	                    public void startNode(String name) {  
//	                        super.startNode(name);
//	                        cdata = false;
//	                        if(name.equals("content")){
//	                        	cdata = true;
//	                        }
//	                    }  
//	  
//	                    @Override  
//	                    protected void writeText(QuickWriter writer, String text) {  
//	                        if (cdata) {  
//	                        	writer.write("<![CDATA[");
//	                            writer.write(text);
//	                            writer.write("]]>");
//	                        } else {  
//	                            writer.write(text);  
//	                        }  
//	                    }  
//	                };  
//	            }  
//	        }
//		);
		XStream xstream = new XStream();
		xstream.alias("step",SchemaXMLStep.class);
		xstream.alias("input-param", SchemaXMLStepInput.class);
		xstream.alias("output-param",SchemaXMLStepOutput.class);
		xstream.alias("formula-line", SchemaXMLStepFormulaLine.class);
		xstream.alias("formula-item", SchemaXMLStepFormulaItem.class);
		xstream.alias("param",SchemaXMLStepParam.class);
		xstream.autodetectAnnotations(true);
		
		return xstream.toXML(step);
		
	}
	
	/**
	 * 
	 * @param fLines
	 * @return
	 */
	public static String convertFromLineItemsToString(List<SchemaXMLStepFormulaLine> fLines){
		StringBuilder formula = new StringBuilder("");
		if(fLines == null){
			return formula.toString();
		}
		
		for (SchemaXMLStepFormulaLine line : fLines) {
			if(line.getItems() != null){
				StringBuilder sln = new StringBuilder("");
				List<SchemaXMLStepFormulaItem> items = line.getItems();
				for (SchemaXMLStepFormulaItem item : items) {
					sln.append(item.getFormulaKey());
				}
				if(!StringUtils.isEmpty(sln.toString())){
					sln.append(";");
					sln.append(System.getProperty("line.separator"));
				}else{
					continue;
				}
				formula.append(sln);
			}else{
				continue;
			}
		}
		
		return formula.toString();
	}
	
	/**
	 * 
	 * @param fLines
	 * @return
	 */
	public static List<String> convertFromLineItemsToArray(List<SchemaXMLStepFormulaLine> fLines){
		List<String> formula = new ArrayList<String>();
		if(fLines == null){
			return Collections.emptyList();
		}
		
		for (SchemaXMLStepFormulaLine line : fLines) {
			if(line.getItems() != null){
				StringBuilder sln = new StringBuilder("");
				List<SchemaXMLStepFormulaItem> items = line.getItems();
				for (SchemaXMLStepFormulaItem item : items) {
					sln.append(item.getFormulaKey());
				}
				if(!StringUtils.isEmpty(sln.toString())){
					formula.add(sln.toString());
				}else{
					continue;
				}
			}else{
				continue;
			}
		}
		
		return formula;
	}
	
	public static void main(String[] args) {
		SchemaStepParser.parseSchemaStepXML();
		
		SchemaXMLStepParam p;
		SchemaXMLStep step = new SchemaXMLStep();
		SchemaXMLStepInput input = new SchemaXMLStepInput();
		
		List<SchemaXMLStepParam> inputParams = new ArrayList<SchemaXMLStepParam>();
		p = new SchemaXMLStepParam();
		p.setName("w_input");
		p.setSequence(1);
		p.setDescription("fuck");
		p.setType("la");
		inputParams.add(p);
		input.setParams(inputParams);
		
		SchemaXMLStepOutput output = new SchemaXMLStepOutput();
		List<SchemaXMLStepParam> outputParams = new ArrayList<SchemaXMLStepParam>();
		p = new SchemaXMLStepParam();
		p.setName("w_output");
		p.setSequence(1);
		p.setType("shit");
		
		List<SchemaXMLStepFormulaLine> fLines = new ArrayList<SchemaXMLStepFormulaLine>();
		List<SchemaXMLStepFormulaItem> itemList = new ArrayList<SchemaXMLStepFormulaItem>();
		SchemaXMLStepFormulaItem fItem = new SchemaXMLStepFormulaItem();
		fItem.setFormulaKey("a");
		fItem.setType(FormulaItemEnum.VAR.value());
		itemList.add(fItem);
		
		fItem = new SchemaXMLStepFormulaItem();
		fItem.setFormulaKey("+");
		fItem.setType(FormulaItemEnum.OPER.value());
		itemList.add(fItem);
		
		SchemaXMLStepFormulaLine fline = new SchemaXMLStepFormulaLine();
		fline.setItems(itemList);
		fLines.add(fline);
		
		p.setFormulaLines(fLines);
		
		outputParams.add(p);
		output.setParams(outputParams);
		
		step.setInput(input);
		step.setOutput(output);
		
		System.out.println(SchemaStepParser.convertSchemaStepXMLToString(step));
	}
	
	
	
}
