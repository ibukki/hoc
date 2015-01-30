hocAdminApp.controller('schemaConfigurationController', function($scope, uiGridConstants, $http,$alert, msgBus, WageTypeServiceForSchema,FunctionServiceForSchema, $modal){

	$scope.schemaStep = {
		gridOptions:{
				columnDefs:[
				            { name: 'stepSeq', displayName: 'Sequence',enableCellEdit: false },
				            { name: 'stepName', displayName:'Step Name', enableCellEdit: true },
				            { name: 'stepDescr', displayName: 'Description',enableCellEdit: true },
				            { name: 'stepInputFormatted', displayName: 'Input', enableCellEdit: false,  allowCellFocus : false},
				            { name: 'stepOutputFormatted', displayName: 'Output', enableCellEdit: false,  allowCellFocus : false},
				            { name: 'delete', displayName: 'Delete', enableCellEdit: false ,cellTemplate: '<button id="editBtn" type="button" style="border-radius:0px" class="btn-small btn-danger form-control" ng-click="getExternalScopes().deleteStep(row.entity)" >Delete</button> '}
				            ],
				data:[],
				enableColumnResize: true,
				enableCellEditOnFocus: true,
			    enableColumnReordering: true,
			    enableCellSelection: true,
			    enableRowSelection: true,
			    showGroupPanel: false,
			    multiSelect:false,
			    enableFiltering: false,
		},

		rowSelection:{},
		editingIndex: null,
		addStep : function(){
			var nextSeq = getMaximumSequence();
			$scope.schemaStep.gridOptions.data.push({
				stepSeq: nextSeq
			});
		},

		deleteStep : function(entity){
			console.debug(entity);
		},
		
		scrollDown : function(){
			$("#main_content").scrollTop(700);
		}
		
	};
	
	var stepFlowValTemplate = "<div><a href='javascript:void(0)' ng-show='COL_FIELD' ng-click='getExternalScopes().editFormula(row)'>Eidt Fomula</a></div>";
	var stepParamTemplate = "<div><span>{{COL_FIELD}}</span><button class='glyphicon glyphicon-filter fright btn' ng-click='getExternalScopes().selectParam(row)'></button></div>";
	$scope.schemaStepFlow = {
		rowSelection:null,
		editingIndex: -1,
		gridApi:null,
		gridOptions:{
				columnDefs:[
				            { name: 'uiSequence', displayName:'Sequence', enableCellEdit: false,
			            	  sort: {
			            		 direction: uiGridConstants.ASC,
			            		 priority: 1
			            	  }
				            },
				            { name: 'paramDescr', displayName: 'Param Name',
                        	  type: 'object',cellTemplate:stepParamTemplate
				            },
				            {
				              name: "type", display : "Type",
				              type: 'object'
				            },
				            { name: 'isInput', displayName: 'Input/Output', type: 'object', 
				            	enableCellEdit: false
				            },
				            { name: 'showFormula', displayName: 'Formula', enableCellEdit: false, cellTemplate: stepFlowValTemplate},
				            { name: 'delete', displayName: 'Delete', enableCellEdit: false ,cellTemplate: '<button id="editBtn" type="button" style="border-radius:0px" class="btn-small btn-danger form-control" ng-click="getExternalScopes().deleteStepFlow(row.entity)" >Delete</button> '}
				            ],
				data:[],
				enableColumnResize: true,
				enableCellEditOnFocus: true,
			    enableColumnReordering: true,
			    enableRowSelection: true,
			    enableCellSelection: true,
			    showGroupPanel: false,
			    multiSelect:false,
			    enableFiltering: false
			},
		addFlowStep : function(type){
			if($scope.schemaStep.rowSelection.stepSeq >= 0){
				
				var nextStep = getMaximumStepFlowSequence(type);
				var stepData = $scope.schemaStep.gridOptions.data[$scope.schemaStep.editingIndex];
				if(type == "input"){
					
					//param can be either primary or second wagetype
					$scope.setWageTypeDropdownData(hocAdminApp.g_wageTypeData);
					$scope.schemaStepFlow.gridOptions.data[nextStep - 1].uiSequence = nextStep;
					$scope.schemaStepFlow.gridOptions.data[nextStep - 1].isInput = type;
					$scope.schemaStepFlow.gridOptions.data[nextStep - 1].showFormula = false;
					
					if(!stepData.stepInput){
						stepData.stepInput = [];
					}
					stepData.stepInput.push({
						paramName:"",
						paramType:"",
						sequence:stepData.stepInput.length
					});
					
					reorganizeStepFlowDBSequence($scope.schemaStepFlow.gridOptions.data);
				}else{
					
					//param can only be second wage type
					$scope.setWageTypeDropdownData(hocAdminApp.g_second_wageTypeData);
					
					$scope.schemaStepFlow.gridOptions.data[nextStep - 1].uiSequence = nextStep;
					$scope.schemaStepFlow.gridOptions.data[nextStep - 1].isInput = type;
					$scope.schemaStepFlow.gridOptions.data[nextStep - 1].showFormula = true;
					
					if(!stepData.stepOutput){
						stepData.stepOutput = [];
					}
					stepData.stepOutput.push({
						paramName:"",
						paramType:"Secondary",
						sequence:stepData.stepOutput.length
					});
				}
				
				reorganizeStepFlowDBSequence($scope.schemaStepFlow.gridOptions.data);
			}else{
				alert("Please select a step first !");
			}
		},
		
		populateData : function(row){
			var inputList = [];
			var outputList = [];
			var fdata;
			this.gridOptions.data = [];
			this.gridApi.grid.rows = [];
			
			if(row.stepInput){
				inputList = row.stepInput;
				for(var i = 0 ; i < inputList.length; i++){
					fdata = {};
					fdata.uiSequence = this.gridOptions.data.length + 1;
					fdata.paramName = inputList[i].paramName;
					fdata.paramDescr = inputList[i].paramDescr;
					fdata.isInput = "input";
					fdata.showFormula = false;
					fdata.sequence = inputList[i].sequence;
					fdata.type = inputList[i].paramType;
					this.gridOptions.data.push(fdata);
				}
			}
			if(row.stepOutput){
				outputList = row.stepOutput;
				for(var i = 0 ; i < outputList.length; i++){
					fdata = {};
					fdata.uiSequence = this.gridOptions.data.length + 1;
					fdata.paramName = outputList[i].paramName;
					fdata.paramDescr = outputList[i].paramDescr;
					fdata.isInput = "output";
					fdata.showFormula = true;
					fdata.type = outputList[i].paramType;
					fdata.formulaLines = outputList[i].formulaLines;
					fdata.sequence = outputList[i].sequence;
					this.gridOptions.data.push(fdata);
				}
			}
			
			
		},
		
		deleteStepFlow : function(entity){
			var uiseq = entity.uiSequence;
			removeOneStepFlowData(uiseq - 1);
			var stepData = $scope.schemaStep.gridOptions.data[$scope.schemaStep.editingIndex];
			
			if(entity.isInput == "input"){
				//check this input is configured in following output
				stepData.stepInput.splice(entity.sequence, 1);
				reorganizeStepDBSequence(stepData.stepInput);
			}else{
				stepData.stepOutput.splice(entity.sequence,1);
				reorganizeStepDBSequence(stepData.stepOutput);
			}
			reorganizeStepFlowDBSequence($scope.schemaStepFlow.gridOptions.data);

		},
		editFormula : function(row){
			//get input parameters
			var inputList = [];
			var entity = row.entity;
			var editIndex = row.index;
			var data = $scope.schemaStepFlow.gridOptions.data;
			
			$scope.schemaStepFlow.editingIndex = editIndex;
			$scope.formula.tempParamList = [];
			
			if(!data[editIndex].paramName){
				return;
			}
			
			
			$scope.sfc.initStageData();
			
			//build up varaibale input
			if(data){
				for(var i = 0 ; i < data.length; i++){
					if(data[i].isInput == "input"){
						var fKey = "READ_WAGETYPE(\""+data[i].paramName+"\")";
						var dLabel = data[i].paramDescr+"("+data[i].paramName+")";
						inputList.push({
							displayLabel:dLabel,
							wtId:data[i].paramName,
							formulaKey:fKey,
							type:3
						});
						$scope.sfc.putLabelToBuffer(3,fKey,dLabel);
					}
				}
				
				inputList.push({
					displayLabel:data[editIndex].paramDescr+"("+data[editIndex].paramName+")",
					wtId:data[editIndex].paramName,
					formulaKey:"W_"+data[editIndex].paramName,
					type:1
				});
				$scope.sfc.putLabelToBuffer(1, "W_"+data[editIndex].paramName, data[editIndex].paramDescr+"("+data[editIndex].paramName+")");
			}
			$scope.formula.inputList = inputList;
			
			//build up operator list
			$scope.formula.operatorList = formulaEditor.createOperatorItems();
			
			var _buildFormulaKey = function(data){
				var key = data.name;
				key = key + "( ";
				if( data.paramList ){
					for(var i = 0; i < data.paramList.length; i++){
						key +=  data.paramList[i].name;
						
						if(i < data.paramList.length - 1){
							key += " , ";
						}
					}
				}
				key = key + " )";
				return key;
			};
			
			//build up function list
			FunctionServiceForSchema.getFunctions($scope,$http,function(data){
				$scope.formula.functionList = [];
				if(data && data.length){
					for(var i = 0 ; i< data.length; i++){
						$scope.formula.functionList.push({
							displayLabel:_buildFormulaKey(data[i]),
							formulaKey:_buildFormulaKey(data[i]),
							type:3,
							paramList:data[i].paramList
						});
						
						$scope.sfc.putFuncParamsToBuffer(data[i].name, data[i].paramList);
					}
				}
			});
			
			//parse formula line
			if(data[editIndex].formulaLines){
				$scope.sfc.buildUIData(data[editIndex].formulaLines);
			}else{
				$scope.sfc.buildUIData(null);
			}
					
			editorModal = $modal({scope: $scope, template: 'ui/payroll/pages/payrollSchemaFormulaEditor.html?sdate='+new Date().toTimeString(), backdrop:"static", show: false,animation : "am-fade-and-slide-top"});
			$scope.formula.title = "Edit Formula with output :" + entity.paramDescr;
			$scope.formula.outputWT = entity.paramDescr;
			
			$scope.styleScrollBar = function(){
				$(".inputP").mCustomScrollbar({
					theme:"dark"
				});
			};
			//show modal
			editorModal.$promise.then(editorModal.show).then($scope.styleScrollBar);
		},
		scrollUp : function(){
			$("#main_content").scrollTop(200);
		},
		selectParam : function(row){
			
			$scope.wtSearch.result = [];
			$scope.schemaStepFlow.editingIndex = row.entity.uiSequence - 1;
		
			wtPickModal = $modal({scope: $scope, template: 'ui/payroll/pages/wageTypeConfSearch.html?sdate='+new Date().toTimeString(), show: false,animation : "am-fade-and-slide-top"});
			$scope.wtsearch_title = "Wage Type Search";
			//show modal
			wtPickModal.$promise.then(wtPickModal.show);
			
			if( row.entity.isInput == "input"){
				$scope.wtSearch.type = "";
			}else{
				$scope.wtSearch.type = "S";
			}
		}
	};
	
	$scope.wtSearch = {
		wagetypeId:"",
		description:"",
		type:"",
		applySearch:function(){
			var searchBean = {
					wagetypeId : this.wagetypeId,
					descr: this.description,
					type: this.type
			};
			$http.post(g_contextpath + "/rest/wagetypeconf/search",JSON.stringify(searchBean),{}).success(function(data, status){
				if(data){
					$scope.wtSearch.result = data;
				}
			}).error(function(data, status) {
		          
		    });
		},
		selectResult : function(index){
			for(var i = 0 ; i < $scope.wtSearch.result.length; i++){
				$scope.wtSearch.result[i].selected = false;
			}
			$scope.wtSearch.result[index].selected = true;
		},
		
		proceedOK : function(){
			var selData = null;
			for(var i = 0 ; i < $scope.wtSearch.result.length; i++){
				if( $scope.wtSearch.result[i].selected){
					selData = $scope.wtSearch.result[i];
					break;
				}
			}
			
			if(selData){
				
				var flowData = $scope.schemaStepFlow.gridOptions.data[$scope.schemaStepFlow.editingIndex];
				flowData.paramName = selData.wagetypeId;
				flowData.paramDescr = selData.description;
				flowData.paramType = selData.type == "S" ? "Secondory" : "Primary";
				
				//update the selection to step
				var stepData = $scope.schemaStep.gridOptions.data[$scope.schemaStep.editingIndex];
				if(flowData.isInput == "input"){
					stepData.stepInput[flowData.sequence].paramDescr = flowData.paramDescr;
		    		stepData.stepInput[flowData.sequence].paramName = flowData.paramName;
		    		stepData.stepInput[flowData.sequence].paramType = flowData.paramType;
				}else{
					stepData.stepOutput[flowData.sequence].paramDescr = flowData.paramDescr;
		    		stepData.stepOutput[flowData.sequence].paramName = flowData.paramName;
		    		stepData.stepOutput[flowData.sequence].paramType = flowData.paramType;
				}
				if(wtPickModal){
					wtPickModal.hide();
				}
			}
		}
	};
	
	$scope.formula = {
		title:"",
		outputWT:"",
		lines:[],
		tempParamList:[],
		isExpertMode:false,
		editingTempParam:false,
		tempParamTooltip:"Temparary parameter will not to be persisted, please create it when needed",
		toggleCollapse : function(divId){
			$("#"+divId).toggle();
		},
		savetoMem : function(){
			//proceed with single step save
			var editIndex = $scope.schemaStepFlow.editingIndex;
			var selFlowData = $scope.schemaStepFlow.gridOptions.data[editIndex];
			selFlowData.formulaLines = $scope.sfc.convertUIToDB();
			
			var stepData = $scope.schemaStep.gridOptions.data[$scope.schemaStep.editingIndex];
			
			
//			if(!selFlowData.originIndex){
//				//stepout data previous is empty
//				selFlowData.originIndex = stepData.stepOutput.length - 1;
//			}
			stepData.stepOutput[selFlowData.sequence].formulaLines = selFlowData.formulaLines;
			stepData.stepOutput[selFlowData.sequence].paramName = selFlowData.paramName;
			stepData.stepOutput[selFlowData.sequence].paramDescr = selFlowData.paramDescr;
			
			if(editorModal){
				editorModal.hide();
			}
		},
		addTempParam : function(){
			this.newTempParam = "";
			this.editingTempParam = true;
		},
		saveTempParam : function(){
			if(this.newTempParam){
				this.tempParamList.push({
					displayLabel:this.newTempParam,
					formulaKey:"T_"+this.newTempParam.toUpperCase()
				});
				this.editingTempParam = false;
			}
		},
		cancelTempParam : function(){
			this.newTempParam = "";
			this.editingTempParam = false;
		},
		switchMode : function(){
			if(this.isExpertMode){
				this.isExpertMode = false;
			}else{
				this.formattedLine = $scope.sfc.getFormattedLine();
				this.isExpertMode = true;
			}
			
		}
	};
	
	
	$scope.schemaStepFlow.gridOptions.onRegisterApi = function(gridApi){
      //set gridApi on scope
      $scope.schemaStepFlow.gridApi = gridApi;
      gridApi.selection.on.rowSelectionChanged($scope,function(row){
    	  $scope.schemaStepFlow.rowSelection = $scope.schemaStepFlow.gridOptions.data[row.index];
      });
    };
    
    $scope.schemaStep.gridOptions.onRegisterApi = function(gridApi){
        //set gridApi on scope
        $scope.schemaStep.gridApi = gridApi;
        gridApi.selection.on.rowSelectionChanged($scope,function(row){
   
          if(row.isSelected){
          	$scope.schemaStep.rowSelection = $scope.schemaStep.gridOptions.data[row.index];
          	$scope.schemaStep.editingIndex = row.index;
          	$scope.schemaStepFlow.populateData($scope.schemaStep.rowSelection);
          }
          
        });
        
      };
    
    $scope.setWageTypeDropdownData = function(data){
    	$scope.schemaStepFlow.gridOptions.columnDefs[1].editDropdownOptionsArray = data;
    };
    
    //load wagetype
  	hocAdminApp.g_wageTypeData = [];
  	WageTypeServiceForSchema.getWageTypes($scope,$http,"",function(data){
  		
  		for(var i = 0 ; i < data.length; i++){
  			var vdata = {
  				id: data[i].wagetypeId,
  				description:data[i].description
  			};
  			hocAdminApp.g_wageTypeData.push(vdata);
  		}
  		
  		
 
  	});
  	
  	hocAdminApp.g_second_wageTypeData = [];
  	WageTypeServiceForSchema.getWageTypes($scope,$http,"S",function(data){
  		
  		for(var i = 0 ; i < data.length; i++){
  			var vdata = {
  				id: data[i].wagetypeId,
  				description:data[i].description
  			};
  			hocAdminApp.g_second_wageTypeData.push(vdata);
  		}
  	});
  	
    msgBus.onMsg('page_resized', $scope, function() {
    	$scope.schemaStep.gridApi.grid.handleWindowResize();
    });
    
    var getMaximumStepFlowSequence = function(type){
    	
		var data = $scope.schemaStepFlow.gridOptions.data;
		var newIndex = 0;
		var max = 0;
		
		var selectRow = null;
		if($scope.schemaStepFlow.rowSelection && $scope.schemaStepFlow.rowSelection.uiSequence){
			selectRow = $scope.schemaStepFlow.rowSelection;
		}
		if(selectRow == null){
			if(data){
				if(type == "input"){
					for(var i = 0 ; i < data.length; i++){
						if(data[i].isInput == "input"){
							if(max < data[i].uiSequence){
								max = data[i].uiSequence;
							}
						}
					}
				}else{
					for(var i = 0 ; i < data.length; i++){
						if(max < data[i].uiSequence){
							max = data[i].uiSequence;
						}
					}
				}
				newIndex = max + 1;
			}else{
				newIndex = 1;
			}
			//if newIndex is in the middle need to shift the content
			shiftStepFlowData(max);
		}else{
			var isInput = selectRow.isInput;
			if(type == "input"){
				if(isInput == "input"){
					newIndex = selectRow.uiSequence + 1;
					shiftStepFlowData(selectRow.uiSequence);
				}
				if(isInput == "output"){
					for(var i = 0 ; i < data.length; i++){
						if(data[i].isInput == "input"){
							if(max < data[i].uiSequence){
								max = data[i].uiSequence;
							}
						}
					}
					newIndex = max + 1;
					shiftStepFlowData(max);
				}
			}
			if(type == "output"){
				if(isInput == "input"){
					for(var i = 0 ; i < data.length; i++){
						if(max < data[i].uiSequence){
							max = data[i].uiSequence;
						}
					}
					newIndex = max + 1;
					shiftStepFlowData(max);
				}
				
				if(isInput == "output"){
					newIndex = selectRow.uiSequence + 1;
					shiftStepFlowData(selectRow.uiSequence);
				}
			}
		}
		
		return newIndex;
	};
	
	var shiftStepFlowData = function(index){
		var data = $scope.schemaStepFlow.gridOptions.data;
		var p1 = data.slice(0,index);
		var p2 = [{uiSequence:index + 1}];
		var p3 = data.slice(index);
		if(p3.length > 0){
			for(var i = 0 ; i < p3.length; i++){
				p3[i].uiSequence = index + i + 2;
			}
		}
		$scope.schemaStepFlow.gridOptions.data = p1.concat(p2).concat(p3);
	};
	
	var removeOneStepFlowData = function(index){
		var data = $scope.schemaStepFlow.gridOptions.data;
		var p1 = [];
		if(index > 0){
			p1 = data.slice(0,index);
		}
		var p2 = data.slice(index,index + 1);
		var p3 = [];
		if(index + 1 < data.length){
			p3 = data.slice(index + 1, data.length);
		}
		if(p3.length > 0){
			for(var i = 0; i < p3.length; i++){
				p3[i].uiSequence = p3[i].uiSequence - 1;
			}
		}
		
		$scope.schemaStepFlow.gridOptions.data = p1.concat(p3);
	}
	
	var getMaximumSequence = function(){
		var data = $scope.schemaStep.gridOptions.data;
		if(data){
			var max = 0;
			for(var i = 0 ; i < data.length; i++){
				if(max < data[i].stepSeq){
					max = data[i].stepSeq;
				}
			}
			return max + 1;
		}else{
			return 1;
		}

	};
	
	var reorganizeStepDBSequence = function(stepData){
		if(stepData){
			for(var i = 0; i < stepData.length; i++){
				stepData[i].sequence = i;
			}
		}		
	}
	
	var reorganizeStepFlowDBSequence = function(stepFlowData){
		if(stepFlowData){
			var firstOutputIndex;
			for(var i = 0 ; i < stepFlowData.length; i++){
				if(stepFlowData[i].isInput == "input"){
					stepFlowData[i].sequence = i;
				}
				if(stepFlowData[i].isInput == "output"){
					firstOutputIndex = i;
					break;
				}
			}
			for(var i = firstOutputIndex; i < stepFlowData.length; i++){
				stepFlowData[i].sequence = i - firstOutputIndex;
			}
		}
		
	};
	
	$scope.schema = {
		schemaData: [],
		selectedSchema:null,
		selectedSchemaWithSteps:null,
		stepFlowBuffer:{},
		loadSchema : function(){
			var that = this;
			$http({
			    url: g_contextpath + "/rest/schema/list", 
			    method: "GET",
			    params: {}
			 }).success(function(data, status) {
				 that.schemaData = data;
				 
		     }).error(function(data, status) {
		          
		     });
		},
		
		loadSingleSchema : function(){
			var that = this;
			if(!this.selectedSchema.configId){
				return;
			}
			$scope.schemaStep.gridOptions.data = [];
			$http({
			    url: g_contextpath + "/rest/schema/" + this.selectedSchema.configId, 
			    method: "GET",
			    params: {}
			 }).success(function(data, status) {
				 that.selectedSchemaWithSteps = data;
				 $scope.schemaStep.gridOptions.data = $scope.schemaStep.gridOptions.data.concat(data.stepList);
				 //format the step input and stepoutput
				 var stepData = $scope.schemaStep.gridOptions.data;
				 for(var i = 0 ; i < stepData.length; i++){
					 var lStep = stepData[i];
					 var formattedInput = "";
					 var formattedOutput = "";
					 if(lStep.stepInput){
						 for(var j = 0; j < lStep.stepInput.length; j++){
							 if(!formattedInput){
								 formattedInput = lStep.stepInput[j].paramName;
							 }else{
								 formattedInput = formattedInput + "," + lStep.stepInput[j].paramName;
							 }
							 
						 }
					 }
					 if(lStep.stepOutput){
						 for(var j = 0; j < lStep.stepOutput.length; j++){
							 if(!formattedOutput){
								 formattedOutput = lStep.stepOutput[j].paramName;
							 }else{
								 formattedOutput = formattedOutput + "," + lStep.stepOutput[j].paramName;
							 }
							 
						 }
					 }
					 lStep.stepInputFormatted = formattedInput;
					 lStep.stepOutputFormatted = formattedOutput;
					 $scope.schemaStep.gridOptions.data[i] = lStep;
				 }
				 
				 $scope.schemaStepFlow.gridOptions.data = [];
		     }).error(function(data, status) {
		          
		     });
		},
		saveConfig : function(){
			//the first time the selectedSchema can be only a configId
			if(this.selectedSchema && !this.selectedSchema.configId){
				var configInput = this.selectedSchema;
				for(var i = 0; i < this.schemaData.length; i++){
					if(this.schemaData[i].configId == configInput){
						this.selectedSchema = this.schemaData[i];
						break;
					}
				}
			}
			
			if(!this.selectedSchema.configId){
				return;
			}
			
			var configData = {};
			configData.configId = this.selectedSchema.configId;
			configData.description = this.selectedSchema.description;
			configData.stepList = $scope.schemaStep.gridOptions.data;
			
			$http.post(g_contextpath + "/rest/schema/save",JSON.stringify(configData),{}).success(function(data, status){
				if(data && data.code == 0){
					var myAlert = $alert({title: 'Save Successful', content: 'Your configuration is now saved !', placement: 'top', type: 'info', show: true, duration:2});
					
					$scope.schema.loadSingleSchema();
				}
			}).error(function(data, status) {
		          
		    });
		}
	};

	$scope.schema.loadSchema();
	
	$scope.$on('$typeahead.select', function(event,data) {
		$scope.schema.loadSingleSchema();
		$scope.schemaStep.rowSelection = {};
    });
	
	$scope.dropItems = [];
	$("#main_content").scrollTop(0);
	jQuery.extend(this, formulaEditor);
});



