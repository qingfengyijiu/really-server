function FilterDialog() {
	this.dialogView = $("<div class='filter-dialog'></div>");
	this.filterContentView = $("<table class='filter-content'></table>");
	this.filterContentHeaderView = $("<thead><tr></tr></thead>");
	this.filterContentBodyView = $("<tbody></tbody>");
	this.filterBtnView = $("<button name='filter-btn' class='ui-button'></button>");
	this.filterInputView = $("<input type='text' name='filter-input' class='filter-input'/>");
	this.filterTitleView = $("<span name='filter-title'></span>");
	this.confirmBtnView = $("<button name='filter-confirm' class='ui-button'></button>");
	this.cancelBtnView = $("<button name='filter-cancel' class='ui-button'></button>");
	this.selectAllView = $("<input type='checkbox' name='select-all'/>");
}
$.extend(FilterDialog.prototype, {
	_initHeader : function() {
		var i,
			headerModel = $("<th class='content-header'></th>");
		headerModel.clone().css("width", "20px").append(this.selectAllView).appendTo(this.filterContentHeaderView);
		for(i = 0; i < this.headerNames.length; i++) {
			headerModel.clone().attr("name", "header-" + this.headerKeys[i]).css("width", this.rowWidths[i]).text(this.headerNames[i]).appendTo(this.filterContentHeaderView);
		}
		this.filterContentHeaderView.appendTo(this.filterContentView);
	},
	_initData : function() {
		var i,
			j,
			dataType,
			trModel = $("<tr></tr>"),
			selectItemModel = $("<input type='checkbox' class='select-item'/>"),
			tdModel = $("<td></td>"),
			currentData,
			currentTrView,
			currentTdView;
		for(i = 0; i < this.datas.length; i++) {
			currentTrView = trModel.clone();
			tdModel.clone().css("width", "20px").append(selectItemModel.clone()).appendTo(currentTrView);
			for(j = 0; j < this.datas[i].length; j++) {
				dataType = this.dataTypes[j];
				currentData = this.datas[i][j];
				currentTdView = tdModel.clone().attr("name", this.headerKeys[i]).css("width", this.rowWidths[j]);
				if(dataType == "html") {
					currentTdView.html(currentData);
				} else {
					currentTdView.text(currentData);
				}
				currentTrView.append(currentTdView);
			}
			currentTrView.appendTo(this.filterContentBodyView);
		}
		this.filterContentBodyView.appendTo(this.filterContentView);
	},
	_initTable : function() {
		this._initHeader();
		this._initData();
	},
	_initFilterButton : function() {
		this.filterBtnView.text(this.filterBtnName);
		this.filterBtnView.click(this.filterBtnCallback);
	},
	_initHeaderTitle : function() {
		this.filterTitleView.text(this.filterTitle);
	},
	_initConfirmBtn : function() {
		this.confirmBtnView.text(this.confirmBtnName).click(this.confirmCallback);
	},
	_initCancelBtn : function() {
		this.cancelBtnView.text(this.cancelBtnName).click(this.cancelCallback);
	},
	_initView : function() {
		var filterTitleZone,
			filterConditionZone,
			filterBtnZoneView;
		this._initHeaderTitle();
		this._initFilterButton();
		filterTitleZone = $("<div class='header-item title-zone'></div>").append(this.filterTitleView);
		filterConditionZone = $("<div class='header-item condition-zone'></div>").append(this.filterInputView).append(this.filterBtnView);
		filterHeaderView = $("<div class='dialog-header'></div>").append(filterTitleZone).append(filterConditionZone).append($("<div class='ui-helper-clearfix'></div>")).appendTo(this.dialogView);
		this._initTable();
		this.filterContentView.appendTo(this.dialogView);
		this._initConfirmBtn();
		this._initCancelBtn();
		filterBtnZoneView = $("<div class='btn-zone'></div>").append(this.confirmBtnView).append(this.cancelBtnView);
		$("<div class='dialog-footer'></div>").append(filterBtnZoneView).append($("<div class='ui-helper-clearfix'></div>")).appendTo(this.dialogView);
		this.dialogView.width(this.width).height(this.height).css({"top":this.top, "left":this.left}).appendTo("body");
	},
	init : function(options) {
		var i,
			rowWidth;
		this.width = options.width;
		this.height = options.height;
		this.left = options.left;
		this.top = options.top;
		this.headerKeys = options.headerKeys || [];
		this.headerNames = options.headerNames || [];
		this.datas = options.datas || [];
		this.dataTypes = options.dataTypes || [];
		this.rowWidths = options.rowWidths || [];
		if(this.rowWidths == null || this.rowWidths.length == 0) {
			rowWidth = (this.width - 40) / this.headerKeys.length;
			for(i = 0; i < this.headerKeys.length; i++) {
				this.rowWidths[i] = rowWidth;
			}
		}
		this.filterBtnName = options.filterBtnName;
		this.filterBtnCallback = options.filterBtnCallback;
		this.filterTitle = options.filterTitle;
		this.confirmBtnName = options.confirmBtnName;
		this.cancelBtnName = options.cancelBtnName;
		this.confirmCallback = options.confirmCallback;
		this.cancelCallback = options.cancelCallback;
		this._initView();
		this._register();
	},
	show : function() {
		this.dialogView.show();
	},
	hide : function() {
		this.dialogView.hide();
	},
	clear : function() {
		this.filterContentBodyView.remove();
		this.filterContentBodyView = $("<tbody></tbody>");
	},
	restLocation: function(location) {
		this.left = location.left;
		this.top = location.top;
		this.dialogView.css({"left" : location.left, "top" : location.top});
	},
	resetData : function(data) {
		this.clear();
		this.datas = data;
		this._initData();
	},
	getSelectedColumns : function() {
		var columnNums = [];
		$(".select-item", this.filterContentBodyView).each(function(index) {
			if($(this).prop("checked")) {
				columnNums.push(index);
			}
		});
		return columnNums;
	},
	_register : function() {
		this.selectAllView.on("change", function() {
			var allItems = $(".select-item", this.filterContentBodyView),
				checkedItems = $(".select-item:checked", this.filterContentBodyView),
				notCheckedItems = $(".select-item:not(:checked)", this.filterContentBodyView);
			if(checkedItems.size() < allItems.size()) {
				notCheckedItems.each(function() {
					$(this).prop("checked", true);
				});
			} else {
				checkedItems.each(function() {
					$(this).prop("checked", false);
				});
			}
		});
	}
});
/*<div class="filter-dialog">
<div class="dialog-header">
	<div class="header-item title">组织</div>
	<div class="header-item filter-zone">
		<input type="text" name="filter-input"/>
		<button name="filter-btn">过滤</button>
	</div>
	<div class="ui-helper-clearfix"/>
</div>
<div class="dialog-body">
	<table class="filter-content">
		<thead>
			<tr>
				<th><input type="checkbox" class="select-all"/></th>
				<th>item1</th>
			</tr>
		</thead>
		<tbody>
			<tr>
				<td><input type="checkbox" class="select-item"/></td>
				<td>test</td>
			</tr>
		</tbody>
	</table>
</div>
</div>*/