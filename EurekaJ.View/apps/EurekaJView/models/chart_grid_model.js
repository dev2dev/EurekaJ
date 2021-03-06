// ==========================================================================
// Project:   EurekaJView.ChartGridModel
// Copyright: ©2010 My Company, Inc.
// ==========================================================================
/*globals EurekaJView */

/** @class

  (Document your Model here)

  @extends SC.Record
  @version 0.1
*/
EurekaJView.ChartGridModel = SC.Record.extend(
/** @scope EurekaJView.ChartGridModel.prototype */ {
	primaryKey: 'chart',
    chart: SC.Record.attr(Array),
    chartLabel: SC.Record.attr(String)
  	//label: SC.Record.attr(String),
	//data: SC.Record.attr(Array)

}) ;
