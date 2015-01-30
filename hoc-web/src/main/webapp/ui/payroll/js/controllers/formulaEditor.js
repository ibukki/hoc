var formulaEditor = {
	fLines: [],
	selItemIndex:null,
	selLineNum:null,
	bufferLabels:{},
	bufferFuncParams:{},
	itemDropdown : [
       {
         "text": "edit",
         "click": "sfc.editItem()"
       },
       {
         "text": "remove",
         "click": "sfc.removeItem()"
       }
	],
   	buildPperateFormulaLineDP:function(index){
   		var dp = [];
   		dp.push({
			"text":"add",
			"click":"sfc.addEmptyLine("+index+")"
		});
   		dp.push({
			"text":"remove",
			"click":"sfc.removeLine("+index+")"
		});
   		return dp;
   	},
	buildItemDropDown : function(item){
		var dp = [];
		if(item.type == 1){
			return this.itemDropdown;
		}else if(item.type == 3){
			item.paramList = this.getParamListForFunc(item);
			if(item.paramList){
				dp.push({
					"text":"edit params",
					"click":"sfc.editFunctionParams()"
				});
			}
			dp.push({
				"text": "remove",
		        "click": "sfc.removeItem()"
			});
			return dp;
		}else{
			return this.itemDropdown;
		} 
	},
	initStageData: function(){
		
	},
	dragStart : function(input){
		var dragItem = $(".ui-draggable-dragging");
		
		//get the last dropItems
		
	},
	dropCallback : function(event, ui ,itm, index){
		//this here refer to whole scope
		var target = event.target;
		$(target).removeClass("dropOver");
		
		//check target item
		var editLn = this.sfc.fLines[this.ln.sequence];
		var lineItems = editLn.items;
		var targetItem = lineItems[index];
		if(targetItem.displayLabel){
			//add the item before it
			editLn.addItem(itm,index);
		}else{
			$.extend(targetItem, itm);
			//append a new item to the line
			editLn.addItem({});
		}
		
	},
	
	onParamDropCallback: function(event,ui, param){
		
	},
	putLabelToBuffer : function(type,formulaKey, label){
		if(!this.bufferLabels[type]){
			this.bufferLabels[type] = {};
		}
		this.bufferLabels[type][formulaKey] = label;
	},
	
	putFuncParamsToBuffer : function(funcName, paramList){
		this.bufferFuncParams[funcName] = paramList;
	},
	editItem:function( ){
		var selItem = this.fLines[this.selLineNum].items[this.selItemIndex];
		selItem.isInput = true;
	},
	
	addEmptyLine: function(index){
		var i = index;
		var end = this.fLines.length;
		var left = this.fLines.slice(0,i);
		var ist = new FormulaLine(i);
		var right = this.fLines.slice(i);
		if(right.length > 0){
			for(var i = 0 ; i < right.length; i++){
				right[i].sequence = index + i + 1;
			}
		}
		this.fLines = left.concat(ist).concat(right);
	},
	
	removeLine : function(index){
		this.fLines.splice(index,1);
		var len = this.fLines.length;
		for(var i = index; i < len; i++){
			this.fLines[i].sequence = this.fLines[i].sequence - 1;
		}
		
	},
	editFunctionParams:function(){
		var selItem = this.fLines[this.selLineNum].items[this.selItemIndex];
		
		//parse the parameter
		var fulKey = selItem.formulaKey;
		fulKey = fulKey.substring(fulKey.indexOf("(")).replace("(","").replace(")","");
		var values = fulKey.split(",");
		
		for(var i = 0 ; i < selItem.paramList.length; i++){
			var param = selItem.paramList[i];
			if(param.type == "VAR"){
				param.dropItem = param.dropItem || {};
				param.dropItem.formulaKey = this.trim(values[i]);
			}
			
			if(param.type == "NUMBER"){
				param.value = this.trim(values[i]);
			}
			
			if(param.type == "STRING"){
				param.value = this.trim(values[i].replace(/\"/g,""));
			}
		}
		selItem.showParamEdit = true;
	},
	
	trim : function(s){
		return s.replace(/(^\s*)|(\s*$)/g, "");
	},
	removeItem: function(){
		var selLine = this.fLines[this.selLineNum];
		if(this.selItemIndex >= selLine.items.length - 1){
			selLine.removeItem(this.selItemIndex);
			selLine.addItem({});
		}else{
			selLine.removeItem(this.selItemIndex);
		}
	},
	
	finishItemEdit: function(){
		var selItem = this.fLines[this.selLineNum].items[this.selItemIndex];
		if(selItem.formulaKey){
			selItem.displayLabel = selItem.formulaKey;
			selItem.isInput = false;
			this.fLines[this.selLineNum].addItem({});
		}else{
			//no input , switch to drag mode
			selItem.isInput = false;
		}
	},
	onDropOver : function(event, ui, ln, itm){
		//this here refer to whole scope
		var target = event.target;
		$(target).addClass("dropOver");
		
	},
	
	onDropOut : function(event, ui, ln, itm){
		//this here refer to whole scope
		var target = event.target;
		$(target).removeClass("dropOver");
		
	},
	
	toggleItemSel : function(itemIndex, ln){
		for(var i = 0 ; i < this.fLines.length; i++){
			var itms = this.fLines[i].items;
			for(var k = 0 ; k < itms.length; k++){
				itms[k].selected = false;
			}
		}
		
		this.selLineNum = ln.sequence;
		this.selItemIndex = itemIndex;
		var selItem = this.fLines[ln.sequence].items[itemIndex];
		if(selItem.selected){
			selItem.selected = false;
		}else{
			selItem.selected = true;
		}
		
	},
	initEmptyLines : function(){
		var s = this.fLines.length;
		for(var i = s ; i < 10 + s; i++){
			this.fLines.push(new FormulaLine(i));
		}
	},
	
	buildUIData : function(dbLines){
		this.fLines = [];
		if(dbLines){
			for(var i = 0 ; i < dbLines.length; i++){
				var uiLine = new FormulaLine(i);
				if(dbLines[i].items){
					var dbItems = dbLines[i].items;
					uiLine.items = [];
					for(var j = 0; j < dbItems.length;j++){
						
						uiLine.addItem(new FormulaItem({
							key:dbItems[j].formulaKey,
							label:this.getItemLabel(dbItems[j].formulaKey, dbItems[j].type),
							type:dbItems[j].type
						}));
					}
					uiLine.addItem({});
				}
				
				this.fLines.push(uiLine);
			}
		}
		
		//fill the table with another 10 empty lines
		this.initEmptyLines();
	},
	
	convertUIToDB : function(){
		var dbLines = [];
		for(var i = 0; i < this.fLines.length; i++){
			var items = this.fLines[i].items;
			if(items && items[0].formulaKey){
				var dbLn = {
						items:[]
				};
				for(var j = 0 ; j < items.length; j++){
					if(items[j].formulaKey){
						dbLn.items.push({
							formulaKey: items[j].formulaKey,
							type:items[j].type
						});
					}
				}
				dbLines.push(dbLn);
			}
		}
		return dbLines;
	},
	getItemLabel : function(key, type){
		if(this.bufferLabels[type] && this.bufferLabels[type][key]){
			return this.bufferLabels[type][key];
		}
		return key;
	},
	
	getParamListForFunc: function(dbItem){
		if(dbItem.type == 3){
			var funcName = dbItem.formulaKey;
			funcName = funcName.substring(0,funcName.indexOf("("));
			if(this.bufferFuncParams){
				return this.bufferFuncParams[funcName];
			}
		}else{
			return [];
		}
	},
	getFormattedLine : function(){
		var formula = "";
		var sline = null;
		for(var i = 0 ; i < this.fLines.length; i++){
			sline = "";
			var items = this.fLines[i].items;
			if(items && !items[0].formulaKey){
				continue;
			}
			for(var j = 0 ; j < items.length; j++){
				if(items[j].formulaKey){
					sline += " " + items[j].formulaKey;
				}
			}
			formula += sline + ";" + "\n";
		}
		return formula;
		
	},
	createOperatorItems : function(){
		var vItems = [];
		vItems.push(new FormulaItem({
			key:"+",
			label:"+",
			type:2,
			icon:""
		}));
		vItems.push(new FormulaItem({
			key:"-",
			label:"-",
			type:2,
			icon:""
		}));
		vItems.push(new FormulaItem({
			key:"*",
			label:"*",
			type:2,
			icon:""
		}));
		vItems.push(new FormulaItem({
			key:"/",
			label:"/",
			type:2,
			icon:""
		}));
		vItems.push(new FormulaItem({
			key:"(",
			label:"(",
			type:2,
			icon:""
		}));
		vItems.push(new FormulaItem({
			key:")",
			label:")",
			type:2,
			icon:""
		}));
		vItems.push(new FormulaItem({
			key:"{",
			label:"{",
			type:2,
			icon:""
		}));
		vItems.push(new FormulaItem({
			key:"}",
			label:"}",
			type:2,
			icon:""
		}));
		vItems.push(new FormulaItem({
			key:"=",
			label:"=",
			type:2,
			icon:""
		}));
		vItems.push(new FormulaItem({
			key:"==",
			label:"==",
			type:2,
			icon:""
		}));
		vItems.push(new FormulaItem({
			key:"!=",
			label:"!=",
			type:2,
			icon:""
		}));
		vItems.push(new FormulaItem({
			key:"&&",
			label:"&&",
			type:2,
			icon:""
		}));
		vItems.push(new FormulaItem({
			key:"||",
			label:"||",
			type:2,
			icon:""
		}));
		vItems.push(new FormulaItem({
			key:"!",
			label:"!",
			type:2,
			icon:""
		}));
		vItems.push(new FormulaItem({
			key:"?",
			label:"?",
			type:2,
			icon:""
		}));
		vItems.push(new FormulaItem({
			key:"if",
			label:"if",
			type:2,
			icon:""
		}));
		vItems.push(new FormulaItem({
			key:"else",
			label:"else",
			type:2,
			icon:""
		}));
		return vItems;
	},
	
	closeParamEdit : function(item){
		var valueList = [];
		//update back the item.paramList value to item formulaKey
		for(var i = 0 ; i < item.paramList.length; i++){
			var param = item.paramList[i];
			if(param.type == 'VAR'){
				if(param.dropItem && param.dropItem.formulaKey){
					valueList.push(param.dropItem.formulaKey);
				}else{
					valueList.push(param.name);
				}
			}
			if(param.type == 'NUMBER'){
				if(param.value){
					valueList.push(param.value);
				}else{
					valueList.push(param.name);
				}
				
			}
			
			if(param.type == "STRING"){
				if(param.value){
					valueList.push("\""+param.value+"\"");
				}else{
					valueList.push(param.name);
				}
			}
		
		}
		
		var paramString = valueList.join(" , ");
		var funcName = item.formulaKey;
		funcName = funcName.substring(0,funcName.indexOf("("));
		item.formulaKey = funcName + "(" + paramString + ")";
		item.displayLabel = item.formulaKey;
		
		item.showParamEdit = false;
	}
};


var FormulaLine = function(seq){
	
	this.sequence = seq;
	this.items = [new FormulaItem({index:0})];
	
	this.addItem = function(itm, idx){
		var newItm = new FormulaItem({});
		newItm.extend(itm);
		if(typeof idx == 'undefined'){
			newItm.index = this.items.length
			this.items.push(newItm);
		}else{
			var len = this.items.length;
			var left = this.items.slice(0,idx);
			var mid = newItm;
			var right = this.items.slice(idx,len);
			
			if(left.length > 0){
				mid.index = left[idx-1].index + 1;
			}else{
				mid.index = 0;
			}
			
			for(var i = 0; i < right.length; i++){
				right[i].index = right[i].index + mid.index;
			}
			
			this.items = left.concat(mid).concat(right);
		}
		
	};
	
	this.removeItem = function(remIndex){
		this.items.splice(remIndex,1);
	};
	
	
};


var FormulaItem = function(xargs){
	
	this.index = xargs.index;
	this.formulaKey = xargs.key;
	this.displayLabel = xargs.label;
	this.selected = xargs.sel || false;
	this.icon = xargs.icon;
	this.type = xargs.type;
	this.isInput = false;
	this.showParamEdit = false;
	this.paramList = xargs.paramList;
	
	this.getActions = function(){
		return this.actions;
	};
	
	this.extend = function(itm){
		return jQuery.extend(this,itm);
	};
};