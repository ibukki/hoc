package com.prometheus.hoc.payroll.function;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import org.springframework.stereotype.Component;

import com.bubuwork.bukki.function.Function;
import com.bubuwork.bukki.function.FunctionParam;

@Component
public class FunctionImplScanner {
	
	private List<String> packList = new ArrayList<String>();
	
	/**
	 * 
	 * @param pack
	 */
	public void addPackageToScanList(String pack){
		this.packList.add(pack);
	}
	
	/**
	 * start to scan
	 */
	public List<ConfFunction> scan(){
		List<ConfFunction> confFunc = new ArrayList<ConfFunction>();
		if(this.packList != null){
			Set<Class<?>> clazSet = PackageScanUtil.getClasses(this.packList);
			
			if(clazSet != null){
				for (Class<?> claz : clazSet) {
					
					//read claz configuraiton
					boolean hasAnnotation = claz.isAnnotationPresent(Function.class);
					if(!hasAnnotation){
						continue;
					}
					
					Function funcAnno = claz.getAnnotation(Function.class);
					ConfFunction func = new ConfFunction();
					func.setName(funcAnno.name());
					List<ConfFunctionParam> paramList = new ArrayList<ConfFunctionParam>();
					FunctionParam[] paramArr = funcAnno.params();
					if(paramArr != null && paramArr.length > 0){
						for(int i = 0 ; i < paramArr.length; i++){
							ConfFunctionParam param = new ConfFunctionParam();
							param.setName(paramArr[i].name());
							param.setType(paramArr[i].type());
							paramList.add(param);
						}
						func.setParamList(paramList);
					}
					func.setDescription(funcAnno.description());
					func.setClassName(claz.getName());
					confFunc.add(func);
				}
			}
		}
		return confFunc;
	}
}