hocAdminApp.filter("wageTypeFilter",function(){
	return function(paramName){
		var dlist = hocAdminApp.g_wageTypeData;
		for(var i = 0; i < dlist.length; i++){
			if(dlist[i].id == paramName){
				return dlist[i].description;
			}
		}
		return paramName;
	};
}).filter("inputTypeFilter",function(){
	return function(inputVal){
		return inputVal == 1 ? "input" : ( inputVal == 2 ? "ouput" : "");
	};
}).filter("dataTypeFilter",function(){
	return function(dataTypeVal){
		return dataTypeVal == 1 ? "Custom Input" : (dataTypeVal == 2 ? "Fomula" : "");
	};
});
hocAdminApp.factory('WageTypeServiceForSchema',function(){
	var wtService = {
	
		getWageTypes : function($scope, $http, type, callback){
			var wUrl = g_contextpath + "/rest/wagetypeconf/list";
			if(type){
				wUrl = wUrl + "?type="+type;
			}
			$http({
			    url: wUrl, 
			    method: "GET",
			    params: {}
			 }).success(function(data, status) {
				 if(data){
					 if(callback){
						 callback(data);
					 }
				 }
		     }).error(function(data, status) {
		          
		     });
		}
	};
	
	return wtService;
});


hocAdminApp.factory('FunctionServiceForSchema',function(){
	
	var funcService = {
		getFunctions : function($scope, $http,callback){
			$http({
			    url: g_contextpath + "/rest/function/list", 
			    method: "GET",
			    params: {}
			 }).success(function(data, status) {
				 if(data){
					 if(callback){
						 callback(data);
					 }
				 }
		     }).error(function(data, status) {
		          
		     });
		}
	};
	
	return funcService;
});