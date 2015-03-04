package com.mycompany.helloworld;

import javax.servlet.annotation.WebServlet;

import com.vaadin.annotations.Theme;
import com.vaadin.annotations.VaadinServletConfiguration;
import com.vaadin.annotations.Widgetset;
import com.vaadin.data.Item;
import com.vaadin.data.util.IndexedContainer;
import com.vaadin.event.ItemClickEvent;
import com.vaadin.event.ItemClickEvent.ItemClickListener;
import com.vaadin.server.VaadinRequest;
import com.vaadin.server.VaadinServlet;
import com.vaadin.ui.Button;
import com.vaadin.ui.Component;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Notification;
import com.vaadin.ui.Table;
import com.vaadin.ui.TextField;
import com.vaadin.ui.UI;
import com.vaadin.ui.VerticalLayout;
import java.util.Iterator;

/**
 *
 */
@Theme("mytheme")
@Widgetset("com.mycompany.helloworld.MyAppWidgetset")
public class MyUI extends UI implements Button.ClickListener {

    private HorizontalLayout mainLayout = new HorizontalLayout();
    private VerticalLayout lblVertical = new VerticalLayout();
    private String[] labels = new String[] {"Firstname","Lastname","Address","Email","Phone"};
    private TextField[] txtFields = new TextField[labels.length];

    private Button addButton = new Button("Add Contact");
    private Table contactList = new Table();
    
    @Override
    public void buttonClick(Button.ClickEvent event) {
        Notification.show("Button pressed");
    }

    @Override
    protected void init(VaadinRequest vaadinRequest) {
        this.setContent(mainLayout);
        
        for(int i=0; i<labels.length; i++) {
            txtFields[i] = new TextField(labels[i]);
            lblVertical.addComponent(txtFields[i]);
        }
       
        lblVertical.addComponent(addButton);
        
        lblVertical.setMargin(true);
        lblVertical.setSpacing(true);
        
        mainLayout.addComponent(lblVertical);
        mainLayout.addComponent(contactList);
        
        // Alternative implementation for addButton event handler
        //addButton.addClickListener(this);
        
        // Anonymous event handler for addButton
        addButton.addClickListener(new Button.ClickListener() {
            @Override
            public void buttonClick(Button.ClickEvent event) {
                
                StringBuilder errMessage = new StringBuilder();
                Iterator<Component> iter = lblVertical.iterator();
                while(iter.hasNext()) {
                    Component c = iter.next();
                    if(c instanceof TextField) {
                        TextField a = (TextField)c;
                        if(a.getValue().isEmpty()) {
                            errMessage.append(a.getCaption() + " ");
                        }
                    }
                }
                
                String err = errMessage.toString();
                
                if(err.isEmpty()) {
                    addItemToTable();
                    for(TextField t: txtFields) {
                        t.setValue("");
                    }
                }
                else {
                    Notification.show(err + "field(s) empty");
                }
            }
        });
        
        contactList.addItemClickListener(new ItemClickListener() {
            @Override
            public void itemClick(ItemClickEvent event) {
                Notification.show(event.getItem().getItemProperty("Firstname").getValue().toString());
            }
        });
        
        this.initTableData();
    }
    
    private void initTableData(){
   
        contactList.setContainerDataSource(createDummyDataSource());
        contactList.setVisibleColumns(new String[]{"Firstname","Lastname","Address","Email","Phone"});
    }
    
    private void addItemToTable(){
     
        Object objectId = contactList.addItem();
        Item row = contactList.getItem(objectId);
        
        for(int i=0; i<labels.length; i++) {
            row.getItemProperty(labels[i]).setValue(txtFields[i].getValue());
        }
    }
    
    private IndexedContainer createDummyDataSource() {
        
        IndexedContainer ic = new IndexedContainer();
        for(int i=0; i<labels.length; i++) {
            ic.addContainerProperty(labels[i], String.class, "");
        }
         return ic;
    }
    
    @WebServlet(urlPatterns = "/*", name = "MyUIServlet", asyncSupported = true)
    @VaadinServletConfiguration(ui = MyUI.class, productionMode = false)
    public static class MyUIServlet extends VaadinServlet {
    }
    
    
}