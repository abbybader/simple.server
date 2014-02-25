/* global Ext, window */

Ext.onReady(function() {
      var fileMenu = new Ext.menu.Menu({
            items : []
          });

      var helpMenu = new Ext.menu.Menu({
            items : [{
                  text : 'Docs',
                  handler : function() {
                    var newWindow = window.open('', '_blank');
                    newWindow.location = '/docs';
                  }
                }]
          });

      var toolbar = new Ext.toolbar.Toolbar(Ext.apply({
            vertical : false,
            items : [{
                  text : 'File',
                  arrowCls : '',
                  tooltip : {
                    title : 'File'
                  },
                  menu : fileMenu
                }, {
                  xtype : 'tbfill'
                }, {
                  text : 'Help',
                  arrowCls : '',
                  menu : helpMenu
                }]
          }));

      var toolbarPanel = {
        region : 'north',
        layout : 'card',
        height : 30,
        activeItem : 0,
        border : true,
        items : [toolbar]
      };

      var mainPanel = new Ext.panel.Panel({
            split : true,
            frame : false,
            region : 'center',
            items : []
          });

      Ext.create('Ext.container.Viewport', {
            layout : 'border',
            items : [toolbarPanel, mainPanel],
            renderTo : Ext.getBody()
          });

    });
